package com.jk.model;

public class Campus {
    private Integer campusid;

    private String campusname;

    public Integer getCampusid() {
        return campusid;
    }

    public void setCampusid(Integer campusid) {
        this.campusid = campusid;
    }

    public String getCampusname() {
        return campusname;
    }

    public void setCampusname(String campusname) {
        this.campusname = campusname == null ? null : campusname.trim();
    }
}