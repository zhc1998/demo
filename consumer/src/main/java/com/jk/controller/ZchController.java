package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.service.ZcService;
import com.jk.util.ResultPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("zch")
public class ZchController {
    @Reference
    private ZcService zcService;

    //查询商品列表
    @RequestMapping("queryCommodity")
    @ResponseBody
    public ResultPage queryCommodity(@RequestBody ResultPage result){

        ResultPage resultPage = zcService.queryCommodity(result);

        return resultPage;
    }

    @RequestMapping("test")
    @ResponseBody
    public Integer test(Integer pageNumber){
        System.out.println(pageNumber);

        return pageNumber;
    }
}
