package com.gdssoft.cqjt.service;

import com.gdssoft.cqjt.pojo.LogCount;

public interface LogCountService {
	
	void insert(LogCount logCount);
	
	LogCount  getCount();

}
