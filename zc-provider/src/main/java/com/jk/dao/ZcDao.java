package com.jk.dao;

import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.CommodityTypeModel;
import com.jk.model.commodity.DrandModel;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface ZcDao {

    //查询总条数
    @Select("select count(*)  from t_item")
    Integer queryCommodityCount(HashMap<String, Object> hashMap);

    //分页查询
    List<CommodityModel> queryCommodity(HashMap<String, Object> hashMap);

    //查询商品类型
    @Select(" select *  from  t_commoditytype ")
    List<CommodityTypeModel> queryCommodityType();

    //查询上下架
    @Select(" SELECT status  from  t_item  GROUP BY status ")
    List<CommodityModel> queryStatus();

    //查询新品或热销
    @Select("SELECT newProduct,selllikeHotCakes  from  t_item  GROUP BY status")
    List<CommodityModel> queryNewOrHot();

    @Select("select *  from  brand")
    List<DrandModel> zcService();

    //查询品牌名称
    @Select("select id,name  from  brand")
    List<DrandModel> queryAllDran();
}
