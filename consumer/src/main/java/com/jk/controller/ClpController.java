package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.QueryYhq;
import com.jk.service.ClpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("clp")
public class ClpController {

    @Reference
    private ClpService clpService;




    //班级课程管理 查询 跳页面
    @RequestMapping("toShowYhq")
    public String toShowYhq(){
        return "showYhq";
    }

    //班级课程管理 查询
    @RequestMapping("queryYhq")
    @ResponseBody
    public Map queryYhq(@RequestBody QueryYhq yhq){
        System.err.println(yhq.getPageNumber());
        System.err.println(yhq.getPageSize());
        return clpService.queryYhq(yhq);
    }






}
