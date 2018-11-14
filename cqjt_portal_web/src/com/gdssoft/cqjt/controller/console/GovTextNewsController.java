package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.TextPublication;
import com.gdssoft.cqjt.pojo.videoNews.VideoRecord;
import com.gdssoft.cqjt.service.CheckStandardService;
import com.gdssoft.cqjt.service.DepartmentScoreService;
import com.gdssoft.cqjt.service.TextGovInfoService;
import com.gdssoft.cqjt.service.TextNewsService;
import com.gdssoft.cqjt.service.TextPublicationService;
import com.gdssoft.cqjt.service.cms.NewsService;
import com.gdssoft.cqjt.service.videoNews.VideoRecordService;
import com.gdssoft.cqjt.util.PortalUtils;

@Controller
@RequestMapping("/console/govTextNews.xhtml")
public class GovTextNewsController extends BaseController {

	@Autowired
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsService;

	@Resource(name = "textPublicationServiceImpl")
	private TextPublicationService textPublicationService;

	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;

	@Resource(name = "deptScoreServiceImpl")
	private DepartmentScoreService deptScoreService;
	
	@Autowired
	@Resource(name = "checkStandardServiceImpl")
	private CheckStandardService checkStandardService;

	// @Resource(name = "departmentServiceImpl")
	// private DepartmentService departmentService;

	// @Resource(name = "reportFormServiceImpl")
	// private ReportFormService reportFormService;

	@Autowired
	@Resource(name = "videoRecordServiceImpl")
	private VideoRecordService videoRecordService;

	@Autowired
	@Resource(name = "cmsNewsService")
	private NewsService cmsNewsService;

	/**
	 * 政务上报管理 显示详细信息
	 * 
	 * @author H2602965
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=showGovInfoDetails")
	public String showGovInfoDetails(Model model,
			@RequestParam("newsId") String newsId) {

		TextGovInfo textGovInfo = textGovInfoService.getTextGovInfoByNewsId(
				newsId).get(0);
		model.addAttribute("GovInfoDetail", textGovInfo);

		return "console/textnews/govInfo/govInfoDetails";
	}

	/**
	 * 政务上报管理编辑页面
	 * 
	 * @author H2602965 20140605
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=editInfoReportView")
	public String editInfoReport(Model model, @RequestParam("giId") String giId) {
		TextGovInfo textGovInfo = textGovInfoService.getTextGovInfo(giId);
		// .getTextGovInfoByNewsId(newsId);
		model.addAttribute("InfoReportDetail", textGovInfo);

		List<VideoRecord> newsRecord = videoRecordService
				.getVideoRecordList(textGovInfo.getNewsId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(textGovInfo.getEntryDate() != null){
			String entryDateStr = format.format(textGovInfo.getEntryDate());
			textGovInfo.setEntryDateStr(entryDateStr);
		}
		model.addAttribute("textPublication", textPublicationService.queryPubInfoList("", "", null, null, 0, 9999));
		model.addAttribute("newsRecord", newsRecord);
		return "console/textnews/govInfo/editInfoReporte";
	}

	/**
	 * 政务上报管理编辑页面
	 * 
	 * @author H2602965 20140605
	 * @param textGovInfo
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=saveInfoReport")
	public void saveInfoReport(
			@ModelAttribute("textGovInfo") TextGovInfo textGovInfo,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		
		if(StringUtils.isNotBlank(textGovInfo.getEntryDateStr())){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				textGovInfo.setEntryDate(format.parse(textGovInfo.getEntryDateStr()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		System.out.println("${InfoReportDetail.entryDate}----------->"+textGovInfo.getEntryDate());
		System.out.println("${InfoReportDetail.getCreateDate}----------->"+textGovInfo.getCreateDate());
		textGovInfoService.update(textGovInfo);
		this.writeMessage(response, "保存成功！");
		/*
		 * String reportType1=textGovInfo.getReportType(); String
		 * reportType2=reportType1.substring(0, reportType1.length()-1); String
		 * reportType[] = reportType2.split(",");
		 */

		VideoRecord videoRecord = new VideoRecord();
		videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
		videoRecord.setVideoId(textGovInfo.getNewsId());
		videoRecord.setFlag("8");
		videoRecord.setUpdateUser(SystemContext.getUserName());
		videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
		videoRecordService.update(videoRecord);

		if(StringUtils.isNotBlank(textGovInfo.getAdoptType())){
			//上报是修改状态   by 2016-1-28 llj
			// 更新投稿信息字段gov_use_flag为采用状态
			String isSelected = textGovInfo.getIsSelected();
//			String dept = textGovInfo.getEntryDept();
			logger.debug("dept=" + textGovInfo.getEntryDept());
			textNewsService.updateGovUseFlagReport(textGovInfo.getNewsId());
			logger.debug("is_selected=" + isSelected);

		}
		
		/*
		 * 政务信息上报管理上报时加分
		 */
		String str = textGovInfo.getAdoptType();
		logger.debug(str);
		if (!StringUtils.isBlank(str)) {
			deptScoreService.govInfoReportScore(textGovInfo);
		}

		// 上报时被合并的信息也要同时加分
		String mergeIds = textGovInfo.getMergeId();
		String newsId = "";
		if (!StringUtils.isBlank(mergeIds)) {
			String[] mergeId = mergeIds.split(",");
			for (int i = 0; i < mergeId.length; i++) {
				newsId = mergeId[i];
				TextGovInfo govInfo = new TextGovInfo();
				govInfo = textGovInfoService.getTextGovInfoByNewsId(newsId)
						.get(0);
				govInfo.setAdoptType(textGovInfo.getAdoptType());
				govInfo.setReportType(textGovInfo.getReportType());
				textGovInfoService.update(govInfo);
				if (govInfo.getAdoptType().length() != 0) {
					deptScoreService.govInfoReportScore(govInfo);
				}
			}
		}
	}

	/**
	 * 重庆交通政务办公网--政务信息列表显示
	 * 
	 * @author zhangpeng 20140530
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listGovInfoView")
	public String listGovInfo(Model model) {
		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		int pageIndex = 0;
		String deptName = "";
		TextGovInfo textGovInfo;
		List<TextNews> textNewsList = textNewsService.getGovNewsList(null, null, new String[] { "1", "2", "3", "7", "8", "9" }, pageIndex, SystemContext.getDefaultPageSize());
		for (TextNews t : textNewsList) {
			List<TextGovInfo> textGovInfoList = textGovInfoService .getTextGovInfoByNewsId(t.getNewsId());
			if(textGovInfoList != null && textGovInfoList.size() > 0){
				if (t.getGovUseFlag()!=0) {
					textGovInfo = textGovInfoList.get(0);
					deptName = textGovInfo.getEntryDept();
					t.setDeptName(deptName);
					t.setNewsTitle(textGovInfo.getGiTitle());
				} else {
					if (!StringUtils.isBlank(t.getMergeId())) {
						if (textGovInfoList.size() > 0) {
							textGovInfo = textGovInfoList.get(0);
							deptName = textGovInfo.getEntryDept();
							t.setDeptName(deptName);
							t.setNewsTitle(textGovInfo.getGiTitle());
						}
					}
				}
			}
		}
		/*
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 */
		model.addAttribute("paginations", textNewsList);

		return "console/textnews/govInfo/listGovInfo";
	}

	/**
	 * 政务信息查询
	 * 
	 * @author H2602975 zhangpeng
	 * @param model
	 * @param newsId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=searchGovInfoView")
	public String queryByUserDate(@RequestParam("newsTitle") String newsTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("newsTag") String newsTag,
			@RequestParam("zxx") String zxx, @RequestParam("hb") String hb,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		List<TextNews> textNewsList = new ArrayList<TextNews>();
		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		entryUser = URLDecoder.decode(entryUser, "utf-8");
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		textNewsList = textNewsService.getGovNewsList(newsTitle, entryUser,
				start, end, new String[] { "1", "2", "3", "7", "8", "9" },
				pageIndex, SystemContext.getDefaultPageSize());
		model.addAttribute("start", start);
		model.addAttribute("end", end);

		String deptName = "";
		TextGovInfo textGovInfo;
		for (TextNews t : textNewsList) {
			List<TextGovInfo> textGovInfoList = textGovInfoService.getTextGovInfoByNewsId(t.getNewsId());
			if (t.getGovUseFlag()!=0) {
				if (textGovInfoList.size() > 0) {
					textGovInfo = textGovInfoList.get(0);
					deptName = textGovInfo.getEntryDept();
					t.setDeptName(deptName);
					t.setNewsTitle(textGovInfo.getGiTitle());
				}
			} else {
				if (!StringUtils.isBlank(t.getMergeId())) {
					if (textGovInfoList.size() > 0) {
						textGovInfo = textGovInfoList.get(0);
						deptName = textGovInfo.getEntryDept();
						t.setDeptName(deptName);
						t.setNewsTitle(textGovInfo.getGiTitle());
					}
				}
			}
		}
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("paginations", textNewsList);
		model.addAttribute("zxx", zxx);
		model.addAttribute("hb", hb);
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);
		return "console/textnews/govInfo/listGovInfo";
	}

	/**
	 * 政务信息采编
	 * 
	 * @author zhangpeng 20140603
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=adoptGovInfoView")
	public String adoptGovInfo(Model model, @RequestParam("newsId") String newsId, HttpServletRequest request) {
		logger.debug(newsId);
		
		String newsTitle = request.getParameter("newsTitle");
		String entryUser = request.getParameter("entryUser");
		String entryDateS = request.getParameter("entryDateS");
		String entryDateE = request.getParameter("entryDateE");
		String pageIndex = request.getParameter("pageIndex");
		String newsTag = request.getParameter("newsTag");
		String zxx = request.getParameter("zxx");
		String hb = request.getParameter("hb");
		
		List<VideoRecord> newsRecord = videoRecordService.getVideoRecordList(newsId);
		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		List<TextGovInfo> textGovInfoList = textGovInfoService.getTextGovInfoByNewsId(newsId);
		TextGovInfo textGovInfo = null;
		String reportType = null;
		String adoptType = null;
		
//			if (textNews.getGovUseFlag()!=0) {
		if (textGovInfoList != null && textGovInfoList.size() > 0) {
			textGovInfo = textGovInfoList.get(0);
			String deptName = textGovInfo.getEntryDept();
			String content = textGovInfo.getGiContent();
			textNews.setDeptName(deptName);
			textNews.setNewsContent(content);
			reportType = textGovInfo.getReportType();//上报类别
			adoptType = textGovInfo.getAdoptType();//采用类别
		} 

		model.addAttribute("newsRecord", newsRecord);
		model.addAttribute("userNo", SystemContext.getUserNO());
		model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("govNewsInfo", textNews);
		model.addAttribute("adoptType", adoptType);
		model.addAttribute("reportType", reportType);
		
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("entryDateS", entryDateS);
		model.addAttribute("entryDateE", entryDateE);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("newsTag", newsTag);
		model.addAttribute("zxx", zxx);
		model.addAttribute("hb", hb);

		model.addAttribute("textPublication", textPublicationService.queryPubInfoList("", "", null, null, 0, 9999));

		return "console/textnews/govInfo/adoptGovInfo";
	}

	/**
	 * 政务信息对未采编信息编辑后保存功能
	 * 
	 * @author H2602975 zhangpeng
	 * @param textGovInfo
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=saveGovInfo", method = RequestMethod.POST)
	public void saveGovInfo(
			@ModelAttribute("textGovInfo") TextGovInfo textGovInfo,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		
		if (StringUtils.isBlank(textGovInfo.getGiId())) {
			String mergeIds = textGovInfo.getMergeId();
			// 创建时间为当前服务器时间,也就是从text_news表取出数据后存入text_gov_info的服务器时间
			textGovInfo.setCreateDate(DateUtil.getCurrentDateStr());
			textGovInfo.setIsReport("0");
			textGovInfo.setEntryDate(DateUtil.formatCSTDate(request.getParameter("entryDate")));
			List<TextGovInfo> govList = textGovInfoService.getTextGovInfoByNewsId(textGovInfo.getNewsId());
			if (govList !=null && govList.size()>0) {
				textGovInfoService.update(textGovInfo);
			} else {
				textGovInfo.setGiId(UUID.randomUUID().toString().replace("-", ""));
				// 从前台获取entryDate,保持和text_news表中日期一致.
				String entryDate = request.getParameter("entryDate");
				textGovInfo.setEntryDate(DateUtil.formatCSTDate(entryDate));
				logger.debug("entryDate=" + DateUtil.formatCSTDate(entryDate));
				textGovInfo.setIsDel("0");
				textGovInfo.setCreateBy(textGovInfo.getEntryUser());
				logger.debug("createDate=" + textGovInfo.getCreateDate());
				textGovInfoService.save(textGovInfo);
			}
			
			// 增加处理记录
			VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(textGovInfo.getNewsId());
			String flag = "4";
			if(StringUtils.isNotBlank(textGovInfo.getAdoptType())){
				flag = "5";
			} else if(StringUtils.isNotBlank(textGovInfo.getReportType())){
				flag = "8";
			}
			videoRecord.setFlag(flag);
			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);
			
			System.out.println(textGovInfo.getAdoptType());
			if(StringUtils.isNotBlank(textGovInfo.getAdoptType())){
				//记录采编条数、积分
				System.out.println("记录采编条数、积分");
				// 更新投稿信息字段gov_use_flag为采用状态
				String isSelected = textGovInfo.getIsSelected();
				String dept = textGovInfo.getEntryDept();
				logger.debug("dept=" + textGovInfo.getEntryDept());
				//TODO:
				if(StringUtils.isNotBlank(textGovInfo.getAdoptType()) && textGovInfo.getAdoptType().contains("subjectInfo")){
					isSelected = "1";
				}else if (textGovInfo.getReportType() != null && textGovInfo.getAdoptType() != null) {
					/** 不选择采用类别，只选择上报类别时，状态改为 未采编 GuoY **/
					String isSelectAdopt="";
					String isSelectReport="";
					String[] adoptTypes = textGovInfo.getAdoptType().split(",");
					String[] reportTypes = textGovInfo.getReportType().split(",");
					// 选择上报类别
					for (int i = 0; i < reportTypes.length; i++) {
						if(null != reportTypes[i] && !"".equals(reportTypes[i])) {
							isSelectReport = "1";
							break;
						}
					}
					// 选择采用类别
					for (int i = 0; i < adoptTypes.length; i++) {
						if(null != adoptTypes[i] && !"".equals(adoptTypes[i])) {
							isSelectAdopt = "1";
							break;
						}
					}
					if ("".equals(isSelectAdopt) && "1".equals(isSelectReport)) {
						isSelected = "2";
					}
				}
				
				textNewsService.updateGovUseFlag(textGovInfo.getNewsId(),isSelected, dept);
				logger.debug("is_selected=" + isSelected);
	
				
	
				/*
				 * 政务信息采编时加分
				 */
				String str = textGovInfo.getAdoptType();
				logger.debug(str);
				if (!StringUtils.isBlank(str)) {
					deptScoreService.govInfoScore(textGovInfo);
				}
	
				// 采编时被合并的信息也要同时加分
				String newsId = "";
				if (!StringUtils.isBlank(mergeIds)) {
					String[] mergeId = mergeIds.split(",");
					for (int i = 0; i < mergeId.length; i++) {
						newsId = mergeId[i];
						TextGovInfo govInfo = new TextGovInfo();
						govInfo = textGovInfoService.getTextGovInfoByNewsId(newsId).get(0);
						govInfo.setAdoptType(textGovInfo.getAdoptType());
						govInfo.setReportType(textGovInfo.getReportType());
						 
						//修改采用状态 by llj
						textNewsService.updateGovUseFlag(govInfo.getNewsId(),isSelected, dept);
						textGovInfoService.update(govInfo);
						String type = govInfo.getAdoptType();
						if (!StringUtils.isBlank(type)) {
							deptScoreService.govInfoScore(govInfo);
						}
					}
				}
				
			} else {
				System.out.println("不做任何操作");
			}

			// 与 政务信息报送查询 同步内容  GuoY
			TextNews textNews = new TextNews();
			textNews.setNewsId(textGovInfo.getNewsId());
			textNews.setNewsContent(textGovInfo.getGiContent());
			textNewsService.updateTextNewsContetn(textNews);

			this.writeMessage(response, "保存成功！");
		} else {
			this.writeMessage(response, "ERROR，保存失败！");
		}
	}

	/**
	 * 政务信息对已采编的信息进行编辑更新操作
	 * 添加对采编状态的修改，by llj
	 * @author H2602975 zhangpeng
	 * @param model
	 * @param newsId
	 * @param flag
	 * @return
	 */
	@RequestMapping(params = "face=editGovInfoView")
	public String editGovInfo(HttpServletRequest request, Model model, @RequestParam("newsId") String newsId,@RequestParam("flag") String flag) {
		logger.debug(newsId);
		logger.debug(flag);
		// Date start = this.getStartDate("");
		// Date end = this.getEndDate("");
		String newsTitle = request.getParameter("newsTitle");
		String entryUser = request.getParameter("entryUser");
		String entryDateS = request.getParameter("entryDateS");
		String entryDateE = request.getParameter("entryDateE");
		String pageIndex = request.getParameter("pageIndex");
		String newsTag = request.getParameter("newsTag");
		String zxx = request.getParameter("zxx");
		String hb = request.getParameter("hb");
		
		List<TextGovInfo> textGovInfo = textGovInfoService.getTextGovInfoByNewsId(newsId);
		List<VideoRecord> newsRecord = videoRecordService.getVideoRecordList(textGovInfo.get(0).getNewsId());
		model.addAttribute("flag", flag);//采编状态标识：只能是 1采编已成刊；2采编未成刊，3采编未成刊
		model.addAttribute("newsRecord", newsRecord);
		model.addAttribute("userNo", SystemContext.getUserNO());
		model.addAttribute("govNewsInfo", textGovInfo.get(0));

		model.addAttribute("textPublication", textPublicationService.queryPubInfoList("", "", null, null, 0, 9999));
		
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("entryDateS", entryDateS);
		model.addAttribute("entryDateE", entryDateE);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("newsTag", newsTag);
		model.addAttribute("zxx", zxx);
		model.addAttribute("hb", hb);
		return "console/textnews/govInfo/editGovInfo";

	}
	

		// 编辑后进行保存 将返回值改为 String 之前为void
		//增加采编、成刊状态标识 by llj
		@RequestMapping(params = "action=saveGovInfoDetail")
		public void saveGovInfoDetatil(
				@ModelAttribute("textGovInfo") TextGovInfo textGovInfo,
				@RequestParam("entryTime") String entryTime,
				HttpServletResponse response, MultipartHttpServletRequest request) {
			System.out.println(textGovInfo.getAdoptType());
			if (StringUtils.isBlank(textGovInfo.getGiId())) {
				String mergeIds = textGovInfo.getMergeId();
				
				
				// 创建时间为当前服务器时间,也就是从text_news表取出数据后存入text_gov_info的服务器时间
				//编辑已采编的数据时EntryDate保存出错，原因：页面上的字段为EntryTime 2016-1-28 by llj
				
				textGovInfo.setCreateDate(DateUtil.getCurrentDateStr());
				textGovInfo.setIsReport("0");
				textGovInfo.setEntryDate(DateUtil.formatCSTDate(entryTime));
				List<TextGovInfo> govList = textGovInfoService.getTextGovInfoByNewsId(textGovInfo.getNewsId());
				if (govList !=null && govList.size()>0) {
					textGovInfoService.update(textGovInfo);
				} else {
					textGovInfo.setGiId(UUID.randomUUID().toString().replace("-", ""));
					// 从前台获取entryDate,保持和text_news表中日期一致.
					//编辑已采编的数据时EntryDate保存出错，原因：页面上的字段为EntryTime 2016-1-28 by llj
					//
					String entryDate = request.getParameter("entryTime");
					textGovInfo.setEntryDate(DateUtil.formatCSTDate(entryDate));
					logger.debug("entryDate=" + DateUtil.formatCSTDate(entryDate));
					textGovInfo.setIsDel("0");
					textGovInfo.setCreateBy(textGovInfo.getEntryUser());
					logger.debug("createDate=" + textGovInfo.getCreateDate());
					textGovInfoService.save(textGovInfo);
				}
				
				
				System.out.println(textGovInfo.getAdoptType());
				if(StringUtils.isNotBlank(textGovInfo.getAdoptType())){
					//记录采编条数、积分
					System.out.println("记录采编条数、积分");
					// 更新投稿信息字段gov_use_flag为采用状态
					String isSelected = textGovInfo.getIsSelected();
					String dept = textGovInfo.getEntryDept();
					logger.debug("dept=" + textGovInfo.getEntryDept());
					if(StringUtils.isNotBlank(textGovInfo.getAdoptType()) && textGovInfo.getAdoptType().contains("subjectInfo")){
						isSelected = "1";
					}else if (textGovInfo.getReportType() != null && textGovInfo.getAdoptType() != null) {
						/** 不选择采用类别，只选择上报类别时，状态改为 未采编 GuoY **/
						String isSelectAdopt="";
						String isSelectReport="";
						String[] adoptTypes = textGovInfo.getAdoptType().split(",");
						String[] reportTypes = textGovInfo.getReportType().split(",");
						// 选择上报类别
						for (int i = 0; i < reportTypes.length; i++) {
							if(null != reportTypes[i] && !"".equals(reportTypes[i])) {
								isSelectReport = "1";
								break;
							}
						}
						// 选择采用类别
						for (int i = 0; i < adoptTypes.length; i++) {
							if(null != adoptTypes[i] && !"".equals(adoptTypes[i])) {
								isSelectAdopt = "1";
								break;
							}
						}
						if ("".equals(isSelectAdopt) && "1".equals(isSelectReport)) {
							isSelected = "2";
						}
					}
					textNewsService.updateGovUseFlag(textGovInfo.getNewsId(),isSelected, dept);
					logger.debug("is_selected=" + isSelected);
		
					// 增加处理记录
					VideoRecord videoRecord = new VideoRecord();
					videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
					videoRecord.setVideoId(textGovInfo.getNewsId());
					videoRecord.setFlag("5");
					videoRecord.setUpdateUser(SystemContext.getUserName());
					videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
					videoRecordService.update(videoRecord);
		
					/*
					 * 政务信息采编时加分
					 */
					String str = textGovInfo.getAdoptType();
					logger.debug(str);
					if (!StringUtils.isBlank(str)) {
						deptScoreService.govInfoScore(textGovInfo);
					}
		
					// 采编时被合并的信息也要同时加分
					String newsId = "";
					if (!StringUtils.isBlank(mergeIds)) {
						String[] mergeId = mergeIds.split(",");
						for (int i = 0; i < mergeId.length; i++) {
							newsId = mergeId[i];
							TextGovInfo govInfo = textGovInfoService.getTextGovInfoByNewsId(newsId).get(0);
							String oldAdoptType = govInfo.getAdoptType();
							String mainAdoptType = textGovInfo.getAdoptType();
							String newAdoptType = oldAdoptType;
							if(StringUtils.isNotBlank(oldAdoptType)){
								String types[] = mainAdoptType.split(",");
								for (String type : types) {
									if(!oldAdoptType.contains(type)){
										newAdoptType += "," + type;
									}
								}
							}
							govInfo.setAdoptType(newAdoptType);
//							govInfo.setReportType(textGovInfo.getReportType());
							textGovInfoService.update(govInfo);
							deptScoreService.govInfoScore(govInfo);
							//修改新闻状态
							String isAdopt = govInfo.getIsSelected();
							if(StringUtils.isNotBlank(govInfo.getAdoptType()) && govInfo.getAdoptType().contains("subjectInfo")){
								isAdopt = "1";
							}else if (textGovInfo.getReportType() != null && textGovInfo.getAdoptType() != null) {
								/** 不选择采用类别，只选择上报类别时，状态改为 未采编 GuoY **/
								String isSelectAdopt="";
								String isSelectReport="";
								String[] adoptTypes = textGovInfo.getAdoptType().split(",");
								String[] reportTypes = textGovInfo.getReportType().split(",");
								// 选择上报类别
								for (int j = 0; j < reportTypes.length; j++) {
									if(null != reportTypes[j] && !"".equals(reportTypes[j])) {
										isSelectReport = "1";
										break;
									}
								}
								// 选择采用类别
								for (int k = 0; k < adoptTypes.length; k++) {
									if(null != adoptTypes[k] && !"".equals(adoptTypes[k])) {
										isSelectAdopt = "1";
										break;
									}
								}
								if ("".equals(isSelectAdopt) && "1".equals(isSelectReport)) {
									isAdopt = "2";
								}
							}
							textNewsService.updateGovUseFlag(govInfo.getNewsId(),isAdopt, dept);
						}
					}
				} else {
					System.out.println("不做任何操作");
				}
			
				// 与 政务信息报送查询 同步内容  GuoY
				TextNews textNews = new TextNews();
				textNews.setNewsId(textGovInfo.getNewsId());
				textNews.setNewsContent(textGovInfo.getGiContent());
				textNewsService.updateTextNewsContetn(textNews);
				
				this.writeMessage(response, "保存成功！");
			} else {
				this.writeMessage(response, "ERROR，保存失败！");
			}
			this.writeMessage(response, "保存成功！");
		}
		
		//将采编信息改为未采编  by llj
		@RequestMapping(params = "action=noCaiBian")
		public void noCaiBian(@ModelAttribute("textGovInfo") TextGovInfo textGovInfo,
				@RequestParam("entryTime") String entryTime) {
			String mergeIds = textGovInfo.getMergeId();
			// 1.如果有合并，将积分信息删除，text_Gov_Info表中adopt_type采用类型修改
			// 为空，（,,），report_type修改为空（,,）,删除积分信息表中数据，pub_type
			// 刊物类别修改为空，pub_id设置为0
			// 采编时被合并的信息要将信息积分信息删除，按照每一条来删除
			if (!StringUtils.isBlank(mergeIds)) {
				String[] mergeId = mergeIds.split(",");
				for (int k = 0; k < mergeId.length; k++) {
					deptScoreService.delete(mergeId[k]);
					TextGovInfo govInfomerge = textGovInfoService
							.getTextGovInfoByNewsId(mergeId[k]).get(0);
					govInfomerge.setAdoptType(",,");
					govInfomerge.setPubId("0");
					govInfomerge.setPubType("");
					govInfomerge.setEntryDate(DateUtil.formatCSTDate(entryTime));
					// 修改新闻状态
					textNewsService.updateGovUseFlagNot(govInfomerge.getNewsId());
					//deptScoreService.delete(govInfomerge.getNewsId());
					// 增加处理记录,修改为未采编
					addVideoRecord(govInfomerge.getNewsId(),"19");
					textGovInfoService.update(govInfomerge);
				}
			} else {
				// 2.将积分信息删除
				deptScoreService.delete(textGovInfo.getNewsId());
				// 修改新闻状态
				textNewsService.updateGovUseFlagNot(textGovInfo.getNewsId());
				// 3.将text_Gov_Info表中adopt_type修改为空
				textGovInfo.setAdoptType("");
				textGovInfo.setPubId("0");
				textGovInfo.setPubType("");
				textGovInfo.setEntryDate(DateUtil.formatCSTDate(entryTime));
				// 增加处理记录,修改为未采编
				addVideoRecord(textGovInfo.getNewsId(),"19");
				textGovInfoService.update(textGovInfo);
				// textGovInfoService.delGovInfoById(textGovInfo.getGiId());
			}
		}
		
		//将采编已成刊信息 改为采编未成刊 by llj
		@RequestMapping(params = "action=noChengKan")
		public void noChengKan(
				@ModelAttribute("textGovInfo") TextGovInfo textGovInfo,
				@RequestParam("entryTime") String entryTime) {
			String mergeIds = textGovInfo.getMergeId();
			// 删除积分信息表中数据，pub_type刊物类别修改为空，pub_id设置为0
			
			if (!StringUtils.isBlank(mergeIds)) {
				String[] mergeId = mergeIds.split(",");
				for (int j = 0; j < mergeId.length; j++) {
					deptScoreService.deletebyScore(mergeId[j]);
					TextGovInfo govInfomergej = textGovInfoService
							.getTextGovInfoByNewsId(mergeId[j]).get(0);
					govInfomergej.setPubId("0");
					govInfomergej.setPubType("");
					govInfomergej.setEntryDate(DateUtil.formatCSTDate(entryTime));
					// 增加处理记录,修改为未成刊
					addVideoRecord(govInfomergej.getNewsId(),"20");
					// 修改新闻状态
					textNewsService.updateGovUseFlagReport(govInfomergej
							.getNewsId());
					textGovInfoService.update(govInfomergej);
				}
			} else {
				// 3.将text_Gov_Info表 pub_type 修改为空
				// 刊物类别修改为空，pub_id设置为0
				//删除不成刊积分
				deptScoreService.deletebyScore(textGovInfo.getNewsId());
				textGovInfo.setPubId("0");
				textGovInfo.setPubType("");
				textGovInfo.setEntryDate(DateUtil.formatCSTDate(entryTime));
				// 增加处理记录
				addVideoRecord(textGovInfo.getNewsId(),"20");
				// 修改新闻状态
				textNewsService.updateGovUseFlagReport(textGovInfo.getNewsId());
				textGovInfoService.update(textGovInfo);
			}
		}
		
		// 增加处理记录 by llj
		public void addVideoRecord(String newsId,String flag){
			VideoRecord videoRecordmerge = new VideoRecord();
			videoRecordmerge.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecordmerge.setVideoId(newsId);
			videoRecordmerge.setFlag(flag);
			videoRecordmerge.setUpdateUser(SystemContext.getUserName());
			videoRecordmerge.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecordmerge);
		}

	/**
	 * 政务信息删除功能
	 * 
	 * @author H2602975 zhangpeng
	 * @param model
	 * @param newsId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteInfo")
	public void deleteInfo(Model model, @RequestParam("newsId") String newsId,
			@RequestParam("outerNewsId") String outerNewsId,
			HttpServletResponse response) {

		String msg = "";
		try {
			VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(newsId);
			videoRecord.setFlag("6");// 采编删除
			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);

			textNewsService.delete(newsId);

			if (!StringUtils.isBlank(outerNewsId)) {// 对应外网id不等于null，则删除时需同时将外网信息也删除
				TextNews textNews = new TextNews();
				textNews.setNewsId(newsId);
				textNews.setOuterNewsId(outerNewsId);
				cmsNewsService.delete(textNews);
			}

			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 政务信息刊物管理列表页面显示
	 * 
	 * @author H2602975 zhangpeng
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listPubInfoView")
	public String listPubInfo(Model model) {
		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		int pageIndex = 0;
		List<TextPublication> pubInfoList = textPublicationService
				.queryPubInfoList("", "", null, null, pageIndex,
						SystemContext.getDefaultPageSize());
		/*
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 */
		model.addAttribute("paginations", pubInfoList);
		return "console/textnews/publication/listPubInfo";
	}

	/**
	 * 政务信息刊物管理查询
	 * 
	 * @author H2602975 zhangpeng
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=queryPubInfoView")
	public String queryPubInfo(@RequestParam("pubName") String pubName,
			@RequestParam("createUser") String createUser,
			@RequestParam("createDateS") String createDateS,
			@RequestParam("createDateE") String createDateE,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		if (createDateS.equals("") || createDateE.equals("")) {
			{
				pubName = URLDecoder.decode(pubName, "utf-8");
				createUser = URLDecoder.decode(createUser, "utf-8");
				List<TextPublication> pubInfoList = textPublicationService
						.queryPubInfoList(pubName, createUser, null, null,
								pageIndex, SystemContext.getDefaultPageSize());
				model.addAttribute("pubName", pubName);
				model.addAttribute("createUser", createUser);
				model.addAttribute("paginations", pubInfoList);

			}
		} else if (createDateS.equals("") && createDateE.equals("")) {
			pubName = URLDecoder.decode(pubName, "utf-8");
			createUser = URLDecoder.decode(createUser, "utf-8");
			logger.debug("pubName==========" + pubName);
			List<TextPublication> pubInfoList = textPublicationService
					.queryPubInfoList(pubName, createUser, null, null,
							pageIndex, SystemContext.getDefaultPageSize());
			model.addAttribute("pubName", pubName);
			model.addAttribute("createUser", createUser);
			model.addAttribute("paginations", pubInfoList);

		} else {
			pubName = URLDecoder.decode(pubName, "utf-8");
			createUser = URLDecoder.decode(createUser, "utf-8");
			Date start = this.getStartDate(createDateS);
			Date end = this.getEndDate(createDateE);
			logger.debug("pubName==========" + pubName);
			List<TextPublication> pubInfoList = textPublicationService
					.queryPubInfoList(pubName, createUser, start, end,
							pageIndex, SystemContext.getDefaultPageSize());
			model.addAttribute("start", start);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			model.addAttribute("pubName", pubName);
			model.addAttribute("createUser", createUser);
			model.addAttribute("paginations", pubInfoList);
		}
		logger.debug("开始时间" + createDateS + "结束时间" + createDateE);

		/*
		 * pubName=URLDecoder.decode(pubName,"utf-8");
		 * createUser=URLDecoder.decode(createUser,"utf-8"); Date start =
		 * this.getStartDate(createDateS); Date end =
		 * this.getEndDate(createDateE);
		 * 
		 * logger.debug("刊物" + pubName); logger.debug("开始时间" + createDateS);
		 * logger.debug("结束时间" + createDateE); List<TextPublication> pubInfoList
		 * = textPublicationService .queryPubInfoList(pubName, createUser,
		 * start, end, pageIndex,SystemContext.getDefaultPageSize());
		 * model.addAttribute("pubName",pubName);
		 * model.addAttribute("createUser",createUser);
		 * model.addAttribute("start",start); model.addAttribute("end",end);
		 * model.addAttribute("paginations", pubInfoList);
		 */
		return "console/textnews/publication/listPubInfo";
	}

	/**
	 * 刊物信息新增页面跳转
	 * 
	 * @author H2602975 zhangpeng
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=addPubInfoView")
	public String addPubInfo(Model model) {
		model.addAttribute("createUserName", SystemContext.getUserName());
		return "console/textnews/publication/addPubInfo";
	}

	/**
	 * 刊物信息新增
	 * 
	 * @author H2602975 zhangpeng
	 * @param textPublication
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=savePubInfo", method = RequestMethod.POST)
	public void savePubInfo(
			@ModelAttribute("textPublication") TextPublication textPublication,
			MultipartHttpServletRequest request, HttpServletResponse response) {

		if (StringUtils.isBlank(textPublication.getPubId())) {
			textPublication.setPubId(UUID.randomUUID().toString()
					.replace("-", ""));
			textPublication.setCreateDate(DateUtil.getCurrentDateStr());
			textPublication.setIsDel("0");
			textPublicationService.save(textPublication);

			VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(textPublication.getPubId());
			videoRecord.setFlag("0");
			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);

			this.writeMessage(response, "保存成功！");
		} else {
			this.writeMessage(response, "ERROR，保存失败！");
		}

	}

	/**
	 * 刊物管理信息维护
	 * 
	 * @author H2602975 zhangpeng
	 * @param model
	 * @param pubId
	 * @return
	 */
	@RequestMapping(params = "face=managePubInfoView")
	public String managePubInfo(Model model, @RequestParam("pubId") String pubId) {

		List<VideoRecord> newsRecord = videoRecordService
				.getVideoRecordList(pubId);
		model.addAttribute("newsRecord", newsRecord);
		model.addAttribute("pubDetail",
				textPublicationService.getPubDetail(pubId));

		return "console/textnews/publication/managePubInfo";
	}

	/**
	 * 保存刊物管理信息
	 * 
	 * @author H2602975 zhangpeng
	 * @param giIdStr
	 * @param sequenceNumStr
	 * @param response
	 */
	@RequestMapping(params = "action=savePubDetail", method = RequestMethod.POST)
	public void savePubDetail(@RequestParam("giIdStr") String giIdStr,
			@RequestParam("pubName") String pubName,
			@RequestParam("pubCode") String pubCode,
			@RequestParam("pubId") String pubId,
			@RequestParam("sequenceNumStr") String sequenceNumStr,
			@RequestParam("isPublic") String isPublic,
			HttpServletResponse response) {

		String giIdS = giIdStr.substring(0, giIdStr.length() - 1);
		String sequenceNumS = sequenceNumStr.substring(0,
				sequenceNumStr.length() - 1);

		String giIdArray[] = giIdS.split(",");
		String sequenceNumArray[] = sequenceNumS.split(",");

		if (giIdArray.length != 0 && sequenceNumArray.length != 0) {
			textGovInfoService.savePubDetail(giIdArray, sequenceNumArray);

			VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(pubId);
			if (isPublic.equals("0")) {
				videoRecord.setFlag("0");
			}
			if (isPublic.equals("1")) {
				videoRecord.setFlag("17");
			}
			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);

			TextPublication textPublication = new TextPublication();
			textPublication.setIsPublic(isPublic);
			textPublication.setPubCode(pubCode);
			textPublication.setPubName(pubName);
			textPublication.setPubId(pubId);
			textPublicationService.update(textPublication);

			logger.debug("pubId" + pubId);
			List<TextGovInfo> textGovInfo = textGovInfoService.getGov(pubId);
			int i;
			if (null == textGovInfo) {
				logger.debug("没有与此刊物相关的政务信息");
				this.writeMessage(response, "保存成功！");
			} else {
				for (i = 0; i < textGovInfo.size(); i++) {
					String newsId = textGovInfo.get(i).getNewsId();
					textNewsService.resumeGovFlag(newsId);
				}
			}
			this.writeMessage(response, "保存成功！");

		} else {
			this.writeMessage(response, "保存失败！");
		}
	}

	/**
	 * 刊物信息删除
	 * 
	 * @param model
	 * @param pubId
	 * @param response
	 */
	@RequestMapping(params = "action=deletePubInfo")
	public void deletePubInfo(Model model, @RequestParam("pubId") String pubId,
			HttpServletResponse response) {
		String msg = "";
		try {
			TextPublication textPublication = new TextPublication();
			textPublication.setPubId(pubId);
			textPublicationService.delete(textPublication);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * private String getImagePath(ServletContext servletContext) { return
	 * servletContext.getRealPath("/") + "resource/textnews/" +
	 * FileUploader.getTimeFolder() + "/"; }
	 */
	/**
	 * 政务信息上报默认显示功能
	 * 
	 * @author H2902992
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listGovInfoReportView")
	public String queryInfoReportManage(Model model) {

		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		int pageIndex = 0;
		// 添加政务上报管理只能查询出上报过后 只能查询出上报过后的上报类型条件
		List<TextGovInfo> textGovInfoList = textGovInfoService
				.getTextGovInfoList("", null, null, "report", pageIndex,
						SystemContext.getDefaultPageSize());
		
		// 计算采用类别
		List<CheckStandard> checkStandards =  checkStandardService.queryCheckStandard(null);
		for (int i = 0; i < textGovInfoList.size(); i++) {
			String codes = PortalUtils.computeAdoptType( textGovInfoList
					.get(i).getAdoptType(), checkStandards);
			textGovInfoList.get(i).setAdoptType(codes);
		}

		model.addAttribute("paginations", textGovInfoList);
		return "console/textnews/govInfo/listGovInfoReport";
	}

	/**
	 * 政务信息上报个管理条件查询功能
	 * 
	 * @author H2602965
	 * @param newsTitle
	 * @param entryDateS
	 * @param entryDateE
	 * @param pageIndex
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=searchInfoReportView")
	public String queryInfoReport(@RequestParam("newsTitle") String newsTitle,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("status") String status,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		// 获取全部采用类别
		List<CheckStandard> checkStandards =  checkStandardService.queryCheckStandard(null);
		
		if (entryDateS.equals("") || entryDateE.equals("")) {
			newsTitle = URLDecoder.decode(newsTitle, "utf-8");
			List<TextGovInfo> textGovInfoList = textGovInfoService
					.getTextGovInfoList(newsTitle, null, null, status,
							pageIndex, SystemContext.getDefaultPageSize());
			// 计算采用类别
			for (int i = 0; i < textGovInfoList.size(); i++) {
				String codes = PortalUtils.computeAdoptType( textGovInfoList
						.get(i).getAdoptType(), checkStandards);
				textGovInfoList.get(i).setAdoptType(codes);
			}
			model.addAttribute("newsTitle", newsTitle);
			model.addAttribute("status", status);
			model.addAttribute("paginations", textGovInfoList);

		} else if (entryDateS.equals("") && entryDateE.equals("")) {
			newsTitle = URLDecoder.decode(newsTitle, "utf-8");
			logger.debug("newsTitle==========" + newsTitle);
			List<TextGovInfo> textGovInfoList = textGovInfoService
					.getTextGovInfoList(newsTitle, null, null, status,
							pageIndex, SystemContext.getDefaultPageSize());
			// 计算采用类别
			for (int i = 0; i < textGovInfoList.size(); i++) {
				String codes = PortalUtils.computeAdoptType( textGovInfoList
						.get(i).getAdoptType(), checkStandards);
				textGovInfoList.get(i).setAdoptType(codes);
			}
			model.addAttribute("newsTitle", newsTitle);
			model.addAttribute("status", status);
			model.addAttribute("paginations", textGovInfoList);

		} else {
			newsTitle = URLDecoder.decode(newsTitle, "utf-8");

			Date start = this.getStartDate(entryDateS);
			Date end = this.getEndDate(entryDateE);
			logger.debug("newsTitle==========" + newsTitle);
			List<TextGovInfo> textGovInfoList = textGovInfoService
					.getTextGovInfoList(newsTitle, start, end, status,
							pageIndex, SystemContext.getDefaultPageSize());
			// 计算采用类别
			for (int i = 0; i < textGovInfoList.size(); i++) {
				String codes = PortalUtils.computeAdoptType( textGovInfoList
						.get(i).getAdoptType(), checkStandards);
				textGovInfoList.get(i).setAdoptType(codes);
			}
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			model.addAttribute("newsTitle", newsTitle);
			model.addAttribute("status", status);
			model.addAttribute("paginations", textGovInfoList);
		}
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);
		return "console/textnews/govInfo/listGovInfoReport";
	}
	
	/**
	 * 政务信息上报管理删除功能
	 * 
	 * @author H2902992 20140606
	 * @param model
	 * @param userId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteInfoReport")
	public void deleteInfoReport(Model model,
			@RequestParam("giId") String giId, HttpServletResponse response) {
		String msg = "";
		try {
			TextGovInfo textGovInfo = new TextGovInfo();
			textGovInfo.setGiId(giId);
			textGovInfoService.deleteInfoReport(textGovInfo);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 刊物添加功能页面，选择未对应进刊物的已采用政务信息
	 * 
	 * @author F3201252 许健
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=listGovInfoForPub")
	public String listGovInfoForPub(Model model, HttpServletRequest request)
			throws UnsupportedEncodingException {

		String newsTitle = request.getParameter("newsTitle");
		String entryDateS = request.getParameter("entryDateS");
		String entryDateE = request.getParameter("entryDateE");

		logger.debug("输入参数：newsTitle=" + newsTitle + ", entryDateS="
				+ entryDateS + ", entryDateE=" + entryDateE);

		if (StringUtils.isBlank(newsTitle))
			newsTitle = "";
		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);

		logger.debug("调用参数：newsTitle=" + newsTitle + ", start=" + start
				+ ", end=" + end);

		List<TextGovInfo> govInfoList = textGovInfoService
				.getUnPubTextGovInfoList(newsTitle, start, end);

		logger.debug("未对应进刊物的已采用政务信息数量：" + govInfoList.size());

		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("govInfoList", govInfoList);

		return "console/textnews/publication/selectGovInfo";
	}

	/**
	 * 保存刊物和政务信息之间的关系
	 * 
	 * @param pubId
	 * @param giId
	 * @param response
	 */
	@RequestMapping(params = "action=savePubGovRelation")
	public void savePubGovRelation(@RequestParam("giId") String giId,
			@RequestParam("pubId") String pubId,
			@RequestParam("pubType") String pubType,
			HttpServletResponse response) {

		// 获取政务信息
		TextGovInfo textGovInfo = textGovInfoService.getTextGovInfo(giId);
		// 设置对应的刊物id
		textGovInfo.setPubId(pubId);
		textGovInfo.setPubType(pubType);
		// 更新政务信息
		textGovInfoService.update(textGovInfo);

		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 默认查询政务信息回收站信息
	 * 
	 * @author H2603282
	 * @param model
	 * @param flag
	 * @param newsTag
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "face=getRecycleGovInfoView")
	public String queryRecycleGovInfo(Model model) {

		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		int pageIndex = 0;
		List<TextNews> textNewsList = textNewsService.getGovNewsRecycleList("",
				"", null, null, pageIndex, SystemContext.getDefaultPageSize());
		/*
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 */
		model.addAttribute("paginations", textNewsList);
		return "console/textnews/govInfo/recycleGovInfo";
	}

	/**
	 * 条件查询政务回收站信息 H2603282
	 * 
	 * @author H2603282
	 * @param newsTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=queryRecycleGovInfoView")
	public String queryRecycleGovInfo(
			@RequestParam("newsTitle") String newsTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {

		/*
		 * newsTitle=URLDecoder.decode(newsTitle,"utf-8");
		 * entryUser=URLDecoder.decode(entryUser,"utf-8"); Date start =
		 * this.getStartDate(entryDateS); Date end =
		 * this.getEndDate(entryDateE);
		 * 
		 * List<TextNews> textNewsList = textNewsService
		 * .getGovNewsRecycleList(newsTitle, entryUser, start, end, pageIndex,
		 * SystemContext.getDefaultPageSize());
		 * 
		 * model.addAttribute("newsTitle", newsTitle);
		 * model.addAttribute("entryUser", entryUser);
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 * model.addAttribute("paginations", textNewsList);
		 */
		if (entryDateS.equals("") || entryDateE.equals("")) {
			{
				newsTitle = URLDecoder.decode(newsTitle, "utf-8");
				entryUser = URLDecoder.decode(entryUser, "utf-8");
				List<TextNews> textNewsList = textNewsService
						.getGovNewsRecycleList(newsTitle, entryUser, null,
								null, pageIndex,
								SystemContext.getDefaultPageSize());
				model.addAttribute("newsTitle", newsTitle);
				model.addAttribute("entryUser", entryUser);
				model.addAttribute("paginations", textNewsList);

			}
		} else if (entryDateS.equals("") && entryDateE.equals("")) {
			newsTitle = URLDecoder.decode(newsTitle, "utf-8");
			entryUser = URLDecoder.decode(entryUser, "utf-8");
			logger.debug("newsTitle==========" + newsTitle);
			List<TextNews> textNewsList = textNewsService
					.getGovNewsRecycleList(newsTitle, entryUser, null, null,
							pageIndex, SystemContext.getDefaultPageSize());
			model.addAttribute("newsTitle", newsTitle);
			model.addAttribute("entryUser", entryUser);
			model.addAttribute("paginations", textNewsList);

		} else {
			newsTitle = URLDecoder.decode(newsTitle, "utf-8");
			entryUser = URLDecoder.decode(entryUser, "utf-8");
			Date start = this.getStartDate(entryDateS);
			Date end = this.getEndDate(entryDateE);
			logger.debug("newsTitle==========" + newsTitle);
			List<TextNews> textNewsList = textNewsService
					.getGovNewsRecycleList(newsTitle, entryUser, start, end,
							pageIndex, SystemContext.getDefaultPageSize());
			model.addAttribute("start", start);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			model.addAttribute("newsTitle", newsTitle);
			model.addAttribute("entryUser", entryUser);
			model.addAttribute("paginations", textNewsList);
		}
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);

		return "console/textnews/govInfo/recycleGovInfo";
	}

	/**
	 * 政务信息回收站复原功能
	 * 
	 * @param model
	 * @param newsId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteRecycleSiteInfo")
	public void deleteRecycleSiteInfo(Model model,
			@RequestParam("newsId") String newsId, HttpServletResponse response) {
		logger.debug("0000=" + newsId);
		TextNews textNewsList = textNewsService.getTextNewsDetail(newsId);
		if (null == textNewsList) {
			logger.debug("不是采编中被删除的信息");
		} else {
			TextNews Textnews = new TextNews();
			Textnews.setNewsId(newsId);
			textNewsService.resumeTextNewsFlag(newsId);
			List<TextGovInfo> textGovInfoL = textGovInfoService.getTextGovInfoListByNewsId(newsId);
			if(textGovInfoL.size()>0){
				textGovInfoService.reUpdate(textGovInfoL.get(0));
			}
		}

		TextGovInfo textGovInfoList = textGovInfoService.getTextGovInfo(newsId);
		if (null == textGovInfoList) {
			logger.debug("不是上报中被删除的信息");
		} else {
			TextGovInfo textGovInfo = new TextGovInfo();
			textGovInfo.setGiId(newsId);
			// textGovInfo.setIsDel("0");
			textGovInfoService.reUpdate(textGovInfo);
			String newId = textGovInfoList.getNewsId();
			textNewsService.resumeTextNewsFlag(newId);
		}

		TextPublication textPublication = textPublicationService
				.getPubList(newsId);
		if (null ==textPublication) {
			logger.debug("不是刊物中被删除的信息");
		} else {
			TextPublication textPublicationList = new TextPublication();
			textPublicationList.setPubId(newsId);
			textPublicationList.setPubName(textPublication.getPubName());
			textPublicationList.setPubCode(textPublication.getPubCode());
			textPublicationList.setCreateUser(textPublication.getCreateUser());
			textPublicationService.update(textPublicationList);
		}

		VideoRecord videoRecord = new VideoRecord();
		videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
		videoRecord.setVideoId(newsId);
		videoRecord.setFlag("16");
		videoRecord.setUpdateUser(SystemContext.getUserName());
		videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
		videoRecordService.update(videoRecord);

		String msg = "";
		try {
			/*
			 * TextNews Textnews = new TextNews(); Textnews.setNewsId(newsId);
			 * textNewsService.resumeTextNewsFlag(newsId);
			 */
			msg = "复原成功！";
		} catch (Exception e) {
			msg = "复原失败！";
		}

		this.writeMessage(response, msg);
	}

	/**
	 * 政务信息上报管理删除功能
	 * 
	 * @author H2902992 20140606
	 * @param model
	 * @param userId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteGovPubRef")
	public void deleteGovPubRef(Model model, @RequestParam("giId") String giId,
			HttpServletResponse response) {
		String msg = "";
		try {
			TextGovInfo textGovInfo = new TextGovInfo();
			textGovInfo.setGiId(giId);
			textGovInfo.setPubId("");
			textGovInfo.setPubType("");
			textGovInfoService.deleteGovPubRef(textGovInfo);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 合并信息
	 * 
	 * @author H2601917
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=mergeInformation")
	public void mergeInformation(Model model, HttpServletRequest request,
			HttpServletResponse response, String isSelected) {
		String msg = "合并失败！";
		String newsIds = request.getParameter("newsIds");
		logger.debug(isSelected);
		logger.debug(newsIds);
		if (newsIds != null) {
			try {
				textGovInfoService.mergeNews(newsIds, isSelected);
				msg = "合并成功！";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("action=mergeInformation is error:",e);
			}
		}
		this.writeMessage(response, msg);
	}

	/**
	 * 批量删除
	 * 
	 * @author H2603282
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=deleteGovAll")
	public void deleteSiteAll(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String msg = "";
		String newsIds = request.getParameter("newsIds");
		logger.debug(newsIds);
		if (newsIds != null) {
			textNewsService.deleteGovAll(newsIds);
			msg = "彻底删除成功！";
		}
		this.writeMessage(response, msg);
	}
}
