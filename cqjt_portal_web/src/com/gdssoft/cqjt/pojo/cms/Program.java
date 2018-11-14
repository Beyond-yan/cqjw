package com.gdssoft.cqjt.pojo.cms;

import com.google.gson.annotations.Expose;

/**
 * @Title 外网栏目
 * @Date 2013/7/5
 * */
public class Program {
	@Expose
	private String id;
	@Expose
	private String parentId;
	@Expose
	private String name;
	@Expose
	private String type;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}



}
