package com.jk.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.*;
import com.jk.service.HbService;
import com.jk.util.CommentsNoteUtil;
import com.jk.util.ResultPage;
import com.jk.util.TreeNoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("hb")
public class HbController {

    @Reference
    private HbService hbService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("getAllTree")
    @ResponseBody
    public List<Tree> getTreeAll() {
        List<Tree> list = new ArrayList();
       /*  //1、定义缓存key
        String key = "trees";
        //2、先判断缓存中是否存在
        if (redisTemplate.hasKey(key)) {
            System.out.println("-----缓存-------");
            //3、a 存在 ：从缓存中获取，返回list
            list = (List<Tree>) redisTemplate.opsForValue().get(key);
        } else {
            System.out.println("-----数据库-------");
            //3、 b 不存在：
            //先从数据库查询、*/
            list = hbService.getTreeAll();
            list = TreeNoteUtil.getFatherNode(list);
           /* //放入缓存中、返回list
            redisTemplate.opsForValue().set(key, list);
            //设置过期时间
            redisTemplate.expire(key, 3, TimeUnit.MINUTES);
        }*/
            return list;
        }
    @RequestMapping("queryuser")
    public String queryuser(){
        return "hbmembers";
    }

    @RequestMapping("querymenmbers")
    @ResponseBody
    public ResultPage querymenmbers(@RequestBody ResultPage result){
        ResultPage resultPage = hbService.querymenmbers(result);
        return resultPage;
    }

    @RequestMapping("saveDialog")
    @ResponseBody
    public String saveDialog(HttpServletRequest request,String password,String sysNewPWInp,String sysConfirmPWInp){
        User us = (User) request.getSession().getAttribute("user");
        if(!us.getPassword().equals(password)){
         return "1";
        }
        if(password.equals(sysNewPWInp)){
            return "2";
        }
        if(!sysNewPWInp.equals(sysConfirmPWInp)){
            return "3";
        }
        hbService.saveDialog(us.getUserid(),sysNewPWInp);
        return "4";
    }

    @RequestMapping("updateAll1")
    @ResponseBody
    public void updateAll1(Integer id){
        hbService.updateAll1(id);
    }

    @RequestMapping("updateAll2")
    @ResponseBody
    public void updateAll2(Integer id){
        hbService.updateAll2(id);
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(Audit audit, HttpServletRequest request) {
        Audit audit1 = hbService.login(audit.getName());
        if (audit1 == null) {
            return "1";
        }
        if(!audit1.getPassword().equals(audit.getPassword())){
            return "2";
        }
        request.getSession().setAttribute("audit", audit1);
        return "0";
    }

    @RequestMapping("updateaudit1")
    @ResponseBody
    public void updateaudit1(Integer id){
        hbService.updateaudit1(id);
    }

    @RequestMapping("updateaudit2")
    @ResponseBody
    public void updateaudit2(Integer id){
        hbService.updateaudit2(id);
    }

    //查询商品列表
    @RequestMapping("queryCommodity")
    @ResponseBody
    public ResultPage queryCommodity(@RequestBody ResultPage result){

        ResultPage resultPage = hbService.queryCommodity(result);
        return resultPage;
    }

    //评论
    @RequestMapping("comments")
    @ResponseBody
    public List<Comments> comments(Integer id){

        List<Comments>list =hbService.comments(id);
        List<Comments>list2=CommentsNoteUtil.getFatherNode(list);
        return list2;
    }



    //跳转到审核失败列表
    @RequestMapping("suditFailure")
    @ResponseBody
    public ResultPage suditFailure(@RequestBody ResultPage result){
        ResultPage resultPage = hbService.suditFailure(result);
        return resultPage;
    }

    //清空所有审核失败商品
    @RequestMapping("delAll")
    @ResponseBody
    public Integer delAll(Integer [] ids){
        hbService.delAll(ids);
        return 1;
    }

    @RequestMapping("addevaluation")
    @ResponseBody
    public void addevaluation(String text,Integer ids,HttpServletRequest request){
        Members members = (Members) request.getSession().getAttribute("members");
        hbService.addevaluation(text,ids,members.getUsername());
    }
    }
