package com.gdssoft.cqjt.pojo;

import java.util.List;
import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class TextPublication extends BasePojo{

	@Expose
	 private String pubId;
	@Expose
	 private String pubCode;
	@Expose
	 private String pubName;
	@Expose
	 private String createUser;
	@Expose
	 private String createDate;
	@Expose
	 private String updateUser;
	@Expose
	 private String updateDate;
	@Expose
	 private String isDel;
	 @Expose
	 private String isPublic;//状态  0 草稿  ， 1 已发布


	 private List<TextGovInfo> workDynamics;
	 private List<TextGovInfo> countyDynamics;
	 private List<TextGovInfo> oneInfos;
	 private List<TextGovInfo> netInfos;
	 
	public String getPubId() {
		return pubId;
	}
	public void setPubId(String pubId) {
		this.pubId = pubId;
	}
	public String getPubCode() {
		return pubCode;
	}
	public void setPubCode(String pubCode) {
		this.pubCode = pubCode;
	}
	public String getPubName() {
		return pubName;
	}
	public void setPubName(String pubName) {
		this.pubName = pubName;
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
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
	public List<TextGovInfo> getWorkDynamics() {
		return workDynamics;
	}
	public void setWorkDynamics(List<TextGovInfo> workDynamics) {
		this.workDynamics = workDynamics;
	}
	public List<TextGovInfo> getCountyDynamics() {
		return countyDynamics;
	}
	public void setCountyDynamics(List<TextGovInfo> countyDynamics) {
		this.countyDynamics = countyDynamics;
	}
	public List<TextGovInfo> getOneInfos() {
		return oneInfos;
	}
	public void setOneInfos(List<TextGovInfo> oneInfos) {
		this.oneInfos = oneInfos;
	}
	public List<TextGovInfo> getNetInfos() {
		return netInfos;
	}
	public void setNetInfos(List<TextGovInfo> netInfos) {
		this.netInfos = netInfos;
	}
	 
}
