package com.jk.model.commodity;

import java.io.Serializable;

public class DetailsModel implements Serializable {
    private Integer id;

    private String commodityName;

    private Double commodityPrice;

    private Integer inventory;//剩余数量

    private String  typeName;

    private String name;

    private String introduction;

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
                '}';
    }
}
