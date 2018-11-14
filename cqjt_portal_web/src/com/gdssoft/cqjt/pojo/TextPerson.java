package com.gdssoft.cqjt.pojo;

import java.io.Serializable;
import java.util.Date;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class TextPerson  extends BasePojo implements Serializable {
	
	private static final long serialVersionUID = 1L;
 
	@Expose
	private String personId;
	@Expose
	private String personTitle;
	@Expose
	private String subPersonTitle;
	@Expose
	private String personContent;
	@Expose
	private String entryUser;
	@Expose
	private Date entryDate;
	@Expose
	private String isDel;
	@Expose
	private String flag;//0草稿，1投稿
	@Expose
	private String createDate;
	@Expose
	private String createBy;
	@Expose
	private String entryDept;
	@Expose
	private String deptName;
	@Expose
	private String textNum;
	@Expose
	private String pubDapt;
	@Expose
	private String pubDate;
	@Expose
	private String subjectCat;
	@Expose
	private String indexNum;
	@Expose
	private String subjectWords;
	@Expose
	private String appointCat;
	@Expose
	private String pubUser;
	
	
	public String getSubPersonTitle() {
		return subPersonTitle;
	}
	public void setSubPersonTitle(String subPersonTitle) {
		this.subPersonTitle = subPersonTitle;
	}
	public String getPubUser() {
		return pubUser;
	}
	public void setPubUser(String pubUser) {
		this.pubUser = pubUser;
	}
	public String getAppointCat() {
		return appointCat;
	}
	public void setAppointCat(String appointCat) {
		this.appointCat = appointCat;
	}
	public TextPerson() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonTitle() {
		return personTitle;
	}
	public void setPersonTitle(String personTitle) {
		this.personTitle = personTitle;
	}
	public String getPersonContent() {
		return personContent;
	}
	public void setPersonContent(String personContent) {
		this.personContent = personContent;
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
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getEntryDept() {
		return entryDept;
	}
	public void setEntryDept(String entryDept) {
		this.entryDept = entryDept;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTextNum() {
		return textNum;
	}
	public void setTextNum(String textNum) {
		this.textNum = textNum;
	}
	public String getPubDapt() {
		return pubDapt;
	}
	public void setPubDapt(String pubDapt) {
		this.pubDapt = pubDapt;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getSubjectCat() {
		return subjectCat;
	}
	public void setSubjectCat(String subjectCat) {
		this.subjectCat = subjectCat;
	}
	public String getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}
	public String getSubjectWords() {
		return subjectWords;
	}
	public void setSubjectWords(String subjectWords) {
		this.subjectWords = subjectWords;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

 
 
	
	
	
	


	 

 
 

 

 
 
 
 
 
}
