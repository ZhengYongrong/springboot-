
package com.example.demo.common.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class STablePageRequest 
{
	private int pageNo=1;
	private int pageSize=10;
	private String sortField="id";
	private String sortOrder = "descend";
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public  Pageable getPageable() {
		Sort sort = new Sort(Direction.DESC, this.sortField);
		if(!this.sortOrder.equals("descend")) {
			sort = new Sort(Direction.ASC, this.sortField);
		}
		return PageRequest.of(this.pageNo-1, this.pageSize , sort);
	}	
}
