package com.gdssoft.cqjt.pojo;

import java.util.Date;
import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class TextGovInfo extends BasePojo {
	
	
	/*
	 * 唯一标识
	 */
	@Expose
	private String giId;
	/*
	 * 对应原稿信息id
	 */
	@Expose
	private String newsId;
	/*
	 * 信息标题
	 */
	@Expose
	private String giTitle;
	/*
	 * 信息内容
	 */
	private String giContent;
	/*
	 * 采用类别
	 */
	@Expose
	private String adoptType;
	/*
	 * 投稿人
	 */
	@Expose
	private String entryUser;
	/*
	 *投稿日期
	 */
	@Expose
	private Date entryDate;
	
	@Expose
	private String entryDateStr;
	
	public String getEntryDateStr() {
		return entryDateStr;
	}
	public void setEntryDateStr(String entryDateStr) {
		this.entryDateStr = entryDateStr;
	}
	/*
	 * 投稿部门
	 */
	@Expose
	private String entryDept;
	/*
	 * 信息创建人
	 */
	@Expose
	private String createBy;
	/*
	 * 序号
	 */
	@Expose
	private Long sequenceNum;
	/*
	 * 刊物类别
	 */
	@Expose
	private String pubType;
	/*
	 * 刊物id
	 */
	@Expose
	private String pubId;
	/*
	 * 是否删除 0-未删除  1-已删除
	 */
	@Expose
	private String isDel;
	/*
	 * 创建日期
	 */
	@Expose
	private String createDate;
	/*
	 * 是否上报  0-没有上报  1-已上报
	 */
	@Expose
	private String isReport;
	/*
	 * 上报类别
	 */
	@Expose
	private String reportType;
	/*
	 * 作者
	 */
	@Expose
	private String newsAuthor;
	/*
	 * 被合并的id
	 */
	@Expose
	private String mergeId;
	
	/*
	 * 判断是否选择刊物
	 */
	@Expose
	private String isSelected;
	
	
	
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getGiId() {
		return giId;
	}
	public void setGiId(String giId) {
		this.giId = giId;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getGiTitle() {
		return giTitle;
	}
	public void setGiTitle(String giTitle) {
		this.giTitle = giTitle;
	}
	public String getGiContent() {
		return giContent;
	}
	public void setGiContent(String giContent) {
		this.giContent = giContent;
	}
	public String getAdoptType() {
		return adoptType;
	}
	public void setAdoptType(String adoptType) {
		this.adoptType = adoptType;
	}
	public String getEntryUser() {
		return entryUser;
	}
	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getEntryDept() {
		return entryDept;
	}
	public void setEntryDept(String entryDept) {
		this.entryDept = entryDept;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Long getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(Long sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public String getPubType() {
		return pubType;
	}
	public void setPubType(String pubType) {
		this.pubType = pubType;
	}
	public String getPubId() {
		return pubId;
	}
	public void setPubId(String pubId) {
		this.pubId = pubId;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getIsReport() {
		return isReport;
	}
	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}
	public String getNewsAuthor() {
		return newsAuthor;
	}
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	public String getMergeId() {
		return mergeId;
	}
	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}
	

}
