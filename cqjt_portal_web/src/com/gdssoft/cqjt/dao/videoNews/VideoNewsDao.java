package com.gdssoft.cqjt.dao.videoNews;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;

@Service("videoNewsDao")
public class VideoNewsDao{
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 视频查询
	 * @author H2602965
	 * @param videoTitle 视频标题关键字
	 * @param entryUser  投稿人关键字
	 * @param entryDateS 投稿起始日期
	 * @param entryDateE 投稿结束日志
	 * @param flag 状态标识(0 草稿,1 已投稿,2 已审核)
	 * @param isDel 删除标识(0 未删除,1 已删除 )
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VideoNews> getVideoNewsList(String videoTitle, String entryUser,Date entryDateS,
			Date entryDateE,String[] flag,String isDel,int pageIndex,int pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("videoTitle", videoTitle);
		map.put("entryUser", entryUser);
		map.put("entryDateS", entryDateS);
		map.put("entryDateE", entryDateE);
		map.put("flag", flag);
		map.put("isDel", isDel);
		
		return (List<VideoNews>) this.sqlMapClientTemplate
				.queryForList("VideoNews.getVideoNewsList", map, pageIndex, pageSize);
	}
	
	
	/**
	 * 更新资料   
	 * @param videoNews
	 */
	public void updateVideo(VideoNews videoNews){
		this.sqlMapClientTemplate.update("VideoNews.updateVideo", videoNews);
	}

	

	public void insertVideoNews(VideoNews videoNews) {
		this.sqlMapClientTemplate.insert("VideoNews.insertVideoNews", videoNews);
	}
	
	/**
	 * 根据videoId查询资料   
	 * @param fileId
	 * @return
	 */
	public VideoNews getVideoByVideoId(String videoId){
		return (VideoNews) this.sqlMapClientTemplate.queryForObject("VideoNews.getVideoByVideoId",videoId);
	}
	



}
