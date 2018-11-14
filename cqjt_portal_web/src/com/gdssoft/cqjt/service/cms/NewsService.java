package com.gdssoft.cqjt.service.cms;

import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;

/**
 * @author Jimxu
 */
public interface NewsService {

	public String addNews(TextNews textNews);
	
	/**
	 * 更新
	 * @author gyf 20140815
	 * @param textNews
	 * @return
	 */
	public void updateNews(TextNews textNews);
	
	/**
	 * 删除
	 * @author gyf 20140815
	 * @param textNews
	 * @return
	 */
	public void delete(TextNews textNews);

	/**
	 * 将审核过的视频信息推送到外网，保存到外网数据库
	 * @author zhp 
	 * @date 20141126
	 */
	public void saveVideoToOuter(VideoNews videoNews);
	
	
}
