package com.jk.model.commodity;

import java.io.Serializable;

//配件类
public class AccessoriesModel implements Serializable {
    private Integer id;

    private Integer typeid;

    private String name;//配件名称

    private Integer piId;

    private String imgSrc;//配件图片

    private Integer price;//配件价格

    private Integer itemId;//关联商品Id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPiId() {
        return piId;
    }

    public void setPiId(Integer piId) {
        this.piId = piId;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "AccessoriesModel{" +
                "id=" + id +
                ", typeid=" + typeid +
                ", name='" + name + '\'' +
                ", piId=" + piId +
                ", imgSrc='" + imgSrc + '\'' +
                ", price=" + price +
                ", itemId=" + itemId +
                '}';
    }
}
