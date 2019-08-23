package com.jk.mapper;

import com.jk.model.commodity.CommodityModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper {

    @Select("select * from t_item where id=#{value}")
    List<CommodityModel>  findProductById(Integer proId);

}
