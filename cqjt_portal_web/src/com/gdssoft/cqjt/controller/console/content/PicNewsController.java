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
import com.gdssoft.cqjt.pojo.content.Picture;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.content.PicNewsService;

@Controller
@RequestMapping("/console/pic.xhtml")
public class PicNewsController extends BaseController {
	
	@Autowired
	@Resource(name = "picNewsServiceImpl")
	private PicNewsService picNewsService;
	
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userDetailService;
    

	/**
	 *首次加载图片信息默认查询
	 * @author H2603282
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getPiclistView")
	public String queryPiclist(Model model) {
		Date start  = this.getStartDate("");
		Date end  = this.getEndDate("");
	    List<Picture> listPicture= picNewsService.getPicList("", "", start, end,new String[]{},0,SystemContext.getDefaultPageSize());
		
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", listPicture);
		
		return "console/picMgt/listPicture";
	}
	/**
	 *按搜索条件查询
	 * @author H2603282
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=searchPicList")
	public String searchPicList(Model model,@RequestParam("picTitle") String picTitle,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,HttpServletResponse response) throws UnsupportedEncodingException{
				
		
		if (entryDateS.equals("")||entryDateE.equals("")){
			 { 
				         picTitle=URLDecoder.decode(picTitle,"utf-8");
						 entryUser=URLDecoder.decode(entryUser,"utf-8");
						 logger.debug("picTitle=========="+picTitle);
             List<Picture> listPicture= picNewsService.getPicList(picTitle,entryUser,null ,null,new String[]{},pageIndex,SystemContext.getDefaultPageSize());
					     model.addAttribute("picTitle", picTitle);
						 model.addAttribute("entryUser", entryUser);
						 model.addAttribute("paginations", listPicture);

							     }
						}
	  else if (entryDateS.equals("")&&entryDateE.equals("")){
				        picTitle=URLDecoder.decode(picTitle,"utf-8");
					    entryUser=URLDecoder.decode(entryUser,"utf-8");
						logger.debug("picTitle=========="+picTitle);
			List<Picture> listPicture= picNewsService.getPicList (picTitle,entryUser,null ,null,new String[]{},pageIndex,SystemContext.getDefaultPageSize());
				        model.addAttribute("picTitle", picTitle);
						model.addAttribute("entryUser", entryUser);
						model.addAttribute("paginations", listPicture);

	 					    }
	  else {
					        picTitle=URLDecoder.decode(picTitle,"utf-8");
							entryUser=URLDecoder.decode(entryUser,"utf-8");
							Date start = this.getStartDate(entryDateS);
							Date end = this.getEndDate(entryDateE);
							logger.debug("picTitle=========="+picTitle);
	      List<Picture> listPicture= picNewsService.getPicList (picTitle,entryUser,start,end,new String[]{},pageIndex,SystemContext.getDefaultPageSize());
							
							model.addAttribute("start", start);
							model.addAttribute("end", end);
							model.addAttribute("picTitle", picTitle);
							model.addAttribute("entryUser", entryUser);
							model.addAttribute("paginations", listPicture);
		  }
							logger.debug("开始时间"+entryDateS+"结束时间"+entryDateE);

		
		/*
		       picTitle=URLDecoder.decode(picTitle,"utf-8");
				entryUser=URLDecoder.decode(entryUser,"utf-8");
				Date start = this.getStartDate(entryDateS);
				Date end = this.getEndDate(entryDateE);
				logger.debug("PicTitle=========="+ picTitle);
				List<Picture> listPicture= picNewsService.getPicList(picTitle,entryUser,start,end,new String[]{},pageIndex,SystemContext.getDefaultPageSize());
				
				model.addAttribute("start", start);
				model.addAttribute("end", end);
				model.addAttribute("picTitle",  picTitle);
				model.addAttribute("entryUser", entryUser);
				model.addAttribute("paginations", listPicture);
			*/	
				return "console/picMgt/listPicture";
	}
	
	/**
	 * 新增图片加载页面
	 * @return
	 */
	@RequestMapping(params = "face=addPic")
	public String addFile(Model model) {
		String entryUser = SystemContext.getUserName();
		String entryDate = DateUtil.getCurrentDateStr();
		String userNo = SystemContext.getUserNO();
		logger.debug("创建人"+entryUser+"时间"+entryDate);
		
		model.addAttribute("approvers", userDetailService.getApprovers(userNo));
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("entryDate",entryDate);
		return "console/picMgt/addPicture";
	}
	
	/**
	 * 逻辑删除资料
	 * @param PicId
	 * @param response
	 */
	@RequestMapping(params = "action=deletePic")
	public void deletePic(@RequestParam("picId") String picId,
			HttpServletResponse response){
		    String msg = "";
		   logger.debug("picId="+picId);
		   try {
			Picture picture = new Picture();
			    picture.setIsDel("1");
			    picture.setPicId(picId);
			    picNewsService.updatePicture(picture);
				msg = "删除成功";
			}
		 catch (Exception e) {
			msg = "刪除失败！";
		}
		this.writeMessage(response, msg);
	}	
	
	/**
	 * 加载编辑页面
	 * @param picId
	 * @return
	 */
	@RequestMapping(params = "face=editPic")
	public String editPic(Model model,@RequestParam("picId") String picId){
		logger.debug("picId="+picId);
		model.addAttribute("picture",picNewsService.getPicByPicId(picId));
		logger.debug(picId);
		logger.debug(picNewsService.getPicByPicId(picId));
		logger.debug("资料名称："+ picNewsService.getPicByPicId(picId).getPicTitle());
		logger.debug("资料名称："+ picNewsService.getPicByPicId(picId).getEntryDate());
		logger.debug("资料名称："+ picNewsService.getPicByPicId(picId).getEntryUser());
		return"console/picMgt/editPicture";
	}
	
	/**
	 * 图片存草稿和发布
	 * 修改 添加编辑方法
	 * @param picture
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=savePicInfo", method = RequestMethod.POST)
	public void saveMagazine(@ModelAttribute("picture") Picture picture,
			@RequestParam("photo") String photo,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		//图片URL
		MultipartFile photoUrl = request.getFile("picFile");
		if (picture.getPicUrl()== null||picture.getPicUrl().equals("")){
			picture.setPicUrl(photo);
		}else{
			picture.setPicUrl(FileUploader.uploadFile(request, photoUrl,
					getImagePath(request.getSession().getServletContext())));
		}
		//保存sava方法
		if (StringUtils.isBlank(picture.getPicId())) {
			picture.setPicId(UUID.randomUUID().toString().replace("-", ""));
			logger.debug("flag====="+ picture.getFlag());
			logger.debug("picUrl====="+ picture.getPicUrl());
			picture.setIsDel("0");
			if(picture.getFlag().equals("0")){
				picNewsService.save(picture);
				this.writeMessage(response, "保存草稿成功！");
			}
			else if(picture.getFlag().equals("1")){
				picNewsService.save(picture);
				this.writeMessage(response, "发布成功！");
			}
			
		} else {
			String msg = "";
			logger.debug("msg========="+msg);
			logger.debug("magazinede zhi wei ==="+picture.getFlag());
			try{
				if(picture.getFlag().equals("2")){
					msg = "审核成功！";
				}else if(picture.getFlag().equals("1")){
					msg="发布成功!";
					logger.debug("msg========="+msg);
				}else{
					msg = "保存草稿成功！";
				}
			}catch(Exception e){
				msg = "保存失败！";
			}
			this.writeMessage(response, msg);
			picNewsService.updatePicture(picture);
		}
	}

	private String getImagePath(ServletContext servletContext) {
		return servletContext.getRealPath("/") + "resource/picnews/"
				+ FileUploader.getTimeFolder() + "/";
	}
}
