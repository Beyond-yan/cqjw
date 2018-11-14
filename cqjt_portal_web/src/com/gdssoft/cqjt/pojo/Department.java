package com.gdssoft.cqjt.pojo;

import com.google.gson.annotations.Expose;
/**
 * 部门Pojo类
 * @author H2602965
 * @Title:department.java
 * @Descrition:
 * @Date:2014/05/30
 * @Version:V1.0
 */

public class Department extends BasePojo{
	
	@Expose
	private Integer deptID;
	@Expose
	private Integer dcId;
	@Expose
	private Long categoryId;
	@Expose
	private String categoryName;
	@Expose
	private String outsiteProgram;
	@Expose
	private String deptName;
	@Expose
	private String deptCategory;
	@Expose
	private Float scoreSite;
	@Expose
	private Float  scoreGov;
	@Expose
	private Float  scoreSiteTotal;
	@Expose
	private Float  scoreGovTotal;
	@Expose
	private Float  scoreSiteYear;
	@Expose
	private Float  scoreGovYear;
	@Expose
	private Float  scoreSiteYearTotal;
	@Expose
	private Float  scoreGovYearTotal;
	@Expose
	private String createDate;
	@Expose
	private String createUserNo;
	@Expose
	private String updateDate;
	@Expose
	private String updateUserNo;
	@Expose
	private String isDel;
	@Expose
	private String flag;
	@Expose
	private String sequenceNum;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Integer getDeptID() {
		return deptID;
	}
	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCategory() {
		return deptCategory;
	}
	public void setDeptCategory(String deptCategory) {
		this.deptCategory = deptCategory;
	}
	public Float getScoreSite() {
		return scoreSite;
	}
	public void setScoreSite(Float scoreSite) {
		this.scoreSite = scoreSite;
	}
	public Float getScoreGov() {
		return scoreGov;
	}
	public void setScoreGov(Float scoreGov) {
		this.scoreGov = scoreGov;
	}
	public String getCreateUserNo() {
		return createUserNo;
	}
	public void setCreateUserNo(String createUserNo) {
		this.createUserNo = createUserNo;
	}
	public String getUpdateUserNo() {
		return updateUserNo;
	}
	public void setUpdateUserNo(String updateUserNo) {
		this.updateUserNo = updateUserNo;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getOutsiteProgram() {
		return outsiteProgram;
	}
	public void setOutsiteProgram(String outsiteProgram) {
		this.outsiteProgram = outsiteProgram;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getDcId() {
		return dcId;
	}
	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}
	public String getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public Float getScoreSiteTotal() {
		return scoreSiteTotal;
	}
	public void setScoreSiteTotal(Float scoreSiteTotal) {
		this.scoreSiteTotal = scoreSiteTotal;
	}
	public Float getScoreGovTotal() {
		return scoreGovTotal;
	}
	public void setScoreGovTotal(Float scoreGovTotal) {
		this.scoreGovTotal = scoreGovTotal;
	}
	public Float getScoreSiteYear() {
		return scoreSiteYear;
	}
	public void setScoreSiteYear(Float scoreSiteYear) {
		this.scoreSiteYear = scoreSiteYear;
	}
	public Float getScoreGovYear() {
		return scoreGovYear;
	}
	public void setScoreGovYear(Float scoreGovYear) {
		this.scoreGovYear = scoreGovYear;
	}
	public Float getScoreSiteYearTotal() {
		return scoreSiteYearTotal;
	}
	public void setScoreSiteYearTotal(Float scoreSiteYearTotal) {
		this.scoreSiteYearTotal = scoreSiteYearTotal;
	}
	public Float getScoreGovYearTotal() {
		return scoreGovYearTotal;
	}
	public void setScoreGovYearTotal(Float scoreGovYearTotal) {
		this.scoreGovYearTotal = scoreGovYearTotal;
	}
}
