package com.gdssoft.cqjt.pojo.report;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class SiteReport extends BasePojo {
		
	@Expose
	private String deptCategory;	
	@Expose
	private String deptName;
	@Expose
	private String categoryName;	
	@Expose
	private Long reportCount;	
	@Expose
	private Long backCount;
	@Expose
	private Long inNetCount;
	@Expose
	private Float inNetCountSorce;
	@Expose
	private Long outNetCount;
	@Expose
	private Float outNetCountSorce;
	@Expose
	private Float addTotalSorce;	
	@Expose
	private Long allTotalCount;
	@Expose
	private Float allTotalCountSorce;
	@Expose
	private Long adoptNum;
	
	public Long getAdoptNum() {
		return adoptNum;
	}
	public void setAdoptNum(Long adoptNum) {
		this.adoptNum = adoptNum;
	}
	public String getDeptCategory() {
		return deptCategory;
	}
	public void setDeptCategory(String deptCategory) {
		this.deptCategory = deptCategory;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getReportCount() {
		return reportCount;
	}
	public void setReportCount(Long reportCount) {
		this.reportCount = reportCount;
	}
	public Long getBackCount() {
		return backCount;
	}
	public void setBackCount(Long backCount) {
		this.backCount = backCount;
	}
	public Long getInNetCount() {
		return inNetCount;
	}
	public void setInNetCount(Long inNetCount) {
		this.inNetCount = inNetCount;
	}
	public Float getInNetCountSorce() {
		return inNetCountSorce;
	}
	public void setInNetCountSorce(Float inNetCountSorce) {
		this.inNetCountSorce = inNetCountSorce;
	}
	public Long getOutNetCount() {
		return outNetCount;
	}
	public void setOutNetCount(Long outNetCount) {
		this.outNetCount = outNetCount;
	}
	public Float getOutNetCountSorce() {
		return outNetCountSorce;
	}
	public void setOutNetCountSorce(Float outNetCountSorce) {
		this.outNetCountSorce = outNetCountSorce;
	}
	public Float getAddTotalSorce() {
		return addTotalSorce;
	}
	public void setAddTotalSorce(Float addTotalSorce) {
		this.addTotalSorce = addTotalSorce;
	}
	public Long getAllTotalCount() {
		return allTotalCount;
	}
	public void setAllTotalCount(Long allTotalCount) {
		this.allTotalCount = allTotalCount;
	}
	public Float getAllTotalCountSorce() {
		return allTotalCountSorce;
	}
	public void setAllTotalCountSorce(Float allTotalCountSorce) {
		this.allTotalCountSorce = allTotalCountSorce;
	}
	

	
	
	
	
	

}
