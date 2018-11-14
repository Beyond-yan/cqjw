package com.gdssoft.cqjt.service;

import java.util.List;

import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.UserDetail;

public interface SubscribeSerivce extends BaseService{

	public List<Department> queryDept();
	public List<Department> queryDeptCategory();
	/**
	 * 查询用户
	 * @return
	 */
	public List<UserDetail> queryUsers();
	/**
	 * 通过用户ID查询用户
	 * @param userId
	 * @return
	 */
	public UserDetail getUserEditInfo(String userId) ;
}
