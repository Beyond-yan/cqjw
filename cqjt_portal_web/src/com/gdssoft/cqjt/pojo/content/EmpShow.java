package com.gdssoft.cqjt.pojo.content;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;
 

public class EmpShow extends BasePojo  {
	
	@Expose
	private String empId;
	@Expose
	private String empName;
	@Expose
	private String empPost;
	@Expose
	private String entryUser;
	@Expose
	private String entryDate;
	@Expose
	private String empDesc;
	@Expose
	private String empUrl;
	@Expose
	private String approvaler;
	@Expose
	private String source;
	@Expose
	private String flag;
	@Expose
	private String isDel;
	@Expose
	private String deptName;
	@Expose
	private String categoryName;
	@Expose
	private String empSort;
	
	
	
	
 
	public String getEmpSort() {
		return empSort;
	}
	public void setEmpSort(String empSort) {
		this.empSort = empSort;
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPost() {
		return empPost;
	}
	public void setEmpPost(String empPost) {
		this.empPost = empPost;
	}
	public String getEntryUser() {
		return entryUser;
	}
	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getEmpUrl() {
		return empUrl;
	}
	public void setEmpUrl(String empUrl) {
		this.empUrl = empUrl;
	}
	public String getApprovaler() {
		return approvaler;
	}
	public void setApprovaler(String approvaler) {
		this.approvaler = approvaler;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
}
