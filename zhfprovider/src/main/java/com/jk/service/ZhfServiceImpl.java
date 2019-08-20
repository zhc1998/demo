package com.jk.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.FamilyheadDao;
import com.jk.dao.OrderoneDao;
import com.jk.model.Familyhead;
import com.jk.model.Orderone;
import com.jk.util.ParameUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class ZhfServiceImpl implements ZhfService{
@Autowired
    private FamilyheadDao familyheadDao;
@Autowired
    private OrderoneDao orderoneDao;

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
    public HashMap<String, Object> queryorderbyuid(Integer userid, ParameUtil parame) {
        long count=orderoneDao.QueryorOrderbyuidcount(userid);
        int statr=(parame.getPageNumber()-1)*parame.getPageSize();
        List<Orderone>list=orderoneDao.queryorderbyuid(statr,parame.getPageSize(),userid);
        HashMap<String,Object>hashMap=new HashMap<>();
        hashMap.put("total",count);
        hashMap.put("rows",list);
        return hashMap;

    }

    @Override
    public void addorder(Orderone orderone) {



        Random random = new Random();
        String subjectno="";
        SimpleDateFormat allTime = new SimpleDateFormat("YYYYMMddHHmmSSS");
         subjectno += allTime.format(new Date())+random.nextInt(10);

        orderone.setOrdernumber(subjectno);
        orderoneDao.insertSelective(orderone);
    }



}
