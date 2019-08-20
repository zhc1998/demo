package com.jk.dao;

import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.CommodityTypeModel;
import com.jk.model.commodity.DrandModel;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface ZcDao {

    //查询总条数
    long queryCommodityCount(HashMap<String, Object> hashMap);

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
    List<DrandModel> queryAllDran(Integer ids);

    //查询回显
    CommodityModel loadOneModel(Integer id);

    //修改
    void updCommodity(CommodityModel commodityModel);

    //加载图文信息
    @Select("SELECT pictureUrl  from t_item WHERE id = #{id}")
    List<CommodityModel> loadImg(CommodityModel commodityModel);

    //新增
    void addCommodity(CommodityModel commodityModel);

    //查询商品分类
    List<CommodityTypeModel> queryClassify(CommodityTypeModel commodityTypeModel);

    //查询描述
    @Select("select *  from  brand WHERE id = #{value}")
    DrandModel loadDescribe(Integer ids);

    //查询平牌
    @Select("SELECT *  from  brand")
    List<DrandModel> queryDran();

    //根据类型Id查询平牌类型管理Id
    DrandModel updAllDran(Integer ids);

    //根据批品牌类型管理Id查询 itemid 平牌关联商品Id
    @Select("select itemId,name from  brand WHERE type = #{value}")
    List<DrandModel> queryAllDranList(Integer itemId);
}
