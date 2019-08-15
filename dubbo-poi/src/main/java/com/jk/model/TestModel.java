package com.jk.model;

import java.io.Serializable;
//测试类
public  class TestModel implements Serializable {
    private Integer id;
    private Integer Pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return Pid;
    }

    public void setPid(Integer pid) {
        Pid = pid;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "id=" + id +
                ", Pid=" + Pid +
                '}';
    }
}
