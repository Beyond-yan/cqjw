package com.gdssoft.cqjt.pojo;

import com.google.gson.annotations.Expose;

public class DepartmentScore extends BasePojo {

	public static final String SCORE_ITEM_SEND = "send";
	public static final String SCORE_ITEM_PUBLISHED = "published";
	/*
	 * 唯一标示
	 */
	@Expose
	private String scoreId;
	/*
	 * 得分项信息    
	 */
	@Expose
	private String scoreInfo;
	/*
	 * 得分部门id
	 */
	@Expose
	private String deptId;
	/*
	 * 得分部门名称
	 */
	@Expose
	private String deptName;
	/*
	 * 得分稿id
	 */
	@Expose
	private String newsId;
	/*
	 * 该稿的累计得分
	 */
	@Expose
	private double score;
	/*
	 * 得分稿类别
	 */
	@Expose
	private String scoreType;
	/*
	 * 创建时间
	 */
	@Expose
	private String createDate;
	/*
	 * 更新时间
	 */
	@Expose
	private String updateDate;
	/*
	 * 是否删除
	 */
	@Expose
	private String isDel;
	
	
	
	public String getScoreId() {
		return scoreId;
	}
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	public String getScoreInfo() {
		return scoreInfo;
	}
	public void setScoreInfo(String scoreInfo) {
		this.scoreInfo = scoreInfo;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getScoreType() {
		return scoreType;
	}
	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	
	
	

}
