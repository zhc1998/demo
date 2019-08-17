package com.jk.model;

import java.io.Serializable;
import java.util.Date;

public class Highcharts implements Serializable {
    private Integer id;

    private Date daytime;
    private Integer visitcount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDaytime() {
        return daytime;
    }

    public void setDaytime(Date daytime) {
        this.daytime = daytime;
    }

    public Integer getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(Integer visitcount) {
        this.visitcount = visitcount;
    }
}
