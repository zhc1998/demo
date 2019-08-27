package com.jk.model.commodity;

import java.io.Serializable;

public class LickMemoryModel implements Serializable {
    private Integer id;

    private Integer memoryId;

    private String memoryName;

    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(Integer memoryId) {
        this.memoryId = memoryId;
    }

    public String getMemoryName() {
        return memoryName;
    }

    public void setMemoryName(String memoryName) {
        this.memoryName = memoryName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LickMemoryModel{" +
                "id=" + id +
                ", memoryId=" + memoryId +
                ", memoryName='" + memoryName + '\'' +
                ", price=" + price +
                '}';
    }
}
