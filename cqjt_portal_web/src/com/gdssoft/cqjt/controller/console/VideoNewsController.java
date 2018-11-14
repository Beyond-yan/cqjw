package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
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
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;
import com.gdssoft.cqjt.pojo.videoNews.VideoRecord;
import com.gdssoft.cqjt.service.DepartmentScoreService;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.cms.NewsService;
import com.gdssoft.cqjt.service.videoNews.VideoNewsService;
import com.gdssoft.cqjt.service.videoNews.VideoRecordService;


@Controller
@RequestMapping("/console/videoNews.xhtml")
public class VideoNewsController extends BaseController {

	@Autowired
	@Resource(name = "videoNewsServiceImpl")
	private VideoNewsService videoNewsService;
	
	@Autowired
	@Resource(name = "videoRecordServiceImpl")
	private VideoRecordService videoRecordService;
	
	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	
	@Autowired
	@Resource(name = "deptScoreServiceImpl")
	private DepartmentScoreService deptScoreService;
	
	@Autowired
	@Resource(name = "cmsNewsService")
	private NewsService cmsNewsService;
	
	/**
	 * 视频查询默认列表显示,页面跳转
	 * @author H2602965
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listView")
	public String query(Model model) {
		
		Date start = this.getStartDate("");
		Date end = this.getEndDate("");
		
		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		List<VideoNews> videoNewsList = new ArrayList<VideoNews>();
		// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
		if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")|| roleName.equals("GovEditor")) {
			videoNewsList = videoNewsService.getVideoNewsList(start, end,0,SystemContext.getDefaultPageSize());
		} else {
			videoNewsList = videoNewsService.getVideoNewsList("",
					SystemContext.getUserName(), start, end, new String[] {},
					0, SystemContext.getDefaultPageSize());
		}
				
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", videoNewsList);
		
		return "console/videonews/listVideoNews";
	}
	/**
	 * 视频查询 条件查询功能
	 * @author H2602965	
	 * @param videoTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "face=searchFileList")
	public String searchFileList(Model model,
			@RequestParam("videoTitle") String videoTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response) throws UnsupportedEncodingException {

		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		List<VideoNews> videoNewsList = new ArrayList<VideoNews>();
		
		if (entryDateS.equals("") || entryDateE.equals("")) {
			{
				videoTitle = URLDecoder.decode(videoTitle, "utf-8");
				entryUser = URLDecoder.decode(entryUser, "utf-8");
				logger.debug("videoTitle==========" + videoTitle);
				// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
				if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")|| roleName.equals("GovEditor")) {
					videoNewsList = videoNewsService
							.getVideoNewsList(videoTitle, entryUser, null, null,
									new String[] {}, pageIndex,
									SystemContext.getDefaultPageSize());
				}else{
					videoNewsList = videoNewsService
							.getVideoNewsList(videoTitle, SystemContext.getUserName(), null, null,
									new String[] {}, pageIndex,
									SystemContext.getDefaultPageSize());
				}
				model.addAttribute("videoTitle", videoTitle);
				model.addAttribute("entryUser", entryUser);
				model.addAttribute("paginations", videoNewsList);

			}
		} else if (entryDateS.equals("") && entryDateE.equals("")) {
			videoTitle = URLDecoder.decode(videoTitle, "utf-8");
			entryUser = URLDecoder.decode(entryUser, "utf-8");
			logger.debug("videoTitle==========" + videoTitle);
			// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
			if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")|| roleName.equals("GovEditor")) {
				videoNewsList = videoNewsService
						.getVideoNewsList(videoTitle, entryUser, null, null,
								new String[] {}, pageIndex,
								SystemContext.getDefaultPageSize());
			}else{
				videoNewsList = videoNewsService
						.getVideoNewsList(videoTitle, SystemContext.getUserName(), null, null,
								new String[] {}, pageIndex,
								SystemContext.getDefaultPageSize());
			}
			model.addAttribute("videoTitle", videoTitle);
			model.addAttribute("entryUser", entryUser);
			model.addAttribute("paginations", videoNewsList);

		} else {
			videoTitle = URLDecoder.decode(videoTitle, "utf-8");
			entryUser = URLDecoder.decode(entryUser, "utf-8");
			Date start = this.getStartDate(entryDateS);
			Date end = this.getEndDate(entryDateE);
			logger.debug("videoTitle==========" + videoTitle);
			// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
			if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")|| roleName.equals("GovEditor")) {
				videoNewsList = videoNewsService
						.getVideoNewsList(videoTitle, entryUser, start, end,
								new String[] {}, pageIndex,
								SystemContext.getDefaultPageSize());
			}else{
				videoNewsList = videoNewsService
						.getVideoNewsList(videoTitle, SystemContext.getUserName(), start, end,
								new String[] {}, pageIndex,
								SystemContext.getDefaultPageSize());
			}
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			model.addAttribute("videoTitle", videoTitle);
			model.addAttribute("entryUser", entryUser);
			model.addAttribute("paginations", videoNewsList);
		}
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);

		/*
		 * videoTitle=URLDecoder.decode(videoTitle,"utf-8");
		 * entryUser=URLDecoder.decode(entryUser,"utf-8"); Date start =
		 * this.getStartDate(entryDateS); Date end =
		 * this.getEndDate(entryDateE); List<VideoNews> videoNewsList =
		 * videoNewsService.getVideoNewsList(videoTitle, entryUser, start,
		 * end,new String[]{}, pageIndex,
		 * SystemContext.getDefaultPageSize());//VideoNewsService
		 * ..getVideoNewsList(videoTitle,entryUser,start,end,"1");
		 * 
		 * model.addAttribute("videoTitle", videoTitle);
		 * model.addAttribute("entryUser", entryUser);
		 * model.addAttribute("start", start); model.addAttribute("end", end);
		 * model.addAttribute("paginations", videoNewsList);
		 */
		return "console/videonews/listVideoNews";
	}
	/**
	 * 逻辑删除资料
	 * @param videoId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteVideo")
	public void deleteVideo(@RequestParam("videoId") String videoId,
			@ModelAttribute("videoRecord") VideoRecord videoRecord,
			HttpServletResponse response){
		String msg = "";
		logger.debug("videoId="+videoId);
		try {
			VideoNews videoNews = new VideoNews();
			VideoNews videoNews1 = new VideoNews();
			videoNews1 =videoNewsService.getVideoByVideoId(videoId);
		    String a =videoNews1.getFlag();
		     logger.debug("flagdezhiwei===="+a);
			if(a.equals("2")){
				
				videoNews.setFlag("3");
				videoNews.setIsDel("0");
				videoNews.setVideoId(videoId);
				logger.debug("flag===="+videoNews.getFlag());
				//添加视频删除的处理记录
				videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
				logger.debug("VrId====="+videoRecord.getVrId());
				videoRecord.setVideoId(videoNews1.getVideoId());
				videoRecord.setFlag("3");
				logger.debug("flag====="+videoRecord.getFlag());
				videoRecord.setUpdateUser(SystemContext.getUserName());
				videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
				videoRecordService.update(videoRecord);
				videoNewsService.updateVideo(videoNews);
				msg = "撤销此视频的发布！";
			}else{
				videoNews.setIsDel("1");
				videoNews.setVideoId(videoId);
				videoNewsService.updateVideo(videoNews);
				msg = "删除成功！";
			}
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		this.writeMessage(response, msg);
	}
	/**
	 * 视频报送功能报送页面 H2602992 温长峰
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=addVideoNewsView")
	public String addVideoNews(Model model) {
			
		String userNo = SystemContext.getUserNO();
		String deptId = SystemContext.getUserDetail().getDeptID();
		String createDate = DateUtil.getCurrentDateStr();
		model.addAttribute("userNo", userNo);
		model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("userDeptID", deptId);
		model.addAttribute("userDeptName", SystemContext.getUserDetail()
				.getDeptName());
		model.addAttribute("createDate",createDate);
		model.addAttribute("videoCategorys",
				departmentService.getTextCategorys(deptId));

		return "console/videonews/addVideoNews";
	}
	
	/**
	 * 视频报送模块发布和存草稿功能 H2602992 20140623温长峰
	 * 修改BY wl 20140625 添加编辑方法
	 * @param videoNews
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=saveVideoInfo", method = RequestMethod.POST)
	public void saveVideo(@ModelAttribute("videoNews") VideoNews videoNews,
			@ModelAttribute("videoRecord") VideoRecord videoRecord,
			@RequestParam("video") String video,
			@RequestParam("photo") String photo,
			@RequestParam("fileName") String fileName,
			@RequestParam("imageName") String imageName,
			@RequestParam("isPublic") String isPublic,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		
		logger.debug("是否上外网"+isPublic);
		
		if (null == videoNews.getIsPublic())
			videoNews.setIsPublic("0");
		
		videoNews.setIsPublic(isPublic);
		
		
		MultipartFile videoUrl = request.getFile("videoFile");
		if(videoNews.getVideoUrl()==null||videoNews.getVideoUrl().equals("")){
			videoNews.setVideoUrl(video);
			videoNews.setFileName(fileName);
		}else{
			videoNews.setVideoUrl(FileUploader.uploadFile(request, videoUrl,
					getImagePath(request.getSession().getServletContext())));
			videoNews.setFileName(videoUrl.getOriginalFilename());
		}
		
		//图片URL
		MultipartFile photoUrl = request.getFile("photoFile");
		if (videoNews.getPhotoUrl()==null||videoNews.getPhotoUrl().equals("")){
			videoNews.setPhotoUrl(photo);
			videoNews.setImageName(imageName);
		}else{
			videoNews.setPhotoUrl(FileUploader.uploadFile(request, photoUrl,
					getImagePath(request.getSession().getServletContext())));
			videoNews.setImageName(photoUrl.getOriginalFilename());
			
		}
		//保存sava方法
		if (StringUtils.isBlank(videoNews.getVideoId())) {
			videoNews.setVideoId(UUID.randomUUID().toString().replace("-", ""));
			logger.debug("flag====="+videoNews.getFlag());
			videoNews.setIsDel("0");
			videoNews.setType("sendVideo");
			if (videoNews.getFlag().equals("0")){
			videoNewsService.save(videoNews);
			//增加处理记录
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			logger.debug("VrId====="+videoRecord.getVrId());
			videoRecord.setVideoId(videoNews.getVideoId());
			videoRecord.setFlag(videoNews.getFlag());
			logger.debug("flag====="+videoRecord.getFlag());
			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);
			this.writeMessage(response, "保存草稿成功！");
			}
			  else if (videoNews.getFlag().equals("1")){
				videoNewsService.save(videoNews);
				//增加处理记录
				videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
				logger.debug("VrId====="+videoRecord.getVrId());
				videoRecord.setVideoId(videoNews.getVideoId());
				videoRecord.setFlag(videoNews.getFlag());
				logger.debug("flag====="+videoRecord.getFlag());
				videoRecord.setUpdateUser(SystemContext.getUserName());
				videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
				videoRecordService.update(videoRecord);
				
				// 部门投稿计分
				deptScoreService.sendVideo(videoNews);
				
				this.writeMessage(response, "发布成功！");
				}
		} else {
			//更新Update方法 修改BY wl 20140625
			String msg = "";
			try{
				if(videoNews.getFlag().equals("2")){
					msg = "审核成功！";
					//增加处理记录
					videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
					logger.debug("VrId====="+videoRecord.getVrId());
					videoRecord.setVideoId(videoNews.getVideoId());
					videoRecord.setFlag(videoNews.getFlag());
					logger.debug("flag====="+videoRecord.getFlag());
					videoRecord.setUpdateUser(SystemContext.getUserName());
					videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
					
					
					
					logger.debug("是否审核过："+videoNews.getFlag());
					
				    /*
				     * 选择上外网进行视频信息的推送     zhp  
				     */
					try {
						if(isPublic.equals("1")){
							 //保存到数据库
							logger.debug("信息推送开始");
							logger.debug("图片url:"+videoNews.getPhotoUrl().replace("textnews", "videonews"));
							videoNews.setPhotoUrl(videoNews.getPhotoUrl().replace("textnews", "videonews"));
							logger.debug("视频url:"+videoNews.getVideoUrl().replace("textnews", "videonews"));
							videoNews.setVideoUrl(videoNews.getVideoUrl().replace("textnews", "videonews"));
							cmsNewsService.saveVideoToOuter(videoNews);
							
						}
					} catch (Exception e) {
						msg = "推送到外网失败！";
					}
					

				}else if(videoNews.getFlag().equals("1")){
					 msg="重新发布成功!";
					 //编辑页面重新发布
					videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
					logger.debug("VrId====="+videoRecord.getVrId());
					videoRecord.setVideoId(videoNews.getVideoId());
					videoRecord.setFlag("4");
					logger.debug("flag====="+videoRecord.getFlag());
					videoRecord.setUpdateUser(SystemContext.getUserName());
					videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
				}
				else if(videoNews.getFlag().equals("0")){
					//编辑页面重新存草稿
					msg="保存草稿成功!";
					videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
					logger.debug("VrId====="+videoRecord.getVrId());
					videoRecord.setVideoId(videoNews.getVideoId());
					videoRecord.setFlag("4");
					logger.debug("flag====="+videoRecord.getFlag());
					videoRecord.setUpdateUser(SystemContext.getUserName());
					videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
				}
			}catch(Exception e){
				msg = "保存失败！";
			}
			this.writeMessage(response, msg);
			videoNewsService.updateVideo(videoNews);
			/*
			//增加处理记录
			*videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			logger.debug("VrId====="+videoRecord.getVrId());
			videoRecord.setVideoId(videoNews.getVideoId());
			videoRecord.setFlag(videoNews.getFlag());
			logger.debug("flag====="+videoRecord.getFlag());
			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			*/

			videoRecordService.update(videoRecord);
		}
		
	}
	private String getImagePath(ServletContext servletContext) {
		return servletContext.getRealPath("/") + "resource/textnews/"
				+ FileUploader.getTimeFolder() + "/";

	}
	
	/**
	 * 加载编辑页面
	 * @param videoId
	 * @return
	 */
	@RequestMapping(params = "face=editVideo")
	public String editVideo(Model model,@RequestParam("videoId") String videoId){
		logger.debug("videoId="+videoId);
		model.addAttribute("video",videoNewsService.getVideoByVideoId(videoId));
		logger.debug("资料名称："+videoNewsService.getVideoByVideoId(videoId).getVideoTitle());
		//VideoRecord videoRecord = new VideoRecord();
		List<VideoRecord> videoRecord = videoRecordService.getVideoRecordList(videoId);
		 
		model.addAttribute("paginations", videoRecord);
		
		return"console/videonews/editVideoNews";
	}
	
	/**
	 * 视频审核功能默认查询以及页面跳转
	 * @author H2602965
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getVerifyVideolistView")
	public String queryVerifyVideolist(Model model) {
		
		Date start = this.getStartDate("");
		Date end = this.getEndDate("");
		
		List<VideoNews> listVerifyVideo= videoNewsService.getVerifyVideoNewsList(start, end,new String[]{}, 0, SystemContext.getDefaultPageSize());
		
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", listVerifyVideo);
		
		return "console/videonews/listVerifyVideoNews";
	}
	
	/**
	 * 视频审核功能 条件查询功能
	 * @author H2602965
	 * @param videoTitle
	 * @param entryUser
	 * @param entryDateS
	 * @param entryDateE
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "face=searchVideoNewsList")
	public String searchVerifyVideoNews(Model model,@RequestParam("videoTitle") String videoTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,HttpServletResponse response) throws UnsupportedEncodingException{
		
		if (entryDateS.equals("")||entryDateE.equals("")){
			 { 
				         videoTitle=URLDecoder.decode(videoTitle,"utf-8");
						 entryUser=URLDecoder.decode(entryUser,"utf-8");
						 logger.debug("videoTitle=========="+videoTitle);
	                     List<VideoNews> videoNewsList = videoNewsService.getVerifyVideoNewsList(videoTitle, entryUser, null, null, new String[]{}, pageIndex, SystemContext.getDefaultPageSize());		     model.addAttribute("videoTitle", videoTitle);
						 model.addAttribute("entryUser", entryUser);
						 model.addAttribute("videoTitle", videoTitle);
						 model.addAttribute("paginations", videoNewsList);

							     }
						}
	  else if (entryDateS.equals("")&&entryDateE.equals("")){
				        videoTitle=URLDecoder.decode(videoTitle,"utf-8");
					    entryUser=URLDecoder.decode(entryUser,"utf-8");
						logger.debug("videoTitle=========="+videoTitle);
						model.addAttribute("videoTitle", videoTitle);
					    List<VideoNews> videoNewsList = videoNewsService.getVerifyVideoNewsList(videoTitle, entryUser, null, null, new String[]{}, pageIndex, SystemContext.getDefaultPageSize());					          model.addAttribute("videoTitle", videoTitle);
						model.addAttribute("entryUser", entryUser);
						model.addAttribute("paginations", videoNewsList);

	 					    }
	  else {
					        videoTitle=URLDecoder.decode(videoTitle,"utf-8");
							entryUser=URLDecoder.decode(entryUser,"utf-8");
							Date start = this.getStartDate(entryDateS);
							Date end = this.getEndDate(entryDateE);
							logger.debug("videoTitle=========="+videoTitle);
                            List<VideoNews> videoNewsList = videoNewsService.getVerifyVideoNewsList(videoTitle, entryUser, start, end, new String[]{}, pageIndex, SystemContext.getDefaultPageSize());						
							model.addAttribute("start", start);
							model.addAttribute("end", end);
							model.addAttribute("videoTitle", videoTitle);
							model.addAttribute("entryUser", entryUser);
							model.addAttribute("paginations", videoNewsList);
		  }
							logger.debug("开始时间"+entryDateS+"结束时间"+entryDateE);

		
		/*logger.debug("entryUser="+entryUser);
		logger.debug("entryDateS"+entryDateS);
		logger.debug("entryDateE="+entryDateE);
		logger.debug("videoTitle="+videoTitle);
		videoTitle=URLDecoder.decode(videoTitle,"utf-8");
		entryUser=URLDecoder.decode(entryUser,"utf-8");
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
	
		List<VideoNews> videoNewsList = videoNewsService.getVerifyVideoNewsList(videoTitle, entryUser, start, end, new String[]{}, pageIndex, SystemContext.getDefaultPageSize());
		
		model.addAttribute("videoTitle", videoTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", videoNewsList);
		*/
		return "console/videonews/listVerifyVideoNews";
	}
	/**
	 * 视频审核编辑页面跳转
	 * @author H2602965
	 * @param model
	 * @param videoId
	 * @return
	 */
	@RequestMapping(params = "face=editVerifyVideo")
	public String editVerifyVideo(Model model,@RequestParam("videoId") String videoId){
		model.addAttribute("video",videoNewsService.getVideoByVideoId(videoId));
		logger.debug("资料名称："+videoNewsService.getVideoByVideoId(videoId).getVideoTitle());
		List<VideoRecord> videoRecord = videoRecordService.getVideoRecordList(videoId);
		model.addAttribute("videoRecord",videoRecord);
		// PageBean page = new PageBean(videoRecord, 1, SystemContext.getDefaultPageSize());
		 model.addAttribute("paginations", videoRecord);
		String verifyDate = DateUtil.getCurrentDateStr();		
		model.addAttribute("verifyDate", verifyDate);
		return"console/videonews/editVerifyVideoNews";
	}
	/**
	 * 内容管理模块  视频管理默认显示页面 wcf 20140711
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listVideoMgtView")
	public String queryVideoMgt(Model model) {
		
		Date start = this.getStartDate("");
		Date end = this.getEndDate("");
		
		List<VideoNews> videoNewsList = videoNewsService.getVideoNewsList(start, end, 0, SystemContext.getDefaultPageSize());
				
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", videoNewsList);
		
		return "console/videoMgt/videoMgt";
	}
	
}
