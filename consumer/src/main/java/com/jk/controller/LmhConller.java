package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.TextBean;
import com.jk.service.LmhService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("lmh")
public class LmhConller {

    @Reference(check = false)
    private LmhService lmhService;


}
