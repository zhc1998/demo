package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("toshow")
public class ToShowController {
    //跳转到商品列表
    @RequestMapping("toCommodity")
    public String toCommodity(){
        return "commodity";
    }

}
