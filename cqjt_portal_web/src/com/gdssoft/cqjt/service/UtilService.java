package com.gdssoft.cqjt.service;

import java.util.List;
import java.util.Map;

import com.gdssoft.cqjt.pojo.SelectBean;

/**
 * @author F3228761
 * @date : Jul 27, 2013 10:34:44 AM
 */
public interface UtilService {

	List<SelectBean> getContentSourceList();

	List<SelectBean> getApproverList();

	List<SelectBean> getBaseDataList(String typeID);

	List<SelectBean> getBaseDataList(String typeID, boolean emptyItem);

	List<SelectBean> getGeneralDataList(Map<String, String> map);

	List<SelectBean> getGeneralDataList(Map<String, String> map,
			boolean emptyItem);

}
