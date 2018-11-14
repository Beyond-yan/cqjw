package com.gdssoft.cqjt.pojo;

import com.google.gson.annotations.Expose;

public class OutSource {
	@Expose
	private String id;
	@Expose
	private String doctitle;
	@Expose
	private String doctime;
	@Expose
	private String keywords;
	@Expose
	private String docsource;
	@Expose
	private String docurl;
	@Expose
	private String doccontent;
	@Expose
	private String import_batch;
	@Expose
	private String outsource_dataname = "舆调数据";
	@Expose
	private String batch;
	@Expose
	private String count;
	
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getOutsource_dataname() {
		return outsource_dataname;
	}
	public void setOutsource_dataname(String outsource_dataname) {
		this.outsource_dataname = outsource_dataname;
	}
	public String getImport_batch() {
		return import_batch;
	}
	public void setImport_batch(String import_batch) {
		this.import_batch = import_batch;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDoctitle() {
		return doctitle;
	}
	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}
	public String getDoctime() {
		return doctime;
	}
	public void setDoctime(String doctime) {
		this.doctime = doctime;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDocsource() {
		return docsource;
	}
	public void setDocsource(String docsource) {
		this.docsource = docsource;
	}
	public String getDocurl() {
		return docurl;
	}
	public void setDocurl(String docurl) {
		this.docurl = docurl;
	}
	public String getDoccontent() {
		return doccontent;
	}
	public void setDoccontent(String doccontent) {
		this.doccontent = doccontent;
	}

}
