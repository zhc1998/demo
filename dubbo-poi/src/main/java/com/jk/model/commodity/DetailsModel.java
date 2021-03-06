package com.jk.model.commodity;

import java.io.Serializable;

public class DetailsModel implements Serializable {
    private Integer id;

    private String commodityName;//商品描述

    private Double commodityPrice;//商品价格

    private Integer inventory;//剩余数量

    private String  typeName;//类型

    private String name;//平牌名称

    private String introduction;//描述

    private Integer sellquantity;//出售数量

    private String pictureUrl;//图片

    private String artNo;//品牌

    private String createDate;//商品创建时间

    private Integer typeId;//商品类型Id

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getSellquantity() {
        return sellquantity;
    }

    public void setSellquantity(Integer sellquantity) {
        this.sellquantity = sellquantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "DetailsModel{" +
                "id=" + id +
                ", commodityName='" + commodityName + '\'' +
                ", commodityPrice=" + commodityPrice +
                ", inventory=" + inventory +
                ", typeName='" + typeName + '\'' +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", sellquantity=" + sellquantity +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", artNo='" + artNo + '\'' +
                ", createDate='" + createDate + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
