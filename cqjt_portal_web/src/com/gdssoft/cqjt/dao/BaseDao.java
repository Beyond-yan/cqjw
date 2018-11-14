package com.gdssoft.cqjt.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gdssoft.core.dao.MySqlMapClientTemplate;

public class BaseDao {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	
	//获取列表
	@SuppressWarnings("unchecked")
	public <T> List<T> queryList(Map<String, Object> map){
		return (List<T>) sqlMapClientTemplate .queryForList(this.getClass().getSimpleName()+".queryList", map);
	}
	//获取总条数
	public int queryCount(Map<String, Object> map){
		return  (int) sqlMapClientTemplate .queryForObject(this.getClass().getSimpleName()+".queryCount", map);
	}
	//获取分页列表
	@SuppressWarnings("unchecked")
	public <T> List<T> queryPageList(Map<String, Object> map){
		return (List<T>) sqlMapClientTemplate .queryForList(this.getClass().getSimpleName()+".queryPageList", map);
	}
	//根据ID获取实体
	@SuppressWarnings("unchecked")
	public <T> T query(String id){
		return  (T) sqlMapClientTemplate .queryForObject(this.getClass().getSimpleName()+".query", id);
	}
	//添加
	public <T> boolean insert(T t){
		//返回的是主键？
		boolean result = false;
		try {
			sqlMapClientTemplate .insert(this.getClass().getSimpleName()+".insert", t);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return  result;
	}
	//修改
	public <T> boolean edit(T t){
		int result = this.sqlMapClientTemplate.update(this.getClass().getSimpleName()+".edit", t);
		System.out.println("--edit-->"+result);
		return  result > 0;
	}
	//是否置顶
	public <T> boolean top(T t){
		int result = this.sqlMapClientTemplate.update(this.getClass().getSimpleName()+".top", t);
		System.out.println("--top-->"+result);
		return  result > 0;
	}
	//逻辑删除
	public boolean del(String id){
		int result = this.sqlMapClientTemplate.update(this.getClass().getSimpleName()+".del", id);
		System.out.println("--del-->"+result);
		return  result > 0;
	}
	//物理删除
	public boolean remove(String id){
		int result = this.sqlMapClientTemplate.delete(this.getClass().getSimpleName()+".remove", id);
		System.out.println("--remove-->"+result);
		return  result > 0;
	}
	
}
