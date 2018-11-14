package com.gdssoft.cqjt.pojo;

import java.io.Serializable;
import java.util.Date;

import com.gdssoft.cqjt.pojo.BasePojo;
import com.google.gson.annotations.Expose;

public class TextNews  extends BasePojo implements Serializable {
	
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
	private String newsId;
	@Expose
	private String newsTitle;
	@Expose
	private String subNewsTitle;

	private String newsContent;
	@Expose
	private String newsTagsStr;
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
	private String newsAuthor;
	@Expose
	private String newsSource;
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
	private String sectionPosition;
	
	
	
	


	/*
	 * 被合并的id
	 */
	@Expose
	private String mergeId;

	/*
	 * 网站信息上外网后对应的外网id
	 */
	@Expose
	private String outerNewsId;

	@Expose
	private String categoryOuter;// 外网栏目id
	@Expose
	private String outerCategory;
	@Expose
	private String newsSort;
	private String adoptType;
	
	
	public String getSectionPosition() {
		return sectionPosition;
	}

	public void setSectionPosition(String sectionPosition) {
		this.sectionPosition = sectionPosition;
	}

	public String getAdoptType() {
		return adoptType;
	}

	public void setAdoptType(String adoptType) {
		this.adoptType = adoptType;
	}

	public String getNewsSort() {
		return newsSort;
	}

	public void setNewsSort(String newsSort) {
		this.newsSort = newsSort;
	}

	public String getOuterCategory() {
		return outerCategory;
	}

	public void setOuterCategory(String outerCategory) {
		this.outerCategory = outerCategory;
	}

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

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getSubNewsTitle() {
		return subNewsTitle;
	}

	public void setSubNewsTitle(String subNewsTitle) {
		this.subNewsTitle = subNewsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsTagsStr() {
		return newsTagsStr;
	}

	public void setNewsTagsStr(String newsTagsStr) {
		this.newsTagsStr = newsTagsStr;
	}

	/**
	 * 模拟属性newsTags
	 * 
	 * @return
	 */
	public String[] getNewsTags() {
		String tmp = this.newsTagsStr;
		if (tmp.startsWith(","))
			tmp = tmp.substring(1, tmp.length());
		if (tmp.endsWith(","))
			tmp = tmp.substring(0, tmp.length() - 1);
		if (tmp.indexOf(",") > 0)
			return tmp.split(",");
		else {
			String[] tmp2 = new String[1];
			tmp2[0] = tmp;
			return tmp2;
		}
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

	public String getNewsAuthor() {
		return newsAuthor;
	}

	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	public String getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
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

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getOuterNewsId() {
		return outerNewsId;
	}

	public void setOuterNewsId(String outerNewsId) {
		this.outerNewsId = outerNewsId;
	}

	public String getCategoryOuter() {
		return categoryOuter;
	}

	public void setCategoryOuter(String categoryOuter) {
		this.categoryOuter = categoryOuter;
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

	/**
	 * @return the isCheckAgain
	 */
	public String getIsCheckAgain() {
		return isCheckAgain;
	}

	/**
	 * @param isCheckAgain
	 *            the isCheckAgain to set
	 */
	public void setIsCheckAgain(String isCheckAgain) {
		this.isCheckAgain = isCheckAgain;
	}

	/**
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the compiler
	 */
	public String getCompiler() {
		return compiler;
	}

	/**
	 * @param compiler the compiler to set
	 */
	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}

	/**
	 * @return the stickState
	 */
	public String getStickState() {
		return stickState;
	}

	/**
	 * @param stickState the stickState to set
	 */
	public void setStickState(String stickState) {
		this.stickState = stickState;
	}

	/**
	 * @return the stickSort
	 */
	public Date getStickSort() {
		return stickSort;
	}

	/**
	 * @param stickSort the stickSort to set
	 */
	public void setStickSort(Date stickSort) {
		this.stickSort = stickSort;
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

}
