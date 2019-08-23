package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.mapper.CartMapper;
import com.jk.model.Members;
import com.jk.model.commodity.CommodityModel;
import com.jk.model.commodity.DrandModel;
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
    public List<CommodityModel> addGoodsToCartList(List<CommodityModel> cartList, Integer proId, Integer num) {
        if(cartList==null){
            cartList=new ArrayList<>();
        }
        List<CommodityModel> product= cartMapper.findProductById(proId);

        if (product==null){
            throw new RuntimeException("没有该商品");
        }else {
            for (CommodityModel drandModel:product){
                cartList.add(drandModel);
            }
            return cartList;
        }
    }

    @Override
    public List<CommodityModel> findCartListFromRedis(String username) {
        System.out.println("从redis中提取购物车数据....."+username);
        List<CommodityModel> cartList = (List<CommodityModel>)redisTemplate.boundHashOps("cartList").get(username);
        if (cartList==null){
            cartList=new ArrayList<>();
        }
        return cartList;
    }

    @Override
    public void saveCartListToRedis(String username, List<CommodityModel> cartList) {
        System.out.println("向redis存入购物车数据....."+username);
        redisTemplate.boundHashOps("cartList").put(username,cartList);
    }

    @Override
    public List<CommodityModel> findProductById(Integer proId) {
        return cartMapper.findProductById(proId);
    }

}
