package com.gdssoft.cqjt.pojo.search;

import com.google.gson.annotations.Expose;

public class InnerSiteSearch {
	
	@Expose
	private String news_id;	
	@Expose
	private String news_title;
	@Expose
	private String entry_user;	
	@Expose
	private String entry_date;	
	@Expose
	private String news_content;	
	@Expose
	private String dept_name;
	@Expose
	private String news_flag;
	@Expose
	private int totals;
	@Expose
	private int pageIndex;
	@Expose
	private int pageSize;
	@Expose
	private String path;
	@Expose
	private String attachment;
	
	
	
	
	public int getTotals() {
		return totals;
	}
	public void setTotals(int totals) {
		this.totals = totals;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getNews_flag() {
		return news_flag;
	}
	public void setNews_flag(String news_flag) {
		this.news_flag = news_flag;
	}
	
	

}
