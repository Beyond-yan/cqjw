package com.gdssoft.cqjt.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.LogCountDao;
import com.gdssoft.cqjt.pojo.LogCount;
import com.gdssoft.cqjt.service.LogCountService;

/**
 * 日志监控
 * 
 * @author H2602965
 */
@Service("logCountServiceImpl")
public class LogCountServiceImpl implements LogCountService {
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Autowired
	@Resource(name = "logCountDao")
	private LogCountDao dao;

	@Override
	public void insert(LogCount logCount) {
		Date time=getCount().getUpdateTime();
		Date time2=logCount.getUpdateTime();
		if(time2.after(time)){
			dao.insertCount(logCount);
		}
		
	}

	@Override
	public LogCount getCount() {
		return dao.getCount();
	}

}
