package com.jk.service;


import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.DrandModel;

import java.util.List;

public interface CartService   {
    List<CommodityModel> addGoodsToCartList(List<CommodityModel> carts_cookie, Integer proid, Integer num);

     List<CommodityModel> findCartListFromRedis(String username);

   void saveCartListToRedis(String username, List<CommodityModel> cartList);

    List<CommodityModel> findProductById(Integer proId);
}


