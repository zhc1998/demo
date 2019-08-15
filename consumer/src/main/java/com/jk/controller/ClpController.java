package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.service.ClpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("clp")
public class ClpController {

    @Reference
    private ClpService clpService;
}
