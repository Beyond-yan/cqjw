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
import com.gdssoft.cqjt.pojo.TextPerson;
import com.gdssoft.cqjt.service.TextPersonService;

@Controller
@RequestMapping("/guest/person.xhtml")
public class PersonController extends BaseController {

	@Autowired
	@Resource(name = "textPersonServiceImpl")
	private TextPersonService textPersonService;

	/**
	 * 
	 * 
	 * @param model
	 * @param response
	 */
	@RequestMapping(params = "face=personList")
	public String personList(Model model, HttpServletResponse response) {

		
		List<TextPerson> textPersonList = new ArrayList<TextPerson>();
		textPersonList = textPersonService.getPersonlistView(null, null, 0, 19);
		
		List<TextPerson> textPersonLista = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListb = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListc = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListd = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListe = new ArrayList<TextPerson>();
		try{
			textPersonLista = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"交通运输部领导");
			textPersonListb = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"委系统市管干部");
			textPersonListc = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"委管干部");
			textPersonListd = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"区县交委");
			textPersonListe = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"其他");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		model.addAttribute("textPersonLista",textPersonLista);
		model.addAttribute("textPersonListb",textPersonListb);
		model.addAttribute("textPersonListc",textPersonListc);
		model.addAttribute("textPersonListd",textPersonListd);
		model.addAttribute("textPersonListe",textPersonListe);
		
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		model.addAttribute("paginations", textPersonList);
		
		return "portal/index/personList";
	}

	
	/**
	 * 分页查询
	 * @param pageIndex
	 * @param categoryId
	 * @param PersonCat
	 * @param Person
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=PersonView")
	public String queryPersonViewByUserDate(@RequestParam("newsTitle") String newsTitle,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("PersonCat") String PersonCat,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
		
		List<TextPerson> textPersonLista = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListb = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListc = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListd = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListe = new ArrayList<TextPerson>();
		try{
			textPersonLista = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"交通运输部领导");
			textPersonListb = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"委系统市管干部");
			textPersonListc = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"委管干部");
			textPersonListd = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"区县交委");
			textPersonListe = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"其他");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		model.addAttribute("textPersonLista",textPersonLista);
		model.addAttribute("textPersonListb",textPersonListb);
		model.addAttribute("textPersonListc",textPersonListc);
		model.addAttribute("textPersonListd",textPersonListd);
		model.addAttribute("textPersonListe",textPersonListe);
 
		//解决中文乱码
		String sc = "";
		try {
			sc = new String(PersonCat.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		
		List<TextPerson> textPersonList = new ArrayList<TextPerson>();
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		logger.debug("newsTitle==========" + newsTitle);

		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
 
		textPersonList = textPersonService.queryPersonlistView(newsTitle,
				"", start, end, pageIndex,"",new String[]{},new int[]{},
				19,sc);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("paginations", textPersonList);
		model.addAttribute("PersonCat", sc);
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);
 
		return "portal/index/personList";
		 
	}
	
	@RequestMapping(params="face=gatPersonDetails")
	public String gatPersonDetails(HttpServletRequest request, Model model){
		
		String PersonId = request.getParameter("personId");
		TextPerson textPerson = new TextPerson();
		List<TextPerson> textPersonLista = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListb = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListc = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListd = new ArrayList<TextPerson>();
		List<TextPerson> textPersonListe = new ArrayList<TextPerson>();
		try{
			textPerson = textPersonService.getTextPersonDetail(PersonId);
			
			textPersonLista = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"交通运输部领导");
			textPersonListb = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"委系统市管干部");
			textPersonListc = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"委管干部");
			textPersonListd = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"区县交委");
			textPersonListe = textPersonService.queryPersonlistView("",
					"", null, null, 0,"",new String[]{},new int[]{},
					3,"其他");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
 
		
		model.addAttribute("textPerson",textPerson);
		model.addAttribute("textPersonLista",textPersonLista);
		model.addAttribute("textPersonListb",textPersonListb);
		model.addAttribute("textPersonListc",textPersonListc);
		model.addAttribute("textPersonListd",textPersonListd);
		model.addAttribute("textPersonListe",textPersonListe);
		return "portal/index/personInfo";
	}
}
