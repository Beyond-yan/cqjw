package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.TextNews;

@Repository("textNewsDao")
public class TextNewsDao {
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * 提供给编辑页面初始化
	 * 
	 * @param textNews
	 * @return
	 */
	public TextNews getTextNewsDetail(String newsId) {
		return (TextNews) this.sqlMapClientTemplate.queryForObject(
				"TextNews.getTextNewsDetail", newsId);
	}
	
	/**
	 * 根据置顶状态查询数据
	 * 
	 * @param textNews
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextNews> getTextNewsByStickState(String stickState) {
		return (List<TextNews>) this.sqlMapClientTemplate.queryForList(
				"TextNews.getTextNewsByStickState", stickState);
	}

	public void updateTextNewsBynewsId(String newsId){
		this.sqlMapClientTemplate.update("TextNews.updateTextNewsByNewsId", newsId);
	}

	/**
	 * 文字新闻管理页面新闻列表查询
	 * 
	 * @param newsTitle
	 *            标题关键字
	 * @param entryUser
	 *            投稿人关键字
	 * @param entryDateS
	 *            起始时间
	 * @param entryDateE
	 *            结束时间
	 * @param newsTag
	 *            新闻标签
	 * @param flags
	 *            状态标识（0 草稿，1已投稿，2已分拣，3已校编 ）
	 * @param categorys
	 *            分类名称（可以按TEXT_CATEGORY表中的值进行查询）
	 * @param isPhotoShow
	 *            是否有主题图片
	 * @param isDel
	 *            删除标识
	 * @param startRow
	 *            开始行号
	 * @param endRow
	 *            结束行号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextNews> getTextNewsList(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, String newsTag, String[] flags,
			String[] categorys, String isPhotoShow, String isDel, int startRow,
			int endRow) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("newsTitle", newsTitle);
		map.put("entryUser", entryUser);

		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);

		map.put("newsTag", newsTag);
		map.put("flags", flags);
		map.put("categorys", categorys);

		if (!StringUtils.isBlank(isPhotoShow))
			map.put("isPhotosShow", isPhotoShow);

		map.put("isDel", isDel);

		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);

		return (List<TextNews>) this.sqlMapClientTemplate.queryForList(
				"TextNews.getTextNewsList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<TextNews> getTextNewsListForExport(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, String newsTag, String[] flags,int[] govUseFlag,
			String[] categorys, String isPhotoShow, String isDel, int startRow,
			int endRow) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("newsTitle", newsTitle);
		map.put("entryUser", entryUser);
		
		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);
		
		map.put("newsTag", newsTag);
		map.put("flags", flags);
		map.put("categorys", categorys);
		
		if (!StringUtils.isBlank(isPhotoShow))
			map.put("isPhotosShow", isPhotoShow);

		if(govUseFlag.length > 0){
			map.put("govUseFlag",govUseFlag);
		}
		
		map.put("isDel", isDel);
		
		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);
		
		return (List<TextNews>) this.sqlMapClientTemplate.queryForList(
				"TextNews.getTextNewsList", map);
	}
	
	//根据栏目ID查询textNews
	@SuppressWarnings("unchecked")
	public List<TextNews> getTextNewsListByCatID(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, String newsTag, String[] flags,
			String[] categorys, String isPhotoShow, String isDel, int startRow,String deptName, String category,
			int endRow) {
		Map<String, Object> map = new HashMap<String, Object>();

 
		if (null != deptName)
			map.put("deptName", deptName);
		if (null != category)
			map.put("category", category);

		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);

		map.put("newsTag", newsTag);
		map.put("flags", flags);
		map.put("categorys", categorys);
 

		if (!StringUtils.isBlank(isPhotoShow))
			map.put("isPhotosShow", isPhotoShow);

		map.put("isDel", isDel);

		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);

		return (List<TextNews>) this.sqlMapClientTemplate.queryForList(
				"TextNews.getTextNewsList", map);

	}

	// 修改分页功能测试 20140723
	@SuppressWarnings("unchecked")
	public List<TextNews> getTextNewsList(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, String newsTag, String[] flags,int[] govUseFlag,
			String[] categorys, String isPhotoShow, String isDel,
			String isCheckAgain, int startRow, int endRow, int pageIndex,
			int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("newsTitle", newsTitle);
		map.put("entryUser", entryUser);
		map.put("newsTag",newsTag);
		
		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);

		map.put("newsTag", newsTag);
		
		if(govUseFlag.length > 0){
			map.put("flags", flags);
			map.put("govUseFlag",govUseFlag);
		}
		if(flags.length > 0){
			map.put("flags", flags);
		}
		
		map.put("categorys", categorys);

		if (!StringUtils.isBlank(isPhotoShow))
			map.put("isPhotosShow", isPhotoShow);

		map.put("isDel", isDel);
		// 新增isCheckAgain栏位，用于角色权限管控；
		map.put("isCheckAgain", isCheckAgain);
		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);

		return (List<TextNews>) sqlMapClientTemplate.queryForList(
				"TextNews.getTextNewsList", map, pageIndex, pageSize);

	}

	/**
	 * 首页交通政务，交通政务详情 置顶显示
	 * 
	 * @author H2602965
	 * @param newsTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param newsTag
	 * @param flags
	 * @param categorys
	 * @param isPhotoShow
	 * @param isDel
	 * @param isCheckAgain
	 * @param startRow
	 * @param endRow
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextNews> getTextNewsList(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, String newsTag, String[] flags,
			String[] categorys, String isPhotoShow, String isDel,
			String isCheckAgain, String isStickState, int startRow, int endRow,
			int pageIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("newsTitle", newsTitle);
		map.put("entryUser", entryUser);

		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);

		map.put("newsTag", newsTag);
		map.put("flags", flags);
		map.put("categorys", categorys);

		if (!StringUtils.isBlank(isPhotoShow))
			map.put("isPhotosShow", isPhotoShow);
		//排序标识位
		map.put("stickState", isStickState);
		map.put("isDel", isDel);
		// 新增isCheckAgain栏位，用于角色权限管控；
		map.put("isCheckAgain", isCheckAgain);
		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);

		return (List<TextNews>) sqlMapClientTemplate.queryForList(
				"TextNews.getTextNewsList", map, pageIndex, pageSize);

	}
	/////
	@SuppressWarnings("unchecked")
	public List<TextNews> getTextNewsList(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, String newsTag, String[] flags,
			String[] categorys, String isPhotoShow, String isDel,
			String isCheckAgain, String isStickState, int startRow, int endRow,
			int pageIndex, int pageSize,String deptName,String categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (null != deptName)
			map.put("deptName", deptName);
		if (null != categoryId)
			map.put("category", categoryId);
		
		map.put("newsTitle", newsTitle);
		map.put("entryUser", entryUser);
		
		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);

		map.put("newsTag", newsTag);
		map.put("flags", flags);
		map.put("categorys", categorys);

		if (!StringUtils.isBlank(isPhotoShow))
			map.put("isPhotosShow", isPhotoShow);
		//排序标识位
		map.put("stickState", isStickState);
		map.put("isDel", isDel);
		// 新增isCheckAgain栏位，用于角色权限管控；
		map.put("isCheckAgain", isCheckAgain);
		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);

		return (List<TextNews>) sqlMapClientTemplate.queryForList(
				"TextNews.getTextNewsList", map, pageIndex, pageSize);

	}

	/**
	 * 新增文字新闻
	 * 
	 * @param textNews
	 */
	public void insertTextNews(TextNews textNews) {
		this.sqlMapClientTemplate.insert("TextNews.insertTextNews", textNews);
	}

	/**
	 * 编辑修改文字新闻
	 * 
	 * @param textNews
	 */
	public void updateTextNews(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateTextNews", textNews);
	}

	/**
	 * 删除文字新闻 （注：删除源文件；逻辑删除）
	 * 
	 * @param textNews
	 */
	public void deleteTextNews(String newsId) {
		this.sqlMapClientTemplate.update("TextNews.deleteTextNews", newsId);
	}

	@SuppressWarnings("unchecked")
	public List<TextNews> getGovRecycleList(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, int pageIndex, int pageSize) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newsTitle", newsTitle);
		map.put("entryUser", entryUser);
		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);

		return (List<TextNews>) this.sqlMapClientTemplate.queryForList(
				"TextNews.getGovRecycleList", map, pageIndex, pageSize);
	}

	/**
	 * 彻底删除
	 * 
	 * @param textNews
	 */
	public void deleteTextNews(TextNews textNews) {
		this.sqlMapClientTemplate.delete("TextNews.deleteTextNews", textNews.getNewsId());
	}

	// 修改分交通信息查询页功能测试 20140918
	@SuppressWarnings("unchecked")
	public List<TextNews> getTNewsList(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, String newsTag, String[] flags,
			String[] categorys, String isPhotoShow, String isDel, int startRow,
			int endRow, int pageIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("newsTitle", newsTitle);
		map.put("entryUser", entryUser);

		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);

		map.put("newsTag", newsTag);
		map.put("flags", flags);
		map.put("categorys", categorys);

		if (!StringUtils.isBlank(isPhotoShow))
			map.put("isPhotosShow", isPhotoShow);

		map.put("isDel", isDel);

		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);

		return (List<TextNews>) sqlMapClientTemplate.queryForList(
				"TextNews.getTNewsList", map, pageIndex, pageSize);

	}

	// 信息动态
	@SuppressWarnings("unchecked")
	public List<TextNews> getDynamicTextNewsList(String newsTitle,
			String entryUser, Date entryDateS, Date entryDateE, String newsTag,
			String[] flags, String[] categorys, String[] dept_category,
			String isPhotoShow, String isDel, int startRow, int endRow,
			int pageIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("newsTitle", newsTitle);
		map.put("entryUser", entryUser);

		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);

		map.put("newsTag", newsTag);
		map.put("flags", flags);
		map.put("categorys", categorys);
		map.put("dept_categorys", dept_category);

		if (!StringUtils.isBlank(isPhotoShow))
			map.put("isPhotosShow", isPhotoShow);

		map.put("isDel", isDel);

		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);

		return (List<TextNews>) sqlMapClientTemplate.queryForList(
				"TextNews.getTextNewsList", map, pageIndex, pageSize);

	}
	
	public void updateNewsSort(Map<String, Object> param) {
		this.sqlMapClientTemplate.update("TextNews.updateNewsSort", param);
	}
	
	/**
	 * 更新新闻类容
	 * @param textNews
	 */
	public void updateTextNewsContetn(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateTextNewsContetn", textNews);
	}
	
	/**
	 * 首页头版头条与头版次条
	 * @param textNews
	 */
	public TextNews getMainOrSubMainTextNews(Map<String,String> map) {
		return (TextNews) this.sqlMapClientTemplate.queryForObject("TextNews.getMainOrSubMainTextNews", map);
	}
	
}
