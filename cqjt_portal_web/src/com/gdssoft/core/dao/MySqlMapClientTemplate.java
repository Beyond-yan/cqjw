package com.gdssoft.core.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.gdssoft.core.tools.ReflectUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.pojo.BasePojo;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;

public class MySqlMapClientTemplate extends SqlMapClientTemplate {
	
	@Autowired
	@Resource(name="pagingSqlExecutor")
	private PagingSqlExecutor pagingSqlExecutor;
	
	public void setEnablePaging(boolean enablePaging) {
		pagingSqlExecutor.setEnablePaging(enablePaging);
	}
	
	@Override
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		if (sqlMapClient instanceof SqlMapClientImpl) {
            ReflectUtil.setFieldValue(((SqlMapClientImpl)sqlMapClient).getDelegate(), 
            		"sqlExecutor", SqlExecutor.class, pagingSqlExecutor);
        }
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<? extends BasePojo> queryForList(String statementName)
			throws DataAccessException {

		return this.queryForList(statementName, null);
	}
	
	@Override
	public List<? extends BasePojo> queryForList(final String statementName, final Object parameterObject)
			throws DataAccessException {

		@SuppressWarnings("unchecked")
		List<? extends BasePojo> list = super.queryForList(statementName, parameterObject);
		
		//如果有结果，把分页结果存到结果集的第一个对象中
		if(list!=null&&list.size()>0) {
			list.get(0).setPagingInfo(0, SystemContext.getDefaultPageSize(), list.size());
		}
		return list;
	}
	
	@Override
	public List<? extends BasePojo> queryForList(String statementName, int pageIndex, int pageSize)
			throws DataAccessException {

		return this.queryForList(statementName, null, pageIndex, pageSize);
	}
	
	@Override
	public List<? extends BasePojo> queryForList(
			final String statementName, final Object parameterObject, final int pageIndex, final int pageSize)
			throws DataAccessException {

		int skipResults = pageIndex*pageSize+1;
		int maxResults = pageSize;

		@SuppressWarnings("unchecked")
		List<? extends BasePojo> list =  super.queryForList(statementName, parameterObject, skipResults, maxResults);
		
		//如果有结果，把分页结果存到结果集的第一个对象中
		if(list!=null&&list.size()>0) {
			long totalCount = pagingSqlExecutor.getTotalCount();
			if (totalCount<=0) totalCount = list.size();
			list.get(0).setPagingInfo(pageIndex, pageSize, totalCount);
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryForListMap(String statementName,Object parameterObject)
			throws DataAccessException {

		return (List<Map<String, Object>>) super.queryForList(statementName, parameterObject);
	}
}
