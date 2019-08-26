package com.jk.model;

import java.io.Serializable;

public class Yhq implements Serializable {
    private Integer id;

    private String yhqname;

    private Double yhqprice;


    private String yhqdate;

    private String yhqmenkan;

    private Integer yhqcount;

    private Integer yhquse;

    private String yhqdes;

    private Integer yhqlevel;

    private String yhqstartdate;

    private String yhqimg;

    private Integer yhqstatus;

    private Integer yhquserid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getYhqname() {
        return yhqname;
    }

    public void setYhqname(String yhqname) {
        this.yhqname = yhqname == null ? null : yhqname.trim();
    }

    public Double getYhqprice() {
        return yhqprice;
    }

    public void setYhqprice(Double yhqprice) {
        this.yhqprice = yhqprice;
    }



    public String getYhqdate() {
        return yhqdate;
    }

    public void setYhqdate(String yhqdate) {
        this.yhqdate = yhqdate == null ? null : yhqdate.trim();
    }

    public String getYhqmenkan() {
        return yhqmenkan;
    }

    public void setYhqmenkan(String yhqmenkan) {
        this.yhqmenkan = yhqmenkan == null ? null : yhqmenkan.trim();
    }

    public Integer getYhqcount() {
        return yhqcount;
    }

    public void setYhqcount(Integer yhqcount) {
        this.yhqcount = yhqcount;
    }

    public Integer getYhquse() {
        return yhquse;
    }

    public void setYhquse(Integer yhquse) {
        this.yhquse = yhquse;
    }

    public String getYhqdes() {
        return yhqdes;
    }

    public void setYhqdes(String yhqdes) {
        this.yhqdes = yhqdes == null ? null : yhqdes.trim();
    }

    public Integer getYhqlevel() {
        return yhqlevel;
    }

    public void setYhqlevel(Integer yhqlevel) {
        this.yhqlevel = yhqlevel;
    }

    public String getYhqstartdate() {
        return yhqstartdate;
    }

    public void setYhqstartdate(String yhqstartdate) {
        this.yhqstartdate = yhqstartdate == null ? null : yhqstartdate.trim();
    }

    public String getYhqimg() {
        return yhqimg;
    }

    public void setYhqimg(String yhqimg) {
        this.yhqimg = yhqimg == null ? null : yhqimg.trim();
    }

    public Integer getYhqstatus() {
        return yhqstatus;
    }

    public void setYhqstatus(Integer yhqstatus) {
        this.yhqstatus = yhqstatus;
    }

    public Integer getYhquserid() {
        return yhquserid;
    }

    public void setYhquserid(Integer yhquserid) {
        this.yhquserid = yhquserid;
    }
}