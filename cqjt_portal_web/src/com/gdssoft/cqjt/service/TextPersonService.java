package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.TextPerson;

public interface TextPersonService {
	
	
	List<TextPerson> getPersonlistView(Date startDate, Date endDate,
			int pageIndex, int pageSize);
	
	List<TextPerson> getPersonlistView(String newsTitle, String entryUser, Date startDate, Date endDate, int pageIndex,
			 int pageSize);
	
	List<TextPerson> getPersonlistView(String newsTitle, String entryUser, Date startDate, Date endDate, int pageIndex,
			String categoryId, String[] flags, int[] govUseFlag, int pageSize);
	
	List<TextPerson> queryPersonlistView(String newsTitle, String entryUser, Date startDate, Date endDate, int pageIndex,
			String categoryId, String[] flags, int[] govUseFlag, int pageSize, String SUB_CATEGORY);

	void save(TextPerson textPerson);

	void update(TextPerson textPerson);

	void delete(String PersonId);

	TextPerson getTextPersonDetail(String PersonId);

}
