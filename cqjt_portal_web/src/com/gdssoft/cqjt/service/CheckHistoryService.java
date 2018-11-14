package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.CheckHistory;

/**
 * 考核历史信息
 * @author  gyf 20150116
 * @return
 */
public interface CheckHistoryService {
	/**
	 * 查询所有资料
	 * @param fileName
	 * @param isDel
	 * @return
	 */
	public List<CheckHistory> getAllFile(String checkName,Date startDate,Date endDate,String isDel,int pageIndex, int pageSize);

	public List<CheckHistory> getFileList(String checkGroup,String isDel, int pageIndex, int pageSize);
	
	/**
	 * 根据checkId查询资料   
	 * @param checkId
	 * @return
	 */
	public CheckHistory getFileByFileId(String checkId);
	/**
	 * 更新资料
	 * @param checkHistory
	 */
	public void updateFile(CheckHistory checkHistory);
	/**
	 * 保存资料
	 * @param checkHistory
	 */

	public void save(CheckHistory checkHistory);
}
