package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextGovReport;
import com.gdssoft.cqjt.pojo.report.GovNewsReport;

@Service("textGovInfoDao")
public class TextGovInfoDao {
	
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	public TextGovInfo getTextGovInfo(String giId) {
		return (TextGovInfo) this.sqlMapClientTemplate.queryForObject(
				"TextGovInfo.getTextGovInfo", giId);
	}
	public TextGovInfo getTextInfo(String newsId) {
		return (TextGovInfo) this.sqlMapClientTemplate.queryForObject(
				"TextGovInfo.getTextInfo", newsId);
	}
	/**
	 * zhangpeng 20140604 //		修改Map<String,String> map = new HashMap<String,String>();
	 * 获取所有政务信息list             为  Map<String,Object> map = new HashMap<String,Object>(); H2602965 20140612
	 * @param newsId
	 */
	@SuppressWarnings("unchecked")
	public List<TextGovInfo> getTextGovInfo(String newsTitle, Date entryDateS, Date entryDateE, String status, String isDel,int pageIndex, int pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		String[] isReport = {"0","1"};
		map.put("newsTitle", newsTitle);
		map.put("entryDateS", entryDateS);
		map.put("entryDateE", entryDateE);
		map.put("status", status);
		map.put("isDel", isDel);
		map.put("isReport", isReport);
		
		return (List<TextGovInfo>) sqlMapClientTemplate
				.queryForList("TextGovInfo.getTextGovInfoList", map, pageIndex, pageSize);
	}


	public void insertGovInfo(TextGovInfo govInfo) {
		this.sqlMapClientTemplate.insert("TextGovInfo.insertTextGovInfo",govInfo);
		
	}
	
	/**
	 * 政务信息上报管理编辑模块 编辑页面初始化
	 * @author H2602965 2014.06.05
	 * getInfoReportDetail获取详细信息提供给编辑页面初始化
	 * @param TextGovInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextGovInfo> getTextGovInfoByNewsId(String newsId) {
		return (List<TextGovInfo>) this.sqlMapClientTemplate.queryForList(
				"TextGovInfo.getTextGovInfoByNewsId", newsId);
	}
	@SuppressWarnings("unchecked")
	public List<TextGovInfo> getTextGovInfoListByNewsId(String newsId) {
		return (List<TextGovInfo>) this.sqlMapClientTemplate.queryForList(
				"TextGovInfo.getTextGovInfoListByNewsId", newsId);
	}
	/**
	 * 政务信息上报管理编辑模块 页面保存功能
	 * @author H2602965 H2602965 2014.06.05
	 * @param govInfo
	 */
	public void update(TextGovInfo textGovInfo){
		this.sqlMapClientTemplate.update("TextGovInfo.updateInfoReport", textGovInfo);
	}
	/**
	 * 政务信息上报删除功能  H2602992 20140606
	 * @param textGovInfo
	 */
	public void deleteInfoReport(TextGovInfo textGovInfo){
		this.sqlMapClientTemplate.update("TextGovInfo.deleteInfoReport", textGovInfo);
	}


	@SuppressWarnings("unchecked")
	public List<TextGovInfo> getGovListByIdAndType(String pubId, String pubType) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("pubId", pubId);
		map.put("pubType", pubType);
		
		return (List<TextGovInfo>) this.sqlMapClientTemplate
				.queryForList("TextGovInfo.getGovByIdAndTyep",map);
	}


	public void savePubDetail(String giId, String sequenceNum) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("giId", giId);
		map.put("sequenceNum", sequenceNum);
		this.sqlMapClientTemplate.update("TextGovInfo.updateSequenceNum", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<TextGovInfo> getUnPubTextGovInfoList(String titleKey, Date startDate, Date endDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("titleKey", titleKey);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return (List<TextGovInfo>) this.sqlMapClientTemplate
				.queryForList("TextGovInfo.getUnPubTextGovInfoList", map);
	}
	/**
	 * 用于政务信息报表查询
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextGovReport> queryGovReportStat(Date startDate, Date endDate, String status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("status", status);
		
		return (List<TextGovReport>) this.sqlMapClientTemplate.queryForList("TextGovInfo.queryGovReportStat",map);
	}
	
	/**
	 * 政务信息统计查询
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextGovReport> getGovReport(Date startDate, Date endDate, String status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("status", status);
		
		return (List<TextGovReport>) this.sqlMapClientTemplate
				.queryForList("TextGovInfo.getGovReport",map);
	}
	
	/**
	 * 用于政务信息报表查询
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TextGovReport> getGovReportNum(Date startDate, Date endDate, String status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("status", status);
		
		return (List<TextGovReport>) this.sqlMapClientTemplate
				.queryForList("TextGovInfo.getGovReportNum",map);
	}
	
	/**
	 * 刊物编辑对应政务信息删除   gyf 2014.07.23
	 * @param textGovInfo
	 */
	public void deleteGovPubRef(TextGovInfo textGovInfo){
		this.sqlMapClientTemplate.update("TextGovInfo.deleteGovPubRef", textGovInfo);
	}
	/**
	 * 政务信息回收站复原
	 * @param textGovInfo
	 */
	public void reUpdate(TextGovInfo textGovInfo){
		this.sqlMapClientTemplate.update("TextGovInfo.reUpdate", textGovInfo);
	}
	/**
	 * 彻底删除政务信息
	 * @param textGovInfo
	 */
	public void deleteTextNews(TextGovInfo textGovInfo) {
		this.sqlMapClientTemplate.delete("TextGovInfo.deleteTextNews", textGovInfo);
	}
	/**
	 * 彻底删除政务信息
	 * @param newsId
	 */
	public void delGovInfoByNewsId(String newsId) {
		this.sqlMapClientTemplate.delete("TextGovInfo.delGovInfo", newsId);
	}
	/**
	 * 彻底删除政务信息
	 * @param newsId
	 */
	public void delGovInfoById(String id) {
		this.sqlMapClientTemplate.delete("TextGovInfo.delGovInfoById", id);
	}
	/**
	 * 查询政务信息
	 * @param textGovInfo
	 */
	public TextGovInfo getTextGov(String newsId) {
		return (TextGovInfo) this.sqlMapClientTemplate.queryForObject(
				"TextGovInfo.getTextGov", newsId);
	}
	/**
	 * 查询政务信息对应的刊物
	 * @param textGovInfo 以及 pubName
	 */
	@SuppressWarnings("unchecked")
	public List<TextGovInfo> getGov(String pubId) {
		return (List<TextGovInfo>) this.sqlMapClientTemplate.queryForList(
				"TextGovInfo.getGov", pubId);
	}
	
	@SuppressWarnings("unchecked")
	public List<GovNewsReport> queryGovNewsReport(Date startDate, Date endDate, String status, Integer isUse){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("status", status);
		map.put("isUse", isUse);
		return (List<GovNewsReport>) this.sqlMapClientTemplate.queryForList(
				"TextGovInfo.queryGovNewsReport", map);
	}
	public List<Map<String, Object>> queryDeptShangbaoStat(Date startDate, Date endDate, String status, Integer isUse){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("status", status);
		map.put("isUse", isUse);
		return this.sqlMapClientTemplate.queryForListMap("TextGovInfo.queryDeptShangbaoStat", map);
	}
	
	/**
	 * 政务信息报送和政务信息采编同步内容
	 * @param textGovInfo
	 */
	public void syncGovInfoContent(TextGovInfo textGovInfo){
		this.sqlMapClientTemplate.update("TextGovInfo.syncGovInfoContent", textGovInfo);
	}
	
}

