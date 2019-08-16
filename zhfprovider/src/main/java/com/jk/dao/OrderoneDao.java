package com.jk.dao;

import com.jk.model.Orderone;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderoneDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Orderone record);

    int insertSelective(Orderone record);

    Orderone selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orderone record);

    int updateByPrimaryKey(Orderone record);

    long QueryorOrderonecount();

    List<Orderone> queryOreryone(@Param("st") int statr,@Param("ro") Integer pageNumber);
}