package com.jk.dao;

import com.jk.model.commodity.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface ZcDao {

    //查询总条数
    long queryCommodityCount(HashMap<String, Object> hashMap);

    //分页查询
    List queryCommodity(HashMap<String, Object> hashMap);

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
    CommodityModel loadOneModel(String id);

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

    //查询详情
    ParticularsModel loadParticulars(Integer ids);

    //根据类型Id查询品牌
    @Select("select br.name,br.id  from  t_commoditytype ty,brand br WHERE ty.typeId = br.type and ty.typeId = #{value}")
    List<DrandModel> angeDran(Integer id);

    //删除
    @Delete("DELETE from t_item WHERE id = #{value}")
    void delCommodity(Integer ids);

    //图片展示
    //@Select("select pictureUrl,id  from  t_item")
    List<CommodityModel> loadHuaWei();

    //查询前台详情
    DetailsModel loadDetails(Integer ids);

    //加载颜色
    @Select("select id,colorName  from  t_color")
    List<ColorModel> queryColor();

    @Select("select *  from  t_item where artNo = #{artNo}")
    CommodityModel queryCommodityByArtNo(String artNo);

    //查询配件
    List<AccessoriesModel> queryAccessories(Integer typeId);

    //查询内存
    @Select("select *  from  t_memory")
    List<LickMemoryModel> queryLickMemory();

    //根据Id查询商品价格
    @Select("select commodityPrice  from  t_item WHERE id = #{value}")
    CommodityModel queryItemPrice(Integer ids);

    //展示手机品牌树
    @Select("select itemId,name  from  brand WHERE type = 2")
    List<DrandModel> queryBranList();

    //根据品牌展示图片
    List<CommodityModel> loadbranImgShow(Integer branId);

    //儿童玩具树展示
    @Select("select itemId,name  from  brand WHERE type = 5")
    List<DrandModel> plaything();

    //化妆品树展示
    @Select("select itemId,name  from  brand WHERE type = 1")
    List<DrandModel> makeup();

    //鞋子树展示
    @Select("select itemId,name  from  brand WHERE type = 4")
    List<DrandModel> shoeTreeShow();

    //钟表首饰树展示
    @Select("select itemId,name  from  brand WHERE type = 6")
    List<DrandModel> watchTreeShow();

    //品质家居
    @Select("select itemId,name  from  brand WHERE type = 7")
    List<DrandModel> homeTreeShow();

    //汽车用品树展示
    @Select("select itemId,name  from  brand WHERE type = 8")
    List<DrandModel> carAccessories();

    //军用品树展示
    @Select("select itemId,name  from  brand WHERE type = 9")
    List<DrandModel> military();

    //展示食品树4
    @Select("select itemId,name  from  brand WHERE type = 10")
    List<DrandModel> foodstuff();
}
