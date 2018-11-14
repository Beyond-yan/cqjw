/**
 * 
 */
package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.TextCategory;

/**
 * @author H2602965
 * @title  部门service类
 * @date 2014.05.30
 */
public interface  DepartmentService {
	List<Department> getDeptAllList();
	List<TextCategory> getTextCategorys(String deptId);
	
	public List<Department> getScoreSumList(String[] deptCategory);
	List<Department> getDeptCategory(String deptCategory,String deptName,int pageIndex, int pageSize);
	//获取所有部门信息
	List<Department> getDeptNameByCat(String deptCategory);
	//根据部门ID 获取栏目名称 H2602965
	List<Department> getCategoryName(String deptId);
	//获取部门信息列表wcf 20140707
	List<Department> getDeptInfoList(String deptID,String deptName,String deptCategory,int pageIndex, int pageSize);
	//删除部门
	void deleteDept(String deptID);
	//更新部门启用停用状态
	void updateDept(String deptID);
	//栏目下拉列表 H2602965
	List<TextCategory> getTextCategorys();
	//逻辑删除,栏目部门表更新 H2602965
	void update(Department department);
	//逻辑删除,
	void delete(Department department);
	//获取部门ID  H2602965
	Department getDeptId(Long categoryId);
	//保存方法 H2602965
	public void insertColumn(Department department);
	//获取栏目部门详细信息
	Department getCategoryDeptDetail(Long categoryId,Integer deptId);
	
	//新增部门保存功能
	void save(Department department);
	//新增部门保存功能
	void updateDeptInfo(Department department);
	//根据部门ID查询部门名称
	Department getDeptName(Integer deptId);
	//根据部门ID查询剩余栏目
	List<Department> getColunmDeptUnInfo(String deptId,String categoryName,Date startDate,Date endDate);
	//部门表更新,只更新SCORE_GOV和SCORE_SITE用于考核后将考核分数存入sys_department中
	void updateDept(Department department);
	//部门表更新,只更新SCORE_GOV_year和SCORE_SITE_year、以及总和用于考核后将考核分数存入sys_department中
	void updateDeptTotal(Department department);
	
}
