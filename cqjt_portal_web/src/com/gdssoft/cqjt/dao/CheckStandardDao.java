package com.gdssoft.cqjt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.CheckStandard;

@Service("checkStandardDao")
public class CheckStandardDao {


	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<CheckStandard> getCheckStandarList(int pageIndex, int pageSize) {
		return (List<CheckStandard>) this.sqlMapClientTemplate
				.queryForList("CheckStandard.getStandardInfo",pageIndex,pageSize);
	}

	public CheckStandard getCheckStandardDetail(String checkCode) {
        Map<String,String> map = new HashMap<String,String>();
		map.put("checkCode", checkCode);
		return (CheckStandard)this.sqlMapClientTemplate.queryForObject("CheckStandard.getStandardInfo", map);
	}
	
	/**
	 * 根据类型查询分数
	 * @param checkCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CheckStandard> queryCheckStandard(String[] codes) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("codes", codes);
		return (List<CheckStandard>) this.sqlMapClientTemplate.queryForList("CheckStandard.queryCheckStandard", map);
	}

	public void save(CheckStandard checkStandard) {
		this.sqlMapClientTemplate.insert("CheckStandard.insertStandard", checkStandard);
	}

	public void update(CheckStandard checkStandard) {
		this.sqlMapClientTemplate.delete("CheckStandard.updateStandard", checkStandard);
	}

	public void delete(String checkId) {
		this.sqlMapClientTemplate.delete("CheckStandard.deleteStandardById", checkId);
	}
	
	
	public CheckStandard getCode(String code) {
    	return (CheckStandard)this.sqlMapClientTemplate.queryForObject("CheckStandard.getSInfo",code);
				
	}

}
