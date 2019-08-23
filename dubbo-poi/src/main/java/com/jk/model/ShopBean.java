package com.jk.model;

import java.io.Serializable;
import java.util.Date;

public class ShopBean implements Serializable {

    private static final long serialVersionUID = -71918752413458570L;
    private Integer id;
    private Date createdDate;
    private Date lastModifiedDate;
    private Integer type;
    private double marketPrice;
    private long sn;
    private String lxname;
    private String spname;
    private Integer page;
    private Integer rows;
    private Integer zt;
    private Integer store_id;//商家ID
    private String Storename;//商家名称
    private String image;


    @Override
    public String toString() {
        return "ShopBean{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", type=" + type +
                ", marketPrice=" + marketPrice +
                ", sn=" + sn +
                ", lxname='" + lxname + '\'' +
                ", spname='" + spname + '\'' +
                ", page=" + page +
                ", rows=" + rows +
                ", zt=" + zt +
                ", store_id=" + store_id +
                ", Storename='" + Storename + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public long getSn() {
        return sn;
    }

    public void setSn(long sn) {
        this.sn = sn;
    }

    public String getLxname() {
        return lxname;
    }

    public void setLxname(String lxname) {
        this.lxname = lxname;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getStorename() {
        return Storename;
    }

    public void setStorename(String storename) {
        Storename = storename;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
