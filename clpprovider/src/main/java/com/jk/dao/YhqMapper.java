package com.jk.dao;

import com.jk.model.Yhq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YhqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Yhq record);

    int insertSelective(Yhq record);

    Yhq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Yhq record);

    int updateByPrimaryKey(Yhq record);

    List<Yhq> queryYhq(@Param("sta") int sta, @Param("pageSize") Integer pageSize);

    long queryCount();

    Yhq toUpdClpYhqPage(@Param("id")Integer id);

    void addYhq(List<Yhq> list);

    void deleteYhqByName(@Param("names")String names);
}