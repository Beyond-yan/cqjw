package com.gdssoft.cqjt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.TextCategory;
import com.gdssoft.cqjt.pojo.UserDetail;

@SuppressWarnings("unchecked")
@Service("subscribeDao")
public class SubscribeDao extends BaseDao{

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * 查询部门
	 * @return
	 */
	public List<Department> queryDept(){
		System.out.println("=================");
		List<Department> deptList = new ArrayList<Department>();
		deptList = (List<Department>)sqlMapClientTemplate.queryForList("Dept.getDeptAll");
		return deptList;
	}
	
	/**
	 * 查询部门分类
	 * @return
	 */
	public List<Department> queryDeptCategory(){
		List<Department> deptCategoryList = (List<Department>) sqlMapClientTemplate.queryForList("Dept.queryCategory");
		return deptCategoryList;
	}
	
	/**
	 * 查询用户
	 * @return
	 */
	public List<UserDetail> queryUsers(){
		List<UserDetail> userList = (List<UserDetail>)sqlMapClientTemplate.queryForList("Users.SelectUser");
		return userList;
	}
	
	/**
	 * 通过用户ID查询用户
	 * @param userId
	 * @return
	 */
	public UserDetail getUserEditInfo(String userId) {
		UserDetail user = (UserDetail)sqlMapClientTemplate.queryForObject("Users.EditUserInfo", userId);
		return user;
	}
	
}
