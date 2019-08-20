package com.jk.model.commodity;

import java.io.Serializable;

public class CommodityModel implements Serializable {
    private Integer id;

    private String commodityName;//商品名称

    private String artNo;//商品货号

    private Double commodityPrice;//商品价格

    private Integer status;//上下架状态

    private Integer newProduct;//是否为新品

    private Integer selllikeHotCakes;//是否热销

    private Integer  inventory;//库存

    private Integer TypeId;//类型Id

    private Integer itemId;//平牌Id

    private String pictureUrl;//图片路径

    private String typeName;

    private String name;

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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Integer newProduct) {
        this.newProduct = newProduct;
    }

    public Integer getSelllikeHotCakes() {
        return selllikeHotCakes;
    }

    public void setSelllikeHotCakes(Integer selllikeHotCakes) {
        this.selllikeHotCakes = selllikeHotCakes;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer typeId) {
        TypeId = typeId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "CommodityModel{" +
                "id=" + id +
                ", commodityName='" + commodityName + '\'' +
                ", artNo='" + artNo + '\'' +
                ", commodityPrice=" + commodityPrice +
                ", status=" + status +
                ", newProduct=" + newProduct +
                ", selllikeHotCakes=" + selllikeHotCakes +
                ", inventory=" + inventory +
                ", TypeId=" + TypeId +
                ", itemId=" + itemId +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", typeName='" + typeName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
