package com.gdssoft.cqjt.controller.portal.index;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.core.tools.WordSegmentTool;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.FileDownload;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.TextPublication;
import com.gdssoft.cqjt.pojo.TrafficRunVo;
import com.gdssoft.cqjt.pojo.search.InnerSiteSearch;
import com.gdssoft.cqjt.pojo.search.PublicArchivesSearch;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;
import com.gdssoft.cqjt.service.FileDownloadService;
import com.gdssoft.cqjt.service.FileWeatherService;
import com.gdssoft.cqjt.service.OAService;
import com.gdssoft.cqjt.service.TextGovInfoService;
import com.gdssoft.cqjt.service.TextNewsService;
import com.gdssoft.cqjt.service.TextPublicationService;
import com.gdssoft.cqjt.service.TrafficRunService;
import com.gdssoft.cqjt.service.search.SearchEngineService;
import com.gdssoft.cqjt.service.videoNews.VideoNewsService;
import com.gdssoft.cqjt.util.PageUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping("/guest/news.xhtml")
public class NewsController extends BaseController {

	@Resource(name = "textPublicationServiceImpl")
	private TextPublicationService textPublicationService;

	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsService;

	@Resource(name = "fileDownloadService")
	private FileDownloadService fileDownloadService;
	
	@Resource(name = "fileWeatherService")
	private FileWeatherService fileWeatherService;

	@Resource(name = "videoNewsServiceImpl")
	private VideoNewsService videoNewsService;

	@Resource(name = "oaService")
	private OAService oaService;

	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;

	@Resource(name = "searchEngineServiceImpl")
	private SearchEngineService searchEngineService;

	@Resource(name = "trafficRunService")
	private TrafficRunService trafficRunService;
	
	@RequestMapping(params = "face=dailyInfoList")
	public String showDailyInfoList(Model model) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);
		List<TextPublication> pubList = textPublicationService.getPub("", "",
				start, end, 0, SystemContext.getDefaultPageSize());
		model.addAttribute("textPublications", pubList);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/index/dailyInfoList";
	}

	@RequestMapping(params = "action=queryDailyInfo")
	public void showDailyInfoList(HttpServletResponse response,
			@RequestParam("pageIndex") int pageIndex) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);
		List<TextPublication> pubList = textPublicationService.getPub("", "",
				start, end, pageIndex, SystemContext.getDefaultPageSize());
		// PageBean pb = new PageBean(pubList, pageIndex,
		// SystemContext.getDefaultPageSize());
		this.writeResult(response, pubList);
	}

	@RequestMapping(params = "face=dailyInfoDetail")
	public String showDailyInfoDetail(Model model,
			@RequestParam("pubId") String pubId) {

		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();

		TextPublication textPublication = textPublicationService
				.getPubDetail(pubId);
		int countyDynamics = textPublication.getCountyDynamics().size();
		int netInfos = textPublication.getNetInfos().size();
		int oneInfos = textPublication.getOneInfos().size();
		int workDynamics = textPublication.getWorkDynamics().size();

		model.addAttribute("textPublication", textPublication);
		model.addAttribute("countyDynamics", countyDynamics);
		model.addAttribute("netInfos", netInfos);
		model.addAttribute("oneInfos", oneInfos);
		model.addAttribute("workDynamics", workDynamics);
		model.addAttribute("pubId", pubId);
		List<TextPublication> pubList = textPublicationService.getPub("", "",
				start, end, 0, 20);
		model.addAttribute("textPublications", pubList);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/index/dailyInfoDetail";
	}

	/**
	 * 交通信息首次加载查询数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=trafficView")
	public String trafficView(Model model) {
		Calendar calendar = Calendar.getInstance();
		// 默认取当前减一年的日期时间为开始
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);
		int pageIndex = 0;
		List<TextNews> trafficNews = textNewsService.getSiteNewsList(start,
				end, new String[] { "3" }, new String[] { "交通要闻" }, pageIndex,
				SystemContext.getDefaultPageSize());
		logger.debug(trafficNews.size());
		// PageBean page = new PageBean(trafficNews, 1,
		// SystemContext.getDefaultPageSize());
		model.addAttribute("paginations", trafficNews);
		// 图片新闻，取前3条
		List<TextNews> trafficPhotoNews = textNewsService.getPhotoNewsList(3);
		List<Map<String,Object>> phoneList = new ArrayList<Map<String,Object>>();
		if(trafficPhotoNews != null){
			for (TextNews t : trafficPhotoNews) {
				String url = t.getPhotoUrl();
				String newsId = t.getNewsId();
				String newsTitle = t.getNewsTitle();
				if(StringUtils.isNotBlank(url)){
					String imgUrls[] = url.split(",");
					for (String s : imgUrls) {
						Map<String,Object> imgMap = new HashMap<String,Object>();
						imgMap.put("newsId", newsId);
						imgMap.put("newsTitle", newsTitle);
						imgMap.put("photoUrl", s);
						phoneList.add(imgMap);
						break;
					}
				}
			}
		}
		model.addAttribute("trafficPhotoNews", phoneList);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/trafficInfoList";
	}
	/**
	 * 新闻首次加载查询数据
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=firstView")
	public String firstView(Model model,@RequestParam("name") String categoryname,@RequestParam("index") int index) {
			Calendar calendar = Calendar.getInstance();
			// 默认取当前减一年的日期时间为开始
			calendar.add(Calendar.YEAR, -1);
			Date start = calendar.getTime();
			//curnames目前只考虑单个
			try {
				categoryname=new String(categoryname.getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			Date end = this.getEndDate("");
			String []categorys;
			if(StringUtils.isBlank(categoryname)){
				categorys=new String[] { "交通要闻" };
			}else{
				categorys=categoryname.split("\\.");
				if(categorys==null||categorys.length==0){
					categorys=new String[] { "交通要闻" };
				}
			}
			ArrayList<String> nameList=new ArrayList<>();
		String currname=null;
		for(int i=0;i<categorys.length;i++){
				if(index==i){
					currname=categorys[i];

				}
				nameList.add(categorys[i]);
			}
		if(currname!=null){
			categorys=new String[] {currname };
		}

		logger.debug("start：" + start + ",end：" + end+",name:"+categoryname);
			int pageIndex = 0;
			List<TextNews> trafficNews = textNewsService.getSiteNewsList(start,
					end, new String[] { "3" },categorys , pageIndex,
					SystemContext.getDefaultPageSize());
			logger.debug(trafficNews.size());
			// PageBean page = new PageBean(trafficNews, 1,
			// SystemContext.getDefaultPageSize());
			model.addAttribute("paginations", trafficNews);
			model.addAttribute("titles", nameList);
			model.addAttribute("cur", categorys[0]);
			// 图片新闻，取前3条
			List<TextNews> trafficPhotoNews = textNewsService.getPhotoNewsList(3);
			List<Map<String,Object>> phoneList = new ArrayList<Map<String,Object>>();
			if(trafficPhotoNews != null){
				for (TextNews t : trafficPhotoNews) {
					String url = t.getPhotoUrl();
					String newsId = t.getNewsId();
					String newsTitle = t.getNewsTitle();
					if(StringUtils.isNotBlank(url)){
						String imgUrls[] = url.split(",");
						for (String s : imgUrls) {
							Map<String,Object> imgMap = new HashMap<String,Object>();
							imgMap.put("newsId", newsId);
							imgMap.put("newsTitle", newsTitle);
							imgMap.put("photoUrl", s);
							phoneList.add(imgMap);
							break;
						}
					}
				}
			}
			model.addAttribute("trafficPhotoNews", phoneList);
			/* ==========当前访问人数========== */
			model.addAttribute("visiterNum", this.getCount());
			return "portal/traffic/newsInfoList";
	}
	/**
	 * 新闻信息分页时查询数据
	 *
	 * @param pageIndex
	 * @param response
	 */
	@RequestMapping(params = "action=getView")
	public void getView(@RequestParam("pageIndex") int pageIndex,
								HttpServletResponse response,@RequestParam("name") String categoryname) {
		Calendar calendar = Calendar.getInstance();
		// 默认取当前减一年的日期时间为开始
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		String []categorys;
		if(StringUtils.isBlank(categoryname)){
			categorys=new String[] { "交通要闻" };
		}else{
			categorys=categoryname.split(",");
			if(categorys==null||categorys.length==0){
				categorys=new String[] { "交通要闻" };
			}
		}
		int count=-1;
		if(pageIndex==-1){

		}
		logger.info("start：" + start + ",end：" + end+",name:"+categoryname);
		List<TextNews> trafficNews = textNewsService.getSiteNewsList(start,
				end, new String[] { "3" }, categorys, pageIndex,
				SystemContext.getDefaultPageSize());
		// PageBean page = new PageBean(trafficNews, pageIndex,
		// SystemContext.getDefaultPageSize());

		this.writeResult(response, trafficNews);
	}
	/**
	 * 交通信息分页时查询数据
	 * 
	 * @param pageIndex
	 * @param response
	 */
	@RequestMapping(params = "action=getTraffictView")
	public void getTraffictView(@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response) {
		Calendar calendar = Calendar.getInstance();
		// 默认取当前减一年的日期时间为开始
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.info("start：" + start + ",end：" + end);
		List<TextNews> trafficNews = textNewsService.getSiteNewsList(start,
				end, new String[] { "3" }, new String[] { "交通要闻" }, pageIndex,
				SystemContext.getDefaultPageSize());
		// PageBean page = new PageBean(trafficNews, pageIndex,
		// SystemContext.getDefaultPageSize());

		this.writeResult(response, trafficNews);
	}

	/**
	 * 交通信息查看详情
	 * 
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=newsDetail")
	public String showNewsDetails(Model model,
			@RequestParam("newsId") String newsId) {

		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		model.addAttribute("textNews", textNews);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);
		int pageIndex = 0;
		List<TextNews> list = textNewsService.getSiteNewsList(start, end,
				new String[] { "3" }, new String[] { "交通要闻" }, pageIndex, 20);
		/*
		 * if(list.size()>20){ list = list.subList(0, 20); }
		 */
		model.addAttribute("textNewsList", list);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/newsDetail";
	}

	/**
	 * 行业动态区县动态等信息查看详情
	 * 
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=dynamicNewsDetail")
	public String showDynamicNewsDetails(Model model,
			@RequestParam("newsId") String newsId,
			@RequestParam("flagType") String flagType) {
		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		model.addAttribute("textNews", textNews);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);
		int pageIndex = 0;
		String[] dept_categorys = null;
		String[] categorys = null;
		if (flagType.equals("cs")) {
			dept_categorys = new String[] { "市交委机关" };
			categorys = new String[] {"政务动态","安全监管","财政信息","风采集锦","妇女工作",
					"工程建设领域项目信息公开及诚信体系","工会工作","交通规划","交通统计",
					"离退休干部相关政策","廉政动态","廉政法规","廉政文化", "人事信息","信访工作",
					"应急管理","政策解读", "政风行风","政府采购情况 ","行政审批项目库","领导活动","领导介绍","职能机构"};
		}
		if (flagType.equals("hy")) {
			dept_categorys = new String[] { "委属单位", "市属相关交通企业" };
			categorys = new String[] { "政务动态", "交通统计", "政策解读", "政策解读" };
		}
		if (flagType.equals("qx")) {
			dept_categorys = new String[] { "区县交通局" };
			categorys = new String[] { "政务动态" };
		}

		List<TextNews> list = textNewsService.getDynamicSiteNewsList(start,
				end, new String[] { "1", "2", "3", "8", "9" }, categorys,
				dept_categorys, pageIndex, 20);
		model.addAttribute("textNewsList", list);
		model.addAttribute("flagType", flagType);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/dynamicNewsDetail";
	}

	/**
	 * 行业动态更多详情页 首次加载查询数据
	 * 
	 * @param model
	 * @edit zhp 20141125
	 * @return
	 */
	@RequestMapping(params = "face=industryNewsList")
	public String industryNewsList(Model model,
			@RequestParam("flagType") String flagType) {
		Calendar calendar = Calendar.getInstance();
		// 默认取当前减一年的日期时间为开始
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);
		int pageIndex = 0;
		String[] dept_categorys = null;
		String[] categorys = null;
		if (flagType.equals("cs")) {
			dept_categorys = new String[] { "市交委机关" };
			categorys = new String[] {"政务动态","安全监管","财政信息","风采集锦","妇女工作",
					"工程建设领域项目信息公开及诚信体系","工会工作","交通规划","交通统计",
					"离退休干部相关政策","廉政动态","廉政法规","廉政文化", "人事信息","信访工作",
					"应急管理","政策解读", "政风行风","政府采购情况 ","行政审批项目库","领导活动","领导介绍","职能机构"};
		}
		if (flagType.equals("hy")) {
			dept_categorys = new String[] { "委属单位", "市属相关交通企业" };
			categorys = new String[] { "政务动态", "交通统计", "政策解读", "政策解读" };
		}
		if (flagType.equals("qx")) {
			dept_categorys = new String[] { "区县交通局" };
			categorys = new String[] { "政务动态" };
		}
		List<TextNews> publicNews = textNewsService.getDynamicSiteNewsList(
				start, end, new String[] {"1", "2", "3", "8", "9"},
				categorys, dept_categorys, pageIndex,
				SystemContext.getDefaultPageSize());
		model.addAttribute("paginations", publicNews);
		model.addAttribute("flagType", flagType);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/industryNewsList";
	}

	/**
	 * 资料下载首次加载查询数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=fileView")
	public String fileView(Model model, HttpServletRequest request) {
		String fileName = request.getParameter("fileName");
		int pageIndex = 0;
		if (null != request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch (Exception e) {
			}
		}
		List<FileDownload> fileDownload = fileDownloadService.getFile(fileName, null,
				null, "0", pageIndex, SystemContext.getDefaultPageSize());
		logger.debug(fileDownload.size());
		model.addAttribute("paginations", fileDownload);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		model.addAttribute("fileName", fileName);
		return "portal/fileDownload/fileList";
	}
	
	/**
	 * 资料下载首次加载查询数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=fileWeatherView")
	public String fileWeatherView(Model model, HttpServletRequest request) {
		int pageIndex = 0;
		String fileType = request.getParameter("fileType");
		if (null != request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch (Exception e) {
			}
		}
		List<FileDownload> fileDownload = fileWeatherService.getFile("", null,
				null, "0", pageIndex, SystemContext.getDefaultPageSize(),fileType);
		logger.debug(fileDownload.size());
		model.addAttribute("paginations", fileDownload);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/fileWeather/fileList";
	}
	
	/**
	 * 获取列表
	 * @param response
	 */
	@RequestMapping(params = "face=getWeatherList")
	public void getWeatherList(HttpServletResponse response,HttpServletRequest request) {
		String fileName = request.getParameter("fileName");
		Date startDate = parseDate(request.getParameter("startDate"));
		Date endDate = parseDate(request.getParameter("endDate"));
		String isDel = request.getParameter("isDel");
		int pageIndex = parseInt(request.getParameter("pageIndex"),0);
		int pageSize = parseInt(request.getParameter("pageSize"),20);
		String fileType = request.getParameter("fileType");
		
		List<FileDownload> weatherList = new ArrayList<FileDownload>();
		weatherList = fileWeatherService.getFile(null, null, null, "0", pageIndex, pageSize, fileType);
		response.setCharacterEncoding("UTF-8");
		try {
			this.writeResult(response, weatherList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 交通运行信息列表显示
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=runnVoListView")
	public String runnVoList(Model model, HttpServletRequest request) {
		int pageIndex = parseInt(request.getParameter("pageIndex"),0);
		int pageSize = parseInt(request.getParameter("pageSize"),20);
		
		//交通运行动态
		Map<String, Object> map = new HashMap<String, Object>();
		int count = trafficRunService.queryCount(map);
		PageUtils page = new PageUtils(count, pageIndex, pageSize);
		map.put("beginLimit", page.getLimitBegin());
		map.put("endLimit", page.getLimitEnd());
		List<TrafficRunVo> runVoList = trafficRunService.queryPageList(map);
		model.addAttribute("runVoList", runVoList);
		return "portal/trafficRun/list";
	}
	
	/**
	 * 交通运行信息列表
	 * @param response
	 */
	@RequestMapping(params = "face=runnVoList")
	public void runnVoList(HttpServletResponse response,HttpServletRequest request) {
		int pageIndex = parseInt(request.getParameter("pageIndex"),0);
		int pageSize = parseInt(request.getParameter("pageSize"),20);
		
		//交通运行动态
		Map<String, Object> map = new HashMap<String, Object>();
		int count = trafficRunService.queryCount(map);
		PageUtils page = new PageUtils(count, pageIndex, pageSize);
		map.put("beginLimit", page.getLimitBegin());
		map.put("endLimit", page.getLimitEnd());
		List<TrafficRunVo> runVoList = trafficRunService.queryPageList(map);
		response.setCharacterEncoding("UTF-8");
		try {
			this.writeResult(response, runVoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 水位气象查看详情
	 * 
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=runnVoDetails")
	public String runnVoDetails(Model model, @RequestParam("runnId") String runnId) {

		TrafficRunVo runVo = trafficRunService.query(runnId);
		model.addAttribute("runVo", runVo);

		//交通运行动态
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginLimit", 0);
		map.put("endLimit", 20);
		List<TrafficRunVo> runVoList = trafficRunService.queryPageList(map);
		
		model.addAttribute("runVoList", runVoList);
		model.addAttribute("visiterNum", this.getCount());
		return "portal/trafficRun/details";
	}
	
	/**
	 * 字符串转换日期
	 */
	private Date parseDate(String s){
		Date result = null;
		if(StringUtils.isNotBlank(s)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				result = sdf.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 字符串转换INT
	 * @param s
	 * @param defaultStr
	 * @return
	 */
	private int parseInt(String s, int defaultStr){
		int result = defaultStr;
		if(StringUtils.isNotBlank(s)){
			result = Integer.parseInt(s);
		}
		return result;
	}
	
	/**
	 * 水位气象查看详情
	 * 
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=fileWeatherDetails")
	public String fileWeatherDetails(Model model,
			@RequestParam("fileId") String fileId,@RequestParam("fileType") String fileType) {

		FileDownload fileDownload = fileWeatherService.getFileByFileId(fileId);
		model.addAttribute("fileDownload", fileDownload);

		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();

		int pageIndex = 0;
		List<FileDownload> list = fileWeatherService.getFile("", start, end, "0", pageIndex, 20,fileType);
		model.addAttribute("weatherList", list);
		model.addAttribute("visiterNum", this.getCount());
		return "portal/fileWeather/fileDetail";
	}
	
	/**
	 * 资料下载分页时查询数据
	 * 
	 * @param pageIndex
	 * @param response
	 */
	@RequestMapping(params = "action=getfileView")
	public void getfileView(@RequestParam("pageIndex") int pageIndex,@RequestParam("fileName") String fileName,
			HttpServletResponse response) {
		
		List<FileDownload> fileDownload = fileDownloadService.getAllFile(fileName,
				null, null, "0", pageIndex, SystemContext.getDefaultPageSize());

		this.writeResult(response, fileDownload);
	}

	/**
	 * 公告公示首次加载查询数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=publicView")
	public String publicView(Model model) {
		Calendar calendar = Calendar.getInstance();
		// 默认取当前减一年的日期时间为开始
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);
		int pageIndex = 0;
		List<TextNews> publicNews = textNewsService.getSiteNewsList(start, end,
				new String[] { "1", "2", "3", "8", "9" }, new String[] {
						"招标公告", "通知公告", "中标结果", "资质审查公示" }, pageIndex,
				SystemContext.getDefaultPageSize());
		logger.debug(publicNews.size());
		model.addAttribute("paginations", publicNews);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/publicNotice/publicNoticeList";
	}

	/**
	 * 公告公示分页时查询数据
	 * 
	 * @param pageIndex
	 * @param response
	 */
	@RequestMapping(params = "action=getPublicView")
	public void getPublicView(@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response) {
		Calendar calendar = Calendar.getInstance();
		// 默认取当前减一年的日期时间为开始
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);

		List<TextNews> publicNews = textNewsService.getSiteNewsList(start, end,
				new String[] { "1", "2", "3", "8", "9" }, new String[] {
						"招标公告", "通知公告", "中标结果", "资质审查公示" }, pageIndex,
				SystemContext.getDefaultPageSize());

		this.writeResult(response, publicNews);
	}

	/**
	 * 交通视频首次加载查询数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=videoView")
	public String videoView(Model model) {
		Calendar calendar = Calendar.getInstance();
		// 默认取当前减一年的日期时间为开始
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);

		List<VideoNews> videoNewsList = videoNewsService.getVideoList("", "",
				start, end, new String[] { "2" }, 0, 6);
		logger.debug(videoNewsList.size());
		// PageBean page = new PageBean(videoNewsList, 1, 6);
		model.addAttribute("paginations", videoNewsList);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/videoNewsList";
	}

	/**
	 * 交通视频分页时查询数据
	 * 
	 * @param pageIndex
	 * @param response
	 */
	@RequestMapping(params = "action=getVideoView")
	public void getVideoView(@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response) {
		Calendar calendar = Calendar.getInstance();
		// 默认取当前减一年的日期时间为开始
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);

		List<VideoNews> videoNewsList = videoNewsService.getVideoList("", "",
				start, end, new String[] { "2" }, pageIndex, 6);
		this.writeResult(response, videoNewsList);
	}

	/**
	 * 交通视频查看详情
	 * 
	 * @param model
	 * @param videoId
	 * @return
	 */
	@RequestMapping(params = "face=videoNewsDetail")
	public String videoNewsDetail(Model model,
			@RequestParam("videoId") String videoId) {

		VideoNews videoNews = videoNewsService.getVideoByVideoId(videoId);
		model.addAttribute("videoNews", videoNews);

		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();

		List<VideoNews> videoNewsList = videoNewsService.getVideoNewsList("",
				"", start, end, new String[] { "2" }, 0, 3);
		/*
		 * if (videoNewsList.size() > 3) videoNewsList=videoNewsList.subList(0,
		 * 3);
		 */
		model.addAttribute("videoNewsList", videoNewsList);
		model.addAttribute("player", "/flowplayer-3.2.14/flowplayer-3.2.14.swf");
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/videoNewsDetail";
	}

	/**
	 * 行业动态分页时查询数据
	 * 
	 * @param pageIndex
	 * @param response
	 */
	@RequestMapping(params = "action=getIndustryNewsList")
	public void getIndustryNewsList(@RequestParam("pageIndex") int pageIndex,
			@RequestParam("flagType") String flagType,
			HttpServletResponse response) {
		Calendar calendar = Calendar.getInstance();
		// 默认取当前减一年的日期时间为开始
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		Date end = this.getEndDate("");
		logger.debug("start：" + start + ",end：" + end);

		String[] dept_categorys = null;
		String[] categorys = null;
		if (flagType.equals("cs")) {
			dept_categorys = new String[] { "市交委机关" };
			categorys = new String[] {"政务动态","安全监管","财政信息","风采集锦","妇女工作",
					"工程建设领域项目信息公开及诚信体系","工会工作","交通规划","交通统计",
					"离退休干部相关政策","廉政动态","廉政法规","廉政文化", "人事信息","信访工作",
					"应急管理","政策解读", "政风行风","政府采购情况 ","行政审批项目库","领导活动","领导介绍","职能机构"};
		}
		if (flagType.equals("hy")) {
			dept_categorys = new String[] { "委属单位", "市属相关交通企业" };
			categorys = new String[] { "政务动态", "交通统计", "政策解读", "政策解读" };
		}
		if (flagType.equals("qx")) {
			dept_categorys = new String[] { "区县交通局" };
			categorys = new String[] { "政务动态" };
		}
		List<TextNews> publicNews = textNewsService.getDynamicSiteNewsList(
				start, end, new String[] { "1", "2", "3", "8", "9" },
				categorys, dept_categorys, pageIndex,
				SystemContext.getDefaultPageSize());
		this.writeResult(response, publicNews);
	}

	// 跳转到交委公文详情页面
	@RequestMapping(params = "face=getPublicArchivesDetail")
	public String getPublicArchivesDetail(Model model,
			HttpServletRequest request) {
		String archivesId = request.getParameter("archivesId");
		String schema = request.getParameter("schema");
		// String publicArchivesList =
		// oaService.getPublicArchivesDetail(archivesId);
		model.addAttribute("archivesId", archivesId);
		model.addAttribute("schema", schema);
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/publicArchivesDetail";
	}

	// 交委公文详情页面加载数据
	@RequestMapping(params = "action=getPublicArchivesDetail")
	public void getPublicArchivesDetails(HttpServletResponse response,
			HttpServletRequest request) {
		String archivesId = request.getParameter("archivesId");
		String schema = request.getParameter("schema");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(
					oaService.getPublicArchivesDetail(archivesId, schema));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("公交公文查询详情错误", e);
		}
	}

	/**
	 * 政务信息查看详情
	 * 
	 * @param model
	 * @param giId
	 * @return
	 */
	@RequestMapping(params = "face=govInfoDetail")
	public String govInfoDetail(Model model, @RequestParam("giId") String giId) {

		TextGovInfo textGovInfo = textGovInfoService.getTextGovInfo(giId);
		model.addAttribute("textGovInfo", textGovInfo);

		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();

		int pageIndex = 0;
		List<TextGovInfo> list = textGovInfoService.getTextGovInfoList("",
				start, end, "", pageIndex, 20);
		/*
		 * if(list.size()>20){ list = list.subList(0, 20); }
		 */
		model.addAttribute("textNewsList", list);
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/govInfoDetail";
	}

	/**
	 * 交委公文列表页面
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "face=publicArchsList")
	public String publicArchsList(HttpServletResponse response, Model model) {
		/*
		 * response.setCharacterEncoding("UTF-8"); try {
		 * response.getWriter().write(oaService.getPublicArchives(9999)); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */
		List<PublicArchivesSearch> searchResult = new ArrayList<PublicArchivesSearch>();
		searchResult = searchEngineService.getArchivesSearchInfoBySchema("oa",
				0, SystemContext.getDefaultPageSize());

		model.addAttribute("paginations", searchResult);
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/publicArchsList";
	}

	/**
	 * 交委公文列表页面加载数据
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "action=publicArchs")
	public void publicArchs(HttpServletResponse response, Model model,
			@RequestParam("pageIndex") int pageIndex) {
		/*
		 * response.setCharacterEncoding("UTF-8"); try {
		 * response.getWriter().write(oaService.getPublicArchives(9999)); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */
		List<PublicArchivesSearch> searchResult = new ArrayList<PublicArchivesSearch>();
		searchResult = searchEngineService.getArchivesSearchInfoBySchema("oa",
				pageIndex, SystemContext.getDefaultPageSize());
		response.setCharacterEncoding("UTF-8");
		try {
			this.writeResult(response, searchResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 公告公示查看详情
	 * 
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=pubNoticenewsDetail")
	public String showPubNewsDetails(Model model,
			@RequestParam("newsId") String newsId) {

		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		model.addAttribute("textNews", textNews);

		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();

		int pageIndex = 0;
		List<TextNews> publicNews = textNewsService.getSiteNewsList(start, end,
				new String[] { "1", "2", "3", "8", "9" }, new String[] {
						"招标公告", "通知公告", "中标结果", "资质审查公示" }, pageIndex, 20);
		/*
		 * if(publicNews.size()>20){ publicNews = publicNews.subList(0, 20); }
		 */
		model.addAttribute("textNewsList", publicNews);
		model.addAttribute("visiterNum", this.getCount());
		return "portal/publicNotice/publicNoticeDetail";
	}

	/**
	 * 
	 * 信息查询页面 2014/09/15
	 * 
	 * 
	 */
	@RequestMapping(params = "face=infoSearch")
	public String infoSearch(Model model, HttpServletResponse response) {
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/infoSearch";
	}

	/**
	 * 
	 * 信息查询页面-公开公文首次加载 2014/09/19
	 * 
	 * 
	 */
	@RequestMapping(params = "face=queryPubNotice")
	public String queryPubNotice(Model model,
			@RequestParam("searchword") String searchword,
			@RequestParam("flag") String flag,
			@RequestParam("pageIndex") int pageIndex) {
		String keyWord = "";
		if (!StringUtils.isBlank(searchword)) {
			WordSegmentTool wordSegment = new WordSegmentTool();
			try {
				searchword = URLDecoder.decode(searchword, "utf-8");
				keyWord = wordSegment.segmentWord(searchword);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<PublicArchivesSearch> searchResult = new ArrayList<PublicArchivesSearch>();
		if (!(searchword.equals("") || (searchword.trim()).length() == 0)) {
			searchResult = searchEngineService.getArchivesSearchInfo(keyWord,
					pageIndex, SystemContext.getDefaultPageSize());
		} else {
			searchResult = searchEngineService.getArchivesSearchInfo(pageIndex,
					SystemContext.getDefaultPageSize());
		}
		model.addAttribute("searchResult", searchResult);
		model.addAttribute("searchword", searchword);
		model.addAttribute("flag", flag);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/infoSearch";

	}

	/**
	 * 
	 * 信息查询页面-交通信息-交通课堂-交通视频2014/09/19
	 * 
	 * 
	 */
	@RequestMapping(params = "face=queryInfo")
	public String queryInfo(Model model,
			@RequestParam("searchword") String searchword,
			@RequestParam("flag") String flag,
			@RequestParam("pageIndex") int pageIndex) {
		String keyWord = "";
		if (!StringUtils.isBlank(searchword)) {
			WordSegmentTool wordSegment = new WordSegmentTool();
			try {
				searchword = URLDecoder.decode(searchword, "utf-8");
				keyWord = wordSegment.segmentWord(searchword);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<InnerSiteSearch> searchResult = new ArrayList<InnerSiteSearch>();
		logger.debug("keyWord：" + keyWord + ",flag：" + flag);
		searchResult = searchEngineService.getInnerTrafficSearchInfo(keyWord,
				flag, pageIndex, SystemContext.getDefaultPageSize());
		model.addAttribute("searchResult", searchResult);
		model.addAttribute("searchword", searchword);
		model.addAttribute("flag", flag);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/traffic/infoSearch";

	}

	private Configuration configuration = null;

	public NewsController() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
	}

	@RequestMapping(params = "action=exportTable")
	public void exportTable(@RequestParam("pubId") String pubId, Model model,
			HttpServletResponse response) {
		
		TextPublication textPublication = textPublicationService
				.getPubDetail(pubId);

		String fileName = textPublication.getPubName();
		String finalFileName = "";
		try {
			finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msword");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ finalFileName + ".doc");

		// 要填入模本的数据文件
		Map<String, Object> dataMap = new HashMap<String, Object>();
		getData(dataMap, textPublication);
		// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		configuration.setClassForTemplateLoading(this.getClass(),
				"/com/gdssoft/core/tools");
		Template t = null;
		try {
			// template.ftl为要装载的模板
			t = configuration.getTemplate("template.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}

		java.io.OutputStream outFile = null;
		try {
			outFile = response.getOutputStream();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Writer out = null;

		try {
			out = new BufferedWriter(new OutputStreamWriter(outFile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			t.process(dataMap, out);
			outFile.close();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());

		//return "portal/index/dailyInfoDetail";
	}
	
	//批量每日信息导出  在模板中去掉首位部分，在导出后将其添上   
	//
	//
	//
	//
//	@RequestMapping(params = "action=exportTable")
//	public void exportTable(@RequestParam("pubId") String pubId, Model model,
//			HttpServletResponse response) {
//		
//		
//		Date start = parseDate("2017-01-01 00:00:00");
//		Date end = parseDate("2018-01-01 00:00:00");
//		
//		List<TextPublication> testMapList = new ArrayList<TextPublication>();
//		
//		List<TextPublication> pubList = textPublicationService.getPub("", "",
//				start, end, 0, 200);
//		
//		for (TextPublication textPub : pubList) {
//		
//		TextPublication textPublication = textPublicationService
//				.getPubDetail(textPub.getPubId());
//
//		testMapList.add(textPublication);
//		}
//		
////		String fileName = textPublication.getPubName();
//		String finalFileName = "2017";
////		try {
////			finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
////		} catch (UnsupportedEncodingException e1) {
////			e1.printStackTrace();
////		}
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/msword");
//		response.setHeader("Content-Disposition", "attachment; filename="
//				+ finalFileName + ".doc");
//
//		
//		// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
//		configuration.setClassForTemplateLoading(this.getClass(),
//				"/com/gdssoft/core/tools");
//		Template t = null;
//		try {
//			// template.ftl为要装载的模板
//			t = configuration.getTemplate("template1.ftl");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		java.io.OutputStream outFile = null;
//		try {
//			outFile = response.getOutputStream();
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		}
//		Writer out = null;
//
//		try {
//			out = new BufferedWriter(new OutputStreamWriter(outFile, "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		try {
//			// 要填入模本的数据文件
//			for(int a=0;a<testMapList.size();a++){
//				getData(dataMap, testMapList.get(a));
//				t.process(dataMap, out);
//			}
//			outFile.close();
//			out.close();
//		} catch (TemplateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		/* ==========当前访问人数========== */
//		model.addAttribute("visiterNum", this.getCount());
//
//		//return "portal/index/dailyInfoDetail";
//	}

	/**
	 * 注意dataMap里存放的数据Key值要与模板中的参数相对应
	 * 
	 * @param dataMap
	 */
	private void getData(Map<String, Object> dataMap,
			TextPublication textPublication) {
		int countyDynamics = textPublication.getCountyDynamics().size();
		int netInfos = textPublication.getNetInfos().size();
		int oneInfos = textPublication.getOneInfos().size();
		int workDynamics = textPublication.getWorkDynamics().size();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dstr = textPublication.getCreateDate();
		Date dat = null;
		try {
			dat = sdf.parse(dstr);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		String createDate = DateFormat.getDateInstance(DateFormat.FULL).format(
				dat);
		String s = "";
		String str = "";
		int m = 0;
		List<Map<String, Object>> workList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> countyList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> oneList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> netList = new ArrayList<Map<String, Object>>();

		dataMap.put("fileName", textPublication.getPubName());
		dataMap.put("createDate", createDate);
		dataMap.put("countyDynamics", countyDynamics);
		dataMap.put("netInfos", netInfos);
		dataMap.put("oneInfos", oneInfos);
		dataMap.put("workDynamics", workDynamics);

		if (workDynamics > 0) {
			dataMap.put("type", "工作动态");
			for (int i = 0; i < workDynamics; i++) {
				s = textPublication.getWorkDynamics().get(i).getGiContent();
				if (!s.equals("") || s != null) {
					str = s.replaceAll("<[.[^<]]*>", "");
				}
				m = i + 1;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", m + "、"
						+ textPublication.getWorkDynamics().get(i).getGiTitle());
				map.put("tit", textPublication.getWorkDynamics().get(i)
						.getGiTitle());
				map.put("content", str);
				map.put("deptName", textPublication.getWorkDynamics().get(i)
						.getEntryDept());
				workList.add(map);
			}
		}
		dataMap.put("work", workList);

		if (countyDynamics > 0) {
			dataMap.put("type", "区县动态");
			for (int i = 0; i < countyDynamics; i++) {
				s = textPublication.getCountyDynamics().get(i).getGiContent();
				if (!s.equals("") || s != null) {
					str = s.replaceAll("<[.[^<]]*>", "");
				}
				m = i + 1;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", m
						+ "、"
						+ textPublication.getCountyDynamics().get(i)
								.getGiTitle());
				map.put("tit", textPublication.getCountyDynamics().get(i)
						.getGiTitle());
				map.put("content", str);
				map.put("deptName", textPublication.getCountyDynamics().get(i)
						.getEntryDept());
				countyList.add(map);
			}
		}
		dataMap.put("county", countyList);

		if (oneInfos > 0) {
			dataMap.put("type", "一句话信息");
			for (int i = 0; i < oneInfos; i++) {
				s = textPublication.getOneInfos().get(i).getGiContent();
				if (!s.equals("") || s != null) {
					str = s.replaceAll("<[.[^<]]*>", "");
				}
				m = i + 1;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", m + "、"
						+ textPublication.getOneInfos().get(i).getGiTitle());
				map.put("tit", textPublication.getOneInfos().get(i)
						.getGiTitle());
				map.put("content", str);
				map.put("deptName", textPublication.getOneInfos().get(i)
						.getEntryDept());
				oneList.add(map);
			}
		}
		dataMap.put("one", oneList);

		if (netInfos > 0) {
			dataMap.put("type", "网络信息");
			for (int i = 0; i < netInfos; i++) {
				s = textPublication.getNetInfos().get(i).getGiContent();
				if (!s.equals("") || s != null) {
					str = s.replaceAll("<[.[^<]]*>", "");
				}
				m = i + 1;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", m + "、"
						+ textPublication.getNetInfos().get(i).getGiTitle());
				map.put("tit", textPublication.getNetInfos().get(i)
						.getGiTitle());
				map.put("content", str);
				map.put("deptName", textPublication.getNetInfos().get(i)
						.getEntryDept());
				netList.add(map);
			}
		}
		dataMap.put("net", netList);
	}
	
	

	
	

	
	
	
	
	
}
