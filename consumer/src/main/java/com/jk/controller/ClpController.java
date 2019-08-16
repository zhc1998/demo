package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.QueryYhq;
import com.jk.model.Yhq;
import com.jk.service.ClpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("clp")
public class ClpController {

    @Reference
    private ClpService clpService;





    //优惠券 查询
    @RequestMapping("queryYhq")
    @ResponseBody
    public Map queryYhq(@RequestBody QueryYhq yhq){
        System.err.println(yhq.getPageNumber());
        System.err.println(yhq.getPageSize());
        return clpService.queryYhq(yhq);
    }



   //addYhq 新增  优惠券
    @RequestMapping("addYhq")
    @ResponseBody
    public void addYhq(Yhq yhq){
        int count=yhq.getYhqcount();
        System.out.println(count);

        for(int i=0;i<100;i++){
            List<Yhq> list=new ArrayList<>(count/100);
            System.out.println("线程名称"+Thread.currentThread().getName()+"i="+i);
            for (int j=0;j<count/100;j++){
                list.add(yhq);
            }
            clpService.addYhq(list);
        }

    }

    // clp 修改页面  优惠券 跳页面toUpdYhqPage
    @RequestMapping("toUpdClpYhqPage")
    public String toUpdClpYhqPage(Model model,Integer id){
        Yhq yhq=clpService.toUpdClpYhqPage(id);
        model.addAttribute("yhq",yhq);
        return "updClpYhqPage";
    }

    //updateYhq  修改 优惠券

    @RequestMapping("updateYhq")
    @ResponseBody
    public void updateYhq(Yhq yhq){
        clpService.updateYhq(yhq);
    }



}
