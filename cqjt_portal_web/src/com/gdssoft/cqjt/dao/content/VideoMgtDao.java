package com.gdssoft.cqjt.dao.content;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.content.VideoMgt;
@Service("videoMgtDao")
public class VideoMgtDao {
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
	public List<VideoMgt> getVideoMgtList(String videoTitle, String entryUser,Date entryDateS, Date entryDateE,
			String[] flag,String isDel,int pageIndex,int pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("videoTitle", videoTitle);
		map.put("entryUser", entryUser);
		map.put("entryDateS", entryDateS);
		map.put("entryDateE", entryDateE);
		map.put("flag", flag);
		map.put("isDel", isDel);
		
		return (List<VideoMgt>) this.sqlMapClientTemplate
				.queryForList("VideoMgt.getVideoMgtList", map, pageIndex, pageSize);
		
	}
	
	
	/**
	 * 更新资料   
	 * @param videoMgt
	 */
	public void updateVideo(VideoMgt videoMgt){
		//logger.debug("资料id："+videoMgt.getVideoId()+",资料是否删除："+videoMgt.getIsDel());
		this.sqlMapClientTemplate.update("VideoMgt.updateVideo", videoMgt);
	}

	
	/**
	 * 保存插入方法
	 * @author H2602965
	 * @param videoMgt
	 */
	public void insertVideoMgt(VideoMgt videoMgt) {
		this.sqlMapClientTemplate.insert("VideoMgt.insertVideoMgt", videoMgt);
	}
	
	/**
	 * 根据videoId查询详细信息   
	 * @author H2602965
	 * @param videoId
	 * @return
	 */
	public VideoMgt getVideoByVideoId(String videoId){
		return (VideoMgt) this.sqlMapClientTemplate.queryForObject("VideoMgt.getVideoByVideoId",videoId);
	}


}
