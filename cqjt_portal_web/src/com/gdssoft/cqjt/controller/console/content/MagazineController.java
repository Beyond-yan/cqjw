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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.gdssoft.cqjt.pojo.content.Magazine;
import com.gdssoft.cqjt.service.content.MagazineService;

@Controller
@RequestMapping("/console/magazine.xhtml")
public class MagazineController extends BaseController  {

	@Autowired
	@Resource(name = "magazineServiceImpl")
	private MagazineService magazineService;
    
	/**
	 *首次加载杂志刊物信息默认查询
	 * @author H2603282
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getMagazinelistView")
	public String queryMagazinelist(Model model) {
     
		Date start = this.getStartDate("");
		Date end = this.getEndDate("");
	    List<Magazine> listMagazine= magazineService.getMagazineList(start, end, 0,SystemContext.getDefaultPageSize());
		
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", listMagazine);
    
		return "console/magazine/listMagazine";
	}
	/**
	 * 用于选择查询
	 * @param fileName
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "face=searchMagazineList")
	public String searchFileList(Model model,@RequestParam("title") String title,
			@RequestParam("entryUser") String entryUser,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,HttpServletResponse response) throws UnsupportedEncodingException{
		
		if (entryDateS.equals("")||entryDateE.equals("")){
			 { 
				         title=URLDecoder.decode(title,"utf-8");
						 entryUser=URLDecoder.decode(entryUser,"utf-8");
						 logger.debug("title=========="+title);
						 List<Magazine> listMagazine= magazineService.getMagazineList(title,entryUser,null ,null,new String[]{},pageIndex,SystemContext.getDefaultPageSize());
					     model.addAttribute("title", title);
						 model.addAttribute("entryUser", entryUser);
						 model.addAttribute("paginations", listMagazine);

							     }
						}
	  else if (entryDateS.equals("")&&entryDateE.equals("")){
				        title=URLDecoder.decode(title,"utf-8");
					    entryUser=URLDecoder.decode(entryUser,"utf-8");
						logger.debug("title=========="+title);
						List<Magazine> listMagazine= magazineService.getMagazineList(title,entryUser,null ,null,new String[]{},pageIndex,SystemContext.getDefaultPageSize());
				        model.addAttribute("title", title);
						model.addAttribute("entryUser", entryUser);
						model.addAttribute("paginations", listMagazine);

	 					    }
	 else {
					        title=URLDecoder.decode(title,"utf-8");
							entryUser=URLDecoder.decode(entryUser,"utf-8");
							Date start = this.getStartDate(entryDateS);
							Date end = this.getEndDate(entryDateE);
							logger.debug("title=========="+title);
							List<Magazine> listMagazine= magazineService.getMagazineList(title,entryUser,start,end,new String[]{},pageIndex,SystemContext.getDefaultPageSize());
							
							model.addAttribute("start", start);
							model.addAttribute("end", end);
							model.addAttribute("title", title);
							model.addAttribute("entryUser", entryUser);
							model.addAttribute("paginations", listMagazine);
		  }
							logger.debug("开始时间"+entryDateS+"结束时间"+entryDateE);

		
		/*
		title=URLDecoder.decode(title,"utf-8");
		entryUser=URLDecoder.decode(entryUser,"utf-8");
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
		logger.debug("title=========="+title);
		List<Magazine> listMagazine= magazineService.getMagazineList(title,entryUser,start,end,new String[]{},pageIndex,SystemContext.getDefaultPageSize());
		
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("title", title);
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("paginations", listMagazine);
		*/
		return "console/magazine/listMagazine";
	}	
	
	/**
	 * 用于添加新的杂志刊物
	 * @param fileName
	 * @param pageIndex
	 * @param response
	 */
		@RequestMapping(params = "face=addMagazine")
		public String addMagazine(Model model) {
			String entryUser = SystemContext.getUserName();
			String entryDate = DateUtil.getCurrentDateStr();
			model.addAttribute("entryUser", entryUser);
			model.addAttribute("entryDate", entryDate);

			return "console/magazine/addMagazine";
		}
		/**
		 * 杂志刊物报送模块发布和存草稿功能 07/11
		 * 修改 添加编辑方法
		 * @param magazine
		 * @param request
		 * @param response
		 */
		@RequestMapping(params = "action=saveMagazineInfo", method = RequestMethod.POST)
		public void saveMagazine(@ModelAttribute("magazine") Magazine magazine,
				@RequestParam("magazine") String magazine1,
				@RequestParam("photo") String photo,
				MultipartHttpServletRequest request, HttpServletResponse response) {
		
			MultipartFile magazineUrl = request.getFile("magazineFile");
			logger.debug("1111=="+magazine1);
			logger.debug("flag========="+magazine.getFlag());
			logger.debug(magazine.getMagazineUrl());
			if(magazine.getMagazineUrl()==null||magazine.getMagazineUrl().equals("")){
				magazine.setMagazineUrl(magazine1);
			}else{
				magazine.setMagazineUrl(FileUploader.uploadFile(request, magazineUrl,
						getImagePath(request.getSession().getServletContext())));
			}
			
			//图片URL
			MultipartFile photoUrl = request.getFile("photoFile");
			if (magazine.getPhotoUrl()==null||magazine.getPhotoUrl().equals("")){
				magazine.setPhotoUrl(photo);
			}else{
				magazine.setPhotoUrl(FileUploader.uploadFile(request, photoUrl,
						getImagePath(request.getSession().getServletContext())));
			}
			//保存sava方法
			if (StringUtils.isBlank(magazine.getMagazineId())) {
			   magazine.setMagazineId(UUID.randomUUID().toString().replace("-", ""));
				logger.debug("flag====="+ magazine.getFlag());
				logger.debug("photoUrl====="+ magazine.getPhotoUrl());
				magazine.setIsDel("0");
				if (magazine.getFlag().equals("0")){
				  magazineService.save(magazine);
				  this.writeMessage(response, "保存成功！");
				}
				else if(magazine.getFlag().equals("1")){
					magazineService.save(magazine);
					this.writeMessage(response, "发布成功！");
					}
			} else {
				String msg = "";
				logger.debug("msg========="+msg);
				logger.debug("magazinede zhi wei ==="+magazine.getFlag());
				try{
					if(magazine.getFlag().equals("2")){
						msg = "审核成功！";
					}else if(magazine.getFlag().equals("1")){
						msg="发布成功!";
						logger.debug("msg========="+msg);
					}else{
						msg = "保存草稿成功！";
					}
				}catch(Exception e){
					msg = "保存失败！";
				}
				this.writeMessage(response, msg);
				magazineService.updateMagazine(magazine);
			}
		}

		private String getImagePath(ServletContext servletContext) {
			return servletContext.getRealPath("/") + "resource/magazinenews/"
					+ FileUploader.getTimeFolder() + "/";
		}
		

		/**
		 * 逻辑删除资料
		 * @param MagazineId
		 * @param response
		 */
		@RequestMapping(params = "action=deleteMagazine")
		public void deleteMagazine(@RequestParam("magazineId") String magazineId,
				HttpServletResponse response){
			  String msg = "";
			   logger.debug("magazineId="+magazineId);
			   try {
				Magazine magazine = new Magazine ();
					magazine.setIsDel("1");
					magazine.setMagazineId(magazineId);
					magazineService.updateMagazine(magazine);
					msg = "删除成功";
				}
			 catch (Exception e) {
				msg = "刪除失败！";
			}
			this.writeMessage(response, msg);
		}	
		
		/**
		 * 加载编辑页面
		 * @param magazineId
		 * @return
		 */
		@RequestMapping(params = "face=editMagazine")
		public String editMagazine(Model model,@RequestParam("magazineId") String magazineId){
			logger.debug("magazineId="+magazineId);
			model.addAttribute("magazine",magazineService.getMagazineByMagazineId(magazineId));
			logger.debug(magazineId);
			logger.debug(magazineService.getMagazineByMagazineId(magazineId));
			logger.debug("资料名称："+ magazineService.getMagazineByMagazineId(magazineId).getTitle());
			logger.debug("资料名称："+ magazineService.getMagazineByMagazineId(magazineId).getEntryDate());
			logger.debug("资料名称："+ magazineService.getMagazineByMagazineId(magazineId).getEntryUser());
			return"console/magazine/editMagazine";
		}
		
		/**
		 * 加载编辑页面
		 * @param magazineId
		 * @return
		 */
		@RequestMapping(params = "face=verifyMagazine")
		public String verifyMagazine(Model model,@RequestParam("magazineId") String magazineId){
			logger.debug("magazineId="+magazineId);
			model.addAttribute("magazine",magazineService.getMagazineByMagazineId(magazineId));
			logger.debug(magazineId);
			logger.debug(magazineService.getMagazineByMagazineId(magazineId));
			logger.debug("资料名称："+ magazineService.getMagazineByMagazineId(magazineId).getTitle());
			logger.debug("资料名称："+ magazineService.getMagazineByMagazineId(magazineId).getEntryDate());
			logger.debug("资料名称："+ magazineService.getMagazineByMagazineId(magazineId).getEntryUser());
			return"console/magazine/verifyMagazine";
		}
}
