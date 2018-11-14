package com.gdssoft.cqjt.pojo.search;

import com.google.gson.annotations.Expose;

public class PublicArchivesSearch {
	
	@Expose
	private String archivesid;	
	@Expose
	private String subject;
	@Expose
	private String issuer;	
	@Expose
	private String createtime;	
	@Expose
	private String schema_code;
	@Expose
	private int totals;
	@Expose
	private int pageIndex;
	@Expose
	private int pageSize;
	
	
	public String getArchivesid() {
		return archivesid;
	}
	public void setArchivesid(String archivesid) {
		this.archivesid = archivesid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSchema_code() {
		return schema_code;
	}
	public void setSchema_code(String schema_code) {
		this.schema_code = schema_code;
	}
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
	

}
