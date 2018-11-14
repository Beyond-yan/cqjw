package com.gdssoft.cqjt.pojo;

import java.util.List;

public class RedBalckData {

	private String result;
	private String message;
	private List<RedBalckForList> lists;
	private String version;
	private String updateurl;
	private String dataType;
	private String dataTitle;
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataTitle() {
		return dataTitle;
	}
	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public List<RedBalckForList> getLists() {
		return lists;
	}
	public void setLists(List<RedBalckForList> lists) {
		this.lists = lists;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUpdateurl() {
		return updateurl;
	}
	public void setUpdateurl(String updateurl) {
		this.updateurl = updateurl;
	}
}
