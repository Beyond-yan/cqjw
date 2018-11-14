package com.gdssoft.cqjt.service.content;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.content.TextData;

/**
 * 文字类信息管理
 * @author  gyf 20140709
 * @return
 */
public interface TextDataService {
	/**
	 * 查询
	 * @param title
	 * @param author
	 * @param startDate
	 * @param endDate
	 * @param isDel
	 * @return
	 */
	public List<TextData> getAllTextData(String title,String author,Date startDate,Date endDate,String isDel,int pageIndex,int pageSize);
	/**
	 * 根据id查询
	 * @param textId
	 * @return
	 */
	public TextData getTextDataById(String textId);
	/**
	 * 更新
	 * @param textData
	 */
	public void updateTextData(TextData textData);
	/**
	 * 更新   状态
	 * @param textData
	 */
	public void updateStatus(TextData textData);
	/**
	 * 新增
	 * @param textData
	 */
	public void insertTextData(TextData textData);
}
