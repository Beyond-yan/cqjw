package com.gdssoft.cqjt.pojo.report;

import com.gdssoft.cqjt.pojo.BasePojo;

public class GovNewsReport extends BasePojo {

	private Integer deptId;
	private String deptName;
	private String deptCategory;
	private String newsTitle;
	/**
	 * sendSite:网站信息投稿,
	 * isPublic:网站信息上外网, 
	 * dailyInfo:每日信息
	 * subjectInfo:专题信息
	 * cityCommittee:市委采用
	 * cityGovernment:市政府采用
	 * trafficDept:交通部采用
	 * comLeaderAppr:被委领导批示
	 * govLeaderAppr:被市领导批示
	 * trafLeaderAppr:被交通部领导批示
	 * sendGov:政务信息投稿
	 * sendVideo:视频报送信息
	 */
	private String adoptType;
	private Integer govUseFlag;//0,'未采编',1,'采编已成刊',2,'采编未成刊', 3,'采编未成刊'
	private String status;
	private String reportType;//上报类型


	public String getDeptCategory() {
		return deptCategory;
	}
	public void setDeptCategory(String deptCategory) {
		this.deptCategory = deptCategory;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public Integer getGovUseFlag() {
		return govUseFlag;
	}
	public void setGovUseFlag(Integer govUseFlag) {
		this.govUseFlag = govUseFlag;
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
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getAdoptType() {
		return adoptType;
	}
	public void setAdoptType(String adoptType) {
		this.adoptType = adoptType;
	}
	
}
