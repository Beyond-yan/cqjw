package com.gdssoft.cqjt.controller.portal.index;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.FileDownload;
import com.gdssoft.cqjt.pojo.ListItem;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.TextPublication;
import com.gdssoft.cqjt.pojo.TrafficRunVo;
import com.gdssoft.cqjt.pojo.UserRelationVo;
import com.gdssoft.cqjt.pojo.report.GovSiteCheckReport;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.FileDownloadService;
import com.gdssoft.cqjt.service.MailService;
import com.gdssoft.cqjt.service.OAService;
import com.gdssoft.cqjt.service.SpecialInformationService;
import com.gdssoft.cqjt.service.TextNewsReportService;
import com.gdssoft.cqjt.service.TextNewsService;
import com.gdssoft.cqjt.service.TextPublicationService;
import com.gdssoft.cqjt.service.TrafficRunService;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.videoNews.VideoNewsService;
import com.gdssoft.cqjt.util.PortalUtils;

@Controller("portalIndexController")
@RequestMapping("/web/index.xhtml")
public class IndexController extends BaseController {

	@Value("${oa.server}")
	private String oaServerUrl;

	@Value("${mail.server}")
	private String mailServerUrl;
	
	@Value("${lcxzsp.sso.url}")
	private String lcxzspSsoUrl;//浪潮单点登录地址
	
	@Value("${lcxzsp.business.url}")
	private String lcxzspBusinessUrl;//浪潮获取代办件地址
	
	@Value("${lcxzsp.sybmol}")
	private String lcxzspSybmol;//浪潮请求标志
	
	@Value("${lcxzsp.target}")
	private String lcxzspTarget;//目标标志

	@Resource(name = "oaService")
	private OAService oaService;

	@Resource(name = "mailService")
	private MailService mailService;

	// @Value("${default.page.size}")
	// private int pageSize;

	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsService;

	@Resource(name = "textPublicationServiceImpl")
	private TextPublicationService textPublicationService;

	@Resource(name = "fileDownloadService")
	private FileDownloadService fileDownloadService;
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;

	@Resource(name = "videoNewsServiceImpl")
	private VideoNewsService videoNewsService;

	@Resource(name = "trafficRunService")
	private TrafficRunService trafficRunService;
	
	@Resource(name = "specialInformationService")
	private SpecialInformationService specialInformationService;
	
	@Autowired
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userService;
	
	// @Resource(name = "searchEngineServiceImpl")
	// private SearchEngineService searchEngineService;
	@Autowired
	@Resource(name = "textNewsReportServiceImpl")
	private TextNewsReportService textNewsReportService;

	@RequestMapping(params = "face=home")
	public String home(Model model, HttpServletRequest request) {

		// Date start = this.getStartDate("");
		// Date end = this.getEndDate("");
		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();

		model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("roleId", SystemContext.getRoleID());
		String userNo = SystemContext.getUserNO();
		model.addAttribute("userNo", userNo);
		/* ==========浪潮待办集成========== */
		UserRelationVo userVo = userService.queryUserRelation(userNo, null);
		String lcLoginId = userVo.getRelaLcLoginId();
		System.out.println("---------->"+lcxzspSsoUrl);
		model.addAttribute("lcxzspSsoUrl", lcxzspSsoUrl);
		model.addAttribute("lcxzspBusinessUrl", lcxzspBusinessUrl);
		model.addAttribute("username_ws", PortalUtils.encrypt(lcLoginId));
		model.addAttribute("sybmol", PortalUtils.encrypt(lcxzspSybmol));
		model.addAttribute("target", PortalUtils.encrypt(lcxzspTarget));
		model.addAttribute("token", PortalUtils.encrypt(lcLoginId+lcxzspSybmol));
		

		model.addAttribute("oaServerUrl", oaServerUrl);
		model.addAttribute("mailServerUrl", mailServerUrl);

		/* ==========OA待办集成========== */
		List<ListItem> todoList = oaService.getTodoList(userNo, 7);
		model.addAttribute("todoList", todoList);
		if (todoList.size() > 0) {
			model.addAttribute("todoListCount", todoList.get(0).getTotlecount());
		} else {
			model.addAttribute("todoListCount", 0);
		}
		/* ==========最新公文集成========== */
		model.addAttribute("totleCount", oaService.getUnReceiveArchivesCount(userNo));
		/* ==========邮件系统集成========== */
		String userEmail = SystemContext.getUserDetail().getUserEmail();
		String mailSession = mailService.userLogin(userEmail);
		// 只有成功登入到邮件系统才获取新邮件相关信息
		if (!StringUtils.isBlank(mailSession)) {
			model.addAttribute("mailSession", mailSession);
			model.addAttribute("newMailCnt",
					mailService.getNewMailCnt(userEmail));
			model.addAttribute("newMailList",
					mailService.getNewMailList(userEmail));
		} else {
			model.addAttribute("newMailCnt", 0);
		}

		/* ==========公告========== */
		String[] categorys = { "招标公告", "通知公告", "中标结果", "资质审查公示" };
		List<TextNews> notices = textNewsService.getTextNewsList(categorys, 4);
		model.addAttribute("noticeNews", notices);

		/* ==========行业动态========== */
		int pageIndex = 0;
		/*
		 * List<TextNews> industryNews = textNewsService .getSiteNewsList(start,
		 * end, new String[]{"1","2","3"},new String[]{"政务动态"},pageIndex, 4);
		 * //if (industryNews.size() > 5) industryNews=industryNews.subList(0,
		 * 5);
		 */
		/**
		 * 20141124 zhp edit
		 */

		// 行业动态
		List<TextNews> industryNews = textNewsService.getDynamicSiteNewsList(
				start, end, new String[] { "1", "2", "3", "8", "9" },
				new String[] {"政务动态","交通统计","政策解读","政策解读" }, new String[] {
						"委属单位", "市属相关交通企业" }, pageIndex, 4);
		// if (industryNews.size() > 5) industryNews=industryNews.subList(0, 5);
		model.addAttribute("industryNews", industryNews);
		// 处室动态
		List<TextNews> chushiNews = textNewsService.getDynamicSiteNewsList(
				start, end, new String[] { "1", "2", "3", "8", "9" },
				new String[] {"政务动态","安全监管","财政信息","风采集锦","妇女工作",
						"工程建设领域项目信息公开及诚信体系","工会工作","交通规划","交通统计",
						"离退休干部相关政策","廉政动态","廉政法规","廉政文化", "人事信息","信访工作",
						"应急管理","政策解读", "政风行风","政府采购情况 ","行政审批项目库","领导活动","领导介绍","职能机构"},
				new String[] { "市交委机关" }, pageIndex, 4);
		// if (industryNews.size() > 5) industryNews=industryNews.subList(0, 5);
		model.addAttribute("chushiNews", chushiNews);
		// 区县动态
		List<TextNews> quxianNews = textNewsService
				.getDynamicSiteNewsList(start, end, new String[] { "1", "2",
						"3", "8", "9" }, new String[] { "政务动态" },
						new String[] { "区县交通局" }, pageIndex, 4);
		// if (industryNews.size() > 5) industryNews=industryNews.subList(0, 5);
		model.addAttribute("quxianNews", quxianNews);
		
		//交通运行动态
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginLimit", 0);
		map.put("endLimit", 4);
		List<TrafficRunVo> runVoList = trafficRunService.queryPageList(map);
		model.addAttribute("runVoList", runVoList);
		
		/* ==========每日信息========== */
		List<TextPublication> textPublications = textPublicationService.getPub(
				"", "", start, end, pageIndex, 5);
		// if (textPublications.size() > 5)
		// textPublications=textPublications.subList(0, 5);
		model.addAttribute("textPublications", textPublications);

		/* ==========交通课堂========== */
		List<FileDownload> fownloadFiles = fileDownloadService.getAllFile("",
				start, end, "0", pageIndex, 5);
		// if (fownloadFiles.size() > 5) fownloadFiles=fownloadFiles.subList(0,
		// 5);
		model.addAttribute("fownloadFiles", fownloadFiles);
		
		//专报信息
//		Map<String, Object> specialInformationMap = new HashMap<String, Object>();
//		specialInformationMap.put("startRow", 0);
//		specialInformationMap.put("endRow", 5);
//		specialInformationMap.put("adoptType", "subjectInfo");
//		List<TextGovInfo> specialVoList = specialInformationService.queryPageList(specialInformationMap);
//		model.addAttribute("specialVoList", specialVoList);
		Map<String, Object> specialInformationMap = new HashMap<String, Object>();
		specialInformationMap.put("beginLimit", 0);
		specialInformationMap.put("endLimit", 5);
		specialInformationMap.put("adoptType", "subjectInfo");
		List<TextGovInfo> specialVoList = specialInformationService.queryPageList(specialInformationMap);
		model.addAttribute("specialVoList", specialVoList);
		
		/* ==========考核排名========== */
//		List<Department> rankList = departmentService
//				.getScoreSumList(new String[] { "市交委机关" });
//		// 查询委属单位
//		List<Department> rankListS = departmentService
//				.getScoreSumList(new String[] { "委属单位", "区县交通局", "市属相关交通企业" });
//		/* if (rankList.size() > 3) rankList=rankList.subList(0, 3); */
//		model.addAttribute("rankList", rankList);
//		/* if (rankListS.size() > 3) rankListS=rankListS.subList(0, 3); */
//		model.addAttribute("rankListS", rankListS);

		textNewsReportService.queryGovSiteCheckStat(model,0);
		/* ==========交通视频========== */
		List<VideoNews> videoNewsList = videoNewsService.getVideoList("", "",
				start, end, new String[] { "2" }, 0, 4);
		// if (videoNewsList.size() > 4) videoNewsList=videoNewsList.subList(0,
		// 4);
		model.addAttribute("videoNewsList", videoNewsList);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum",this.getCount());
		return "portal/index/index";

	}

	@RequestMapping(params = "action=getUnReceiveArchives")
	public void getUnReceiveArchives(HttpServletResponse response) {
		String userNo = SystemContext.getUserNO();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(
					oaService.getUnReceiveArchives(userNo, 7));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据具体的MId跳转到指定的邮箱
	 * 
	 * @author H2602965
	 * @param response
	 * @param mid
	 */
	@RequestMapping(params = "action=getEmailUrl")
	public void getEmailUrl(HttpServletResponse response,
			@RequestParam("mid") String mid) {
		String userEmail = SystemContext.getUserDetail().getUserEmail();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(mailService.getEmailUrl(userEmail, mid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
