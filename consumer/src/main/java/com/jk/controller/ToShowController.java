package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("toshow")
public class ToShowController {



    @RequestMapping("showorderone")
    public String showorderone(){

        return "showorderone";
    }

    //跳转到商品列表
    @RequestMapping("toCommodity")
    public String toCommodity(){
        return "commodity";
    }

    //跳转到商品品牌页面
    @RequestMapping("toDran")
    public String toDran(){
        return "dran";
    }

    //跳转到修改商品页面
    @RequestMapping("toUpdCommodity")
    public String toUpdCommodity(Integer id, Model model){
        model.addAttribute("id",id);
        return "updCommodity";
    }
}
