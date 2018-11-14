package com.gdssoft.cqjt.pojo.content;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;
/**
 * 文字类数据
 * @author  gyf 20140708
 * @return
 */
public class TextData extends BasePojo  {
	@Expose
	private String textId;  
	@Expose
	private String title;   //标题
	@Expose
	private String textContent;   //内容
	@Expose
	private String author;   //作者
	@Expose
	private String position;    //版面位置
	@Expose
	private String entryDept;  //投稿部门
	@Expose
	private String createDate;   //创建时间
	@Expose
	private String textSource; //来源
	@Expose
	private String isPhotoShow;   //图片是否滚动显示
	@Expose
	private String photoUrl;    //图片URL
	@Expose
	private String approvaler;  //审核人
	@Expose
	private String isDel;   //是否删除 0未删除   1删除    
	@Expose
	private String status; //状态  1发布   0草稿
	@Expose
	private String photos;    //中间传参用
	
	public String getTextId() {
		return textId;
	}
	public void setTextId(String textId) {
		this.textId = textId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEntryDept() {
		return entryDept;
	}
	public void setEntryDept(String entryDept) {
		this.entryDept = entryDept;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getTextSource() {
		return textSource;
	}
	public void setTextSource(String textSource) {
		this.textSource = textSource;
	}
	public String getIsPhotoShow() {
		return isPhotoShow;
	}
	public void setIsPhotoShow(String isPhotoShow) {
		this.isPhotoShow = isPhotoShow;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
}
