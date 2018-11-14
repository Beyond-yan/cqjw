package com.gdssoft.cqjt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.CheckHistoryDao;
import com.gdssoft.cqjt.pojo.CheckHistory;
import com.gdssoft.cqjt.service.CheckHistoryService;
/**
 * 考核历史信息
 * @author  gyf 20150116
 * @return
 */

@Service("checkHistoryService")
public class CheckHistoryServiceImpl implements CheckHistoryService {
	@Autowired
	@Resource(name = "checkHistoryDao")
	private CheckHistoryDao checkHistoryDao;

	@Override
	public List<CheckHistory> getAllFile(String checkName, Date startDate,
			Date endDate, String isDel, int pageIndex, int pageSize) {
		List<CheckHistory> checkHistoryList = checkHistoryDao.getAllFile(checkName, startDate, endDate, isDel, pageIndex, pageSize);
		return checkHistoryList;
	}

	@Override
	public CheckHistory getFileByFileId(String checkId) {
		CheckHistory checkHistory = checkHistoryDao.getFileByFileId(checkId);
		return checkHistory;
	}

	@Override
	public void updateFile(CheckHistory checkHistory) {
		checkHistoryDao.updateFile(checkHistory);
	}

	@Override
	public void save(CheckHistory checkHistory) {
		checkHistoryDao.insertFile(checkHistory);
	}

	@Override
	public List<CheckHistory> getFileList(String checkGroup, String isDel,
			int pageIndex, int pageSize) {
		List<CheckHistory> checkHistoryList = checkHistoryDao.getFileList(checkGroup, isDel, pageIndex, pageSize);
		return checkHistoryList;
	}
}
