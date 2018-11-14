package com.gdssoft.cqjt.pojo;

import com.google.gson.annotations.Expose;

public class CategoryRelation extends BasePojo {
	
	@Expose
	private String relationId;	
	@Expose
	private String innerCategory;
	@Expose
	private String outerCategory;	
	@Expose
	private String innerId;	
	@Expose
	private String outerId;
	@Expose
	private String createDate;	
	@Expose
	private String createUser;	
	@Expose
	private String isDel;
	@Expose
	private String isPublic;
	
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getInnerCategory() {
		return innerCategory;
	}
	public void setInnerCategory(String innerCategory) {
		this.innerCategory = innerCategory;
	}
	public String getOuterCategory() {
		return outerCategory;
	}
	public void setOuterCategory(String outerCategory) {
		this.outerCategory = outerCategory;
	}
	public String getInnerId() {
		return innerId;
	}
	public void setInnerId(String innerId) {
		this.innerId = innerId;
	}
	public String getOuterId() {
		return outerId;
	}
	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}	
	

}
