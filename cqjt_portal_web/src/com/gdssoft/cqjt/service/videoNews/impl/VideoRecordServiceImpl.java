package com.gdssoft.cqjt.service.videoNews.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.videoNews.VideoRecordDao;
import com.gdssoft.cqjt.pojo.videoNews.VideoRecord;
import com.gdssoft.cqjt.service.videoNews.VideoRecordService;

@Service("videoRecordServiceImpl")
public class VideoRecordServiceImpl implements VideoRecordService {
	
	@Autowired
	@Resource(name = "videoRecordDao")
	private VideoRecordDao videoRecordDao;
	
	@Override
	public VideoRecord getVideoByVideoId(String videoId) {
	   return videoRecordDao.getVideoByVideoId(videoId);
	}
	
	@Override
	public void update(VideoRecord videoRecord) {
		videoRecordDao.insertVideo(videoRecord);
	}
	/**
	 * @author H2603282
	 * 默认查询视频 信息
	 */
		@Override
		public List<VideoRecord> getVideoRecordList(String videoId) {
			return videoRecordDao.getVideoRecordList(videoId);
			}
}
