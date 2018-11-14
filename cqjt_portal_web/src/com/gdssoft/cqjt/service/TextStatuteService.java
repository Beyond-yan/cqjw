package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.TextStatute;

public interface TextStatuteService {
	
	
	List<TextStatute> getStatutelistView(Date startDate, Date endDate,
			int pageIndex, int pageSize);
	
	List<TextStatute> getStatutelistView(String newsTitle, String entryUser, Date startDate, Date endDate, int pageIndex,
			 int pageSize);
	
	List<TextStatute> getStatutelistView(String newsTitle, String entryUser, Date startDate, Date endDate, int pageIndex,
			String categoryId, String[] flags, int[] govUseFlag, int pageSize);
	
	List<TextStatute> queryStatutelistView(String newsTitle, String entryUser, Date startDate, Date endDate, int pageIndex,
			String categoryId, String[] flags, int[] govUseFlag, int pageSize, String SUB_CATEGORY,
			String SUB_CATEGORY_INFO);

	void save(TextStatute textStatute);

	void update(TextStatute textStatute);

	void delete(String statuteId);

	TextStatute getTextStatuteDetail(String statuteId);

}
