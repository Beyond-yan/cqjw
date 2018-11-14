package com.gdssoft.cqjt.service.impl;

/**
 * 刊物信息实现类
 * @author  zhangpeng 20140604
 * @return
 */

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.TextGovInfoDao;
import com.gdssoft.cqjt.dao.TextPublicationDao;
import com.gdssoft.cqjt.pojo.TextPublication;
import com.gdssoft.cqjt.service.TextPublicationService;

@Service("textPublicationServiceImpl")
public class TextPublicationServiceImpl implements TextPublicationService {

	@Autowired
	@Resource(name = "textPublicationDao")
	private TextPublicationDao textPublicationDao;

	@Autowired
	@Resource(name = "textGovInfoDao")
	private TextGovInfoDao textGovInfoDao;

	@Override
	public TextPublication getPubDetail(String pubId) {

		TextPublication textPublication = textPublicationDao
				.getPubDetail(pubId);

		textPublication.setWorkDynamics(textGovInfoDao.getGovListByIdAndType(
				pubId, "workDynamic"));
		textPublication.setCountyDynamics(textGovInfoDao.getGovListByIdAndType(
				pubId, "countyDynamic"));
		textPublication.setOneInfos(textGovInfoDao.getGovListByIdAndType(pubId,
				"oneInfo"));
		textPublication.setNetInfos(textGovInfoDao.getGovListByIdAndType(pubId,
				"netInfo"));

		return textPublication;

	}

	@Override
	public void save(TextPublication textPublication) {
		textPublicationDao.save(textPublication);

	}

	@Override
	public void update(TextPublication textPublication) {
		textPublicationDao.update(textPublication);
	}

	@Override
	public void delete(TextPublication textPublication) {
		textPublicationDao.delete(textPublication);
	}

	/**
	 * queryPubInfoList 政务信息刊物上报管理，政务信息上报管理
	 */
	@Override
	public List<TextPublication> queryPubInfoList(String pubName,
			String createUser, Date createDateS, Date createDateE,
			int pageIndex, int pageSize) {
		return textPublicationDao.queryPubInfoList("", pubName, createUser,
				createDateS, createDateE, pageIndex, pageSize);
	}

	@Override
	public List<TextPublication> queryPubInfoList(String pubName,
			String createUser, Date createDateS, Date createDateE) {
		return textPublicationDao.queryPubInfoList(pubName, createUser,
				createDateS, createDateE);
	}

	/*
	 * @Override public List<TextPublication> getPublicationList() { return
	 * textPublicationDao.getTextPublication(); }
	 */

	@Override
	public TextPublication getPubList(String pubId) {
		return textPublicationDao.getPubList(pubId);
	}

	@Override
	public List<TextPublication> getPubInfoList(String pubName,
			String createUser, Date createDateS, Date createDateE,
			int pageIndex, int pageSize) {
		return textPublicationDao.queryPubInfoList("", pubName, createUser,
				createDateS, createDateE, pageIndex, pageSize);
	}

	/**
	 * 首页每日信息显示， 每日信息显示详情左侧边栏
	 */

	@Override
	public List<TextPublication> getPub(String pubName, String createUser,
			Date createDateS, Date createDateE, int pageIndex, int pageSize) {
		return textPublicationDao.queryPubInfoList("1", pubName, createUser,
				createDateS, createDateE, pageIndex, pageSize);
	}
}
