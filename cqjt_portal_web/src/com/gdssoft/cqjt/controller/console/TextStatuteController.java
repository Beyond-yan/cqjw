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
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.TextStatute;
import com.gdssoft.cqjt.pojo.videoNews.VideoRecord;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.TextStatuteService;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.videoNews.VideoRecordService;

@Controller
@RequestMapping("/console/textStatute.xhtml")
public class TextStatuteController extends BaseController {
	
	@Autowired
	@Resource(name = "textStatuteServiceImpl")
	private TextStatuteService textStatuteService;
	
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userDetailService;
	
	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	
	@Autowired
	@Resource(name = "videoRecordServiceImpl")
	private VideoRecordService videoRecordService;
	
	@RequestMapping(params = "face=StatutelistView")
	public String queryStatutelistView(HttpServletRequest request, Model model) {
		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		List<TextStatute> textStatuteList = new ArrayList<TextStatute>();
		int pageIndex = 0;
			// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
			if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")
					|| roleName.equals("GovEditor")) {
				textStatuteList = textStatuteService.getStatutelistView(null, null,
						pageIndex, SystemContext.getDefaultPageSize());
			} else {// 查询当前用户报送信息
				textStatuteList = textStatuteService.getStatutelistView("",
						SystemContext.getUserName(), null, null, pageIndex,
						SystemContext.getDefaultPageSize());
			}
			model.addAttribute("paginations", textStatuteList);
			return "console/textnews/news/TextStatuteList";
	}
	
	
	@RequestMapping(params = "face=StatuteView")
	public String queryStatuteViewByUserDate(@RequestParam("newsTitle") String newsTitle,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("StatuteCat") String statuteCat,
			@RequestParam("Statute") String statute,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
 
		//解决中文乱码
		String sc = "";
		String s = "";
		try {
			sc = new String(statuteCat.getBytes("iso-8859-1"),"utf-8");
			s = new String(statute.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		
		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		List<TextStatute> textStatuteList = new ArrayList<TextStatute>();
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		logger.debug("newsTitle==========" + newsTitle);
 
		// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
		if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")
				|| roleName.equals("GovEditor")) {
			textStatuteList = textStatuteService.queryStatutelistView(newsTitle,
					"", start, end, pageIndex,"",new String[]{},new int[]{},
					SystemContext.getDefaultPageSize(),sc,s);
		} else {// 查询当前用户报送信息
			textStatuteList = textStatuteService.queryStatutelistView(newsTitle,
					SystemContext.getUserName(), start, end, pageIndex,"",new String[]{},new int[]{},
					SystemContext.getDefaultPageSize(),sc,s);
		}
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("paginations", textStatuteList);
		model.addAttribute("statuteCat", sc);
		model.addAttribute("statute", s);
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);
 
		return "console/textnews/news/TextStatuteList";
		 
	}
	
	
	@RequestMapping(params = "face=PerlistView")
	public String queryPerlistView(HttpServletRequest request, Model model) {
		
		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		
		List<TextStatute> textStatuteList = new ArrayList<TextStatute>();
		int pageIndex = 0;
			// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
			if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")
					|| roleName.equals("GovEditor")) {
				textStatuteList = textStatuteService.getStatutelistView(null, null,
						pageIndex, SystemContext.getDefaultPageSize());
			} else {// 查询当前用户报送信息
				textStatuteList = textStatuteService.getStatutelistView("",
						SystemContext.getUserName(), null, null, pageIndex,
						SystemContext.getDefaultPageSize());
			}
			model.addAttribute("paginations", textStatuteList); 
			return "console/textnews/news/PerMatterList";	 
	}
	
	
	/**
	 * 跳转到法律法规新增页面并加载数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=addStatuteView")
	public String addStatute(Model model) {
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
		return "console/textnews/news/addStatute";
	}
	
	
	/**
	 * 法律法规保存
	 * @param textStatute
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=saveStatute", method = RequestMethod.POST)
	public void saveStatute(@ModelAttribute("textNews") TextStatute textStatute,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		
		textStatute.setStatuteId(UUID.randomUUID().toString().replace("-", ""));
		textStatute.setEntryDate(DateUtil.getCurrentDate());
		textStatute.setIsDel("0");
		textStatute.setGovUseFlag(0);
		textStatute.setCreateBy(textStatute.getEntryUser());
		textStatute.setCreateDate(DateUtil.getCurrentDateStr());
		textStatute.setStatuteContent(textStatute.getEditorValue());
		
		try {
			textStatuteService.save(textStatute);
			this.writeMessage(response, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName(), e);
			this.writeMessage(response, "ERROR，保存失败！");
		}
	}
	
	@RequestMapping(params = "action=deleteInfo")
	public void deleteInfo(Model model, @RequestParam("statuteId") String statuteId,
			HttpServletResponse response) {

		String msg = "";
		try {
			TextStatute textStatute = new TextStatute();
			textStatute.setStatuteId(statuteId);
			textStatuteService.delete(statuteId);
 
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}

		this.writeMessage(response, msg);
	}
	
	@RequestMapping(params = "face=editStatuteView")
	public String editStatute(Model model, @RequestParam("statuteId") String statuteId) {

		logger.debug("得到statuteId的值" + statuteId);
		String userNo = SystemContext.getUserNO();
		String deptId = SystemContext.getUserDetail().getDeptID();
		String roleName = SystemContext.getRoleName();
		List<VideoRecord> newsRecord = videoRecordService.getVideoRecordList(statuteId);
		model.addAttribute("newsRecord", newsRecord);

		model.addAttribute("userNo", userNo);
		// model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("userDeptID", deptId);
		model.addAttribute("userDeptName", SystemContext.getUserDetail().getDeptName());
		
		
		TextStatute textStatute = textStatuteService.getTextStatuteDetail(statuteId);
		if(null!=textStatute.getPubDate() || "".equals(textStatute.getPubDate())){
			textStatute.setPubDate(textStatute.getPubDate().substring(0,10));	
		}
		if(null!=textStatute.getInplementDate() || "".equals(textStatute.getInplementDate())){
			textStatute.setInplementDate(textStatute.getInplementDate().substring(0,10));
		}
		model.addAttribute("textNewsEditInfo", textStatute);
		model.addAttribute("textCategorys",departmentService.getTextCategorys(deptId));
		String userName = null;
		List<String> photoList = new ArrayList<String>();
		if(textStatute != null){
			userName = textStatute.getEntryUser();
			String photoUrl = textStatute.getPhotoUrl();
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
	 * 法律法规编辑操作
	 * @param textNews
	 * @param imageUrl
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=updateTextStatute")
	public void updateStatuteNews(@ModelAttribute("textNews") TextStatute textStatute,
			MultipartHttpServletRequest request, HttpServletResponse response) {


		DateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date entryDate = null;
		try {
			entryDate = fds.parse(textStatute.getCreateDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		textStatute.setEntryDate(entryDate);
		if (!StringUtils.isBlank(textStatute.getStatuteId())) {
			//logger.debug("flag=" + textNews.getFlag());
			textStatute.setIsDel("0");
			 
			VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(textStatute.getStatuteId());
			if (textStatute.getFlag().equals("1")) {
				videoRecord.setFlag("4");// 信息处理记录，flag为4表示修改
			}
			if (textStatute.getFlag().equals("0")) {
				videoRecord.setFlag("0");// 信息处理记录，flag为0表示存草稿
			}
			if (textStatute.getFlag().equals("2")) {
				videoRecord.setFlag("2");// 信息处理记录，flag为2表示分拣
			}
			if (textStatute.getFlag().equals("8")) {
				videoRecord.setFlag("18");// 信息处理记录，flag为18表示归档
			}
			if (textStatute.getFlag().equals("7")) {
				String rejectReason = request.getParameter("rejectReason");
				videoRecord.setRejectReason(rejectReason);
				videoRecord.setFlag("13");
			} else {
				videoRecord.setRejectReason("");
			}

			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);
			if (textStatute.getFlag().equals("7")) {
				this.writeMessage(response, "退稿成功！");
			} else {
				this.writeMessage(response, "保存成功！");
			}
			if (textStatute.getFlag().equals("1")) {
				if (textStatute.getFg().equals("0")) {

				} else {
					textStatute.setFlag(textStatute.getFg());
				}
			}
			textStatuteService.update(textStatute);
		} else {
			this.writeMessage(response, "保存失败！");
		}
	}

}
