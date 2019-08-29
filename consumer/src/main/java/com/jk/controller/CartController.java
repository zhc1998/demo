package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.jk.model.Members;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.DrandModel;
import com.jk.model.commodity.ShoppingTrolleyModel;
import com.jk.service.CartService;
import com.jk.util.CookieUtil;
import com.jk.util.ResultPage;
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
    public ResultPage findCartList(){
        ResultPage resultPage = new ResultPage();
        Members admin = (Members) session.getAttribute("members");
        String cartList = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
        if (cartList==null||cartList.equals("")){
            cartList="[]";
        }

        List<CommodityModel> carts_cookie = JSON.parseArray(cartList, CommodityModel.class);
        resultPage.setRows(carts_cookie);
        if (admin==null){
            return resultPage;
        }else{
            List<CommodityModel> cartListFromRedis = cartService.findCartListFromRedis(admin.getUsername());
            if (carts_cookie.size()>0) {
                //合并购物车
                for (CommodityModel members : carts_cookie) {
                    cartListFromRedis.add(members);
                }

                //清除本地cookie的数据
                CookieUtil.deleteCookie(request, response, "cartList");
                //将合并后的数据存入redis
                cartService.saveCartListToRedis(admin.getUsername(), cartListFromRedis);

            }
            resultPage.setRows(cartListFromRedis);
            return resultPage;
        }

    }


    @RequestMapping("add")
    @ResponseBody
    public HashMap<String,Object> addProductToCartList(Integer proId,Integer num,ShoppingTrolleyModel shop,Double commodityPrices){
        Members admin = (Members) session.getAttribute("members");

        HashMap<String, Object> map = new HashMap<>();
        try {
            //查询根据Id查询数据存入
            List<CommodityModel> carts = cartService.findProductById(proId);
            for(CommodityModel commodityModel2:carts){
                commodityModel2.setCommodityPrices(commodityPrices);
            }
            if (admin==null){
                if(CookieUtil.getCookieValue(request, "cartList", "UTF-8")!=null && !"".equals(CookieUtil.getCookieValue(request, "cartList", "UTF-8"))){
                    String cartList = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
                    List<CommodityModel> carts_cookie = JSON.parseArray(cartList, CommodityModel.class);
                    for (CommodityModel commodityModel1:carts_cookie){
                        carts.add(commodityModel1);
                    }
                }

                CookieUtil.setCookie(request, response, "cartList", JSON.toJSONString(carts),3600*24,"UTF-8");
            }else{
                for(CommodityModel commodityModel2:carts){
                    commodityModel2.setCommodityPrices(commodityPrices);
                }
                if(cartService.findCartListFromRedis(admin.getUsername())!=null){
                    List<CommodityModel> cartListFromRedis = cartService.findCartListFromRedis(admin.getUsername());
                    for(CommodityModel commodityModel:cartListFromRedis){
                        carts.add(commodityModel);
                    }
                }
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



    //删除购物车指定数据
    @RequestMapping("delete")
    @ResponseBody
    public Boolean delete(Integer id){

        try {
            Members admin = (Members) session.getAttribute("members");
            if(admin==null){
                String cartList = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
                List<CommodityModel> carts_cookie = JSON.parseArray(cartList, CommodityModel.class);
                if(carts_cookie.size()==1){
                    CookieUtil.deleteCookie(request,response,"cartList");
                }else{
                      for (int i=0;i<carts_cookie.size();i++){
                          if (carts_cookie.get(i).getId()==id){
                              carts_cookie.remove(i);
                              i--;
                          }

                      }
                    CookieUtil.setCookie(request, response, "cartList", JSON.toJSONString(carts_cookie),3600*24,"UTF-8");
                }
            }
              if(admin!=null){
                List<CommodityModel> cartListFromRedis = cartService.findCartListFromRedis(admin.getUsername());
                if(cartListFromRedis.size()<1){
                    CookieUtil.deleteCookie(request,response,"cartList");
                }else{
                    for (int i=0;i<cartListFromRedis.size();i++){
                        if (cartListFromRedis.get(i).getId()==id){
                            cartListFromRedis.remove(i);
                            i--;
                        }
                    }
                    cartService.saveCartListToRedis(admin.getUsername(),cartListFromRedis);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //清空购物车
    @RequestMapping("deleteAll")
    @ResponseBody
    public Boolean deleteAll(){
        try {
            Members admin = (Members) session.getAttribute("members");
            if(admin==null){
                CookieUtil.deleteCookie(request,response,"cartList");
            }else {
                redisTemplate.delete("cartList");
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
