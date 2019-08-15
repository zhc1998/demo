package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.XxfDao;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class XxfServiceImpl implements XxfService{
    @Autowired
    private XxfDao xxfDao;

    @Override
    public User login(String username) {
        return xxfDao.login(username);
    }
}
