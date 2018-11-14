package com.gdssoft.cqjt.service;

import java.util.List;
import com.gdssoft.cqjt.pojo.ListItem;

/**
 * @author Jimxu
 */
public interface OAService {

	List<ListItem> getTodoList(String userNo, int count);

	String getPublicArchives(int count);
	
	String getPublicArchivesDetail(String archivesId,String schema);
	
	String getUnReceiveArchives(String userNo, int count);

	String getUnReceiveArchivesCount(String userNo);

}
