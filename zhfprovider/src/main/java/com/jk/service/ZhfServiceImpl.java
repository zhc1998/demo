package com.jk.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.FamilyheadDao;
import com.jk.dao.OrderoneDao;
import com.jk.model.Familyhead;
import com.jk.model.Orderone;
import com.jk.util.ParameUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

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
        System.err.println(parame.getOrdernumber());
        List<Orderone>list=orderoneDao.queryOreryone(statr,parame.getPageSize(),parame);
        HashMap<String,Object>hashMap=new HashMap<>();
        hashMap.put("total",count);
        hashMap.put("rows",list);
        return hashMap;
    }


}
