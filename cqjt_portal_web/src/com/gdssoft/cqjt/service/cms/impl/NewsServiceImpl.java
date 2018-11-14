package com.gdssoft.cqjt.service.cms.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.cms.NewsDao;
import com.gdssoft.cqjt.pojo.CategoryRelation;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;
import com.gdssoft.cqjt.service.ColumnService;
import com.gdssoft.cqjt.service.cms.NewsService;

/**
 * @author Jimxu
 */
@Service("cmsNewsService")
public class NewsServiceImpl implements NewsService {

	@Resource(name="cmsNewsDao")
	private NewsDao cmsNewsDao;
	
	/*@Resource(name="columnService")
	private ColumnService columnService;*/
	
	protected transient final Log logger = LogFactory.getLog(getClass());

	@Override
	public String addNews(TextNews textNews) {

		String newsId = "";
		
		int isFirst = 1;
		String programId = textNews.getCategoryOuter();
		logger.debug(programId);
		newsId = cmsNewsDao.addNews(textNews, programId);
		
		logger.debug("newsId:"+newsId);
		//如果文章发布成功，则添加文章和栏目的对应关系
		if (StringUtils.isNotBlank(newsId)) {
			cmsNewsDao.addProgramRef(newsId, programId, isFirst, textNews.getCreateBy());
		}
		
		return newsId;
	}

	@Override
	public void updateNews(TextNews textNews) {
		int isFirst = 1;
		String programId = textNews.getCategoryOuter();;
		logger.debug(programId);
		
		cmsNewsDao.updateNews(textNews, programId);
		cmsNewsDao.deleteProgramRef(textNews);//1.将原来新闻对应栏目关系数据删掉，2.根据新的栏目重新添加对应关系
		
		logger.debug("newsId:"+textNews.getOuterNewsId());
		
		if (StringUtils.isNotBlank(textNews.getOuterNewsId())) {
			cmsNewsDao.addProgramRef(textNews.getOuterNewsId(), programId, isFirst, textNews.getCreateBy());
		}
	}

	@Override
	public void delete(TextNews textNews) {
		cmsNewsDao.deleteNews(textNews);
		cmsNewsDao.deleteProgramRef(textNews);
	}
	
	/**
	 * 推送到外网
	 */
	@Override
	public void saveVideoToOuter(VideoNews videoNews) {
		cmsNewsDao.saveVideoToOuter(videoNews);		
	}
	
}
