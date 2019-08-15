package com.jk.dao;

import com.jk.model.Familyhead;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FamilyheadDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Familyhead record);

    int insertSelective(Familyhead record);

    Familyhead selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Familyhead record);

    int updateByPrimaryKey(Familyhead record);
@Select("select * from z_familyhead where usernumber =#{usernumber}")
    Familyhead QueryfByname(@Param("usernumber") String usernumber);
}