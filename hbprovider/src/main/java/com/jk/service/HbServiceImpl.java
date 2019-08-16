package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.jk.dao.HbDao;
import com.jk.model.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class HbServiceImpl implements HbService {

    @Autowired
    private HbDao hbDao;

    @Override
    public List<Tree> getTreeAll() {

        return hbDao.getTreeAll();
    }
}
