package com.jk.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.service.HbService;
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
    }
