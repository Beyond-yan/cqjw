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
import com.gdssoft.cqjt.dao.TextPersonDao;
import com.gdssoft.cqjt.pojo.TextPerson;
import com.gdssoft.cqjt.service.TextGovInfoService;
import com.gdssoft.cqjt.service.TextPersonService;

@Service("textPersonServiceImpl")
public class TextPersonServiceImpl implements TextPersonService {
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Autowired
	@Resource(name = "textPersonDao")
	private TextPersonDao textPersonDao;
	
	@Autowired
	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;
	
	@Autowired
	@Resource(name = "textGovInfoDao")
	private TextGovInfoDao textGovInfoDao;
	
	
	@Override
	public void save(TextPerson textPerson) {
		textPerson.setIsDel("0");
		textPersonDao.insertTextPerson(textPerson);
	}

	@Override
	public void update(TextPerson textPerson) {
		textPerson.setIsDel("0");
		textPersonDao.updateTextPerson(textPerson);

	}

	@Override
	public void delete(String PersonId) {
		textPersonDao.deleteTextPerson(PersonId);
	}
	
	@Override
	public List<TextPerson> getPersonlistView(Date startDate, Date endDate,
			int pageIndex, int pageSize) {
		return textPersonDao.getTextPersonList("", "", startDate, endDate, "",
				new String[] {}, new int[]{}, new String[] {}, "", "0", "", 0, 0, pageIndex,
				pageSize,null);
	}
	@Override
	public List<TextPerson> getPersonlistView(String newsTitle, String entryUser,
			Date startDate, Date endDate, int pageIndex, int pageSize) {
		return textPersonDao.getTextPersonList(newsTitle, entryUser, startDate,
				endDate, "", new String[] {},new int[]{}, new String[] {}, "", "0", "", 0,
				0, pageIndex, pageSize,null);
	}

	@Override
	public List<TextPerson> getPersonlistView(String newsTitle, String entryUser,
			Date startDate, Date endDate, int pageIndex, String categoryId,
			String[] flags, int[] govUseFlag, int pageSize) {
		return textPersonDao.getTextPersonList(newsTitle, entryUser, startDate,
				endDate, "", flags, govUseFlag, new String[] {}, "", "0", "", 0,
				0, pageIndex, pageSize,null);
	}
	
	@Override
	public List<TextPerson> queryPersonlistView(String newsTitle, String entryUser,
			Date startDate, Date endDate, int pageIndex, String categoryId,
			String[] flags, int[] govUseFlag, int pageSize,String SUB_CATEGORY) {
		return textPersonDao.getTextPersonList(newsTitle, entryUser, startDate,
				endDate, "", flags, govUseFlag, new String[] {}, "", "0", "", 0,
				0, pageIndex, pageSize,SUB_CATEGORY);
	}
	
	/**
	 * 提供给编辑页面初始化
	 * 
	 * @param textNews
	 * @return
	 */
	@Override
	public TextPerson getTextPersonDetail(String PersonId) {
		TextPerson textPerson = textPersonDao.getTextPersonDetail(PersonId);
		if(null != textPerson){
			String content = textPerson.getPersonContent();
			content = StringEscapeUtils.unescapeHtml(content);
			textPerson.setPersonContent(content);
		}
		return textPerson;
	}
	
}
