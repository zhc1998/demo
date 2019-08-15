package com.jk.dao;

import com.jk.model.ClpTeacher;

public interface ClpTeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClpTeacher record);

    int insertSelective(ClpTeacher record);

    ClpTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClpTeacher record);

    int updateByPrimaryKey(ClpTeacher record);
}