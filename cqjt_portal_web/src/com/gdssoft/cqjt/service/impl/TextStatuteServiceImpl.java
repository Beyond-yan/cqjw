package com.gdssoft.cqjt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.TextGovInfoDao;
import com.gdssoft.cqjt.dao.TextStatuteDao;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.TextStatute;
import com.gdssoft.cqjt.service.TextGovInfoService;
import com.gdssoft.cqjt.service.TextStatuteService;

@Service("textStatuteServiceImpl")
public class TextStatuteServiceImpl implements TextStatuteService {
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Autowired
	@Resource(name = "textStatuteDao")
	private TextStatuteDao textStatuteDao;
	
	@Autowired
	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;
	
	@Autowired
	@Resource(name = "textGovInfoDao")
	private TextGovInfoDao textGovInfoDao;
	
	
	@Override
	public void save(TextStatute textStatute) {
		textStatute.setIsDel("0");
		textStatuteDao.insertTextStatute(textStatute);
	}

	@Override
	public void update(TextStatute textStatute) {
		textStatute.setIsDel("0");
		textStatuteDao.updateTextStatute(textStatute);

	}

	@Override
	public void delete(String statuteId) {
		textStatuteDao.deleteTextStatute(statuteId);
	}
	
	
	

	@Override
	public List<TextStatute> getStatutelistView(Date startDate, Date endDate,
			int pageIndex, int pageSize) {
		return textStatuteDao.getTextStatuteList("", "", startDate, endDate, "",
				new String[] {}, new int[]{}, new String[] {}, "", "0", "", 0, 0, pageIndex,
				pageSize,null,null);
	}
	@Override
	public List<TextStatute> getStatutelistView(String newsTitle, String entryUser,
			Date startDate, Date endDate, int pageIndex, int pageSize) {
		return textStatuteDao.getTextStatuteList(newsTitle, entryUser, startDate,
				endDate, "", new String[] {},new int[]{}, new String[] {}, "", "0", "", 0,
				0, pageIndex, pageSize,null,null);
	}

	@Override
	public List<TextStatute> getStatutelistView(String newsTitle, String entryUser,
			Date startDate, Date endDate, int pageIndex, String categoryId,
			String[] flags, int[] govUseFlag, int pageSize) {
		return textStatuteDao.getTextStatuteList(newsTitle, entryUser, startDate,
				endDate, "", flags, govUseFlag, new String[] {}, "", "0", "", 0,
				0, pageIndex, pageSize,null,null);
	}
	
	@Override
	public List<TextStatute> queryStatutelistView(String newsTitle, String entryUser,
			Date startDate, Date endDate, int pageIndex, String categoryId,
			String[] flags, int[] govUseFlag, int pageSize,String SUB_CATEGORY, String SUB_CATEGORY_INFO) {
		return textStatuteDao.getTextStatuteList(newsTitle, entryUser, startDate,
				endDate, "", flags, govUseFlag, new String[] {}, "", "0", "", 0,
				0, pageIndex, pageSize,SUB_CATEGORY,SUB_CATEGORY_INFO);
	}
	
	/**
	 * 提供给编辑页面初始化
	 * 
	 * @param textNews
	 * @return
	 */
	@Override
	public TextStatute getTextStatuteDetail(String statuteId) {
		TextStatute textStatute = textStatuteDao.getTextStatuteDetail(statuteId);
		if(null != textStatute){
			if (textStatute.getIsOldData() == 1) {
				String content = textStatute.getStatuteContent();
				content = StringEscapeUtils.unescapeHtml(content);
				textStatute.setStatuteContent(content);
			}
		}
		return textStatute;
	}
	
}
