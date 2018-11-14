package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.gdssoft.cqjt.pojo.report.GovCheckReport;
import com.gdssoft.cqjt.pojo.report.GovSiteCheckReport;

/**
 * 政务信息统计
 * @author acer
 *
 */
public interface TextNewsReportService {

	/**
	 * 统计政务考核信息
	 * @param start
	 * @param end
	 * @param status
	 * @return
	 */
	public List<GovCheckReport> queryGovInfoCheckList(Date start, Date end, String status);
	
	/**
	 * 导出政务考核统计excel
	 * @param start
	 * @param end
	 * @param status
	 * @return
	 */
	public void exportExcelGovInfoCheckList(HttpServletResponse response, List<GovCheckReport> govCheckReportList, String startDate, String endDate);
	
	/**
	 * 考核政务信息统计，计算考核数据，及累计数据
	 * @param start
	 * @param end
	 * @param status
	 */
	public void govCheckReportList(Date start, Date end, String status);
	
	/**
	 * 查询统计
	 * @param beginDate
	 * @param endDate
	 * @param Category
	 * @return
	 */
	public List<GovSiteCheckReport> queryGovSiteCheckReportStat(Date beginDate, Date endDate, String category);
	
	public void queryGovSiteCheckStat(Model model, int type);
}
