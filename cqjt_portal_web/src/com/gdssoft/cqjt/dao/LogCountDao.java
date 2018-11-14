package com.gdssoft.cqjt.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.LogCount;

@Repository("logCountDao")
public class LogCountDao {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	public LogCount getCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endRow", 1);
		return (LogCount) this.sqlMapClientTemplate.queryForObject(
				"logCount.selectCount", map);
	}

	public void insertCount(LogCount logCount) {
		this.sqlMapClientTemplate.insert("logCount.insert", logCount);
	}

}
