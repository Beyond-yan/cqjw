package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.TextPerson;

@Repository("textPersonDao")
public class TextPersonDao {
	
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
 
	public void insertTextPerson(TextPerson textPerson) {
		this.sqlMapClientTemplate.insert("TextPerson.insertTextPerson", textPerson);
	}
 
	public void updateTextPerson(TextPerson textPerson) {
		this.sqlMapClientTemplate.update("TextPerson.updateTextPerson", textPerson);
	}
 
	public void deleteTextPerson(String PersonId) {
		this.sqlMapClientTemplate.update("TextPerson.deleteTextPerson", PersonId);
	}
	
	public TextPerson getTextPersonDetail(String PersonId) {
		return (TextPerson) this.sqlMapClientTemplate.queryForObject(
				"TextPerson.getTextPersonDetail", PersonId);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TextPerson> getTextPersonList(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, String newsTag, String[] flags,int[] govUseFlag,
			String[] categorys, String isPhotoShow, String isDel,
			String isCheckAgain, int startRow, int endRow, int pageIndex,
			int pageSize,String SUB_CATEGORY) {
		Map<String, Object> map = new HashMap<String, Object>();	
		
		map.put("isDel", isDel);

		map.put("PersonTitle", newsTitle);
		map.put("entryUser", entryUser);
		
		if (null != SUB_CATEGORY)
			map.put("appointCat", SUB_CATEGORY);
		
		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);
		
		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);

		return (List<TextPerson>) sqlMapClientTemplate.queryForList(
				"TextPerson.getTextPersonList", map, pageIndex, pageSize);

	}
	
	
}
