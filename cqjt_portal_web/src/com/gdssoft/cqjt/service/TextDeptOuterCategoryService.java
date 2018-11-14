package com.gdssoft.cqjt.service;

import java.util.List;

import com.gdssoft.cqjt.pojo.TextDeptOuterCategory;

/**
 * 外网栏目管理
 * @author  gyf 20141125
 * @return
 */
public interface TextDeptOuterCategoryService {
	//根据部门id查询外网栏目信息
	public List<TextDeptOuterCategory> getOuterCategoryById(Long depId);
	/**
	 * 根据外网id查询外网栏目信息
	 */
	public TextDeptOuterCategory getTextDeptOuterCategory(String outCategoryId,String depId);
}
