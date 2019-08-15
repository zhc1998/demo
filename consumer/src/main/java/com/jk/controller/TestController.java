package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.TestModel;
import com.jk.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("test")
public class TestController {
    @Reference
    private TestService testService;

    //测试查询
    @RequestMapping("query")
    @ResponseBody
    public List<TestModel> query(){
        List<TestModel> list = testService.query();
        return list;
    }
}
