package com.gdssoft.core.tools;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageBean {

	@Expose
	private int pageIndex;
	@Expose
	private int pageSize;
	@Expose
	private long totalCount;
	@Expose
	private int pageCount;
	@Expose
	@SerializedName("pageData")
	private List<?> result;
	
	public PageBean(int pageIndex, int pageSize){
		this.pageIndex=pageIndex;
		this.pageSize=pageSize;
	}
	
	public PageBean(List<?> list, int pageIndex, int pageSize) {
		if (pageIndex < 1) pageIndex = 1;
		if (pageSize < 1) pageSize = 1;
		
		this.pageSize = pageSize;
		if (list.size() <= pageSize) {
			this.pageIndex = 1;
			setTotalCount(list.size()) ;
			this.result = list;
		} else {
			int count = list.size();
			this.pageIndex = pageIndex;
			setTotalCount(count) ;
			int fromIndex = 0;
			int toIndex = 0;
			if (pageIndex * pageSize < totalCount) {
				fromIndex = (pageIndex - 1) * pageSize;
				toIndex = pageIndex * pageSize;
			} else {
				fromIndex = (pageIndex - 1) * pageSize;
				toIndex = list.size();
			}
			this.result = list.subList(fromIndex, toIndex);
		}
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageIndex() {
		return this.pageIndex;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public int getFirstResult() {
		return ((this.pageIndex - 1) * this.pageSize);
	}

	public boolean getHasPrevious() {
		return (this.pageIndex > 1);
	}

	public boolean getHasNext() {
		return (this.pageIndex < this.pageCount);
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		this.pageCount = (int) (totalCount / this.pageSize + ((totalCount
				% this.pageSize == 0L) ? 0 : 1));

		if (totalCount == 0L) {
			this.pageIndex = 0;
		} else if (this.pageIndex > this.pageCount)
			this.pageIndex = this.pageCount;
	}

	public boolean isEmpty() {
		return (this.totalCount == 0L);
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}
	
	
}