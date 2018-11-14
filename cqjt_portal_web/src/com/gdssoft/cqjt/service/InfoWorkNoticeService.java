package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.InfoWorkNotice;
import com.gdssoft.cqjt.pojo.TextPublication;

public interface InfoWorkNoticeService extends BaseService
{
	//首次信息查询，带分页功能
	public List<InfoWorkNotice> queryInfoNoticeList(String workTitle,
			Date beginDate, Date endDate,int pageIndex, int pageSize);
	
	//首次信息查询，带分页功能以及过滤未发布信息
	public List<InfoWorkNotice> queryPublishInfoNotice(String workTitle,
			Date beginDate, Date endDate, int pageIndex, int pageSize);
}
