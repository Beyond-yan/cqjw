package com.gdssoft.cqjt.controller.portal.index;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.gdssoft.core.tools.WordSegmentTool;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.FileDownload;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.TextPublication;
import com.gdssoft.cqjt.pojo.TrafficRunVo;
import com.gdssoft.cqjt.pojo.report.GovSiteCheckReport;
import com.gdssoft.cqjt.pojo.search.InnerSiteSearch;
import com.gdssoft.cqjt.pojo.search.PublicArchivesSearch;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.FileDownloadService;
import com.gdssoft.cqjt.service.SpecialInformationService;
import com.gdssoft.cqjt.service.TextNewsReportService;
import com.gdssoft.cqjt.service.TextNewsService;
import com.gdssoft.cqjt.service.TextPublicationService;
import com.gdssoft.cqjt.service.TrafficRunService;
import com.gdssoft.cqjt.service.search.SearchEngineService;
import com.gdssoft.cqjt.service.videoNews.VideoNewsService;

@Controller
@RequestMapping("/guest/index.xhtml")
public class GuestIndexController extends BaseController {

	@Value("${oa.server}")
	private String oaServerUrl;

	@Value("${mail.server}")
	private String mailServerUrl;

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

	@Resource(name = "searchEngineServiceImpl")
	private SearchEngineService searchEngineService;
	
	@Resource(name = "trafficRunService")
	private TrafficRunService trafficRunService;
	
	@Resource(name = "specialInformationService")
	private SpecialInformationService specialInformationService;
	
	@Autowired
	@Resource(name = "textNewsReportServiceImpl")
	private TextNewsReportService textNewsReportService;

	@RequestMapping
	public String home(Model model, HttpServletRequest request) {
		// Date start = this.getStartDate("");
		// Date end = this.getEndDate("");
		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();

		model.addAttribute("oaServerUrl", oaServerUrl);
		model.addAttribute("mailServerUrl", mailServerUrl);

		/* ==========公告========== */
		String[] categorys = { "招标公告", "通知公告", "中标结果", "资质审查公示" };
		List<TextNews> notices = textNewsService.getTextNewsList(categorys, 4);
		model.addAttribute("noticeNews", notices);

		int pageIndex = 0;

		/* ==========交通信息========== */
		List<TextNews> trafficNews = textNewsService.getSiteNewsList(start, end, new String[] { "3" }, new String[] { "交通要闻" }, pageIndex,7);
		// if (trafficNews.size() > 7) trafficNews=trafficNews.subList(0, 7);

		/* 头版头条和头版次条 */
		Map<String, String> mapMain = new HashMap<String, String>();
		mapMain.put("isDel", "0");
		mapMain.put("sectionPosition", "1");
		TextNews mainTextNews = textNewsService.getMainOrSubMainTextNews(mapMain);
		mapMain.put("sectionPosition", "0");
		TextNews subMainTextNews = textNewsService.getMainOrSubMainTextNews(mapMain);
		model.addAttribute("mainTextNews", mainTextNews);
		model.addAttribute("subMainTextNews", subMainTextNews);
		// 删除头版和次版
		for (int i = 0; i < trafficNews.size(); i++) {
			if( null != mainTextNews && trafficNews.get(i)
					.getNewsId().equals(mainTextNews.getNewsId())) {
				trafficNews.remove(i);
			}
			if ( null != subMainTextNews && trafficNews.get(i)
					.getNewsId().equals(subMainTextNews.getNewsId())) {
				trafficNews.remove(i);
			}
			
		}
		model.addAttribute("trafficNews", trafficNews);
		/* ==========行业动态========== */
		/**
		 * 20141124 zhp edit
		 */
		// 行业动态
		List<TextNews> industryNews = textNewsService.getDynamicSiteNewsList(
				start, end, new String[] { "1", "2", "3", "8", "9" },
				new String[] { "政务动态","交通统计","政策解读","政策解读"}, new String[] { "委属单位", "市属相关交通企业" },
				pageIndex, 4);
		// if (industryNews.size() > 5) industryNews=industryNews.subList(0, 5);
		model.addAttribute("industryNews", industryNews);
		// 处室动态
		List<TextNews> chushiNews = textNewsService.getDynamicSiteNewsList(
				start, end, new String[] { "1", "2", "3", "8", "9" },
				new String[] { "政务动态", "安全监管", "财政信息", "风采集锦", "妇女工作",
						"工程建设领域项目信息公开及诚信体系", "工会工作", "交通规划", "交通统计",
						"离退休干部相关政策", "廉政动态", "廉政法规", "廉政文化", "人事信息", "信访工作",
						"应急管理", "政策解读", "政风行风", "政府采购情况 ", "行政审批项目库", "领导活动",
						"领导介绍", "职能机构" }, new String[] { "市交委机关" }, pageIndex,4);
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
//		List<Department> rankList = departmentService.getScoreSumList(new String[] { "市交委机关" });
//		// 查询委属单位
//		List<Department> rankListS = departmentService.getScoreSumList(new String[] { "委属单位", "区县交通局", "市属相关交通企业" });
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
		
		/* ==========图片新闻，取前4条========== */
		List<TextNews> trafficPhotoNews = textNewsService.getPhotoNewsList(5);
		List<Map<String,Object>> phoneList = new ArrayList<Map<String,Object>>();
		if(trafficPhotoNews != null){
			int count = 1;
			for (TextNews t : trafficPhotoNews) {
				if(count > 5){
					break;
				}
				String url = t.getPhotoUrl();
				String newsId = t.getNewsId();
				String newsTitle = t.getNewsTitle();
				if(StringUtils.isNotBlank(url)){
					String imgUrls[] = url.split(",");
					for (String s : imgUrls) {
						if(count > 5){
							break;
						}
						Map<String,Object> imgMap = new HashMap<String,Object>();
						imgMap.put("newsId", newsId);
						imgMap.put("newsTitle", newsTitle);
						imgMap.put("photoUrl", s);
						phoneList.add(imgMap);
						count++;
					}
				}
			}
			
			
		}
		model.addAttribute("trafficPhotoNews", phoneList);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/index/index_guest";
	}


	/*
	 * //交通信息页面跳转 20140610
	 * 
	 * @RequestMapping(params = "face=trafficView") public String
	 * traffic(HttpServletRequest request, HttpServletResponse response) {
	 * return "portal/index/trafficInfoList"; }
	 * 
	 * //每日信息登陆前页面跳转 20140610 H2902992
	 * 
	 * @RequestMapping(params = "face=showDailyInfoView") public String
	 * showDailyInfo(HttpServletRequest request, HttpServletResponse response) {
	 * return "portal/index/dailyInfoList"; }
	 */
	@RequestMapping(params = "action=getPublicArchives")
	public void getPublicArchives(HttpServletResponse response) {
		List<PublicArchivesSearch> searchResult = new ArrayList<PublicArchivesSearch>();
		searchResult = searchEngineService.getArchivesSearchInfoBySchema("oa", 0, 5);
		response.setCharacterEncoding("UTF-8");
		try {
			// response.getWriter().write(oaService.getPublicArchives(5));
			this.writeResult(response, searchResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 首页网站搜索
	 * 
	 * @param model
	 * @param keyStr
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping(params = "face=siteSearchInfoView")
	public String siteSearchInfoView(Model model,
			@RequestParam("keyStr") String keyStr,
			@RequestParam("pageIndex") int pageIndex) {
		String keyWord = "";
		WordSegmentTool wordSegment = new WordSegmentTool();
		try {
			keyStr = URLDecoder.decode(keyStr, "utf-8");
			keyWord = wordSegment.segmentWord(keyStr);
			// keyWord = keyStr.replace(" ", " +");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("输出的关键子：" + keyWord);
		if (!(keyStr.equals("") || (keyStr.trim()).length() == 0)) {
			List<InnerSiteSearch> searchResult = new ArrayList<InnerSiteSearch>();
			searchResult = searchEngineService.getInnerSiteSearchInfo(keyWord,
					pageIndex, SystemContext.getDefaultPageSize());
			logger.debug("输出list长度：" + searchResult.size());
			/*
			 * if (searchResult.size() <= pageSize){ pageIndex = 1; } PageBean
			 * page = new PageBean(searchResult, pageIndex,
			 * SystemContext.getDefaultPageSize());
			 */
			model.addAttribute("searchResult", searchResult);
			model.addAttribute("keyStr", keyStr);
		}
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/index/siteSearchInfoList";
	}

	@RequestMapping(params = "face=downLoad")
	public void downLoad(HttpServletResponse response,
			HttpServletRequest request) {
		String fileName = "";
		try {
			fileName = URLDecoder.decode(request.getParameter("fileName"),
					"utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		String filePath = request.getParameter("filePath");
		logger.debug("fileName=" + fileName + ",filePath=" + filePath);
		String finalFilePath = "";
		if (filePath.substring(0, 8).equals("resource")) {
			logger.debug("资料下载");
			finalFilePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ filePath;

			String finalFileName = "";
			try {
				finalFileName = new String(fileName.getBytes("gb2312"),
						"iso8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			logger.debug("finalFileName=" + finalFileName + ",finalFilePath="
					+ finalFilePath);

			// response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("content-type", "application/x-msdownload");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ finalFileName);

			java.io.OutputStream outp = null;
			java.io.FileInputStream in = null;
			try {
				outp = response.getOutputStream();
				in = new java.io.FileInputStream(finalFilePath);

				byte[] b = new byte[1024];
				int i = 0;

				while ((i = in.read(b)) > 0) {
					outp.write(b, 0, i);
				}
				outp.flush();
			} catch (Exception e) {
				logger.debug("Error!");
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					in = null;
				}
				if (outp != null) {
					// out.clear();
					// out=pageContext.pushBody();
					try {
						outp.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					outp = null;
				}
			}

		} else {
			logger.debug("交委公文");
			finalFilePath = filePath;

			InputStream insr = null;
			URL myURL;
			try {
				myURL = new URL(finalFilePath);
				HttpURLConnection httpsConn = (HttpURLConnection) myURL
						.openConnection();
				insr = httpsConn.getInputStream();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String finalFileName = "";
			try {
				finalFileName = new String(fileName.getBytes("gb2312"),
						"iso8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			logger.debug("finalFileName=" + finalFileName + ",finalFilePath="
					+ finalFilePath);

			// response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("content-type", "application/x-msdownload");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ finalFileName);

			java.io.OutputStream outp = null;
			try {
				outp = response.getOutputStream();

				byte[] b = new byte[1024];
				int i = 0;
				while ((i = insr.read(b)) > 0) {
					outp.write(b, 0, i);
				}
				outp.flush();
			} catch (Exception e) {
				logger.debug("Error!");
				e.printStackTrace();
			} finally {
				if (insr != null) {
					try {
						insr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					insr = null;
				}
				if (outp != null) {
					// out.clear();
					// out=pageContext.pushBody();
					try {
						outp.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					outp = null;
				}
			}
		}
	}

	/**
	 * 加载温馨提示页面
	 */
	@RequestMapping(params = "face=tipWindow")
	public String updatePassword() {

		return "portal/index/tipWindow";
	}
	
	/**
	 * 2016-12-30
	 * @param 新年贺词
	 * @return
	 */
	@RequestMapping(params = "action=jumpNewYear")
	public String jumpNewYear(HttpServletRequest request) {
	
		return "portal/index/happyNewYear";
	}
	
}
