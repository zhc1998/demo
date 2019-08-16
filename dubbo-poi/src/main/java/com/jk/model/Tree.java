/** 
 * <pre>项目名称:expserver 
 * 文件名称:Tree.java 
 * 包名:com.jk.model 
 * 创建日期:2019年7月26日下午4:19:17 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * <pre>项目名称：expserver    
 * 类名称：Tree    
 * 类描述：    
 * 创建人：黄斌
 *
 * 励志语录:业精于勤荒于嬉 行成于思毁于随
 *
 * 创建时间：2019年7月26日 下午4:19:17    
 * 修改人：黄斌  2424859198@qq.com
 * 修改时间：2019年7月26日 下午4:19:17    
 * 修改备注：       
 * @version </pre>    
 */
public class Tree implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String text;
	private Integer pid;
	private String url;
    private Map<String, Object> attributes = new HashMap<String, Object>(); // 添加到节点的自定义属性
    private List<Tree> nodes; //子节点数据


	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List<Tree> getNodes() {
		return nodes;
	}
	public void setNodes(List<Tree> nodes) {
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
