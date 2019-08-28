package com.jk.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.jk.model.Highcharts;
import com.jk.model.Members;
import com.jk.model.User;
import com.jk.service.XxfService;
import com.jk.util.CheckImgUtil;
import com.jk.util.CheckSumBuilder;
import com.jk.util.FileUtil;
import com.jk.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("xxf")
public class XxfConltroller {
    @Reference
    private XxfService xxfService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate2;


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



    @RequestMapping("frontLogin")
    @ResponseBody
    public String frontLogin(Members members, HttpServletRequest request) throws UnknownHostException {
        Members members1 = xxfService.frontLogin(members.getUsername());
        String key=getIp();
        if(redisTemplate2.hasKey(key)){
            String sum = redisTemplate2.opsForValue().get(key);
            int int1 = Integer.parseInt(sum);
            if(int1>=10){
                return "3";
            }
        }
        if (members1 == null) {
                if(!redisTemplate2.hasKey(key)){
                    redisTemplate2.opsForValue().set(key,"1");
                    redisTemplate2.expire(key,60, TimeUnit.MINUTES);
                }else{
                    Long increment = redisTemplate2.opsForValue().increment(key, 1);
                    if (increment>=10) {
                        redisTemplate2.expire(key,5, TimeUnit.MINUTES);
                        return "3";
                    }
                }

            return "1";
        }
        if(!members1.getPassword().equals(members.getPassword())){

            if(!redisTemplate2.hasKey(key)){
                redisTemplate2.opsForValue().set(key,"1");
                redisTemplate2.expire(key,60, TimeUnit.MINUTES);
            }else{
                Long increment = redisTemplate2.opsForValue().increment(key, 1);
                if (increment>=10) {
                    redisTemplate2.expire(key,5, TimeUnit.MINUTES);
                    return "3";
                }
            }

            return "2";
        }
        request.getSession().setAttribute("members", members1);
        redisTemplate2.delete(key);
        return "0";
    }


    /**
     * 获取  ip 地址
     * @return
     * @throws UnknownHostException
     */
    private String getIp() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr.getHostAddress();
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress.getHostAddress();
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }

    @RequestMapping("getcode")
    public void df(HttpServletRequest request, HttpServletResponse response) throws Exception{
        CheckImgUtil.checkImg(request, response);
    }

    @RequestMapping("yz")
    @ResponseBody
    public String yz(Members members,String code,HttpServletRequest request){
        String realcode = request.getSession().getAttribute("checkcode").toString();
        if(!realcode.toLowerCase().equals(code.toLowerCase())){
            return "1";
        }
        Members members1= xxfService.frontLogin(members.getUsername());
        if(members1==null){
            return "2";
        }
        return "0";
    }


    @RequestMapping("huoCode")
    @ResponseBody
    public String huoCode(Members members2){
        Members members =xxfService.queryMembers(members2.getPhone());
        if(members==null||!members.getUsername().equals(members2.getUsername())){
                 return "1";
        }

       /* String url = "https://api.netease.im/sms/sendcode.action";
        String CurTime=String.valueOf(new Date().getTime());
        String Nonce= UUID.randomUUID().toString().replace("-", "");

        HashMap<String, Object> headers = new HashMap<String, Object>();
        headers.put("AppKey", "b9fa9dcb8c8661b78808db9dd18977c0");
        headers.put("Nonce", Nonce);
        headers.put("CurTime", CurTime);
        headers.put("CheckSum", CheckSumBuilder.getCheckSum("7c7427caa619", Nonce, CurTime));


        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("mobile",members2.getPhone());
        params.put("templateid","14798448");

        try {
            String str= HttpClientUtil.post(url, params, headers);
            JSONObject jsonObject = JSONObject.parseObject(str);
            String code=jsonObject.getString("code");

            if("200".equals(code)){
                String objcode = jsonObject.getString("obj");*/
                String objcode="1234";
                String key="phone"+members2.getPhone();
                List<Object> list = new ArrayList<Object>();
                    redisTemplate.opsForValue().set(key, objcode);
                    redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        /*} catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        return "0";
    }

    @RequestMapping("huoCode2")
    @ResponseBody
    public String huoCode2(Members members2){
        Members members =xxfService.queryMembers(members2.getPhone());
          if(members!=null){
               return "1";
          }

       /* String url = "https://api.netease.im/sms/sendcode.action";
        String CurTime=String.valueOf(new Date().getTime());
        String Nonce= UUID.randomUUID().toString().replace("-", "");

        HashMap<String, Object> headers = new HashMap<String, Object>();
        headers.put("AppKey", "b9fa9dcb8c8661b78808db9dd18977c0");
        headers.put("Nonce", Nonce);
        headers.put("CurTime", CurTime);
        headers.put("CheckSum", CheckSumBuilder.getCheckSum("7c7427caa619", Nonce, CurTime));


        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("mobile",members2.getPhone());
        params.put("templateid","14798448");

        try {
            String str= HttpClientUtil.post(url, params, headers);
            JSONObject jsonObject = JSONObject.parseObject(str);
            String code=jsonObject.getString("code");

            if("200".equals(code)){
                String objcode = jsonObject.getString("obj");*/
        String objcode="1234";
        String key="phone"+members2.getPhone();
        List<Object> list = new ArrayList<Object>();
        redisTemplate.opsForValue().set(key, objcode);
        redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        /*} catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        return "0";
    }

    @RequestMapping("addMembers")
    @ResponseBody
    public String addMembers(Members members){
        members.setType(1);
        String key="phone"+members.getPhone();
        String code = (String) redisTemplate.opsForValue().get(key);
        if(!members.getCode().equals(code)){
                      return "1";
        }
        xxfService.addMembers(members);
        return "0";
    }


    @RequestMapping("uploadNewsImg")
    @ResponseBody
    public String uploadNewsImg(MultipartFile img, HttpServletRequest request){
        String path = FileUtil.upload(img, request);
        return path;
    }




    @RequestMapping("queryCodeByPhone")
    @ResponseBody
    public String queryCodeByPhone(Members members){
        String key="phone"+members.getPhone();
        String code = (String) redisTemplate.opsForValue().get(key);
        if(!members.getCode().equals(code)){
              return "2";
        }
        xxfService.updateMembers(members);
        redisTemplate.delete(key);
        return "0";
    }



}
