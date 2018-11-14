package com.gdssoft.cqjt.controller.portal.index;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.TextStatute;
import com.gdssoft.cqjt.service.TextStatuteService;

@Controller
@RequestMapping("/guest/statute.xhtml")
public class StatuteController extends BaseController {

	@Autowired
	@Resource(name = "textStatuteServiceImpl")
	private TextStatuteService textStatuteService;

	/**
	 * 法律法规
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(params = "face=lawRegulations")
	public String lawRegulations(Model model, HttpServletResponse response) {

		List<TextStatute> textStatuteList = new ArrayList<TextStatute>();
		textStatuteList = textStatuteService.getStatutelistView(null, null, 0, 18);
		
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		model.addAttribute("paginations", textStatuteList);
		return "portal/index/lawRegulations";
	}

	/**
	 * 分页查询
	 * @param pageIndex
	 * @param categoryId
	 * @param statuteCat
	 * @param statute
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
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
		
		List<TextStatute> textStatuteList = new ArrayList<TextStatute>();
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		logger.debug("newsTitle==========" + newsTitle); 
 
		textStatuteList = textStatuteService.queryStatutelistView(newsTitle,
				"", start, end, pageIndex,"",new String[]{},new int[]{},
				SystemContext.getDefaultPageSize(),sc,s);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("paginations", textStatuteList);
		model.addAttribute("statuteCat", sc);
		model.addAttribute("statute", s);
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);
 
		return "portal/index/lawRegulations";
		 
	}
	
	@RequestMapping(params="face=gatStatuteDetails")
	public String gatStatuteDetails(HttpServletRequest request, Model model){
		
		String statuteId = request.getParameter("statuteId");
		TextStatute textStatute = new TextStatute();
		List<TextStatute> textStatuteList = new ArrayList<TextStatute>();
		try{
			textStatute = textStatuteService.getTextStatuteDetail(statuteId);
			textStatuteList = textStatuteService.getStatutelistView(null, null, 0, 20);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		model.addAttribute("textStatute",textStatute);
		model.addAttribute("textStatuteList",textStatuteList);
		return "portal/index/lawRegDetails";
	}
}
