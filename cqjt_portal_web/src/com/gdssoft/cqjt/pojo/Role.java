package com.gdssoft.cqjt.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.google.gson.annotations.Expose;

//commented by Cube @130725
/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;*/


public class Role extends BasePojo implements Serializable {

	private static final long serialVersionUID = 2686470026810050166L;

	// 角色編碼
	@Expose
	private String roleId;

	@Expose
	private String roleName;

	@Expose
	private Date createDate;

	@Expose
	private String createUserno;

	@Expose
	private Date updateDate;

	@Expose
	private String updateId;

	@Expose
	private String description;
	
	//renamed by Cube @130801
	@Expose
	private String isDel;
	
	
	private Set<UserDetail> users;

	
	private Set<AuthorityResource> resources;
	
	
	public String getDeleteOrNot() {
		return isDel;
	}

	public void setDeleteOrNot(String deleteOrNot) {
		this.isDel = deleteOrNot;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserno() {
		return createUserno;
	}

	public void setCreateUserno(String createUserno) {
		this.createUserno = createUserno;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserDetail> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDetail> users) {
		this.users = users;
	}

	public Set<AuthorityResource> getResources() {
		return resources;
	}

	public void setResources(Set<AuthorityResource> resources) {
		this.resources = resources;
	}

}
