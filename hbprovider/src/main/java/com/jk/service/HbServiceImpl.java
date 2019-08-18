package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.jk.dao.HbDao;
import com.jk.model.Members;
import com.jk.model.Tree;
import com.jk.model.commodity.CommodityModel;
import com.jk.util.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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

    @Override
    public ResultPage querymenmbers(ResultPage result) {
        ResultPage resultPage = new ResultPage();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("result", result);

        //查询总条数
        Integer count = hbDao.querymenmberscount(hashMap);
        resultPage.setTotal(count);

        resultPage.setPageNumber(result.getPageNumber());
        resultPage.setPageSize(result.getPageSize());

        hashMap.put("start", (result.getPageNumber()-1)*result.getPageSize());
        hashMap.put("end", result.getPageSize());

        //查询所有数据
        List<CommodityModel> list = hbDao.HashMap(hashMap);
        resultPage.setRows(list);
        return resultPage;
    }
}
