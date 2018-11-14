package com.gdssoft.cqjt.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;

public class RolePermission extends BasePojo {
	@Expose
   private String  resourceID;
	@Expose
   private String  resourceName;
	@Expose
   private String  parentID;
	@Expose
   private String  isCheck;
	@Expose
   private String  isOpen;
	@Expose
   private String  roleID;
	@Expose
   private String  createUser;
	@Expose
   private String  createDate;
	@Expose
   private String  modifyUser;
	@Expose
   private String  modifyDate;
   
   private List<RolePermission> rolePermissionList;

public String getResourceID() {
	return resourceID;
}

public void setResourceID(String resourceID) {
	this.resourceID = resourceID;
}

public String getResourceName() {
	return resourceName;
}

public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}

public String getParentID() {
	return parentID;
}

public void setParentID(String parentID) {
	this.parentID = parentID;
}

public String getIsCheck() {
	return isCheck;
}

public void setIsCheck(String isCheck) {
	this.isCheck = isCheck;
}

public String getIsOpen() {
	return isOpen;
}

public void setIsOpen(String isOpen) {
	this.isOpen = isOpen;
}

public String getRoleID() {
	return roleID;
}

public void setRoleID(String roleID) {
	this.roleID = roleID;
}

public String getCreateUser() {
	return createUser;
}

public void setCreateUser(String createUser) {
	this.createUser = createUser;
}

public String getCreateDate() {
	return createDate;
}

public void setCreateDate(String createDate) {
	this.createDate = createDate;
}

public String getModifyUser() {
	return modifyUser;
}

public void setModifyUser(String modifyUser) {
	this.modifyUser = modifyUser;
}

public String getModifyDate() {
	return modifyDate;
}

public void setModifyDate(String modifyDate) {
	this.modifyDate = modifyDate;
}

public List<RolePermission> getRolePermissionList() {
	return rolePermissionList;
}

public void setRolePermissionList(List<RolePermission> rolePermissionList) {
	this.rolePermissionList = rolePermissionList;
}
   
   
}
