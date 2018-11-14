package com.gdssoft.cqjt.pojo;

import java.io.Serializable;
import java.util.Date;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class TextStatute  extends BasePojo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 已校编
	 */
	public static final String checked = "1"; // 已校编
	/**
	 * 未校编
	 */
	public static final String unchecked = "0";
	/**
	 * 已删除
	 */
	public static final String deleted = "1"; // 已删除
	/**
	 * 未删除
	 */
	public static final String undeleted = "0"; // 未删除

	@Expose
	private String statuteId;
	@Expose
	private String statuteTitle;
	@Expose
	private String substatuteTitle;

	private String statuteContent;
	@Expose
	private String statuteTagsStr;
	@Expose
	private String category;
	@Expose
	private String entryUser;
	@Expose
	private Date entryDate;
	@Expose
	private String entryDept;
	@Expose
	private String approvaler;
	@Expose
	private String isPhotosShow;
	@Expose
	private String photoUrl;
	@Expose
	private String isDel;
	@Expose
	private String flag;//0草稿，1投稿
	@Expose
	private String statuteAuthor;
	@Expose
	private String statuteSource;
	@Expose
	private String isPublic;
	@Expose
	private int govUseFlag;//0未采编；1采编已成刊；2采编未成刊，3采编未成刊
	@Expose
	private String createDate;
	@Expose
	private String createBy;
	@Expose
	private String deptName;
	@Expose
	private String categoryName;// 栏目名称 20140618 gyf
	@Expose
	private String SUB_CATEGORY;
	@Expose
	private String SUB_CATEGORY_INFO;
	@Expose
	private String pubPerson;
	@Expose
	private String pubDate;
	@Expose
	private String pubTextNum;
	@Expose
	private String pubDapt;
	@Expose
	private String inplementDate;
	@Expose
	private String timeliness;
	@Expose
	private String caption;


	/*
	 * 被合并的id
	 */
	@Expose
	private String mergeId;

	/*
	 * 网站信息上外网后对应的外网id
	 */
	@Expose
	private String outerstatuteId;

	@Expose
	private String categoryOuter;// 外网栏目id
	@Expose
	private String outerCategory;
	@Expose
	private String statuteSort;

 
 

	@Expose
	private String fg;// 标志位
	@Expose
	private int isOldData; // 1旧数据
	
	/**
	 * @add by wanglei 是否再次送校编的栏位 1： 是 0： 否
	 */
	@Expose
	private String isCheckAgain;
	/**
	 * @add by wanglei 内网ID
	 */
	@Expose
	private Long categoryId;
	/**
	 * @add by wl 校编人
	 */
	private String compiler;
	/**
	 * @add by wl
	 * 是否置顶标志位，默认是0不置顶.(0不置顶,1置顶)
	 */
	@Expose
	private String stickState;
	/**
	 * @add by wl
	 * 排序标识
	 * 
	 */
	@Expose
	private Date stickSort;
	
	private String editorValue;
	
	public String getEditorValue() {
		return editorValue;
	}
	public void setEditorValue(String editorValue) {
		this.editorValue = editorValue;
	}
	public String getStatuteId() {
		return statuteId;
	}
	public void setStatuteId(String statuteId) {
		this.statuteId = statuteId;
	}
	public String getStatuteTitle() {
		return statuteTitle;
	}
	public void setStatuteTitle(String statuteTitle) {
		this.statuteTitle = statuteTitle;
	}
	public String getSubstatuteTitle() {
		return substatuteTitle;
	}
	public void setSubstatuteTitle(String substatuteTitle) {
		this.substatuteTitle = substatuteTitle;
	}
	public String getStatuteContent() {
		return statuteContent;
	}
	public void setStatuteContent(String statuteContent) {
		this.statuteContent = statuteContent;
	}
	public String getStatuteTagsStr() {
		return statuteTagsStr;
	}
	public void setStatuteTagsStr(String statuteTagsStr) {
		this.statuteTagsStr = statuteTagsStr;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEntryUser() {
		return entryUser;
	}
	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getEntryDept() {
		return entryDept;
	}
	public void setEntryDept(String entryDept) {
		this.entryDept = entryDept;
	}
	public String getApprovaler() {
		return approvaler;
	}
	public void setApprovaler(String approvaler) {
		this.approvaler = approvaler;
	}
	public String getIsPhotosShow() {
		return isPhotosShow;
	}
	public void setIsPhotosShow(String isPhotosShow) {
		this.isPhotosShow = isPhotosShow;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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
	public String getStatuteAuthor() {
		return statuteAuthor;
	}
	public void setStatuteAuthor(String statuteAuthor) {
		this.statuteAuthor = statuteAuthor;
	}
	public String getStatuteSource() {
		return statuteSource;
	}
	public void setStatuteSource(String statuteSource) {
		this.statuteSource = statuteSource;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public int getGovUseFlag() {
		return govUseFlag;
	}
	public void setGovUseFlag(int govUseFlag) {
		this.govUseFlag = govUseFlag;
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSUB_CATEGORY() {
		return SUB_CATEGORY;
	}
	public void setSUB_CATEGORY(String sUB_CATEGORY) {
		SUB_CATEGORY = sUB_CATEGORY;
	}
	public String getSUB_CATEGORY_INFO() {
		return SUB_CATEGORY_INFO;
	}
	public void setSUB_CATEGORY_INFO(String sUB_CATEGORY_INFO) {
		SUB_CATEGORY_INFO = sUB_CATEGORY_INFO;
	}
	public String getPubPerson() {
		return pubPerson;
	}
	public void setPubPerson(String pubPerson) {
		this.pubPerson = pubPerson;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getPubTextNum() {
		return pubTextNum;
	}
	public void setPubTextNum(String pubTextNum) {
		this.pubTextNum = pubTextNum;
	}
	public String getPubDapt() {
		return pubDapt;
	}
	public void setPubDapt(String pubDapt) {
		this.pubDapt = pubDapt;
	}
	public String getInplementDate() {
		return inplementDate;
	}
	public void setInplementDate(String inplementDate) {
		this.inplementDate = inplementDate;
	}
	public String getTimeliness() {
		return timeliness;
	}
	public void setTimeliness(String timeliness) {
		this.timeliness = timeliness;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getMergeId() {
		return mergeId;
	}
	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}
	public String getOuterstatuteId() {
		return outerstatuteId;
	}
	public void setOuterstatuteId(String outerstatuteId) {
		this.outerstatuteId = outerstatuteId;
	}
	public String getCategoryOuter() {
		return categoryOuter;
	}
	public void setCategoryOuter(String categoryOuter) {
		this.categoryOuter = categoryOuter;
	}
	public String getOuterCategory() {
		return outerCategory;
	}
	public void setOuterCategory(String outerCategory) {
		this.outerCategory = outerCategory;
	}
	public String getStatuteSort() {
		return statuteSort;
	}
	public void setStatuteSort(String statuteSort) {
		this.statuteSort = statuteSort;
	}
	public String getFg() {
		return fg;
	}
	public void setFg(String fg) {
		this.fg = fg;
	}
	public int getIsOldData() {
		return isOldData;
	}
	public void setIsOldData(int isOldData) {
		this.isOldData = isOldData;
	}
	public String getIsCheckAgain() {
		return isCheckAgain;
	}
	public void setIsCheckAgain(String isCheckAgain) {
		this.isCheckAgain = isCheckAgain;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCompiler() {
		return compiler;
	}
	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}
	public String getStickState() {
		return stickState;
	}
	public void setStickState(String stickState) {
		this.stickState = stickState;
	}
	public Date getStickSort() {
		return stickSort;
	}
	public void setStickSort(Date stickSort) {
		this.stickSort = stickSort;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getChecked() {
		return checked;
	}
	public static String getUnchecked() {
		return unchecked;
	}
	public static String getDeleted() {
		return deleted;
	}
	public static String getUndeleted() {
		return undeleted;
	}
}
