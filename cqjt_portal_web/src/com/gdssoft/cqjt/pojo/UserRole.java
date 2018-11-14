package com.gdssoft.cqjt.pojo;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
/**
 * 用戶角色類
 * 
 * @Title:RoleResource.java
 * @Descrition:TODO
 * @Author:F3221422
 * @Date:2011/4/13
 * @Version:V1.0
 */

public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5791472274105987964L;

	@Expose
	private String urId;

	@Expose
	private String 	userId;

	@Expose
	private String roleId;

	@Expose
	private String createDate;

	@Expose
	private String createUserNo;

	@Expose
	private String updateDate;

	@Expose
	private String 	updateUserNo;

	@Expose
	private String personName;
	
	public UserRole(){
		
	}
	
	public UserRole(String userId, String personName){
		this.userId = userId;
		this.personName = personName;
	}
	
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getUrId() {
		return urId;
	}

	public void setUrId(String urId) {
		this.urId = urId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String string) {
		this.createDate = string;
	}

	public String getCreateUserNo() {
		return createUserNo;
	}

	public void setCreateUserNo(String createUserNo) {
		this.createUserNo = createUserNo;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUserNo() {
		return updateUserNo;
	}

	public void setUpdateUserNo(String updateUserNo) {
		this.updateUserNo = updateUserNo;
	}
	
	
}
