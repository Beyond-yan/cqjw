package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.CategoryRelation;
import com.gdssoft.cqjt.pojo.TextCategory;
import com.gdssoft.cqjt.pojo.cms.Program;

/**
 * 栏目管理
 * 
 * @author gyf 20140703
 * @return
 */
public interface ColumnService {
	/**
	 * 查询
	 * 
	 * @param categoryName
	 * @param startDate
	 * @param endDate
	 * @param isDel
	 * @return
	 */
	public List<TextCategory> getAllColumn(String categoryName, Date startDate,
			Date endDate, String isDel, int pageIndex, int pageSize);

	public List<TextCategory> getAllColumn(String categoryName, Date startDate,
			Date endDate, String isDel);

	/**
	 * 根据id查询
	 * 
	 * @param categoryId
	 * @return
	 */
	public TextCategory getColumnById(Long categoryId);

	/**
	 * 更新
	 * 
	 * @param textCategory
	 */
	public void updateColumn(TextCategory textCategory);

	/**
	 * 更新
	 * 
	 * @author H2602965
	 * @param textCategory
	 */
	public void updateTextCategoryById(TextCategory textCategory);

	/**
	 * 新增
	 * 
	 * @param textCategory
	 */
	public void insertColumn(TextCategory textCategory);

	/**
	 * 外网栏目列表查询
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<Program> getProgramsByCategoryId(String categoryId);

	/**
	 * 外网内网栏目对应关系保存
	 * 
	 * @author H2602975 zhpeng
	 * @param textCategory
	 */
	public void saveCategoryRelation(CategoryRelation CategoryRelation);

	/**
	 * 外网内网栏目对应关系查询
	 * 
	 * @author H2602975 zhpeng
	 * @param textCategory
	 */
	public List<CategoryRelation> queryCategoryRelationList(String innerId);

	/**
	 * 外网内网栏目对应关系查询
	 * 
	 * @author H2602975 zhpeng
	 * @param textCategory
	 */
	public void upateCategoryRelation(CategoryRelation CategoryRelation);

	/**
	 * 获取外网内网栏目对应关系详情
	 * 
	 * @author H2602975 zhpeng
	 * @param categoryId
	 */
	public CategoryRelation getRelationDetail(String relationId);

	/**
	 * 外网内网栏目对应关系删除
	 * 
	 * @author H2602975 zhpeng
	 * @param categoryId
	 */
	public void deleteCategoryRelation(String relationId);

	/**
	 * 获取外网栏目
	 * 
	 * @author H2602975 zhpeng
	 * @param categoryId
	 */
	public List<CategoryRelation> getOuterCategoryList();

	/**
	 * @author H2602965
	 * 获取未选定的栏目列表
	 * @param categoryId
	 * @return
	 */
	public List<TextCategory> getColunmUnSelect(Long categoryId,String categoryName,Date startDate,Date endDate);
	/**
	 * @author H2602965
	 * 获取选定的栏目列表
	 * @param categoryId
	 * @return
	 */
	public List<TextCategory> getColunmSelect(String id);

	public void insertSelectColumn(TextCategory textCategory);

	public void deleteSelect(TextCategory textCategory);

}
