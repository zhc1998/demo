package com.jk.service;


import com.jk.model.Members;

import java.util.List;

public interface CartService   {
    List<Members> addGoodsToCartList(List<Members> carts_cookie, Integer proid, Integer num);

     List<Members> findCartListFromRedis(String username);

   void saveCartListToRedis(String username, List<Members> cartList);

}


