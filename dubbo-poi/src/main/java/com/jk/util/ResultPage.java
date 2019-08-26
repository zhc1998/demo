/** 
 * <pre>项目名称:yasyui 
 * 文件名称:ResultPage.java 
 * 包名:com.jk.zc.utils 
 * 创建日期:2019年5月17日下午8:13:02 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.util;

import java.io.Serializable;

/**
 * <pre>项目名称：yasyui    
 * 类名称：ResultPage    
 * 类描述：    
 * 创建人：张晨
 * 创建时间：2019年5月17日 下午8:13:02    
 * 修改人：张晨
 * 修改时间：2019年5月17日 下午8:13:02    
 * 修改备注：       
 * @version </pre>   
 */
public class ResultPage implements Serializable {
   private Integer total;
   private Object rows;
   private Integer pageNumber;//当前页
   private Integer pageSize;//每页条数

   private Integer typeIds;//类型Id

   private String commodityName;//商品名称

	private Integer status;//上下架

	private Integer is;//新品或热销

	private Integer dran;//品牌

	private String pName;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(Integer typeIds) {
		this.typeIds = typeIds;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIs() {
		return is;
	}

	public void setIs(Integer is) {
		this.is = is;
	}

	public Integer getDran() {
		return dran;
	}

	public void setDran(Integer dran) {
		this.dran = dran;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	@Override
	public String toString() {
		return "ResultPage{" +
				"total=" + total +
				", rows=" + rows +
				", pageNumber=" + pageNumber +
				", pageSize=" + pageSize +
				", typeIds=" + typeIds +
				", commodityName='" + commodityName + '\'' +
				", status=" + status +
				", is=" + is +
				", dran=" + dran +
				", pName='" + pName + '\'' +
				'}';
	}
}
