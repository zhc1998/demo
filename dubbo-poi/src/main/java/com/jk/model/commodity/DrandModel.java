package com.jk.model.commodity;

import java.io.Serializable;

public class DrandModel implements Serializable {
    private Integer id;//品牌Id

    private String name;//品牌名称

    private String logo;//品牌log

    private String url;//品牌网址

    private String introduction;//品牌描述

    private Integer itemId;//关联商品列表Id

    private Integer type;//关联类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DrandModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", url='" + url + '\'' +
                ", introduction='" + introduction + '\'' +
                ", itemId=" + itemId +
                ", type=" + type +
                '}';
    }
}
