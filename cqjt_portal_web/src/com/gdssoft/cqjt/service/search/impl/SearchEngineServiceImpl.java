package com.gdssoft.cqjt.service.search.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.search.InnerSiteSearch;
import com.gdssoft.cqjt.pojo.search.PublicArchivesSearch;
import com.gdssoft.cqjt.service.search.SearchEngineService;

@Service("searchEngineServiceImpl")
public class SearchEngineServiceImpl implements SearchEngineService {
	
//	public static final String HOST = "http://localhost:8080/cq_search";
    public static final String HOST = "http://10.224.5.183:8080/cq_search";
	public static final String CORE = "JW_INNER_NEWS";
	public static final String CORETEXT = "JW_TEXT_NEWS";
	public static final String COREARCHIVES = "JW_PUBLIC_ARCHIVES";
	public static final String CORETRAFFIC = "JW_INNER_TRAFFIC";
	public static final String QT = "select";
	public static final String HEADER = HOST + "/" + CORE + "/" + QT;
	public static final String HEADERTEXT = HOST + "/" + CORETEXT + "/" + QT;
	public static final String HEADERARCHIVES = HOST + "/" + COREARCHIVES + "/" + QT;
	public static final String HEADERTRAFFIC = HOST + "/" + CORETRAFFIC + "/" + QT;
	public static String EMPTY_RESULT = "[]";
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 内网首页网站搜索分页查询
	 * @author H2602975
	 * @param keyStr
	 */	
	@Override
	public List<InnerSiteSearch> getInnerSiteSearchInfo(String keyStr,int pageIndex,int pageSize) {
		String url = HEADER + "?" + getParamForQueryALL(keyStr);
		// url中设定的wt=json 指定结果为json。start与rows用于分页
        int start = pageIndex*pageSize;
		url += "&sort=entry_date+desc&start="+start+"&rows="+pageSize+"&wt=json&indent=true";
		
		String result = null;
		try {
			result = call(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject all = JSONObject.fromObject(result);

		// 解析一般下获取response节点
		JSONObject res = all.getJSONObject("response");
		// 结果一般在docs节点下，但是response节点下也有其它有用的节点，比如可以获取结果的总数
		int total = res.getInt("numFound");
		
		logger.debug("获得的总数是："+total);
		// 获取最终结果
		JSONArray docs = res.getJSONArray("docs");
		List<InnerSiteSearch> resultList = tranFromJSONObject(docs);
		if(resultList.size()!=0){
			resultList.get(0).setPageIndex(pageIndex);
			resultList.get(0).setTotals(total);
			resultList.get(0).setPageSize(pageSize);
		}
		return resultList;
	}
	
	

	@Override
	public List<TextNews> getTextNewsSearchInfo(String title, String entryUser,
			String startD, String endD) {
		String url = HEADER + "?" + getParamForQueryALL(title, entryUser, startD,endD);
	
		url += "&sort=entry_date+desc&start=0&rows=600&wt=json&indent=true";
		
		String result = null;
		try {
			result = call(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject all = JSONObject.fromObject(result);
		// 解析一般下获取response节点
		JSONObject res = all.getJSONObject("response");
		// 结果一般在docs节点下，但是response节点下也有其它有用的节点，比如可以获取结果的总数
		int total = res.getInt("numFound");
		
		logger.debug("获得的总数是："+total);
		// 获取最终结果
		JSONArray docs = res.getJSONArray("docs");
		List<TextNews> textNewsList = tranFromTextNewsJSONObject(docs);
		return textNewsList;
	}


	private static List<InnerSiteSearch> tranFromJSONObject(JSONArray array) {
		List<InnerSiteSearch> siteInfos = new ArrayList<InnerSiteSearch>();

		for (int i = 0; i < array.size(); i++) {
			InnerSiteSearch siteInfo = new InnerSiteSearch();
			try {
				JSONObject json = array.getJSONObject(i);
				siteInfo.setNews_id(getValueByName(json, "news_id"));
				siteInfo.setNews_title(getValueByName(json, "news_title"));
				siteInfo.setDept_name(getValueByName(json, "dept_name"));
				siteInfo.setNews_content(getValueByName(json, "news_content"));
				siteInfo.setEntry_user(getValueByName(json, "entry_user"));
				siteInfo.setEntry_date(getValueByName(json, "entry_date"));
				siteInfo.setNews_flag(getValueByName(json, "news_flag"));
				siteInfo.setPath(getValueByName(json, "path"));
				siteInfo.setAttachment(getValueByName(json, "attachment"));
				siteInfos.add(siteInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return siteInfos;
	}
	
	private static List<TextNews> tranFromTextNewsJSONObject(JSONArray array) {
		List<TextNews> textNewsList = new ArrayList<TextNews>();

		for (int i = 0; i < array.size(); i++) {
			TextNews textNews = new TextNews();
			try {
				JSONObject json = array.getJSONObject(i);
				textNews.setNewsId(getValueByName(json, "news_id"));
				textNews.setNewsTitle(getValueByName(json, "news_title"));
				textNews.setDeptName(getValueByName(json, "dept_name"));
				textNews.setNewsContent(getValueByName(json, "news_content"));
				textNews.setEntryUser(getValueByName(json, "entry_user"));
				//textNews.setEntryDate(getValueByName(json, "entry_date"));
				
				textNewsList.add(textNews);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return textNewsList;
	}
	
	private static String getParamForQueryAll() {
		return "q=*:*";
	}
	
	@SuppressWarnings("unused")
	private String getParamForQuerySchema(String schema) {
		StringBuffer param = new StringBuffer();
		param.append("q=");
		param.append("schema_code:");
		param.append(schema);
		return param.toString();
	}
	private String getParamForQueryALL(String keyStr) {
		StringBuffer param = new StringBuffer();
		param.append("q=");
		param.append("text:");
		try {
			param.append(java.net.URLEncoder.encode(keyStr.toString(),
					"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return param.toString();
	}
	
	private String getParamForQueryALL(String keyStr,String type) {
		StringBuffer param = new StringBuffer();
		param.append("q=");
		if(keyStr==null||keyStr.equals("")){
			param.append("news_flag:");
			param.append(type.toString());
		}else{
			param.append("text:");
			try {
				param.append(java.net.URLEncoder.encode(keyStr.toString(),
						"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			param.append("+AND+");
			param.append("news_flag:");
			param.append(type.toString());
		}
		return param.toString();
	}
	
	private String getParamForQueryALL(String title, String entryUser,
			String startD, String endD) {
		StringBuffer param = new StringBuffer();
		param.append("q=");
		try {
				if(title!=null){
					param.append("news_title:");
					param.append(java.net.URLEncoder.encode(title.toString(), 
								"utf-8"));
				}
				if(entryUser!=null){
					param.append("entry_user:");
					param.append(java.net.URLEncoder.encode(entryUser.toString(), 
								"utf-8"));
				}
				if(startD!=null){
					startD+="T00:00:00Z";
					if(endD!=null){
						endD = "*";
					}else{
						endD+="T00:00:00Z";
					}
					param.append("entry_date:["); 
					param.append(java.net.URLEncoder.encode(startD.toString()+" TO "+endD.toString(), 
								"utf-8"));
				}
				
		 } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
	     }

		return param.toString();
	}

	

	@SuppressWarnings("unused")
	private String getParamForQuerySubjectAndDocnum(String subject,
			String docnum) {
		StringBuffer param = new StringBuffer();
		param.append("q=");
		param.append("subject:");
		param.append(subject);
		param.append("+AND+");
		param.append("docnum:");
		param.append(docnum);
		return param.toString();
	}
	@Override
	public String call(String url) throws IOException {

		// 創建一個鏈接遠端服務的客戶端
		HttpURLConnection httpurlconnection = null;
		// String temourl=java.net.URLEncoder.encode(url.toString(),
		// "ISO-8859-1");
		// URL my_url = new URL(temourl);
		URL my_url = new URL(url);
		httpurlconnection = (HttpURLConnection) my_url.openConnection();
		httpurlconnection.setDoOutput(true);
		httpurlconnection.setRequestMethod("GET");
		httpurlconnection.setRequestProperty("Content-type",
				"text/xml; charset=UTF-8");
		// 接受結果
		BufferedReader br = new BufferedReader(new InputStreamReader(
				httpurlconnection.getInputStream(), "UTF-8"));
		StringBuilder stringBulider = new StringBuilder();
		String linerep = null;
		while ((linerep = br.readLine()) != null) {
			stringBulider.append(linerep);
		}
		br.close();
		br = null;
		return stringBulider.toString();
	}


	// 獲取屬性的值

	public String getValueByName(String jString, String pName) {

		try {
			JSONObject jsonObject = JSONObject.fromObject(jString);
			return jsonObject.getString(pName);
		}
		// 處理異常
		catch (Exception e) {
			return "";
		}
	}

	// 獲取屬性的值
	public static String getValueByName(JSONObject json, String pName) {
		String result = "";
		try {
			if (json != null) {
				result = json.getString(pName);
			}

		}
		// 處理異常
		catch (Exception e) {
			result = "";
		}
		return result;
		
	}

	public JSONObject parseJSONObject(String jString) {
		JSONObject result = null;
		try {
			result = JSONObject.fromObject(jString);

		}
		// 處理異常
		catch (Exception e) {

		}
		return result;
	}



	@Override
	public List<PublicArchivesSearch> getArchivesSearchInfo(String keyStr,int pageIndex,int pageSize) {
		String url = HEADERARCHIVES + "?" + getParamForQueryALL(keyStr);
		// url中设定的wt=json 指定结果为json。start与rows用于分页
	/*	url += "&start=" + fromIndex + "&rows=" + (toIndex - fromIndex)
				+ "&wt=json&indent=true";*/
		int start = pageIndex*pageSize;
		url += "&sort=createtime+desc&start="+start+"&rows="+pageSize+"&wt=json&indent=true"; 
		
		String result = null;
		try {
			result = call(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject all = JSONObject.fromObject(result);

		// 解析一般下获取response节点
		JSONObject res = all.getJSONObject("response");
		// 结果一般在docs节点下，但是response节点下也有其它有用的节点，比如可以获取结果的总数
		int total = res.getInt("numFound");
		
		logger.debug("获得的总数是："+total);
		// 获取最终结果
		JSONArray docs = res.getJSONArray("docs");
		List<PublicArchivesSearch> resultList = tranFromArchivesJSONObject(docs);
		if(resultList.size()!=0){
			resultList.get(0).setPageIndex(pageIndex);
			resultList.get(0).setTotals(total);
			resultList.get(0).setPageSize(pageSize);
		}
		return resultList;
	}



	@Override
	public List<PublicArchivesSearch> getArchivesSearchInfo(int pageIndex,int pageSize) {
		String url = HEADERARCHIVES + "?" + getParamForQueryAll();
		// url中设定的wt=json 指定结果为json。start与rows用于分页
	/*	url += "&start=" + fromIndex + "&rows=" + (toIndex - fromIndex)
				+ "&wt=json&indent=true";*/
	    int start = pageIndex*pageSize;
		url += "&sort=createtime+desc&start="+start+"&rows="+pageSize+"&wt=json&indent=true";
		
		String result = null;
		try {
			result = call(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject all = JSONObject.fromObject(result);

		// 解析一般下获取response节点
		JSONObject res = all.getJSONObject("response");
		// 结果一般在docs节点下，但是response节点下也有其它有用的节点，比如可以获取结果的总数
		int total = res.getInt("numFound");
		
		logger.debug("获得的总数是："+total);
		// 获取最终结果
		JSONArray docs = res.getJSONArray("docs");
		List<PublicArchivesSearch> resultList = tranFromArchivesJSONObject(docs);
		if(resultList.size()!=0){
			resultList.get(0).setPageIndex(pageIndex);
			resultList.get(0).setTotals(total);
			resultList.get(0).setPageSize(pageSize);
		}
		return resultList;
	}

	private static List<PublicArchivesSearch> tranFromArchivesJSONObject(JSONArray array) {
		List<PublicArchivesSearch> archives = new ArrayList<PublicArchivesSearch>();

		for (int i = 0; i < array.size(); i++) {
			PublicArchivesSearch archive = new PublicArchivesSearch();
			try {
				JSONObject json = array.getJSONObject(i);
				archive.setArchivesid(getValueByName(json, "archivesid"));
				archive.setIssuer(getValueByName(json, "issuer"));
				archive.setCreatetime(getValueByName(json, "createtime"));
				archive.setSubject(getValueByName(json, "subject"));
				archive.setSchema_code(getValueByName(json, "schema_code"));
				archives.add(archive);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return archives;
	}



	@Override
	public List<InnerSiteSearch> getInnerTrafficSearchInfo(String keyStr,String type,
			int pageIndex, int pageSize) {
		String url = HEADERTRAFFIC + "?" + getParamForQueryALL(keyStr,type);
		// url中设定的wt=json 指定结果为json。start与rows用于分页
        int start = pageIndex*pageSize;
		
        url += "&sort=entry_date+desc&start="+start+"&rows="+pageSize+"&wt=json&indent=true";
        
		String result = null;
		try {
			result = call(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject all = JSONObject.fromObject(result);

		// 解析一般下获取response节点
		JSONObject res = all.getJSONObject("response");
		// 结果一般在docs节点下，但是response节点下也有其它有用的节点，比如可以获取结果的总数
		int total = res.getInt("numFound");
		logger.debug("获得的总数是："+total);
		// 获取最终结果
		JSONArray docs = res.getJSONArray("docs");
		List<InnerSiteSearch> resultList = tranFromJSONObject(docs);
		if(resultList.size()!=0){
			resultList.get(0).setPageIndex(pageIndex);
			resultList.get(0).setTotals(total);
			resultList.get(0).setPageSize(pageSize);
		}
		return resultList;
//		return null;
	}



	@Override
	public List<PublicArchivesSearch> getArchivesSearchInfoBySchema(String schema,
			int pageIndex, int pageSize) {
		String url = HEADERARCHIVES + "?" +"q=schema_code:oa";
		// url中设定的wt=json 指定结果为json。start与rows用于分页
	/*	url += "&start=" + fromIndex + "&rows=" + (toIndex - fromIndex)
				+ "&wt=json&indent=true";*/
		
		int start = pageIndex*pageSize;
		url += "&sort=createtime+desc&start="+start+"&rows="+pageSize+"&wt=json&indent=true"; 
		
		String result = null;
		try {
			result = call(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<PublicArchivesSearch> resultList = new ArrayList<PublicArchivesSearch>();
		if(result != null){
			JSONObject all = JSONObject.fromObject(result);

			// 解析一般下获取response节点
			JSONObject res = all.getJSONObject("response");
			// 结果一般在docs节点下，但是response节点下也有其它有用的节点，比如可以获取结果的总数
			int total = res.getInt("numFound");
			logger.debug("获得的总数是："+total);
			// 获取最终结果
			JSONArray docs = res.getJSONArray("docs");
			resultList = tranFromArchivesJSONObject(docs);
			if(resultList.size()!=0){
				resultList.get(0).setPageIndex(pageIndex);
				resultList.get(0).setTotals(total);
				resultList.get(0).setPageSize(pageSize);
			}
		}
		
		
		return resultList;
	}
	
}
