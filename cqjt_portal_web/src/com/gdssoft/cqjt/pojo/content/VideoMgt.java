package com.gdssoft.cqjt.pojo.content;
import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;
/**
 * 视频管理类
 * @author H2602965
 * @Date 2014/7/15
 *
 */
public class VideoMgt extends BasePojo {
	
	/*
	 * 视频Id
	 */
	@Expose
	private String videoId;
	/*视频标题*/
	@Expose
	private String videoTitle;
	/*副标题*/
	@Expose
	private String subVideoTitle;
	/*投稿人*/
	@Expose
	private String entryUser;
	/*投稿部门*/
	@Expose
	private String entryDeptName;
	/*投稿部门Id*/
	@Expose
	private String entryDeptId;
	/*视频描述*/
	@Expose
	private String videoDesc;
	/*
	 * 标识符
	 * flag 状态标识(0 草稿,1 已投稿,2 已审核)
	 * 
	 * */
	@Expose
	private String flag;
	/*类别*/
	@Expose
	private String category;
	/*视频uRl*/
	@Expose
	private String videoUrl;
	/*	图片url*/
	@Expose
	private String photoUrl;
	/*创建时间*/
	@Expose
	private String createDate;
	/*审核人*/
	@Expose
	private String approvaler;
	/*视频来源*/
	@Expose
	private String videoSource;
	/*是否上外网
	0 不可上外网
	1 可以上外网*/
	@Expose
	private String isPublic;
	/*是否删除标识
	0 未删除
	1 已删除*/
	@Expose
	private String isDel;
	
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

}
