package com.gdssoft.cqjt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.CheckStandardDao;
import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.service.CheckStandardService;
/**
 * 考核标准管理实现
 * @author H2602975 zhpeng 20140620
 */
@Service("checkStandardServiceImpl")
public class CheckStandardServiceImpl implements CheckStandardService {

	@Resource(name = "checkStandardDao")
	private CheckStandardDao checkStandardDao;
	
	@Override
	public List<CheckStandard> getCheckStandardList(int pageIndex,int pageSize) {
		return checkStandardDao.getCheckStandarList( pageIndex, pageSize);
	}

	@Override
	public CheckStandard getCheckStandardDetail(String code) {
		return checkStandardDao.getCheckStandardDetail(code);
	}
	@Override
	public List<CheckStandard> queryCheckStandard(String[] code) {
		return checkStandardDao.queryCheckStandard(code);
	}

	@Override
	public void saveStandard(CheckStandard checkStandard) {
		checkStandardDao.save(checkStandard);

	}

	@Override
	public void updateStandard(CheckStandard checkStandard) {
		checkStandardDao.update(checkStandard);

	}

	@Override
	public void deleteByCheckId(String checkId) {
		checkStandardDao.delete(checkId);

	}
	@Override
	public CheckStandard getCode(String code){
		return checkStandardDao.getCode(code);
	}
	

}
