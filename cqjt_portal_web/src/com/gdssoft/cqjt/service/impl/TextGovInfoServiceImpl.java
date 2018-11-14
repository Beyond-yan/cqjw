package com.gdssoft.cqjt.service.impl;
/**
 * 政务信息实现类
 * @author  zhangpeng 20140604
 * @return
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.cqjt.dao.CheckStandardDao;
import com.gdssoft.cqjt.dao.TextGovInfoDao;
import com.gdssoft.cqjt.dao.TextNewsDao;
import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextGovReport;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.report.GovNewsReport;
import com.gdssoft.cqjt.service.DepartmentScoreService;
import com.gdssoft.cqjt.service.TextGovInfoService;

@Service("textGovInfoServiceImpl")
public class TextGovInfoServiceImpl implements TextGovInfoService {
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Autowired
	@Resource(name = "textGovInfoDao")
	private TextGovInfoDao textGovInfoDao;	
	
	@Resource(name = "checkStandardDao")
	private CheckStandardDao checkStandardDao;
	
	@Autowired
	@Resource(name = "textNewsDao")
	private TextNewsDao textNewsDao;
	
	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;
	
	@Resource(name = "deptScoreServiceImpl")
	private DepartmentScoreService deptScoreService;

	@Override
	public TextGovInfo getTextGovInfo(String giId) {
		return textGovInfoDao.getTextGovInfo(giId);
	}
 
	@Override
	public void save(TextGovInfo textGovInfo) {
		// TODO Auto-generated method stub
		textGovInfoDao.insertGovInfo(textGovInfo);
	}
	/**
	 * @author H2602965
	 * 新增获取政务上报详细信息 ,用于页面编辑 H2602965 2014.06.05
	 */
	@Override
	public List<TextGovInfo> getTextGovInfoByNewsId(String newsId) {
		return textGovInfoDao.getTextGovInfoByNewsId(newsId);
	}
	@Override
	public List<TextGovInfo> getTextGovInfoListByNewsId(String newsId) {
		return textGovInfoDao.getTextGovInfoListByNewsId(newsId);
	}
	/**
	 * @author H2602965 2014.06.05
	 * 新增获取政务上报编辑模块,保存功能 ,用于编辑后的数据保存
	 * 
	 */
	@Override
	public void update(TextGovInfo textGovInfo) {
		textGovInfoDao.update(textGovInfo);
		
	}
	/**
	 * H2902992 20140606政务信息上报删除功能
	 */
	@Override
	public void deleteInfoReport(TextGovInfo textGovInfo) {
		textGovInfoDao.deleteInfoReport(textGovInfo);
		
	}
	/**
	 * H2902992 20160129政务信息删除功能通过id删除 by llj
	 */
	@Override
	public void delGovInfoById(String giId) {
		textGovInfoDao.delGovInfoById(giId);
		
	}
	
	@Override
	public void savePubDetail(String[] giIdArray, String[] sequenceNumArray) {
		// TODO Auto-generated method stub
		for (int i = 0; i < sequenceNumArray.length; i++) {
			textGovInfoDao.savePubDetail(giIdArray[i],sequenceNumArray[i]);
		}
	}
	
	@Override
	public List<TextGovInfo> getUnPubTextGovInfoList(String titleKey,
			Date startDate, Date endDate) {
		return textGovInfoDao.getUnPubTextGovInfoList(titleKey, startDate, endDate);
	}
	
	@Override
	public List<TextGovInfo> getTextGovInfoList(String newsTitle, Date entryDateS, Date entryDateE, String status,int pageIndex, int pageSize) {
		return textGovInfoDao.getTextGovInfo(newsTitle,entryDateS,entryDateE,status,"0",pageIndex, pageSize);
	}

	@Override
	public List<TextGovReport> getGovReport(Date startDate, Date endDate,  String status) {
		List<TextGovReport> reportList = textGovInfoDao.getGovReport(startDate, endDate, status);
		List<TextGovReport> reportListNum = new ArrayList<TextGovReport>();
		List<TextGovReport> reportListNume = new ArrayList<TextGovReport>();
		//获取当前时间上个月的最后一天
		Calendar cal = Calendar.getInstance();	
		cal.setTime(startDate);//获取查询当月的时间
		String year=String.valueOf(cal.get(Calendar.YEAR));//获取当前年
		String month=String.valueOf(cal.get(Calendar.MONTH) + 1);//获取当前月
		if(!month.equals("1")){
			cal.add(Calendar.MONTH, -1);//调到上个月		
			int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);//得到一个月最最后一天日期(31/30/29/28)
			cal.set( cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay, 23, 59, 59);//按你的要求设置时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//按格式输出
			String ee = sdf.format(cal.getTime());
			String ss = year + "-01-01 00:00:00";
			System.out.println(ee+"==="+ss);
			Date sDate = null;Date eDate = null ;
			try {
				sDate = sdf.parse(ss);
				eDate = sdf.parse(ee);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			reportListNum = textGovInfoDao.getGovReportNum(sDate, eDate, status);
			reportListNume = textGovInfoDao.getGovReportNum(sDate, endDate, status);
		}
		Float daily = checkStandardDao.getCheckStandardDetail("daily").getScore();
		Float subject = checkStandardDao.getCheckStandardDetail("subject").getScore();
		Float traffic = checkStandardDao.getCheckStandardDetail("traffic").getScore();
		Float cityCom = checkStandardDao.getCheckStandardDetail("cityCom").getScore();
		Float cityGov = checkStandardDao.getCheckStandardDetail("cityGov").getScore();
		Float comLeader = checkStandardDao.getCheckStandardDetail("comLeader").getScore();
		Float govLeader = checkStandardDao.getCheckStandardDetail("govLeader").getScore();
		Float trafLeader = checkStandardDao.getCheckStandardDetail("trafLeader").getScore();
		Float shangbScore = checkStandardDao.getCheckStandardDetail("sendGov").getScore();

		//for(TextGovReport t:reportList){
		for(int i=0; i<reportList.size();i++){	
			TextGovReport t = reportList.get(i);
			Float da = t.getDaily()==null ? 0:(t.getDaily()*daily);
			Float su = t.getSubject()==null ? 0:(t.getSubject()*subject);
			Float tr = t.getTraffic()==null ? 0:(t.getTraffic()*traffic);
			Float ci = t.getCityCom()==null ? 0:(t.getCityCom()*cityCom);
			Float cg = t.getCityGov()==null ? 0:(t.getCityGov()*cityGov);
			Float co = t.getComLeader()==null ? 0:(t.getComLeader()*comLeader);
			Float go = t.getGovLeader()==null ? 0:(t.getGovLeader()*govLeader);
			Float tl = t.getTrafLeader()==null ? 0:(t.getTrafLeader()*trafLeader);
			Float sb = 0f;
			
			if(da==0){t.setDailyNum(null);}else{t.setDailyNum(da);}
			if(su==0){t.setSubjectNum(null);}else{t.setSubjectNum(su);}
			if(tr==0){t.setTrafficNum(null);}else{t.setTrafficNum(tr);}
			if(ci==0){t.setCityComNum(null);}else{t.setCityComNum(ci);}
			if(cg==0){t.setCityGovNum(null);}else{t.setCityGovNum(cg);}
			if(co==0){t.setComLeaderNum(null);}else{t.setComLeaderNum(co);}
			if(go==0){t.setGovLeaderNum(null);}else{t.setGovLeaderNum(go);}
			if(tl==0){t.setTrafLeaderNum(null);}else{t.setTrafLeaderNum(tl);}
			
			if(month.equals("1")){
				sb = t.getShangb()==null ? 0:(t.getShangb()*shangbScore);
				if(sb>=50){
					sb = 50f;
				}
				if(sb==0){t.setShangbScore(null);}else{t.setShangbScore(sb);}
			}else{			
				TextGovReport m = reportListNum.get(i);
				TextGovReport n = reportListNume.get(i);
				if(n.getShangb()*shangbScore>=50){//到目前为止上报得分大于等于50分
					if(m.getShangb()*shangbScore>=50){//前几个月得分是否大于50分
						sb = 0f;
					}else{
						sb = 50 - m.getShangb()*shangbScore;
					}
					t.setShangbScore(sb);
				}else{
					sb = t.getShangb()==null ? 0:(t.getShangb()*shangbScore);
					if(sb==0){t.setShangbScore(null);}else{t.setShangbScore(sb);}
				}
			}
						
			if(da+su+tr+ci+cg+co+go+tl+sb==0){t.setTotal(null);}else{t.setTotal(da+su+tr+ci+cg+co+go+tl+sb);}
			if(t.getShangb()==0){t.setShangb(null);}
			if(t.getCaiy()==0){t.setCaiy(null);}
		}
		return reportList;
	}
	
	//添加按累积得分排序
	@SuppressWarnings("unchecked")
	public void sortIntMethod(List list){  
		Collections.sort(list, new Comparator(){        
			@Override        
			public int compare(Object o1, Object o2) {            
				TextGovReport tr1=(TextGovReport)o1;            
				TextGovReport tr2=(TextGovReport)o2;     
				if(tr1.getTotalYear()==null){
					tr1.setTotalYear(0f);
				}
				if(tr2.getTotalYear()==null){
					tr2.setTotalYear(0f);
				}
				if(tr1.getTotalYear()<tr2.getTotalYear()){                
					return 1;            
					}else if(tr1.getTotalYear()==tr2.getTotalYear()){ 
						return 0;    
						}else{       
							return -1;     
							}       
				}    
			});  
		}

	@Override
	public void exportGovInfoReportListAll(List<TextGovReport> textGovReportList,String start,String end,HttpServletResponse response) {
		//先排序，按照得分高低拍
		sortIntMethod(textGovReportList);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel");
		String fileName = "政务信息统计报表";
		String finalFileName = "";
		try {
			finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition",
				"attachment; filename="+finalFileName+".xls");
		OutputStream output = null;
		WritableWorkbook wwb =null;
		
		//FileOutputStream fos;
		try {    
            //    设置单元格的文字格式
            WritableFont font1=new WritableFont(WritableFont.createFont("楷体 _GB2312"),10,WritableFont.NO_BOLD );
     		WritableFont fontHead=new WritableFont(WritableFont.createFont("楷体 _GB2312"),18,WritableFont.BOLD );
     		WritableFont fontTitle=new WritableFont(WritableFont.createFont("楷体 _GB2312"),10,WritableFont.BOLD );
     		WritableCellFormat format1=new WritableCellFormat(font1);
     		WritableCellFormat formatHead=new WritableCellFormat(fontHead);
     		WritableCellFormat formatTitle=new WritableCellFormat(fontTitle);
     		format1.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatHead.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatHead.setAlignment(jxl.format.Alignment.CENTRE);
			formatHead.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			formatTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			formatTitle.setAlignment(jxl.format.Alignment.CENTRE);//把水平对齐方式指定为居中 
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			formatTitle.setWrap(true);
			//fos = new FileOutputStream("政务信息统计报表.xls");
			output = response.getOutputStream();
            wwb = Workbook.createWorkbook(output);
            
            boolean flag = false;//用于判断是否选择全部导出
            List<String> listSheetName = new ArrayList<String>();//用于sheet名字
            
    		for(int i0=0;i0<textGovReportList.size();i0++){
    			if(!textGovReportList.get(0).getDeptCategory().equals(textGovReportList.get(i0).getDeptCategory())){
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
    			listSheetName.add(textGovReportList.get(0).getDeptCategory());
    		}
            
			for (int i1 = 0; i1 < listSheetName.size(); i1++) {

				// WritableSheet ws = wwb.createSheet("政务信息统计报表", 10); //
				// 创建一个工作表
				WritableSheet ws = wwb.createSheet(listSheetName.get(i1), 10); // 创建一个工作表
				ws.addCell(new Label(0, 0, "政务信息统计报表（" + start + "~" + end
						+ "）", formatHead));
				ws.mergeCells(0, 0, 30, 0);// 合并单元格
				

//				ws.addCell(new Label(0, 1, "分类", formatTitle));
//				ws.mergeCells(0, 1, 0, 3);// 合并单元格
//				ws.addCell(new Label(1, 1, "部门", formatTitle));
//				ws.mergeCells(1, 1, 2, 3);
//				ws.addCell(new Label(3, 1, "交委", formatTitle));
//				ws.mergeCells(3, 1, 9, 1);
//				ws.addCell(new Label(10, 1, "市府", formatTitle));
//				ws.mergeCells(10, 1, 14, 1);
//				ws.addCell(new Label(15, 1, "市委", formatTitle));
//				ws.mergeCells(15, 1, 19, 1);
//				ws.addCell(new Label(20, 1, "交通部", formatTitle));
//				ws.mergeCells(20, 1, 22, 1);
//				ws.addCell(new Label(23, 1, "上级单位", formatTitle));
//				ws.mergeCells(23, 1, 25, 1);
//				ws.addCell(new Label(26, 1, "上级约稿", formatTitle));
//				ws.mergeCells(26, 1, 26, 3);
//				ws.addCell(new Label(27, 1, "上报总数", formatTitle));
//				ws.mergeCells(27, 1, 27, 3);
//				ws.addCell(new Label(28, 1, "上报得分", formatTitle));
//				ws.mergeCells(28, 1, 28, 3);
//				ws.addCell(new Label(29, 1, "当月得分", formatTitle));
//				ws.mergeCells(29, 1, 29, 3);
//				ws.addCell(new Label(30, 1, "累积得分", formatTitle));
//				ws.mergeCells(30, 1, 30, 3);
//				ws.addCell(new Label(3, 2, "每日信息", formatTitle));
//				ws.mergeCells(3, 2, 6, 2);
//				ws.addCell(new Label(7, 2, "专题信息", formatTitle));
//				ws.mergeCells(7, 2, 8, 2);
//				ws.addCell(new Label(9, 2, "被委领导批示", formatTitle));
//				ws.mergeCells(9, 2, 9, 3);
//				ws.addCell(new Label(10, 2, "专题信息", formatTitle));
//				ws.mergeCells(10, 2, 11, 2);
//				ws.addCell(new Label(12, 2, "昨日简报", formatTitle));
//				ws.mergeCells(12, 2, 13, 2);
//				ws.addCell(new Label(14, 2, "被市府领导批示", formatTitle));
//				ws.mergeCells(14, 2, 14, 3);
//				ws.addCell(new Label(15, 2, "专题信息", formatTitle));
//				ws.mergeCells(15, 2, 16, 2);
//				ws.addCell(new Label(17, 2, "一把手手机报", formatTitle));
//				ws.mergeCells(17, 2, 17, 3);
//				ws.addCell(new Label(18, 2, "每日要情", formatTitle));
//				ws.mergeCells(18, 2, 18, 3);
//				ws.addCell(new Label(19, 2, "被市委领导批示", formatTitle));
//				ws.mergeCells(19, 2, 19, 3);
//				ws.addCell(new Label(20, 2, "交通部简报", formatTitle));
//				ws.mergeCells(20, 2, 21, 2);
//				ws.addCell(new Label(22, 2, "被交通部领导批示", formatTitle));
//				ws.mergeCells(22, 2, 22, 3);
//				ws.addCell(new Label(23, 2, "中办、国办", formatTitle));
//				ws.mergeCells(23, 2, 24, 2);
//				ws.addCell(new Label(25, 2, "被上级领导批示", formatTitle));
//				ws.mergeCells(25, 2, 25, 3);
//				ws.addCell(new Label(3, 3, "工作动态", formatTitle));
//				ws.addCell(new Label(4, 3, "区县动态", formatTitle));
//				ws.addCell(new Label(5, 3, "一句话信息 ", formatTitle));
//				ws.addCell(new Label(6, 3, "网络信息", formatTitle));
//				ws.addCell(new Label(7, 3, "独编", formatTitle));
//				ws.addCell(new Label(8, 3, "综合", formatTitle));
//				ws.addCell(new Label(10, 3, "独编", formatTitle));
//				ws.addCell(new Label(11, 3, "综合", formatTitle));
//				ws.addCell(new Label(12, 3, "工作动态", formatTitle));
//				ws.addCell(new Label(13, 3, "一句话新闻 ", formatTitle));
//				ws.addCell(new Label(15, 3, "独编", formatTitle));
//				ws.addCell(new Label(16, 3, "综合", formatTitle));
//				ws.addCell(new Label(20, 3, "情况与交流", formatTitle));
//				ws.addCell(new Label(21, 3, "日报", formatTitle));
//				ws.addCell(new Label(23, 3, "独编", formatTitle));
//				ws.addCell(new Label(24, 3, "综合", formatTitle));
				
				ws.addCell(new Label(0, 1, "分类", formatTitle));
				ws.mergeCells(0, 1, 0, 3);// 合并单元格
				ws.addCell(new Label(1, 1, "部门", formatTitle));
				ws.mergeCells(1, 1, 2, 3);
				ws.addCell(new Label(3, 1, "交委", formatTitle));
				ws.mergeCells(3, 1, 8, 1);
				ws.addCell(new Label(9, 1, "市委", formatTitle));
				ws.mergeCells(9, 1, 12, 1);
				ws.addCell(new Label(13, 1, "市政府", formatTitle));
				ws.mergeCells(13, 1, 16, 1);
				ws.addCell(new Label(17, 1, "交通部", formatTitle));
				ws.mergeCells(17, 1, 18, 1);
				ws.addCell(new Label(19, 1, "上级单位", formatTitle));
				ws.mergeCells(19, 1, 20, 1);
				ws.addCell(new Label(21, 1, "领导批示", formatTitle));
				ws.mergeCells(21, 1, 23, 1);
				ws.addCell(new Label(24, 1, "上报总数", formatTitle));
				ws.mergeCells(24, 1, 24, 3);
				ws.addCell(new Label(25, 1, "约稿", formatTitle));
				ws.mergeCells(25, 1, 25, 3);
				ws.addCell(new Label(26, 1, "当月得分", formatTitle));
				ws.mergeCells(26, 1, 26, 3);
				ws.addCell(new Label(27, 1, "累积得分", formatTitle));
				ws.mergeCells(27, 1, 27, 3);
				ws.addCell(new Label(3, 2, "每日信息", formatTitle));
				ws.mergeCells(3, 2, 6, 2);
				ws.addCell(new Label(7, 2, "信息专报", formatTitle));
				ws.mergeCells(7, 2, 8, 2);
				ws.addCell(new Label(9, 2, "信息专报", formatTitle));
				ws.mergeCells(9, 2, 10, 2);
				ws.addCell(new Label(11, 2, "一把手手机报", formatTitle));
				ws.mergeCells(11, 2, 11, 3);
				ws.addCell(new Label(12, 2, "每日要情", formatTitle));
				ws.mergeCells(12, 2, 12, 3);
				ws.addCell(new Label(13, 2, "专报信息", formatTitle));
				ws.mergeCells(13, 2, 14, 2);
				ws.addCell(new Label(15, 2, "昨日简报", formatTitle));
				ws.mergeCells(15, 2, 16, 2);
				ws.addCell(new Label(17, 2, "交通部简报", formatTitle));
				ws.mergeCells(17, 2, 18, 2);
				ws.addCell(new Label(19, 2, "中办、国办", formatTitle));
				ws.mergeCells(19, 2, 20, 2);
				ws.addCell(new Label(21, 2, "被委领导批示", formatTitle));
				ws.mergeCells(21, 2, 21, 3);
				ws.addCell(new Label(22, 2, "被市领导批示", formatTitle));
				ws.mergeCells(22, 2, 22, 3);
				ws.addCell(new Label(23, 2, "被上级领导批示", formatTitle));
				ws.mergeCells(23, 2, 23, 3);
				ws.addCell(new Label(3, 3, "工作动态", formatTitle));
				ws.addCell(new Label(4, 3, "区县动态", formatTitle));
				ws.addCell(new Label(5, 3, "一句话信息 ", formatTitle));
				ws.addCell(new Label(6, 3, "网络信息", formatTitle));
				ws.addCell(new Label(7, 3, "独编", formatTitle));
				ws.addCell(new Label(8, 3, "综合", formatTitle));
				ws.addCell(new Label(9, 3, "独编", formatTitle));
				ws.addCell(new Label(10, 3, "综合", formatTitle));
				ws.addCell(new Label(13, 3, "独编", formatTitle));
				ws.addCell(new Label(14, 3, "综合 ", formatTitle));
				ws.addCell(new Label(15, 3, "工作动态", formatTitle));
				ws.addCell(new Label(16, 3, "一句话信息", formatTitle));
				ws.addCell(new Label(17, 3, "情况与交流", formatTitle));
				ws.addCell(new Label(18, 3, "日报", formatTitle));
				ws.addCell(new Label(19, 3, "独编", formatTitle));
				ws.addCell(new Label(20, 3, "综合", formatTitle));
				
				ws.setRowView(1, 600);// 设置行高

				
				Float tdaily=0f,tjwdbsubjectInfo=0f,tjwzhsubjectInfo=0f,tcomLeader=0f,tsfdbsubjectInfo=0f,
						tsfzhsubjectInfo=0f, tworkdynamic=0f,tsfwordinfo=0f,tgovLeader=0f, tswdbsubjectInfo=0f,
						tswzhsubjectInfo=0f,theadphonepa=0f, tswdayinfo=0f,tgovComLeader=0f,ttrafsitcomm=0f,
						ttrafdaypa=0f, ttrafLeader=0f, totherdbsubjectInfo=0f,totherzhsubjectInfo=0f,
						totherLeader=0f,tshangb = 0f,tshangbScore =0f,ttotal =0f, ttotalYear=0f,
						tpubtypedynamic=0f,tcountyDynamic=0f,tnetInfo=0f,tpubTypewordinfo=0f,tyuegao=0f;
				
				int jnum =0;//用于计算数据条数
				// 填充数据的内容
				for (int i = 0; i < textGovReportList.size(); i++) {
					TextGovReport t = textGovReportList.get(i);
					if(t.getDeptCategory().equals(listSheetName.get(i1))){
						jnum = jnum+1;
					
						// 计算每种数量总和
						tshangb += (t.getShangb() == null ? 0 : t.getShangb());
						tshangbScore += (t.getShangbScore() == null ? 0 : t
								.getShangbScore());
						ttotal += (t.getTotal() == null ? 0 : t.getTotal());
						ttotalYear += (t.getTotalYear() == null ? 0 : t
								.getTotalYear());
						totherLeader += (t.getOtherLeader() == null ? 0 : t.getOtherLeader());
						totherzhsubjectInfo += (t.getOtherzhsubjectInfo() == null ? 0 : t.getOtherzhsubjectInfo());
						totherdbsubjectInfo += (t.getOtherdbsubjectInfo() == null ? 0 : t.getOtherdbsubjectInfo());
						ttrafLeader += (t.getTrafLeader() == null ? 0 : t.getTrafLeader());
						ttrafdaypa += (t.getTrafdaypa() == null ? 0 : t.getTrafdaypa());
						ttrafsitcomm += (t.getTrafsitcomm() == null ? 0 : t.getTrafsitcomm());
						tgovComLeader += (t.getGovComLeader() == null ? 0 : t.getGovComLeader());
						tswdayinfo += (t.getSwdayinfo() == null ? 0 : t.getSwdayinfo());
						theadphonepa += (t.getHeadphonepa() == null ? 0 : t.getHeadphonepa());
						tswzhsubjectInfo += (t.getSwzhsubjectInfo() == null ? 0 : t.getSwzhsubjectInfo());
						tswdbsubjectInfo += (t.getSwdbsubjectInfo() == null ? 0 : t.getSwdbsubjectInfo());
						tgovLeader += (t.getGovLeader() == null ? 0 : t.getGovLeader());
						tsfwordinfo += (t.getSfwordinfo() == null ? 0 : t.getSfwordinfo());
						tworkdynamic += (t.getWorkdynamic() == null ? 0 : t.getWorkdynamic());
						tsfzhsubjectInfo += (t.getSfzhsubjectInfo() == null ? 0 : t.getSfzhsubjectInfo());
						tsfdbsubjectInfo += (t.getSfdbsubjectInfo() == null ? 0 : t.getSfdbsubjectInfo());
						tcomLeader += (t.getComLeader() == null ? 0 : t.getComLeader());
						tjwzhsubjectInfo += (t.getJwzhsubjectInfo() == null ? 0 : t.getJwzhsubjectInfo());
						tjwdbsubjectInfo += (t.getJwdbsubjectInfo() == null ? 0 : t.getJwdbsubjectInfo());
						tpubtypedynamic += (t.getPubtypedynamic() == null ? 0 : t.getPubtypedynamic());
						tcountyDynamic += (t.getCountyDynamic() == null ? 0 : t.getCountyDynamic());
						tnetInfo += (t.getNetInfo() == null ? 0 : t.getNetInfo());
						tpubTypewordinfo += (t.getPubTypewordinfo() == null ? 0 : t.getPubTypewordinfo());
						tdaily += (t.getDaily() == null ? 0 : t.getDaily());
						tyuegao += (t.getYuegao() == null ? 0 : t.getYuegao());
	
						ws.setRowView(jnum + 5, 300);// 设置行高
						ws.addCell(new Label(0, jnum + 3, t.getDeptCategory(), format1));
						ws.addCell(new Label(1, jnum + 3, t.getDeptName(), format1));
						ws.mergeCells(1, jnum + 3, 2, jnum + 3);// 合并单元格
						ws.addCell(new Label(3, jnum + 3, (t.getPubtypedynamic() == null ? ""
								: t.getPubtypedynamic().toString()), format1));
						ws.addCell(new Label(4, jnum + 3, (t.getCountyDynamic() == null ? ""
								: t.getCountyDynamic().toString()), format1));
						ws.addCell(new Label(5, jnum + 3, (t.getPubTypewordinfo() == null ? ""
								: t.getPubTypewordinfo().toString()), format1));
						ws.addCell(new Label(6, jnum + 3, (t.getNetInfo() == null ? ""
								: t.getNetInfo().toString()), format1));
						ws.addCell(new Label(7, jnum + 3,
								(t.getJwdbsubjectInfo() == null ? ""
										: t.getJwdbsubjectInfo().toString()), format1));
						ws.addCell(new Label(8, jnum + 3, (t.getSwzhsubjectInfo() == null ? ""
								: t.getSwzhsubjectInfo().toString()), format1));
						
						ws.addCell(new Label(9, jnum + 3,
								(t.getSfdbsubjectInfo() == null ? ""
										: t.getSfdbsubjectInfo().toString()), format1));
						ws.addCell(new Label(10, jnum + 3, (t.getSfzhsubjectInfo() == null ? ""
								: t.getSfzhsubjectInfo().toString()), format1));
						ws.addCell(new Label(11, jnum + 3,
								(t.getWorkdynamic() == null ? ""
										: t.getWorkdynamic().toString()), format1));
						ws.addCell(new Label(12, jnum + 3,
								(t.getSfwordinfo() == null ? ""
										: t.getSfwordinfo().toString()), format1));
						ws.addCell(new Label(13, jnum + 3,
								(t.getJwdbsubjectInfo() == null ? ""
										: t.getJwdbsubjectInfo().toString()), format1));
						ws.addCell(new Label(14, jnum + 3,
								(t.getJwzhsubjectInfo() == null ? ""
										: t.getJwzhsubjectInfo().toString()), format1));
						ws.addCell(new Label(15, jnum + 3,
								(t.getHeadphonepa() == null ? ""
										: t.getHeadphonepa().toString()), format1));
						ws.addCell(new Label(16, jnum + 3,
								(t.getSwdayinfo() == null ? ""
										: t.getSwdayinfo().toString()), format1));
						ws.addCell(new Label(17, jnum + 3,
								(t.getTrafsitcomm() == null ? ""
										: t.getTrafsitcomm().toString()), format1));
						ws.addCell(new Label(18, jnum + 3,
								(t.getTrafdaypa() == null ? ""
										: t.getTrafdaypa().toString()), format1));
						
						ws.addCell(new Label(19, jnum + 3,
								(t.getOtherdbsubjectInfo() == null ? ""
										: t.getOtherdbsubjectInfo().toString()), format1));
						ws.addCell(new Label(20, jnum + 3,
								(t.getOtherzhsubjectInfo() == null ? ""
										: t.getOtherzhsubjectInfo().toString()), format1));
						ws.addCell(new Label(21, jnum + 3, (t.getComLeader() == null ? ""
								: t.getComLeader()
								.toString()), format1));
						ws.addCell(new Label(22, jnum + 3,
								(t.getGovLeader() == null ? "" : t.getGovLeader()
										.toString()), format1));
						ws.addCell(new Label(23, jnum + 3, (t.getOtherLeader() == null ? ""
								: t.getOtherLeader().toString()), format1));
						ws.addCell(new Label(24, jnum + 3,
								(t.getShangb() == null ? ""
										: t.getShangb().toString()), format1));
						ws.addCell(new Label(25, jnum + 3,
								(t.getYuegao() == null ? ""
										: t.getYuegao().toString()), format1));
						ws.addCell(new Label(26, jnum + 3,
								(t.getTotal() == null ? "" : t.getTotal().toString()), format1));
						ws.addCell(new Label(27, jnum + 3,
								(t.getTotalYear() == null ? "" : t
										.getTotalYear().toString()), format1));
					}
				}
//				int j = textGovReportList.size();
				int j = jnum;
				jnum = 0;
				ws.addCell(new Label(0, j + 4, "合计", format1));
				ws.addCell(new Label(1, j + 4, "", format1));
				ws.mergeCells(1, j + 4, 2, j + 4);// 合并单元格
				//tpubtypedynamic=0f,tcountyDynamic=0f,tnetInfo=0f,tpubTypewordinfo=0f;
				ws.addCell(new Label(3, j + 4, tpubtypedynamic.toString(), format1));
				ws.addCell(new Label(4, j + 4, tcountyDynamic.toString(), format1));
				ws.addCell(new Label(5, j + 4, tpubTypewordinfo.toString(), format1));
				ws.addCell(new Label(6, j + 4, tnetInfo.toString(), format1));
				ws.addCell(new Label(7, j + 4, tjwdbsubjectInfo.toString(), format1));
				ws.addCell(new Label(8, j + 4, tjwzhsubjectInfo.toString(), format1));
				ws.addCell(new Label(9, j + 4, tsfdbsubjectInfo.toString(), format1));
				ws.addCell(new Label(10, j + 4, tsfzhsubjectInfo.toString(), format1));
				ws.addCell(new Label(11, j + 4, tworkdynamic.toString(), format1));
				ws.addCell(new Label(12, j + 4, tsfwordinfo.toString(), format1));
				ws.addCell(new Label(13, j + 4, tjwdbsubjectInfo.toString(), format1));
				ws.addCell(new Label(14, j + 4, tjwzhsubjectInfo.toString(), format1));
				ws.addCell(new Label(15, j + 4, theadphonepa.toString(), format1));
				ws.addCell(new Label(16, j + 4, tswdayinfo.toString(), format1));
				ws.addCell(new Label(17, j + 4, ttrafsitcomm.toString(), format1));
				ws.addCell(new Label(18, j + 4, ttrafdaypa.toString(), format1));
				ws.addCell(new Label(19, j + 4, totherdbsubjectInfo.toString(), format1));
				ws.addCell(new Label(20, j + 4, totherzhsubjectInfo.toString(), format1));
				ws.addCell(new Label(21, j + 4, tcomLeader.toString(), format1));
				ws.addCell(new Label(22, j + 4, tgovLeader.toString(), format1));
				ws.addCell(new Label(23, j + 4, totherLeader.toString(), format1));
				ws.addCell(new Label(24, j + 4, tshangb.toString(), format1));
				ws.addCell(new Label(25, j + 4, tyuegao.toString(), format1));
				ws.addCell(new Label(26, j + 4, ttotal.toString(), format1));
				ws.addCell(new Label(27, j + 4, ttotalYear.toString(), format1));

			}
        }  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}catch (WriteException e) {
				e.printStackTrace();
			} 
		}
	}

	@Override
	public void deleteGovPubRef(TextGovInfo textGovInfo) {
		textGovInfoDao.deleteGovPubRef(textGovInfo);
	}
	@Override
	public void reUpdate(TextGovInfo textGovInfo) {
		textGovInfoDao.reUpdate(textGovInfo);
		
	}

	/**
	 * 合并政务信息
	 * @param newsIds
	 * @param mainNewsId
	 */
	public void mergeNews(String newsIds, String mainNewsId){
		String[] ids= newsIds.split(",");
		String content = "", mergeId="",deptName = "",adoptType = "";
		//获取主信息
		TextNews news = textNewsDao.getTextNewsDetail(mainNewsId);
		//获取主信息的采编信息列表
		List<TextGovInfo> textGovInfoList = textGovInfoService.getTextGovInfoByNewsId(mainNewsId);
		boolean isSave = true;
		if(textGovInfoList != null && textGovInfoList.size() > 0){
			//TODO:需确认需求
			TextGovInfo t = textGovInfoList.get(0);
			content = t.getGiContent();
			deptName = t.getEntryDept();
			adoptType = t.getAdoptType();
			isSave = false;
		} else {
			content = news.getNewsContent();
			deptName = news.getDeptName();
		}
		mergeId = news.getMergeId(); //合并的ID
		for (String id : ids) {
			boolean isMerge= false;//是否合并
			if(StringUtils.isNotBlank(id) && !id.equals(mainNewsId)){
				if(StringUtils.isNotBlank(mergeId) ){
					if(!mergeId.contains(id)){
						isMerge = true;
						mergeId += ","+id;
					}
				} else {
					isMerge = true;
					mergeId = mainNewsId+","+id;
				}
			}
			//合并
			if(isMerge){
				TextNews newsInfo = textNewsDao.getTextNewsDetail(id);
				//获取主信息的采编信息列表
				List<TextGovInfo> govInfoList = textGovInfoService.getTextGovInfoByNewsId(id);
				TextGovInfo textGovInfo = new TextGovInfo();
				
				if(govInfoList != null && govInfoList.size() > 0){
//					TextGovInfo t = textGovInfoList.get(0);
					TextGovInfo t = govInfoList.get(0);
					content += t.getGiContent();
					deptName += "、" + t.getEntryDept();
				} else {
					content += newsInfo.getNewsContent();
					deptName += "、" +newsInfo.getDeptName();
					//添加采编信息
					textGovInfo = addGovInfo(newsInfo.getNewsContent(), null, newsInfo.getDeptName(), adoptType, newsInfo);
				}
				//分支积分
				if (!StringUtils.isBlank(adoptType)) {
					if(govInfoList != null && govInfoList.size() > 0){
						//修改采编信息
						textGovInfo = govInfoList.get(0);
						textGovInfo.setAdoptType(adoptType);
						textGovInfoService.update(textGovInfo);
					}
					//添加积分
					deptScoreService.govInfoScore(textGovInfo);
					//修改新闻状态
					newsInfo.setGovUseFlag(news.getGovUseFlag());
					textNewsDao.updateTextNews(newsInfo);
				}
			}
			
		}
		
		//修改主新闻采编信息
		if(isSave){
			news.setNewsContent(content);
			addGovInfo(content, mergeId, deptName, null, news);
		} else {
			TextGovInfo govInfo = textGovInfoList.get(0);
			govInfo.setEntryDept(deptName);
			govInfo.setGiContent(content);	
			govInfo.setMergeId(mergeId);
			textGovInfoService.update(govInfo);
		}
		//修改主新闻信息
		news.setMergeId(mergeId);
		textNewsDao.updateTextNews(news);
		
	}

	private TextGovInfo addGovInfo(String content, String mergeId, String deptName, String adoptType, TextNews news) {
		TextGovInfo textGovInfo = new TextGovInfo();
		textGovInfo.setGiId(UUID.randomUUID().toString().replace("-", ""));
		textGovInfo.setNewsId(news.getNewsId());
		textGovInfo.setGiTitle(news.getNewsTitle());
		textGovInfo.setGiContent(content);
		textGovInfo.setEntryUser(news.getEntryUser());
		textGovInfo.setEntryDate(news.getEntryDate());
		textGovInfo.setEntryDept(deptName);
		textGovInfo.setCreateDate(DateUtil.getCurrentDateStr());
		textGovInfo.setCreateBy(news.getCreateBy());
		textGovInfo.setPubId("0");
		textGovInfo.setIsDel("0");
		textGovInfo.setIsReport("3");
		textGovInfo.setNewsAuthor(news.getNewsAuthor());
		textGovInfo.setMergeId(mergeId);
		textGovInfo.setAdoptType(adoptType);
		textGovInfoService.save(textGovInfo);
		return textGovInfo;
	}
	
	/**
	 * isSelected 主信息ID
	 */
	@Override
	public void mergeInformation(String newsIds,String isSelected) {
		String[] ids = newsIds.split(",");
		String content = "";
		String content2= "";
		String deptName = "";
		String deptName2 = "";
		String mergeId = "";
		String mergeId1 = "";
		String mergeId2 = "";
		//获取主信息
		TextNews news = textNewsDao.getTextNewsDetail(isSelected);
		//获取主信息的采编信息列表
		List<TextGovInfo> textGovInfoList = textGovInfoService.getTextGovInfoByNewsId(isSelected);
		if(textGovInfoList.size()>0){
			TextGovInfo t = textGovInfoList.get(0);
			content = t.getGiContent();
			deptName = t.getEntryDept();
		}else{
			content = news.getNewsContent();
			deptName = news.getDeptName();
		}
		//已合并的新闻
		mergeId = news.getMergeId();
		mergeId1 = news.getMergeId()+",";
		for(int i=0 ; i < ids.length; i++){	
			logger.debug(ids[i]);
			if(!StringUtils.isBlank(ids[i])){
				TextNews textNews = textNewsDao.getTextNewsDetail(ids[i]);
				int GovUseFlag = textNews.getGovUseFlag();
				if (GovUseFlag==1){
				textNews.setGovUseFlag(1);
				}
				else {
					textNews.setGovUseFlag(2);
				}	
				
				if(ids[i].equals(isSelected)==false){
					List<TextGovInfo> textGovInfoListN = textGovInfoService.getTextGovInfoByNewsId(ids[i]);
					if(textGovInfoListN.size()>0){
						TextGovInfo m = textGovInfoListN.get(0);
						content += m.getGiContent();
						deptName += "、"+ m.getEntryDept();
					}else{
						content += textNews.getNewsContent();
						deptName += "、"+textNews.getDeptName();
					}
					if(mergeId==null){
						mergeId = textNews.getNewsId();
					} else{
						mergeId += ","+textNews.getNewsId();
					}
					if(textNews.getMergeId()!=null){
						mergeId += ","+textNews.getMergeId();
					}	
					textNewsDao.updateTextNews(textNews);
					List<TextGovInfo> textGovInfoLists = textGovInfoService.getTextGovInfoByNewsId(textNews.getNewsId());
					if(textGovInfoLists.size()>0){
						/*TextGovInfo textGovInfo = textGovInfoLists.get(0);
						textGovInfo.setEntryDept(deptName2);
						textGovInfo.setGiContent(content2);	
						textGovInfo.setMergeId(mergeId2);
						textGovInfoService.update(textGovInfo);*/
					}else{
						TextGovInfo textGovInfo = new TextGovInfo();
						textGovInfo.setGiId(UUID.randomUUID().toString().replace("-", ""));
						textGovInfo.setNewsId(textNews.getNewsId());
						textGovInfo.setGiTitle(textNews.getNewsTitle());
						textGovInfo.setGiContent(textNews.getNewsContent());
						textGovInfo.setEntryUser(textNews.getEntryUser());
						textGovInfo.setEntryDate(textNews.getEntryDate());
						textGovInfo.setEntryDept(textNews.getDeptName());
						textGovInfo.setCreateDate(DateUtil.getCurrentDateStr());
						textGovInfo.setCreateBy(textNews.getCreateBy());
						textGovInfo.setPubId("0");
						textGovInfo.setIsDel("0");
						textGovInfo.setIsReport("3");
						textGovInfo.setNewsAuthor(textNews.getNewsAuthor());
						
						textGovInfoService.save(textGovInfo);
					}
				}
			}
		}
		content2=content;//.replaceFirst(content1, "");		
		deptName2=deptName;//.replaceFirst(deptName1, "");	
		mergeId2=mergeId;//.replaceFirst(mergeId1, "");	
		logger.debug(content2);
		logger.debug(deptName2);
		logger.debug(mergeId2);
		news.setMergeId(mergeId2);
		textNewsDao.updateTextNews(news);
		if (news.getGovUseFlag()==0){
			if(textGovInfoList.size()>0){
				TextGovInfo textGovInfo = textGovInfoService.getTextInfo(isSelected);
				textGovInfo.setEntryDept(deptName2);
				textGovInfo.setGiContent(content2);	
				textGovInfo.setMergeId(mergeId2);
				textGovInfoService.update(textGovInfo);
			} else{
				TextGovInfo textGovInfo = new TextGovInfo();
				textGovInfo.setGiId(UUID.randomUUID().toString().replace("-", ""));
				textGovInfo.setNewsId(news.getNewsId());
				textGovInfo.setGiTitle(news.getNewsTitle());
				textGovInfo.setGiContent(content2);
				textGovInfo.setEntryUser(news.getEntryUser());
				textGovInfo.setEntryDate(news.getEntryDate());
				textGovInfo.setEntryDept(deptName2);
				textGovInfo.setCreateDate(DateUtil.getCurrentDateStr());
				textGovInfo.setCreateBy(news.getCreateBy());
				textGovInfo.setPubId("0");
				textGovInfo.setIsDel("0");
				textGovInfo.setIsReport("3");
				textGovInfo.setMergeId(mergeId2);
				textGovInfo.setNewsAuthor(news.getNewsAuthor());
				
				textGovInfoService.save(textGovInfo);
			}
		}
		else{
			TextGovInfo textGovInfo = textGovInfoService.getTextInfo(isSelected);
			textGovInfo.setEntryDept(deptName2);
			textGovInfo.setGiContent(content2);	
			textGovInfo.setMergeId(mergeId2);
			textGovInfoService.update(textGovInfo);
		}
			
	}
	@Override
	public List<TextGovInfo> getGov(String pubId) {
		return textGovInfoDao.getGov(pubId);
	}
	@Override
	public TextGovInfo getTextInfo(String newsId) {
		return textGovInfoDao.getTextInfo(newsId);
	}

	@Override
	public List<TextGovReport> queryGovReportStat(Date startDate, Date endDate,
			String status) {
		
		List<TextGovReport> reportList = textGovInfoDao.queryGovReportStat(startDate, endDate, status);
		
		if(reportList != null && reportList.size() > 0){
			//查询基础积分 添加积分类型代码 llj 20170623
//			String[] codes = {"dailyInfo","subjectInfo","cityCommittee","cityGovernment","trafficDept","comLeaderAppr","govLeaderAppr","trafLeaderAppr","sendGov"};
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
//			Float dailyInfoScore = (float) 0, subjectInfoScore = (float) 0, cityCommitteeScore = (float) 0, 
//					cityGovernmentScore = (float) 0, trafficDeptScore = (float) 0, comLeaderApprScore = (float) 0, 
//					govLeaderApprScore = (float) 0, trafLeaderApprScore = (float) 0, sendGovScore = (float) 0;
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
			
			for (TextGovReport govReport : reportList) {
				//计算得分
				Float totalScore = (float)0;
				//投稿得分
				Float shangbScore = null;
				if(govReport.getShangb() != null){
					shangbScore = govReport.getShangb() * baseShangbScore;
					totalScore += shangbScore;
				}
				govReport.setShangbScore(shangbScore);
				//老数据专报信息数量
				Float jwdbsubjectInfoNum = null;
				if(govReport.getSubject() != null){
					jwdbsubjectInfoNum = govReport.getSubject() * basejwdbsubjectInfoNum;
					totalScore += jwdbsubjectInfoNum;
				}
				if(govReport.getJwdbsubjectInfo() != null){
					jwdbsubjectInfoNum += govReport.getJwdbsubjectInfo() * basejwdbsubjectInfoNum;
					totalScore += govReport.getJwdbsubjectInfo() * basejwdbsubjectInfoNum;
				}
				govReport.setJwdbsubjectInfoNum(jwdbsubjectInfoNum);
				
				//老数据交通部采用数量
				Float trafsitcommNum = null;
				if(govReport.getTraffic() != null){
					trafsitcommNum = govReport.getTraffic() * basetrafsitcommNum;
					totalScore += trafsitcommNum;
				}
				if(govReport.getTrafsitcomm() != null){
					trafsitcommNum += govReport.getTrafsitcomm() * basetrafsitcommNum;
					totalScore += govReport.getTrafsitcomm() * basetrafsitcommNum;
				}
				govReport.setTrafsitcommNum(trafsitcommNum);
				
				//老数据市委采用数量
				Float swdbsubjectInfoNum = null;
				if(govReport.getCityCom() != null){
					swdbsubjectInfoNum = govReport.getCityCom() * baseswdbsubjectInfoNum;
					totalScore += swdbsubjectInfoNum;
				}
				if(govReport.getSwdbsubjectInfo() != null){
					swdbsubjectInfoNum += govReport.getSwdbsubjectInfo() * baseswdbsubjectInfoNum;
					totalScore += govReport.getSwdbsubjectInfo() * baseswdbsubjectInfoNum;
				}
				govReport.setSwdbsubjectInfoNum(swdbsubjectInfoNum);
				
				//老数据市府采用数量
				Float sfdbsubjectInfoNum = null;
				if(govReport.getCityGov() != null){
					sfdbsubjectInfoNum = govReport.getCityGov() * basesfdbsubjectInfoNum;
					totalScore += sfdbsubjectInfoNum;
				}
				if(govReport.getSfdbsubjectInfo() != null){
					sfdbsubjectInfoNum += govReport.getSfdbsubjectInfo() * basesfdbsubjectInfoNum;
					totalScore += govReport.getJwdbsubjectInfo() * basesfdbsubjectInfoNum;
				}
				govReport.setSfdbsubjectInfoNum(sfdbsubjectInfoNum);
				
				//老数据每日信息数量及工作动态
				Float pubtypedynamicNum = null;
				if(govReport.getDaily() != null){
					pubtypedynamicNum = govReport.getDaily() * basepubtypedynamicNum;
					totalScore += pubtypedynamicNum;
				}
				if(govReport.getPubtypedynamic() != null){
					pubtypedynamicNum += govReport.getSfdbsubjectInfo() * basepubtypedynamicNum;
					totalScore += govReport.getJwdbsubjectInfo() * basepubtypedynamicNum;
				}
				govReport.setPubtypedynamicNum(pubtypedynamicNum);
				
				//交委综合专题信息数量
				Float jwzhsubjectInfoNum = null;
				if(govReport.getJwzhsubjectInfo() != null){
					jwzhsubjectInfoNum = govReport.getJwzhsubjectInfo() * basejwzhsubjectInfoNum;
					totalScore += jwzhsubjectInfoNum;
				}
				govReport.setJwzhsubjectInfoNum(jwzhsubjectInfoNum);
				
				//交通部日报数量
				Float trafdaypaNum = null;
				if(govReport.getTrafdaypa() != null){
					trafdaypaNum = govReport.getTrafdaypa() * basetrafdaypaNum;
					totalScore += trafdaypaNum;
				}
				govReport.setTrafdaypaNum(trafdaypaNum);
				//市委综合数量
				Float swzhsubjectInfoNum = null;
				if(govReport.getSwzhsubjectInfo() != null){
					swzhsubjectInfoNum = govReport.getSwzhsubjectInfo() * baseswzhsubjectInfoNum;
					totalScore += swzhsubjectInfoNum;
				}
				govReport.setSwzhsubjectInfoNum(swzhsubjectInfoNum);
				//市府综合数量
				Float sfzhsubjectInfoNum = null;
				if(govReport.getSfzhsubjectInfo() != null){
					sfzhsubjectInfoNum = govReport.getSfzhsubjectInfo() * basesfzhsubjectInfoNum;
					totalScore += sfzhsubjectInfoNum;
				}
				govReport.setSfzhsubjectInfoNum(sfzhsubjectInfoNum);
				//市委批示数量
				Float GovComLeaderNum = null;
				if(govReport.getGovComLeader() != null){
					GovComLeaderNum = govReport.getGovComLeader() * baseGovComLeaderNum;
					totalScore += GovComLeaderNum;
				}
				govReport.setGovComLeaderNum(GovComLeaderNum);
				//市府批示数量
				Float govLeaderNum = null;
				if(govReport.getGovLeader() != null){
					govLeaderNum = govReport.getGovLeader() * baseGovLeaderNum;
					totalScore += govLeaderNum;
				}
				govReport.setGovLeaderNum(govLeaderNum);
				//交委批示数量
				Float comLeaderNum = null;
				if(govReport.getComLeader() != null){
					comLeaderNum = govReport.getComLeader() * baseComLeaderNum;
					totalScore += comLeaderNum;
				}
				govReport.setComLeaderNum(comLeaderNum);
				//交通部批示数量
				Float trafLeaderNumn = null;
				if(govReport.getTrafLeader() != null){
					trafLeaderNumn = govReport.getTrafLeader() * baseTrafLeaderNum;
					totalScore += trafLeaderNumn;
				}
				govReport.setTrafLeaderNum(trafLeaderNumn);
				//市府工作动态数量
				Float workdynamicNum = null;
				if(govReport.getWorkdynamic() != null){
					workdynamicNum = govReport.getWorkdynamic() * baseworkdynamicNum;
					totalScore += workdynamicNum;
				}
				govReport.setWorkdynamicNum(workdynamicNum);
				//市府一句话新闻数量
				Float sfwordinfoNum = null;
				if(govReport.getSfwordinfo() != null){
					sfwordinfoNum = govReport.getSfwordinfo() * basesfwordinfoNum;
					totalScore += sfwordinfoNum;
				}
				govReport.setShangbScore(sfwordinfoNum);
				//市委一把手手机报数量
				Float headphonepaNum = null;
				if(govReport.getHeadphonepa() != null){
					headphonepaNum = govReport.getHeadphonepa() * baseheadphonepaNum;
					totalScore += headphonepaNum;
				}
				govReport.setHeadphonepaNum(headphonepaNum);
				//市委每日要情数量
				Float swdayinfoNum = null;
				if(govReport.getSwdayinfo() != null){
					swdayinfoNum = govReport.getSwdayinfo() * baseswdayinfoNum;
					totalScore += swdayinfoNum;
				}
				govReport.setSwdayinfoNum(swdayinfoNum);
				//上级单位独编数量
				Float otherdbsubjectInfoNum = null;
				if(govReport.getOtherdbsubjectInfo() != null){
					otherdbsubjectInfoNum = govReport.getOtherdbsubjectInfo() * baseotherdbsubjectInfoNum;
					totalScore += otherdbsubjectInfoNum;
				}
				govReport.setOtherdbsubjectInfoNum(otherdbsubjectInfoNum);
				//上级单位综合数量
				Float otherzhsubjectInfoNum = null;
				if(govReport.getOtherzhsubjectInfo() != null){
					otherzhsubjectInfoNum = govReport.getOtherzhsubjectInfo() * baseotherzhsubjectInfoNum;
					totalScore += otherzhsubjectInfoNum;
				}
				govReport.setOtherzhsubjectInfoNum(otherzhsubjectInfoNum);
				//区县动态数量
				Float countyDynamicNum = null;
				if(govReport.getCountyDynamic() != null){
					countyDynamicNum = govReport.getCountyDynamic() * basecountyDynamicNum;
					totalScore += countyDynamicNum;
				}
				govReport.setCountyDynamicNum(countyDynamicNum);
				//网络信息数量
				Float netInfoNum = null;
				if(govReport.getNetInfo() != null){
					netInfoNum = govReport.getNetInfo() * basenetInfoNum;
					totalScore += netInfoNum;
				}
				govReport.setNetInfoNum(netInfoNum);
				//工作动态数量
				Float pubTypewordinfoNum = null;
				if(govReport.getPubTypewordinfo() != null){
					pubTypewordinfoNum = govReport.getPubTypewordinfo() * basepubTypewordinfoNum;
					totalScore += pubTypewordinfoNum;
				}
				govReport.setPubTypewordinfoNum(pubTypewordinfoNum);
				//上级批示
				Float otherLeaderApprNum = null;
				if(govReport.getOtherLeader() != null){
					otherLeaderApprNum = govReport.getOtherLeader() * baseOtherLeaderApprNum;
					totalScore += otherLeaderApprNum;
				}
				govReport.setOtherLeaderNum(otherLeaderApprNum);
				//投稿得分
				Float yuegaoNum = null;
				if(govReport.getYuegao() != null){
					yuegaoNum = govReport.getYuegao() * baseyuegaoNum;
					totalScore += yuegaoNum;
				}
				govReport.setYuegaoNum(yuegaoNum);

				//累计得分total
				govReport.setTotal(totalScore);
			}
		}
		
		return reportList;
	}
	
	public Map<String,Float> getCheckStandardScore(){
		//查询基础积分 添加积分类型代码 llj 20170623
//		String[] codes = {"dailyInfo","subjectInfo","cityCommittee","cityGovernment","trafficDept","comLeaderAppr","govLeaderAppr","trafLeaderAppr","sendGov"};
		String[] codes = {"dailyInfo","subjectInfo","cityCommittee","cityGovernment",
				"trafficDept","comLeaderAppr","govLeaderAppr","trafLeaderAppr","sendGov",
				"jwdbsubjectInfo","jwzhsubjectInfo","sfdbsubjectInfo","sfzhsubjectInfo","workdynamic",
				"sfwordinfo","otherLeaderAppr","swdbsubjectInfo","swzhsubjectInfo","headphonepa","swdayinfo","trafsitcomm",
				"trafdaypa","otherdbsubjectInfo","otherzhsubjectInfo","pubtypedynamic","pubTypewordinfo","countyDynamic",
				"netInfo","dayinfon","yuegao","govcomLeaderAppr"};
		
		List<CheckStandard> stanList = checkStandardDao.queryCheckStandard(codes);
		Map<String, Float> result = new HashMap<String, Float>();
		if(stanList != null && stanList.size() > 0){
			for (CheckStandard cs : stanList) {
				result.put(cs.getCode(), cs.getScore());
			}
		}
		return result;
	}
	
	public List<GovNewsReport> queryGovNewsReport(Date startDate, Date endDate, String status, Integer isUse){
		return textGovInfoDao.queryGovNewsReport(startDate, endDate, status, isUse);
	}
	
	public Map<Integer,Integer> queryDeptShangbaoStat(Date startDate, Date endDate, String status, Integer isUse){
		endDate = startDate;
		startDate = DateUtil.getYearFirst(0, startDate);
		List<Map<String,Object>> list =  textGovInfoDao.queryDeptShangbaoStat(startDate, endDate, status, isUse);
		Map<Integer,Integer> result = new HashMap<Integer, Integer>();
		if(list != null && list.size() > 0){
			for (Map<String, Object> map : list) {
				int deptId = Integer.parseInt(String.valueOf(map.get("deptId")));
				int submitNum = Integer.parseInt(String.valueOf(map.get("submitNum")));
				result.put(deptId, submitNum);
			}
		}
		return result;
	}

	@Override
	public void syncGovInfoContent(TextGovInfo textGovInfo) {
		textGovInfoDao.syncGovInfoContent(textGovInfo);
		
	}
}
