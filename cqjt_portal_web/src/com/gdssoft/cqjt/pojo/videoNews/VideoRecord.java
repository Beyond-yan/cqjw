package com.gdssoft.cqjt.pojo.videoNews;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class VideoRecord  extends BasePojo{
	@Expose
	private String vrId;
	
	@Expose
	private String videoId;
	
	@Expose
	private String updateDate;
	
	@Expose
	private String updateUser;
	@Expose
	private String flag;
	@Expose
	private String rejectReason;

	public String getVrId() {
		return vrId;
	}

	public void setVrId(String vrId) {
		this.vrId = vrId;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
}
