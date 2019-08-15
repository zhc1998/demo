package com.jk.model;

import java.util.Date;

public class Sxkc {
    private Integer sxkcid;

    private String sxkcname;

    private Date startdate;

    private Date enddate;

    public Integer getSxkcid() {
        return sxkcid;
    }

    public void setSxkcid(Integer sxkcid) {
        this.sxkcid = sxkcid;
    }

    public String getSxkcname() {
        return sxkcname;
    }

    public void setSxkcname(String sxkcname) {
        this.sxkcname = sxkcname == null ? null : sxkcname.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}