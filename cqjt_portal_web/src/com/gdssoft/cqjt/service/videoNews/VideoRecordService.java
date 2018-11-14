package com.gdssoft.cqjt.service.videoNews;
import java.util.List;

import com.gdssoft.cqjt.pojo.videoNews.VideoRecord;

public interface VideoRecordService {
	
	/**
	 * 根据videoId查询资料   
	 * @param videoId
	 * @return
	 */
	public VideoRecord getVideoByVideoId(String videoId);
	/**
	 * 保存视频记录信息
	 * @param videoRecord
	 */
	public void update(VideoRecord videoRecord);
	/**
	 * 查询信息列表
	 * @author H2603282
	 * @param videoId
	 * @return
	 * 
	 */
	List<VideoRecord> getVideoRecordList(String videoId);
}
