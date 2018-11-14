/**
 * 
 */
package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.TextCategory;

/**
 * @author H2602965
 * @title 部门Dao类
 * @date 2014.05.30
 */
@Repository("departmentDAO")
public class DepartmentDao{
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	
	 @SuppressWarnings("unchecked")
	public List<Department> getDeptAllList(){
		 return  (List<Department>) this.sqlMapClientTemplate.queryForList("Dept.getDeptAll");
	}


	@SuppressWarnings("unchecked")
	public List<TextCategory> getCategorys(String deptId) {
		return (List<TextCategory>) sqlMapClientTemplate.queryForList("Dept.getCategorys", deptId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> getScoreSumList(String[] deptCategory){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deptCategory", deptCategory);
		return  (List<Department>) this.sqlMapClientTemplate.queryForList("Dept.getSumList",map);
	}
	
	/**
	 * 獲取部門名稱和部門分類
	 * 新增功能下拉列表
	 * @author H2602965
	 * @param deptCategory
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getDeptCategory(String deptCategory,String deptName,int pageIndex, int pageSize) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("deptCategory", deptCategory);
		map.put("deptName", deptName);
		
		return (List<Department>) sqlMapClientTemplate
				.queryForList("Dept.getDeptCategory", map, pageIndex, pageSize);

	}
	
	/**
	 * 根据部门ID获取栏目姓名
	 * @author H2602965
	 * @param deptId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getCategoryName(String deptId) {
		return (List<Department>) sqlMapClientTemplate.queryForList("Dept.getCategoryName", deptId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> getDeptInfoList(String deptID,String deptName,String deptCategory,int pageIndex, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deptID", deptID);
		map.put("deptName", deptName);
		map.put("deptCategory", deptCategory);
		
		return (List<Department>) sqlMapClientTemplate
				.queryForList("Dept.getDeptInfo", map, pageIndex, pageSize);
		
	}
	
	public void deleteDepartment(String deptID) {
		this.sqlMapClientTemplate.update("Dept.deleteDepartment", deptID);
	}
	
	public void updateDepartment(String deptID) {
		this.sqlMapClientTemplate.update("Dept.updateDepartment", deptID);
	}
	
	/**
	 * 栏目下拉列表
	 * @author H2602965
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextCategory> getTextCategorys() {
		return (List<TextCategory>) sqlMapClientTemplate.queryForList("Dept.getCategorySelect");
	}
	/**
	 * 逻辑删除部门对应的栏目
	 * @author H2602965
	 * @param department
	 */
	public void delete(Department department){
		logger.debug("IsDel删除======="+department.getIsDel());
		this.sqlMapClientTemplate.update("Dept.deleteCategoryDept",department);
	}
	
	/**
	 * 根据栏目ID查询部门ID删除使用
	 * @author H2602965
	 * @param categoryId
	 * @return
	 */
	public Department getDeptId(Long categoryId) {
		return (Department) sqlMapClientTemplate.queryForObject("Dept.getDeptId",categoryId);
	}
	/**
	 * 保存方法,部门栏目表
	 * @author H2602965
	 * @param department
	 */
	public void insert(Department department) {
		this.sqlMapClientTemplate.insert("Dept.addColumn",department);
	}
	/**
	 * 部门表插入数据
	 * @edit H2602965
	 * @param department
	 */
	public void insertDepartment(Department department) {
		this.sqlMapClientTemplate.insert("Dept.insertDepartment", department);
	}
	
	
	/**
	 * 获取部门栏目详细信息用于编辑
	 * @author H2602965
	 * @param categoryId
	 * @param deptId
	 * @return
	 */
	public Department getCategoryDeptDetail(Long categoryId,Integer deptId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("categoryId", categoryId);
		map.put("deptID", deptId);
		return (Department) this.sqlMapClientTemplate.queryForObject("Dept.getCategoryDeptDetail", map);
	}
	
	/**
	 * 更新保存
	 * @author H2602965
	 * @param department
	 */
	public void update(Department department){
		this.sqlMapClientTemplate.update("Dept.updateCategoryDept",department);
	}
	//部门管理 编辑保存
	public void updateDeptInfo(Department department) {
		this.sqlMapClientTemplate.update("Dept.updateDeptInfo", department);
	}
	/**
	 * 获取部门名称 根据部门ID
	 * @author H2602965
	 * @param deptID
	 * @return
	 */
	public Department getDeptName(Integer deptID) {
		return (Department) sqlMapClientTemplate.queryForObject("Dept.getDeptName",deptID);
	}
	
	/**
	 * 根据部门分类獲取所有部門名稱和部門分類
	 * @author H2602965
	 * @param deptCategory
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getDeptNameByCat(String deptCategory) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("deptCategory", deptCategory);
		return (List<Department>) sqlMapClientTemplate
				.queryForList("Dept.getDeptCategory", map);
	}
	
	
	/**
	 * 按照条件查询部门对应的栏目,查询的是剩余的栏目信息
	 * @author H2602965
	 * @param deptId
	 * @param categoryName
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getColunmDeptUnInfo(String deptId,String categoryName,Date startDate,Date endDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deptID", deptId);
		map.put("categoryName", categoryName);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return (List<Department>) sqlMapClientTemplate.queryForList("Dept.getColunmDeptUnInfo",map);
	}
	/**
	 * 部门表更新,只更新SCORE_GOV和SCORE_SITE用于考核后将考核分数存入sys_department中
	 * @author H2602965
	 * @param department
	 */
	public void updateDept(Department department) {
		this.sqlMapClientTemplate.update("Dept.updateDept", department);
	}
	
	/**
	 * 部门表更新,只更新SCORE_GOV_YEAR和SCORE_SITE_YEAR、SCORE_GOV_YEAR_TOTAL、SCORE_SITE_YEAR_TOTALsys_department中
	 * @author H2602965
	 * @param department
	 */
	public void updateDeptTotal(Department department) {
		this.sqlMapClientTemplate.update("Dept.updateDeptTotal", department);
	}
}
