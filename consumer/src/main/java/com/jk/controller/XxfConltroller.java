package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Highcharts;
import com.jk.model.User;
import com.jk.service.XxfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("xxf")
public class XxfConltroller {
    @Reference
    private XxfService xxfService;
   /* @Autowired
    private StringRedisTemplate redisTemplate2;*/






    @RequestMapping("queryDayCount")
    @ResponseBody
    public List<Highcharts> queryDayCount(){
        List<Highcharts> list=xxfService.queryDayCount();
        return list;
    }


    @RequestMapping("login")
    @ResponseBody
    public String login(User user, HttpServletRequest request) {
        User user1 = xxfService.login(user.getUsername());
        if (user1 == null) {
            return "1";
        }
        if(!user1.getPassword().equals(user.getPassword())){
            return "2";
        }
        /*
        String key="login"+user1.getUserid();
        if(redisTemplate2.hasKey(key)){
            String sum = redisTemplate2.opsForValue().get(key);
            int int1 = Integer.parseInt(sum);
            if(int1>=3){
                return "3";
            }
            if(!user1.getPassword().equals(user.getPassword())){
                if(!redisTemplate2.hasKey(key)){
                    redisTemplate2.opsForValue().set(key, "1");
                    redisTemplate2.expire(key, 5, TimeUnit.MINUTES);
                    return "2";
                }
                Long increment = redisTemplate2.opsForValue().increment(key, 1);
                if(increment>=3){
                    redisTemplate2.expire(key, 5, TimeUnit.MINUTES);
                }
                return "2";
            }
        }
        redisTemplate2.delete(key);*/
        request.getSession().setAttribute("user", user1);
        return "0";
    }
}
