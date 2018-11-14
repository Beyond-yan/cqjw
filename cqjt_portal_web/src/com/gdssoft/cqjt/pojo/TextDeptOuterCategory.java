package com.gdssoft.cqjt.pojo;

import com.google.gson.annotations.Expose;
/**
 * 部门与外网栏目关系表
 * @author H2601917
 *
 */
public class TextDeptOuterCategory extends BasePojo{
	@Expose
	private Long docId;  
	@Expose
	private Long deptId;//部门id
	@Expose
	private String outCategoryName;//外网栏目名称
	@Expose
	private String outCategoryId;//外网栏目id
	@Expose
	private String flag;  //0不能直接上外网，1可以直接上外网
	@Expose
	private String isDel;    //是否删除 0未删除   1删除
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getOutCategoryName() {
		return outCategoryName;
	}
	public void setOutCategoryName(String outCategoryName) {
		this.outCategoryName = outCategoryName;
	}
	public String getOutCategoryId() {
		return outCategoryId;
	}
	public void setOutCategoryId(String outCategoryId) {
		this.outCategoryId = outCategoryId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
}
