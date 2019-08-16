package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("toshow")
public class ToShowController {



    @RequestMapping("showorderone")
    public String showorderone(){

        return "showorderone";
    }
}
