package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.pojo.TextPublication;
import com.gdssoft.core.dao.MySqlMapClientTemplate;

@Service("textPublicationDao")
public class TextPublicationDao {

	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * 获取刊物信息list
	 * 
	 * @param newsId
	 */
	/*
	 * @SuppressWarnings("unchecked") public List<TextPublication>
	 * getTextPublication() { return
	 * this.sqlMapClientTemplate.queryForList("TextPublication.getPubList"); }
	 */

	/**
	 * 刊物管理获取详细信息 返回一条数据 2014.06.07 添加return条件 H2602965 wl
	 * 
	 * @author H2602965
	 * @param pubId
	 * @return
	 */
	public TextPublication getPubDetail(String pubId) {
		return (TextPublication) this.sqlMapClientTemplate.queryForObject(
				"TextPublication.getPubList", pubId);
	}

	public void save(TextPublication textPublication) {
		this.sqlMapClientTemplate.insert("TextPublication.insertPubInfo",
				textPublication);
	}

	/**
	 * 刊物管理模块 更新功能]
	 * 
	 * @author H2602965 2014.06.07
	 * @param textPublication
	 */
	public void update(TextPublication textPublication) {
		this.sqlMapClientTemplate.update("TextPublication.updatePubInfo",
				textPublication);
	}

	public void delete(TextPublication textPublication) {
		this.sqlMapClientTemplate.update("TextPublication.deletePubInfo",
				textPublication);
	}

	@SuppressWarnings("unchecked")
	public List<TextPublication> queryPubInfoList(String pubName,
			String createUser, Date createDateS, Date createDateE) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pubName", pubName);
		map.put("createUser", createUser);
		map.put("createDateS", createDateS);
		map.put("createDateE", createDateE);
		return (List<TextPublication>) this.sqlMapClientTemplate.queryForList(
				"TextPublication.queryPubInfoList", map);
	}

	/**
	 * 首页每日信息，以及每日信息详情页左侧边栏 列表显示
	 * @param isPublic
	 * @param pubName
	 * @param createUser
	 * @param createDateS
	 * @param createDateE
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<TextPublication> queryPubInfoList(String isPublic,
			String pubName, String createUser, Date createDateS,
			Date createDateE, int pageIndex, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pubName", pubName);
		map.put("createUser", createUser);
		map.put("createDateS", createDateS);
		map.put("createDateE", createDateE);
		map.put("createDateE", createDateE);
		map.put("isPublic", isPublic);

		return (List<TextPublication>) sqlMapClientTemplate.queryForList(
				"TextPublication.queryPubInfoList", map, pageIndex, pageSize);

	}


	public TextPublication getPubList(String pubId) {
		return (TextPublication) this.sqlMapClientTemplate.queryForObject(
				"TextPublication.getPubByPubId", pubId);
	}

	/**
	 * 彻底删除刊物信息
	 * 
	 * @param textGovInfo
	 */
	public void deleteTextNews(TextPublication textPublication) {
		this.sqlMapClientTemplate.delete("TextPublication.deleteTextNews",
				textPublication);
	}
	/**
	 * 彻底删除刊物信息
	 * 
	 * @param textGovInfo
	 */
	public void deleteTextNewsById(String id) {
		this.sqlMapClientTemplate.delete("TextPublication.deleteTextNewsById",
				id);
	}

	/**
	 * 刊物管理获取详细信息 返回一条数据 2014.09.04 添加return条件 H2602965 wl
	 * 
	 * @author H2602965
	 * @param pubId
	 * @return
	 */
	public TextPublication getPub(String pubId) {
		return (TextPublication) this.sqlMapClientTemplate.queryForObject(
				"TextPublication.getPub", pubId);
	}

}
