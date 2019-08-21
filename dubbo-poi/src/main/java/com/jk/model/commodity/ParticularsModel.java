package com.jk.model.commodity;

import java.io.Serializable;

public class ParticularsModel implements Serializable {

    private String color;

    private String weight;

    private String crowd;

    private String commodityName;//商品名称

    private String typeName;//商品类型

    private String name;//品牌

    private Integer itemId;

    private Integer typeId;

    private Integer dranId;

    private String introduction;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDranId() {
        return dranId;
    }

    public void setDranId(Integer dranId) {
        this.dranId = dranId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCrowd() {
        return crowd;
    }

    public void setCrowd(String crowd) {
        this.crowd = crowd;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
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

    @Override
    public String toString() {
        return "ParticularsModel{" +
                "color='" + color + '\'' +
                ", weight='" + weight + '\'' +
                ", crowd='" + crowd + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", typeName='" + typeName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
