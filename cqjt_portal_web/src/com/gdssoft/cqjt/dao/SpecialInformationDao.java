package com.gdssoft.cqjt.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.TextGovInfo;

@Service("specialInformationDao")
public class SpecialInformationDao extends BaseDao{
	
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<TextGovInfo> getGovNewsList(Map<String, Object> map, int pageIndex, int pageSize){
		return (List<TextGovInfo>) sqlMapClientTemplate.queryForList(
				"SpecialInformationDao.queryPageList", map, pageIndex, pageSize);
	}
}
