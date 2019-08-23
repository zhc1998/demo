/**
 * Copyright (C), 2019-2019, 金科
 * FileName: Comments
 * Author:  黄斌
 * Date:     2019/8/21 21:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 斌
 * @create 2019/8/21
 * @since 1.0.0
 */
public class Comments implements Serializable {

    private Integer id;
    private String text;
    private Integer pid;
    private String sid;
    private String uid;
    private Map<String, Object> attributes = new HashMap<String, Object>(); // 添加到节点的自定义属性
    private List<Comments> nodes; //子节点数据

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<Comments> getNodes() {
        return nodes;
    }

    public void setNodes(List<Comments> nodes) {
        this.nodes = nodes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", pid=" + pid +
                ", sid='" + sid + '\'' +
                ", uid=" + uid +
                ", attributes=" + attributes +
                ", nodes=" + nodes +
                '}';
    }
}
