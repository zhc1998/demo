package com.jk.mapper;

import com.jk.model.Members;
import org.apache.ibatis.annotations.Select;

public interface CartMapper {

    @Select("select * from t_item where id=#{value}")
    Members findProductById(Integer getpid);

}
