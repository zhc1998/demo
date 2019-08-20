package com.jk.service;

import com.jk.model.Orderone;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.CommodityTypeModel;
import com.jk.model.commodity.DrandModel;
import com.jk.util.ResultPage;

import java.util.List;

public interface ZcService {

    ResultPage queryCommodity(ResultPage result);

    List<CommodityTypeModel> queryCommodityType();

    List<CommodityModel> queryStatus();

    List<CommodityModel> queryNewOrHot();

    List<DrandModel> zcService();

    List<DrandModel> queryAllDran(Integer ids,String zt);


    CommodityModel loadOneModel(Integer id);

    void updCommodity(CommodityModel commodityModel);

    List<CommodityModel> loadImg(CommodityModel commodityModel);

    void addCommodity(CommodityModel commodityModel);

    List<CommodityTypeModel> queryClassify(CommodityTypeModel commodityTypeModel);

    DrandModel loadDescribe(Integer ids);
}
