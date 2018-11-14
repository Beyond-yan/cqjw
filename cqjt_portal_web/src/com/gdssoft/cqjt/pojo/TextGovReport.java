package com.gdssoft.cqjt.pojo;
/**
 * 政务信息报表查询类   20140630  gyf
 */

import com.google.gson.annotations.Expose;

public class TextGovReport extends BasePojo {
	/*
	 * 分类
	 */
	@Expose
	private String deptCategory;
	/*
	 * 部门名称
	 */
	@Expose
	private String deptName;
	/*
	 * 上报数量
	 */
	@Expose
	private Long shangb;
	/*
	 * 上报得分
	 */
	@Expose
	private Float shangbScore;
	/*
	 * 采用数量
	 */
	@Expose
	private Long caiy;
	/*
	 * 每日信息数量
	 */
	@Expose
	private Long daily;
	/*
	 * 每日信息得分
	 */
	@Expose
	private Float dailyNum;
	/*
	 *专题信息数量
	 */
	@Expose
	private Long subject;
	/*
	 *约稿数量
	 */
	@Expose
	private Long yuegao;
	
	 /*
	  *约稿数量得分
	 */
	@Expose
	private Float yuegaoNum;
	/*
	 * 专题信息得分
	 */
	@Expose
	private Float subjectNum;
	/*
	 * 交通信息数量
	 */
	@Expose
	private Long traffic;
	/*
	 * 交通信息得分
	 */
	@Expose
	private Float trafficNum;
	/*
	 * 市委数量
	 */
	@Expose
	private Long cityCom;
	/*
	 * 市委得分
	 */
	@Expose
	private Float cityComNum;
	/*
	 * 市府数量
	 */
	@Expose
	private Long cityGov;
	/*
	 * 市府得分
	 */
	@Expose
	private Float cityGovNum;
	/*
	 * 交委批示数量
	 */
	@Expose
	private Long comLeader;
	/*
	 * 交委批示得分
	 */
	@Expose
	private Float comLeaderNum;
	/*
	 * 市府批示数量
	 */
	@Expose
	private Long govLeader;
	/*
	 * 市委批示数量
	 */
	@Expose
	private Long govComLeader;
	/*
	 * 市府批示得分
	 */
	@Expose
	private Float govLeaderNum;
	/*
	 * 市委批示得分
	 */
	@Expose
	private Float govComLeaderNum;
	/*
	 * 交通部批示数量
	 */
	@Expose
	private Long trafLeader;
	/*
	 * 交通部批示得分
	 */
	@Expose
	private Float trafLeaderNum;
	/*
	 * 上级其他领导批示数量
	 */
	@Expose
	private Long otherLeader;
	/*
	 * 上级其他领导批示得分
	 */
	@Expose
	private Float otherLeaderNum;
	/*
	 * 交通部简报情况交流
	 */
	@Expose
	private Long trafsitcomm;
	/*
	 * 交通部简报情况交流得分
	 */
	@Expose
	private Float trafsitcommNum;
	/*
	 * 交通部简报日报
	 */
	@Expose
	private Long trafdaypa;
	/*
	 * 交通部简报日报得分
	 */
	@Expose
	private Float trafdaypaNum;
	/*
	 * 交委独编专报信息
	 */
	@Expose
	private Long jwdbsubjectInfo;
	/*
	 * 交委独编专报信息得分
	 */
	@Expose
	private Float jwdbsubjectInfoNum;
	/*
	 * 交委综合专题信息
	 */
	@Expose
	private Long jwzhsubjectInfo;
	/*
	 * 交委综合专题信息得分
	 */
	@Expose
	private Float jwzhsubjectInfoNum;
	/*
	 * 刊物工作动态
	 */
	@Expose
	private Long pubtypedynamic;
	/*
	 * 刊物工作动态得分
	 */
	@Expose
	private Float pubtypedynamicNum;
	/*
	 * 刊物一句话信息
	 */
	@Expose
	private Long pubTypewordinfo;
	/*
	 * 刊物一句话信息得分
	 */
	@Expose
	private Float pubTypewordinfoNum;
	/*
	 * 刊物区县动态信息
	 */
	@Expose
	private Long countyDynamic;
	/*
	 * 刊物区县动态得分
	 */
	@Expose
	private Float countyDynamicNum;
	/*
	 * 刊物网络信息
	 */
	@Expose
	private Long netInfo;
	/*
	 * 刊物网络信息得分
	 */
	@Expose
	private Float netInfoNum;
	/*
	 * 上级独编
	 */
	@Expose
	private Long otherdbsubjectInfo;
	/*
	 * 上级独编得分
	 */
	@Expose
	private Float otherdbsubjectInfoNum;
	/*
	 * 上级综合
	 */
	@Expose
	private Long otherzhsubjectInfo;
	/*
	 * 上级综合得分
	 */
	@Expose
	private Float otherzhsubjectInfoNum;
	/*
	 * 市府独编专题信息
	 */
	@Expose
	private Long sfdbsubjectInfo;
	/*
	 * 市府独编专题信息得分
	 */
	@Expose
	private Float sfdbsubjectInfoNum;
	/*
	 * 市府工作动态
	 */
	@Expose
	private Long workdynamic;
	/*
	 * 市府工作动态得分
	 */
	@Expose
	private Float workdynamicNum;
	/*
	 * 市府一句话信息
	 */
	@Expose
	private Long sfwordinfo;
	/*
	 * 市府一句话信息得分
	 */
	@Expose
	private Float sfwordinfoNum;
	/*
	 * 市府综合专题信息
	 */
	@Expose
	private Long sfzhsubjectInfo;
	/*
	 * 市府综合专题信息得分
	 */
	@Expose
	private Float sfzhsubjectInfoNum;
	/*
	 * 市委独编专题信息
	 */
	@Expose
	private Long swdbsubjectInfo;
	/*
	 * 市委独编专题信息得分
	 */
	@Expose
	private Float swdbsubjectInfoNum;
	/*
	 * 市委每日要情
	 */
	@Expose
	private Long swdayinfo;
	/*
	 * 市委每日要情得分
	 */
	@Expose
	private Float swdayinfoNum;
	/*
	 * 市委综合专题信息
	 */
	@Expose
	private Long swzhsubjectInfo;
	/*
	 * 市委综合专题信息得分
	 */
	@Expose
	private Float swzhsubjectInfoNum;
	/*
	 * 一把手手机报
	 */
	@Expose
	private Long headphonepa;
	/*
	 * 一把手手机报得分
	 */
	@Expose
	private Float headphonepaNum;

	/*
	 * 当月得分
	 */
	@Expose
	private Float total ;
	/**
	 * 当年得分
	 */
	@Expose
	private Float totalYear ;
	
	
	public Float getTotalYear() {
		return totalYear;
	}
	public void setTotalYear(Float totalYear) {
		this.totalYear = totalYear;
	}
	public String getDeptCategory() {
		return deptCategory;
	}
	public void setDeptCategory(String deptCategory) {
		this.deptCategory = deptCategory;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Long getShangb() {
		return shangb;
	}
	public void setShangb(Long shangb) {
		this.shangb = shangb;
	}
	public Float getShangbScore() {
		return shangbScore;
	}
	public void setShangbScore(Float shangbScore) {
		this.shangbScore = shangbScore;
	}
	public Long getCaiy() {
		return caiy;
	}
	public void setCaiy(Long caiy) {
		this.caiy = caiy;
	}
	public Long getDaily() {
		return daily;
	}
	public void setDaily(Long daily) {
		this.daily = daily;
	}
	public Float getDailyNum() {
		return dailyNum;
	}
	public void setDailyNum(Float dailyNum) {
		this.dailyNum = dailyNum;
	}
	public Long getSubject() {
		return subject;
	}
	public void setSubject(Long subject) {
		this.subject = subject;
	}
	public Float getSubjectNum() {
		return subjectNum;
	}
	public void setSubjectNum(Float subjectNum) {
		this.subjectNum = subjectNum;
	}
	public Long getTraffic() {
		return traffic;
	}
	public void setTraffic(Long traffic) {
		this.traffic = traffic;
	}
	public Float getTrafficNum() {
		return trafficNum;
	}
	public void setTrafficNum(Float trafficNum) {
		this.trafficNum = trafficNum;
	}
	public Long getCityCom() {
		return cityCom;
	}
	public void setCityCom(Long cityCom) {
		this.cityCom = cityCom;
	}
	public Float getCityComNum() {
		return cityComNum;
	}
	public void setCityComNum(Float cityComNum) {
		this.cityComNum = cityComNum;
	}
	public Long getCityGov() {
		return cityGov;
	}
	public void setCityGov(Long cityGov) {
		this.cityGov = cityGov;
	}
	public Float getCityGovNum() {
		return cityGovNum;
	}
	public void setCityGovNum(Float cityGovNum) {
		this.cityGovNum = cityGovNum;
	}
	public Long getComLeader() {
		return comLeader;
	}
	public void setComLeader(Long comLeader) {
		this.comLeader = comLeader;
	}
	public Float getComLeaderNum() {
		return comLeaderNum;
	}
	public void setComLeaderNum(Float comLeaderNum) {
		this.comLeaderNum = comLeaderNum;
	}
	public Long getGovLeader() {
		return govLeader;
	}
	public void setGovLeader(Long govLeader) {
		this.govLeader = govLeader;
	}
	public Float getGovLeaderNum() {
		return govLeaderNum;
	}
	public void setGovLeaderNum(Float govLeaderNum) {
		this.govLeaderNum = govLeaderNum;
	}
	public Long getTrafLeader() {
		return trafLeader;
	}
	public void setTrafLeader(Long trafLeader) {
		this.trafLeader = trafLeader;
	}
	public Float getTrafLeaderNum() {
		return trafLeaderNum;
	}
	public void setTrafLeaderNum(Float trafLeaderNum) {
		this.trafLeaderNum = trafLeaderNum;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Long getOtherLeader() {
		return otherLeader;
	}
	public void setOtherLeader(Long otherLeader) {
		this.otherLeader = otherLeader;
	}
	public Long getTrafsitcomm() {
		return trafsitcomm;
	}
	public void setTrafsitcomm(Long trafsitcomm) {
		this.trafsitcomm = trafsitcomm;
	}
	public Long getTrafdaypa() {
		return trafdaypa;
	}
	public void setTrafdaypa(Long trafdaypa) {
		this.trafdaypa = trafdaypa;
	}
	public Long getJwdbsubjectInfo() {
		return jwdbsubjectInfo;
	}
	public void setJwdbsubjectInfo(Long jwdbsubjectInfo) {
		this.jwdbsubjectInfo = jwdbsubjectInfo;
	}
	public Long getJwzhsubjectInfo() {
		return jwzhsubjectInfo;
	}
	public void setJwzhsubjectInfo(Long jwzhsubjectInfo) {
		this.jwzhsubjectInfo = jwzhsubjectInfo;
	}
	public Long getPubtypedynamic() {
		return pubtypedynamic;
	}
	public void setPubtypedynamic(Long pubtypedynamic) {
		this.pubtypedynamic = pubtypedynamic;
	}
	public Long getPubTypewordinfo() {
		return pubTypewordinfo;
	}
	public void setPubTypewordinfo(Long pubTypewordinfo) {
		this.pubTypewordinfo = pubTypewordinfo;
	}
	public Long getOtherdbsubjectInfo() {
		return otherdbsubjectInfo;
	}
	public void setOtherdbsubjectInfo(Long otherdbsubjectInfo) {
		this.otherdbsubjectInfo = otherdbsubjectInfo;
	}
	public Long getOtherzhsubjectInfo() {
		return otherzhsubjectInfo;
	}
	public void setOtherzhsubjectInfo(Long otherzhsubjectInfo) {
		this.otherzhsubjectInfo = otherzhsubjectInfo;
	}
	public Long getSfdbsubjectInfo() {
		return sfdbsubjectInfo;
	}
	public void setSfdbsubjectInfo(Long sfdbsubjectInfo) {
		this.sfdbsubjectInfo = sfdbsubjectInfo;
	}
	public Long getWorkdynamic() {
		return workdynamic;
	}
	public void setWorkdynamic(Long workdynamic) {
		this.workdynamic = workdynamic;
	}
	public Long getSfwordinfo() {
		return sfwordinfo;
	}
	public void setSfwordinfo(Long sfwordinfo) {
		this.sfwordinfo = sfwordinfo;
	}
	public Long getSfzhsubjectInfo() {
		return sfzhsubjectInfo;
	}
	public void setSfzhsubjectInfo(Long sfzhsubjectInfo) {
		this.sfzhsubjectInfo = sfzhsubjectInfo;
	}
	public Long getSwdbsubjectInfo() {
		return swdbsubjectInfo;
	}
	public void setSwdbsubjectInfo(Long swdbsubjectInfo) {
		this.swdbsubjectInfo = swdbsubjectInfo;
	}
	public Long getSwdayinfo() {
		return swdayinfo;
	}
	public void setSwdayinfo(Long swdayinfo) {
		this.swdayinfo = swdayinfo;
	}
	public Long getSwzhsubjectInfo() {
		return swzhsubjectInfo;
	}
	public void setSwzhsubjectInfo(Long swzhsubjectInfo) {
		this.swzhsubjectInfo = swzhsubjectInfo;
	}
	public Long getHeadphonepa() {
		return headphonepa;
	}
	public void setHeadphonepa(Long headphonepa) {
		this.headphonepa = headphonepa;
	}
	public Long getCountyDynamic() {
		return countyDynamic;
	}
	public void setCountyDynamic(Long countyDynamic) {
		this.countyDynamic = countyDynamic;
	}
	public Long getNetInfo() {
		return netInfo;
	}
	public void setNetInfo(Long netInfo) {
		this.netInfo = netInfo;
	}
	public Long getGovComLeader() {
		return govComLeader;
	}
	public void setGovComLeader(Long govComLeader) {
		this.govComLeader = govComLeader;
	}
	public Long getYuegao() {
		return yuegao;
	}
	public void setYuegao(Long yuegao) {
		this.yuegao = yuegao;
	}
	public Float getYuegaoNum() {
		return yuegaoNum;
	}
	public void setYuegaoNum(Float yuegaoNum) {
		this.yuegaoNum = yuegaoNum;
	}
	public Float getGovComLeaderNum() {
		return govComLeaderNum;
	}
	public void setGovComLeaderNum(Float govComLeaderNum) {
		this.govComLeaderNum = govComLeaderNum;
	}
	public Float getOtherLeaderNum() {
		return otherLeaderNum;
	}
	public void setOtherLeaderNum(Float otherLeaderNum) {
		this.otherLeaderNum = otherLeaderNum;
	}
	public Float getTrafsitcommNum() {
		return trafsitcommNum;
	}
	public void setTrafsitcommNum(Float trafsitcommNum) {
		this.trafsitcommNum = trafsitcommNum;
	}
	public Float getTrafdaypaNum() {
		return trafdaypaNum;
	}
	public void setTrafdaypaNum(Float trafdaypaNum) {
		this.trafdaypaNum = trafdaypaNum;
	}
	public Float getJwdbsubjectInfoNum() {
		return jwdbsubjectInfoNum;
	}
	public void setJwdbsubjectInfoNum(Float jwdbsubjectInfoNum) {
		this.jwdbsubjectInfoNum = jwdbsubjectInfoNum;
	}
	public Float getJwzhsubjectInfoNum() {
		return jwzhsubjectInfoNum;
	}
	public void setJwzhsubjectInfoNum(Float jwzhsubjectInfoNum) {
		this.jwzhsubjectInfoNum = jwzhsubjectInfoNum;
	}
	public Float getPubtypedynamicNum() {
		return pubtypedynamicNum;
	}
	public void setPubtypedynamicNum(Float pubtypedynamicNum) {
		this.pubtypedynamicNum = pubtypedynamicNum;
	}
	public Float getPubTypewordinfoNum() {
		return pubTypewordinfoNum;
	}
	public void setPubTypewordinfoNum(Float pubTypewordinfoNum) {
		this.pubTypewordinfoNum = pubTypewordinfoNum;
	}
	public Float getCountyDynamicNum() {
		return countyDynamicNum;
	}
	public void setCountyDynamicNum(Float countyDynamicNum) {
		this.countyDynamicNum = countyDynamicNum;
	}
	public Float getNetInfoNum() {
		return netInfoNum;
	}
	public void setNetInfoNum(Float netInfoNum) {
		this.netInfoNum = netInfoNum;
	}
	public Float getOtherdbsubjectInfoNum() {
		return otherdbsubjectInfoNum;
	}
	public void setOtherdbsubjectInfoNum(Float otherdbsubjectInfoNum) {
		this.otherdbsubjectInfoNum = otherdbsubjectInfoNum;
	}
	public Float getOtherzhsubjectInfoNum() {
		return otherzhsubjectInfoNum;
	}
	public void setOtherzhsubjectInfoNum(Float otherzhsubjectInfoNum) {
		this.otherzhsubjectInfoNum = otherzhsubjectInfoNum;
	}
	public Float getSfdbsubjectInfoNum() {
		return sfdbsubjectInfoNum;
	}
	public void setSfdbsubjectInfoNum(Float sfdbsubjectInfoNum) {
		this.sfdbsubjectInfoNum = sfdbsubjectInfoNum;
	}
	public Float getWorkdynamicNum() {
		return workdynamicNum;
	}
	public void setWorkdynamicNum(Float workdynamicNum) {
		this.workdynamicNum = workdynamicNum;
	}
	public Float getSfwordinfoNum() {
		return sfwordinfoNum;
	}
	public void setSfwordinfoNum(Float sfwordinfoNum) {
		this.sfwordinfoNum = sfwordinfoNum;
	}
	public Float getSfzhsubjectInfoNum() {
		return sfzhsubjectInfoNum;
	}
	public void setSfzhsubjectInfoNum(Float sfzhsubjectInfoNum) {
		this.sfzhsubjectInfoNum = sfzhsubjectInfoNum;
	}
	public Float getSwdbsubjectInfoNum() {
		return swdbsubjectInfoNum;
	}
	public void setSwdbsubjectInfoNum(Float swdbsubjectInfoNum) {
		this.swdbsubjectInfoNum = swdbsubjectInfoNum;
	}
	public Float getSwdayinfoNum() {
		return swdayinfoNum;
	}
	public void setSwdayinfoNum(Float swdayinfoNum) {
		this.swdayinfoNum = swdayinfoNum;
	}
	public Float getSwzhsubjectInfoNum() {
		return swzhsubjectInfoNum;
	}
	public void setSwzhsubjectInfoNum(Float swzhsubjectInfoNum) {
		this.swzhsubjectInfoNum = swzhsubjectInfoNum;
	}
	public Float getHeadphonepaNum() {
		return headphonepaNum;
	}
	public void setHeadphonepaNum(Float headphonepaNum) {
		this.headphonepaNum = headphonepaNum;
	}
	
	
}
