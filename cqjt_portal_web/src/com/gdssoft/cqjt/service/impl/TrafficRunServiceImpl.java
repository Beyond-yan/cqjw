package com.gdssoft.cqjt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.TrafficRunDao;
import com.gdssoft.cqjt.service.TrafficRunService;

@Service("trafficRunService")
public class TrafficRunServiceImpl implements TrafficRunService {

	@Autowired
	@Resource(name = "trafficRunDao")
	private TrafficRunDao trafficRunDao;

	@Override
	public <T> List<T> queryList(Map<String, Object> map) {
		return trafficRunDao.queryList(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return trafficRunDao.queryCount(map);
	}

	@Override
	public <T> List<T> queryPageList(Map<String, Object> map) {
		return trafficRunDao.queryPageList(map);
	}

	@Override
	public <T> T query(String id) {
		return trafficRunDao.query(id);
	}

	@Override
	public <T> boolean insert(T t) {
		return trafficRunDao.insert(t);
	}

	@Override
	public <T> boolean edit(T t) {
		return trafficRunDao.edit(t);
	}

	@Override
	public boolean del(String id) {
		return trafficRunDao.del(id);
	}

	@Override
	public boolean remove(String id) {
		return trafficRunDao.remove(id);
	}

	@Override
	public <T> boolean top(T t)
	{
		// TODO Auto-generated method stub
		return false;
	}


}
