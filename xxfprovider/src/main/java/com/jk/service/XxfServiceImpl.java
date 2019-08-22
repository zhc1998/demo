package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.XxfDao;

import com.jk.model.Highcharts;
import com.jk.model.Members;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class XxfServiceImpl implements XxfService{
    @Autowired
    private XxfDao xxfDao;

    @Override
    public User login(String username) {
        return xxfDao.login(username);
    }

    @Override
    public List<Highcharts> queryDayCount() {
        return xxfDao.queryDayCount();
    }

    @Override
    public Highcharts queryHighcharts(String time) {
        return xxfDao.queryHighcharts(time);
    }

    @Override
    public void updateHighcharts(Integer id) {
        xxfDao.updateHighcharts(id);
    }

    @Override
    public void addHighcharts(Highcharts highcharts) {
        xxfDao.addHighcharts(highcharts);
    }

    @Override
    public Members frontLogin(String username) {
        return xxfDao.frontLogin(username);
    }



    @Override
    public void addMembers(Members members) {
        xxfDao.addMembers(members);
    }

    @Override
    public Members queryMembers(String phone) {
        return xxfDao.queryMembers(phone);
    }

    @Override
    public void updateMembers(Members members) {
        xxfDao.updateMembers(members);
    }


}
