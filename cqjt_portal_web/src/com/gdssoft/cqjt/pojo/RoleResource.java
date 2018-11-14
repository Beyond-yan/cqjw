package com.gdssoft.cqjt.pojo;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * 角色資源類
 * 
 * @Title:RoleResource.java
 * @Descrition:TODO
 * @Version:V1.0
 */

public class RoleResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expose
	private String roleResourceId;

	@Expose
	private String roleId;

	@Expose
	private String resourceId;

	@Expose
	private Date createDate;

	@Expose
	private String createUserNo;

	@Expose
	private Date updateDate;

	@Expose
	private String authority;

	@Expose
	private String updateUserNo;

	@Expose
	private String resourceName;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserNo() {
		return createUserNo;
	}

	public void setCreateUserNo(String createUserNo) {
		this.createUserNo = createUserNo;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public String getUpdateUserNo() {
		return updateUserNo;
	}

	public void setUpdateUserNo(String updateUserNo) {
		this.updateUserNo = updateUserNo;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getRoleResourceId() {
		return roleResourceId;
	}

	public void setRoleResourceId(String roleResourceId) {
		this.roleResourceId = roleResourceId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
