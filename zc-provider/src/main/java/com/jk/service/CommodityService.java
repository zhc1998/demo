package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.ZcDao;
import com.jk.model.commodity.*;
import com.jk.util.ParameUtil;
import com.jk.util.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommodityService implements ZcService {
    @Autowired
    private ZcDao zcDao;


    //查询商品
    @Override
    public Map queryCommodity(ResultPage result) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        hashMap.put("result", result);
        //查询总条数
        Long count = zcDao.queryCommodityCount(hashMap);
        hashMap.put("start", (result.getPageNumber()-1)*result.getPageSize());
        hashMap.put("end", result.getPageSize());
        //查询所有数据
        List list = zcDao.queryCommodity(hashMap);
        map.put("rows",list);
        map.put("total",count);
        return map;
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
    public List<DrandModel> queryAllDran() {

            List<DrandModel> drandModels = zcDao.queryDran();
            return drandModels;
    }

    @Override
    public CommodityModel loadOneModel(String id) {
        return zcDao.loadOneModel(id);
    }

    /*
    //查询回显
    @Override
    public CommodityModel loadOneModel(Integer id) {
        return zcDao.loadOneModel(id);
    }*/

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

    //查询平牌Id
    @Override
    public DrandModel updAllDran(Integer ids) {
        DrandModel dran = zcDao.updAllDran(ids);
        return dran;
    }

    //根据批品牌类型管理Id查询 itemid 平牌关联商品Id
    @Override
    public List<DrandModel> queryAllDranList(Integer itemId) {
        return zcDao.queryAllDranList(itemId);
    }

    //查询详情
    @Override
    public ParticularsModel loadParticulars(Integer ids) {
        return zcDao.loadParticulars(ids);
    }

    //根据类型Id查询品牌
    @Override
    public List<DrandModel> angeDran(Integer id) {
        return zcDao.angeDran(id);
    }

    //删除
    @Override
    public void delCommodity(Integer ids) {
        zcDao.delCommodity(ids);
    }

    //图片展示
    @Override
    public List<CommodityModel> loadHuaWei() {
        return zcDao.loadHuaWei();
    }

    //查询前台详情
    @Override
    public DetailsModel loadDetails(Integer ids) {
        return zcDao.loadDetails(ids);
    }

    //加载颜色
    @Override
    public List<ColorModel> queryColor() {
        return zcDao.queryColor();
    }

    @Override
    public CommodityModel queryCommodityByArtNo(String artNo) {
        return zcDao.queryCommodityByArtNo(artNo);
    }


    //查询配件
    @Override
    public List<AccessoriesModel> queryAccessories(Integer typeId) {
        return zcDao.queryAccessories(typeId);
    }

    //查询内存
    @Override
    public List<LickMemoryModel> queryLickMemory() {
        return zcDao.queryLickMemory();
    }

    //根据Id查询商品总价格
    @Override
    public CommodityModel queryItemPrice(Integer ids) {
        return zcDao.queryItemPrice(ids);
    }


}
