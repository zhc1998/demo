package com.jk.util;


import java.io.Serializable;


public class TbOrderPro implements Serializable {
    private Integer proid;
    private Integer num;
    private String picPath;
    private double price;
    private Integer storeid;
    private String title;
    private double sumPrice;


    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }


    @Override
    public String toString() {
        return "TbOrderPro{" +
                "proid=" + proid +
                ", num=" + num +
                ", picPath='" + picPath + '\'' +
                ", price=" + price +
                ", storeid=" + storeid +
                ", title='" + title + '\'' +
                ", sumPrice=" + sumPrice +
                '}';
    }
}

