package com.jk.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.FamilyheadDao;
import com.jk.dao.OrderoneDao;
import com.jk.model.Familyhead;
import com.jk.model.Orderone;
import com.jk.util.ParameUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
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
    public void addorder(Orderone order1) {
        orderoneDao.insertSelective(order1);
        
    }


    @RabbitListener(queues = "DelOrder")//添加RabbitListener注解 监听
    public void addorder(String key) {

        redisTemplate.delete(key);
    }
    @RabbitListener(queues = "addredistOrder")//添加RabbitListener注解 监听
    public void addredistOrder(Orderone orderone) {
        String key= orderone.getUserid()+""+orderone.getOrdernumber();
        redisTemplate.opsForValue().set(key,orderone);
        redisTemplate.expire(key, 30, TimeUnit.MINUTES);
    }


}
