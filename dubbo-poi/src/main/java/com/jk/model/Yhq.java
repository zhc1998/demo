package com.jk.model;

import java.io.Serializable;

public class Yhq implements Serializable {
    private Integer id;

    private String yhqname;

    private Double yhqprice;

    private Integer yhqlimit;

    private String yhqdate;

    private String yhqmenkan;

    private Integer yhqcount;

    private Integer yhquse;

    private String yhqdes;

    private Integer yhqlevel;

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

    public Integer getYhqlimit() {
        return yhqlimit;
    }

    public void setYhqlimit(Integer yhqlimit) {
        this.yhqlimit = yhqlimit;
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
}