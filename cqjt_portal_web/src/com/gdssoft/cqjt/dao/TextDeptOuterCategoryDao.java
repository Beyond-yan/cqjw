package com.gdssoft.cqjt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.Menu;
import com.gdssoft.cqjt.pojo.TextDeptOuterCategory;

/**
 * 外网栏目管理
 * @author  gyf 20141125
 * @return
 */
@Service("textDeptOuterCategoryDao")
public class TextDeptOuterCategoryDao {
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 根据部门id查询外网栏目信息
	 */
	@SuppressWarnings("unchecked")
	public List<TextDeptOuterCategory> getOuterCategoryById(Long depId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("depId", depId);
		map.put("outCategoryId","");
		return (List<TextDeptOuterCategory>) sqlMapClientTemplate
				.queryForList("DeptOuterCategory.getOuterCategoryById", map);
	}
	/**
	 * 根据外网id查询外网栏目信息
	 */
	public TextDeptOuterCategory getTextDeptOuterCategory(String outCategoryId,String depId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("depId", depId);
		map.put("outCategoryId",outCategoryId);
		return (TextDeptOuterCategory)sqlMapClientTemplate.queryForObject("DeptOuterCategory.getOuterCategoryById", map);
	}
}
