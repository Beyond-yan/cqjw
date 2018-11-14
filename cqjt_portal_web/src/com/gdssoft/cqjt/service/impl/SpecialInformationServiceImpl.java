package com.gdssoft.cqjt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.SpecialInformationDao;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.service.SpecialInformationService;

@Service("specialInformationService")
public class SpecialInformationServiceImpl implements SpecialInformationService{

	@Autowired
	@Resource(name = "specialInformationDao")
	private SpecialInformationDao specialInformationDao;

	@Override
	public <T> List<T> queryList(Map<String, Object> map) {
		return specialInformationDao.queryList(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return specialInformationDao.queryCount(map);
	}

	@Override
	public <T> List<T> queryPageList(Map<String, Object> map) {
		return specialInformationDao.queryPageList(map);
	}

	@Override
	public <T> T query(String id) {
		return specialInformationDao.query(id);
	}

	@Override
	public <T> boolean insert(T t) {
		return specialInformationDao.insert(t);
	}

	@Override
	public <T> boolean edit(T t) {
		return specialInformationDao.edit(t);
	}

	@Override
	public boolean del(String id) {
		return specialInformationDao.del(id);
	}

	@Override
	public boolean remove(String id) {
		return specialInformationDao.remove(id);
	}

	@Override
	public <T> boolean top(T t)
	{
		return specialInformationDao.edit(t);
	}
	
	@Override
	public List<TextGovInfo> getGovNewsList(Map<String, Object> map, int pageIndex, int pageSize){
		return specialInformationDao.getGovNewsList(map, pageIndex, pageSize);
	}
}
