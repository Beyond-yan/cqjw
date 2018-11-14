package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.report.AccountReport;
import com.gdssoft.cqjt.pojo.report.GovCheckReport;
import com.gdssoft.cqjt.pojo.report.siteCheckReport;
import com.gdssoft.cqjt.pojo.report.SiteReport;

@Service("reportFormDao")
public class ReportFormDao {


	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<GovCheckReport> getGovCheckReportByDept(String deptName,String startDate, String endDate) {
	    Map<String,String> map = new HashMap<String,String>();
	    map.put("startDate", startDate+" 00:00:00");
		map.put("endDate", endDate+" 23:59:59");
		map.put("deptName", deptName);
		return (List<GovCheckReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getGovCheckReportByDept", map);
	}
	@SuppressWarnings("unchecked")
	public List<GovCheckReport> getGovCheckReportByDept(String deptName,Date startDate, Date endDate) {
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("deptName", deptName);
		return (List<GovCheckReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getGovCheckReportByDept", map);
	}
	@SuppressWarnings("unchecked")
	public List<GovCheckReport> getGovCheckReportByDate(String startDate, String endDate,String status) {
	    Map<String,String> map = new HashMap<String,String>();
		map.put("startDate", startDate+" 00:00:00");
		map.put("endDate", endDate+" 23:59:59");
		map.put("status", status);
		return (List<GovCheckReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getGovCheckReportByDate", map);
	}
	@SuppressWarnings("unchecked")
	public List<GovCheckReport> getGovCheckReportByDate(Date startDate, Date endDate,String status) {
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("status", status);
		return (List<GovCheckReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getGovCheckReportByDate", map);
	}

	/**
	 * 网站信息考核统计报表  查询出采用标题,得分,采用类别后三项内容
	 * @Data 20140630
	 * @author H2602965
	 * @param deptCategory
	 * @param createDateS
	 * @param createDateE
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<siteCheckReport> getSiteCheckReportByDept(String[] deptCategory,String deptId,
		Date createDateS, Date createDateE) {
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("deptCategory", deptCategory);
	    map.put("deptId", deptId);
	    map.put("createDateS", createDateS);
	    map.put("createDateE", createDateE);
	  
		return (List<siteCheckReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getSiteCheckReportByDept", map);
	}
	
	/**
	 * 网站信息考核统计报表 查询出 网站信息考核统计报表 分类,部门,总分,上报条数,前四项内容
	 * @author H2602965
	 * @param deptCategory
	 * @param createDateS
	 * @param createDateE
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<siteCheckReport> getSiteCheckReport(String[] deptCategory,
			Date createDateS, Date createDateE,String status) {
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("deptCategory", deptCategory);
	    map.put("createDateS", createDateS);
	    map.put("createDateE", createDateE);
	    map.put("status", status);
		return (List<siteCheckReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getSiteCheckReport", map);
	}
	@SuppressWarnings("unchecked")
	public List<SiteReport> getSiteReportByDept(String deptName,String startDate, String endDate) {
	    Map<String,String> map = new HashMap<String,String>();
	    map.put("startDate", startDate+" 00:00:00");
		map.put("endDate", endDate+" 23:59:59");
		map.put("deptName", deptName);
		return (List<SiteReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getSiteReportFormByDept", map);
	}

	@SuppressWarnings("unchecked")
	public List<SiteReport> getSiteReportByDate(String startDate, String endDate,String status) {
	    Map<String,String> map = new HashMap<String,String>();
		map.put("startDate", startDate+" 00:00:00");
		map.put("endDate", endDate+" 23:59:59");
		map.put("status", status);
		return (List<SiteReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getSiteReportFormByDate", map);
	}
	
	
	/**
	 * 根据日期查询出台账信息包括：部门信息以及合计金额
	 * @author H2603282  20140703
	 * @param startDate
	 * @param endDate
	 * @param deptName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AccountReport> getAccountReportByDate(String startDate,
			String endDate,String status) {
	    Map<String,String> map = new HashMap<String,String>();
	    map.put("startDate", startDate+" 00:00:00");
		map.put("endDate", endDate+" 23:59:59");
		map.put("status", status);
		return (List<AccountReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getAccountReportByDate", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountReport> getAccountReport(Date startDate,
			Date endDate,String status) {
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("status", status);
		return (List<AccountReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getAccountReportByDate", map);
	}
	
	
	/**
	 * 根据部门名和日期查询出台账信息包括：各个部门采用信息的标题，创建时间，采用类别
	 * @author H2603282  20140703
	 * @param startDate
	 * @param endDate
	 * @param deptName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AccountReport> getAccountReportByDept(String deptName,String startDate, String endDate) {
	    Map<String,String> map = new HashMap<String,String>();
	    map.put("startDate", startDate+" 00:00:00");
		map.put("endDate", endDate+" 23:59:59");
		map.put("deptName", deptName);
		return (List<AccountReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getAccountReportByDept", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountReport> getAccountReportByDept(String deptName,Date startDate,
			Date endDate) {
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("deptName", deptName);
		return (List<AccountReport>) this.sqlMapClientTemplate
				.queryForList("ReportForm.getAccountReportByDept", map);
	}
}

