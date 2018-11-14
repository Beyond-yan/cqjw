package com.gdssoft.cqjt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.InfoWorkNoticeDao;
import com.gdssoft.cqjt.pojo.InfoWorkNotice;
import com.gdssoft.cqjt.pojo.TextPublication;
import com.gdssoft.cqjt.service.InfoWorkNoticeService;

@Service("infoWorkNoticeServiceImpl")
public class InfoWorkNoticeServiceImpl implements InfoWorkNoticeService
{
	@Autowired
	@Resource(name = "infoWorkNoticeDao")
	private InfoWorkNoticeDao infoWorkNoticeDao;

	@Override
	public <T> List<T> queryList(Map<String, Object> map)
	{
		return infoWorkNoticeDao.queryList(map);
	}

	@Override
	public int queryCount(Map<String, Object> map)
	{
		return infoWorkNoticeDao.queryCount(map);
	}

	@Override
	public <T> List<T> queryPageList(Map<String, Object> map)
	{
		return infoWorkNoticeDao.queryPageList(map);
	}
	
	@Override
	public <T> T query(String id)
	{
		return infoWorkNoticeDao.query(id);
	}

	@Override
	public <T> boolean insert(T t)
	{
		return infoWorkNoticeDao.insert(t);
	}

	@Override
	public <T> boolean edit(T t)
	{
		return infoWorkNoticeDao.edit(t);
	}

	@Override
	public boolean del(String id)//物理删除
	{
		return infoWorkNoticeDao.del(id);
	}

	@Override
	public boolean remove(String id)//逻辑删除
	{
		return infoWorkNoticeDao.remove(id);
	}
	
	//首次信息查询，带分页功能
	@Override
	public List<InfoWorkNotice> queryInfoNoticeList(String workTitle,
			Date beginDate, Date endDate, int pageIndex, int pageSize)
	{
		return infoWorkNoticeDao.queryInfoNoticeList(workTitle, beginDate,
				endDate, pageIndex, pageSize);
	}
	//首次信息查询，带分页功能以及过滤未发布通知
	@Override
	public List<InfoWorkNotice> queryPublishInfoNotice(String workTitle,
			Date beginDate, Date endDate, int pageIndex, int pageSize)
	{
		return infoWorkNoticeDao.queryPublishInfoNotice(workTitle, beginDate,
				endDate, pageIndex, pageSize);
	}

	@Override
	public <T> boolean top(T t)//是否置顶
	{
		return infoWorkNoticeDao.top(t);
	}

}
