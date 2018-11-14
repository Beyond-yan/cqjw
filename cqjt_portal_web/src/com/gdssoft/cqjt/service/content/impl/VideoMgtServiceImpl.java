package com.gdssoft.cqjt.service.content.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.content.VideoMgtDao;
import com.gdssoft.cqjt.pojo.content.VideoMgt;
import com.gdssoft.cqjt.service.content.VideoMgtService;

@Service("videoMgtServiceImpl")
public class VideoMgtServiceImpl implements VideoMgtService {
	@Autowired
	@Resource(name = "videoMgtDao")
	private VideoMgtDao videoMgtDao;

	@Override
	public List<VideoMgt> getVideoMgtList(String videoTitle, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize) {
		return videoMgtDao.getVideoMgtList(videoTitle,entryUser,entryDateS,entryDateE,new String[]{},"0",pageIndex,pageSize);
	}

/**
 * @author H2602965
 * 默认查询视频 信息
 */
	@Override
	public List<VideoMgt> getVideoMgtList(Date entryDateS, Date entryDateE,int pageIndex,int pageSize) {
		return videoMgtDao.getVideoMgtList("","",entryDateS,entryDateE,new String[]{},"0",pageIndex,pageSize);
		}


	@Override
	public void save(VideoMgt videoNews) {
		videoMgtDao.insertVideoMgt(videoNews);
	}

	@Override
	public void updateVideo(VideoMgt videoNews) {
		videoMgtDao.updateVideo(videoNews);

	}
	@Override
	public VideoMgt getVideoByVideoId(String videoId) {
	return videoMgtDao.getVideoByVideoId(videoId);
	}
}
