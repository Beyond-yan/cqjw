package com.gdssoft.cqjt.pojo;

import com.google.gson.annotations.Expose;

public class TextCategory extends BasePojo {

	@Expose
	private Long categoryId;  //String->Long
	@Expose
	private String categoryName;
	@Expose
	private String outsiteProgram;
	@Expose
	private String isDel;    //是否删除 0未删除   1删除
	@Expose
	private String createDate;  //创建时间
	@Expose
	private String createBy;   //创建人
	@Expose
	private String isOutsite; //栏目类型  1 内网栏目， 0外网栏目
	
	private Long id; //首页模块查询条件 ID
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getOutsiteProgram() {
		return outsiteProgram;
	}
	public void setOutsiteProgram(String outsiteProgram) {
		this.outsiteProgram = outsiteProgram;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getIsOutsite() {
		return isOutsite;
	}
	public void setIsOutsite(String isOutsite) {
		this.isOutsite = isOutsite;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
