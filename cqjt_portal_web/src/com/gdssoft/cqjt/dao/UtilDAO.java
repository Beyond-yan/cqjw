package com.gdssoft.cqjt.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.SelectBean;

/*import com.foxconn.pojo.UserDetail;*/

@Repository("utilDAO")
public class UtilDAO {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<SelectBean> getContentSourceList() {
		return (List<SelectBean>) sqlMapClientTemplate
				.queryForList("UtilSQL.getContentSourceList");
	}

	@SuppressWarnings("unchecked")
	public List<SelectBean> getApproverList() {
		return (List<SelectBean>) sqlMapClientTemplate.queryForList("UtilSQL.getApproverList");
	}

	@SuppressWarnings("unchecked")
	public List<SelectBean> getBaseDataList(String typeID) {
		return (List<SelectBean>) sqlMapClientTemplate.queryForList("UtilSQL.getBaseDataList",
				typeID);
	}

	@SuppressWarnings("unchecked")
	public List<SelectBean> getGeneralDataList(Map<String, String> map) {
		return (List<SelectBean>) sqlMapClientTemplate.queryForList("UtilSQL.getGeneralDataList",
				map);
	}
}
