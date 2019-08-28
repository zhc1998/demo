package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀商品表（秒杀商品表和其他商品表不同，属于独立的模块）
 *
 * @auther TyCoding
 * @date 2018/10/6
 */
public class Seckill implements Serializable {

    private Integer seckillid; //商品ID
    private String title; //商品标题
    private String image; //商品图片
    private BigDecimal price; //商品原价格
    private BigDecimal costprice; //商品秒杀价格

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime; //创建时间

    private String starttime; //秒杀开始时间

    private String endtime; //秒杀结束时间

    private long stockcount; //剩余库存数量

    public Integer getSeckillid() {
        return seckillid;
    }

    public void setSeckillid(Integer seckillid) {
        this.seckillid = seckillid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public long getStockcount() {
        return stockcount;
    }

    public void setStockcount(long stockcount) {
        this.stockcount = stockcount;
    }
}
