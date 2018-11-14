package com.gdssoft.cqjt.pojo.videoNews;


import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class VideoNews extends BasePojo{
	

	@Expose
	private String videoId;
	@Expose
	private String videoTitle;
	@Expose
	private String subVideoTitle;
	
	@Expose
	private String entryUser;
	@Expose
	private String entryDeptName;
	@Expose
	private String entryDeptId;
	@Expose
	private String videoDesc;
	@Expose
	private String flag;
	@Expose
	private String category;
	@Expose
	private String videoUrl;
	@Expose
	private String photoUrl;
	@Expose
	private String createDate;
	@Expose
	private String verifyDate;
	@Expose
	private String approvaler;
	@Expose
	private String videoSource;
	@Expose
	private String isPublic;
	@Expose
	private String isDel;
	@Expose
	private String type;
	@Expose
	private String fileName;
	@Expose
	private String imageName;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getSubVideoTitle() {
		return subVideoTitle;
	}
	public void setSubVideoTitle(String subVideoTitle) {
		this.subVideoTitle = subVideoTitle;
	}
	public String getEntryUser() {
		return entryUser;
	}
	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}
	public String getEntryDeptName() {
		return entryDeptName;
	}
	public void setEntryDeptName(String entryDeptName) {
		this.entryDeptName = entryDeptName;
	}
	public String getEntryDeptId() {
		return entryDeptId;
	}
	public void setEntryDeptId(String entryDeptId) {
		this.entryDeptId = entryDeptId;
	}
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getApprovaler() {
		return approvaler;
	}
	public void setApprovaler(String approvaler) {
		this.approvaler = approvaler;
	}
	public String getVideoSource() {
		return videoSource;
	}
	public void setVideoSource(String videoSource) {
		this.videoSource = videoSource;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
