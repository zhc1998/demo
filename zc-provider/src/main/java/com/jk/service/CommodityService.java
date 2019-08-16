package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.ZcDao;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.CommodityTypeModel;
import com.jk.util.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
@Service
public class CommodityService implements ZcService {
    @Autowired
    private ZcDao zcDao;


    //查询商品
    @Override
    public ResultPage queryCommodity(ResultPage result) {
        ResultPage resultPage = new ResultPage();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("result", result);

        //查询总条数
        Integer count = zcDao.queryCommodityCount(hashMap);
        resultPage.setTotal(count);

        resultPage.setPageNumber(result.getPageNumber());
        resultPage.setPageSize(result.getPageSize());

        hashMap.put("start", (result.getPageNumber()-1)*result.getPageSize());
        hashMap.put("end", result.getPageSize());

        //查询所有数据
        List<CommodityModel> list = zcDao.queryCommodity(hashMap);
        resultPage.setRows(list);
        return resultPage;
    }

    //查询商品类型
    @Override
    public List<CommodityTypeModel> queryCommodityType() {
        return zcDao.queryCommodityType();
    }
}
