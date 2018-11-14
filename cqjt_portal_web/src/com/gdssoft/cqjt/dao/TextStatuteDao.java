package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.TextStatute;

@Repository("textStatuteDao")
public class TextStatuteDao {
	
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
 
	public void insertTextStatute(TextStatute textStatute) {
		this.sqlMapClientTemplate.insert("TextStatute.insertTextStatute", textStatute);
	}
 
	public void updateTextStatute(TextStatute textStatute) {
		this.sqlMapClientTemplate.update("TextStatute.updateTextStatute", textStatute);
	}
 
	public void deleteTextStatute(String statuteId) {
		this.sqlMapClientTemplate.update("TextStatute.deleteTextStatute", statuteId);
	}
	
	public TextStatute getTextStatuteDetail(String statuteId) {
		return (TextStatute) this.sqlMapClientTemplate.queryForObject(
				"TextStatute.getTextStatuteDetail", statuteId);
	}
	
	
	//法律法规查询
	@SuppressWarnings("unchecked")
	public List<TextStatute> getTextStatuteList(String newsTitle, String entryUser,
			Date entryDateS, Date entryDateE, String newsTag, String[] flags,int[] govUseFlag,
			String[] categorys, String isPhotoShow, String isDel,
			String isCheckAgain, int startRow, int endRow, int pageIndex,
			int pageSize,String SUB_CATEGORY, String SUB_CATEGORY_INFO) {
		Map<String, Object> map = new HashMap<String, Object>();	
		if (null != SUB_CATEGORY)
			map.put("SUB_CATEGORY", SUB_CATEGORY);
		if (null != SUB_CATEGORY_INFO)
			map.put("SUB_CATEGORY_INFO", SUB_CATEGORY_INFO);
		
		map.put("isDel", isDel);

		map.put("statuteTitle", newsTitle);
		map.put("entryUser", entryUser);


		if (!StringUtils.isBlank(isPhotoShow))
			map.put("isPhotosShow", isPhotoShow);
		
		if (null != entryDateS)
			map.put("entryDateS", entryDateS);
		if (null != entryDateE)
			map.put("entryDateE", entryDateE);
		
		// 新增isCheckAgain栏位，用于角色权限管控；
		map.put("isCheckAgain", isCheckAgain);
		if (startRow > 0)
			map.put("startRow", startRow);
		if (endRow > 0)
			map.put("endRow", endRow);

		return (List<TextStatute>) sqlMapClientTemplate.queryForList(
				"TextStatute.getTextStatuteList", map, pageIndex, pageSize);

	}
	
	
}
