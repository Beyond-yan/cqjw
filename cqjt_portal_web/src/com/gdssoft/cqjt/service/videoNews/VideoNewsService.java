package com.gdssoft.cqjt.service.videoNews;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.videoNews.VideoNews;

public interface VideoNewsService {
	


	List<VideoNews> getVideoNewsList(String videoTitle, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize);
	/**
	 * 根据日期范围查询交通视频信息
	 * @author H2603282
	 * @param entryDateS
	 * @param entryDateE
	 * @return
	 * 
	 */
	List<VideoNews> getVideoList(String videoTitle, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize);
	/**
	 * 根据日期范围查询视频信息
	 * @author H2602965
	 * @param entryDateS
	 * @param entryDateE
	 * @return
	 * 
	 */
	List<VideoNews> getVideoNewsList( Date entryDateS, Date entryDateE,int pageIndex,int pageSize);
	/**
	 * 默认查询视频审核信息
	 * @author H2602965
	 * @param entryDateS
	 * @param entryDateE
	 * @param flag
	 * @return
	 */
	List<VideoNews> getVerifyVideoNewsList(Date entryDateS, Date entryDateE,String[] flag,int pageIndex,int pageSize);
	void save(VideoNews videoNews);
	
	/**
	 * 更新资料
	 * @param fileDownload
	 */
	public void updateVideo(VideoNews videoNews);
	
	/**
	 * 根据videoId查询资料   
	 * @param fileId
	 * @return
	 */
	public VideoNews getVideoByVideoId(String videoId);
	/**
	 * 条件查询视频审核信息
	 * @author H2602965
	 * @param videoTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param flag
	 * @return
	 */
	List<VideoNews> getVerifyVideoNewsList(String videoTitle, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize);
	
	
	
}
