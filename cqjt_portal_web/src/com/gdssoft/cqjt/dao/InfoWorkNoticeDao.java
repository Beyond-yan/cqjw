package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.InfoWorkNotice;

@Service("infoWorkNoticeDao")
public class InfoWorkNoticeDao extends BaseDao
{

	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	//首次信息查询，带分页功能
	@SuppressWarnings("unchecked")
	public List<InfoWorkNotice> queryInfoNoticeList(String workTitle, Date beginDate,
			Date endDate, int pageIndex, int pageSize) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workTitle", workTitle);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		return (List<InfoWorkNotice>) sqlMapClientTemplate.queryForList(
				"InfoWorkNoticeDao.queryInfoNoticeList", map, pageIndex, pageSize);
	}
	
	//首次信息查询，带分页功能以及过滤未发布通知
	@SuppressWarnings("unchecked")
	public List<InfoWorkNotice> queryPublishInfoNotice(String workTitle, Date beginDate,
			Date endDate, int pageIndex, int pageSize) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workTitle", workTitle);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		return (List<InfoWorkNotice>) sqlMapClientTemplate.queryForList(
				"InfoWorkNoticeDao.queryPublishInfoNotice", map, pageIndex, pageSize);
	}

}
