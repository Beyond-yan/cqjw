package com.gdssoft.cqjt.service.videoNews.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.videoNews.VideoNewsDao;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;
import com.gdssoft.cqjt.service.videoNews.VideoNewsService;

@Service("videoNewsServiceImpl")
public class VideoNewsServiceImpl implements VideoNewsService {
	@Autowired
	@Resource(name = "videoNewsDao")
	private VideoNewsDao videoNewsDao;

	@Override
	public List<VideoNews> getVideoNewsList(String videoTitle, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize) {
		return videoNewsDao.getVideoNewsList(videoTitle,entryUser,entryDateS,entryDateE,new String[]{},"0",pageIndex,pageSize);
	}

/**
 * @author H2602965
 * 默认查询视频 信息
 */
	@Override
	public List<VideoNews> getVideoNewsList(Date entryDateS, Date entryDateE,int pageIndex,int pageSize) {
		return videoNewsDao.getVideoNewsList("","",entryDateS,entryDateE,new String[]{},"0",pageIndex,pageSize);
		}
	/**
	 * @author H2603282
	 * 默认查询视频 信息
	 */
	@Override
	public List<VideoNews> getVideoList(String videoTitle, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize) {
		return videoNewsDao.getVideoNewsList(videoTitle,entryUser,entryDateS,entryDateE,new String[]{"2"},"0",pageIndex,pageSize);
	}

	@Override
	public void save(VideoNews videoNews) {
		videoNewsDao.insertVideoNews(videoNews);
	}

	@Override
	public void updateVideo(VideoNews videoNews) {
		videoNewsDao.updateVideo(videoNews);

	}
	@Override
	public VideoNews getVideoByVideoId(String videoId) {
	return videoNewsDao.getVideoByVideoId(videoId);
	}
	/**
	 * 默认查询视频审核信息
	 */
	@Override
	public List<VideoNews> getVerifyVideoNewsList(Date entryDateS, Date entryDateE,
			String[] flag,int pageIndex,int pageSize) {
		return  videoNewsDao.getVideoNewsList("","",entryDateS,entryDateE,new String[]{"1","2","3"},"0",pageIndex,pageSize);
	}
	/**
	 * @author H2602965
	 * 条件查询视频审核信息
	 */
	@Override
	public List<VideoNews> getVerifyVideoNewsList(String videoTitle,
			String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize) {
		return videoNewsDao.getVideoNewsList(videoTitle,entryUser,entryDateS,entryDateE,new String[]{"1","2","3"},"0",pageIndex,pageSize);
	}
	


}
