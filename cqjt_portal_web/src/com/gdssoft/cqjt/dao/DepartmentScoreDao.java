package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.DepartmentScore;

@Service("deptScoreDao")
public class DepartmentScoreDao {
	
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	public DepartmentScore getDeptScoreDetail(String scoreId) {
		return (DepartmentScore) this.sqlMapClientTemplate.queryForObject("DeptScore.getDeptScoreDetail", scoreId);
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentScore> getDeptScoreList(String deptName, String deptId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("deptName", deptName);
		map.put("deptId", deptId);
		return (List<DepartmentScore>) this.sqlMapClientTemplate
				.queryForList("DeptScore.getDeptScoreList", map);
	}

	public void save(DepartmentScore deptScoreObj) {
		this.sqlMapClientTemplate.insert("DeptScore.insertDeptScore", deptScoreObj);
	}
	/**
	 * 根据新闻ID删除积分
	 * @param deptScoreObj
	 */
	public void delDeptScore(String newId) {
		this.sqlMapClientTemplate.insert("DeptScore.delDeptScore", newId);
	}
	
	/**
	 * 根据新闻ID删除刊物积分
	 * @param deptScoreObj
	 */
	public void delbyScoreDeptScore(String newId) {
		this.sqlMapClientTemplate.insert("DeptScore.delbyScoreDeptScore", newId);
	}

	public void update(DepartmentScore deptScoreObj) {
		this.sqlMapClientTemplate.update("DeptScore.updateDeptScore", deptScoreObj);
	}
	
	@SuppressWarnings("unchecked")
	public List<DepartmentScore> getScoreSumList(String scoreType, Date createDateS, Date createDateE) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("scoreType", scoreType);
		map.put("createDateS", createDateS);
		map.put("createDateE",createDateE);
		return (List<DepartmentScore>) this.sqlMapClientTemplate
				.queryForList("DeptScore.getScoreSumList", map);
	}
	public List<DepartmentScore> queryScoreByCodeAndNewsId(String scoreType, String newsId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("scoreType", scoreType);
		map.put("newsId", newsId);
		return (List<DepartmentScore>) this.sqlMapClientTemplate.queryForList("DeptScore.queryScoreByCodeAndNewsId", map);
	}


}
