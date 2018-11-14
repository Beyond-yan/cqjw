package com.gdssoft.core.tools;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.gdssoft.cqjt.service.search.SearchEngineService;


public class UpdateSearchEngineIndexJob {

	@Autowired
	@Resource(name = "searchEngineServiceImpl")
	private SearchEngineService searchEngine ;
	
	@Value("${solr.url}")
	private String solrUrl ;
	protected Log log = LogFactory.getLog(getClass());

	public void updateIndexOfInnerSiteNews() {
		log.debug("solrUrl的值："+solrUrl);
		//全量索引
	/*	String url = solrUrl
				+ "JW_INNER_NEWS/dataimport?command=full-import&entity=innerNet-search"
				+ "&commit=true&optimize=true&wt=xml&indent=true&verbose=false"
				+ "&clean=false&debug=false";*/
		//增量索引，若用此种索引需要修改solr中该数据源conf文件夹下db-data-config.xml文件
		
		String url = solrUrl
				+ "JW_INNER_NEWS/dataimport?command=delta-import&entity=innerNet-search"
				+ "&commit=true&optimize=true&wt=xml&indent=true&verbose=false"
				+ "&clean=false&debug=false";
		
		try {
			String res = searchEngine.call(url);
			log.info("执行了增量索引"+res);
		} catch (IOException e) {
			log.error("更新网站信息增量索引发生IO异常",e);
		}
	}
	
	
	
}
