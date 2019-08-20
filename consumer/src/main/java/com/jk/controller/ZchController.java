package com.jk.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.CommodityTypeModel;
import com.jk.model.commodity.DrandModel;
import com.jk.model.Orderone;
import com.jk.service.ZcService;
import com.jk.util.ResultPage;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
@Controller
@RequestMapping("zch")
public class ZchController {
    @Reference
    private ZcService zcService;

    //查询商品列表
    @RequestMapping("queryCommodity")
    @ResponseBody
    public ResultPage queryCommodity(@RequestBody ResultPage result){

        ResultPage resultPage = zcService.queryCommodity(result);
        return resultPage;
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

    //品牌条件查询
    @RequestMapping("queryAllDran")
    @ResponseBody
    public List<DrandModel> queryAllDran(Integer ids,String zt){

        return zcService.queryAllDran(ids,zt);
    }

    //查询回显
    @RequestMapping("loadOneModel")
    public String loadOneModel(Integer id,Model model){
        CommodityModel commodityModel = zcService.loadOneModel(id);
        List<CommodityTypeModel> list = zcService.queryCommodityType();
        model.addAttribute("com",commodityModel);
        model.addAttribute("id",id);
        model.addAttribute("list",list);
        return "updCommodity";
    }

    //修改商品
    @RequestMapping("updCommodity")
    @ResponseBody
    public boolean updCommodity(CommodityModel commodityModel){
        if(commodityModel.getId()!=null){
            zcService.updCommodity(commodityModel);
            return true;
        }
        return false;
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

    //新增
    @RequestMapping("addCommodity")
    @ResponseBody
    public boolean addCommodity(CommodityModel commodityModel){
        if(commodityModel.getId()==null){
            zcService.addCommodity(commodityModel);
            return true;
        }
        return false;
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

}
