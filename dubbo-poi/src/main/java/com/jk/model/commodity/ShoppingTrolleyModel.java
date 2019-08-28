package com.jk.model.commodity;

import java.io.Serializable;

//购物车
public class ShoppingTrolleyModel implements Serializable {
    private Integer id;

    private String partsId;//关联配件Id

    private String spName;//商品名称

    private String serial;//商品编号

    private String brandName;//品牌

    private String weight;//重量

    private String igc;//图片路径

    private Double commodityPrice;//商品总价

    private String colorName;//商品颜色

    private Integer memoryPrice;//内存价格

    private Integer purchaseQuantity;//购买数量

    private Integer userId;//用户Id

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartsId() {
        return partsId;
    }

    public void setPartsId(String partsId) {
        this.partsId = partsId;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIgc() {
        return igc;
    }

    public void setIgc(String igc) {
        this.igc = igc;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Integer getMemoryPrice() {
        return memoryPrice;
    }

    public void setMemoryPrice(Integer memoryPrice) {
        this.memoryPrice = memoryPrice;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    @Override
    public String toString() {
        return "ShoppingTrolleyModel{" +
                "id=" + id +
                ", partsId='" + partsId + '\'' +
                ", spName='" + spName + '\'' +
                ", serial='" + serial + '\'' +
                ", brandName='" + brandName + '\'' +
                ", weight='" + weight + '\'' +
                ", igc='" + igc + '\'' +
                ", commodityPrice=" + commodityPrice +
                ", colorName='" + colorName + '\'' +
                ", memoryPrice=" + memoryPrice +
                ", purchaseQuantity=" + purchaseQuantity +
                '}';
    }
}
