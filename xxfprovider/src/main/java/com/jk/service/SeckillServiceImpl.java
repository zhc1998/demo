package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import com.jk.dao.XxfDao;
import com.jk.dto.Exposer;
import com.jk.dto.SeckillExecution;
import com.jk.model.Members;
import com.jk.model.Orderone;
import com.jk.model.Seckill;
import com.jk.model.SeckillOrder;
import com.jk.enums.SeckillStatEnum;
import com.jk.exception.RepeatKillException;
import com.jk.exception.SeckillCloseException;
import com.jk.exception.SeckillException;
import com.jk.service.SeckillService;
import com.jk.service.ZhfService;
import com.jk.utillllll.Ordernumber;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @auther TyCoding
 * @date 2018/10/6
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //设置盐值字符串，随便定义，用于混淆MD5值
    private final String salt = "sjajaspu-i-2jrfm;sd";
    //设置秒杀redis缓存的key
    private final String key = "seckill";

    @Autowired
    private XxfDao xxfDao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Seckill> findAll() {
        List<Seckill> seckillList = redisTemplate.boundHashOps("seckill").values();
        if (seckillList == null || seckillList.size() == 0){
            //说明缓存中没有秒杀列表数据
            //查询数据库中秒杀列表数据，并将列表数据循环放入redis缓存中
            seckillList = xxfDao.findAll();
            for (Seckill seckill : seckillList){
                //将秒杀列表数据依次放入redis缓存中，key:秒杀表的ID值；value:秒杀商品数据
                redisTemplate.boundHashOps(key).put(seckill.getSeckillid(), seckill);
                logger.info("findAll -> 从数据库中读取放入缓存中");
            }
        }else{
            logger.info("findAll -> 从缓存中读取");
        }
        return seckillList;
    }

    @Override
    public Seckill findById(long seckillId) {
        return xxfDao.findById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) throws ParseException {
        Seckill seckill = (Seckill) redisTemplate.boundHashOps(key).get(seckillId);
        if (seckill == null) {
            //说明redis缓存中没有此key对应的value
            //查询数据库，并将数据放入缓存中
            seckill = xxfDao.findById(seckillId);
            if (seckill == null) {
                //说明没有查询到
                return new Exposer(false, seckillId);
            } else {
                //查询到了，存入redis缓存中。 key:秒杀表的ID值； value:秒杀表数据
                redisTemplate.boundHashOps(key).put(seckill.getSeckillid(), seckill);
                logger.info("RedisTemplate -> 从数据库中读取并放入缓存中");
            }
        } else {
            logger.info("RedisTemplate -> 从缓存中读取");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime =format.parse(format.format(format.parse(seckill.getStarttime())));
        Date endTime = format.parse(format.format(format.parse(seckill.getEndtime())));
        //获取系统时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转换特定字符串的过程，不可逆的算法
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    //生成MD5值
    private String getMD5(Long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    /**
     * 使用注解式事务方法的有优点：开发团队达成了一致约定，明确标注事务方法的编程风格
     * 使用事务控制需要注意：
     * 1.保证事务方法的执行时间尽可能短，不要穿插其他网络操作PRC/HTTP请求（可以将这些请求剥离出来）
     * 2.不是所有的方法都需要事务控制，如只有一条修改的操作、只读操作等是不需要进行事务控制的
     * <p>
     * Spring默认只对运行期异常进行事务的回滚操作，对于编译异常Spring是不进行回滚的，所以对于需要进行事务控制的方法尽可能将可能抛出的异常都转换成运行期异常
     */
    @Override
    /*@RabbitListener(queues = "seckill")//添加RabbitListener注解 监听*/
    public synchronized Orderone executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5,Members members)
            {
        Orderone orderone = new Orderone();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            orderone.setOrdertime("1");
            return orderone;
        }
        //执行秒杀逻辑：1.减库存；2.储存秒杀订单
        Date nowTime = new Date();


            //记录秒杀订单信息
            int insertCount = xxfDao.insertOrder(seckillId, money, userPhone);
            //唯一性：seckillId,userPhone，保证一个用户只能秒杀一件商品
                //减库存
                int updateCount = xxfDao.reduceStock(seckillId, nowTime);
                if (updateCount <= 0) {
                    //没有更新记录，秒杀结束
                    orderone.setOrdertime("3");
                    return orderone;
                } else {
                    //秒杀成功
                    SeckillOrder seckillOrder = xxfDao.findById2(seckillId, userPhone);

                    //更新缓存（更新库存数量）
                    Seckill seckill = (Seckill) redisTemplate.boundHashOps(key).get(seckillId);
                    seckill.setStockcount(seckill.getStockcount()- 1);
                    redisTemplate.boundHashOps(key).put(seckillId, seckill);

                    orderone.setOrdernumber(Ordernumber.getBillCode());
                    orderone.setConsignee(members.getUsername());
                    orderone.setTel(members.getPhone());
                    orderone.setAmountpayable(Double.parseDouble(money.toString()));
                    orderone.setState(1);
                    orderone.setTotalmoney(Double.parseDouble(money.toString()));
                    orderone.setBuyer(members.getUsername());
                    orderone.setOrdertime("0");
                    orderone.setAmount(1);
                    orderone.setUserid(members.getId());
                    orderone.setAddress("");
                    orderone.setCommodityname("");
                    return orderone;
                }


    }


}
