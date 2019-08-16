package com.jk.controller;



import org.springframework.stereotype.Controller;

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
