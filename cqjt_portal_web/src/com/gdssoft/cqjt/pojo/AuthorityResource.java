package com.gdssoft.cqjt.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

import com.google.gson.annotations.Expose;

public class AuthorityResource extends BasePojo{
	
	@Expose
	private String resourceId;	
	@Expose
	private String resourceName;
	@Expose
	private String resourceType;	
	@Expose
	private String resourceValue;	
	@Expose
	private String resourcePriority;
	@Expose
	private Date createDate;	
	@Expose
	private String createUserno;	
	@Expose
	private Date updateDate;	
	@Expose
	private String updateId;	
	@Expose
	private String resourceDesc;	
	@Expose
	private String deleteOrNot;
	//added by Cube @130725
	@Expose
	private String parentID;
	
	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getIsCategory() {
		return isCategory;
	}

	public void setIsCategory(String isCategory) {
		this.isCategory = isCategory;
	}

	private String isCategory;
	
	public String getDeleteOrNot() {
		return deleteOrNot;
	}

	public void setDeleteOrNot(String deleteOrNot) {
		this.deleteOrNot = deleteOrNot;
	}
	
	private Set<Role> roles; 
	
	public String getRoleAuthorities() {
    	List<String> roleAuthorities = new ArrayList<String>();
    	for(Role role : roles) {
    		roleAuthorities.add(role.getRoleName());
    	}
        return StringUtils.join(roleAuthorities.toArray(), ",");
    }

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourcePriority() {
		return resourcePriority;
	}

	public void setResourcePriority(String resourcePriority) {
		this.resourcePriority = resourcePriority;
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

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getResourceValue() {
		return resourceValue;
	}

	public void setResourceValue(String resourceValue) {
		this.resourceValue = resourceValue;
	}

		
	public boolean isNew() {
		return (this.resourceId == null);
	}
	
}
