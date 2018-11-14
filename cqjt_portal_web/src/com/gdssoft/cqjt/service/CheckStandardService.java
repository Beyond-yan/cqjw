package com.gdssoft.cqjt.service;

import java.util.List;

import com.gdssoft.cqjt.pojo.CheckStandard;
/**
 * 考核标准管理service
 * @author H2602975 zhpeng 20140620
 */

public interface CheckStandardService {
	
	public List<CheckStandard> getCheckStandardList(int pageIndex,int pageSize);

	public CheckStandard getCheckStandardDetail(String code);
	
	public void saveStandard(CheckStandard checkStandard);
	
	public void updateStandard(CheckStandard checkStandard);
	
	public void deleteByCheckId(String checkId);
	
	/**
	 * 根据deptID查询code 
	 * @param deptID
	 * @return
	 */
	public CheckStandard getCode(String code);
	
	/**
	 * 根据类型查询分数
	 * @param code
	 * @return
	 */
	public List<CheckStandard> queryCheckStandard(String[] code) ;
	
	
}
