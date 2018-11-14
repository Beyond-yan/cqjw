package com.gdssoft.cqjt.pojo;

import java.util.Date;

import com.google.gson.annotations.Expose;


/**
 * @author jimxu
 * 
 */
public class ListItem {
	
	@Expose
	private Integer totlecount;
	@Expose
	private Integer resultcount;
	@Expose
	private String title;
	@Expose
	private Date date;
	@Expose
	private String link;
	@Expose
	private Long userId;
	@Expose
	private String schemaCode;
	
	public Integer getTotlecount() {
		return totlecount;
	}
	public void setTotlecount(Integer totlecount) {
		this.totlecount = totlecount;
	}
	public Integer getResultcount() {
		return resultcount;
	}
	public void setResultcount(Integer resultcount) {
		this.resultcount = resultcount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getSchemaCode() {
		return schemaCode;
	}
	public void setSchemaCode(String schemaCode) {
		this.schemaCode = schemaCode;
	}
	
	
}
