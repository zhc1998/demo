package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.QueryYhq;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.Yhq;
import com.jk.service.ClpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.ArrayList;
import java.util.List;
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

    //优惠券 查询
    @RequestMapping("queryYhq")
    @ResponseBody
    public Map queryYhq(@RequestBody QueryYhq yhq){
        return clpService.queryYhq(yhq);
    }



   //addYhq 新增  优惠券
    @RequestMapping("addYhq")
    @ResponseBody
    public void addYhq(Yhq yhq){
        int count=yhq.getYhqcount();
        if(yhq.getYhquse()==null){
            yhq.setYhquse(0);
        }
        if(yhq.getYhqcount()%100==0){
            addyhq(yhq);
        }else {
           addyhq(yhq);
            for (int i = 0; i<1; i++) {
                //若知道list容量在创建时需要给出初始容量
                ArrayList<Yhq> list = new ArrayList<>(yhq.getYhqcount()%100);
                for (int j = 0; j <yhq.getYhqcount()%100; j++) {
                    list.add(yhq);
                }
                clpService.addYhq(list);
            }
        }
    }

    private void  addyhq(Yhq yhq){
        for (int i = 0; i <100; i++) {
            //若知道list容量在创建时需要给出初始容量
            ArrayList<Yhq> list = new ArrayList<>(100);
            for (int j = 0; j <  yhq.getYhqcount()/ 100; j++) {
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


    //deleteYhqByName  批量删除
    @RequestMapping("deleteYhqByName")
    @ResponseBody
    public void deleteYhqByName(String names){
        System.out.println(names);
        clpService.deleteYhqByName(names);
    }


}
