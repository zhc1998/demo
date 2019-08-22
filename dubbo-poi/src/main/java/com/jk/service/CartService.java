package com.jk.service;


import com.jk.model.Cart;
import java.util.List;

public interface CartService   {
    List<Cart> addGoodsToCartList(List<Cart> carts_cookie, Integer proid, Integer num);

     List<Cart> findCartListFromRedis(String username);

   void saveCartListToRedis(String username, List<Cart> cartList);

    List<Cart> mergeCartList(List<Cart> cartList1, List<Cart> cartList2);
}


