package com.gdssoft.cqjt.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;

/**
 * 用于绑定下拉菜单
 * 
 * @author F3220900
 * 
 */
public class SelectBean extends BasePojo {

	@Expose
	private String label;
	@Expose
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}

	// added by Cube @130801
	public SelectBean(String value, String text) {
		this.value = value;
		this.label = text;
	}

	public SelectBean() {
	}
}
