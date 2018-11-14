package com.gdssoft.cqjt.service.content.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.content.TextDataDao;
import com.gdssoft.cqjt.pojo.content.TextData;
import com.gdssoft.cqjt.service.content.TextDataService;
/**
 * 文字类信息管理
 * @author  gyf 20140709
 * @return
 */
@Service("textDataService")
public class TextDataServiceImpl implements TextDataService {
	@Autowired
	@Resource(name = "textDataDao")
	private TextDataDao textDataDao;
	
	@Override
	public List<TextData> getAllTextData(String title, String author,
			Date startDate, Date endDate, String isDel,int pageIndex,int pageSize) {
		return textDataDao.getAllTextData(title, author, startDate, endDate, isDel,pageIndex,pageSize);
	}

	@Override
	public TextData getTextDataById(String textId) {
		return textDataDao.getTextDataById(textId);
	}

	@Override
	public void updateTextData(TextData textData) {
		textDataDao.updateTextData(textData);
	}

	@Override
	public void insertTextData(TextData textData) {
		textDataDao.insertTextData(textData);
	}

	@Override
	public void updateStatus(TextData textData) {
		textDataDao.updateStatus(textData);
	}

}
