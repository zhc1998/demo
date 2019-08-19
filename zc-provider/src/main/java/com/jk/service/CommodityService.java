package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.ZcDao;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.CommodityTypeModel;
import com.jk.model.commodity.DrandModel;
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

    //查询上下架
    @Override
    public List<CommodityModel> queryStatus() {
        return zcDao.queryStatus();
    }

    //查询新品或热销
    @Override
    public List<CommodityModel> queryNewOrHot() {
        return zcDao.queryNewOrHot();
    }

    //查询品牌
    @Override
    public List<DrandModel> zcService() {
        return zcDao.zcService();
    }

    //查询品牌名称
    @Override
    public List<DrandModel> queryAllDran(Integer ids) {

        System.out.println(ids);
        return zcDao.queryAllDran(ids);
    }

    //查询回显
    @Override
    public CommodityModel loadOneModel(Integer id) {
        return zcDao.loadOneModel(id);
    }

    @Override
    public void updCommodity(CommodityModel commodityModel) {

        zcDao.updCommodity(commodityModel);
    }

    //加载图文信息
    @Override
    public List<CommodityModel> loadImg(CommodityModel commodityModel) {

        return zcDao.loadImg(commodityModel);
    }

    //新增商品
    @Override
    public void addCommodity(CommodityModel commodityModel) {

       zcDao.addCommodity(commodityModel);
    }

    //查询商品分类
    @Override
    public List<CommodityTypeModel> queryClassify(CommodityTypeModel commodityTypeModel) {

        return zcDao.queryClassify(commodityTypeModel);
    }

    //查询描述
    @Override
    public DrandModel loadDescribe(Integer ids) {
        return zcDao.loadDescribe(ids);
    }


}
