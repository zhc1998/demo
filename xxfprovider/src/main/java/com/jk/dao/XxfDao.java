package com.jk.dao;

import com.jk.model.*;
import com.jk.model.commodity.CommodityModel;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface XxfDao {
    User login(String username);

    List<Highcharts> queryDayCount();

    Highcharts queryHighcharts(String time);

    void updateHighcharts(Integer id);

    void addHighcharts(Highcharts highcharts);

    Members frontLogin(String username);


    void addMembers(Members members);

    Members queryMembers(String phone);

    void updateMembers(Members members);

    /**
     * 查询所有秒杀商品的记录信息
     *
     * @return
     */
    List<Seckill> findAll();

    /**
     * 根据主键查询当前秒杀商品的数据
     *
     * @param id
     * @return
     */
    Seckill findById(long id);

    /**
     * 减库存。
     * 对于Mapper映射接口方法中存在多个参数的要加@Param()注解标识字段名称，不然Mybatis不能识别出来哪个字段相互对应
     *
     * @param seckillId 秒杀商品ID
     * @param killTime  秒杀时间
     * @return 返回此SQL更新的记录数，如果>=1表示更新成功
     */
    int reduceStock(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);


    /**
     * 插入购买订单明细
     *
     * @param seckillId 秒杀到的商品ID
     * @param money     秒杀的金额
     * @param userPhone 秒杀的用户
     * @return 返回该SQL更新的记录数，如果>=1则更新成功
     */
    int insertOrder(@Param("seckillId") long seckillId, @Param("money") BigDecimal money, @Param("userPhone") long userPhone);

    /**
     * 根据秒杀商品ID查询订单明细数据并得到对应秒杀商品的数据，因为我们再SeckillOrder中已经定义了一个Seckill的属性
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    SeckillOrder findById2(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    Long querySeckillCount(HashMap<String, Object> hashMap);

    List<Seckill> querySeckill(HashMap<String, Object> hashMap);

    void deleteSeckill(Integer ids);

    Seckill querySeckillById(Integer id);

    void addSeckill2(Seckill seckill);

    void updateSeckill2(Seckill seckill);

    void addorderone();

    void addSeckillOrder(Orderone orderone);
}
