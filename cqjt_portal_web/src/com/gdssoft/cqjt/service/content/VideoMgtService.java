package com.gdssoft.cqjt.service.content;

import java.util.Date;
import java.util.List;
import com.gdssoft.cqjt.pojo.content.VideoMgt;

public interface VideoMgtService {
	


	List<VideoMgt> getVideoMgtList(String videoTitle, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize);
	/**
	 * 根据日期范围查询视频信息
	 * @author H2602965
	 * @param entryDateS
	 * @param entryDateE
	 * @return
	 * 
	 */
	List<VideoMgt> getVideoMgtList( Date entryDateS, Date entryDateE,int pageIndex,int pageSize);
	/**
	 * 视频管理保存方法
	 * @author H2602965
	 * @param videoNews
	 */
	void save(VideoMgt videoNews);
	
	/**
	 * 更新资料
	 * @param fileDownload
	 */
	public void updateVideo(VideoMgt videoNews);
	
	/**
	 * 根据videoId查询资料   
	 * @param fileId
	 * @return
	 */
	public VideoMgt getVideoByVideoId(String videoId);
}
