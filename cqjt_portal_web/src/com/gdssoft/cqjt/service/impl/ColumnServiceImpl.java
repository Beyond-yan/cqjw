package com.gdssoft.cqjt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.ColumnDao;
import com.gdssoft.cqjt.pojo.CategoryRelation;
import com.gdssoft.cqjt.pojo.TextCategory;
import com.gdssoft.cqjt.pojo.cms.Program;
import com.gdssoft.cqjt.service.ColumnService;
/**
 * 栏目管理
 * @author  gyf 20140703
 * @return
 */
@Service("columnService")
public class ColumnServiceImpl implements ColumnService {
	@Autowired
	@Resource(name = "columnDao")
	private ColumnDao columnDao;
	
	@Override
	public List<TextCategory> getAllColumn(String categoryName, Date startDate,
			Date endDate, String isDel,int pageIndex, int pageSize) {
		return columnDao.getAllColumn(categoryName, startDate, endDate, isDel,pageIndex,pageSize);
	}
	
	@Override
	public List<TextCategory> getAllColumn(String categoryName, Date startDate,
			Date endDate, String isDel) {
		return columnDao.getAllColumn(categoryName, startDate, endDate, isDel);
	}

	@Override
	public TextCategory getColumnById(Long categoryId) {
		return columnDao.getColumnById(categoryId);
	}

	@Override
	public void updateColumn(TextCategory textCategory) {
		columnDao.updateColumn(textCategory);
	}

	@Override
	public void insertColumn(TextCategory textCategory) {
		columnDao.insertColumn(textCategory);
	}

	@Override
	public List<Program> getProgramsByCategoryId(String categoryId) {
		return null;
	}

	public void saveCategoryRelation(CategoryRelation categoryRelation) {
		columnDao.insertCategoryRelation(categoryRelation);		
	}

	@Override
	public List<CategoryRelation> queryCategoryRelationList(String innerId) {
		return columnDao.queryCategoryRelationList(innerId);
	}

	@Override
	public void upateCategoryRelation(CategoryRelation CategoryRelation) {
            columnDao.updateCategoryRelation(CategoryRelation);		
	}

	@Override
	public CategoryRelation getRelationDetail(String relationId) {
		return columnDao.getRelationDetail(relationId);
	}

	@Override
	public void deleteCategoryRelation(String relationId) {
		  columnDao.deleteCategoryRelation(relationId);		
	}

	@Override
	public List<CategoryRelation> getOuterCategoryList() {
		return null;
	}

	@Override
	public void updateTextCategoryById(TextCategory textCategory) {
		columnDao.updateTextCategoryById(textCategory);
	}

	@Override
	public List<TextCategory> getColunmUnSelect(Long categoryId,String categoryName,Date startDate,Date endDate) {
		return columnDao.queryCategoryUnSlectList(categoryId,categoryName,startDate,endDate);
	}

	@Override
	public List<TextCategory> getColunmSelect(String categoryId) {
		return columnDao.queryCategorySlectList(categoryId);
	}

	@Override
	public void insertSelectColumn(TextCategory textCategory) {
		columnDao.insertSelectColumn(textCategory);
	}

	@Override
	public void deleteSelect(TextCategory textCategory) {
		// TODO Auto-generated method stub
		columnDao.deleteSelectColumn(textCategory);
	}

}
