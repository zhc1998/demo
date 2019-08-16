/**
 * Copyright (C), 2019-2019, 金科
 * FileName: Members
 * Author:  黄斌
 * Date:     2019/8/16 19:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 斌
 * @create 2019/8/16
 * @since 1.0.0
 */
public class Members implements Serializable {

    private Integer id;
    private String name;
    private String gender;
    private String headportrait;
    private String wechataddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date zctime;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }

    public String getWechataddress() {
        return wechataddress;
    }

    public void setWechataddress(String wechataddress) {
        this.wechataddress = wechataddress;
    }

    public Date getZctime() {
        return zctime;
    }

    public void setZctime(Date zctime) {
        this.zctime = zctime;
    }
}
