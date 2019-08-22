package com.jk.service;

import com.jk.model.Orderone;
import com.jk.model.commodity.*;
import com.jk.util.ResultPage;

import java.util.List;

public interface ZcService {

    ResultPage queryCommodity(ResultPage result);

    List<CommodityTypeModel> queryCommodityType();

    List<CommodityModel> queryStatus();

    List<CommodityModel> queryNewOrHot();

    List<DrandModel> zcService();

    List<DrandModel> queryAllDran();


    CommodityModel loadOneModel(Integer id);

    void updCommodity(CommodityModel commodityModel);

    List<CommodityModel> loadImg(CommodityModel commodityModel);

    void addCommodity(CommodityModel commodityModel);

    List<CommodityTypeModel> queryClassify(CommodityTypeModel commodityTypeModel);

    DrandModel loadDescribe(Integer ids);

    DrandModel updAllDran(Integer ids);

    List<DrandModel> queryAllDranList(Integer itemId);

    ParticularsModel loadParticulars(Integer ids);

    List<DrandModel> angeDran(Integer id);

    void delCommodity(Integer ids);

    List<CommodityModel> loadHuaWei();

    DetailsModel loadDetails(Integer ids);

    List<ColorModel> queryColor();
}
