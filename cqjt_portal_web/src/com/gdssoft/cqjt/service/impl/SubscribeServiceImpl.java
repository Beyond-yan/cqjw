package com.gdssoft.cqjt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.SubscribeDao;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.UserDetail;
import com.gdssoft.cqjt.service.SubscribeSerivce;

@Service("subscribeSerivce")
public class SubscribeServiceImpl implements SubscribeSerivce{

	@Autowired
	@Resource(name="subscribeDao")
	private SubscribeDao subscribeDao;

	@Override
	public <T> List<T> queryList(Map<String, Object> map) {
		return null;
	}
	@Override
	public int queryCount(Map<String, Object> map) {
		return subscribeDao.queryCount(map);
	}
	@Override
	public <T> List<T> queryPageList(Map<String, Object> map) {
		return subscribeDao.queryPageList(map);
	}
	@Override
	public <T> T query(String id) {
		return subscribeDao.query(id);
	}
	@Override
	public <T> boolean insert(T t) {
		return subscribeDao.insert(t);
	}
	@Override
	public <T> boolean edit(T t) {
		return subscribeDao.edit(t);
	}
	@Override
	public boolean del(String id) {
		return subscribeDao.del(id);
	}
	@Override
	public boolean remove(String id) {
		return false;
	}
	
	public List<Department> queryDept(){
		System.out.println("==========================");
		return subscribeDao.queryDept();
	}
	
	/**
	 * 查询部门分类
	 * @return
	 */
	public List<Department> queryDeptCategory(){
		return subscribeDao.queryDeptCategory();
	}
	/**
	 * 查询用户
	 * @return
	 */
	public List<UserDetail> queryUsers(){
		return subscribeDao.queryUsers();
	}
	
	/**
	 * 通过用户ID查询用户
	 * @param userId
	 * @return
	 */
	public UserDetail getUserEditInfo(String userId) {
		return subscribeDao.getUserEditInfo(userId);
	}
	@Override
	public <T> boolean top(T t)
	{
		return subscribeDao.top(t);
	}
}
