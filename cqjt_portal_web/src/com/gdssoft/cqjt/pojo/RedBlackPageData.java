package com.gdssoft.cqjt.pojo;

import java.util.List;

import com.centit.gk.msasoa.BlackRedBackRequest;
import org.apache.commons.lang.builder.ToStringBuilder;

public class RedBlackPageData {

	private String result;
	private String resultcode;
	private String message;
	private List<RedBlackType> rbTypeList;
	private String version;
	private String updateurl;
	private RedBlackQueryRecord record;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<RedBlackType> getRbTypeList() {
		return rbTypeList;
	}
	public void setRbTypeList(List<RedBlackType> rbTypeList) {
		this.rbTypeList = rbTypeList;
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

	public RedBlackQueryRecord getRecord() {
		return record;
	}

	public void setRecord(RedBlackQueryRecord record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
