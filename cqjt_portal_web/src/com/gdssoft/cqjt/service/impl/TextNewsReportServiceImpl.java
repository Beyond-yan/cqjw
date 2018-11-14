package com.gdssoft.cqjt.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
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
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.cqjt.dao.CheckStandardDao;
import com.gdssoft.cqjt.dao.GovSiteCheckReportDao;
import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.TextGovReport;
import com.gdssoft.cqjt.pojo.report.GovCheckReport;
import com.gdssoft.cqjt.pojo.report.GovNewsReport;
import com.gdssoft.cqjt.pojo.report.GovSiteCheckReport;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.TextGovInfoService;
import com.gdssoft.cqjt.service.TextNewsReportService;
import com.gdssoft.cqjt.util.PKGenerator;

@Service("textNewsReportServiceImpl")
public class TextNewsReportServiceImpl implements TextNewsReportService {

	@Resource(name = "govSiteCheckReportDao")
	private GovSiteCheckReportDao govSiteCheckReportDao;
	
	@Resource(name = "checkStandardDao")
	private CheckStandardDao checkStandardDao;
	
	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;
	
	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	
	/**
	 * 统计政务考核信息
	 */
	@Override
	public List<GovCheckReport> queryGovInfoCheckList(Date start, Date end,
			String status) {
		
		List<GovCheckReport> resultList = new ArrayList<GovCheckReport>();//返回结果
		//计算部门
		Map<Integer ,GovCheckReport> deptMap = new HashMap<Integer, GovCheckReport>();
		//查询统计日志
		List<GovNewsReport> govnewsList = textGovInfoService.queryGovNewsReport(start, end, status, 1);
		//查询部门列表
		List<Department> deptList = departmentService.getDeptAllList();
		//查询基础积分 添加积分类型代码 llj 20170623
//		String[] codes = {"dailyInfo","subjectInfo","cityCommittee","cityGovernment","trafficDept","comLeaderAppr","govLeaderAppr","trafLeaderAppr","sendGov"};
		String[] codes = {"dailyInfo","subjectInfo","cityCommittee","cityGovernment",
				"trafficDept","comLeaderAppr","govLeaderAppr","trafLeaderAppr","sendGov",
				"jwdbsubjectInfo","jwzhsubjectInfo","sfdbsubjectInfo","sfzhsubjectInfo","workdynamic",
				"sfwordinfo","otherLeaderAppr","swdbsubjectInfo","swzhsubjectInfo","headphonepa","swdayinfo","trafsitcomm",
				"trafdaypa","otherdbsubjectInfo","otherzhsubjectInfo","pubtypedynamic","pubTypewordinfo","countyDynamic",
				"netInfo","dayinfon","yuegao","govcomLeaderAppr"};
		List<CheckStandard> stanList = checkStandardDao.queryCheckStandard(codes);
		
		//查询考核规则
		Map<String,Float> checkScoreMap = new HashMap<String, Float>();
		//查询考核规则对应名称
		Map<String,String> checkNameMap = new HashMap<String, String>();
		if(stanList != null && stanList.size() > 0){
			for (CheckStandard cs : stanList) {
				checkScoreMap.put(cs.getCode(), cs.getScore());
				checkNameMap.put(cs.getCode(), cs.getItemName());
			}
		}
		
		govNewsCheckReport(govnewsList, deptMap, checkScoreMap, checkNameMap);
		List<GovCheckReport> deptGovList = new ArrayList<GovCheckReport>();//返回结果
		//计算部门
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
					GovCheckReport govReport = deptMap.get(deptId);
					if(govReport == null){
						govReport = new GovCheckReport();
						govReport.setDeptName(dept.getDeptName());
						govReport.setDeptCategory(dept.getDeptCategory());
						govReport.setAdoptNum("1");
					}
					if(deptId != 441){//委领导不显示
						deptGovList.add(govReport);
					}
				}
			}
		} else {
			for (Integer key : deptMap.keySet()) {
				deptGovList.add(deptMap.get(key));
			}
		}
		//计算分类
		String oldDeptCategory = null;
		List<GovCheckReport> newDeptGovList = new ArrayList<GovCheckReport>();
		GovCheckReport category = new GovCheckReport();
		int adoptNum = 0;
		for (GovCheckReport gov : deptGovList) {
			String deptCategory = gov.getDeptCategory();
			if(oldDeptCategory !=null && !oldDeptCategory.equals(deptCategory)){
				category.setDeptCategory(oldDeptCategory);
				category.setAdoptNum(""+(adoptNum));
				category.setReportList(newDeptGovList);
				resultList.add(category);
				category = new GovCheckReport();
				newDeptGovList = new ArrayList<GovCheckReport>();
				adoptNum = 0;
			}
			
			int logNum = gov.getReportList().size();
			if(logNum == 0){
				logNum ++;
			}
			adoptNum += logNum;
			oldDeptCategory = deptCategory;
			newDeptGovList.add(gov);
		}
		//计算最后一条
		category.setDeptCategory(oldDeptCategory);
		category.setAdoptNum(""+(adoptNum));
		category.setReportList(newDeptGovList);
		resultList.add(category);
		
		return resultList;
	}
	
	/**
	 * 计算政务统计数据
	 * @param govnewsList
	 * @param textGovReportList
	 */
	private void govNewsCheckReport(List<GovNewsReport> govnewsList, Map<Integer ,GovCheckReport> deptMap,Map<String,Float> checkScoreMap,Map<String,String> checkNameMap){
		if(govnewsList != null && govnewsList.size() > 0){
			int oldDeptId = 0; 
			String oldDeptName = null;
			String oldDeptCategory = null;
			
//			float baseShangbScore = checkScoreMap.get("sendGov");
//			float baseDailyNum = checkScoreMap.get("dailyInfo");
//			float baseSubjectNum = checkScoreMap.get("subjectInfo");
//			float baseTrafficNum = checkScoreMap.get("trafficDept");
//			float baseCityComNum = checkScoreMap.get("cityCommittee");
//			float baseCityGovNum = checkScoreMap.get("cityGovernment");
//			float baseComLeaderNum = checkScoreMap.get("comLeaderAppr");
//			float baseGovLeaderNum = checkScoreMap.get("govLeaderAppr");
//			float baseTrafLeaderNum = checkScoreMap.get("trafLeaderAppr");
			float totalScore = 0;
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
			GovCheckReport deptGovCheck = new GovCheckReport();//部门
			List<GovCheckReport> govCheckList = new ArrayList<GovCheckReport>();//日志列表
			for (GovNewsReport govnews : govnewsList) {
				int deptId = govnews.getDeptId();
				String deptName = govnews.getDeptName();
				String deptCategory = govnews.getDeptCategory();
				GovCheckReport govCheckLog = new GovCheckReport();
				govCheckLog.setTitle(govnews.getNewsTitle());
				
				if(oldDeptId !=0 && oldDeptId != deptId){
					deptGovCheck.setDeptCategory(oldDeptCategory);
					deptGovCheck.setDeptName(oldDeptName);
					deptGovCheck.setAdoptNum(""+govCheckList.size());
					deptGovCheck.setDeptId(""+oldDeptId);
					deptGovCheck.setScore(totalScore);
					deptGovCheck.setReportList(govCheckList);
					deptMap.put(oldDeptId, deptGovCheck);
					deptGovCheck = new GovCheckReport();
					govCheckList = new ArrayList<GovCheckReport>();//日志列表
					totalScore = 0;
				}
				//计算分数
				float oneTotalSocre = 0;
//				oneTotalSocre += baseShangbScore;
				String adoptTypeStr = "";
				if(govnews.getGovUseFlag() > 0){
					String adoptType = govnews.getAdoptType();
					if(StringUtils.isNotBlank(adoptType)){
						for (String s : adoptType.split(",")) {
							if(StringUtils.isNotBlank(s)){
								switch (s) {
								case "dailyInfo"://老数据每日信息 现归到刊物工作动态
									oneTotalSocre += basepubtypedynamicNum;
									adoptTypeStr += checkNameMap.get("pubtypedynamic")+",";
									break;
								case "subjectInfo"://老数据专报信息 现归到交委独编专报信息
									oneTotalSocre += basejwdbsubjectInfoNum;
									adoptTypeStr += checkNameMap.get("jwdbsubjectInfo")+",";
									break;
								case "cityCommittee"://老数据市委采用 现归于市委独编专题信息
//									oneTotalSocre += baseswdbsubjectInfoNum;
//									adoptTypeStr += checkNameMap.get("swdbsubjectInfo")+",";
									oneTotalSocre += baseswdayinfoNum;
									adoptTypeStr += checkNameMap.get("swdayinfo")+",";
									break;
								case "cityGovernment"://老数据市政府采用 现归于市府独编专题信息
//									oneTotalSocre += basesfdbsubjectInfoNum;
//									adoptTypeStr += checkNameMap.get("sfdbsubjectInfo")+",";
									oneTotalSocre += baseworkdynamicNum;
									adoptTypeStr += checkNameMap.get("workdynamic")+",";
									break;
								case "trafficDept"://老数据交通部采用 现归于交通部简报情况交流
									oneTotalSocre += basetrafsitcommNum;
									adoptTypeStr += checkNameMap.get("trafsitcomm")+",";
									break;
								case "comLeaderAppr"://被委领导批示
									oneTotalSocre += baseComLeaderNum;
									adoptTypeStr += checkNameMap.get("comLeaderAppr")+",";
									break;
								case "govLeaderAppr"://被市领导批示
									oneTotalSocre += baseGovLeaderNum;
									adoptTypeStr += checkNameMap.get("govLeaderAppr")+",";
									break;
								case "trafLeaderAppr"://被交通部领导批示
									oneTotalSocre += baseTrafLeaderNum;
									adoptTypeStr += checkNameMap.get("trafLeaderAppr")+",";
									break;
								case "otherLeaderAppr"://被上级领导批示
									oneTotalSocre += baseOtherLeaderApprNum;
									adoptTypeStr += checkNameMap.get("otherLeaderAppr")+",";
									break;
								case "trafsitcomm"://交通部简报情况交流
									oneTotalSocre += basetrafsitcommNum;
									adoptTypeStr += checkNameMap.get("trafsitcomm")+",";
									break;
								case "trafdaypa"://交通部简报日报
									oneTotalSocre += basetrafdaypaNum;
									adoptTypeStr += checkNameMap.get("trafdaypa")+",";
									break;
								case "jwdbsubjectInfo"://交委独编专报信息
									oneTotalSocre += basejwdbsubjectInfoNum;
									adoptTypeStr += checkNameMap.get("jwdbsubjectInfo")+",";
									break;
								case "jwzhsubjectInfo"://交委综合专报信息
									oneTotalSocre += basejwzhsubjectInfoNum;
									adoptTypeStr += checkNameMap.get("jwzhsubjectInfo")+",";
									break;
								case "pubtypedynamic"://刊物工作动态
									oneTotalSocre += basepubtypedynamicNum;
									adoptTypeStr += checkNameMap.get("pubtypedynamic")+",";
									break;
								case "pubTypewordinfo"://刊物一句话信息
									oneTotalSocre += basepubTypewordinfoNum;
									adoptTypeStr += checkNameMap.get("pubTypewordinfo")+",";
									break;
								case "countyDynamic"://刊物区县动态
									oneTotalSocre += basecountyDynamicNum;
									adoptTypeStr += checkNameMap.get("countyDynamic")+",";
									break;
								case "netInfo"://刊物网络信息
									oneTotalSocre += basenetInfoNum;
									adoptTypeStr += checkNameMap.get("netInfo")+",";
									break;
								case "otherdbsubjectInfo"://上级独编
									oneTotalSocre += baseotherdbsubjectInfoNum;
									adoptTypeStr += checkNameMap.get("otherdbsubjectInfo")+",";
									break;
								case "otherzhsubjectInfo"://上级综合
									oneTotalSocre += baseotherzhsubjectInfoNum;
									adoptTypeStr += checkNameMap.get("otherzhsubjectInfo")+",";
									break;
								case "sfdbsubjectInfo"://市府独编专题信息
									oneTotalSocre += basesfdbsubjectInfoNum;
									adoptTypeStr += checkNameMap.get("sfdbsubjectInfo")+",";
									break;
								case "workdynamic"://市府工作动态
									oneTotalSocre += baseworkdynamicNum;
									adoptTypeStr += checkNameMap.get("workdynamic")+",";
									break;
								case "sfwordinfo"://市府一句话信息
									oneTotalSocre += basesfwordinfoNum;
									adoptTypeStr += checkNameMap.get("sfwordinfo")+",";
									break;
								case "sfzhsubjectInfo"://市府综合专题信息
									oneTotalSocre += basesfzhsubjectInfoNum;
									adoptTypeStr += checkNameMap.get("sfzhsubjectInfo")+",";
									break;
								case "swdbsubjectInfo"://市委独编专题信息
									oneTotalSocre += baseswdbsubjectInfoNum;
									adoptTypeStr += checkNameMap.get("swdbsubjectInfo")+",";
									break;
								case "swdayinfo"://市委每日要情
									oneTotalSocre += baseswdayinfoNum;
									adoptTypeStr += checkNameMap.get("swdayinfo")+",";
									break;
								case "swzhsubjectInfo"://市府综合专题信息
									oneTotalSocre += baseswzhsubjectInfoNum;
									adoptTypeStr += checkNameMap.get("swzhsubjectInfo")+",";
									break;
								case "headphonepa"://一把手手机报
									oneTotalSocre += baseheadphonepaNum;
									adoptTypeStr += checkNameMap.get("headphonepa")+",";
									break;
								case "govcomLeaderAppr"://市委领导批示
									oneTotalSocre += baseGovComLeaderNum;
									adoptTypeStr += checkNameMap.get("govcomLeaderAppr")+",";
									break;
								case "yuegao"://上级约稿
									oneTotalSocre += baseyuegaoNum;
									adoptTypeStr += checkNameMap.get("yuegao")+",";
									break;
								}
							}
						}
					}
				}
				oldDeptId = deptId;
				oldDeptName = deptName;
				oldDeptCategory = deptCategory;
				
				
				totalScore += oneTotalSocre;
				govCheckLog.setScore(oneTotalSocre);
				govCheckLog.setAdoptType(adoptTypeStr);
				govCheckList.add(govCheckLog);
			}
			//最后一次计算
			deptGovCheck.setDeptCategory(oldDeptCategory);
			deptGovCheck.setDeptName(oldDeptName);
			
			deptGovCheck.setAdoptNum(""+govCheckList.size());
			deptGovCheck.setDeptId(""+oldDeptId);
			deptGovCheck.setScore(totalScore);
			deptGovCheck.setReportList(govCheckList);
			deptMap.put(oldDeptId, deptGovCheck);
		}
	}
	
	//添加按累积得分排序
	@SuppressWarnings("unchecked")
	public void sortIntMethod(List list){  
		Collections.sort(list, new Comparator(){        
			@Override        
			public int compare(Object o1, Object o2) {            
				GovCheckReport tr1=(GovCheckReport)o1;            
				GovCheckReport tr2=(GovCheckReport)o2;   
				if(tr1.getScore()==null){
					tr1.setScore(0f);
				}
				if(tr2.getScore()==null){
					tr2.setScore(0f);
				}
				if(tr1.getScore()<tr2.getScore()){                
					return 1;            
					}else if(tr1.getScore()==tr2.getScore()){ 
						return 0;    
						}else{       
							return -1;     
							}       
				}    
			});  
		}

	/**
	 * 导出excel
	 */
	@Override
	public void exportExcelGovInfoCheckList( HttpServletResponse response,
			List<GovCheckReport> govCheckReportList, String startDate, String endDate) {
		// TODO Auto-generated method stub
		//按分数排序
		//sortIntMethod(govCheckReportList);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel");
		String fileName = "政务考核统计报表";
		String finalFileName = "";
		try {
			finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment; filename="+finalFileName+".xls");
		OutputStream output = null;
		WritableWorkbook wwb = null;
		try {
			// 设置单元格的文字格式
			WritableFont font1 = new WritableFont(WritableFont.createFont("楷体 _GB2312"), 10, WritableFont.NO_BOLD);
			WritableFont fontHead = new WritableFont(WritableFont.createFont("楷体 _GB2312"), 18, WritableFont.BOLD);
			WritableFont fontTitle = new WritableFont(WritableFont.createFont("楷体 _GB2312"), 10, WritableFont.BOLD);
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
			
			
			 boolean flag = false;//用于判断是否选择全部导出
	            List<String> listSheetName = new ArrayList<String>();//用于sheet名字
	    		for(int i0=0;i0<govCheckReportList.size();i0++){
	    			if(!govCheckReportList.get(0).getDeptCategory().equals(govCheckReportList.get(i0).getDeptCategory())){
	    				flag = true;
	    				break;
	    			}
	    		}
	    		if(flag){
	    			listSheetName.add("市交委机关");
	    			listSheetName.add("委属单位");
	    			listSheetName.add("区县交通局");
	    			listSheetName.add("市属相关交通企业");
	    		}else{
	    			listSheetName.add(govCheckReportList.get(0).getDeptCategory());
	    		}
			
	    	for (int i1 = 0; i1 < listSheetName.size(); i1++) {
	    		
	    	
//			WritableSheet ws = wwb.createSheet("政务考核统计报表", 2); // 创建一个工作表
	    	WritableSheet ws = wwb.createSheet(listSheetName.get(i1), 10); // 创建一个工作表
			ws.addCell(new Label(0, 0, "政务考核统计报表（" + startDate + "~" + endDate + "）", formatHead));
			ws.mergeCells(0, 0, 21, 0);// 合并单元格：第一列第一行到22列第一行
			ws.addCell(new Label(0, 1, "分类", formatTitle));//label:0列，1行  表示第一列第二行
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
			int category_beginMergeCols = 0;
			int category_endMergeCols =1;
			int dept_beginMergeCols = 2;
			int dept_endMergeCols = 4;
			int useNum_beginMergeCols = 5;
			int useNum_endMergeCols = 5;
			int totalScore_beginMergeCols = 6;
			int totalScore_endMergeCols = 6;
			int title_beginMergeCols = 7;
			int title_endMergeCols = 13;
			int score_beginMergeCols = 14;
			int score_endMergeCols = 14;
			int useType_beginMergeCols = 15;
			int useType_endMergeCols = 21;
			
			int beginMergRows=2;
			for(int j = 0; j < govCheckReportList.size(); j++){
				GovCheckReport category = govCheckReportList.get(j);
				
				if(category.getDeptCategory().equals(listSheetName.get(i1))){
					
				List<GovCheckReport> deptReportList = category.getReportList();
				//按分数排序
				sortIntMethod(deptReportList);
				int mergeRows =0;
				if(StringUtils.isNotBlank(category.getAdoptNum())){
					mergeRows = Integer.parseInt(category.getAdoptNum())-1;
		        }
				//分类
				ws.addCell(new Label(category_beginMergeCols, beginMergRows , category.getDeptCategory(), format1));
				ws.mergeCells(category_beginMergeCols, beginMergRows, category_endMergeCols, beginMergRows + mergeRows);// 合并单元格
				
				int deptBeginMergRows = beginMergRows;
				for (GovCheckReport dept : deptReportList) {
					int deptMergRows =0;
					List<GovCheckReport> detailsReportList = dept.getReportList();
					String deptUseNum = "";
					String deptScore="";
					if(detailsReportList!=null && detailsReportList.size() > 0){
						deptMergRows = detailsReportList.size()-1;
						deptUseNum = ""+detailsReportList.size();
						deptScore = ""+dept.getScore();
			        }
					//部门名称
					ws.addCell(new Label(dept_beginMergeCols, deptBeginMergRows , dept.getDeptName(), format1));
					ws.mergeCells(dept_beginMergeCols, deptBeginMergRows, dept_endMergeCols, deptBeginMergRows + deptMergRows);// 合并单元格
					//采用条数
					ws.addCell(new Label(useNum_beginMergeCols, deptBeginMergRows , deptUseNum, format1));
					ws.mergeCells(useNum_beginMergeCols, deptBeginMergRows, useNum_endMergeCols, deptBeginMergRows + deptMergRows);// 合并单元格
					//得分总计
					ws.addCell(new Label(totalScore_beginMergeCols, deptBeginMergRows , deptScore, format1));
					ws.mergeCells(totalScore_beginMergeCols, deptBeginMergRows, totalScore_endMergeCols, deptBeginMergRows + deptMergRows);// 合并单元格
					
					
					int detailsBeginMergRows = deptBeginMergRows;
					if(detailsReportList!= null && detailsReportList.size() > 0){
						for (GovCheckReport details : detailsReportList) {
							//得分标题
							ws.addCell(new Label(title_beginMergeCols, detailsBeginMergRows , ""+details.getTitle(), format1));
							ws.mergeCells(title_beginMergeCols, detailsBeginMergRows, title_endMergeCols, detailsBeginMergRows);// 合并单元格
							
							//得分
							ws.addCell(new Label(score_beginMergeCols, detailsBeginMergRows , ""+details.getScore(), format1));
							ws.mergeCells(score_beginMergeCols, detailsBeginMergRows, score_endMergeCols, detailsBeginMergRows);// 合并单元格
							
							//采用类别
							ws.addCell(new Label(useType_beginMergeCols, detailsBeginMergRows , ""+details.getAdoptType(), format1));
							ws.mergeCells(useType_beginMergeCols, detailsBeginMergRows, useType_endMergeCols, detailsBeginMergRows);// 合并单元格
							detailsBeginMergRows ++;
						}
					} else {
						//得分标题
						ws.addCell(new Label(title_beginMergeCols, detailsBeginMergRows , "", format1));
						ws.mergeCells(title_beginMergeCols, detailsBeginMergRows, title_endMergeCols, detailsBeginMergRows);// 合并单元格
						
						//得分
						ws.addCell(new Label(score_beginMergeCols, detailsBeginMergRows , "", format1));
						ws.mergeCells(score_beginMergeCols, detailsBeginMergRows, score_endMergeCols, detailsBeginMergRows);// 合并单元格
						
						//采用类别
						ws.addCell(new Label(useType_beginMergeCols, detailsBeginMergRows , "", format1));
						ws.mergeCells(useType_beginMergeCols, detailsBeginMergRows, useType_endMergeCols, detailsBeginMergRows);// 合并单元格
					}
					if(deptMergRows == 0){
						deptMergRows = 1;
					} else {
						deptMergRows += 1;
					}
					deptBeginMergRows += deptMergRows;
				}
				
				beginMergRows += mergeRows+1;
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
	}

	/**
	 * 政务信息考核统计
	 * @param start
	 * @param end
	 * @param status
	 * @return
	 */
	@Override
	public void govCheckReportList(Date start, Date end, String status) {
		//计算部门
		Map<Integer ,GovSiteCheckReport> deptMap = new HashMap<Integer, GovSiteCheckReport>();
		//查询统计日志
		List<GovNewsReport> govnewsList = textGovInfoService.queryGovNewsReport(start, end, status, null);
		//查询部门列表
		List<Department> deptList = departmentService.getDeptAllList();
		//查询基础积分 添加积分类型代码 llj 20170623
//		String[] codes = {"dailyInfo","subjectInfo","cityCommittee","cityGovernment","trafficDept","comLeaderAppr","govLeaderAppr","trafLeaderAppr","sendGov"};
		String[] codes = {"dailyInfo","subjectInfo","cityCommittee","cityGovernment",
				"trafficDept","comLeaderAppr","govLeaderAppr","trafLeaderAppr","sendGov",
				"jwdbsubjectInfo","jwzhsubjectInfo","sfdbsubjectInfo","sfzhsubjectInfo","workdynamic",
				"sfwordinfo","otherLeaderAppr","swdbsubjectInfo","swzhsubjectInfo","headphonepa","swdayinfo","trafsitcomm",
				"trafdaypa","otherdbsubjectInfo","otherzhsubjectInfo","pubtypedynamic","pubTypewordinfo","countyDynamic",
				"netInfo","dayinfon","yuegao","govcomLeaderAppr"};
		List<CheckStandard> stanList = checkStandardDao.queryCheckStandard(codes);
		
		//查询考核规则
		Map<String,Float> checkScoreMap = new HashMap<String, Float>();
		//TODO:
		Map<Integer,Integer> submitMap = textGovInfoService.queryDeptShangbaoStat(start, end, status, null);
		//查询考核规则对应名称
		Map<String,String> checkNameMap = new HashMap<String, String>();
		if(stanList != null && stanList.size() > 0){
			for (CheckStandard cs : stanList) {
				checkScoreMap.put(cs.getCode(), cs.getScore());
				checkNameMap.put(cs.getCode(), cs.getItemName());
			}
		}
		Date fristDate = DateUtil.getCurrentMouthOneDate(start);
		System.out.println("---->fristDate:"+fristDate);
		govCheckReport(govnewsList, deptMap, checkScoreMap, fristDate, submitMap);
		//计算部门
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
					GovSiteCheckReport govReport = deptMap.get(deptId);
					if(govReport == null){
						govReport  = new GovSiteCheckReport();
						govReport.setReportId(PKGenerator.generateKey());
						govReport.setDeptId(dept.getDeptID());
						govReport.setDeptName(dept.getDeptName());
						govReport.setDeptCategoryName(dept.getDeptCategory());
						govReport.setGovScore((float)0);
						govReport.setGovTotalScore((float)0);
						govReport.setReportDate(fristDate);
					}
					if(deptId != 441){//委领导不显示
//						resultList.add(govReport);
						//添加数据
						try {
							int x = govSiteCheckReportDao.updateGovCheckReport(govReport);
							System.out.println("--xxx--->"+x);
							if(x == 0){
								govSiteCheckReportDao.insertGovSiteCheckReport(govReport);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} else {
			for (Integer key : deptMap.keySet()) {
				int x = govSiteCheckReportDao.updateGovCheckReport(deptMap.get(key));
				if(x == 0){
					govSiteCheckReportDao.insertGovSiteCheckReport(deptMap.get(key));
				}
			}
		}
	}
	
	/**
	 * 计算政务统计数据
	 * @param govnewsList
	 * @param textGovReportList
	 */
	private void govCheckReport(List<GovNewsReport> govnewsList, Map<Integer,GovSiteCheckReport> deptMap,Map<String,Float> checkScoreMap, Date fristDate, Map<Integer,Integer> submitMap){
		if(govnewsList != null && govnewsList.size() > 0){
			int oldDeptId = 0; 
			String oldDeptName = null;
			String oldDeptCategory = null;
			
//			float baseShangbScore = checkScoreMap.get("sendGov");
//			float baseDailyNum = checkScoreMap.get("dailyInfo");
//			float baseSubjectNum = checkScoreMap.get("subjectInfo");
//			float baseTrafficNum = checkScoreMap.get("trafficDept");
//			float baseCityComNum = checkScoreMap.get("cityCommittee");
//			float baseCityGovNum = checkScoreMap.get("cityGovernment");
//			float baseComLeaderNum = checkScoreMap.get("comLeaderAppr");
//			float baseGovLeaderNum = checkScoreMap.get("govLeaderAppr");
//			float baseTrafLeaderNum = checkScoreMap.get("trafLeaderAppr");
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
			float totalScore = 0, useGovScore = 0, initShangbScore = 0;
			for (GovNewsReport govnews : govnewsList) {
				int deptId = govnews.getDeptId();
				String deptName = govnews.getDeptName();
				String deptCategory = govnews.getDeptCategory();
				
				if(oldDeptId != deptId){
					//一年累计上报分数50分
					int oldsubmitNum = submitMap.get(deptId)== null ? 0 : submitMap.get(deptId);
					initShangbScore = 	oldsubmitNum * baseShangbScore;
				}
				
				if(oldDeptId !=0 && oldDeptId != deptId){
					GovSiteCheckReport govSite  = new GovSiteCheckReport();
					govSite.setReportId(PKGenerator.generateKey());
					govSite.setDeptId(oldDeptId);
					govSite.setDeptName(oldDeptName);
					govSite.setDeptCategoryName(oldDeptCategory);
					govSite.setGovScore(useGovScore);
					govSite.setGovTotalScore(totalScore);
					govSite.setReportDate(fristDate);
					deptMap.put(oldDeptId, govSite);
					useGovScore = 0;
					totalScore = 0;
					
				}
				//计算分数
				float oneTotalSocre = 0;
				
				if(govnews.getGovUseFlag() > 0){
					String adoptType = govnews.getAdoptType();
					if(StringUtils.isNotBlank(adoptType)){
						for (String s : adoptType.split(",")) {
							if(StringUtils.isNotBlank(s)){
//								switch (s) {
//								case "dailyInfo"://每日信息
//									oneTotalSocre += baseDailyNum;
//									break;
//								case "subjectInfo"://专报信息
//									oneTotalSocre += baseSubjectNum;
//									break;
//								case "cityCommittee"://市委采用
//									oneTotalSocre += baseCityComNum;
//									break;
//								case "cityGovernment"://市政府采用
//									oneTotalSocre += baseCityGovNum;
//									break;
//								case "trafficDept"://交通部采用
//									oneTotalSocre += baseTrafficNum;
//									break;
//								case "comLeaderAppr"://被委领导批示
//									oneTotalSocre += baseComLeaderNum;
//									break;
//								case "govLeaderAppr"://被市领导批示
//									oneTotalSocre += baseGovLeaderNum;
//									break;
//								case "trafLeaderAppr"://被交通部领导批示
//									oneTotalSocre += baseTrafLeaderNum;
//									break;
//								}
								switch (s) {
								case "dailyInfo"://老数据每日信息 现归到刊物工作动态
									oneTotalSocre += basepubtypedynamicNum;
									break;
								case "subjectInfo"://老数据专报信息 现归到交委独编专报信息
									oneTotalSocre += basejwdbsubjectInfoNum;
									break;
								case "cityCommittee"://老数据市委采用 现归于市委独编专题信息
//									oneTotalSocre += baseswdbsubjectInfoNum;
									oneTotalSocre += baseswdayinfoNum;
									break;
								case "cityGovernment"://老数据市政府采用 现归于市府独编专题信息
//									oneTotalSocre += basesfdbsubjectInfoNum;
									oneTotalSocre += baseworkdynamicNum;
									break;
								case "trafficDept"://老数据交通部采用 现归于交通部简报情况交流
									oneTotalSocre += basetrafsitcommNum;
									break;
								case "comLeaderAppr"://被委领导批示
									oneTotalSocre += baseComLeaderNum;
									break;
								case "govLeaderAppr"://被市领导批示
									oneTotalSocre += baseGovLeaderNum;
									break;
								case "trafLeaderAppr"://被交通部领导批示
									oneTotalSocre += baseTrafLeaderNum;
									break;
								case "otherLeaderAppr"://被上级领导批示
									oneTotalSocre += baseOtherLeaderApprNum;
									break;
								case "trafsitcomm"://交通部简报情况交流
									oneTotalSocre += basetrafsitcommNum;
									break;
								case "trafdaypa"://交通部简报日报
									oneTotalSocre += basetrafdaypaNum;
									break;
								case "jwdbsubjectInfo"://交委独编专报信息
									oneTotalSocre += basejwdbsubjectInfoNum;
									break;
								case "jwzhsubjectInfo"://交委综合专报信息
									oneTotalSocre += basejwzhsubjectInfoNum;
									break;
								case "pubtypedynamic"://刊物工作动态
									oneTotalSocre += basepubtypedynamicNum;
									break;
								case "pubTypewordinfo"://刊物一句话信息
									oneTotalSocre += basepubTypewordinfoNum;
									break;
								case "countyDynamic"://刊物区县动态
									oneTotalSocre += basecountyDynamicNum;
									break;
								case "netInfo"://刊物网络信息
									oneTotalSocre += basenetInfoNum;
									break;
								case "otherdbsubjectInfo"://上级独编
									oneTotalSocre += baseotherdbsubjectInfoNum;
									break;
								case "otherzhsubjectInfo"://上级综合
									oneTotalSocre += baseotherzhsubjectInfoNum;
									break;
								case "sfdbsubjectInfo"://市府独编专题信息
									oneTotalSocre += basesfdbsubjectInfoNum;
									break;
								case "workdynamic"://市府工作动态
									oneTotalSocre += baseworkdynamicNum;
									break;
								case "sfwordinfo"://市府一句话信息
									oneTotalSocre += basesfwordinfoNum;
									break;
								case "sfzhsubjectInfo"://市府综合专题信息
									oneTotalSocre += basesfzhsubjectInfoNum;
									break;
								case "swdbsubjectInfo"://市委独编专题信息
									oneTotalSocre += baseswdbsubjectInfoNum;
									break;
								case "swdayinfo"://市委每日要情
									oneTotalSocre += baseswdayinfoNum;
									break;
								case "swzhsubjectInfo"://市府综合专题信息
									oneTotalSocre += baseswzhsubjectInfoNum;
									break;
								case "headphonepa"://一把手手机报
									oneTotalSocre += baseheadphonepaNum;
									break;
								case "govcomLeaderAppr"://市委领导批示
									oneTotalSocre += baseGovComLeaderNum;
									break;
								case "yuegao"://上级约稿
									oneTotalSocre += baseyuegaoNum;
									break;
								}
							}
						}
					}
				}
				oldDeptId = deptId;
				oldDeptName = deptName;
				oldDeptCategory = deptCategory;
				if(govnews.getGovUseFlag() > 0){
					useGovScore += oneTotalSocre;
				}
				if(initShangbScore < 50){
					oneTotalSocre += baseShangbScore;
				}
				totalScore += oneTotalSocre;
				initShangbScore += baseShangbScore;
			}
			//最后一次计算
			GovSiteCheckReport govSite  = new GovSiteCheckReport();
			govSite.setReportId(PKGenerator.generateKey());
			govSite.setDeptId(oldDeptId);
			govSite.setDeptName(oldDeptName);
			govSite.setDeptCategoryName(oldDeptCategory);
			govSite.setGovScore(useGovScore);
			govSite.setGovTotalScore(totalScore);
			govSite.setReportDate(fristDate);
			deptMap.put(oldDeptId, govSite);
		}
	}

	@Override
	public List<GovSiteCheckReport> queryGovSiteCheckReportStat(Date beginDate,
			Date endDate, String category) {
		return govSiteCheckReportDao.queryGovSiteCheckReportStat(beginDate, endDate, category);
	}

	/**
	 * 前台获取政务考核信息
	 */
	@Override
	public void queryGovSiteCheckStat(Model model, int type) {
		boolean queryMonth = false, queryYear = false;
		if(type == 1){
			queryMonth = true;
		} else if(type == 2){
			queryYear = true;
		} else {
			queryMonth = true;
			queryYear = true;
		}
		Date nowDate = DateUtil.getCurrentDate();//当前时间
		Date nowFirstMouthDate = DateUtil.getMouthOneDate(0,nowDate);
		Date beforeFirstMouthDate = DateUtil.getMouthOneDate(-1,nowDate);
		Date firstYearDate = DateUtil.getYearFirst(0, nowDate);
		Date beginDate = beforeFirstMouthDate, endDate = nowFirstMouthDate;
		String swCategory = "'市交委机关'", qtCategory = "'委属单位','区县交通局','市属相关交通企业'";
		int month = DateUtil.getMonth(beforeFirstMouthDate);
		if(queryMonth){
			List<GovSiteCheckReport> govList = queryGovSiteCheckReportStat(beginDate, endDate, swCategory);
			List<GovSiteCheckReport> govList1 = null;
			if(govList == null || govList.size() == 0){
				beginDate = DateUtil.getMouthOneDate(-2, nowDate);
				endDate = beforeFirstMouthDate;
				govList = queryGovSiteCheckReportStat(beginDate, endDate, swCategory);
				month --;
				if(month == 0){
					month = 12;
				}
			}
			govList1 = queryGovSiteCheckReportStat(beginDate, endDate, qtCategory);
			
			model.addAttribute("govList", govList);
			model.addAttribute("govList1", govList1);
		}
		if(queryYear){
			beginDate = firstYearDate; endDate = null;
			List<GovSiteCheckReport> govTotalList = queryGovSiteCheckReportStat(beginDate, endDate, swCategory);
			List<GovSiteCheckReport> govTotalList1 = null;
			if(govTotalList == null || govTotalList.size() == 0){
				Date beforeFirstYearDate = DateUtil.getYearFirst(-1, nowDate);
				beginDate = beforeFirstYearDate;
				endDate = null;
				govTotalList = queryGovSiteCheckReportStat(beginDate, endDate, swCategory);
			}
			govTotalList1 = queryGovSiteCheckReportStat(beginDate, endDate, qtCategory);
			model.addAttribute("govTotalList", govTotalList);
			model.addAttribute("govTotalList1", govTotalList1);
		}
		/*
		 * 1. 数据库 - 表
		 * 2. 登陆用户 跳转到浪潮
		 * 3. 单点登录 待办事项
		 * 4. http://23.99.127.111:8083/
		 */
		model.addAttribute("month", month);
	}
}
