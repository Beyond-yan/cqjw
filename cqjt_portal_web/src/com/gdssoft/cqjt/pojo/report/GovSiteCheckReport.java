package com.gdssoft.cqjt.pojo.report;

import java.util.Date;

import com.gdssoft.cqjt.pojo.BasePojo;

public class GovSiteCheckReport extends BasePojo {
	
	private Long reportId;
	private Integer deptId;
	private String deptName;
	private String deptCategoryName;
	private float govScore;
	private float govTotalScore;
	private float siteScore;
	private float siteTotalScore;
	private Date reportDate;
	
	
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCategoryName() {
		return deptCategoryName;
	}
	public void setDeptCategoryName(String deptCategoryName) {
		this.deptCategoryName = deptCategoryName;
	}
	public float getGovScore() {
		return govScore;
	}
	public void setGovScore(float govScore) {
		this.govScore = govScore;
	}
	public float getGovTotalScore() {
		return govTotalScore;
	}
	public void setGovTotalScore(float govTotalScore) {
		this.govTotalScore = govTotalScore;
	}
	public float getSiteScore() {
		return siteScore;
	}
	public void setSiteScore(float siteScore) {
		this.siteScore = siteScore;
	}
	public float getSiteTotalScore() {
		return siteTotalScore;
	}
	public void setSiteTotalScore(float siteTotalScore) {
		this.siteTotalScore = siteTotalScore;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	
}
