package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.FileUploader;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.dao.DepartmentScoreDao;
import com.gdssoft.cqjt.pojo.DepartmentScore;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.videoNews.VideoRecord;
import com.gdssoft.cqjt.service.CheckStandardService;
import com.gdssoft.cqjt.service.ColumnService;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.TextGovInfoService;
import com.gdssoft.cqjt.service.TextNewsService;
import com.gdssoft.cqjt.service.cms.NewsService;
import com.gdssoft.cqjt.service.cms.ProgramService;
import com.gdssoft.cqjt.service.videoNews.VideoRecordService;
import com.gdssoft.cqjt.service.UserDetailService;

@Controller
@RequestMapping("/console/siteTextNews.xhtml")
public class SiteTextNewsController extends BaseController {

	@Autowired
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsService;

	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;

	@Autowired
	@Resource(name = "columnService")
	private ColumnService columnService;
	
	@Autowired
	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;

	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userDetailService;

	// @Autowired
	// @Resource(name = "reportFormServiceImpl")
	// private ReportFormService reportFormService ;

	@Autowired
	@Resource(name = "videoRecordServiceImpl")
	private VideoRecordService videoRecordService;

	@Autowired
	@Resource(name = "cmsNewsService")
	private NewsService cmsNewsService;

	@Autowired
	@Resource(name = "programService")
	private ProgramService programService;

	@Autowired
	@Resource(name = "deptScoreDao")
	private DepartmentScoreDao deptScoreDao;

	@Autowired
	@Resource(name = "checkStandardServiceImpl")
	private CheckStandardService checkStandardService;

	/**
	 * 网站信息查询默认显示功能 H2602992 温长峰 20140616
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getSiteInfoView")
	public String query( Model model) {
		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		 int pageIndex = 0;
		 List<TextNews> textNewsList = textNewsService.getSiteNewsList("", "",
				null, null, new String[] { "1", "2", "3", "7", "8", "9" }, "",
				pageIndex, SystemContext.getDefaultPageSize());
		/*
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 */
		model.addAttribute("paginations", textNewsList);

		return "console/textnews/siteInfo/listSiteInfo";
	}

	/**
	 * 网站信息分页查询显示功能 H2602992 温长峰 20140616
	 * 
	 * @author
	 * @param pageIndex
	 * @param pageSize
	 * @param newsTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=querySiteNewsView")
	public String querySiteNews( @RequestParam("newsTitle") String newsTitle,
			 @RequestParam("entryUser") String entryUser,
			 @RequestParam("entryDateS") String entryDateS,
			 @RequestParam("entryDateE") String entryDateE,
			 @RequestParam("pageIndex") int pageIndex,
			 HttpServletResponse response,  Model model)
			throws UnsupportedEncodingException {
		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		entryUser = URLDecoder.decode(entryUser, "utf-8");
		 Date start = this.getStartDate(entryDateS);
		 Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		 List<TextNews> getVerifySiteNewsList = textNewsService.getSiteNewsList(
				newsTitle, entryUser, start, end,
				new String[] { "1", "2", "3" }, "", pageIndex,
				SystemContext.getDefaultPageSize());
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", getVerifySiteNewsList);

		return "console/textnews/siteInfo/listSiteInfo";
	}

	/**
	 * 网站信息删除功能 H2602992 温长峰 20140616
	 * 
	 * @param model
	 * @param newsId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteSiteInfo")
	public void deleteSiteInfo( Model model,
			 @RequestParam("newsId") String newsId,
			 @RequestParam("outerNewsId") String outerNewsId,
			 @RequestParam("flag") String flag,  HttpServletResponse response) {

		 String msg = "";
		try {
			textNewsService.delete(newsId);
			if (!StringUtils.isBlank(outerNewsId)) {// 对应外网id不等于null，则删除时需同时将外网信息也删除
				 TextNews textNews = new TextNews();
				textNews.setNewsId(newsId);
				textNews.setOuterNewsId(outerNewsId);
				cmsNewsService.delete(textNews);
			}
			 VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(newsId);
			videoRecord.setFlag(flag);
			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}

		this.writeMessage(response, msg);
	}

	/**
	 * 网站信息校编默认显示功能 H2602992 温长峰 20140616
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getVerifySiteInfoView")
	public String queryVerifySiteNews( Model model) {
		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		 int pageIndex = 0;
		// 增加权限管控。
		String isCheckAgain;
		if (SystemContext.getRoleName().equals("WebsiteEditor")||SystemContext.getRoleName().equals("Admin")) {
			isCheckAgain = "0";
		} else {
			isCheckAgain = "1";
		}
		 List<TextNews> textNewsList = textNewsService.getSiteNewsList(null,
				null, new String[] { "2" }, isCheckAgain, pageIndex,
				SystemContext.getDefaultPageSize());

		/*
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 */
		model.addAttribute("paginations", textNewsList);

		return "console/textnews/siteInfo/listVerifySiteInfo";
	}

	/**
	 * 网站信息校编按条件查询 H2602992 20140619
	 * 
	 * @param newsTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param pageIndex
	 * @param flag
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=queryVerifySiteNewsView")
	public String queryVerifySiteNews(
			 @RequestParam("newsTitle") String newsTitle,
			 @RequestParam("entryUser") String entryUser,
			 @RequestParam("entryDateS") String entryDateS,
			 @RequestParam("entryDateE") String entryDateE,
			 @RequestParam("pageIndex") int pageIndex,
			 @RequestParam("flag") String flag,  HttpServletResponse response,
			 Model model) throws UnsupportedEncodingException {

		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		entryUser = URLDecoder.decode(entryUser, "utf-8");
		 Date start = this.getStartDate(entryDateS);
		 Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		 String[] flagArray = { flag };
		// 增加权限管控。
		String isCheckAgain;
		if (SystemContext.getRoleName().equals("WebsiteEditor")) {
			isCheckAgain = "0";
		} else {
			isCheckAgain = "1";
		}

		 List<TextNews> getVerifySiteNewsList = textNewsService.getSiteNewsList(
				newsTitle, entryUser, start, end, flagArray, isCheckAgain,
				pageIndex, SystemContext.getDefaultPageSize());

		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", getVerifySiteNewsList);
		return "console/textnews/siteInfo/listVerifySiteInfo";
	}

	/*
	 * 已校编网站信息跳转到编辑页面 H2603282 20140730
	 */
	@RequestMapping(params = "face=updateVerifySiteNewsView")
	public String editSite( Model model,  @RequestParam("newsId") String newsId) {

		 List<VideoRecord> newsRecord = videoRecordService
				.getVideoRecordList(newsId);
		model.addAttribute("newsRecord", newsRecord);
		model.addAttribute("userDeptID",
				textNewsService.getTextNewsDetail(newsId).getEntryDept());
		//TODO:
		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		List<String> photoList = new ArrayList<String>();
		if(textNews != null){
			String photoUrl = textNews.getPhotoUrl();
			if(StringUtils.isNotBlank(photoUrl)){
				String urls[] = photoUrl.split(",");
				for (String s : urls) {
					photoList.add(s);
				}
			}
		}
		model.addAttribute("photoList", photoList);
		model.addAttribute("textNewsEditInfo", textNews);
		model.addAttribute("textCategorys",
				columnService.getAllColumn("", null, null, "0"));
		/*
		 * String userName = textNewsService.getTextNewsDetail(newsId)
		 * .getEntryUser(); model.addAttribute("userName", userName);
		 * logger.debug("用户:" + userName);
		 */
		model.addAttribute("approvers",
				userDetailService.getApprovers(SystemContext.getUserNO()));
		if (SystemContext.getRoleName().equals("WebsiteEditor")) {
			model.addAttribute("textCategorysOuter", programService.getAll("0"));
		}
		return "console/textnews/siteInfo/updateVerifySiteNews";
	}

	/*
	 * 网站信息校编编辑跳转到编辑页面 H2602992 温长峰 20140616
	 */
	@RequestMapping(params = "face=editVerifySiteNewsView")
	public String edit( Model model,  @RequestParam("newsId") String newsId) {

		 List<VideoRecord> newsRecord = videoRecordService
				.getVideoRecordList(newsId);
		model.addAttribute("newsRecord", newsRecord);
		model.addAttribute("userDeptID",
				textNewsService.getTextNewsDetail(newsId).getEntryDept());

		/*
		 * String userName = textNewsService.getTextNewsDetail(newsId)
		 * .getEntryUser(); model.addAttribute("userName", userName);
		 * logger.debug("yonghu:" + userName);
		 */
		model.addAttribute("approvers",
				userDetailService.getApprovers(SystemContext.getUserNO()));
		logger.debug("账号:" + SystemContext.getUserNO());
		//TODO:

		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		List<String> photoList = new ArrayList<String>();
		if(textNews != null){
			String photoUrl = textNews.getPhotoUrl();
			if(StringUtils.isNotBlank(photoUrl)){
				String urls[] = photoUrl.split(",");
				for (String s : urls) {
					photoList.add(s);
				}
			}
		}
		model.addAttribute("photoList", photoList);
		model.addAttribute("textNewsEditInfo", textNews);
		model.addAttribute("textCategorys",
				columnService.getAllColumn("", null, null, "0"));
		if (SystemContext.getRoleName().equals("WebsiteEditor")) {
			model.addAttribute("textCategorysOuter", programService.getAll("0"));
		}
		return "console/textnews/siteInfo/editVerifySiteNews";
	}

	/**
	 * H2602992 网站信息校编编辑页面 保存功能 温长峰
	 * 添加置顶相关代码 
	 * @param textNews
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=updateSiteNews", method = RequestMethod.POST)
	public void updateSiteNews( @ModelAttribute("textNews") TextNews textNews,
			 @RequestParam("imageUrl") String imageUrl,
			 MultipartHttpServletRequest request,  HttpServletResponse response) {

		/*
		 * String aa =
		 * textNewsService.getTextNewsDetail(textNews.getNewsId()).getIsPublic
		 * (); logger.debug(aa);
		 */
		
//		if (null == textNews.getIsPhotosShow())
//			textNews.setIsPhotosShow("0");
		
		if (null == textNews.getStickState())
			textNews.setStickState("0");
		else if (textNews.getStickState().equals("1")) {
			// 数据库操作
			System.out.println("数据库操作");
			//1.查找状态为置顶的数据
			List<TextNews> textNewsold = textNewsService.getTextNewsByStickState("1");
			//2.将置顶数据改为0
			for (TextNews textNews2 : textNewsold) {
				textNewsService.updateTextNewsBynewsId(textNews2.getNewsId());
			}
			
		}
		
		if (null == textNews.getIsPublic()) {
			textNews.setIsPublic("0");
		} else if (textNews.getIsPublic().equals("0")) {
			textNews.setOuterCategory("");
		}

//		 MultipartFile newsImage = request.getFile("newsImage");
		if (textNews.getPhotoUrl() == null || textNews.getPhotoUrl().equals(""))
			textNews.setPhotoUrl(imageUrl);
		else {
//			textNews.setPhotoUrl(FileUploader.uploadFile(request, newsImage,
//					getImagePath(request.getSession().getServletContext())));
		}

		if (!StringUtils.isBlank(textNews.getNewsId())) {
			logger.debug("flag=" + textNews.getFlag());
			logger.debug(textNews.getIsPublic());

			DateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date entryDate = null;
			try {
				entryDate = fds.parse(textNews.getCreateDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			textNews.setEntryDate(entryDate);
			textNews.setIsDel("0");
			// textNews.setGovUseFlag("0");
			textNews.setCreateBy(textNews.getEntryUser());
			// 添加排序标识
			if (null != textNews.getStickState()) {
				if (textNews.getStickState().equals("1")) {
					textNews.setStickSort(DateUtil.getCurrentDate());
				}
				if (textNews.getStickState().equals("0")) {
					textNews.setStickSort(null);
				}
			}
			VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(textNews.getNewsId());
			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			 String outerNewsId = "";
			if (textNews.getFlag().equals("3")) {
				videoRecord.setFlag("3");
				logger.debug(textNews.getOuterNewsId());

				/* ==========上外网模块 start========== */
				// 校编后发布新闻上外网
				if (textNews.getIsPublic().equals("1")) {
					logger.debug("选择发布新闻上外网");
					 Long categoryId = Long.parseLong(textNews.getCategory());
					if (columnService.getColumnById(categoryId).getIsOutsite()
							.equals("1")) {
						logger.debug("有权发布新闻上外网");
						if (!StringUtils.isBlank(textNews.getOuterNewsId())) { // 更新
							// 归档后修改
							cmsNewsService.updateNews(textNews);
						} else { // 新增
							outerNewsId = cmsNewsService.addNews(textNews);
							textNews.setOuterNewsId(outerNewsId);
							// isPublicScore(textNews);// 上外网加分
						}
					}
				} else {
					if (!StringUtils.isBlank(textNews.getOuterNewsId())) {
						cmsNewsService.delete(textNews);
					}
				}
				/* ==========上外网模块 end========== */

				// 若选择‘是’，且点击了【校编】按钮，弹出提示‘请确认不再送校编？’；在提示框中选择了‘确定’则直接通过校编，IsCheckAgain=0；
				if (textNews.getIsCheckAgain().equals("1")) {
					textNews.setIsCheckAgain("0");
				}
				textNewsService.update(textNews);
				this.writeMessage(response, "校编成功！");
			}

			if (textNews.getFlag().equals("4")) {
				videoRecord.setFlag("4");
				textNews.setFlag("3");
				/* ==========上外网模块 start========== */
				if (textNews.getIsPublic().equals("1")) {
					logger.debug("选择发布新闻上外网");
					 Long categoryId = Long.parseLong(textNews.getCategory());
					if (columnService.getColumnById(categoryId).getIsOutsite()
							.equals("1")) {
						logger.debug("有权发布新闻上外网");
						if (!StringUtils.isBlank(textNews.getOuterNewsId())) {
							// 归档后更新修改
							cmsNewsService.updateNews(textNews);
						} else { // 新增
							outerNewsId = cmsNewsService.addNews(textNews);
							textNews.setOuterNewsId(outerNewsId);
							isPublicScore(textNews);// 归档上外网加分
						}
					}
				} else {
					if (!StringUtils.isBlank(textNews.getOuterNewsId())) {
						cmsNewsService.delete(textNews);
					}
				}
				/* ==========上外网模块 end========== */

				textNewsService.update(textNews);
				this.writeMessage(response, "修改成功！");
			}

			if (textNews.getFlag().equals("7")) {
				 String rejectReason = request.getParameter("rejectReason");
				videoRecord.setRejectReason(rejectReason);
				videoRecord.setFlag("14");
				textNewsService.update(textNews);
				this.writeMessage(response, "退稿成功！");
			} else {
				videoRecord.setRejectReason("");
			}
			// 新增状态号19 表示送校编
			if (textNews.getFlag().equals("19")
					&& textNews.getIsCheckAgain().equals("1")) {
				videoRecord.setFlag("19");
				textNews.setFlag("2");
				textNewsService.update(textNews);
				this.writeMessage(response, "送校编成功！");
			}

			videoRecordService.update(videoRecord);
		} else {
			this.writeMessage(response, "校编失败！");
		}
	}


	/*
	 * 网站信息分拣编辑的跳转页面
	 */
	@RequestMapping(params = "face=editSortSiteNewsView")
	public String editSort( Model model,  @RequestParam("newsId") String newsId) {

		 String deptId = SystemContext.getUserDetail().getDeptID();
		 List<VideoRecord> newsRecord = videoRecordService .getVideoRecordList(newsId);
		model.addAttribute("newsRecord", newsRecord);
		model.addAttribute("userDeptID", textNewsService.getTextNewsDetail(newsId).getEntryDept());
		model.addAttribute("userName", textNewsService .getTextNewsDetail(newsId).getEntryUser());
		/*
		 * String userName = textNewsService.getTextNewsDetail(newsId)
		 * .getEntryUser(); model.addAttribute("userName", userName);
		 */
		model.addAttribute("textCategorys", departmentService.getTextCategorys(deptId));
		model.addAttribute("approvers", userDetailService.getApprovers(SystemContext.getUserNO()));
		logger.debug("approvers" + userDetailService.getApprovers(SystemContext.getUserNO()));
		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		List<String> photoList = new ArrayList<String>();
		if(textNews != null){
			String photoUrl = textNews.getPhotoUrl();
			if(StringUtils.isNotBlank(photoUrl)){
				String urls[] = photoUrl.split(",");
				for (String s : urls) {
					photoList.add(s);
				}
			}
		}
		model.addAttribute("photoList", photoList);
		model.addAttribute("textNewsEditInfo", textNews);
		return "console/textnews/siteInfo/editSortSiteInfo";
	}

	/**
	 * 网站分拣信息查询默认显示功能
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getSortSiteInfoView")
	public String querySortSiteInfoView( Model model) {
		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		 int pageIndex = 0;
		 List<TextNews> textNewsList = textNewsService.getSiteNewsList(null,
				null, new String[] { "1" }, "", pageIndex,
				SystemContext.getDefaultPageSize());
		/*
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 */
		model.addAttribute("paginations", textNewsList);

		return "console/textnews/siteInfo/listSortSiteInfo";
	}

	/**
	 * 网站信息分拣分页查询显示功能
	 * 
	 * @author
	 * @param pageIndex
	 * @param pageSize
	 * @param newsTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=querySortSiteNewsView")
	public String querySortSiteNews(
			 @RequestParam("newsTitle") String newsTitle,
			 @RequestParam("entryUser") String entryUser,
			 @RequestParam("entryDateS") String entryDateS,
			 @RequestParam("entryDateE") String entryDateE,
			 @RequestParam("pageIndex") int pageIndex,
			 HttpServletResponse response,  Model model)
			throws UnsupportedEncodingException {

		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		entryUser = URLDecoder.decode(entryUser, "utf-8");
		 Date start = this.getStartDate(entryDateS);
		 Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		 List<TextNews> getTraffictList = textNewsService.getSiteNewsList(
				newsTitle, entryUser, start, end, new String[] { "1" }, "",
				pageIndex, SystemContext.getDefaultPageSize());

		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", getTraffictList);

		return "console/textnews/siteInfo/listSortSiteInfo";
	}

	/**
	 * 默认查询回收站信息 H2602965 wl 20140616 flag.equals("9")
	 * 
	 * @author H2602965
	 * @param model
	 * @param flag
	 * @param newsTag
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "face=getRecycleSiteInfoView")
	public String queryRecycleSiteInfo( Model model) {
		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		 int pageIndex = 0;
		 List<TextNews> textNewsList = textNewsService.getSiteNewsRecycleList(
				"", "", null, null, pageIndex,
				SystemContext.getDefaultPageSize());
		/*
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 */
		model.addAttribute("paginations", textNewsList);
		return "console/textnews/siteInfo/recycleSiteInfo";
	}

	/**
	 * 条件查询回收站信息 H2602965 wl 20140616
	 * 
	 * @author H2602965
	 * @param newsTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=queryRecycleSiteInfoView")
	public String queryRecycleSiteInfo(
			 @RequestParam("newsTitle") String newsTitle,
			 @RequestParam("entryUser") String entryUser,
			 @RequestParam("entryDateS") String entryDateS,
			 @RequestParam("entryDateE") String entryDateE,
			 @RequestParam("pageIndex") int pageIndex,
			 HttpServletResponse response,  Model model)
			throws UnsupportedEncodingException {

		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		entryUser = URLDecoder.decode(entryUser, "utf-8");
		 Date start = this.getStartDate(entryDateS);
		 Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		 List<TextNews> textNewsList = textNewsService.getSiteNewsRecycleList(
				newsTitle, entryUser, start, end, pageIndex,
				SystemContext.getDefaultPageSize());

		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", textNewsList);

		return "console/textnews/siteInfo/recycleSiteInfo";
	}

	/**
	 * 回收站复原功能 相当于逻辑删除 将is_del字段改为 1 状态为 已投稿
	 * 
	 * @author H2602965 wl 20140616
	 * @param model
	 * @param newsId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteRecycleSiteInfo")
	public void deleteRecycleSiteInfo( Model model,
			 @RequestParam("newsId") String newsId,  HttpServletResponse response) {

		 String msg = "";
		try {
			textNewsService.resumeTextNewsFlag(newsId);
			
			List<TextGovInfo> textGovInfoL = textGovInfoService.getTextGovInfoListByNewsId(newsId);
			if(textGovInfoL.size()>0){
				textGovInfoService.reUpdate(textGovInfoL.get(0));
			}

			 VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(newsId);
			videoRecord.setFlag("16");
			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);

			msg = "复原成功！";
		} catch (Exception e) {
			msg = "复原失败！";
		}

		this.writeMessage(response, msg);
	}

	/**
	 * 默认查询归档信息 H2602965 wl 20140616 flag.equals("9")
	 * 
	 * @author H2602965
	 * @param model
	 * @param flag
	 * @param newsTag
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "face=getArchiveSiteInfoView")
	public String queryArchiveSiteInfo( Model model) {
		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		 int pageIndex = 0;
		 List<TextNews> textNewsList = textNewsService.getSiteNewsList(null,
				null, new String[] { "3", "8", "9" }, "", pageIndex,
				SystemContext.getDefaultPageSize());
		/*
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 */
		model.addAttribute("paginations", textNewsList);
		return "console/textnews/siteInfo/listArchiveSiteInfo";
	}

	/**
	 * 条件查询归档信息 H2602965 wl 20140616 ,查询失效bug修复 20140619 H2602992
	 * 
	 * @author H2602965
	 * @param newsTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=queryArchiveSiteView")
	public String queryArchiveSiteInfo(
			 @RequestParam("newsTitle") String newsTitle,
			 @RequestParam("entryUser") String entryUser,
			 @RequestParam("entryDateS") String entryDateS,
			 @RequestParam("entryDateE") String entryDateE,
			 @RequestParam("newsCategory") String newsCategory,
			 @RequestParam("pageIndex") int pageIndex,
			 HttpServletResponse response,  Model model)
			throws UnsupportedEncodingException {

		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		entryUser = URLDecoder.decode(entryUser, "utf-8");
		newsCategory = URLDecoder.decode(newsCategory, "utf-8");
		System.out.println(newsCategory);
		 Date start = this.getStartDate(entryDateS);
		 Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		String categorys[] = null;
		String falgs[] = new String[]{"3","8","9"};
		if(StringUtils.isNotBlank(newsCategory)){
			categorys = new String[]{newsCategory};
			falgs = new String[]{"3"};
		}
		 List<TextNews> textNewsList = textNewsService.getSiteNewsList(
				newsTitle, entryUser, start, end,
				falgs, categorys, "", pageIndex,
				SystemContext.getDefaultPageSize());

		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("newsCategory", newsCategory);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", textNewsList);
		return "console/textnews/siteInfo/listArchiveSiteInfo";
	}

	// 20140617 H2602992
	private String getImagePath( ServletContext servletContext) {
		return servletContext.getRealPath("/") + "resource/textnews/"
				+ FileUploader.getTimeFolder() + "/";
	}

	/**
	 * 批量分拣
	 * 
	 * @author H2602965
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=sortSiteAll")
	public void sortSiteAll( Model model,  HttpServletRequest request,
			 HttpServletResponse response) {
		 String msg = "";
		 String newsIds = request.getParameter("newsIds");
		logger.debug(newsIds);
		if (null != newsIds) {
			msg = "分拣成功！";
			textNewsService.sortSiteAll(newsIds);
			 String[] ids = newsIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				logger.debug(ids[i]);
				 VideoRecord videoRecord = new VideoRecord();
				videoRecord.setVrId(UUID.randomUUID().toString()
						.replace("-", ""));
				videoRecord.setVideoId(ids[i]);
				videoRecord.setFlag("2");
				videoRecord.setUpdateUser(SystemContext.getUserName());
				videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
				videoRecordService.update(videoRecord);
			}
			this.writeMessage(response, msg);
		}

	}

	/**
	 * 批量删除
	 * 
	 * @author H2603282
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=deleteSiteAll")
	public void deleteSiteAll( Model model,  HttpServletRequest request,
			 HttpServletResponse response) {
		 String msg = "";
		 String newsIds = request.getParameter("newsIds");
		logger.debug(newsIds);
		if (newsIds != null) {
			textNewsService.deleteSiteAll(newsIds);
			msg = "彻底删除成功！";
		}
		/**
		 * String[] ids = newsIds.split(","); for(int i=0 ; i < ids.length;
		 * i++){ logger.debug(ids[i]); VideoRecord videoRecord =new
		 * VideoRecord();
		 * videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
		 * videoRecord.setVideoId(ids[i]); videoRecord.setFlag("2");
		 * videoRecord.setUpdateUser(SystemContext.getUserName());
		 * videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
		 * videoRecordService.update(videoRecord); }
		 */
		this.writeMessage(response, msg);
	}

	/**
	 * 批量归档
	 * 
	 * @author H2602965
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=archiveSiteAll")
	public void archiveSiteAll( Model model,  HttpServletRequest request,
			 HttpServletResponse response) {
		 String msg = "";
		 String newsIds = request.getParameter("newsIds");
		logger.debug(newsIds);
		if (newsIds != null) {
			textNewsService.archiveSiteAll(newsIds);
			msg = "归档成功！";
			 String[] ids = newsIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				logger.debug(ids[i]);
				 VideoRecord videoRecord = new VideoRecord();
				videoRecord.setVrId(UUID.randomUUID().toString()
						.replace("-", ""));
				videoRecord.setVideoId(ids[i]);
				videoRecord.setFlag("18");
				videoRecord.setUpdateUser(SystemContext.getUserName());
				videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
				videoRecordService.update(videoRecord);
			}
			this.writeMessage(response, msg);
		}
	}

	public void isPublicScore( TextNews textNews) {
		// 上外网时加分
		 DepartmentScore deptScore = new DepartmentScore();
		deptScore.setCreateDate(DateUtil.getCurrentDateStr());
		deptScore.setDeptName(textNews.getDeptName());
		deptScore.setDeptId(textNews.getEntryDept());
		deptScore.setNewsId(textNews.getNewsId());
		deptScore.setScore(checkStandardService.getCheckStandardDetail(
				"isPublic").getScore());
		deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
		deptScore.setScoreType("SiteNews");
		deptScore.setScoreInfo("isPublic");// 表示上外网
		deptScoreDao.save(deptScore);
		logger.debug("上网得分" + deptScore.getScore());
	}
	
	
	@RequestMapping(params = "action=updateNewsSort")
	public void updateNewsSort( Model model,  HttpServletRequest request,
			 HttpServletResponse response) {
		String newsId = request.getParameter("newsId");
		String newsSort = request.getParameter("newsSort");
		String msg = "成功";
		 
		if (StringUtils.isNotBlank(newsId) && StringUtils.isNotBlank(newsSort)) {
			try {
				textNewsService.updateNewsSort(newsSort, newsId);
			} catch (Exception e) {
				e.printStackTrace();
				msg = "修改失败";
			}
			
		} else {
			msg = "参数错误";
		}
		this.writeMessage(response, msg);
	}
}
