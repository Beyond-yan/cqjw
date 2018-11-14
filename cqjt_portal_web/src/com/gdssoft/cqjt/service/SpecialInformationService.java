package com.gdssoft.cqjt.service;

import java.util.List;
import java.util.Map;

import com.gdssoft.cqjt.pojo.TextGovInfo;

public interface SpecialInformationService extends BaseService{

	public List<TextGovInfo> getGovNewsList(Map<String, Object> map, int pageIndex, int pageSize) ;
}
