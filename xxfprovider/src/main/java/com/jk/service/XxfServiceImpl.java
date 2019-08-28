package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.XxfDao;

import com.jk.model.Highcharts;
import com.jk.model.Members;
import com.jk.model.Seckill;
import com.jk.model.User;
import com.jk.model.commodity.CommodityModel;
import com.jk.util.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
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

    @Override
    public ResultPage querySeckill(ResultPage result) {
        ResultPage resultPage = new ResultPage();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("result", result);

        //查询总条数
        Long count = xxfDao.querySeckillCount(hashMap);
        resultPage.setTotal(Integer.parseInt(count.toString()));

        resultPage.setPageNumber(result.getPageNumber());
        resultPage.setPageSize(result.getPageSize());

        hashMap.put("start", (result.getPageNumber()-1)*result.getPageSize());
        hashMap.put("end", result.getPageSize());

        //查询所有数据
        List<Seckill> list = xxfDao.querySeckill(hashMap);
        resultPage.setRows(list);
        return resultPage;
    }

    @Override
    public void deleteSeckill(Integer ids) {
        xxfDao.deleteSeckill(ids);
    }

    @Override
    public Seckill querySeckillById(Integer id) {
        return xxfDao.querySeckillById(id);
    }

    @Override
    public void addSeckill2(Seckill seckill) {
        xxfDao.addSeckill2(seckill);
    }

    @Override
    public void updateSeckill2(Seckill seckill) {
        xxfDao.updateSeckill2(seckill);
    }


}
