package com.jk.model.commodity;

import java.io.Serializable;

public class CommodityModel implements Serializable {
    private Integer id;

    private String commodityName;//商品名称

    private String artNo;//商品货号

    private Double commodityPrice;//商品价格

    private Integer status;//上下架状态

    private Integer newProduct;//是否为新品


    private Integer  inventory;//库存

    private Integer typeId;//类型Id

    private Integer itemId;//平牌Id

    private String pictureUrl;//图片路径

    private Integer colorId;

    private Integer sellquantity;

    private String createDate;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
                ", inventory=" + inventory +
                ", typeId=" + typeId +
                ", itemId=" + itemId +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", colorId=" + colorId +
                ", sellquantity=" + sellquantity +
                ", createDate='" + createDate + '\'' +
                ", typeName='" + typeName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
