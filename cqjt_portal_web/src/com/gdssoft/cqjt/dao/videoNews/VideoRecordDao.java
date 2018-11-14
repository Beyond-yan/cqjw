package com.gdssoft.cqjt.dao.videoNews;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.videoNews.VideoRecord;

@Service("videoRecordDao")
public class VideoRecordDao {
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * 插入视频修改记录 
	 * @param videoRecord
	 */
	public void insertVideo(VideoRecord videoRecord) {
		this.sqlMapClientTemplate.insert("VideoRecord.insertVideo", videoRecord);
	}
	
	/**
	 * 根据videoId查询资料   
	 * @param videoId
	 * @return
	 */
	public VideoRecord getVideoByVideoId(String videoId){
		return (VideoRecord) this.sqlMapClientTemplate.queryForObject("VideoRecord.getVideoByVideoId",videoId);
	}

	@SuppressWarnings("unchecked")
	public List<VideoRecord> getVideoRecordList(String videoId) {
		
		return (List<VideoRecord>) this.sqlMapClientTemplate.queryForList("VideoRecord.getVideoByVideoId", videoId);

	}
}
