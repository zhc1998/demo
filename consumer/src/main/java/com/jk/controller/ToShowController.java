package com.jk.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("toshow")
public class ToShowController {


    //跳转前台登陆页面showye
    @RequestMapping("tofrontLogin")
    public String tofrontLogin(){
        return "ffqt/frontLogin";
    }
    //跳转前台修改密码手机验证
    @RequestMapping("toYz")
    public String toYz(Model mdoel,String username){
        mdoel.addAttribute("username",username);
        return "yz";
    }
    //跳转秒杀新增
    @RequestMapping("addSeckill")
    public String addSeckill(){
        return "addSeckill";
    }
    //跳转秒杀查询
    @RequestMapping("querySeckill")
    public String querySeckill(){
        return "querySeckill";
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

    //跳转自己的订单列表
    @RequestMapping("showmyorder")
    public String showmyorder(){
        return "showmyorder";
    }

    //跳转订单查询页面
    @RequestMapping("kuaidi")
    public String kuaidi(){
        return "kuaidi";
    }

    /**
     *  跳转到个人中心
     * @return
     */



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


    //优惠券前台展示
    @RequestMapping("toshowClpYhq2")
    public String toshowClpYhq2(){
        return "houtai/showClpYhq2";
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

    //跳转审核失败
    @RequestMapping("suditFailure")
    public String suditFailure(){
        return "suditFailure";
    }


    //跳转到详情页面
    @RequestMapping("particulars")
    public String particulars(Integer id,Model model){
        model.addAttribute("ids",id);
        return "houtai/particulars";
    }


    //跳转到前台HTML
    @RequestMapping("index")
    public String index(){
        return "hbqt/index";
    }

    //跳转到评论页面
    @RequestMapping("comments")
    public String comments(Integer id,Model model){
        model.addAttribute("ids",id);
        return "hbqt/comments";
    }

    //跳转到前台商品详情
    @RequestMapping("toParticulars")
    public String toParticulars(Integer ids,Model model){

        model.addAttribute("ids",ids);
        return "index";
    }

    //跳转到购物车
    @RequestMapping("toShoppingTrolley")
    public String toShoppingTrolley(){
        return "gouwuche";
    }

    @RequestMapping("comments2")
    public String comments2(){
        return "comments2";
    }

    //根据商品名称跳转页面
    @RequestMapping("toList")
    public String toList(){
        return "hbqt/list";
    }



    @RequestMapping("zhuxiaodenglu")
    public String zhuxiaodenglu(HttpSession session){
        session.removeAttribute("members");
        return "redirect:../toshow/index";
    }


    //根据品牌展示
    @RequestMapping("toListShow")
    public String toList(Integer ids,Model model){

        model.addAttribute("branId",ids);
        return "hbqt/list";
    }
    @RequestMapping("toshowlist")
    public String toshowlist(String pName,Model model){
        model.addAttribute("pName",pName);
        return "hbqt/showlist";
    }
}
