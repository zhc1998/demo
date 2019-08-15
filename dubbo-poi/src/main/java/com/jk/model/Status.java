package com.jk.model;

public class Status {
    private Integer statusid;

    private String statusidname;

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public String getStatusidname() {
        return statusidname;
    }

    public void setStatusidname(String statusidname) {
        this.statusidname = statusidname == null ? null : statusidname.trim();
    }
}