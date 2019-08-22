package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.mapper.CartMapper;
import com.jk.model.Cart;
import com.jk.model.Members;
import com.jk.model.ShopBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = CartService.class)
@Component
public class CartServiceImpl implements  CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Members> addGoodsToCartList(List<Members> cartList, Integer proId, Integer num) {
        if(cartList==null){
            cartList=new ArrayList<>();
        }
        Members product= cartMapper.findProductById(proId);

        if (product==null){
            throw new RuntimeException("没有该商品");
        }else {
            cartList.add(product);
            return cartList;
        }
    }

    @Override
    public List<Members> findCartListFromRedis(String username) {
        System.out.println("从redis中提取购物车数据....."+username);
        List<Members> cartList = (List<Members>)redisTemplate.boundHashOps("cartList").get(username);
        if (cartList==null){
            cartList=new ArrayList<>();
        }
        return cartList;
    }

    @Override
    public void saveCartListToRedis(String username, List<Members> cartList) {
        System.out.println("向redis存入购物车数据....."+username);
        redisTemplate.boundHashOps("cartList").put(username,cartList);
    }

}
