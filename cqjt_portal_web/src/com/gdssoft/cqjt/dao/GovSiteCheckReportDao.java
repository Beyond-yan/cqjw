package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.report.GovSiteCheckReport;

@Service("govSiteCheckReportDao")
public class GovSiteCheckReportDao {
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	public int insertGovSiteCheckReport(GovSiteCheckReport report) {
		Object obj = this.sqlMapClientTemplate.insert("govSiteCheckReport.insertGovSiteCheckReport",report);
		int result =  0;
		if(obj != null){
//			result = Integer.parseInt(obj.toString());
		}
		System.out.println("--insert---->"+obj+","+result);
		return result;
	}
	public int updateGovCheckReport(GovSiteCheckReport report) {
		int result =  this.sqlMapClientTemplate.update("govSiteCheckReport.updateGovCheckReport",report);
		System.out.println("--update---->"+result);
		return result;
	}
	public int updateSiteCheckReport(GovSiteCheckReport report) {
		int result =  this.sqlMapClientTemplate.update("govSiteCheckReport.updateSiteCheckReport",report);
		System.out.println("--update---->"+result);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<GovSiteCheckReport> queryGovSiteCheckReportList(Date beginDate, Date endDate, String category) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("category", category);
		return (List<GovSiteCheckReport>) this.sqlMapClientTemplate.queryForList(
				"govSiteCheckReport.govSiteCheckReportList", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<GovSiteCheckReport> queryGovSiteCheckReportStat(Date beginDate, Date endDate, String category) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("category", category);
		return (List<GovSiteCheckReport>) this.sqlMapClientTemplate.queryForList(
				"govSiteCheckReport.queryGovSiteCheckReportStat", param);
	}
	
}
