package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.jk.model.Members;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.DrandModel;
import com.jk.service.CartService;
import com.jk.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Reference(check = false)
    private CartService cartService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private  HttpSession session;

    @Autowired
    private  HttpServletResponse response;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("findCartList")
    @ResponseBody
    public List<CommodityModel> findCartList(){
            Members admin = (Members) session.getAttribute("members");
            String cartList = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
            if (cartList==null||cartList.equals("")){
                cartList="[]";
            }

        List<CommodityModel> carts_cookie = JSON.parseArray(cartList, CommodityModel.class);
        if (admin==null){
            return carts_cookie;
        }else{
            List<CommodityModel> cartListFromRedis = cartService.findCartListFromRedis(admin.getUsername());
            if (carts_cookie.size()>0){
                //合并购物车
                for (CommodityModel members:carts_cookie){
                   cartListFromRedis.add(members);
               }
                //清除本地cookie的数据
                CookieUtil.deleteCookie(request, response, "cartList");
                //将合并后的数据存入redis
                cartService.saveCartListToRedis(admin.getUsername(), cartListFromRedis);
            }

            return cartListFromRedis;

        }

    }


    @RequestMapping("add")
    @ResponseBody
    public HashMap<String,Object> addProductToCartList(Integer proId,Integer num){

        Members admin = (Members) session.getAttribute("members");

        HashMap<String, Object> map = new HashMap<>();
        try {
            List<CommodityModel> carts = cartService.findProductById(proId);
            System.out.println(carts);
            if (admin==null){
                CookieUtil.setCookie(request, response, "cartList", JSON.toJSONString(carts),3600*24,"UTF-8");
            }else{
                cartService.saveCartListToRedis(admin.getUsername(),carts);
                System.out.println("当前登录用户："+admin.getUsername());
            }
            map.put("status",true);
            map.put("msg","添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("msg","添加失败");
        }
        return map;
    }

}
