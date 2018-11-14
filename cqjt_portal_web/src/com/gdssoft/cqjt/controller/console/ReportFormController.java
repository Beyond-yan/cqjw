package com.gdssoft.cqjt.controller.console;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.JsonUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.TextGovReport;
import com.gdssoft.cqjt.pojo.report.AccountReport;
import com.gdssoft.cqjt.pojo.report.GovCheckReport;
import com.gdssoft.cqjt.pojo.report.GovNewsReport;
import com.gdssoft.cqjt.pojo.report.GovSiteCheckReport;
import com.gdssoft.cqjt.pojo.report.SiteReport;
import com.gdssoft.cqjt.pojo.report.siteCheckReport;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.ReportFormService;
import com.gdssoft.cqjt.service.TextGovInfoService;
import com.gdssoft.cqjt.service.TextNewsReportService;
import com.gdssoft.cqjt.util.ApiUtils;

@Controller
@RequestMapping("/console/reportForm.xhtml")
public class ReportFormController extends BaseController {
	@Autowired
	@Resource(name = "reportFormServiceImpl")
	private ReportFormService reportFormService;

	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;
	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	
	@Autowired
	@Resource(name = "textNewsReportServiceImpl")
	private TextNewsReportService textNewsReportService;

	/**
	 * 政务信息考核统计报表跳转
	 * 
	 * @author H2602975 zhpeng 20140630
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getGovCheckReportView")
	public String queryGovReport(Model model) {
		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.MONTH,-1);
		Date start = calendar.getTime();
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "console/reportForm/govCheckReportForm";
	}

	/**
	 * 根据部门名和日期查询出政务考核信息包括：各个部门采用信息的标题，每条信息得分，采用类别
	 * 
	 * @author H2602975 zhpeng 20140630
	 * @param startDate
	 * @param endDate
	 * @param deptName
	 * @return
	 */
	@RequestMapping(params = "action=queryGovCheckReportByDept")
	public void queryGovReportListByDeptName(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("deptName") String deptName,
			HttpServletResponse response,HttpServletRequest request) {
		Date start = this.getStartDate(startDate);
	    Date end = this.getEndDate(endDate);
		List<GovCheckReport> govCheckReportDetailList = new ArrayList<GovCheckReport>();
		govCheckReportDetailList = reportFormService.getGovCheckReportByDept(deptName, start, end);
		//TODO:根据部门查询
		for(GovCheckReport gcr : govCheckReportDetailList){
			if(gcr.getDeptName().equals("委领导")){
				govCheckReportDetailList.remove(gcr);
			}
			break;
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JsonUtil.toJson(govCheckReportDetailList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据日期查询出投稿日期在此范围内的政务考核信息包括：分类，部门，总采用条数，总的分
	 * @author H2602975 zhpeng 20140630
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(params = "action=queryGovCheckReportByDate")
	public void queryGovReportList(
			HttpServletResponse response,HttpServletRequest request) {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String status = request.getParameter("status");
		if("全部".equals(status)){
			status="";
		}
		logger.debug(startDate+","+endDate+",status:"+status);
		Date start = this.getStartDate(startDate);
	    Date end = this.getEndDate(endDate);
//		List<GovCheckReport> govCheckReportList = new ArrayList<GovCheckReport>();
//		govCheckReportList = reportFormService.getGovCheckReportByDate(start,end,status);
//		//TODO:查询政务考核信息GovCheckReport
//		for(GovCheckReport gcr : govCheckReportList){
//			if(gcr.getDeptName().equals("委领导")){
//				govCheckReportList.remove(gcr);
//			}
//			break;
//		}
		List<GovCheckReport> govCheckReportList = textNewsReportService.queryGovInfoCheckList(start, end, status);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JsonUtil.toJson(govCheckReportList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 网站信息统计报表页面跳转 温长峰H2602992 20140626
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getSiteInfoReportList")
	public String getSiteReportList(Model model) {
		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.MONTH,-1);
		Date start = calendar.getTime();
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "console/reportForm/siteReportForm";
	}

	@RequestMapping(params = "action=querySiteReportByDate")
	public void querySiteReportList(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			HttpServletResponse response,HttpServletRequest request) {
		String status = request.getParameter("status");
		if(status.equals("全部")){
			status="";
		}
		logger.debug(startDate+","+endDate+",status:"+status);
		List<SiteReport> siteReportList = new ArrayList<SiteReport>();
		siteReportList = reportFormService.getSiteReportByDate(startDate,
				endDate,status);
		for(SiteReport gcr : siteReportList){
			if(gcr.getDeptName().equals("委领导")){
				siteReportList.remove(gcr);
			}
			break;
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JsonUtil.toJson(siteReportList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(params = "action=querySiteReportByDept")
	public void querySiteListByDeptName(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("deptName") String deptName,
			HttpServletResponse response,HttpServletRequest request) {
		
		List<SiteReport> siteReportDetailList = new ArrayList<SiteReport>();
		siteReportDetailList = reportFormService.getSiteReportByDept(deptName,
				startDate, endDate);
		
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JsonUtil.toJson(siteReportDetailList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 网站信息考核统计报表跳转
	 * @author H2602965
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getSiteCheckReportView")
	public String queryCheckSiteReport(Model model) {
		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.MONTH,-1);
		Date start = calendar.getTime();
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "console/reportForm/siteCheckReportForm";
	}
	/**
	 * 网站信息考核统计报表 默认查询   默认查询 前四项的内容,包括分类,部门,上报条数,得分总计
	 * @author H2602965
	 * @param response
	 * @param request
	 * @param model
	 */
	@RequestMapping(params="action=getSiteCheckReport")
	public void getSiteCheckReport(HttpServletResponse response,HttpServletRequest request,Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createDateS = request.getParameter("createDateS");
		String createDateE = request.getParameter("createDateE");
		String status = request.getParameter("status");
		logger.debug("传入参数:createDateS="+createDateS+":createDateE="+createDateE+":status="+status);
		if(status.equals("全部")){
			status="";
		}
		Date start = null;Date end = null;
		if (createDateS != null && !"".equals(createDateS)) {
			try {
				 start = (Date) sdf.parse(createDateS+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if (createDateE != null && !"".equals(createDateE)) {
			try {
				 end = (Date) sdf.parse(createDateE+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		logger.debug(createDateS+","+createDateE);
		List<siteCheckReport> reportList=reportFormService.getSiteCheckReport(start, end,status);
		for(siteCheckReport gcr : reportList){
			if(gcr.getDeptName().equals("委领导")){
				reportList.remove(gcr);
			}
			break;
		}
		this.writeResult(response, reportList);
	}
	/**
	 * 网站信息考核统计报表,使用部门ID查询出标题,得分,发布渠道后三项内容
	 * @author H2602965
	 * @param response
	 * @param request
	 */
	@RequestMapping(params="action=getSiteCheckReportByDept")
	public void getSiteCheckReportByDept(HttpServletResponse response,HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createDateS = request.getParameter("createDateS");
		String createDateE = request.getParameter("createDateE");
		String deptId = request.getParameter("deptId");
		Date start = null;Date end = null;
		if (createDateS != null && !"".equals(createDateS)) {
			try {
				 start = (Date) sdf.parse(createDateS+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if (createDateE != null && !"".equals(createDateE)) {
			try {
				 end = (Date) sdf.parse(createDateE+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		logger.debug(createDateS+","+createDateE);
		List<siteCheckReport> reportListByDept=reportFormService.getSiteCheckReportByDept(new String[]{}, deptId, start, end);
		
		this.writeResult(response, reportListByDept);
	}
	
	/**
	 * 台账信息统计报表跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getAccountReportView")
	public String queryAccountReport(Model model,HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.MONTH,-1);
		Date start = calendar.getTime();
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "console/reportForm/accountReportForm";
	}
	/**
	 * 根据日期查询出台账信息包括：部门信息以及合计金额
	 * @author H2603282  20140703
	 * @param startDate
	 * @param endDate
	 * @param deptName
	 * @return
	 */
	@RequestMapping(params = "action=queryAccountReportByDate")
	public void queryAccuntReportList(
			HttpServletResponse response,HttpServletRequest request) {
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String status = request.getParameter("status");
		if(status.equals("全部")){
			status="";
		}
		Date start = null;Date end = null;
		if (startDate != null && !"".equals(startDate)) {
			try {
				 start = (Date) sdf.parse(startDate+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if (endDate != null && !"".equals(endDate)) {
			try {
				 end = (Date) sdf.parse(endDate+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		List<AccountReport> accountReportDetailList = new ArrayList<AccountReport>();
		accountReportDetailList = reportFormService.getAccountReport(start, end, status);
		*/
	
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		logger.debug("开始时间"+startDate+"结束时间"+endDate);
		String status = request.getParameter("status");
		if(status.equals("全部")){
			status="";
		}
		logger.debug(startDate+","+endDate+",status:"+status);
		/*List<AccountReport> accountReportDetailList = new ArrayList<AccountReport>();
		accountReportDetailList = reportFormService.getAccountReportByDate(startDate, endDate, status);
       */

		Date start = this.getStartDate(startDate);
		Date end = this.getEndDate(endDate);
		List<AccountReport> accountReportDetailList = new ArrayList<AccountReport>();
		accountReportDetailList = reportFormService.getAccountReport(start, end, status);
		for(AccountReport ar : accountReportDetailList){
			if(ar.getDeptName().equals("委领导")){
				accountReportDetailList.remove(ar);
			}
			break;
		}

		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JsonUtil.toJson(accountReportDetailList));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	/**
	 * 根据部门名和日期查询出政务考核信息包括：各个部门采用信息的标题，创建时间，采用类别
	 * @author H2603282  20140703
	 * @param startDate
	 * @param endDate
	 * @param deptName
	 * @return
	 */
	@RequestMapping(params = "action=queryAccountReportByDept")
	public void queryAccoutReportListByDeptName(
			HttpServletResponse response,HttpServletRequest request) {
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String deptName = request.getParameter("deptName");
		Date start = null;Date end = null;
		if (startDate != null && !"".equals(startDate)) {
			try {
				 start = (Date) sdf.parse(startDate+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if (endDate != null && !"".equals(endDate)) {
			try {
				 end = (Date) sdf.parse(endDate+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		List<AccountReport> accountReportDetailList = new ArrayList<AccountReport>();
		accountReportDetailList = reportFormService.getAccountReport(deptName, start, end);
		
		*/
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String deptName = request.getParameter("deptName");
		/*
		List<AccountReport> accountReportDetailList = new ArrayList<AccountReport>();
		accountReportDetailList = reportFormService.getAccountReportByDept(deptName, startDate, endDate);
       /*
        
        */
	
		Date start = this.getStartDate(startDate);
		Date end = this.getEndDate(endDate);
		List<AccountReport> accountReportDetailList = new ArrayList<AccountReport>();
		accountReportDetailList = reportFormService.getAccountReport(deptName, start, end);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(JsonUtil.toJson(accountReportDetailList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 政务信息报表首次加载数据    20130630  gyf
	 * @return
	 */
	@RequestMapping(params="face=getGovInfoReportList")
	public String getGovInfoReportList(Model model){
		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.MONTH,-1);
		Date start = calendar.getTime();
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "console/reportForm/govInfoReport";
	}
	
	
	/**
	 * 政务信息统计报表查询   20130630  gyf
	 * @return
	 */
	@RequestMapping(params="action=getGovInfoReportListAll")
	public void getGovInfoReportListAll(HttpServletResponse response,HttpServletRequest request){
		DateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;Date end = null;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String status = request.getParameter("status");
		if(status.equals("全部")){
			status="";
		}
		logger.debug(startDate+","+endDate+",status:"+status);
		if(startDate!=null&&endDate!=null){
			try {
				start = fds.parse(startDate.substring(0, 10)+" 00:00:00");
				end = fds.parse(endDate.substring(0, 10)+" 23:59:59");
			} catch (ParseException e) {
				logger.debug("日期格式转换出错");
				e.printStackTrace();
			}
		}
		List<TextGovReport> textGovReportList = getTextGovReportList(start, end, status);
		this.writeResult(response, textGovReportList);
	}
	
	private List<TextGovReport> getTextGovReportList(Date start, Date end, String status){
		List<TextGovReport> textGovReportList = new ArrayList<TextGovReport>();//返回结果
		Map<Integer ,TextGovReport> textGovMap = new HashMap<Integer, TextGovReport>();
		//查询统计日志
		List<GovNewsReport> govnewsList = textGovInfoService.queryGovNewsReport(start, end, status, null);
		//查询部门列表
		List<Department> deptList = departmentService.getDeptAllList();
		//查询考核规则
		Map<String,Float> checkScoreMap = textGovInfoService.getCheckStandardScore();
		Map<Integer,Integer> submitMap = textGovInfoService.queryDeptShangbaoStat(start, end, status, null);
		//查询当年得分
		Date fristDate = ApiUtils.getFristDayYear(start);
		Date lastDate = ApiUtils.getLastDayYear(start);
		Map<Integer, Float> totalMap = queryTextGovReportYearStat(fristDate, lastDate);
		List<GovSiteCheckReport> totalList= textNewsReportService.queryGovSiteCheckReportStat(start, end, null);
		boolean isAddCurrent = false;
		if(totalList != null && totalList.size() > 0){
			
		} else {
			isAddCurrent = true;
		}
		govNewsReport(govnewsList, textGovMap, checkScoreMap, submitMap);
		if(deptList != null && deptList.size() > 0){
			for (Department dept: deptList) {
				boolean isResult = false;
				if(StringUtils.isNotBlank(status)){
					if(status.equals(dept.getDeptCategory())){
						isResult = true;
					}
				} else {
					isResult = true;
				}
				if(isResult){
					Integer deptId = dept.getDeptID();
					TextGovReport govReport = textGovMap.get(deptId);
					if(govReport == null){
						govReport = new TextGovReport();
						govReport.setDeptName(dept.getDeptName());
						govReport.setDeptCategory(dept.getDeptCategory());
					}
					if(deptId != 441){//委领导不显示
						Float totalScore = totalMap.get(deptId);
						Float total = govReport.getTotal();
						if(totalScore == null){
							totalScore = 0f;
						}
						if(total == null){
							total = 0f;
						}
						
						if(isAddCurrent){
							totalScore += total;
						}
						
						if(totalScore == 0){
							totalScore = null;
						}
						govReport.setTotalYear(totalScore);
						textGovReportList.add(govReport);
					}
				}
			}
		} else {
			for (Integer key : textGovMap.keySet()) {
				textGovReportList.add(textGovMap.get(key));
			}
		}
		return textGovReportList;
	}
	
	private Map<Integer, Float> queryTextGovReportYearStat(Date fristDate, Date lastDate){
		Map<Integer, Float> result = new HashMap<Integer, Float>();
		List<GovSiteCheckReport> list= textNewsReportService.queryGovSiteCheckReportStat(fristDate, lastDate, null);
		if(list != null && list.size() >0){
			for (GovSiteCheckReport gov : list) {
				result.put(gov.getDeptId(), gov.getGovTotalScore());
			}
		}
		return result;
	}
	
	/**
	 * 计算政务统计数据
	 * @param govnewsList
	 * @param textGovReportList
	 * 添加了采编积分规则 llj 20170623
	 */
	private void govNewsReport(List<GovNewsReport> govnewsList, Map<Integer,TextGovReport> textGovMap,Map<String,Float> checkScoreMap, Map<Integer,Integer> submitNumMap){
		if(govnewsList != null && govnewsList.size() > 0){
			int oldDeptId = 0; 
			String oldDeptName = null;
			String oldDeptCategory = null;
			long shangb = 0;//上报数量
			long caiy = 0;//采编数量
			long daily=0;//每日信息数量
			long jwdbsubject = 0; // 交委独编专题信息数量
			long jwzhsubject = 0; // 交委综合专题信息数量
			long trafficqj = 0; //交通情况与交流数量
			long trafficrb = 0; //交通日报数量
			long swdbsubject = 0; //市委独编数量
			long swzhsubject = 0; //市委综合数量
			long sfzhsubject = 0; //市府综合数量
			long sfdbsubject = 0; //市府独编数量
			long govcomLeader = 0; //市委批示数量
			long govLeader = 0; //市府批示数量
			long jwLeader = 0; //交委批示数量
			long trafLeader = 0; //交通部批示数量
			long sfworkd = 0; //市府工作动态数量
			long sfoneword = 0; //市府一句话新闻数量
			long swheadphone = 0; //市委一把手手机报数量
			long swdailyinfo = 0; //市委每日要情数量
			long otherdb = 0; //上级单位独编数量
			long otherzh = 0; //上级单位综合数量
			long otherLeader = 0; //上级领导批示数量
			long pubtypedynamic = 0; //刊物工作动态
			long countyDynamic = 0; //刊物区县动态
			long netInfo = 0; //刊物网络信息
			long pubTypewordinfo = 0; //刊物一句话信息
			long yuegao = 0;//约稿
			long subjectInfo = 0;//老数据专报信息
			long cityCommittee = 0;//老数据市委采用信息
			long cityGovernment = 0;//老数据市府采用信息
			long trafficDept = 0;//老数据市政府采用信息
			float baseShangbScore = checkScoreMap.get("sendGov");
			float baseDailyNum = checkScoreMap.get("dailyInfo");
			float baseOtherLeaderApprNum = checkScoreMap.get("otherLeaderAppr");
			float baseTrafficNum = checkScoreMap.get("trafficDept");
			float basetrafsitcommNum = checkScoreMap.get("trafsitcomm");
			float basetrafdaypaNum = checkScoreMap.get("trafdaypa");
			float basejwdbsubjectInfoNum = checkScoreMap.get("jwdbsubjectInfo");
			float basejwzhsubjectInfoNum = checkScoreMap.get("jwzhsubjectInfo");
			float basepubtypedynamicNum = checkScoreMap.get("pubtypedynamic");
			float basepubTypewordinfoNum = checkScoreMap.get("pubTypewordinfo");
			float basecountyDynamicNum = checkScoreMap.get("countyDynamic");
			float basenetInfoNum = checkScoreMap.get("netInfo");
			float baseotherdbsubjectInfoNum = checkScoreMap.get("otherdbsubjectInfo");
			float baseotherzhsubjectInfoNum = checkScoreMap.get("otherzhsubjectInfo");
			float basesfdbsubjectInfoNum = checkScoreMap.get("sfdbsubjectInfo");
			float baseworkdynamicNum = checkScoreMap.get("workdynamic");
			float basesfwordinfoNum = checkScoreMap.get("sfwordinfo");
			float basesfzhsubjectInfoNum = checkScoreMap.get("sfzhsubjectInfo");
			float baseCityComNum = checkScoreMap.get("cityCommittee");
			float baseswdbsubjectInfoNum = checkScoreMap.get("swdbsubjectInfo");
			float baseswdayinfoNum = checkScoreMap.get("swdayinfo");
			float baseswzhsubjectInfoNum = checkScoreMap.get("swzhsubjectInfo");
			float baseheadphonepaNum = checkScoreMap.get("headphonepa");
			float baseSubjectNum = checkScoreMap.get("subjectInfo");
			float baseCityGovNum = checkScoreMap.get("cityGovernment");
			
			float baseComLeaderNum = checkScoreMap.get("comLeaderAppr");
			float baseGovLeaderNum = checkScoreMap.get("govLeaderAppr");
			float baseGovComLeaderNum = checkScoreMap.get("govcomLeaderAppr");
			float baseTrafLeaderNum = checkScoreMap.get("trafLeaderAppr");
			float baseyuegaoNum = checkScoreMap.get("yuegao");
			TextGovReport textReport = new TextGovReport();
			for (GovNewsReport govnews : govnewsList) {
				int deptId = govnews.getDeptId();
				String deptName = govnews.getDeptName();
				String deptCategory = govnews.getDeptCategory();
				if(oldDeptId !=0 && oldDeptId != deptId){
					setGovNewsReport(textGovMap, oldDeptId, oldDeptName,oldDeptCategory,
							shangb, caiy, daily, jwdbsubject , jwzhsubject , trafficqj ,
							trafficrb , swdbsubject , swzhsubject , sfzhsubject ,sfdbsubject ,govcomLeader,
							govLeader ,jwLeader ,trafLeader ,sfworkd ,sfoneword ,swheadphone ,
							swdailyinfo ,otherdb ,otherzh ,otherLeader ,pubtypedynamic,countyDynamic,netInfo,pubTypewordinfo,yuegao,
							subjectInfo,cityCommittee,cityGovernment,trafficDept,
							baseShangbScore, baseDailyNum, baseOtherLeaderApprNum ,baseTrafficNum ,
							basetrafsitcommNum ,basetrafdaypaNum ,basejwdbsubjectInfoNum ,basejwzhsubjectInfoNum ,
							basepubtypedynamicNum ,basepubTypewordinfoNum ,baseotherdbsubjectInfoNum ,
							baseotherzhsubjectInfoNum ,basesfdbsubjectInfoNum ,baseworkdynamicNum ,
							basesfwordinfoNum ,basesfzhsubjectInfoNum ,baseCityComNum ,baseswdbsubjectInfoNum ,
							baseswdayinfoNum ,baseswzhsubjectInfoNum ,baseheadphonepaNum ,baseSubjectNum ,
							baseCityGovNum ,baseComLeaderNum ,baseGovLeaderNum ,baseTrafLeaderNum,
							
							basecountyDynamicNum,basenetInfoNum,baseGovComLeaderNum,baseyuegaoNum,
							
							 textReport,submitNumMap);
					textReport = new TextGovReport();
					 shangb = 0;//上报数量
					 caiy = 0;//采编数量
					 daily=0;//每日信息数量
					 jwdbsubject = 0; // 交委独编专题信息数量
					 jwzhsubject = 0; // 交委综合专题信息数量
					 trafficqj = 0; //交通情况与交流数量
					 trafficrb = 0; //交通日报数量
					 swdbsubject = 0; //市委独编数量
					 swzhsubject = 0; //市委综合数量
					 sfzhsubject = 0; //市府综合数量
					 sfdbsubject = 0; //市府独编数量
					 govcomLeader = 0; //市委批示数量
					 govLeader = 0; //市府批示数量
					 jwLeader = 0; //交委批示数量
					 trafLeader = 0; //交通部批示数量
					 sfworkd = 0; //市府工作动态数量
					 sfoneword = 0; //市府一句话新闻数量
					 swheadphone = 0; //市委一把手手机报数量
					 swdailyinfo = 0; //市委每日要情数量
					 otherdb = 0; //上级单位独编数量
					 otherzh = 0; //上级单位综合数量
					 otherLeader = 0; //上级领导批示数量
					 pubtypedynamic = 0; //刊物工作动态
					 countyDynamic = 0; //刊物区县动态
					 netInfo = 0; //刊物网络信息
					 pubTypewordinfo = 0; //刊物一句话信息
					 yuegao = 0;//约稿
					 subjectInfo = 0;//老数据专报信息
					 cityCommittee = 0;//老数据市委采用信息
					 cityGovernment = 0;//老数据市府采用信息
					 trafficDept = 0;//老数据市政府采用信息
				}
				//计算分数
				shangb ++;
				if(govnews.getGovUseFlag() > 0){
					

					
					caiy ++;
					String adoptType = govnews.getAdoptType();
					if(StringUtils.isNotBlank(adoptType)){
						for (String s : adoptType.split(",")) {
							if(StringUtils.isNotBlank(s)){
								switch (s) {
								case "dailyInfo"://每日信息
									pubtypedynamic ++;
									break;
								case "cityCommittee"://老数据市委采用
//									swdbsubject  ++;
									swdailyinfo  ++;
									break;
								case "cityGovernment"://老数据市政府采用
//									sfdbsubject  ++;
									sfworkd  ++;
									break;
								case "trafficDept"://老数据交通部采用
									trafficqj  ++;
									break;
								case "subjectInfo"://老数据专报信息
									jwdbsubject  ++;
									break;
								case "jwdbsubjectInfo"://交委独编专题
									jwdbsubject  ++;
									break;
								case "jwzhsubjectInfo"://交委综合专题
									jwzhsubject   ++;
									break;
								case "trafsitcomm"://交通部情况与交流
									trafficqj   ++;
									break;
								case "trafdaypa"://交通日报
									trafficrb  ++;
									break;
								case "swdbsubjectInfo"://市委独编
									swdbsubject   ++;
									break;
								case "swzhsubjectInfo"://市委综合
									swzhsubject  ++;
									break;
								case "sfzhsubjectInfo"://市府综合
									sfzhsubject  ++;
									break;
								case "sfdbsubjectInfo"://市府独编
									sfdbsubject  ++;
									break;
								case "govcomLeaderAppr"://被市委批示批示
									govcomLeader  ++;
									break;
								case "govLeaderAppr"://被市府领导批示
									govLeader  ++;
									break;
								case "comLeaderAppr"://被交委领导批示
									jwLeader  ++;
									break;
								case "trafLeaderAppr"://被交通部领导批示
									trafLeader  ++;
									break;
								case "workdynamic"://市府工作动态
									sfworkd  ++;
									break;
								case "sfwordinfo"://市府一句话新闻
									sfoneword  ++;
									break;
								case "headphonepa"://市委一把手手机报
									swheadphone  ++;
									break;
								case "swdayinfo"://市委每日要情
									swdailyinfo  ++;
									break;
								case "otherdbsubjectInfo"://上级单位独编
									otherdb  ++;
									break;
								case "otherzhsubjectInfo"://上级单位综合
									otherzh  ++;
									break;
								case "otherLeaderAppr"://上级领导批示
									otherLeader  ++;
									break;
								case "pubtypedynamic"://刊物工作动态
									pubtypedynamic  ++;
									break;
								case "countyDynamic"://刊物区县动态
									countyDynamic  ++;
									break;
								case "netInfo"://刊物网络信息
									netInfo  ++;
									break;
								case "pubTypewordinfo"://刊物一句话信息
									pubTypewordinfo  ++;
									break;
								case "yuegao"://约稿信息
									yuegao  ++;
									break;
							
								}
							}
						}
					}
				}
				oldDeptId = deptId;
				oldDeptName = deptName;
				oldDeptCategory = deptCategory;
			}
			//最后一次计算
			setGovNewsReport(textGovMap, oldDeptId, oldDeptName, oldDeptCategory,
					shangb, caiy, daily, jwdbsubject , jwzhsubject , trafficqj ,
					trafficrb , swdbsubject , swzhsubject , sfzhsubject ,sfdbsubject ,govcomLeader,
					govLeader ,jwLeader ,trafLeader ,sfworkd ,sfoneword ,swheadphone ,
					swdailyinfo ,otherdb ,otherzh ,otherLeader ,pubtypedynamic,countyDynamic,netInfo,pubTypewordinfo,yuegao,
					subjectInfo,cityCommittee,cityGovernment,trafficDept,
					baseShangbScore, baseDailyNum, baseOtherLeaderApprNum ,baseTrafficNum ,
					basetrafsitcommNum ,basetrafdaypaNum ,basejwdbsubjectInfoNum ,basejwzhsubjectInfoNum ,
					basepubtypedynamicNum ,basepubTypewordinfoNum ,baseotherdbsubjectInfoNum ,
					baseotherzhsubjectInfoNum ,basesfdbsubjectInfoNum ,baseworkdynamicNum ,
					basesfwordinfoNum ,basesfzhsubjectInfoNum ,baseCityComNum ,baseswdbsubjectInfoNum ,
					baseswdayinfoNum ,baseswzhsubjectInfoNum ,baseheadphonepaNum ,baseSubjectNum ,
					baseCityGovNum ,baseComLeaderNum ,baseGovLeaderNum ,baseTrafLeaderNum , 
					
					basecountyDynamicNum,basenetInfoNum,baseGovComLeaderNum,baseyuegaoNum,
					
					textReport,submitNumMap);
		}
	}

	/***
	 * 修改函数添加了不同积分规则 llj 20170626
	 */
	private void setGovNewsReport(Map<Integer, TextGovReport> textGovMap,
			int oldDeptId, String oldDeptName, String oldDeptCategory,long shangb, long caiy,
			long daily, long jwdbsubject , long jwzhsubject , long trafficqj , long trafficrb ,
			long swdbsubject , long swzhsubject , long sfzhsubject ,long sfdbsubject  ,
			long comLeader  ,long govLeader  ,long jwLeader  ,long trafLeader  ,
			long sfworkd  ,long sfoneword  ,long swheadphone  ,long swdailyinfo  ,
			long otherdb  ,long otherzh  ,long otherLeader  ,
			long pubtypedynamic,long countyDynamic,long netInfo,long pubTypewordinfo,long yuegao,
			long subjectInfo,long cityCommittee,long cityGovernment,long trafficDept,
			float baseShangbScore, float baseDailyNum, float baseOtherLeaderApprNum ,
			float baseTrafficNum , float basetrafsitcommNum , float basetrafdaypaNum ,
			float basejwdbsubjectInfoNum , float basejwzhsubjectInfoNum ,float basepubtypedynamicNum  ,
			float basepubTypewordinfoNum  ,float baseotherdbsubjectInfoNum  ,float baseotherzhsubjectInfoNum  ,
			float basesfdbsubjectInfoNum  ,float baseworkdynamicNum  ,float basesfwordinfoNum  ,
			float basesfzhsubjectInfoNum  ,float baseCityComNum  ,float baseswdbsubjectInfoNum  ,
			float baseswdayinfoNum  ,float baseswzhsubjectInfoNum  ,float baseheadphonepaNum  ,
			float baseSubjectNum   ,float baseCityGovNum   ,float baseComLeaderNum   ,
			float baseGovLeaderNum   ,float baseTrafLeaderNum,
			float basecountyDynamicNum ,float basenetInfoNum,float baseGovComLeaderNum, float baseyuegaoNum,
			TextGovReport textReport, Map<Integer,Integer> submitNumMap) {
		float shangbScore = 0;//得分
//		float dailyNum = 0; //每日信息得分
//		float subjectNum = 0; //专题信息得分
//		float trafficNum = 0; // 交通信息得分
//		float cityComNum = 0; //市委得分
//		float cityGovNum = 0; //市府得分
//		float comLeaderNum = 0; //市委批示得分
//		float govLeaderNum = 0; //市府批示得分
//		float trafLeaderNum = 0; //交通部批示得分
		

		float dailyNum = 0;// 每日信息数量
		float jwdbsubjectNum = 0; // 交委独编专题信息数量
		float jwzhsubjectNum = 0; // 交委综合专题信息数量
		float trafficqjNum = 0; // 交通情况与交流数量
		float trafficrbNum = 0; // 交通日报数量
		float swdbsubjectNum = 0; // 市委独编数量
		float swzhsubjectNum = 0; // 市委综合数量
		float sfzhsubjectNum = 0; // 市府综合数量
		float sfdbsubjectNum = 0; // 市府独编数量
		float comLeaderNum = 0; // 市委批示数量
		float govLeaderNum = 0; // 市府批示数量
		float jwLeaderNum = 0; // 交委批示数量
		float trafLeaderNum = 0; // 交通部批示数量
		float sfworkdNum = 0; // 市府工作动态数量
		float sfonewordNum = 0; // 市府一句话新闻数量
		float swheadphoneNum = 0; // 市委一把手手机报数量
		float swdailyinfoNum = 0; // 市委每日要情数量
		float otherdbNum = 0; // 上级单位独编数量
		float otherzhNum = 0; // 上级单位综合数量
		float otherLeaderNum = 0; // 上级领导批示数量
		float pubtypedynamicNum = 0; // 刊物工作动态数量
		float countyDynamicNum = 0; // 刊物区县动态数量
		float netInfoNum = 0; // 刊物网络信息数量
		float pubTypewordinfoNum = 0; // 刊物一句话信息数量
		float yuegaoNum = 0; // 约稿
		float subjectInfoNum = 0; //老数据专报信息数量
		float cityCommitteeNum = 0;//老数据市委采用数量
		float cityGovernmentNum = 0;//老数据市府采用数量
		float trafficDeptNum = 0;//老数据交通部采用数量

		float total = 0; //累计得分
		
		//一年累计上报分数50分
		int oldsubmitNum = submitNumMap.get(oldDeptId)== null ? 0 : submitNumMap.get(oldDeptId);
		float oldSubmitScore = 	oldsubmitNum * baseShangbScore;
		shangbScore = shangb * baseShangbScore;
		if(oldSubmitScore >= 50){
			shangbScore = 0;
		} else if((oldSubmitScore + shangbScore) > 50){
			shangbScore = 50 - oldSubmitScore;
		}
		System.out.println("deptId:"+oldDeptId + ", oldsubmitNum:" + oldsubmitNum+", shangbScore:"+shangbScore);
//		dailyNum = daily * baseDailyNum;
		subjectInfoNum = subjectInfo * basejwdbsubjectInfoNum;
		trafficDeptNum = trafficDept * basetrafsitcommNum;
		cityCommitteeNum = cityCommittee * baseswdbsubjectInfoNum;
		cityGovernmentNum = cityGovernment * basesfdbsubjectInfoNum;
//		comLeaderNum = comLeader * baseComLeaderNum;
//		govLeaderNum = govLeader * baseGovLeaderNum;
//		trafLeaderNum = trafLeader * baseTrafLeaderNum;
		 dailyNum = daily*basepubtypedynamicNum;// 每日信息数量
		 jwdbsubjectNum = jwdbsubject*basejwdbsubjectInfoNum; // 交委独编专题信息数量
		 jwzhsubjectNum = jwzhsubject*basejwzhsubjectInfoNum; // 交委综合专题信息数量
		 trafficqjNum = trafficqj*basetrafsitcommNum ; // 交通情况与交流数量
		 trafficrbNum = trafficrb*basetrafdaypaNum ; // 交通日报数量
		 swdbsubjectNum = swdbsubject*baseswdbsubjectInfoNum ; // 市委独编数量
		 swzhsubjectNum = swzhsubject*baseswzhsubjectInfoNum ; // 市委综合数量
		 sfzhsubjectNum = sfzhsubject*basesfzhsubjectInfoNum ; // 市府综合数量
		 sfdbsubjectNum = sfdbsubject*basesfdbsubjectInfoNum ; // 市府独编数量
		 comLeaderNum = comLeader*baseGovComLeaderNum ; // 市委批示数量
		 govLeaderNum = govLeader*baseGovLeaderNum ; // 市府批示数量
		 jwLeaderNum = jwLeader*baseComLeaderNum ; // 交委批示数量
		 trafLeaderNum = trafLeader*baseTrafLeaderNum; // 交通部批示数量
		 sfworkdNum = sfworkd*baseworkdynamicNum ; // 市府工作动态数量
		 sfonewordNum = sfoneword*basesfwordinfoNum ; // 市府一句话新闻数量
		 swheadphoneNum = swheadphone*baseheadphonepaNum ; // 市委一把手手机报数量
		 swdailyinfoNum = swdailyinfo*baseswdayinfoNum ; // 市委每日要情数量
		 otherdbNum = otherdb*baseotherdbsubjectInfoNum ; // 上级单位独编数量
		 otherzhNum = otherzh*baseotherzhsubjectInfoNum ; // 上级单位综合数量
		 pubtypedynamicNum = pubtypedynamic*basepubtypedynamicNum ; // 上级领导批示数量
		 countyDynamicNum = countyDynamic*basecountyDynamicNum ; // 上级领导批示数量
		 netInfoNum = netInfo*basenetInfoNum ; // 上级领导批示数量
		 pubTypewordinfoNum = pubTypewordinfo*basepubTypewordinfoNum ; // 上级领导批示数量
		 otherLeaderNum = otherLeader*baseOtherLeaderApprNum ; // 上级领导批示数量
		 yuegaoNum = yuegao*baseyuegaoNum ; // 约稿数量
		total = shangbScore+dailyNum+jwdbsubjectNum+jwzhsubjectNum+trafficqjNum+trafficrbNum+
				swdbsubjectNum+swzhsubjectNum+sfzhsubjectNum+sfdbsubjectNum+comLeaderNum+
				govLeaderNum+jwLeaderNum+trafLeaderNum+sfworkdNum+sfonewordNum+swheadphoneNum+
				swdailyinfoNum+otherdbNum+otherzhNum+otherLeaderNum+pubtypedynamicNum+countyDynamicNum+yuegaoNum+
				netInfoNum+pubTypewordinfoNum+subjectInfoNum
				+trafficDeptNum
				+cityCommitteeNum
				+cityGovernmentNum;
		
		textReport.setDeptCategory(oldDeptCategory);
		textReport.setDeptName(oldDeptName);
		textReport.setShangb(shangb);
		textReport.setShangbScore(shangbScore);
		textReport.setCaiy(caiy);
		textReport.setDaily(daily);
		textReport.setJwdbsubjectInfo(jwdbsubject);
		textReport.setJwzhsubjectInfo(jwzhsubject);
		textReport.setTrafsitcomm(trafficqj);
		textReport.setTrafdaypa(trafficrb);
		textReport.setSwdbsubjectInfo(swdbsubject);
		textReport.setSwzhsubjectInfo(swzhsubject);
		textReport.setSfzhsubjectInfo(sfzhsubject);
		textReport.setSfdbsubjectInfo(sfdbsubject);
		textReport.setComLeader(jwLeader);
		textReport.setWorkdynamic(sfworkd);
		textReport.setSfwordinfo(sfoneword);
		textReport.setHeadphonepa(swheadphone);
		textReport.setSwdayinfo(swdailyinfo);
		textReport.setOtherdbsubjectInfo(otherdb);
		textReport.setOtherzhsubjectInfo(otherzh);
		textReport.setOtherLeader(otherLeader);
		textReport.setPubtypedynamic(pubtypedynamic);
		textReport.setCountyDynamic(countyDynamic);
		textReport.setNetInfo(netInfo);
		textReport.setPubTypewordinfo(pubTypewordinfo);
		textReport.setYuegao(yuegao);
//		textReport.setSubject(subject);
//		textReport.setSubjectNum(subjectNum);
//		textReport.setTraffic(traffic);
//		textReport.setTrafficNum(trafficNum);
//		textReport.setCityCom(cityCom);
//		textReport.setCityComNum(cityComNum);
//		textReport.setCityGov(cityGov);
//		textReport.setCityGovNum(cityGovNum);
		textReport.setComLeaderNum(comLeaderNum);
		textReport.setGovComLeader(comLeader);
		textReport.setGovLeader(govLeader);
		textReport.setGovLeaderNum(govLeaderNum);
		textReport.setTrafLeader(trafLeader);
		textReport.setTrafLeaderNum(trafLeaderNum);
		textReport.setTotal(total);
		textGovMap.put(oldDeptId, textReport);
	}
	/**
	 * 政务信息报表导出excel    20130701  gyf   
	 * @return
	 */
	@RequestMapping(params="face=exportGovInfoReportListAll")
	public void exportGovInfoReportListAll(HttpServletRequest request,HttpServletResponse response){
		DateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;Date end = null;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String statusS = request.getParameter("status");
		logger.debug("上报类型："+statusS);
		String status = "";
		try {
			status = URLDecoder.decode(statusS,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(status.equals("全部")){
			status="";
		}
		
		logger.debug(startDate+","+endDate+",status:"+status);
		if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
			try {
				start = fds.parse(startDate.substring(0, 10)+" 00:00:00");
				end = fds.parse(endDate.substring(0, 10)+" 23:59:59");
			} catch (ParseException e) {
				logger.debug("日期格式转换出错");
				e.printStackTrace();
			}
		}
//		List<TextGovReport> textGovReportList = textGovInfoService.getGovReport(start, end, status);
		List<TextGovReport> textGovReportList = getTextGovReportList(start, end, status);
		textGovInfoService.exportGovInfoReportListAll(textGovReportList,startDate,endDate,response);
		//return "console/reportForm/govInfoReport";
	}
	
	/**
	 * 政务考核报表导出excel
	 * @author H2602975 zhangpeng 20140703
	 * @return
	 */
	@RequestMapping(params = "action=exportGovCheckReport")
	public void exportGovCheckReport(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			HttpServletResponse response,HttpServletRequest request) {
		String statusS = request.getParameter("status");
		logger.debug("上报类型："+statusS);
		String status = "";
		try {
			status = URLDecoder.decode(statusS,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(status.equals("全部")){
			status="";
		}
		
		logger.debug(startDate+","+endDate+",status:"+status);
		Date start = this.getStartDate(startDate);
	    Date end = this.getEndDate(endDate);

//		List<GovCheckReport> govCheckReportList = reportFormService.getGovCheckReportByDate(start, end,status);
		List<GovCheckReport> govCheckReportList = textNewsReportService.queryGovInfoCheckList(start, end, status);
		//TODO:导出excel
		textNewsReportService.exportExcelGovInfoCheckList(response, govCheckReportList, startDate, endDate);
		/*************** 导出Excel数据拼接 **************************/

		/*
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel");
		String fileName = "政务考核统计报表";
		String finalFileName = "";
		try {
			finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition",
				"attachment; filename="+finalFileName+".xls");
		OutputStream output = null;
		WritableWorkbook wwb = null;
		try {
			// 设置单元格的文字格式
			WritableFont font1 = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 10,
					WritableFont.NO_BOLD);
			WritableFont fontHead = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 18,
					WritableFont.BOLD);
			WritableFont fontTitle = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 10,
					WritableFont.BOLD);
			WritableCellFormat format1 = new WritableCellFormat(font1);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			WritableCellFormat formatTitle = new WritableCellFormat(fontTitle);

			format1.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatHead.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			formatHead.setAlignment(jxl.format.Alignment.CENTRE);
			formatHead.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			formatTitle.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			formatTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

			// fos = new FileOutputStream("政务信息统计报表.xls");
			output = response.getOutputStream();
			wwb = Workbook.createWorkbook(output);
			WritableSheet ws = wwb.createSheet("政务考核统计报表", 2); // 创建一个工作表
			ws.addCell(new Label(0, 0, "政务考核统计报表（" + startDate + "~" + endDate
					+ "）", formatHead));
			ws.mergeCells(0, 0, 21, 0);// 合并单元格
			ws.addCell(new Label(0, 1, "分类", formatTitle));
			ws.mergeCells(0, 1, 1, 1);
			ws.addCell(new Label(2, 1, "部门", formatTitle));
			ws.mergeCells(2, 1, 4, 1);
			ws.addCell(new Label(5, 1, "采用条数", formatTitle));
			ws.addCell(new Label(6, 1, "得分总计", formatTitle));
			ws.addCell(new Label(7, 1, "采用标题", formatTitle));
			ws.mergeCells(7, 1, 13, 1);
			ws.addCell(new Label(14, 1, "得分", formatTitle));
			ws.addCell(new Label(15, 1, "采用类别", formatTitle));
			ws.mergeCells(15, 1, 21, 1);
			ws.setRowView(1, 500);// 设置行高
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			int adoptNum , num=0;int l = 0,y=0;
			for(int j = 0; j < govCheckReportList.size(); j++){
				GovCheckReport t = govCheckReportList.get(j);
				
				if(t.getAdoptNum()!=null){
		        	adoptNum = new Integer(t.getAdoptNum());
					if(j==0){
						num = new Integer(t.getAdoptNum());
					}else{
						num = new Integer(t.getAdoptNum()) + num ;
					}
		        }else{
		        	adoptNum = 1;
					if(j==0){
						num = 1;
					}else{
						num = 1 + num ;
					}
		        	t.setAdoptNum("");
		        	t.setScore(null);
		        }
				
				logger.debug("num="+num+",adoptNum="+adoptNum);
				
				ws.addCell(new Label(0, 2+ num - adoptNum, t.getDeptCategory(), format1));
				ws.mergeCells(0, 2 + num - adoptNum, 1, 1 + num);// 合并单元格
				ws.addCell(new Label(2, 2+ num - adoptNum, t.getDeptName(), format1));
				ws.mergeCells(2, 2 + num - adoptNum, 4, 1 + num);// 合并单元格
				ws.addCell(new Label(5, 2+ num - adoptNum, t.getAdoptNum(), format1));
				ws.mergeCells(5, 2 + num - adoptNum, 5, 1 + num);// 合并单元格
				ws.addCell(new Label(6, 2+ num - adoptNum, (t.getScore()==null ? "" : t.getScore().toString()), format1));
				ws.mergeCells(6, 2 + num - adoptNum, 6, 1 + num);// 合并单元格
				
				List<GovCheckReport> GovCheckReportList = new ArrayList<GovCheckReport>();
				GovCheckReportList = reportFormService.getGovCheckReportByDept(t.getDeptName(),
						start, end);				
				if(GovCheckReportList.size()==0){
					l=l+1;
					y=l-1;
				}else{
					l=l+GovCheckReportList.size();
					y=l-GovCheckReportList.size();
				}
				
				if(GovCheckReportList.size()==0){
					ws.addCell(new Label(7, y + 2, "", format1));
					ws.mergeCells(7, y + 2, 13, y + 2);// 合并单元格
					ws.addCell(new Label(14, y + 2, "",
							format1));
					ws.addCell(new Label(15, y + 2, "", format1));
					ws.mergeCells(15, y + 2, 21, y + 2);// 合并单元格
				}else{
				
					for (int i = 0; i < GovCheckReportList.size(); i++) {
						GovCheckReport m = GovCheckReportList.get(i);
						String adoptStr = "";
						String adopt = m.getAdoptType();
	
						if (adopt.indexOf("dailyInfo") >= 0) {
							adoptStr += "每日信息   ";
						}
						if (adopt.indexOf("subjectInfo") >= 0) {
							adoptStr += "专题信息   ";
						}
						if (adopt.indexOf("cityCommittee") >= 0) {
							adoptStr += "市委   ";
						}
						if (adopt.indexOf("cityGovernment") >= 0) {
							adoptStr += "市政府   ";
						}
						if (adopt.indexOf("trafficDept") >= 0) {
							adoptStr += "交通部   ";
						}
						if (adopt.indexOf("comLeaderAppr") >= 0) {
							adoptStr += "委领导批示   ";
						}
						if (adopt.indexOf("comLeaderAppr") >= 0) {
							adoptStr += "市领导批示   ";
						}
						if (adopt.indexOf("trafLeaderAppr") >= 0) {
							adoptStr += "被交通部领导批示   ";
						}
						
						ws.setRowView(i + 3, 300);// 设置行高
						ws.addCell(new Label(7, y+i + 2, m.getTitle(), format1));
						ws.mergeCells(7, y+i + 2, 13, y+i + 2);// 合并单元格
						ws.addCell(new Label(14, y+i + 2, m.getScore().toString(),
								format1));
						ws.addCell(new Label(15, y+i + 2, adoptStr, format1));
						ws.mergeCells(15, y+i + 2, 21, y+i + 2);// 合并单元格
						
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

*/
		//return "console/reportForm/govCheckReportForm";
	}
	

	
	/**
	 * 网站信息报表导出excel
	 * @author H2602975 zhangpeng 20140704
	 * @return
	 */
	@RequestMapping(params = "action=exportSiteReport")
	public void exportSiteReport(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			HttpServletResponse response,HttpServletRequest request) {
		String statusS = request.getParameter("status");
		logger.debug("上报类型："+statusS);
		String status = "";
		try {
			status = URLDecoder.decode(statusS,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(status.equals("全部")){
			status="";
		}
		
		logger.debug(startDate+","+endDate+",status:"+status);
		
		List<SiteReport> siteReportListAll = reportFormService.getSiteReportByDate(startDate,
				endDate,status);

		/*************** 导出Excel数据拼接 **************************/

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel");
		String fileName = "网站信息统计报表";
		String finalFileName = "";
	    try {
	    	finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition",
				"attachment; filename="+finalFileName+".xls");
		OutputStream output = null;
		WritableWorkbook wwb = null;
		try {
			// 设置单元格的文字格式
			WritableFont font1 = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 10,
					WritableFont.NO_BOLD);
			WritableFont fontHead = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 18,
					WritableFont.BOLD);
			WritableFont fontTitle = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 10,
					WritableFont.BOLD);
			WritableCellFormat format1 = new WritableCellFormat(font1);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			WritableCellFormat formatTitle = new WritableCellFormat(fontTitle);

			format1.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatHead.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);

			formatHead.setAlignment(jxl.format.Alignment.CENTRE);
			formatHead.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			formatTitle.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			formatTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// fos = new FileOutputStream("政务信息统计报表.xls");
			output = response.getOutputStream();
			wwb = Workbook.createWorkbook(output);
			WritableSheet ws = wwb.createSheet("网站信息统计报表", 2); // 创建一个工作表
			ws.addCell(new Label(0, 0, "重庆市交通网站信息统计分析（" + startDate + "~" + endDate + "）", formatHead));
			ws.mergeCells(0, 0, 21, 0);// 合并单元格
			ws.addCell(new Label(0, 1, "分类", formatTitle));
			ws.mergeCells(0, 1, 1, 1);
			ws.addCell(new Label(2, 1, "单位", formatTitle));
			ws.mergeCells(2, 1, 4, 1);
			ws.addCell(new Label(5, 1, "累计上报数量", formatTitle));
			ws.addCell(new Label(6, 1, "累计综合得分", formatTitle));
			ws.addCell(new Label(7, 1, "栏目", formatTitle));
			ws.mergeCells(7, 1, 10, 1);
			ws.addCell(new Label(11, 1, "上报数量", formatTitle));
			ws.addCell(new Label(12, 1, "退回数量", formatTitle));
			ws.addCell(new Label(13, 1, "内网信息数量", formatTitle));
			ws.mergeCells(13, 1, 14, 1);
			ws.addCell(new Label(15, 1, "内网信息得分", formatTitle));
			ws.mergeCells(15, 1, 16, 1);
			ws.addCell(new Label(17, 1, "外网信息数量", formatTitle));
			ws.mergeCells(17, 1, 18, 1);
			ws.addCell(new Label(19, 1, "外网信息得分", formatTitle));
			ws.mergeCells(19, 1, 20, 1);
			ws.addCell(new Label(21, 1, "累计得分", formatTitle));
			
			ws.setRowView(1, 500);// 设置行高
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			int adoptNum , num=0;
			int l = 0,y=0;
			for(int j = 0; j < siteReportListAll.size(); j++){
				SiteReport m = siteReportListAll.get(j);
				adoptNum = m.getAdoptNum().intValue();
				if(j==0){
					num = m.getAdoptNum().intValue();
				}else{
					num = m.getAdoptNum().intValue() + num ;
				}
				logger.debug("num="+num+",adoptNum="+adoptNum);
				
				ws.addCell(new Label(0, 2+ num - adoptNum, m.getDeptCategory(), format1));
				ws.mergeCells(0, 2 + num - adoptNum, 1, 1 + num);// 合并单元格
				ws.addCell(new Label(2, 2+ num - adoptNum, m.getDeptName(), format1));
				ws.mergeCells(2, 2 + num - adoptNum, 4, 1 + num);// 合并单元格
				ws.addCell(new Label(5, 2+ num - adoptNum, (m.getAllTotalCount()==null ? "" : m.getAllTotalCount().toString()), format1));
				ws.mergeCells(5, 2 + num - adoptNum, 5, 1 + num);// 合并单元格
				ws.addCell(new Label(6, 2+ num - adoptNum, (m.getAllTotalCountSorce()==null ? "" : m.getAllTotalCountSorce().toString()), format1));
				ws.mergeCells(6, 2 + num - adoptNum, 6, 1 + num);// 合并单元格
				logger.debug(m.getDeptName());
				List<SiteReport> siteReportList = new ArrayList<SiteReport>();
				siteReportList = reportFormService.getSiteReportByDept(m.getDeptName(),startDate, endDate);
				l=l+siteReportList.size();
				y=l-siteReportList.size();
				logger.debug("l="+l+",y="+y);
				// 填充数据的内容
				for (int i=0; i < siteReportList.size(); i++) {
					
					SiteReport t = siteReportList.get(i);
					logger.debug(t.getCategoryName());
					ws.setRowView(i + 3, 300);// 设置行高

					ws.addCell(new Label(7, y + i + 2, t.getCategoryName(), format1));
					ws.mergeCells(7, y + i + 2, 10, y + i + 2);// 合并单元格
					ws.addCell(new Label(11, y + i + 2,(t.getReportCount()==null? "":t.getReportCount().toString()),format1));
					ws.addCell(new Label(12, y + i + 2,(t.getBackCount()==null? "":t.getBackCount().toString()), format1));
					ws.addCell(new Label(13, y + i + 2,(t.getInNetCount()==null? "":t.getInNetCount().toString()), format1));
					ws.mergeCells(13, y + i + 2, 14, y + i + 2);// 合并单元格
					ws.addCell(new Label(15, y + i + 2,(t.getInNetCountSorce()==null?"":t.getInNetCountSorce().toString()), format1));
					ws.mergeCells(15, y + i + 2, 16, y + i + 2);// 合并单元格
					ws.addCell(new Label(17, y + i + 2,(t.getOutNetCount()==null?"":t.getOutNetCount().toString()), format1));
					ws.mergeCells(17, y + i + 2, 18, y + i + 2);// 合并单元格
					ws.addCell(new Label(19, y + i + 2,(t.getOutNetCountSorce()==null?"":t.getOutNetCountSorce().toString()), format1));
					ws.mergeCells(19, y + i + 2, 20, y + i + 2);// 合并单元格
					ws.addCell(new Label(21, y + i + 2,(t.getAddTotalSorce()==null?"":t.getAddTotalSorce().toString()), format1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

		//return "console/reportForm/siteReportForm";
	}
	
	/**
	 * 台账报表导出excel
	 * @author H2602975 zhangpeng 20140704
	 * @return
	 */
	@RequestMapping(params = "action=exportAccountReport")
	public void exportAccountReport(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			HttpServletResponse response,HttpServletRequest request) {
		String statusS = request.getParameter("status");
		logger.debug("上报类型："+statusS);
		String status = "";
		try {
			status = URLDecoder.decode(statusS,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(status.equals("全部")){
			status="";
		}
		Date start = this.getStartDate(startDate);
	    Date end = this.getEndDate(endDate);
			
		List<AccountReport> accountReportDetailList = reportFormService.getAccountReport( start, end,status);

		/*************** 导出Excel数据拼接 **************************/

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel");
		String fileName = "台账统计报表";
		String finalFileName = "";
		try {
			finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition",
				"attachment; filename="+finalFileName+".xls");
		OutputStream output = null;
		WritableWorkbook wwb = null;
		try {
			// 设置单元格的文字格式
			WritableFont font1 = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 10,
					WritableFont.NO_BOLD);
			WritableFont fontHead = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 18,
					WritableFont.BOLD);
			WritableFont fontTitle = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 10,
					WritableFont.BOLD);
			WritableCellFormat format1 = new WritableCellFormat(font1);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			WritableCellFormat formatTitle = new WritableCellFormat(fontTitle);

			format1.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatHead.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);

			formatHead.setAlignment(jxl.format.Alignment.CENTRE);
			formatHead.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			formatTitle.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			formatTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// fos = new FileOutputStream("政务信息统计报表.xls");
			output = response.getOutputStream();
			wwb = Workbook.createWorkbook(output);
			WritableSheet ws = wwb.createSheet("台账统计报表", 2); // 创建一个工作表
			ws.addCell(new Label(0, 0, "重庆市交通信息报送台账统计分析（" + startDate + "~" + endDate + "）", formatHead));
			ws.mergeCells(0, 0, 21, 0);// 合并单元格
			ws.addCell(new Label(0, 1, "分类", formatTitle));
			ws.mergeCells(0, 1, 1, 1);
			ws.addCell(new Label(2, 1, "部门", formatTitle));
			ws.mergeCells(2, 1, 4, 1);
			ws.addCell(new Label(5, 1, "采用条数", formatTitle));
			ws.addCell(new Label(6, 1, "合计金额", formatTitle));
			ws.addCell(new Label(7, 1, "采用标题", formatTitle));
			ws.mergeCells(7, 1, 13, 1);
			ws.addCell(new Label(14, 1, "投稿日期", formatTitle));
			ws.mergeCells(14, 1, 15, 1);
			ws.addCell(new Label(16, 1, "采用类别", formatTitle));
			ws.mergeCells(16, 1, 21, 1);
			ws.setRowView(1, 500);// 设置行高
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			int adoptNum , num=0;int l = 0,y=0;
			for (int j = 0; j < accountReportDetailList.size(); j++) {
				AccountReport m = accountReportDetailList.get(j);
				
				if(m.getAdoptNum()!=null){
		        	adoptNum = new Integer(m.getAdoptNum());
					if(j==0){
						num = new Integer(m.getAdoptNum());
					}else{
						num = new Integer(m.getAdoptNum()) + num ;
					}
		        }else{
		        	adoptNum = 1;
					if(j==0){
						num = 1;
					}else{
						num = 1 + num ;
					}
		        	m.setAdoptNum("");
		        	m.setTotalPrice(null) ;
		        }
				
				logger.debug("num="+num+",adoptNum="+adoptNum);
				
				ws.addCell(new Label(0, 2+ num - adoptNum, m.getDeptCategory(), format1));
				ws.mergeCells(0, 2 + num - adoptNum, 1, 1 + num);// 合并单元格
				ws.addCell(new Label(2, 2+ num - adoptNum, m.getDeptName(), format1));
				ws.mergeCells(2, 2 + num - adoptNum, 4, 1 + num);// 合并单元格
				ws.addCell(new Label(5, 2+ num - adoptNum, m.getAdoptNum(), format1));
				ws.mergeCells(5, 2 + num - adoptNum, 5, 1 + num);// 合并单元格
				ws.addCell(new Label(6, 2+ num - adoptNum, (m.getTotalPrice()==null ? "":m.getTotalPrice().toString()), format1));
				ws.mergeCells(6, 2 + num - adoptNum, 6, 1 + num);// 合并单元格
				
				List<AccountReport> accountReportList = new ArrayList<AccountReport>();
				accountReportList = reportFormService.getAccountReport(m.getDeptName(),start, end);
				
				if(accountReportList.size()==0){
					l=l+1;
					y=l-1;
				}else{
					l=l+accountReportList.size();
					y=l-accountReportList.size();
				}
				
				if(accountReportList.size()==0){
					ws.addCell(new Label(7, y + 2, "", format1));
					ws.mergeCells(7, y + 2, 13, y + 2);// 合并单元格
					ws.addCell(new Label(14, y + 2, "",format1));
					ws.mergeCells(14, y + 2, 15, y + 2);// 合并单元格
					ws.addCell(new Label(16, y + 2,"", format1));
					ws.mergeCells(16, y + 2, 21, y + 2);// 合并单元格
				}else{
					for (int i = 0; i < accountReportList.size(); i++) {
						AccountReport t = accountReportList.get(i);
						
						String	adoptStr = "";
					    String	adopt = t.getAdoptType();
						
						if (adopt.indexOf("dailyInfo") >= 0) {
							adoptStr += "每日信息   ";
						}
						if (adopt.indexOf("subjectInfo") >= 0) {
							adoptStr += "专报信息   ";
						}
						if (adopt.indexOf("cityCommittee") >= 0) {
							adoptStr += "市委   ";
						}
						if (adopt.indexOf("cityGovernment") >= 0) {
							adoptStr += "市政府   ";
						}
						if (adopt.indexOf("trafficDept") >= 0) {
							adoptStr += "交通部   ";
						}
						if (adopt.indexOf("comLeaderAppr") >= 0) {
							adoptStr += "委领导批示   ";
						}
						if (adopt.indexOf("comLeaderAppr") >= 0) {
							adoptStr += "市领导批示   ";
						}
						if (adopt.indexOf("trafLeaderAppr") >= 0) {
							adoptStr += "被交通部领导批示   ";
						}
						if (adopt.indexOf("sendVideo") >= 0) {
							adoptStr += "视频报送投稿 ";
						}
						ws.setRowView(i + 3, 300);// 设置行高
						ws.addCell(new Label(7, y+i + 2, t.getTitle(), format1));
						ws.mergeCells(7, y+i + 2, 13, y+i + 2);// 合并单元格
						ws.addCell(new Label(14, y+i + 2, t.getEntryDate(),format1));
						ws.mergeCells(14, y+i + 2, 15, y+i + 2);// 合并单元格
						ws.addCell(new Label(16, y+i + 2,adoptStr, format1));
						ws.mergeCells(16, y+i + 2, 21, y+i + 2);// 合并单元格
						
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

		//return "console/reportForm/accountReportForm";
	}
	
	/**
	 * 网站信息考核报表导出excel
	 * @author H2602975 zhangpeng 20140704
	 * @return
	 */
	@RequestMapping(params = "action=exportSiteCheckReport")
	public void exportSiteCheckReport(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			HttpServletResponse response,HttpServletRequest request) {
		String statusS = request.getParameter("status");
		logger.debug("上报类型："+statusS);
		String status = "";
		try {
			status = URLDecoder.decode(statusS,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(status.equals("全部")){
			status="";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.debug(startDate+","+endDate);
		Date start = null;Date end = null;
		if (startDate != null && !"".equals(startDate)) {
			try {
				 start = (Date) sdf.parse(startDate+" 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		if (endDate != null && !"".equals(endDate)) {
			try {
				 end = (Date) sdf.parse(endDate+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		
		List<siteCheckReport> reportList=reportFormService.getSiteCheckReport(start, end,status);
		/*************** 导出Excel数据拼接 **************************/

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel");
		String fileName = "网站信息考核统计报表";
		String finalFileName = "";
		try {
			finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition",
				"attachment; filename="+finalFileName+".xls");
		OutputStream output = null;
		WritableWorkbook wwb = null;
		try {
			// 设置单元格的文字格式
			WritableFont font1 = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 10,
					WritableFont.NO_BOLD);
			WritableFont fontHead = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 18,
					WritableFont.BOLD);
			WritableFont fontTitle = new WritableFont(
					WritableFont.createFont("楷体 _GB2312"), 10,
					WritableFont.BOLD);
			WritableCellFormat format1 = new WritableCellFormat(font1);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			WritableCellFormat formatTitle = new WritableCellFormat(fontTitle);

			format1.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatHead.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);

			formatHead.setAlignment(jxl.format.Alignment.CENTRE);
			formatHead.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			formatTitle.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			formatTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// fos = new FileOutputStream("政务信息统计报表.xls");
			output = response.getOutputStream();
			wwb = Workbook.createWorkbook(output);
			WritableSheet ws = wwb.createSheet("网站信息考核统计报表", 2); // 创建一个工作表
			ws.addCell(new Label(0, 0, "重庆市交通网站信息采用统计分析（"+startDate+"~"+endDate+"）",formatHead));
			ws.mergeCells(0, 0, 18, 0);// 合并单元格
			ws.addCell(new Label(0, 1, "分类", formatTitle));
			ws.mergeCells(0, 1, 1, 1);
			ws.addCell(new Label(2, 1, "部门", formatTitle));
			ws.mergeCells(2, 1, 4, 1);
			ws.addCell(new Label(5, 1, "上报条数", formatTitle));
			ws.addCell(new Label(6, 1, "得分总计", formatTitle));
			ws.addCell(new Label(7, 1, "信息标题", formatTitle));
			ws.mergeCells(7, 1, 13, 1);
			ws.addCell(new Label(14, 1, "内网栏目", formatTitle));
			ws.addCell(new Label(15, 1, "外网栏目", formatTitle));
			ws.addCell(new Label(16, 1, "得分", formatTitle));
			ws.addCell(new Label(17, 1, "发布渠道", formatTitle));
			ws.mergeCells(17, 1, 18, 1);
			
			ws.setRowView(1, 500);// 设置行高
			int adoptNum , num=0;int l = 0,y=0;
			for (int j = 0; j < reportList.size(); j++) {
				siteCheckReport m = reportList.get(j);
				if(m.getAdoptNum()!=null){
		        	adoptNum = new Integer(m.getAdoptNum());
					if(j==0){
						num = new Integer(m.getAdoptNum());
					}else{
						num = new Integer(m.getAdoptNum()) + num ;
					}
		        }else{
		        	adoptNum = 1;
					if(j==0){
						num = 1;
					}else{
						num = 1 + num ;
					}
		        	m.setAdoptNum("");
		        	m.setScoreSum("");
		        }
				logger.debug("num="+num+",adoptNum="+adoptNum);
				
				ws.addCell(new Label(0, 2+ num - adoptNum, m.getDeptCategory(), format1));
				ws.mergeCells(0, 2 + num - adoptNum, 1, 1 + num);// 合并单元格
				ws.addCell(new Label(2, 2+ num - adoptNum, m.getDeptName(), format1));
				ws.mergeCells(2, 2 + num - adoptNum, 4, 1 + num);// 合并单元格
				ws.addCell(new Label(5, 2+ num - adoptNum, m.getAdoptNum(), format1));
				ws.mergeCells(5, 2 + num - adoptNum, 5, 1 + num);// 合并单元格
				ws.addCell(new Label(6, 2+ num - adoptNum, m.getScoreSum(), format1));
				ws.mergeCells(6, 2 + num - adoptNum, 6, 1 + num);// 合并单元格
			
				List<siteCheckReport> siteCheckReportList = new ArrayList<siteCheckReport>();
				siteCheckReportList = reportFormService.getSiteCheckReportByDept(new String[]{}, m.getDeptId(), start, end);
				if(siteCheckReportList.size()==0){
					l=l+1;
					y=l-1;
				}else{
					l=l+siteCheckReportList.size();
					y=l-siteCheckReportList.size();
				}
				
				if(siteCheckReportList.size()==0){					
					ws.addCell(new Label(7, y+ + 2, "", format1));
					ws.mergeCells(7, y+ + 2, 13, y+ + 2);// 合并单元格
					ws.addCell(new Label(14, y+ + 2, "", format1));
					ws.addCell(new Label(15, y+ + 2, "", format1));
					ws.addCell(new Label(16, y+ + 2,"",format1));
					ws.addCell(new Label(17, y+ + 2, "", format1));
					ws.mergeCells(17, y+ + 2, 18, y+ + 2);// 合并单元格
				}else{
					// 填充数据的内容
					for (int i = 0; i < siteCheckReportList.size(); i++) {
						siteCheckReport t = siteCheckReportList.get(i);
						String innerCategory=t.getInnerCategory();
				    	if (StringUtils.isEmpty(innerCategory)){
				    		innerCategory="";	
				    	}
				    	String outerCategory=t.getOuterCategory();
				    	if (StringUtils.isEmpty(outerCategory)){
				    		 outerCategory="";
				    	}
						String	adoptStr = "";
					    String	adopt = t.getAdoptType();
						if(adopt.equals("sendSite,isPublic")||adopt.equals("isPublic,sendSite")){
							 adoptStr="内网、外网 ";
					    }else if(adopt.equals("sendVideo")){
					    	 adoptStr="视频报送 ";
	   			    	}else if(adopt.equals("sendSite")){
				    		 adoptStr="内网 ";
				    		 outerCategory="";
				    	 } 
		
						ws.setRowView(i + 3, 300);// 设置行高
						ws.addCell(new Label(7, y+i + 2, t.getTitle(), format1));
						ws.mergeCells(7, y+i + 2, 13, y+i + 2);// 合并单元格
						ws.addCell(new Label(14, y+i + 2, innerCategory, format1));
						ws.addCell(new Label(15, y+i + 2, outerCategory, format1));
						ws.addCell(new Label(16, y+i + 2,(t.getScore()==null?"":t.getScore().toString()),format1));
						ws.addCell(new Label(17, y+i + 2, adoptStr, format1));
						ws.mergeCells(17, y+i + 2, 18, y+i + 2);// 合并单元格
						
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

		//return "console/reportForm/siteCheckReportForm";
	}
	/**
	 * 网站信息考核分数导入
	 * @author H2602965
	 * @param model
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "action=checkReport")
	public void checkReport(Model model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{
		String flag = request.getParameter("flag");
		String msg = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取一个月的政务考核、政务信息累计得分
		if(flag.equals("0")){
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String status = request.getParameter("status");
			status=URLDecoder.decode(status,"utf-8");
			if(status.equals("全部")){
				status="";
			}
			logger.debug("传入参数:startDate="+startDate+":endDate="+endDate+":status="+status);
			Date start = null;Date end = null;
			if (startDate != null && !"".equals(startDate)) {
				try {
					 start = (Date) sdf.parse(startDate+" 00:00:00");
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
			if (endDate != null && !"".equals(endDate)) {
				try {
					 end = (Date) sdf.parse(endDate+" 23:59:59");
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
			logger.debug(start+","+end);
			List<siteCheckReport> reportList=reportFormService.getSiteCheckReport(start, end,status);
			logger.debug("reportList大小"+reportList.size());
			for(int i=0 ; i < reportList.size(); i++){	
				String reportId = reportList.get(i).getDeptId();
				Float scoreSum=0f;
				if(reportList.get(i).getScoreSum()!=null){
					scoreSum=Float.parseFloat(reportList.get(i).getScoreSum());
				}
				
				logger.debug("reportId"+reportId);
				logger.debug("scoreSum"+scoreSum);
				Department department=new Department();
				department.setDeptID(Integer.parseInt(reportId));
				department.setFlag("0");
				department.setScoreSite(scoreSum);
				//取得当前服务器时间
				department.setUpdateDate(DateUtil.getCurrentDateStr());
				//取得当前用户名
				department.setUpdateUserNo(SystemContext.getUserName());
				logger.debug("输入参数:"+":DeptID="+Integer.parseInt(reportId)+":ScoreSite="+scoreSum+
						":UpdateDate="+DateUtil.getCurrentDateStr()+":UpdateUserNo="+SystemContext.getUserName());
				departmentService.updateDept(department);
			}
			//添加网站信息得分到Department中
			siteReportFormTotal( startDate, endDate, status,flag);
			msg = "考核成功！";
		}else{
			//获取当年的信息
			Date date = new Date(); 
	        int monthD = new Integer(new SimpleDateFormat("MM").format(date)); 
	        //当前月份不是1月,才能进行当年统计，统计时间为当年一月一日到前一个月最后一天
	        if(monthD>1){
	        	String yearD = new SimpleDateFormat("yyyy").format(date); 
	        	Calendar calendar = Calendar.getInstance();  
	            int month = calendar.get(Calendar.MONTH);
	            calendar.set(Calendar.MONTH, month-1);
	            calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
	            Date strDateTo = calendar.getTime();  
	            
	            String startDate = yearD+"-01-01";
	            String endDate = sdf.format(strDateTo).substring(0, 10);
	            
	            String status = "";
	            
	            Date start = null;Date end = null;
	            if (startDate != null && !"".equals(startDate)) {
	            	try {
	            		start = (Date) sdf.parse(startDate+" 00:00:00");
	            	} catch (ParseException e) {
	            		e.printStackTrace();
	            	}
	            	
	            }
	            if (endDate != null && !"".equals(endDate)) {
	            	try {
	            		end = (Date) sdf.parse(endDate+" 23:59:59");
	            	} catch (ParseException e) {
	            		e.printStackTrace();
	            	}
	            	
	            }
	            List<siteCheckReport> reportList=reportFormService.getSiteCheckReport(start, end,status);
	            for(int i=0 ; i < reportList.size(); i++){	
	            	String reportId = reportList.get(i).getDeptId();
	            	Float scoreSum=0f;
	            	if(reportList.get(i).getScoreSum()!=null){
	            		scoreSum=Float.parseFloat(reportList.get(i).getScoreSum());
	            	}
	            	
	            	logger.debug("reportId"+reportId);
	            	logger.debug("scoreSum"+scoreSum);
	            	Department department=new Department();
	            	department.setDeptID(Integer.parseInt(reportId));
	            	department.setFlag("0");
	            	department.setScoreSiteYear(scoreSum);
	            	//取得当前服务器时间
	            	department.setUpdateDate(DateUtil.getCurrentDateStr());
	            	//取得当前用户名
	            	department.setUpdateUserNo(SystemContext.getUserName());
	            	logger.debug("输入参数:"+":DeptID="+Integer.parseInt(reportId)+":ScoreSite="+scoreSum+
	            			":UpdateDate="+DateUtil.getCurrentDateStr()+":UpdateUserNo="+SystemContext.getUserName());
	            	departmentService.updateDept(department);
	            }
	            //添加网站信息得分到Department中
	            siteReportFormTotal( startDate, endDate, status,flag);
	            msg = "考核成功！";
	        }else{
	        	//
	        	msg = "没有当年考核信息";
	        }
		}
		
		this.writeMessage(response, msg);
	}	
	
	/**
	 * 政务信息考核分数导入
	 * @author H2602965
	 * @param model
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "action=checkGovReport")
	public void checkGovReport(Model model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String status = request.getParameter("status");
		String msg = "考核成功！";
		try {
			Date start = this.getStartDate(startDate);
		    Date end = this.getEndDate(endDate);
		    status = URLDecoder.decode(status, "utf-8");
		    System.out.println("--->status:"+status);
		    if("全部".equals(status)){
				status="";
			}
			textNewsReportService.govCheckReportList(start, end, status);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "考核失败！";
		}
		this.writeMessage(response, msg);
		
		/**
		String msg = "";
		String flag = request.getParameter("flag");
		if (flag.equals("0")) {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String status = request.getParameter("status");
			status = URLDecoder.decode(status, "utf-8");
			if (status.equals("全部")) {
				status = "";
			}
			logger.debug("传入参数:startDate=" + startDate + ":endDate=" + endDate
					+ ":status=" + status);
			Date start = this.getStartDate(startDate);
			Date end = this.getEndDate(endDate);
			List<GovCheckReport> reportList = new ArrayList<GovCheckReport>();
			reportList = reportFormService.getGovCheckReportByDate(start, end,
					status);
			logger.debug("reportList大小" + reportList.size());
			for (int i = 0; i < reportList.size(); i++) {
				String reportId = reportList.get(i).getDeptId();
				logger.debug("reportId" + reportId);
				Float score = 0f;
				if (reportList.get(i).getScore() != null) {
					score = reportList.get(i).getScore();
				}

				Department department = new Department();
				department.setDeptID(Integer.parseInt(reportId));
				department.setFlag("0");
				department.setScoreGov(score);
				// 取得当前服务器时间
				department.setUpdateDate(DateUtil.getCurrentDateStr());
				// 取得当前用户名
				department.setUpdateUserNo(SystemContext.getUserName());
				logger.debug("输入参数:" + ":DeptID=" + Integer.parseInt(reportId)
						+ ":ScoreGov=" + score + ":UpdateDate="
						+ DateUtil.getCurrentDateStr() + ":UpdateUserNo="
						+ SystemContext.getUserName());
				departmentService.updateDept(department);
			}
			// 添加政务信息累计得分
			checkGovReportTotal(startDate, endDate, status, flag);
			msg = "考核成功！";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 获取当年的信息
			Date date = new Date();
			int monthD = new Integer(new SimpleDateFormat("MM").format(date));
			 //当前月份不是1月,才能进行当年统计，统计时间为当年一月一日到前一个月最后一天
			if (monthD > 1) {

				String yearD = new SimpleDateFormat("yyyy").format(date);
				Calendar calendar = Calendar.getInstance();
				int month = calendar.get(Calendar.MONTH);
				calendar.set(Calendar.MONTH, month - 1);
				calendar.set(Calendar.DAY_OF_MONTH,
						calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date strDateTo = calendar.getTime();

				String startDate = yearD + "-01-01";
				String endDate = sdf.format(strDateTo).substring(0, 10);

				String status = "";

				Date start = this.getStartDate(startDate);
				Date end = this.getEndDate(endDate);

				List<GovCheckReport> reportList = new ArrayList<GovCheckReport>();
				reportList = reportFormService.getGovCheckReportByDate(start,
						end, status);
				for (int i = 0; i < reportList.size(); i++) {
					String reportId = reportList.get(i).getDeptId();
					Float score = 0f;
					if (reportList.get(i).getScore() != null) {
						score = reportList.get(i).getScore();
					}

					Department department = new Department();
					department.setDeptID(Integer.parseInt(reportId));
					department.setFlag("0");
					department.setScoreGovYear(score);
					// 取得当前服务器时间
					department.setUpdateDate(DateUtil.getCurrentDateStr());
					// 取得当前用户名
					department.setUpdateUserNo(SystemContext.getUserName());
					departmentService.updateDept(department);
				}
				// 添加政务信息累计得分
				checkGovReportTotal(startDate, endDate, status, flag);
				msg = "考核成功！";

			} else {
						msg = "没有当年的考核记录";
					}
		}

		this.writeMessage(response, msg);
		*/
	}
	
	//政务考核将政务信息累积得分添加到Department表中
	public void checkGovReportTotal(String startDate,String endDate,String status,String flag){
		DateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;Date end = null;
		if(status.equals("全部")){
			status="";
		}
		if(flag.equals("0")){
			if(startDate!=null&&endDate!=null){
				try {
					start = fds.parse(startDate.substring(0, 10)+" 00:00:00");
					end = fds.parse(endDate.substring(0, 10)+" 23:59:59");
				} catch (ParseException e) {
					logger.debug("日期格式转换出错");
					e.printStackTrace();
				}
			}
			List<TextGovReport> textGovReportList0 = textGovInfoService.queryGovReportStat(start, end, status);
			//获取一月的
			for(int i=0 ; i < textGovReportList0.size(); i++){	
				String reportName = textGovReportList0.get(i).getDeptName();
				Float score=0f;
				if(textGovReportList0.get(i).getTotal()!=null){
					score=textGovReportList0.get(i).getTotal();
				}
				
				Department department=new Department();
				department.setDeptName(reportName);
				department.setFlag("0");
				department.setScoreGovTotal(score);
				//取得当前服务器时间
				department.setUpdateDate(DateUtil.getCurrentDateStr());
				//取得当前用户名
				department.setUpdateUserNo(SystemContext.getUserName());
				departmentService.updateDeptTotal(department);
			}
		}else{
			if(startDate!=null&&endDate!=null){
				try {
					start = fds.parse(startDate.substring(0, 10)+" 00:00:00");
					end = fds.parse(endDate.substring(0, 10)+" 23:59:59");
				} catch (ParseException e) {
					logger.debug("日期格式转换出错");
					e.printStackTrace();
				}
			}
			//获取当年的信息
				List<TextGovReport> textGovReportList1 = textGovInfoService.queryGovReportStat
						(start, end, status);
				for(int i=0 ; i < textGovReportList1.size(); i++){	
					String reportName = textGovReportList1.get(i).getDeptName();
					Float score=0f;
					if(textGovReportList1.get(i).getTotal()!=null){
						score=textGovReportList1.get(i).getTotal();
					}
					
					Department department=new Department();
					department.setDeptName(reportName);
					department.setFlag("0");
					department.setScoreGovYearTotal(score);
					//取得当前服务器时间
					department.setUpdateDate(DateUtil.getCurrentDateStr());
					//取得当前用户名
					department.setUpdateUserNo(SystemContext.getUserName());
					departmentService.updateDeptTotal(department);
				}
		}

	}
	
	//网站考核将网站信息统计累积得分添加到Department表中
	/***
	 * 
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @param flag
	 */
	public void siteReportFormTotal(String startDate,String endDate,String status,String flag){
		if(status.equals("全部")){
			status="";
		}
		List<SiteReport> siteReportList = new ArrayList<SiteReport>();
		if(flag.equals("0")){
			siteReportList = reportFormService.getSiteReportByDate(startDate,
					endDate,status);
			//考核一个月的
			for(int i=0 ; i < siteReportList.size(); i++){	
				String reportName = siteReportList.get(i).getDeptName();
				Float score=0f;
				if(siteReportList.get(i).getAllTotalCountSorce()!=null){
					score=siteReportList.get(i).getAllTotalCountSorce();
				}
				
				Department department=new Department();
				department.setDeptName(reportName);
				department.setFlag("0");
				department.setScoreSiteTotal(score);
				//取得当前服务器时间
				department.setUpdateDate(DateUtil.getCurrentDateStr());
				//取得当前用户名
				department.setUpdateUserNo(SystemContext.getUserName());
				departmentService.updateDeptTotal(department);
			}
		}else{
			// 考核当年的
			siteReportList = reportFormService.getSiteReportByDate(startDate,
					endDate, status);
			for (int i = 0; i < siteReportList.size(); i++) {
				String reportName = siteReportList.get(i).getDeptName();
				Float score = 0f;
				if (siteReportList.get(i).getAllTotalCountSorce() != null) {
					score = siteReportList.get(i).getAllTotalCountSorce();
				}

				Department department = new Department();
				department.setDeptName(reportName);
				department.setFlag("0");
				department.setScoreSiteYearTotal(score);
				// 取得当前服务器时间
				department.setUpdateDate(DateUtil.getCurrentDateStr());
				// 取得当前用户名
				department.setUpdateUserNo(SystemContext.getUserName());
				departmentService.updateDeptTotal(department);
			}
		}
	}
}
