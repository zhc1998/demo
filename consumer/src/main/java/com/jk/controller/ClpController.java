package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.common.ConstanConf;
import com.jk.model.Members;
import com.jk.model.QueryYhq;
import com.jk.model.User;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.Yhq;
import com.jk.service.ClpService;
import com.jk.util.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("clp")
public class ClpController {

    @Reference
    private ClpService clpService;


    @Autowired
    private RedisTemplate redisTemplate;



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

        String key="yhq"+yhq.getYhqname();
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

            redisTemplate.opsForValue().set(key,yhq);


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
        return "houtai/updClpYhqPage";
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
        clpService.deleteYhqByName(names);
    }

    //updateUseYhq
    @RequestMapping("updateClpUseYhq")
    @ResponseBody
    public Integer updateClpUseYhq(Integer id) throws  ParseException {
        Yhq yhq=clpService.toUpdClpYhqPage(id);
        String key="yhq"+yhq.getYhqname();
        if(redisTemplate.hasKey(key)){
            System.out.println("====缓存===");
            redisTemplate.opsForValue().get(key);
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sdf.parse(yhq.getYhqdate());
        Date date2=new Date();
        long time=(date.getTime()-date2.getTime())/1000/60;
        if(time<=0){
            redisTemplate.expire(key,0, TimeUnit.MINUTES);
            deleteYhqByName(yhq.getYhqname());
        }else{
           redisTemplate.expire(key,time, TimeUnit.MINUTES);
        }
      return (int)time;
    }


    /**
     * OSS阿里云上传图片
     */
    @RequestMapping("updaloadImg")
    @ResponseBody
    public String uploadImg(MultipartFile imgg)throws IOException {
        if (imgg == null || imgg.getSize() <= 0) {
            throw new IOException("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(imgg);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        //System.out.println(split[0]);
        return split[0];
    }

    //updateClpUseYhq2
    @RequestMapping("updateClpUseYhq2")
    @ResponseBody
    public Integer updateClpUseYhq2() throws ParseException {
        List<Yhq> list=clpService.queryClpYhq();
        long time=0;
        if(list.size()>0){
            Yhq yhq=list.get(0);
            String key="yhq"+yhq.getYhqname();

            String yhqname="'"+yhq.getYhqname()+"'";
            if(redisTemplate.hasKey(key)){
                redisTemplate.opsForValue().get(key);
            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=sdf.parse(yhq.getYhqdate());
            Date date2=new Date();
             time=(date.getTime()-date2.getTime())/1000/60;
            if(time<=0){
                redisTemplate.expire(key,0, TimeUnit.MINUTES);
                deleteYhqByName(yhqname);
            }else{
                redisTemplate.expire(key,time, TimeUnit.MINUTES);
            }
        }


        return (int)time;
    }


    //优惠券页面展示
    @RequestMapping("showClpYhq")
    public String showClpYhq(Model model) throws ParseException {
        List<Yhq> list=clpService.queryClpYhq();
        if(list.size()>0){
            Yhq yhq=list.get(0);
            String key="yhq"+yhq.getYhqname();
            String yhqname="'"+yhq.getYhqname()+"'";

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();
            Date date2=sdf.parse(yhq.getYhqdate());
            long time=(date2.getTime()-date.getTime())/1000/60;
            if(redisTemplate.hasKey(key)){
                redisTemplate.opsForValue().get(key);
                model.addAttribute("list",list);
                model.addAttribute("time",time);
            }

            if(time<=0){
                redisTemplate.expire(key,0,TimeUnit.MINUTES);
                deleteYhqByName(yhqname);
            }else{
                redisTemplate.expire(key,time,TimeUnit.MINUTES);
            }
        }
        return "houtai/showClpYhq";
    }







    //优惠券领取
    @RequestMapping("addYhq3")
    @ResponseBody
    public String addYhq3(Integer id,HttpServletRequest request) throws ParseException {
        Members members=(Members) request.getSession().getAttribute("members");
        List<Yhq> list=clpService.queryClpYhq();

        String a=null;
        if(list.size()>0){
            Yhq yhq=list.get(0);
                    String key="yhq"+members.getId()+yhq.getYhqname();

                     System.out.println(key);
                     if(redisTemplate.hasKey(key)){
                         return a="1";
                     }
                    redisTemplate.opsForValue().set(key,yhq);
                    clpService.updateYhqUse(id,members.getId());

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();
            Date date2=sdf.parse(yhq.getYhqdate());
            long time=(date2.getTime()-date.getTime())/1000/60;
            if(time<=0){
                redisTemplate.expire(key,0,TimeUnit.MINUTES);
            }else{
                redisTemplate.expire(key,time,TimeUnit.MINUTES);
            }
            return a="2";
            }
        return a;
    }




    //我的优惠券
    @RequestMapping("showClpYhq3")
    public String showClpYhq3(Model model, HttpServletRequest request) throws ParseException {
        Members members=(Members) request.getSession().getAttribute("members");
        Yhq yhq=new Yhq();
        List<Yhq> list=clpService.queryClpYhq2(members.getId());
        if(list.size()>0){
             yhq=list.get(0);
            String key="yhq"+members.getId()+yhq.getYhqname();
                if(redisTemplate.hasKey(key)){
                    redisTemplate.opsForValue().get(key);
                    model.addAttribute("list",list);
                }
        }
        return "houtai/showClpYhq3";
    }

}
