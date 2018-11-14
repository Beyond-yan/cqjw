/**
 * 
 */
package com.gdssoft.cqjt.service.impl;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.DepartmentDao;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.TextCategory;
import com.gdssoft.cqjt.service.DepartmentService;

/**
 * @author H2602965
 * @title 部门实现类
 * @date 2014.05.30
 */
@Service("departmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService {
	@Resource(name="departmentDAO")
	private DepartmentDao departmentDao;
	/**
	 * @author H2602965 2014.05.30
	 */
	@Override
	public List<Department> getDeptAllList() {
		return departmentDao.getDeptAllList();
	}
	
	public List<TextCategory> getTextCategorys(String deptId) {
		return departmentDao.getCategorys(deptId);
	}

	@Override
	public List<Department> getScoreSumList(String[] deptCategory) {
		return departmentDao.getScoreSumList(deptCategory);
	}
	/**
	 * 20140704
	 * @author H2602965
	 */
	@Override
	public List<Department> getDeptCategory(String deptCategory,String deptName,int pageIndex, int pageSize) {
		return departmentDao.getDeptCategory(deptCategory,deptName,pageIndex,pageSize);
	}
	/**
	 * 根据部门ID获取栏目名称
	 * @author H2602965 
	 */
	@Override
	public List<Department> getCategoryName(String deptId) {
		return departmentDao.getCategoryName(deptId);
	}
	/**
	 * 查询部门结果列表 wcf20140707
	 */
	@Override
	public List<Department> getDeptInfoList(String deptID,String deptName,String deptCategory,int pageIndex, int pageSize) {
		return departmentDao.getDeptInfoList(deptID,deptName,deptCategory,pageIndex,pageSize);
	}
	
	@Override
	public void deleteDept(String deptID) {
		departmentDao.deleteDepartment(deptID);
	}
	
	@Override
	public void updateDept(String deptID) {
		departmentDao.updateDepartment(deptID);
	}
	/**
	 * 栏目下拉列表
	 * @author H2602965
	 */
	@Override
	public List<TextCategory> getTextCategorys() {
		return departmentDao.getTextCategorys();
	}
	/**
	 * 逻辑删除,部门与栏目表更新
	 * @author H2602965
	 * @param department
	 */
	public void delete(Department department){
		departmentDao.delete(department);
	}
	/**
	 * 根据栏目ID查询部门Id
	 * @author H2602965
	 * @param categoryId
	 * @return
	 */
	public Department getDeptId(Long categoryId) {
		return (Department) departmentDao.getDeptId(categoryId);
	}
	
	/**
	 * 保存方法
	 * @author H2602965
	 */
	@Override
	public void insertColumn(Department department) {
		departmentDao.insert(department);
	}
	
	@Override
	public void save(Department department) {
		departmentDao.insertDepartment(department);
	}
	/**
	 * 获取部门栏目详细信息
	 *@author H2602965 
	 */		
	@Override
	public Department getCategoryDeptDetail(Long categoryId, Integer deptId) {
		return departmentDao.getCategoryDeptDetail(categoryId, deptId);
	}

	/**
	 * 更新方法
	 * @author H2602965
	 */
	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}
	//部门管理 编辑保存
	@Override
	public void updateDeptInfo(Department department) {
		departmentDao.updateDeptInfo(department);

	}
	/**
	 * 根据部门ID查询部门名称
	 * @author H2602965
	 */
	@Override
	public Department getDeptName(Integer deptId) {
		return departmentDao.getDeptName(deptId);
	}

	@Override
	public List<Department> getColunmDeptUnInfo(String deptId,String categoryName,Date startDate,Date endDate) {
		return departmentDao.getColunmDeptUnInfo(deptId,categoryName,startDate,endDate);
	}
	
	/**
	 * 部门表更新,只更新SCORE_GOV和SCORE_SITE用于考核后将考核分数存入sys_department中
	 * @author H2602965
	 * @param department
	 */
	public void updateDept(Department department) {
		departmentDao.updateDept(department);

	}
	/**
	 * 部门表更新,只更新SCORE_GOV_YEAR和SCORE_SITE_YEAR、SCORE_GOV_YEAR_TOTAL、SCORE_SITE_YEAR_TOTAL用于考核后将考核分数存入sys_department中
	 * @author H2602965
	 * @param department
	 */
	public void updateDeptTotal(Department department) {
		departmentDao.updateDeptTotal(department);
		
	}

	@Override
	public List<Department> getDeptNameByCat(String deptCategory) {
		return departmentDao.getDeptNameByCat(deptCategory);
	}
}
