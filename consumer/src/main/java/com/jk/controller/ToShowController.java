package com.jk.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("toshow")
public class ToShowController {


    //跳转前台登陆页面
    @RequestMapping("tofrontLogin")
    public String tofrontLogin(){
        return "ffqt/frontLogin";
    }
    //跳转前台修改密码手机验证
    @RequestMapping("toYz")
    public String toYz(){
        return "yz";
    }




    //跳转修改密码页面
    @RequestMapping("toUpdatePassword")
    public String toUpdatePassword(){
        return "updatePassword";
    }
    //跳转注册页面
    @RequestMapping("toZhuCe")
    public String toZhuCe(){
        return "zhuCe";
    }
    @RequestMapping("toZhuCe2")
    public String toZhuCe2(){
        return "zhuCe2";
    }


//跳转展示普通订单页面
    @RequestMapping("showorderone")
    public String showorderone(){

        return "houtai/showorderone";
    }
    //跳转到商品列表
    @RequestMapping("toCommodity")
    public String toCommodity(){
        return "houtai/commodity";
    }


    // clp  优惠券 查询 跳页面
    @RequestMapping("toShowYhq")
    public String toShowYhq(){
        return "houtai/showYhq";
    }


    // clp 新增  优惠券 跳页面toAddYhqPage
    @RequestMapping("toAddClpYhqPage")
    public String toAddClpYhqPage(){
        return "houtai/addClpYhq";
    }






    //跳转到商品品牌页面
    @RequestMapping("toDran")
    public String toDran(){
        return "houtai/dran";
    }

    //跳转到修改商品页面
    @RequestMapping("toUpdCommodity")
    public String toUpdCommodity(Integer id, Model model) {
        model.addAttribute("id", id);
        return "houtai/updCommodity";
    }
   //跳转登录页
    @RequestMapping("toIndex")
    public String toindex(){
        return "houtai/index";
    }
    //跳转树
    @RequestMapping("tohbtree")
    public String tohbtree(){
        return "houtai/hbtree";
    }


    //会员用户列表
    @RequestMapping("queryuser")
    public String queryuser(){
        return "houtai/hbmembers";
    }

    //修改密码
    @RequestMapping("toupdate")
    public String toupdate(){
        return "houtai/update";
    }

    //跳转到图文信息
    @RequestMapping("imageText")
    public String imageText(Integer ids,Model model){

        model.addAttribute("ids",ids);
        return "houtai/imageText";
    }

    //跳转到新增商品
    @RequestMapping("addCommodity")
    public String addCommodity(){
        return "houtai/addCommodity";
    }

    //跳转到商品类型展示
    @RequestMapping("toCommodityType")
    public String toCommodityType(){
        return "houtai/commodityType";
    }
    //跳转到描述页面
    @RequestMapping("toDescribe")
    public String toDescribe(Integer id,Model model){
        model.addAttribute("id",id);
        return "houtai/describe";
    }

    //跳转到轮播图
    @RequestMapping("figure")
    public String figure(){
        return "houtai/figure";
    }
    //注销
    @RequestMapping("zhuxiao")
    public String zhuxiao(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:../toshow/toIndex";
    }

    //跳转到用户前台
    @RequestMapping("showye")
    public String showye(){ return "hbqt/showye"; }

    //跳转审核登录
    @RequestMapping("audit")
    public String audit(){ return "audit"; }

    //跳转审核页面
    @RequestMapping("querygoods")
    public String querygoods(){ return "querygoods"; }
}
