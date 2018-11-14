package com.gdssoft.cqjt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.TextDeptOuterCategoryDao;
import com.gdssoft.cqjt.pojo.TextDeptOuterCategory;
import com.gdssoft.cqjt.service.TextDeptOuterCategoryService;
/**
 * 外网栏目管理
 * @author  gyf 20141125
 * @return
 */
@Service("textDeptOuterCategoryService")
public class TextDeptOuterCategoryServiceImpl implements
		TextDeptOuterCategoryService {
	@Autowired
	@Resource(name = "textDeptOuterCategoryDao")
	private TextDeptOuterCategoryDao textDeptOuterCategoryDao;
	
	@Override
	public List<TextDeptOuterCategory> getOuterCategoryById(Long depId) {
		return textDeptOuterCategoryDao.getOuterCategoryById(depId);
	}

	@Override
	public TextDeptOuterCategory getTextDeptOuterCategory(String outCategoryId,String depId) {
		return textDeptOuterCategoryDao.getTextDeptOuterCategory(outCategoryId,depId);
	}

}
