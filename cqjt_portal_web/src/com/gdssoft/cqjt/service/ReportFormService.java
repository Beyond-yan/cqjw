package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.report.AccountReport;
import com.gdssoft.cqjt.pojo.report.GovCheckReport;
import com.gdssoft.cqjt.pojo.report.siteCheckReport;
import com.gdssoft.cqjt.pojo.report.SiteReport;
/**
 * 统计分析报表管理service
 * @author H2602975 zhpeng 20140630
 */

public interface ReportFormService {
	
	/**
	 * 根据部门名查询出政务考核信息包括：各个部门采用信息的标题，每条信息得分，采用类别
	 * @author H2602975 20140630
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<GovCheckReport> getGovCheckReportByDept(String deptName ,String startDate,String endDate);
	public List<GovCheckReport> getGovCheckReportByDept(String deptName ,Date startDate,Date endDate);
	/**
	 * 根据日期查询出投稿日期在此范围内的政务考核信息包括：分类，部门，总采用条数，总的分
	 * @author H2602975 20140630
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<GovCheckReport> getGovCheckReportByDate(String startDate,String endDate,String status);
	public List<GovCheckReport> getGovCheckReportByDate(Date startDate,Date endDate,String status);
	/**
	 * 默认查询网站考核信息统计分析报表
	 * @author H2602965
	 * @return
	 */
	public List<siteCheckReport> getSiteCheckReportByDept();
	
	
	public List<SiteReport> getSiteReportByDept(String deptName ,String startDate,String endDate);
	public List<SiteReport> getSiteReportByDate(String startDate,String endDate, String status);
	/**
	 * 按照部门类型查询网站考核信息统计分析报表
	 * @param deptCategory
	 * @return
	 */
	public List<siteCheckReport> getSiteCheckReportByDept(String[] deptCategory);
	/**
	 * 根据日期查询网站考核信息统计分析报表
	 * @author H2602965 
	 * @param createDateS
	 * @param createDateE
	 * @return
	 */
	public List<siteCheckReport> getSiteCheckReportByDept(Date createDateS,Date createDateE);
	
	/**
	 * 条件查询网站信息考核统计分析报表 
	 * @author H2602965
	 * @param deptCategory
	 * @param deptName
	 * @param createDateS
	 * @param createDateE
	 * @return
	 */
	public List<siteCheckReport> getSiteCheckReportByDept(String[] deptCategory,String deptId,Date createDateS,Date createDateE);
	
	/**
	 * 条件查询网站信息考核统计分析报表
	 * @author H2602965
	 * @param deptCategory
	 * @param createDateS
	 * @param createDateE
	 * @return
	 */
	public List<siteCheckReport> getSiteCheckReportByDept(String[] deptCategory,Date createDateS,Date createDateE);
    /**
	 * 根据日期查询出投稿日期在此范围内的台账信息
	 * @author 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
    public List<AccountReport> getAccountReportByDate(String startDate,String endDate, String status);
    public List<AccountReport> getAccountReport(Date startDate,Date endDate, String status);
    
    /**
	 * 根据部门名查询出政务考核信息包括：各个部门采用信息的标题，每条信息得分，采用类别
	 * @author  20140703
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<AccountReport> getAccountReportByDept(String deptName,String startDate,String endDate);
	public List<AccountReport> getAccountReport(String deptName,Date startDate,Date endDate);
    
    /**
     * 根据日期查询出 网站信息考核统计报表 分类,部门,总分,上报条数,前四项内容
     * @author H2602965
     * @param createDateS
     * @param createDateE
     * @return
     */
	public List<siteCheckReport> getSiteCheckReport(Date createDateS,Date createDateE,String status);
}
