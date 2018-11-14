package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;
 
import com.gdssoft.cqjt.pojo.TextPublication;

public interface TextPublicationService {
	
	public TextPublication getPubDetail(String pubId);
	public void save(TextPublication textPublication);
	public void update(TextPublication textPublication);
	public void delete(TextPublication textPublication);
	
	//刊物信息查询
	public List<TextPublication> queryPubInfoList(String pubName, String createUser,
			Date createDateS, Date createDateE,int pageIndex, int pageSize);
	//刊物信息查询
		public List<TextPublication> queryPubInfoList(String pubName, String createUser,
				Date createDateS, Date createDateE);
	public TextPublication getPubList(String pubId);
	public List<TextPublication> getPubInfoList(String pubName, String createUser,
			Date createDateS, Date createDateE, int pageIndex, int pageSize);
	//首页显示已发布信息
	public List<TextPublication> getPub(String pubName, String createUser,
			Date createDateS, Date createDateE, int pageIndex, int pageSize);
}
