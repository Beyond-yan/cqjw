package com.gdssoft.cqjt.dao.cms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;

/**
 * 外网新闻Dao
 * @author F3219058
 */
@Repository("cmsNewsDao")
public class NewsDao {
	
	@Autowired
	@Resource(name = "cmsSqlMapClientTemplate")
	private SqlMapClientTemplate cmsSqlMapClientTemplate;

	/**
	 * 发布新闻上外网
	 * @param textNews 新闻实体
	 * @param programId 栏目编号
	 * @return
	 */
	public String addNews(TextNews textNews, String programId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("textNews", textNews);
		map.put("programId", programId);
		
		return (String) cmsSqlMapClientTemplate.insert("CMSNews.insertTextNews", map);
	}
	/**
	 * 一条新闻对应多个栏目，添加新闻栏目的参考。
	 * @param newsId 新闻编号
	 * @param programId 栏目编号
	 * @param isFirst 是否主栏目
	 * @param createUser 
	 */
	public void addProgramRef(String newsId, String programId, int isFirst, String createUser){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newsId", newsId);
		map.put("programId", programId);
		map.put("isFirst", isFirst);
		map.put("createUser", createUser);
		System.out.println("========================="+newsId);
		cmsSqlMapClientTemplate.insert("CMSNews.insertProgramRef", map);
	}	
	/**
	 * 上外网同步更新更新外网新闻
	 * @param textNews
	 * @param programId
	 */
	public void updateNews(TextNews textNews, String programId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("textNews", textNews);
		map.put("programId", programId);
		
		cmsSqlMapClientTemplate.update("CMSNews.updateTextNews", map);
	}
	/**
	 * 删除外网新闻
	 * @param textNews
	 */
	public void deleteNews(TextNews textNews){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("textNews", textNews);
		map.put("isDel", "1");
		cmsSqlMapClientTemplate.update("CMSNews.updateTextNews", map);
	}	
	/**
	 * 删除新闻与栏目关系
	 * @param textNews
	 */
	public void deleteProgramRef(TextNews textNews){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("textNews", textNews);
		map.put("isDel", "1");
		cmsSqlMapClientTemplate.update("CMSNews.updateProgramRef", map);
	}
	
	
	/**
	 * 推送到外网
	 */
	public void saveVideoToOuter(VideoNews videoNews) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("videoNews", videoNews);
		
		cmsSqlMapClientTemplate.insert("CMSNews.insertVidoeNewsToOuter", map);
		
	}
	
}
