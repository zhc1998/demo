package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class pageController {

    @RequestMapping("/toaaa")
    public String toaaa(){
        return "aaa";
    }


    @RequestMapping("/toaddimg")
    public String toaddimg(){
        return "addimg";
    }
}
