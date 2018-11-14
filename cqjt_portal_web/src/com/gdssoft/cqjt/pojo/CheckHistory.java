package com.gdssoft.cqjt.pojo;

import com.google.gson.annotations.Expose;

/**
 * 考核历史信息     
 * @author  20150115 gyf
 *
 */
public class CheckHistory extends BasePojo{
	@Expose
	private String checkId;  //id
	@Expose
	private String checkName;  //名称
	@Expose
	private String checkDesc;  //备注
	@Expose
	private String filePath;  //路径
	@Expose
	private String isDel;    //是否删除  0未删除   1删除
	@Expose
	private String createDate;  //创建时间
	@Expose
	private String createBy;   //创建人
	@Expose
	private String checkType;   //类别（选项：网站信息、政务信息）
	@Expose
	private String attachment;   //附件
	@Expose
	private String checkGroup;   //考核分组（如报表分组）
	@Expose
	private String path;   //
	
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public String getCheckDesc() {
		return checkDesc;
	}
	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getCheckGroup() {
		return checkGroup;
	}
	public void setCheckGroup(String checkGroup) {
		this.checkGroup = checkGroup;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
