package com.gdssoft.cqjt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.UtilDAO;
import com.gdssoft.cqjt.pojo.SelectBean;
import com.gdssoft.cqjt.service.UtilService;

/**
 * @author F3228761
 * @date : Jul 27, 2013 10:39:23 AM
 */
@Service("utilServiceImpl")
public class UtilServiceImpl implements UtilService {

	@Resource(name = "utilDAO")
	private UtilDAO utilDAO;

	@Override
	public List<SelectBean> getContentSourceList() {
		// modify by david on date 2013/07/30
		return utilDAO.getContentSourceList();
	}

	@Override
	public List<SelectBean> getApproverList() {
		return utilDAO.getApproverList();
	}

	// added by Cube @130801
	@Override
	public List<SelectBean> getBaseDataList(String typeID) {
		return utilDAO.getBaseDataList(typeID);
	}

	// added by Cube @130801
	@Override
	public List<SelectBean> getGeneralDataList(Map<String, String> map) {
		return utilDAO.getGeneralDataList(map);
	}

	// added by Cube @130801
	@Override
	public List<SelectBean> getBaseDataList(String typeID, boolean emptyItem) {
		List<SelectBean> list = utilDAO.getBaseDataList(typeID);
		if (emptyItem)
			list.add(0, new SelectBean(null, ""));
		return list;
	}

	// added by Cube @130801
	@Override
	public List<SelectBean> getGeneralDataList(Map<String, String> map,
			boolean emptyItem) {
		List<SelectBean> list = utilDAO.getGeneralDataList(map);
		if (emptyItem)
			list.add(0, new SelectBean(null, ""));
		return list;

	}
}
