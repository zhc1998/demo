package com.jk.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("toshow")
public class ToShowController {


//跳转展示普通订单页面
    @RequestMapping("showorderone")
    public String showorderone(){

        return "showorderone";
    }
    //跳转到商品列表
    @RequestMapping("toCommodity")
    public String toCommodity(){
        return "commodity";
    }


    // clp  优惠券 查询 跳页面
    @RequestMapping("toShowYhq")
    public String toShowYhq(){
        return "showYhq";
    }


    // clp 新增  优惠券 跳页面toAddYhqPage
    @RequestMapping("toAddClpYhqPage")
    public String toAddClpYhqPage(){
        return "addClpYhq";
    }




   //跳转登录页
    @RequestMapping("toIndex")
    public String toindex(){
        return "index";
    }
    //跳转树
    @RequestMapping("tohbtree")
    public String tohbtree(){
        return "hbtree";
    }
    //跳转控制台
    @RequestMapping("toHighcharts")
    public String toHighcharts(){
        return "highcharts";
    }
}
