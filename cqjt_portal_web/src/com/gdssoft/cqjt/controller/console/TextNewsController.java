package com.gdssoft.cqjt.controller.console;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.DepartmentScore;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextGovReport;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.videoNews.VideoRecord;
import com.gdssoft.cqjt.service.CheckStandardService;
import com.gdssoft.cqjt.service.ColumnService;
import com.gdssoft.cqjt.service.DepartmentScoreService;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.TextDeptOuterCategoryService;
import com.gdssoft.cqjt.service.TextGovInfoService;
import com.gdssoft.cqjt.service.TextNewsService;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.cms.NewsService;
import com.gdssoft.cqjt.service.cms.ProgramService;
import com.gdssoft.cqjt.service.videoNews.VideoRecordService;
import com.gdssoft.cqjt.util.PortalUtils;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@Controller
@RequestMapping("/console/textNews.xhtml")
public class TextNewsController extends BaseController {

	@Autowired
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsService;

	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userDetailService;

	@Resource(name = "deptScoreServiceImpl")
	private DepartmentScoreService deptScoreService;

	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;

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
	@Resource(name = "textDeptOuterCategoryService")
	private TextDeptOuterCategoryService textDeptOuterCategoryService;

	@Autowired
	@Resource(name = "deptScoreDao")
	private DepartmentScoreDao deptScoreDao;

	@Autowired
	@Resource(name = "checkStandardServiceImpl")
	private CheckStandardService checkStandardService;

	@Autowired
	@Resource(name = "columnService")
	private ColumnService columnService;
	
	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;

	@RequestMapping(params = "face=listView")
	public String query(HttpServletRequest request, Model model, String tags) {
		
		// 取前一个月的同一天  
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date date = cal.getTime();  
		
		Date start = this.getStartDate(DateUtil.dateFormat(date));
		Date end = this.getEndDate(DateUtil.dateFormat(new Date()));
		
		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		
		List<TextNews> textNewsList = new ArrayList<TextNews>();
		int pageIndex = 0;
		// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
		if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")
				|| roleName.equals("GovEditor")) {
			textNewsList = textNewsService.getTextNewsList(start, end,
					pageIndex, SystemContext.getDefaultPageSize(), tags);
		} else {// 查询当前用户报送信息
			textNewsList = textNewsService.getTextNewsList("",
					SystemContext.getUserName(), start, end, pageIndex,tags,
					SystemContext.getDefaultPageSize());
		}
		// 获取全部采用类别
		List<CheckStandard> checkStandards =  checkStandardService.queryCheckStandard(null);
		// 计算采用类别
		for (int i = 0; i < textNewsList.size(); i++) {
			String codes = PortalUtils.computeAdoptType( textNewsList
					.get(i).getAdoptType(), checkStandards);
			textNewsList.get(i).setAdoptType(codes);
		}
		
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", textNewsList);
		model.addAttribute("tags", tags);
		return "console/textnews/news/list";
	}


	/**
	 * 查询报送信息
	 * 
	 * @author H2602975 zhangpeng 20140523 修改添加分页H2602965 20140606
	 * @param newsTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=searchView")
	public String queryByUserDate(@RequestParam("newsTitle") String newsTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("tags") String tags,
			@RequestParam("flag") String flag,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		entryUser = URLDecoder.decode(entryUser, "utf-8");
		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		List<TextNews> textNewsList = new ArrayList<TextNews>();
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		logger.debug("newsTitle==========" + newsTitle);
		//通过flag值判断查询状态
		System.out.println(flag);
		String[] flags = new String[3];
		int[] govUseFlag = new int[4];
		switch(flag){
			case "1": break;//全部
			case "2": flags[0]="0";break;//草稿
			case "3": flags[0]="1";govUseFlag[0]=0; break;//未采编or投稿
//			case "4": flags[0]="1";govUseFlag[0]=2;govUseFlag[1]=3; break;//采编未成刊
//			case "5": flags[0]="1";govUseFlag[0]=1;govUseFlag[1]=2222; break;//采编已成刊
			case "5": flags[0]="1";govUseFlag[0]=2;govUseFlag[1]=3;govUseFlag[2]=1;govUseFlag[3]=2222; break;//采编
			case "6": flags[0]="8";flags[1]="3";flags[2]="9";break;//归档
			case "7": flags[0]="2";break;//分拣
			case "9": flags[0]="1";break;//投稿
		}
		// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
		if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")
				|| roleName.equals("GovEditor")) {
			if("1".equals(flag)){
				textNewsList = textNewsService.getTextNewsList(newsTitle,
						entryUser, start, end, pageIndex,tags,new String[]{},new int[]{},
						SystemContext.getDefaultPageSize());
			}else{
				textNewsList = textNewsService.getTextNewsList(newsTitle,
						entryUser, start, end, pageIndex,tags,flags,govUseFlag,
						SystemContext.getDefaultPageSize());
			}
		} else {// 查询当前用户报送信息
			if("1".equals(flag)){
				
				textNewsList = textNewsService.getTextNewsList(newsTitle,
						SystemContext.getUserName(), start, end, pageIndex,tags,new String[]{},new int[]{},
						SystemContext.getDefaultPageSize());
			}else{
				textNewsList = textNewsService.getTextNewsList(newsTitle,
						SystemContext.getUserName(), start, end, pageIndex,tags,flags,govUseFlag,
						SystemContext.getDefaultPageSize());
			}
			
		}
		
		// 获取全部采用类别
		List<CheckStandard> checkStandards =  checkStandardService.queryCheckStandard(null);
		// 计算采用类别
		for (int i = 0; i < textNewsList.size(); i++) {
			String codes = PortalUtils.computeAdoptType( textNewsList
					.get(i).getAdoptType(), checkStandards);
			textNewsList.get(i).setAdoptType(codes);
		}
		
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("paginations", textNewsList);
		model.addAttribute("tags", tags);
		model.addAttribute("flag", flag);
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);
		if("StatuteNews".equals(tags)){
			return "console/textnews/news/StatuteNewsList";
		}
		return "console/textnews/news/list";
	}
	
	/**
	 * 导出 excel
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "face=exportExcel") 
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		
		String tags = request.getParameter("tags");
		String flag = request.getParameter("flag");
		String entryUser = request.getParameter("entryUser");
		String newsTitle = request.getParameter("newsTitle");
		String entryDateS = request.getParameter("entryDateS");
		String entryDateE = request.getParameter("entryDateE");
		
		try {
			newsTitle = URLDecoder.decode(newsTitle, "utf-8");
			entryUser = URLDecoder.decode(entryUser, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		List<TextNews> textNewsList = new ArrayList<TextNews>();
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		//通过flag值判断查询状态
		String[] flags = new String[3];
		int[] govUseFlag = new int[4];
		switch(flag){
			case "1": break;//全部
			case "2": flags[0]="0";break;//草稿
			case "3": flags[0]="1";govUseFlag[0]=0; break;//未采编or投稿
//			case "4": flags[0]="1";govUseFlag[0]=2;govUseFlag[1]=3; break;//采编未成刊
//			case "5": flags[0]="1";govUseFlag[0]=1;govUseFlag[1]=2222; break;//采编已成刊
			case "5": flags[0]="1";govUseFlag[0]=2;govUseFlag[1]=3;govUseFlag[2]=1;govUseFlag[3]=2222; break;//采编
			case "6": flags[0]="8";flags[1]="3";flags[2]="9";break;//归档
			case "7": flags[0]="2";break;//分拣
			case "9": flags[0]="1";break;//投稿
		}
		// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
		if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")
				|| roleName.equals("GovEditor")) {
			if("1".equals(flag)){
				textNewsList = textNewsService.getTextNewsListForExport(newsTitle,
						entryUser, start, end,tags,new String[]{},new int[]{});
			}else{
				textNewsList = textNewsService.getTextNewsListForExport(newsTitle,
						entryUser, start, end,tags,flags, govUseFlag);
			}
		} else {// 查询当前用户报送信息
			if("1".equals(flag)){
				
				textNewsList = textNewsService.getTextNewsListForExport(newsTitle,
						SystemContext.getUserName(), start, end, tags,new String[]{},new int[]{});
			}else{
				textNewsList = textNewsService.getTextNewsListForExport(newsTitle,
						SystemContext.getUserName(), start, end, tags,flags,govUseFlag);
			}
		}
		// 获取全部采用类别
		List<CheckStandard> checkStandards =  checkStandardService.queryCheckStandard(null);
		textNewsService.exportTextNewsList(textNewsList, checkStandards, entryDateS, entryDateE, response, tags);
	}
	
	/**
	 * 跳转到新增页面并加载数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=addView")
	public String add(Model model) {
		String userNo = SystemContext.getUserNO();
		String deptId = SystemContext.getUserDetail().getDeptID();

		model.addAttribute("userNo", userNo);
		model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("userDeptID", deptId);
		model.addAttribute("userDeptName", SystemContext.getUserDetail()
				.getDeptName());

		model.addAttribute("approvers", userDetailService.getApprovers(userNo));

		model.addAttribute("textCategorys",
				departmentService.getTextCategorys(deptId));
		List<Department> list = new ArrayList<Department>();
		list = departmentService.getCategoryName(deptId);
		/**
		 * 添加是否滚定显示权限管控，如果CategoryName名字改变，这里会出问题，表设计。 管理员，网站校编员、政务校编员，可以滚动显示
		 */
		if (list.get(0).getCategoryName().equals("市交委机关")) {
			model.addAttribute("deptCategory", true);
		} else if (SystemContext.getRoleName().equals("Admin")
				|| SystemContext.getRoleName().equals("WebsiteEditor")
				|| SystemContext.getRoleName().equals("GovEditor")) {
			model.addAttribute("deptCategory", true);
		}
		return "console/textnews/news/add";
	}


	

	
	
	/**
	 * 投稿页面保存
	 * 
	 * @param textNews
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=save", method = RequestMethod.POST)
	public void save(@ModelAttribute("textNews") TextNews textNews,
			MultipartHttpServletRequest request, HttpServletResponse response) {

		if (null == textNews.getIsPhotosShow()){//是否滚动显示图片
			textNews.setIsPhotosShow("0");
		}
		if (null == textNews.getIsPublic()) {//是否上外网
			textNews.setIsPublic("0");
		} else if (textNews.getIsPublic().equals("0")) {//外网栏目
			textNews.setOuterCategory("");
		}
		
		
		textNews.setNewsId(UUID.randomUUID().toString().replace("-", ""));
		textNews.setEntryDate(DateUtil.getCurrentDate());
		textNews.setIsDel("0");
		textNews.setGovUseFlag(0);
		textNews.setCreateBy(textNews.getEntryUser());
		textNews.setCreateDate(DateUtil.getCurrentDateStr());
		String category = textNews.getCategory();//网站栏目
		String tagesStr = textNews.getNewsTagsStr();
		System.out.println("----------------->category:"+category);
		System.out.println("----------------->tagesStr:"+tagesStr);
		try {
			System.out.println("---->imgUrl"+textNews.getPhotoUrl());
			if(category != null && !tagesStr.endsWith("SiteNews")){
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
				ObjectOutputStream out= new ObjectOutputStream(byteOut);  
				out.writeObject(textNews);//写对象，序列化  
				ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
				ObjectInputStream in = new ObjectInputStream(byteIn);
				TextNews textNews1=(TextNews)in.readObject(); //读对象，反序列化 
					
				textNews1.setNewsId(UUID.randomUUID().toString().replace("-", ""));
				textNews1.setNewsTagsStr("SiteNews");
				textNews.setCategory(null);
				textNews.setNewsTagsStr("GovNews");
				System.out.println("----------------->newId1:"+textNews1.getNewsId());
				System.out.println("----------------->newId:"+textNews.getNewsId());
				System.out.println("----------------->getNewsTagsStr1:"+textNews1.getNewsTagsStr());
				System.out.println("----------------->getNewsTagsStr:"+textNews.getNewsTagsStr());
				System.out.println("----------------->getCategory1:"+textNews1.getCategory());
				System.out.println("----------------->getCategory:"+textNews.getCategory());
				//同步外网
				syncProtal(textNews1);
				textNewsService.save(textNews1);
				//视频积分
				videoRecord(textNews1);
				//部门投稿积分
				addScore(textNews1);
			} 
			//同步外网
			syncProtal(textNews);
			textNewsService.save(textNews);
			//视频积分
			videoRecord(textNews);
			//部门投稿积分
			addScore(textNews);
			this.writeMessage(response, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName(), e);
			this.writeMessage(response, "ERROR，保存失败！");
		}
	}

	
	/**
	 * 投稿页面保存
	 * 
	 * @param textNews
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=uploadFile")
	public void uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) {
		int code = 0;
		String msg = "成功", imgUrl = null;
		try {
			MultipartFile newsImage = request.getFile("newsImage");
			imgUrl = FileUploader.uploadFile(request, newsImage,  getImagePath(request.getSession().getServletContext()));
			System.out.println("imgUrl");
		} catch (Exception e) {
			code = 1;
			msg = "上传失败";
		}
		try {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(imgUrl);
//			response.getWriter().write("{'code':"+code+",'msg':"+msg+",'imgUrl':"+imgUrl+"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 同步外网
	 */
	private void syncProtal(TextNews textNews){
		// 当投稿即flag==1时，判断外网栏目是否可以直接上外网，如果可以则将flag=9并且发布新闻上外网同时要加分（上外网）
		if (textNews.getFlag().equals("1") && textNews.getCategory() != null) {// 投稿
			if (textNews.getIsPublic().equals("1")) {// 判断前台是否上外网传值
				// 判断是否有权利上外网 IS_OUTSITE ==1? ，category网站栏目
				Long categoryId = Long.parseLong(textNews.getCategory());
				if (columnService.getColumnById(categoryId).getIsOutsite().equals("1")) { // 判断上外网权限
					textNews.setFlag("9");// 直接上外网
					logger.debug("发布新闻上外网");
					
					// 内外网附件掉包问题的URL转换 20170320
					String newsContent = textNews.getNewsContent().replace("/resource/", "http://api.cqjt.gov.cn/resource/");
					textNews.setNewsContent(newsContent);
					String outerNewsId = cmsNewsService.addNews(textNews);
					// 存入内网时将URL转换回来 20170425
					newsContent = textNews.getNewsContent().replace("http://api.cqjt.gov.cn/resource/", "/resource/");
					textNews.setNewsContent("");
					textNews.setNewsContent(newsContent);
					
					textNews.setOuterNewsId(outerNewsId);
					// 上外网时加分
					isPublicScore(textNews);
				} else {
					//2018年7月27日14:32:14  更改：如果不能上外网，将标记保留，方便编辑人员手动上外网
					textNews.setIsPublic("1"); // 如果不能上网，将IsPublic字段修改为0
				}
			}
		}
	}
	
	private void videoRecord(TextNews textNews){
		VideoRecord videoRecord = new VideoRecord();
		videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
		videoRecord.setVideoId(textNews.getNewsId());
		videoRecord.setFlag(textNews.getFlag());
		videoRecord.setUpdateUser(SystemContext.getUserName());
		videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
		videoRecordService.update(videoRecord);
	}
	
	/**
	 * 部门投稿计分
	 * @param textNews
	 */
	private void addScore(TextNews textNews){
		String str = textNews.getNewsTagsStr();
		if (str.indexOf("GovNews") >= 0 || str.indexOf("SiteNews") >= 0) {
			deptScoreService.sendInfoScore(textNews);
		}
	}
	
	/**
	 * 报送查询列表编辑功能跳转到编辑页面
	 * 
	 * @author H2602975 zhangpeng 20140526
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=editView")
	public String edit(Model model, @RequestParam("newsId") String newsId) {

		logger.debug("得到newsId的值" + newsId);
		String userNo = SystemContext.getUserNO();
		String deptId = SystemContext.getUserDetail().getDeptID();
		String roleName = SystemContext.getRoleName();
		List<VideoRecord> newsRecord = videoRecordService.getVideoRecordList(newsId);
		model.addAttribute("newsRecord", newsRecord);

		model.addAttribute("userNo", userNo);
		// model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("userDeptID", deptId);
		model.addAttribute("userDeptName", SystemContext.getUserDetail().getDeptName());
		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		model.addAttribute("textNewsEditInfo", textNews);
		model.addAttribute("textCategorys",departmentService.getTextCategorys(deptId));
		String userName = null;
		List<String> photoList = new ArrayList<String>();
		if(textNews != null){
			userName = textNews.getEntryUser();
			String photoUrl = textNews.getPhotoUrl();
			if(StringUtils.isNotBlank(photoUrl)){
				String urls[] = photoUrl.split(",");
				for (String s : urls) {
					photoList.add(s);
				}
			}
		}
		model.addAttribute("photoList", photoList);
		model.addAttribute("userName", userName);
		model.addAttribute("roleName", roleName);
		logger.debug("yonghu:" + userName);
		model.addAttribute("approvers", userDetailService.getApp(userName));
		logger.debug("qianfaren" + userDetailService.getApp(userName));

		return "console/textnews/news/editTextNews";
	}
	@RequestMapping(params = "face=editStatuteView")
	public String editStatute(Model model, @RequestParam("newsId") String newsId) {

		logger.debug("得到newsId的值" + newsId);
		String userNo = SystemContext.getUserNO();
		String deptId = SystemContext.getUserDetail().getDeptID();
		String roleName = SystemContext.getRoleName();
		List<VideoRecord> newsRecord = videoRecordService.getVideoRecordList(newsId);
		model.addAttribute("newsRecord", newsRecord);

		model.addAttribute("userNo", userNo);
		// model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("userDeptID", deptId);
		model.addAttribute("userDeptName", SystemContext.getUserDetail().getDeptName());
		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		model.addAttribute("textNewsEditInfo", textNews);
		model.addAttribute("textCategorys",departmentService.getTextCategorys(deptId));
		String userName = null;
		List<String> photoList = new ArrayList<String>();
		if(textNews != null){
			userName = textNews.getEntryUser();
			String photoUrl = textNews.getPhotoUrl();
			if(StringUtils.isNotBlank(photoUrl)){
				String urls[] = photoUrl.split(",");
				for (String s : urls) {
					photoList.add(s);
				}
			}
		}
		model.addAttribute("photoList", photoList);
		model.addAttribute("userName", userName);
		model.addAttribute("roleName", roleName);
		logger.debug("yonghu:" + userName);
		model.addAttribute("approvers", userDetailService.getApp(userName));
		logger.debug("qianfaren" + userDetailService.getApp(userName));

		return "console/textnews/news/editStatuteNews";
	}

	/**
	 * 报送查询列表编辑功能更新数据方法
	 * 
	 * @author H2602975 zhangpeng 20140527
	 * @param TextNews
	 */
	@RequestMapping(params = "action=updateNews")
	public void updateNews(@ModelAttribute("textNews") TextNews textNews,
			@RequestParam("imageUrl") String imageUrl,
			MultipartHttpServletRequest request, HttpServletResponse response) {

		if (null == textNews.getIsPhotosShow())
			textNews.setIsPhotosShow("0");

		if (null == textNews.getIsPublic())
			textNews.setIsPublic("0");
		if (textNews.getPhotoUrl() == null || textNews.getPhotoUrl().equals("")) {
			textNews.setPhotoUrl(imageUrl);
		} else {
//			MultipartFile newsImage = request.getFile("newsImage");
//			textNews.setPhotoUrl(FileUploader.uploadFile(request, newsImage,
//					getImagePath(request.getSession().getServletContext())));
		}

		DateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date entryDate = null;
		try {
			entryDate = fds.parse(textNews.getCreateDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		textNews.setEntryDate(entryDate);
		if (!StringUtils.isBlank(textNews.getNewsId())) {
			logger.debug("flag=" + textNews.getFlag());
			textNews.setIsDel("0");
			// 添加排序标识
			if (null != textNews.getStickState()) {
				if (textNews.getStickState().equals("1")) {
					textNews.setStickSort(DateUtil.getCurrentDate());
				}
				if (textNews.getStickState().equals("0")) {
					textNews.setStickSort(null);
				}
			}

			// 是否上外网 更新
			if (textNews.getIsPublic().equals("1")) {
				logger.debug("发布新闻上外网");
				// 判断是否有权利上外网 IS_OUTSITE ==1?
				Long categoryId = Long.parseLong(textNews.getCategory());
				if (columnService.getColumnById(categoryId).getIsOutsite()
						.equals("1")) {
					logger.debug("有权发布新闻上外网");
					// textNews.setFlag("9"); //直接上外网
					if (!StringUtils.isBlank(textNews.getOuterNewsId())) { // 更新
						cmsNewsService.updateNews(textNews);// 归档后修改
						logger.debug("发布新闻上外网");
					} else {
						// 如果outerNewsId为空
						String outerNewsId = cmsNewsService.addNews(textNews);
						textNews.setOuterNewsId(outerNewsId);
						// 网站信息编辑，分拣 上外网时加分
						isPublicScore(textNews);
					}
				}
			} else {
				if (!StringUtils.isBlank(textNews.getOuterNewsId())) {
					cmsNewsService.delete(textNews);
				}
			}

			VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(textNews.getNewsId());
			if (textNews.getFlag().equals("1")) {
				videoRecord.setFlag("4");// 信息处理记录，flag为4表示修改
			}
			if (textNews.getFlag().equals("0")) {
				videoRecord.setFlag("0");// 信息处理记录，flag为0表示存草稿
			}
			if (textNews.getFlag().equals("2")) {
				videoRecord.setFlag("2");// 信息处理记录，flag为2表示分拣
			}
			if (textNews.getFlag().equals("8")) {
				videoRecord.setFlag("18");// 信息处理记录，flag为18表示归档
			}
			if (textNews.getFlag().equals("7")) {
				String rejectReason = request.getParameter("rejectReason");
				videoRecord.setRejectReason(rejectReason);
				videoRecord.setFlag("13");
			} else {
				videoRecord.setRejectReason("");
			}

			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);
			if (textNews.getFlag().equals("7")) {
				this.writeMessage(response, "退稿成功！");
			} else {
				this.writeMessage(response, "保存成功！");
			}
			if (textNews.getFlag().equals("1")) {
				if (textNews.getFg().equals("0")) {

				} else {
					textNews.setFlag(textNews.getFg());
				}
			}
			textNewsService.update(textNews);
			
			// 与 政务信息采编 同步内容  GuoY
			if ("GovNews".equals(textNews.getNewsTagsStr())) {
				TextGovInfo textGovInfo = new TextGovInfo();
				textGovInfo.setNewsId(textNews.getNewsId());
				textGovInfo.setGiContent(textNews.getNewsContent());
				textGovInfoService.syncGovInfoContent(textGovInfo);
			}
			
		} else {
			this.writeMessage(response, "保存失败！");
		}
	}
	


	/**
	 * 交通信息查询
	 * 
	 * @author H2602965 2014.06.06
	 * @param models
	 * @return
	 */
	@RequestMapping(params = "face=getReportListView")
	public String queryTrafficReport(Model model,
			@RequestParam("newsTagsStr") String newsTagsStr) {
		/*
		 * Date start = this.getStartDate(""); Date end = this.getEndDate("");
		 */
		int pageIndex = 0;
		List<TextNews> getTraffictList = textNewsService.getTextNewsListByTag(
				null, null, newsTagsStr, new String[] { "1", "2", "3" },
				pageIndex, SystemContext.getDefaultPageSize());
		/*
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 */
		model.addAttribute("paginations", getTraffictList);

		if (newsTagsStr.equals("TrafficPaper")) {
			return "console/textnews/traffic/listTrafficReport";
		} else
			return "console/textnews/traffic/listTrafficMagazine";

	}

	/**
	 * 分页查询交通报交通杂志信息
	 * 
	 * @author H2602965 2014.06.06
	 * @param pageIndex
	 * @param pageSize
	 * @param newsTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "action=queryTraffictView")
	public String queryByTrafficReport(
			@RequestParam("newsTitle") String newsTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("newsTagsStr") String newsTagsStr,
			HttpServletResponse response, Model model)
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
		logger.debug("newsTitle==========" + newsTitle);
		List<TextNews> getTraffictList = textNewsService.getTextNewsListByTag(
				newsTitle, entryUser, start, end, newsTagsStr, new String[] {
						"1", "2", "3" }, pageIndex,
				SystemContext.getDefaultPageSize());
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("paginations", getTraffictList);
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);

		if (newsTagsStr.equals("TrafficPaper")) {
			return "console/textnews/traffic/listTrafficReport";
		} else
			return "console/textnews/traffic/listTrafficMagazine";
	}

	/**
	 * 信息报送中信息删除方法
	 * 
	 * @author H2602965 20140606
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
			TextNews textNews = new TextNews();
			textNews.setNewsId(newsId);
			textNewsService.delete(newsId);

			if (!StringUtils.isBlank(outerNewsId)) {// 对应外网id不等于null，则删除时需同时将外网信息也删除
				textNews.setOuterNewsId(outerNewsId);
				cmsNewsService.delete(textNews);
			}

			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}

		this.writeMessage(response, msg);
	}

	private String getImagePath(ServletContext servletContext) {
		return servletContext.getRealPath("/") + "resource/textnews/"
				+ FileUploader.getTimeFolder() + "/";
	}

	/**
	 * 批量删除报送查询信息
	 * 
	 * @author gyf 20140707
	 * @param model
	 * @param request
	 */
	@RequestMapping(params = "action=deleteAll")
	public void deleteAll(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String msg = "";
		String newsIds = request.getParameter("newsIds");
		logger.debug(newsIds);
		if (newsIds != null) {
			textNewsService.deleteAll(newsIds);
			msg = "删除成功！";
			this.writeMessage(response, msg);
		} else {
			msg = "删除失败！";
			this.writeMessage(response, msg);
		}

	}

	/**
	 * 发布新闻上外网加分
	 * 
	 * @author H2602965
	 * @param textNews
	 */
	public void isPublicScore(TextNews textNews) {
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
	@RequestMapping(params = "face=location")
	public String lonationHerf(){
		return "console/textnews/news/location";
	}
}
