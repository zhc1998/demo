package com.jk.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.FamilyheadDao;
import com.jk.dao.OrderoneDao;
import com.jk.model.Familyhead;
import com.jk.model.Members;
import com.jk.model.Orderone;
import com.jk.util.ParameUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class ZhfServiceImpl implements ZhfService{
@Autowired
    private FamilyheadDao familyheadDao;
@Autowired
    private OrderoneDao orderoneDao;
@Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Familyhead loginf(Familyhead familyhead) {
        return familyheadDao.QueryfByname(familyhead.getUsernumber());
    }

    @Override
    public HashMap<String, Object> queryorderone(ParameUtil parame) {
        long count=orderoneDao.QueryorOrderonecount(parame);
         int statr=(parame.getPageNumber()-1)*parame.getPageSize();
        List<Orderone>list=orderoneDao.queryOreryone(statr,parame.getPageSize(),parame);
        HashMap<String,Object>hashMap=new HashMap<>();
        hashMap.put("total",count);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public Orderone queryorderbyid(Integer id) {

        return orderoneDao.selectByPrimaryKey(id);
    }

    @Override
    public HashMap<String, Object> queryordertable(Integer oid) {
        Orderone orderone = orderoneDao.queryordertable(oid);
        List<Orderone>list=new ArrayList<>();
        list.add(orderone);
        HashMap<String,Object>hashMap=new HashMap<>();
        hashMap.put("total",1);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> queryorderbyuid(Integer userid) {
        long count=orderoneDao.QueryorOrderbyuidcount(userid);

        List<Orderone>list=orderoneDao.queryorderbyuid(userid);
        HashMap<String,Object>hashMap=new HashMap<>();
        hashMap.put("total",count);
        hashMap.put("rows",list);
        return hashMap;

    }

    @Override
    @RabbitListener(queues = "AddOrder")//添加RabbitListener注解 监听
    public void addorder(Orderone orderone) {
        String subjectno=getBillCode();
        String key=orderone.getUserid().toString();
        orderone.setOrdernumber(subjectno);
        redisTemplate.opsForList().leftPush(key,orderone);
        redisTemplate.expire(key, 30, TimeUnit.MINUTES);
        //orderoneDao.insertSelective(orderone);
    }

    /**
     * 生成唯一订单号
     * 规则：四位随机数+""+格式化到秒的时间+""+六位随机数
     */
    public static String getBillCode() {
        Random rd = new Random(); // 创建随机对象
        String n = "";            //保存随机数
        int rdGet;                // 取得随机数
        do {
            if (rd.nextInt() % 2 == 1) {
                rdGet = Math.abs(rd.nextInt()) % 10 + 48;  // 产生48到57的随机数(0-9的键位值)
            } else {
                rdGet = Math.abs(rd.nextInt()) % 26 + 97;  // 产生97到122的随机数(a-z的键位值)
            }
            char num1 = (char) rdGet;                      //int转换char
           String dd = Character.toString(num1);
            n += dd;
        } while (n.length() < 8);// 设定长度，此处假定长度小于8
        String r1 = (((Math.random() * 9 + 1) * 100000) + "").substring(0, 6);
        String r2 = (((Math.random() * 9 + 1) * 100000) + "").substring(0, 6);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String SNDate = sdf.format(new Date());
        String orderCode = r1 + ""+ SNDate+ ""+r2 ;//+ n.toUpperCase();
        return orderCode;
    }
}
