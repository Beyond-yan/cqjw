package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.pojo.TextNews;

public interface TextNewsService {

	/**
	 * 保存信息
	 * @param textNews
	 * @author 许健 
	 */
	void save(TextNews textNews);
	/**
	 * 更新信息
	 * @param textNews
	 * @author 许健 
	 */
	void update(TextNews textNews);
	
	/**
	 * 删除信息（逻辑删除）
	 * @param newsId
	 * @author 许健 
	 */
	void delete(String newsId);
	
	/**
	 * 交通报交通杂志   信息查询整合H2602965
	 * List<TextNews> getTrafficList(String newsTitle, String entryUser, Date entryDateS, Date entryDateE, String newsTag);
	 * 政务信息采用后更改gov_use_flag字段
	 * @param newsId
	 * @author 许健 
	 */
	public void updateGovUseFlag(String newsId);
	/*新增
	 * 
	 */
	
	// by llj
	public void updateGovUseFlagReport(String newsId);
	// by llj
	public void updateGovUseFlagNot(String newsId);
	/*新增
	 * 
	 */
	public void updateGovUseFlag(String newsId,String isSelected,String dept);
	/**
	 * 回收站复原
	 * @author H2602965
	 * @param newsId
	 * @author 许健 
	 */
	public void resumeTextNewsFlag(String newsId);
	/*
	 * 重新修改政务信息的采编状态
	 * 
	 * */
	
	public void resumeGovFlag(String newsId);
	
	/**
	 * 获取单个信息的详情
	 * @param newsId
	 * @return
	 * @author 许健 
	 */
	TextNews getTextNewsDetail(String newsId);
	
	
	/**
	 * 通过置顶信息查询数据
	 * @param newsId
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getTextNewsByStickState(String stickState);
	
	/**
	 * 通过newsId修改置顶数据
	 * @param newsId
	 * @return
	 * @author 许健 
	 */
	void updateTextNewsBynewsId(String newsId);
	
	/**
	 * 取图片信息列表
	 * @param topNum 只取前几条记录
	 * @return
	 */
	List<TextNews> getPhotoNewsList(int topNum);
    /**
     * 取信息列表（根据分类来筛选）
     * @param categorys 分类名称（可以按TEXT_CATEGORY表中的值进行查询）
     * @param topNum 只取前几条记录
     * @return
	 * @author 许健 
     */
	List<TextNews> getTextNewsList(String[] categorys, int topNum);
	
    /**
     * 根据栏目ID取信息列表
     * @param categorys 分类名称（可以按TEXT_CATEGORY表中的值进行查询）
     * @param topNum 只取前几条记录
     * @return
	 * @author 许健 
     */
	List<TextNews> getTextNewsListByCatID(String deptName, String category, int topNum);
	
	/**
	 * 取信息列表（根据日期来筛选）
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @return
	 * @author 许健 
	 */
	//List<TextNews> getTextNewsList(Date startDate, Date endDate);
	List<TextNews> getTextNewsList(Date startDate, Date endDate,int pageIndex, int pageSize, String tags);
	/**
	 * 根据日期和状态筛选信息列表
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getTextNewsList(Date startDate, Date endDate, String[] flags);
	/**
	 * 根据标题、投稿人、状态和日期筛选信息列表
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param tags 信息类型
	 * @param 
	 * @return
	 * @author 许健  111
	 */
	List<TextNews> getTextNewsList(String newsTitle, String entryUser, Date startDate, Date endDate,int pageIndex, String tags,
			String[] flags,int[] govUseFlag, int pageSize);
	/**
	 * 根据标题、投稿人和日期筛选信息列表
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param tags 信息类型
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getTextNewsList(String newsTitle, String entryUser, Date startDate, Date endDate,int pageIndex, String tags,
			int pageSize);
	/**
	 * 根据标题、投稿人、日期和状态筛选信息列表
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getTextNewsList(String newsTitle, String entryUser, Date startDate, Date endDate, String[] flags);

	
	
	
	
	
	/**
	 * 根据日期筛选网站信息列表
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getSiteNewsList(Date startDate, Date endDate);
	/**
	 * 根据日期和状态筛选网站信息列表
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getSiteNewsList(Date startDate, Date endDate, String[] flags,String isCheckAgain,int pageIndex, int pageSize);
	/**
	 * 根据标题、投稿人和日期筛选网站信息列表
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getSiteNewsList(String newsTitle, String entryUser, Date startDate, Date endDate,int pageIndex, int pageSize);
	/**
	 * 根据标题、投稿人、日期和状态筛选网站信息列表
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getSiteNewsList(String newsTitle, String entryUser, Date startDate, Date endDate, String[] flags,String isCheckAgain,int pageIndex, int pageSize);
	
	public List<TextNews> getSiteNewsList(String newsTitle, String entryUser,
			Date startDate, Date endDate, String[] flags,String[] categorys, String isCheckAgain,
			int pageIndex, int pageSize);

	/**
	 * 根据日期、状态和新闻分类来筛选网站信息列表
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @param categorys 分类名称（可以按TEXT_CATEGORY表中的值进行查询）
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getSiteNewsList(Date startDate, Date endDate, String[] flags, String[] categorys,int pageIndex, int pageSize);

	
	/**
	 * 根据日期、状态和新闻分类来筛选网站信息列表
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @param categorys 分类名称（可以按TEXT_CATEGORY表中的值进行查询）
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getSiteNewsList(Date startDate, Date endDate, String[] flags, String[] categorys,int pageIndex, int pageSize, String deptName, String categoryId);
	
	/**
	 * 根据标题、投稿人和日期筛选网站信息列表（回收站）
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getSiteNewsRecycleList(String newsTitle, String entryUser, Date startDate, Date endDate,int pageIndex, int pageSize);
	
	
	
	
	/**
	 * 根据日期筛选政务信息列表
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getGovNewsList(Date startDate, Date endDate);
	/**
	 * 根据日期和状态筛选政务信息列表
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getGovNewsList(Date startDate, Date endDate, String[] flags,int pageIndex, int pageSize);
	/**
	 * 根据标题、投稿人、日期筛选政务信息列表
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getGovNewsList(String newsTitle, String entryUser, Date startDate, Date endDate);
	/**
	 * 根据标题、投稿人、日期和状态筛选政务信息列表
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getGovNewsList(String newsTitle, String entryUser, Date startDate, Date endDate, String[] flags,int pageIndex, int pageSize);

	
	
	/**
	 * 根据日期和标签筛选信息列表
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param newsTag 新闻标签
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getTextNewsListByTag(Date startDate, Date endDate, String newsTag);
	/**
	 * 根据标题、投稿人、日期和标签筛选信息列表
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param newsTag 新闻标签
	 * @return
	 * @author 许健 
	 */
	List<TextNews> getTextNewsListByTag(String newsTitle, String entryUser, Date startDate, Date endDate, String newsTag);
	/**
	 * 根据标题、投稿人、日期和标签筛选信息列表,用于查询交通信息
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param newsTag 新闻标签
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @return
	 * @author zhpeng 20140621
	 */
	List<TextNews> getTextNewsListByTag(String newsTitle, String entryUser, Date startDate, Date endDate, String newsTag,String[] flags,int pageIndex, int pageSize);
	/**
	 * 根据日期和标签筛选信息列表，用于默认查询交通信息
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param newsTag 新闻标签
	 * @param flags 状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @return
	 * @author zhpeng 20140621
	 */
	List<TextNews> getTextNewsListByTag(Date startDate, Date endDate, String newsTag, String[] flags,int pageIndex, int pageSize);

	/**
	 * 根据标题、投稿人和日期筛选政务信息列表
	 * @param newsTitle 标题关键字
	 * @param entryUser 投稿人关键字
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @return
	 * @author 0630
	 */
	List<TextNews> getGovNewsRecycleList(String newsTitle, String createUser, Date startDate, Date endDate,int pageIndex, int pageSize);
	/**
	 * 批量删除信息（逻辑删除）
	 * @param newsId
	 * @author gyf
	 */
	void deleteAll(String newsIds);
	/**
	 * 批量分拣
	 * @author H2602965
	 * @param newsIds
	 */
	void sortSiteAll(String newsIds);
	/**
	 *彻底删除网站信息
	 * @author H2602965
	 * @param newsIds
	 */
	void deleteSiteAll(String newsIds);
	/**
	 * 彻底删除政务信息
	 * @author H2602965
	 * @param newsIds
	 */
	void deleteGovAll(String newsIds);
	/*
	 * 交通信息筛选
	 */
	List<TextNews> getTSiteNewsList(Date startDate, Date endDate, String[] flags,int pageIndex, int pageSize);

	/**
	 * 批量归档
	 * @author H2602965
	 * @param newsIds
	 */
	void archiveSiteAll(String newsIds);


	/**
	 * 首页行业动态 、处室动态、区县动态
	 * @author zhp  20141124
	 */
	
	List<TextNews> getDynamicSiteNewsList(Date startDate, Date endDate, String[] flags, String[] categorys,String[] dept_category ,int pageIndex, int pageSize);
	
	void updateNewsSort(String newsSort,String newsId);
	
	/**
	 * 导出excel 查询
	 * @param map
	 * @return
	 */
	List<TextNews> getTextNewsListForExport(String newsTitle, String entryUser, Date startDate, Date endDate, String tags,
			String[] flags,int[] govUseFlag);
	
	/**
	 * 导出excel
	 * @param textNewsList
	 * @param response
	 */
	void exportTextNewsList(List<TextNews> textNewsList, List<CheckStandard> checkStandards, String start, String end, HttpServletResponse response, String tags);
	
	/**
	 * 更新新闻类容
	 * @param textNews
	 */
	void updateTextNewsContetn(TextNews textNews);
	
	/**
	 * 首页头版头条与头版次条
	 * @param map
	 */
	public TextNews getMainOrSubMainTextNews(Map<String,String> map);
	
}
