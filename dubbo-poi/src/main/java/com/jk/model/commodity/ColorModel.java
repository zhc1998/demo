package com.jk.model.commodity;

import java.io.Serializable;

public class ColorModel implements Serializable {
    private Integer id;

    private String colorName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return "ColorModel{" +
                "id=" + id +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}
