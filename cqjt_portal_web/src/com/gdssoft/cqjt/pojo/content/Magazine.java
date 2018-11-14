package com.gdssoft.cqjt.pojo.content;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;
/**
 * 杂志刊物管理
 * @author  20140707
 * @return
 */

public class Magazine extends BasePojo {
	    @Expose
	    private String magazineId;//杂志id
		@Expose
		private String Title;	//杂志标题
	    @Expose
	    private String magazineNo;//杂志期数
		@Expose
		private String entryUser; //投稿人
		@Expose
		private String entryDate; //投稿日期
		@Expose
		private String photoUrl; //主图片的路径
	    @Expose
		private String magazineUrl; //杂志文件
		@Expose
		private String approvaler; //审核人
		@Expose
		private String isDel; //是否删除
	    @Expose
		private String flag; //文件的标志
		public String getMagazineId() {
			return magazineId;
		}
		public void setMagazineId(String magazineId) {
			this.magazineId = magazineId;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getMagazineNo() {
			return magazineNo;
		}
		public void setMagazineNo(String magazineNo) {
			this.magazineNo = magazineNo;
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
		public String getPhotoUrl() {
			return photoUrl;
		}
		public void setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
		}
		public String getMagazineUrl() {
			return magazineUrl;
		}
		public void setMagazineUrl(String magazineUrl) {
			this.magazineUrl = magazineUrl;
		}
		public String getApprovaler() {
			return approvaler;
		}
		public void setApprovaler(String approvaler) {
			this.approvaler = approvaler;
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
