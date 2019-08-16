package com.jk.model;

import java.io.Serializable;

public class QueryYhq implements Serializable {


	private Integer pageNumber;
	
	private Integer pageSize;



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
	
	
}
