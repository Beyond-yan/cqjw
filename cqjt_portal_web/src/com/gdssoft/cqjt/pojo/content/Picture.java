package com.gdssoft.cqjt.pojo.content;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;
/**
 * 图片信息管理
 * @author  201407014
 * @return
 */

public class Picture extends BasePojo  {
	@Expose
	private String picId;
	@Expose
	private String picTitle;
	@Expose
	private String subTitle;
	@Expose
	private String entryUser;
	@Expose
	private String entryDate;
	@Expose
	private String approvaler;
	@Expose
	private String source;
	@Expose
	private String picDesc;
	@Expose
	private String picUrl;
	@Expose
	private String isDel;
	@Expose
	private String flag;
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public String getPicTitle() {
		return picTitle;
	}
	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getEntryUser() {
		return entryUser;
	}
	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getApprovaler() {
		return approvaler;
	}
	public void setApprovaler(String approvaler) {
		this.approvaler = approvaler;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPicDesc() {
		return picDesc;
	}
	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
