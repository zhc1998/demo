package com.jk.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.FamilyheadDao;
import com.jk.model.Familyhead;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ZhfServiceImpl implements ZhfService{
@Autowired
    private FamilyheadDao familyheadDao;

    @Override
    public Familyhead loginf(Familyhead familyhead) {
        return familyheadDao.QueryfByname(familyhead.getUsernumber());
    }
}
