package com.jk.model;

import java.util.Date;

public class Hdkc {
    private Integer hdkcid;

    private String hdkcname;

    private Date begindate;

    private Date enddate;

    public Integer getHdkcid() {
        return hdkcid;
    }

    public void setHdkcid(Integer hdkcid) {
        this.hdkcid = hdkcid;
    }

    public String getHdkcname() {
        return hdkcname;
    }

    public void setHdkcname(String hdkcname) {
        this.hdkcname = hdkcname == null ? null : hdkcname.trim();
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}