package com.gdssoft.cqjt.pojo.report;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class siteCheckReport extends BasePojo {
	
	@Expose
	private String deptCategory;	
	@Expose
	private String deptName;
	@Expose
	private String adoptNum;	
	@Expose
	private Float score;	
	@Expose
	private String title;
	@Expose
	private String adoptType;
	@Expose
	private String isPublic;
	@Expose
	private String scoreSum;
	@Expose
	private String deptId;
	@Expose
	private String newsId;
	@Expose
	private String innerCategory;
	@Expose
	private String outerCategory;
	public String getInnerCategory() {
		return innerCategory;
	}
	public void setInnerCategory(String innerCategory) {
		this.innerCategory = innerCategory;
	}
	public String getOuterCategory() {
		return outerCategory;
	}
	public void setOuterCategory(String outerCategory) {
		this.outerCategory = outerCategory;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
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
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public String getScoreSum() {
		return scoreSum;
	}
	public void setScoreSum(String scoreSum) {
		this.scoreSum = scoreSum;
	}
	public String getDeptCategory() {
		return deptCategory;
	}
	public void setDeptCategory(String deptCategory) {
		this.deptCategory = deptCategory;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	
	
	

}
