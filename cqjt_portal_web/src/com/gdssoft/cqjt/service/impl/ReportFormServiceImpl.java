package com.gdssoft.cqjt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.CheckStandardDao;
import com.gdssoft.cqjt.dao.ReportFormDao;
import com.gdssoft.cqjt.pojo.report.AccountReport;
import com.gdssoft.cqjt.pojo.report.GovCheckReport;
import com.gdssoft.cqjt.pojo.report.siteCheckReport;
import com.gdssoft.cqjt.pojo.report.SiteReport;
import com.gdssoft.cqjt.service.ReportFormService;

@Service("reportFormServiceImpl")
public class ReportFormServiceImpl implements ReportFormService {

	@Resource(name = "reportFormDao")
	private ReportFormDao reportFormDao;
	
	@Resource(name = "checkStandardDao")
	private CheckStandardDao checkStandardDao;
	
	@Override
	public List<GovCheckReport> getGovCheckReportByDept(String deptName,String startDate, String endDate) {
		return reportFormDao.getGovCheckReportByDept(deptName,startDate,endDate);
	}
	@Override
	public List<GovCheckReport> getGovCheckReportByDept(String deptName,Date startDate, Date  endDate) {
		List<GovCheckReport> govCheckReportList = reportFormDao.getGovCheckReportByDept(deptName,startDate,endDate);
		Float a=checkStandardDao.getCheckStandardDetail("sendGov").getScore();
		for(GovCheckReport govCheckReport : govCheckReportList){
			govCheckReport.setScore(govCheckReport.getScore()+a);
		}
		return govCheckReportList;
	}

	@Override
	public List<GovCheckReport> getGovCheckReportByDate(String startDate,
			String endDate,String status) {
		return reportFormDao.getGovCheckReportByDate(startDate , endDate,status);
	}
	@Override
	public List<GovCheckReport> getGovCheckReportByDate(Date startDate,
			Date endDate,String status) {
		List<GovCheckReport> govCheckReportList = reportFormDao.getGovCheckReportByDate(startDate , endDate,status);
		Float a=checkStandardDao.getCheckStandardDetail("sendGov").getScore();
		int b = 0;
		for(GovCheckReport govCheckReport : govCheckReportList){
			if(govCheckReport.getAdoptNum()!=null){
				b = Integer.parseInt(govCheckReport.getAdoptNum());
				govCheckReport.setScore(govCheckReport.getScore()+a*b);
			}
		}
		return govCheckReportList;
	}


	
	

	/**
	 * 默认查询网站考核信息
	 */
	@Override
	public List<siteCheckReport> getSiteCheckReportByDept() {
		
		return reportFormDao.getSiteCheckReportByDept(new String[]{},"", null, null);
	}
	/**
	 * 按照时间查询网站考核信息
	 */
	@Override
	public List<siteCheckReport> getSiteCheckReportByDept(Date createDateS,
			Date createDateE) {
		
		return reportFormDao.getSiteCheckReportByDept(new String[]{}, "",createDateS, createDateE);
	}
	
	@Override
	public List<SiteReport> getSiteReportByDept(String deptName,String startDate, String endDate) {
		return reportFormDao.getSiteReportByDept(deptName,startDate,endDate);
	}

	@Override
	public List<SiteReport> getSiteReportByDate(String startDate,
			String endDate, String status) {
		return reportFormDao.getSiteReportByDate(startDate , endDate,status);
	}
	/**
	 * 条件查询   按照部门, 查询网站考核信息统计报表   update by gyf 20141119
	 */
	@Override
	public List<siteCheckReport> getSiteCheckReportByDept(String[] deptCategory,String deptId,
			Date createDateS, Date createDateE) {
		//List<siteCheckReport> reportListByDepttemp = new ArrayList<siteCheckReport>();
		List<siteCheckReport> reportListByDept = new ArrayList<siteCheckReport>();
		reportListByDept = reportFormDao.getSiteCheckReportByDept(deptCategory,deptId, createDateS, createDateE);
		//去除重复数据
		/*String[] title;
		String titles = "";
		Float score = 0f;
		for(siteCheckReport a : reportListByDepttemp){
			if(a.getAdoptType().equals("isPublic")){
				titles += a.getNewsId() + ",";
				score = a.getScore();
			}else{
				reportListByDept.add(a);
			}
		}
		title = titles.split(",");
		for(int a = 0; a < title.length; a++){
			for(siteCheckReport aa : reportListByDept){
				if(aa.getNewsId().equals(title[a])){
					aa.setScore(score+aa.getScore());
					aa.setAdoptType("isPublic");
					break;
				}
			}
		}
      */
		return reportListByDept;
	}
	/**
	 * 按照部门查询网站考核信息统计报表
	 */
	@Override
	public List<siteCheckReport> getSiteCheckReportByDept(String[] deptCategory) {
		
		return  reportFormDao.getSiteCheckReportByDept(deptCategory,"", null, null);
	}
	
	/**
	 * 条件查询 按照部门 查询网站信息考核统计报表
	 */
	@Override
	public List<siteCheckReport> getSiteCheckReportByDept(String[] deptCategory,
			Date createDateS, Date createDateE) {
		return reportFormDao.getSiteCheckReportByDept(deptCategory,"", null, null);
	}

	/**
	 *  查询网站信息考核统计报表 
	 */
	@Override
	public List<siteCheckReport> getSiteCheckReport(Date createDateS,
			Date createDateE,String status) {
		return reportFormDao.getSiteCheckReport(new String[]{},createDateS,createDateE,status);
	}
	
	
	/**
	 * 根据部门名和日期查询出台账信息包括：各个部门采用信息的标题，创建时间，采用类别
	 * @author H2603282  20140703
	 * @param startDate
	 * @param endDate
	 * @param deptName
	 * @return
	 */
	@Override
	public List<AccountReport> getAccountReportByDept(String deptName,String startDate, String endDate) {
		return reportFormDao.getAccountReportByDept(deptName,startDate,endDate);
	}
	
	@Override
	public List<AccountReport> getAccountReport(String deptName,Date startDate, Date endDate) {
		return reportFormDao.getAccountReportByDept(deptName,startDate,endDate);
	}
	
	/**
	 * 根据部日期查询出台账信息包括：部门信息以及合计金额
	 * @author H2603282  20140703
	 * @param startDate
	 * @param endDate
	 * @param deptName
	 * @return
	 */
	@Override
	public List<AccountReport> getAccountReportByDate(String startDate,
			String endDate, String status) {
		return reportFormDao.getAccountReportByDate(startDate , endDate,status);
	}
	@Override
	public List<AccountReport> getAccountReport(Date startDate,
			Date endDate, String status) {
		return reportFormDao.getAccountReport(startDate , endDate,status);
	}	
		
}
