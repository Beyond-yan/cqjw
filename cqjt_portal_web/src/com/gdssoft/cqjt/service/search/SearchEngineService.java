package com.gdssoft.cqjt.service.search;

import java.io.IOException;
import java.util.List;

import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.search.InnerSiteSearch;
import com.gdssoft.cqjt.pojo.search.PublicArchivesSearch;

public interface SearchEngineService {
	//内网全网站全文搜索
	public List<InnerSiteSearch> getInnerSiteSearchInfo(String keyStr,int pageIndex,int pageSize);
	//报送查询（暂时没用）
	public List<TextNews> getTextNewsSearchInfo(String title,String entryUser,String startD,String endD);
	//查询公开公文
	public List<PublicArchivesSearch> getArchivesSearchInfo(String keyStr,int page,int pageSize);
	//默认查询公开公文
	public List<PublicArchivesSearch> getArchivesSearchInfo(int page,int pageSize);
	//查询内网交通信息:6，交通课堂:5，交通视频:3
	public List<InnerSiteSearch> getInnerTrafficSearchInfo(String keyStr,String type,int pageIndex,int pageSize);
	//默认查询公开公文限制OA信息
	public List<PublicArchivesSearch> getArchivesSearchInfoBySchema(String schema,int page,int pageSize);
	
	
	//http公用方法
	public String call(String url) throws IOException;

}
