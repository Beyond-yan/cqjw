package com.gdssoft.cqjt.pojo.report;

import java.util.ArrayList;
import java.util.List;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class GovCheckReport extends BasePojo {
	
	@Expose
	private String deptCategory;//分类名称
	@Expose
	private String deptName;//部门名称
	@Expose
	private String adoptNum;	//部门个数
	@Expose
	private Float score;//得分
	@Expose
	private String title;//标题
	@Expose
	private String adoptType;//采用类型
	@Expose
	private String deptId;
	@Expose
	private List<GovCheckReport> reportList = new ArrayList<GovCheckReport>();
	
	public List<GovCheckReport> getReportList() {
		return reportList;
	}
	public void setReportList(List<GovCheckReport> reportList) {
		this.reportList = reportList;
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
	public String getAdoptNum() {
		return adoptNum;
	}
	public void setAdoptNum(String adoptNum) {
		this.adoptNum = adoptNum;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAdoptType() {
		return adoptType;
	}
	public void setAdoptType(String adoptType) {
		this.adoptType = adoptType;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	
	
	

}
