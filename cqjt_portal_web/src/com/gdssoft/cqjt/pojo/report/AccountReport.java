package com.gdssoft.cqjt.pojo.report;


import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class AccountReport extends BasePojo {
	@Expose
	private String deptCategory;
	@Expose
	private String deptName;
	@Expose
	private String adoptNum;
	@Expose
	private Float totalPrice;
	@Expose
	private String title;
	@Expose
	private String entryDate;
	@Expose
	private String adoptType;
	
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
	public String getAdoptNum() {
		return adoptNum;
	}
	public void setAdoptNum(String adoptNum) {
		this.adoptNum = adoptNum;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String  entryDate) {
		this.entryDate = entryDate;
	}
	public String getAdoptType() {
		return adoptType;
	}
	public void setAdoptType(String adoptType) {
		this.adoptType = adoptType;
	}
		
}
