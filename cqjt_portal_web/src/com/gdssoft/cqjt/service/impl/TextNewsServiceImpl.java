package com.gdssoft.cqjt.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.cqjt.dao.DepartmentScoreDao;
import com.gdssoft.cqjt.dao.TextGovInfoDao;
import com.gdssoft.cqjt.dao.TextNewsDao;
import com.gdssoft.cqjt.dao.TextPublicationDao;
import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.service.TextGovInfoService;
import com.gdssoft.cqjt.service.TextNewsService;
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

@Service("textNewsServiceImpl")
public class TextNewsServiceImpl implements TextNewsService {
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Autowired
	@Resource(name = "textNewsDao")
	private TextNewsDao textNewsDao;

	@Autowired
	@Resource(name = "textGovInfoDao")
	private TextGovInfoDao textGovInfoDao;

	@Autowired
	@Resource(name = "textPublicationDao")
	private TextPublicationDao textPublicationDao;
	
	@Autowired
	@Resource(name = "textGovInfoServiceImpl")
	private TextGovInfoService textGovInfoService;
	
	@Resource(name = "deptScoreDao")
	private DepartmentScoreDao deptScoreDao;

	@Override
	public void save(TextNews textNews) {
		textNews.setIsDel("0");
		textNewsDao.insertTextNews(textNews);
	}

	@Override
	public void update(TextNews textNews) {
		textNews.setIsDel("0");
		textNewsDao.updateTextNews(textNews);

	}

	@Override
	public void delete(String newsId) {
		// textNewsDao.deleteTextNews(newsId);
		TextNews textNews = textNewsDao.getTextNewsDetail(newsId);
		textNews.setIsDel("1");
		textNewsDao.updateTextNews(textNews);
		List<TextGovInfo> textGovInfoList = textGovInfoService.getTextGovInfoByNewsId(newsId);
		if(textGovInfoList.size()>0){
			for(TextGovInfo textGovInfo:textGovInfoList){
				textGovInfoDao.deleteInfoReport(textGovInfo);
			}
		}
	}

	// 政务信息采用后更改gov_use_flag字段
	@Override
	public void updateGovUseFlag(String newsId) {
		// textNewsDao.updateGovUseFlag(newsId);
		TextNews textNews = textNewsDao.getTextNewsDetail(newsId);
		textNews.setGovUseFlag(1);
		textNews.setIsDel("0");
		textNewsDao.updateTextNews(textNews);
	}
	
	// 政务信息上报后更改gov_use_flag字段  by llj
	public void updateGovUseFlagReport(String newsId){
			TextNews textNews = textNewsDao.getTextNewsDetail(newsId);
			textNews.setGovUseFlag(3);
			textNews.setIsDel("0");
			textNewsDao.updateTextNews(textNews);
		}
	
	//政务信息状态改为未采编后更改gov_use_flag字段  by llj
	public void updateGovUseFlagNot(String newsId){
		TextNews textNews = textNewsDao.getTextNewsDetail(newsId);
		textNews.setGovUseFlag(0);
		textNews.setIsDel("0");
		textNewsDao.updateTextNews(textNews);
	}
	// 政务信息采用后更改gov_use_flag字段修改
	@Override
	public void updateGovUseFlag(String newsId, String isSelected, String dept) {
		// textNewsDao.updateGovUseFlag(newsId);
		TextNews textNews = textNewsDao.getTextNewsDetail(newsId);
		if (isSelected.equals("1")) {
			textNews.setGovUseFlag(1);
		} else if (isSelected.equals("0")) {
			textNews.setGovUseFlag(3);
		} else if (isSelected.equals("2")) {
			textNews.setGovUseFlag(0);
		}
		textNews.setIsDel("0");
		// textNews.setDeptName(dept);
		textNewsDao.updateTextNews(textNews);
	}

	/**
	 * 回收站复原
	 * 
	 * @author H2602965
	 */
	@Override
	public void resumeTextNewsFlag(String newsId) {
		// textNewsDao.resumeTextNews(newsId);
		TextNews textNews = textNewsDao.getTextNewsDetail(newsId);
		textNews.setIsDel("0");
		textNews.setFlag("1");
		textNewsDao.updateTextNews(textNews);
	}

	/**
	 * 提供给编辑页面初始化
	 * 
	 * @param textNews
	 * @return
	 */
	@Override
	public TextNews getTextNewsDetail(String newsId) {
		TextNews textNews = textNewsDao.getTextNewsDetail(newsId);
		if(null != textNews){
			if (textNews.getIsOldData() == 1) {
				String content = textNews.getNewsContent();
				content = StringEscapeUtils.unescapeHtml(content);
				textNews.setNewsContent(content);
			}
		}
		return textNews;
	}
	
	public List<TextNews> getTextNewsByStickState(String stickState){
		List<TextNews> textNews = textNewsDao.getTextNewsByStickState(stickState);
		return textNews;
	}
	
	public void updateTextNewsBynewsId(String newsId){
		textNewsDao.updateTextNewsBynewsId(newsId);
		
	}

	@Override
	public List<TextNews> getPhotoNewsList(int topNum) {
		return textNewsDao.getTextNewsList("", "", null, null, "",
				new String[] {"3","8","9"}, new String[] {}, "1", "0", 1, topNum);
	}

	@Override
	public List<TextNews> getTextNewsList(String[] categorys, int topNum) {
		return textNewsDao.getTextNewsList("", "", null, null, "",
				new String[] {}, categorys, "", "0", 1, topNum);
	}
	///
	@Override
	public List<TextNews> getTextNewsListByCatID(String deptName,String category, int topNum) {
		return textNewsDao.getTextNewsListByCatID("", "", null, null, "",
				new String[] {"3"}, new String[]{}, "", "0", 1,deptName,category, topNum);
	}

	/*
	 * @Override public List<TextNews> getTextNewsList(Date startDate, Date
	 * endDate) { return textNewsDao.getTextNewsList("", "", startDate, endDate,
	 * "", new String[]{}, new String[]{}, "", "0", 0, 0); }
	 */
	public List<TextNews> getTextNewsList(Date startDate, Date endDate,
			int pageIndex, int pageSize, String tags) {
		return textNewsDao.getTextNewsList("", "", startDate, endDate, tags,
				new String[] {}, new int[]{}, new String[] {}, "", "0", "", 0, 0, pageIndex,
				pageSize);
	}
	

	@Override
	public List<TextNews> getTextNewsList(Date startDate, Date endDate,
			String[] flags) {
		return textNewsDao.getTextNewsList("", "", null, null, "", flags,
				new String[] {}, "", "0", 0, 0);
	}

	@Override
	public List<TextNews> getTextNewsList(String newsTitle, String entryUser,
			Date startDate, Date endDate, int pageIndex,String tags, int pageSize) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, tags, new String[] {},new int[]{}, new String[] {}, "", "0", "", 0,
				0, pageIndex, pageSize);
	}
	
	

	@Override
	public List<TextNews> getTextNewsList(String newsTitle, String entryUser,
			Date startDate, Date endDate, int pageIndex, String tags,
			String[] flags, int[] govUseFlag, int pageSize) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, tags, flags, govUseFlag, new String[] {}, "", "0", "", 0,
				0, pageIndex, pageSize);
	}
	
	@Override
	public List<TextNews> getTextNewsList(String newsTitle, String entryUser,
			Date startDate, Date endDate, String[] flags) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, "", flags, new String[] {}, "0", "", 0, 0);
	}

	@Override
	public List<TextNews> getSiteNewsList(Date startDate, Date endDate) {
		return textNewsDao.getTextNewsList("", "", startDate, endDate,
				"SiteNews", new String[] {}, new String[] {}, "", "0", 0, 0);
	}

	@Override
	public List<TextNews> getSiteNewsList(Date startDate, Date endDate,
			String[] flags, String isCheckAgain, int pageIndex, int pageSize) {
		return textNewsDao.getTextNewsList("", "", startDate, endDate,
				"SiteNews", flags, new int[]{}, new String[] {}, "", "0", isCheckAgain, 0,
				0, pageIndex, pageSize);
	}

	@Override
	public List<TextNews> getSiteNewsList(String newsTitle, String entryUser,
			Date startDate, Date endDate, int pageIndex, int pageSize) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, "SiteNews", new String[] {}, new int[]{}, new String[] {}, "", "0",
				"", 0, 0, pageIndex, pageSize);
	}

	@Override
	public List<TextNews> getSiteNewsList(String newsTitle, String entryUser,
			Date startDate, Date endDate, String[] flags, String isCheckAgain,
			int pageIndex, int pageSize) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, "SiteNews", flags, new int[]{}, new String[] {}, "", "0",
				isCheckAgain, 0, 0, pageIndex, pageSize);
	}
	@Override
	public List<TextNews> getSiteNewsList(String newsTitle, String entryUser,
			Date startDate, Date endDate, String[] flags,String[] categorys, String isCheckAgain,
			int pageIndex, int pageSize) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, "SiteNews", flags, new int[]{}, categorys, "", "0",
				isCheckAgain, 0, 0, pageIndex, pageSize);
	}

	@Override
	public List<TextNews> getSiteNewsList(Date startDate, Date endDate,
			String[] flags, String[] categorys, int pageIndex, int pageSize) {
		return textNewsDao.getTextNewsList("", "", startDate, endDate,
				"SiteNews", flags, categorys, "", "0", "", "0", 0, 0,
				pageIndex, pageSize);
	}
	
	////////////
	@Override
	public List<TextNews> getSiteNewsList(Date startDate, Date endDate,
			String[] flags, String[] categorys, int pageIndex, int pageSize,String deptName, String categoryId) {
		return textNewsDao.getTextNewsList("", "", startDate, endDate,
				"SiteNews", flags, categorys, "", "0", "", "0", 0, 0,
				pageIndex, pageSize,deptName,categoryId);
	}

	@Override
	public List<TextNews> getSiteNewsRecycleList(String newsTitle,
			String entryUser, Date startDate, Date endDate, int pageIndex,
			int pageSize) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, "SiteNews", new String[] {}, new int[]{}, new String[] {}, "", "1",
				"", 0, 0, pageIndex, pageSize);
	}

	@Override
	public List<TextNews> getGovNewsList(Date startDate, Date endDate) {
		return textNewsDao.getTextNewsList("", "", startDate, endDate,
				"GovNews", new String[] {}, new String[] {}, "", "0", 0, 0);
	}

	@Override
	public List<TextNews> getGovNewsList(Date startDate, Date endDate,
			String[] flags, int pageIndex, int pageSize) {
		return textNewsDao.getTextNewsList("", "", startDate, endDate,
				"GovNews", flags, new int[]{}, new String[] {}, "", "0", "", 0, 0,
				pageIndex, pageSize);
	}

	@Override
	public List<TextNews> getGovNewsList(String newsTitle, String entryUser,
			Date startDate, Date endDate) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, "GovNews", new String[] {}, new String[] {}, "", "0",
				0, 0);
	}

	@Override
	public List<TextNews> getGovNewsList(String newsTitle, String entryUser,
			Date startDate, Date endDate, String[] flags, int pageIndex,
			int pageSize) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, "GovNews", flags, new int[]{}, new String[] {}, "", "0", "", 0, 0,
				pageIndex, pageSize);
	}

	@Override
	public List<TextNews> getTextNewsListByTag(Date startDate, Date endDate,
			String newsTag) {
		return textNewsDao.getTextNewsList("", "", startDate, endDate, newsTag,
				new String[] {}, new String[] {}, "", "0", 0, 0);
	}

	@Override
	public List<TextNews> getTextNewsListByTag(Date startDate, Date endDate,
			String newsTag, String[] flags, int pageIndex, int pageSize) {
		return textNewsDao.getTextNewsList("", "", startDate, endDate, newsTag,
				flags, new int[]{}, new String[] {}, "", "0", "", 0, 0, pageIndex, pageSize);
	}

	@Override
	public List<TextNews> getTextNewsListByTag(String newsTitle,
			String entryUser, Date startDate, Date endDate, String newsTag) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, newsTag, new String[] {}, new String[] {}, "", "0", 0,
				0);
	}

	@Override
	public List<TextNews> getTextNewsListByTag(String newsTitle,
			String entryUser, Date startDate, Date endDate, String newsTag,
			String[] flags, int pageIndex, int pageSize) {
		return textNewsDao.getTextNewsList(newsTitle, entryUser, startDate,
				endDate, newsTag, flags, new int[]{}, new String[] {}, "", "0", "", 0, 0,
				pageIndex, pageSize);
	}

	@Override
	public List<TextNews> getGovNewsRecycleList(String newsTitle,
			String createUser, Date startDate, Date endDate, int pageIndex,
			int pageSize) {
		return textNewsDao.getGovRecycleList(newsTitle, createUser, startDate,
				endDate, pageIndex, pageSize);
	}

	@Override
	public void deleteAll(String newsIds) {
		String[] ids = newsIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			logger.debug(ids[i]);
			TextNews textNews = textNewsDao.getTextNewsDetail(ids[i]);
			textNews.setIsDel("1");
			textNewsDao.updateTextNews(textNews);
		}

	}

	/**
	 * 批量分拣
	 * 
	 * @author H2602965
	 */
	@Override
	public void sortSiteAll(String newsIds) {
		String[] ids = newsIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			logger.debug(ids[i]);
			TextNews textNews = textNewsDao.getTextNewsDetail(ids[i]);
			textNews.setFlag("2");
			textNews.setIsCheckAgain("0");// 设置IsCheckAgain 默认为0
			textNewsDao.updateTextNews(textNews);
		}

	}

	/**
	 * 彻底删除
	 * 
	 * @author H2603282
	 */
	@Override
	public void deleteSiteAll(String newsIds) {
		String[] ids = newsIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			logger.debug(ids[i]);
			TextNews textNews = textNewsDao.getTextNewsDetail(ids[i]);
			textNewsDao.deleteTextNews(textNews);
		}

	}

	/**
	 * 彻底删除
	 * 
	 * @author H2603282
	 */
	@Override
	public void deleteGovAll(String newsIds) {
		String[] ids = newsIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			//如果是新闻信息
			TextNews textNewsList = textNewsDao.getTextNewsDetail(id);
			if(textNewsList != null){
				//删除积分TEXT_DEPT_SCORE 
				deptScoreDao.delDeptScore(id);
				//删除报送TEXT_GOV_INFO
				textGovInfoDao.delGovInfoByNewsId(id);
				//删除原始表TEXT_NEWS
				textNewsDao.deleteTextNews(id);
			} else if(textGovInfoDao.getTextGovInfo(id) != null){//如果是采编信息
				textGovInfoDao.delGovInfoById(id);
			} else {//刊物信息
				textPublicationDao.deleteTextNewsById(id);
			}
			
//			logger.debug(ids[i]);
//			TextNews textNewsList = textNewsDao.getTextNewsDetail(ids[i]);
//			if (null == textNewsList) {
//				logger.debug("不是信息报送中被删除的信息");
//			} else {
//				logger.debug("是信息报送中被删除的信息");
//				textNewsList.setNewsId(ids[i]);
//				textNewsDao.deleteTextNews(textNewsList);
//				;
//			}
//			TextGovInfo govL = textGovInfoDao.getTextGov(ids[i]);
//			if (null == govL) {
//				logger.debug("不是政务信息表中被删除的信息");
//			} else {
//				logger.debug("是政务信息表中被删除的信息");
//				govL.setNewsId(ids[i]);
//				textGovInfoDao.deleteTextNews(govL);
//			}
//			TextPublication textPublication = textPublicationDao.getPub(ids[i]);
//			if (null == textPublication ) {
//				logger.debug("不是刊物中被删除的信息");
//			} else {
//				logger.debug("是刊物中被删除的信息");
//				textPublication.setPubId(ids[i]);
//				textPublicationDao.deleteTextNews(textPublication);
//			}
		}
	}

	/**
	 * 重置GovUseflag
	 * 
	 * @author H2602965
	 */
	@Override
	public void resumeGovFlag(String newsId) {
		// textNewsDao.resumeTextNews(newsId);
		TextNews textNews = textNewsDao.getTextNewsDetail(newsId);
		textNews.setGovUseFlag(1);
		textNewsDao.updateTextNews(textNews);
	}

	/**
	 * 交通信息筛选
	 */
	@Override
	public List<TextNews> getTSiteNewsList(Date startDate, Date endDate,
			String[] flags, int pageIndex, int pageSize) {
		return textNewsDao.getTNewsList("", "", startDate, endDate, "SiteNews",
				flags, new String[] {}, "", "0", 0, 0, pageIndex, pageSize);
	}

	/**
	 * 批量归档
	 * 
	 * @author H2602965
	 */
	@Override
	public void archiveSiteAll(String newsIds) {
		String[] ids = newsIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			logger.debug(ids[i]);
			TextNews textNews = textNewsDao.getTextNewsDetail(ids[i]);
			textNews.setFlag("8");
			textNewsDao.updateTextNews(textNews);
		}
	}

	/*
	 * 信息动态
	 * 
	 * @author zhp
	 */
	@Override
	public List<TextNews> getDynamicSiteNewsList(Date startDate, Date endDate,
			String[] flags, String[] categorys, String[] dept_category,
			int pageIndex, int pageSize) {
		return textNewsDao.getDynamicTextNewsList("", "", startDate, endDate,
				"SiteNews", flags, categorys, dept_category, "", "0", 0, 0,
				pageIndex, pageSize);
	}
	
	@Override
	public void updateNewsSort(String newsSort, String newsId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("newsSort", newsSort);
		param.put("newsId", newsId);
		textNewsDao.updateNewsSort(param);
	}
	
	/**
	 * 导出 excel 查询
	 */
	@Override
	public List<TextNews> getTextNewsListForExport(String newsTitle, String entryUser, Date startDate, Date endDate, String tags,
			String[] flags, int[] govUseFlag) {
		return textNewsDao.getTextNewsListForExport(newsTitle, entryUser, startDate,
				endDate, tags, flags,govUseFlag,new String[] {}, "", "0", 0, 0);
	}
	
	/**
	 * 政务信息报送信息查询导出excel
	 * @param textNewsList
	 * @param response
	 */
	public void exportTextNewsList(List<TextNews> textNewsList, List<CheckStandard> checkStandards, String start, String end, HttpServletResponse response, String tags) {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel");
		String fileName = "政务信息报送查询_" + DateUtil.dateFormat(new Date());
		String finalFileName = "";
		try {
			finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment; filename="+finalFileName+".xls");
		OutputStream output = null;
		WritableWorkbook wwb =null;
		
		try {    
            // 设置单元格的文字格式
            WritableFont font1=new WritableFont(WritableFont.createFont("楷体 _GB2312"),10,WritableFont.NO_BOLD );
     		WritableFont fontHead=new WritableFont(WritableFont.createFont("楷体 _GB2312"),18,WritableFont.BOLD );
     		WritableFont fontTitle=new WritableFont(WritableFont.createFont("楷体 _GB2312"),10,WritableFont.BOLD );
     		WritableCellFormat format1=new WritableCellFormat(font1);
     		WritableCellFormat formatHead=new WritableCellFormat(fontHead);
     		WritableCellFormat formatTitle=new WritableCellFormat(fontTitle);
     		format1.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatHead.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);
			formatHead.setAlignment(jxl.format.Alignment.CENTRE);
			formatHead.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			formatTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			formatTitle.setAlignment(jxl.format.Alignment.CENTRE);//把水平对齐方式指定为居中 
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			formatTitle.setWrap(true);
			output = response.getOutputStream();
            wwb = Workbook.createWorkbook(output);
            
			// 创建一个工作表
			WritableSheet ws = wwb.createSheet("市交委机关", 10); 
			if (( null != start && !"".equals(start) ) 
					&& (null != end && !"".equals(end)) ) {
				ws.addCell(new Label(0, 0, "政务信息报送查询（" + start + "~" + end
						+ "）", formatHead));
			} else {
				ws.addCell(new Label(0, 0, "政务信息报送查询", formatHead));
			}
			ws.mergeCells(0, 0, 19, 2); // 合并单元格
			
			ws.addCell(new Label(0, 3, "序号", formatTitle));
			ws.addCell(new Label(1, 3, "主标题", formatTitle));
			ws.mergeCells(1, 3, 5, 1);
			ws.addCell(new Label(6, 3, "滚动展示", formatTitle));
			ws.addCell(new Label(7, 3, "状态", formatTitle));
			ws.mergeCells(7, 3, 8, 1);
			ws.addCell(new Label(9, 3, "投稿部门", formatTitle));
			ws.mergeCells(9, 3, 10, 1);
			ws.addCell(new Label(11, 3, "投稿时间", formatTitle));
			ws.mergeCells(11, 3, 13, 1);
			ws.addCell(new Label(14, 3, "采用类别", formatTitle));
			ws.mergeCells(14, 3, 17, 1);
			ws.addCell(new Label(18, 3, "投稿人", formatTitle));
			ws.mergeCells(18, 3, 19, 1);
			// 设置行高
			ws.setRowView(1, 600);
			for (int i = 0, j = 1, jnum = 4; i < textNewsList.size(); i++, j++, jnum ++) {
				String strStatus = "";
				TextNews texeNews = textNewsList.get(i);
				String flag = texeNews.getFlag();
				String isPublic = texeNews.getIsPublic();
				int govUseFlag = texeNews.getGovUseFlag();

				if (flag.equals("7")) {
					strStatus = "已退稿";
				} else if (flag.equals("3") && isPublic.equals("0")) {
					strStatus = "归档";
				} else if (flag.equals("3") && isPublic.equals("1")) {
					strStatus = "归档（校编上外网）";
				} else if (flag.equals("2")) {
					strStatus = "分拣";
				} else if (flag.equals("1") && govUseFlag == 1 ) {
					strStatus = "采编已成刊";
				} else if (flag.equals("1") && govUseFlag == 2 ) {
					strStatus = "采编已成刊";
				} else if (flag.equals("1") && govUseFlag == 3 ) {
					strStatus = "采编已成刊";
				} else if (flag.equals("1") && govUseFlag == 0) {
					strStatus = "未采编";
				} else if (flag.equals("0") ) {
					strStatus = "草稿";
				} 
				
				String photosShowStatus = "";
				String isPhotosShow = texeNews.getIsPhotosShow();
				if (null != isPhotosShow && "1".equals(isPhotosShow)) {
					photosShowStatus = "是";
				} else if (null != isPhotosShow && "0".equals(isPhotosShow)) {
					photosShowStatus = "否";
				} else {
					photosShowStatus = "";
				}
				
				
				// 计算采用类别
				String codes = PortalUtils.computeAdoptType(texeNews
						.getAdoptType(), checkStandards);
				texeNews.setAdoptType(codes);
				
				ws.addCell(new Label(0, jnum, String.valueOf(j), format1));
				ws.addCell(new Label(1, jnum, texeNews.getNewsTitle(), format1));
				ws.mergeCells(1, jnum, 5, 1);
				ws.addCell(new Label(6, jnum, photosShowStatus, format1));
				ws.addCell(new Label(7, jnum, strStatus, format1));
				ws.mergeCells(7, jnum, 8, 1);
				ws.addCell(new Label(9, jnum, texeNews.getDeptName(), format1));
				ws.mergeCells(9, jnum, 10, 1);
				ws.addCell(new Label(11, jnum, DateUtil.dateFormat(texeNews.getEntryDate()), format1));
				ws.mergeCells(11, jnum, 13, 1);
				ws.addCell(new Label(14, jnum, texeNews.getAdoptType(), format1));
				ws.mergeCells(14, jnum, 17, 1);
				ws.addCell(new Label(18, jnum, texeNews.getEntryUser(), format1));
				ws.mergeCells(18, jnum, 19, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}catch (WriteException e) {
				e.printStackTrace();
			} 
		}
	}

	@Override
	public void updateTextNewsContetn(TextNews textNews) {
		textNewsDao.updateTextNewsContetn(textNews);
	}

	@Override
	public TextNews getMainOrSubMainTextNews(Map<String, String> map) {
		// TODO Auto-generated method stub
		return textNewsDao.getMainOrSubMainTextNews(map);
	}
	
}
