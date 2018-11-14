package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.CategoryRelation;
import com.gdssoft.cqjt.pojo.TextCategory;

/**
 * 栏目管理
 * 
 * @author gyf 20140703
 * @return
 */
@Service("columnDao")
public class ColumnDao {
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * 查询所有
	 */
	@SuppressWarnings("unchecked")
	public List<TextCategory> getAllColumn(String categoryName, Date startDate,
			Date endDate, String isDel, int pageIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryName", categoryName);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("isDel", isDel);

		return (List<TextCategory>) sqlMapClientTemplate.queryForList(
				"Column.getColumnAll", map, pageIndex, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<TextCategory> getAllColumn(String categoryName, Date startDate,
			Date endDate, String isDel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryName", categoryName);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("isDel", isDel);
		return (List<TextCategory>) sqlMapClientTemplate.queryForList(
				"Column.getColumnAll", map);
	}

	/**
	 * 根据Id查询
	 * 
	 * @param categoryId
	 * @return
	 */
	public TextCategory getColumnById(Long categoryId) {
		return (TextCategory) this.sqlMapClientTemplate.queryForObject(
				"Column.getColumnById", categoryId);
	}

	/**
	 * 更新
	 * 
	 * @param textCategory
	 */
	public void updateColumn(TextCategory textCategory) {
		this.sqlMapClientTemplate.update("Column.updateColumn", textCategory);
	}

	/**
	 * 更新
	 * 
	 * @param textCategory
	 */
	public void updateTextCategoryById(TextCategory textCategory) {
		this.sqlMapClientTemplate.update("Column.updateTextCategoryById",
				textCategory);
	}

	/**
	 * 保存
	 */
	public void insertColumn(TextCategory textCategory) {
		this.sqlMapClientTemplate.insert("Column.addColumn", textCategory);
	}

	/**
	 * 外网与内外栏目对应关系管理
	 * 
	 * @author H2602975 zhpeng
	 * @param categoryRelation
	 */
	public void insertCategoryRelation(CategoryRelation categoryRelation) {
		this.sqlMapClientTemplate.insert("Column.insertCategoryRelation",
				categoryRelation);

	}

	@SuppressWarnings("unchecked")
	public List<CategoryRelation> queryCategoryRelationList(String innerId) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("innerId", innerId);
		return (List<CategoryRelation>) this.sqlMapClientTemplate.queryForList(
				"Column.queryCategoryRelationList", map);
	}

	public CategoryRelation getRelationDetail(String relationId) {
		return (CategoryRelation) this.sqlMapClientTemplate.queryForObject(
				"Column.getRelationDetail", relationId);
	}

	public void updateCategoryRelation(CategoryRelation categoryRelation) {
		this.sqlMapClientTemplate.update("Column.updateCategoryRelation",
				categoryRelation);
	}

	public void deleteCategoryRelation(String relationId) {
		this.sqlMapClientTemplate.delete("Column.deleteCategoryRelationDetail",
				relationId);
	}

	/**
	 * 获取未选中的栏目列表
	 * @author H2602965
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextCategory> queryCategoryUnSlectList(Long categoryId,String categoryName,Date startDate,Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryId", categoryId);
		map.put("categoryName", categoryName);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return (List<TextCategory>) this.sqlMapClientTemplate.queryForList(
				"Column.getColunmUnSelect", map);
	}
	/**
	 * 获取选中的栏目列表
	 * @author H2602965
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextCategory> queryCategorySlectList(String categoryId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("categoryId", categoryId);
		return (List<TextCategory>) this.sqlMapClientTemplate.queryForList(
				"Column.getColunmSelect", map);
	}

	/**
	 * 新增到首页对应 模板栏目
	 * @author H2602965
	 * @param textCategory
	 */
	public void insertSelectColumn(TextCategory textCategory) {
		this.sqlMapClientTemplate.insert("Column.insertSelectColumn", textCategory);
	}

	/**
	 * 删除首页对应模板栏目
	 * @param textCategory
	 */
	public void deleteSelectColumn(TextCategory textCategory) {
		this.sqlMapClientTemplate.delete("Column.deleteSelectColumn",
				textCategory);
	}
}
