package com.gdssoft.cqjt.controller.console.content;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.gdssoft.cqjt.pojo.content.VideoMgt;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.content.VideoMgtService;

@Controller
@RequestMapping("/console/videoMgt.xhtml")
public class VideoMgtController extends BaseController {

	@Autowired
	@Resource(name = "videoMgtServiceImpl")
	private VideoMgtService videoMgtService;
	
	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	
	/**
	 * 内容管理模块,视频管理默认列表显示,页面跳转
	 * @author H2602965
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listView")
	public String query(Model model) {
		
		Date start = this.getStartDate("");
		Date end = this.getEndDate("");
		
		List<VideoMgt> videoMgtList = videoMgtService.getVideoMgtList(start, end,0,SystemContext.getDefaultPageSize());
				
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", videoMgtList);
		
		return "console/videoMgt/listVideoMgt";
	}
	/**
	 * 内容管理模块,视频管理 条件查询功能
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
	public String searchFileList(Model model,@RequestParam("videoTitle") String videoTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,HttpServletResponse response) throws UnsupportedEncodingException{
		
		
			if (entryDateS.equals("")||entryDateE.equals("")){
				 { 
					         videoTitle=URLDecoder.decode(videoTitle,"utf-8");
							 entryUser=URLDecoder.decode(entryUser,"utf-8");
							 logger.debug("videoTitle=========="+videoTitle);
		    List<VideoMgt> videoMgtList = videoMgtService.getVideoMgtList(videoTitle, entryUser, null, null,new String[]{},pageIndex,SystemContext.getDefaultPageSize());//VideoMgtService..getVideoMgtList(videoTitle,entryUser,start,end,"1");					     model.addAttribute("videoTitle", videoTitle);
							 model.addAttribute("entryUser", entryUser);
							 model.addAttribute("videoTitle", videoTitle);
							 model.addAttribute("paginations", videoMgtList);
	
								     }
							}
		   else if (entryDateS.equals("")&&entryDateE.equals("")){
					        videoTitle=URLDecoder.decode(videoTitle,"utf-8");
						    entryUser=URLDecoder.decode(entryUser,"utf-8");
							logger.debug("videoTitle=========="+videoTitle);
					List<VideoMgt> videoMgtList = videoMgtService.getVideoMgtList(videoTitle, entryUser, null, null,new String[]{},pageIndex,SystemContext.getDefaultPageSize());//VideoMgtService..getVideoMgtList(videoTitle,entryUser,start,end,"1");
					        model.addAttribute("videoTitle", videoTitle);
							model.addAttribute("entryUser", entryUser);
							model.addAttribute("paginations", videoMgtList);
	
		 					    }
		    else {
						        videoTitle=URLDecoder.decode(videoTitle,"utf-8");
								entryUser=URLDecoder.decode(entryUser,"utf-8");
								Date start = this.getStartDate(entryDateS);
								Date end = this.getEndDate(entryDateE);
								logger.debug("videoTitle=========="+videoTitle);
	             List<VideoMgt> videoMgtList = videoMgtService.getVideoMgtList(videoTitle, entryUser, start, end,new String[]{},pageIndex,SystemContext.getDefaultPageSize());//VideoMgtService..getVideoMgtList(videoTitle,entryUser,start,end,"1");							
								model.addAttribute("start", start);
								model.addAttribute("end", end);
								model.addAttribute("videoTitle", videoTitle);
								model.addAttribute("entryUser", entryUser);
								model.addAttribute("paginations", videoMgtList);
			   }
								logger.debug("开始时间"+entryDateS+"结束时间"+entryDateE);
		
		/*
		videoTitle=URLDecoder.decode(videoTitle,"utf-8");
		entryUser=URLDecoder.decode(entryUser,"utf-8");
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
		List<VideoMgt> videoMgtList = videoMgtService.getVideoMgtList(videoTitle, entryUser, start, end,new String[]{},pageIndex,SystemContext.getDefaultPageSize());//VideoMgtService..getVideoMgtList(videoTitle,entryUser,start,end,"1");
		
		model.addAttribute("videoTitle", videoTitle);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", videoMgtList);
		*/
		return "console/videoMgt/listVideoMgt";
	}
	/**
	 * 逻辑删除资料
	 * @param videoId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteVideo")
	public void deleteVideo(@RequestParam("videoId") String videoId,
			HttpServletResponse response){
		String msg = "";
		logger.debug("videoId="+videoId);
		try {
			VideoMgt videoMgt = new VideoMgt();
			VideoMgt videoMgt1 = new VideoMgt();
			videoMgt1 =videoMgtService.getVideoByVideoId(videoId);
		    String a =videoMgt1.getFlag();
		     logger.debug("flagdezhiwei===="+a);
			if(a.equals("2")){
				videoMgt.setFlag("3");
				videoMgt.setIsDel("0");
				videoMgt.setVideoId(videoId);
				logger.debug("flag===="+videoMgt.getFlag());
				videoMgtService.updateVideo(videoMgt);
				msg = "撤销此视频的发布！";
			}else{
				videoMgt.setIsDel("1");
				videoMgt.setVideoId(videoId);
				videoMgtService.updateVideo(videoMgt);
				msg = "删除成功！";
			}
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		this.writeMessage(response, msg);
	}
	/**
	 * 视频管理跳转到新增页面
	 * @author H2602965
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=addVideoMgtView")
	public String addVideoMgt(Model model) {
			
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

		return "console/videoMgt/addVideoMgt";
	}
	
	/**
	 * 视频管理保存模块
	 *@author H2602965
	 * @param videoMgt
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=saveVideoInfo", method = RequestMethod.POST)
	public void saveVideo(@ModelAttribute("videoMgt") VideoMgt videoMgt,
			@RequestParam("video") String video,
			@RequestParam("photo") String photo,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		
		if (null == videoMgt.getIsPublic())
			videoMgt.setIsPublic("0");
		
		MultipartFile videoUrl = request.getFile("videoFile");
		if(videoMgt.getVideoUrl()==null||videoMgt.getVideoUrl().equals("")){
			videoMgt.setVideoUrl(video);
		}else{
			videoMgt.setVideoUrl(FileUploader.uploadFile(request, videoUrl,
					getImagePath(request.getSession().getServletContext())));
		}
		
		//图片URL
		MultipartFile photoUrl = request.getFile("photoFile");
		if (videoMgt.getPhotoUrl()==null||videoMgt.getPhotoUrl().equals("")){
			videoMgt.setPhotoUrl(photo);
		}else{
			videoMgt.setPhotoUrl(FileUploader.uploadFile(request, photoUrl,
					getImagePath(request.getSession().getServletContext())));
		}
		//保存sava方法
		if (StringUtils.isBlank(videoMgt.getVideoId())) {
			videoMgt.setVideoId(UUID.randomUUID().toString().replace("-", ""));
			logger.debug("flag====="+videoMgt.getFlag());
			videoMgt.setIsDel("0"); 
			if(videoMgt.getFlag().equals("1")){
				videoMgtService.save(videoMgt);
				this.writeMessage(response, "录入成功！");
			}else if(videoMgt.getFlag().equals("0")){
				videoMgtService.save(videoMgt);
				this.writeMessage(response, "保存草稿成功！");
			}
			
		} else {
			//更新Update方法 修改BY wl 20140625
			String msg = "";
			try{
				if(videoMgt.getFlag().equals("2")){
					msg = "审核成功！";
				}else if(videoMgt.getFlag().equals("1")){
					msg="录入成功!";
				}else{
					msg = "保存草稿成功！";
				}
			}catch(Exception e){
				msg = "保存失败！";
			}
			this.writeMessage(response, msg);
			videoMgtService.updateVideo(videoMgt);
		}
	}
	private String getImagePath(ServletContext servletContext) {
		return servletContext.getRealPath("/") + "resource/textnews/"
				+ FileUploader.getTimeFolder() + "/";

	}
	
	/**
	 * 内容管理模块,视频管理编辑方法,跳转到编辑页面
	 * @param videoId
	 * @return
	 */
	@RequestMapping(params = "face=editVideo")
	public String editVideo(Model model,@RequestParam("videoId") String videoId){
		logger.debug("videoId="+videoId);
		model.addAttribute("video",videoMgtService.getVideoByVideoId(videoId));
		logger.debug("资料名称："+videoMgtService.getVideoByVideoId(videoId).getVideoTitle());
		return"console/videoMgt/editVideoMgt";
	}
}
