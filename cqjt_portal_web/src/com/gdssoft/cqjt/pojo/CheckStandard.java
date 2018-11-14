package com.gdssoft.cqjt.pojo;

import com.google.gson.annotations.Expose;

public class CheckStandard extends BasePojo {

	/*
	 * 唯一标示
	 */
	@Expose
	private String checkId;
	/*
	 * 得分项
	 */
	@Expose
	private String itemName;
	/*
	 * 分值
	 */
	@Expose
	private Float score;
	/*
	 * 编码
	 */
	@Expose
	private String code;
	/*
	 * 创建日期
	 */
	@Expose
	private String createDate;
	/*
	 * 创建人
	 */
	@Expose
	private String createUser;
	/*
	 * 是否删除
	 */
	@Expose
	private String isDel;
	
	/*
	 * 奖金额度
	 */
	@Expose
	private Float reward;
	
	
	
	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Float getReward() {
		return reward;
	}

	public void setReward(Float reward) {
		this.reward = reward;
	}

}
