package com.jk.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.commodity.*;
import com.jk.service.ZcService;
import com.jk.util.ResultPage;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("zch")
public class ZchController {
    @Reference
    private ZcService zcService;
    @Autowired
    private SolrClient client;



    //查询商品列表
    @RequestMapping("queryCommodity")
    @ResponseBody
    public Map queryCommodity(@RequestBody ResultPage result){
        Map<String,Object> mSolr=new HashMap<String,Object>();
    List<CommodityModel> comlist=new ArrayList<>();
    SolrQuery params=new SolrQuery();
        if(!"".equals(result.getCommodityName())&&result.getCommodityName()!=null){
        params.set("q",result.getCommodityName());
    }else{
        params.set("q","*:*");
    }
        params.set("df","commodityName");
        params.set("fl","id,commodityName,artNo,commodityPrice,status,newProduct,inventory,typeId,pictureUrl,itemId,colorId,sellquantity");
        params.addHighlightField("commodityName");//设置高亮字段
    //分页
        params.setStart(result.getPageNumber());
        params.setRows(result.getPageSize());
    //打开高亮
        params.setHighlight(true);
    //设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
    //设置后缀
        params.setHighlightSimplePost("</span>");
        try {
        QueryResponse queryResponse=client.query(params);
        SolrDocumentList results=queryResponse.getResults();
        long numFound=results.getNumFound();
        Map<String,Map<String,List<String>>> higlight=queryResponse.getHighlighting();
        for (SolrDocument res:results){
            CommodityModel com=new CommodityModel();
            String highname="";
            Map<String, List<String>> map=higlight.get(res.get("id"));
            List<String> list=map.get("commodityName");
            if(list==null){
                highname=(String)res.get("commodityName");
            }else{
                highname=list.get(0);
            }
            com.setId((String) res.get("id"));
            com.setSellquantity((Integer) res.get("sellquantity"));
            com.setArtNo((String) res.get("artNo"));
            com.setPictureUrl((String) res.get("pictureUrl"));
            com.setColorId((Integer) res.get("coloId"));
            com.setCommodityName((String) res.get("commodityName"));
            com.setCommodityPrice(Double.parseDouble(res.get("commodityPrice").toString()));
            com.setInventory((Integer) res.get("inventory"));
            com.setItemId((Integer) res.get("itemId"));
            com.setNewProduct((Integer) res.get("newProduct"));
            com.setStatus((Integer) res.get("status"));
            com.setTypeId((Integer) res.get("typeId"));
            comlist.add(com);
        }
        mSolr.put("total",numFound);
        mSolr.put("rows",comlist);
    } catch (SolrServerException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return mSolr;
    //return zcService.queryCommodity(result);
}

    //查询商品类型
    @RequestMapping("queryCommodityType")
    @ResponseBody
    public List<CommodityTypeModel> queryCommodityType(){

        return zcService.queryCommodityType();
    }

    //查询上下架
    @RequestMapping("queryStatus")
    @ResponseBody
    public List<CommodityModel> queryStatus(){
        return zcService.queryStatus();
    }

    //查询新品或者热销
    @RequestMapping("queryNewOrHot")
    @ResponseBody
    public List<CommodityModel> queryNewOrHot(){
        return zcService.queryNewOrHot();
    }

    //查询品牌
    @RequestMapping("queryDran")
    @ResponseBody
    public ResultPage zcService(){
        ResultPage resultPage = new ResultPage();
        List<DrandModel> drandModels = zcService.zcService();
        resultPage.setRows(drandModels);
        return resultPage;
    }

    //加载品牌
    @RequestMapping("queryAllDran")
    @ResponseBody
    public List<DrandModel> queryAllDran(){

        return zcService.queryAllDran();
    }

    //查询回显
    @RequestMapping("loadOneModel")
    public String loadOneModel(String id,Model model){
        CommodityModel commodityModel = zcService.loadOneModel(id);
        model.addAttribute("com",commodityModel);
        model.addAttribute("id",id);
        return "houtai/updCommodity";
    }

    //修改商品与solr索引
    @RequestMapping("updCommodity")
    @ResponseBody
    public String updCommodity(CommodityModel commodityModel){
        String str=commodityModel.getPictureUrl();
        commodityModel.setPictureUrl(str);
        System.out.println(commodityModel.getId());
        zcService.updCommodity(commodityModel);
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", commodityModel.getId());
            doc.setField("commodityName",commodityModel.getCommodityName());
            doc.setField("artNo", commodityModel.getArtNo());
            doc.setField("commodityPrice", commodityModel.getCommodityPrice());
            doc.setField("status", commodityModel.getStatus());
            doc.setField("newProduct", commodityModel.getNewProduct());
            doc.setField("inventory", commodityModel.getInventory());
            doc.setField("typeId", commodityModel.getTypeId());
            doc.setField("itemId",commodityModel.getItemId());
            doc.setField("pictureUrl", commodityModel.getPictureUrl());
            doc.setField("colorId", commodityModel.getColorId());
            doc.setField("sellquantity", commodityModel.getSellquantity());
            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
             * 下面都是一样的
             */
            client.add(doc);
            client.commit();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    //加载图片
    @RequestMapping("loadImg")
    @ResponseBody
    public ResultPage loadImg(CommodityModel commodityModel){
        ResultPage resultPage = new ResultPage();
        List<CommodityModel> list = zcService.loadImg(commodityModel);
        resultPage.setRows(list);
        return resultPage;
    }

    //新增商品与solr索引
    @RequestMapping("addCommodity")
    @ResponseBody
    public String addCommodity(CommodityModel commodityModel){
        String val = "";
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Random random = new Random();
        for ( int i = 0; i < 7; i++ )
        {
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) )
            { // 产生字母
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) ( nextInt + random.nextInt( 26 ) );
            }
            else if ( "num".equalsIgnoreCase( str ) )
            { // 产生数字
                val += String.valueOf( random.nextInt( 10 ) );
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        commodityModel.setCreateDate(format.format(new Date()));
        commodityModel.setStatus(2);
        commodityModel.setId(uuid);
        commodityModel.setArtNo(val);
        commodityModel.setSellquantity(0);
        zcService.addCommodity(commodityModel);
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id",uuid);
            doc.setField("commodityName",commodityModel.getCommodityName());
            doc.setField("artNo", commodityModel.getArtNo());
            doc.setField("commodityPrice", commodityModel.getCommodityPrice());
            doc.setField("status", commodityModel.getStatus());
            doc.setField("newProduct", commodityModel.getNewProduct());
            doc.setField("inventory", commodityModel.getInventory());
            doc.setField("typeId", commodityModel.getTypeId());
            doc.setField("itemId",commodityModel.getItemId());
            doc.setField("pictureUrl", commodityModel.getPictureUrl());
            doc.setField("colorId", commodityModel.getColorId());
            doc.setField("sellquantity", commodityModel.getSellquantity());
            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
              下面都是一样的
             */
            client.add(doc);
            client.commit();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    //查询商品分类
    @RequestMapping("queryClassify")
    @ResponseBody
    public ResultPage queryClassify(CommodityTypeModel commodityTypeModel){
        ResultPage resultPage = new ResultPage();
        List<CommodityTypeModel> list = zcService.queryClassify(commodityTypeModel);
        resultPage.setRows(list);
        return resultPage;
    }

    //查询品牌描述
    @RequestMapping("loadDescribe")
    @ResponseBody
    public DrandModel loadDescribe(Integer ids){
        DrandModel drandModel =  zcService.loadDescribe(ids);
        return drandModel;
    }

    //修改或新增品牌
    @RequestMapping("updAllDran")
    @ResponseBody
    public List<DrandModel> updAllDran(Integer ids){
        //根据类型Id查询平牌类型关联Id
        DrandModel list = zcService.updAllDran(ids);
        //根据批品牌类型管理Id查询 itemid 平牌关联商品Id
        Integer itemId = list.getType();
        List<DrandModel> drandModels = zcService.queryAllDranList(itemId);
        return drandModels;
    }

    //加载商品详情
    @RequestMapping("loadParticulars")
    @ResponseBody
    public ParticularsModel loadParticulars(String ids){
        ParticularsModel particularsModel = zcService.loadParticulars(ids);
        return particularsModel;
    }

    //根据类型查询品牌
    @RequestMapping("angeDran")
    @ResponseBody
    public List<DrandModel> angeDran(Integer id){
        return zcService.angeDran(id);

    }

    //删除商品
    @RequestMapping("delCommodity")
    @ResponseBody
    public String delCommodity(String ids){
        zcService.delCommodity(ids);
        try {
            client.deleteById(String.valueOf(ids));
            client.commit();
            return "123";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }


    //图片展示
    @RequestMapping("loadHuaWei")
    @ResponseBody
    public List<CommodityModel> loadHuaWei(){
        List<CommodityModel> commodityModels = zcService.loadHuaWei();
        return commodityModels;
    }

    //查询前台详情
    @RequestMapping("queryOneDetails")
    @ResponseBody
    public DetailsModel queryOneDetails(Integer ids){
        DetailsModel detailsModel = zcService.loadDetails(ids);
        return detailsModel;

    }

    //加载颜色
    @RequestMapping("queryColor")
    @ResponseBody
    public List<ColorModel> queryColor(){
        List<ColorModel> colorModels = zcService.queryColor();
        return colorModels;
    }

}
