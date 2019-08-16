package com.jk.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.CommodityTypeModel;
import com.jk.model.commodity.DrandModel;
import com.jk.service.ZcService;
import com.jk.util.ResultPage;
import org.springframework.stereotype.Controller;

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
        System.out.println(result.getIs());

    public ResultPage queryCommodity(ResultPage result){


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
    public List<DrandModel> queryAllDran(){
        return zcService.queryAllDran();
    }

}
