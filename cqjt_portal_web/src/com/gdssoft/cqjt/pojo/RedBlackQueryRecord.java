package com.gdssoft.cqjt.pojo;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: lgaoyi
 * @date: 2018/6/21 18:13
 */
public class RedBlackQueryRecord extends BasePojo implements Serializable {

    private static final long serialVersionUID = 1L;
    // 未反馈
    public static final String NOT_COMMIT = "0";
    // 反馈中
    public static final String IN_COMMIT = "1";
    // 已反馈
    public static final String COMMITED = "2";

    @Expose
    private String id;

    @Expose
    private String redNum;

    @Expose
    private String redDes;

    @Expose
    private String blackNum;

    @Expose
    private String blackDes;

    @Expose
    private String queryName;

    @Expose
    private String userId;

    @Expose
    private Date createTime;

    @Expose
    private String result;

    @Expose
    private String status;

    @Expose
    private String requestId;

    @Expose
    private String detailIds;

    @Expose
    private String commitDetailIds;

    @Expose
    private Date updateTime;

    @Expose
    private String commitInfo;

    private JSONObject commitInfoJson;

    private String createTimeStr;

    private Date beginCreateTime;

    private Date endCreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRedNum() {
        return redNum;
    }

    public void setRedNum(String redNum) {
        this.redNum = redNum;
    }

    public String getRedDes() {
        return redDes;
    }

    public void setRedDes(String redDes) {
        this.redDes = redDes;
    }

    public String getBlackNum() {
        return blackNum;
    }

    public void setBlackNum(String blackNum) {
        this.blackNum = blackNum;
    }

    public String getBlackDes() {
        return blackDes;
    }

    public void setBlackDes(String blackDes) {
        this.blackDes = blackDes;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getDetailIds() {
        return detailIds;
    }

    public void setDetailIds(String detailIds) {
        this.detailIds = detailIds;
    }

    public String getCommitDetailIds() {
        return commitDetailIds;
    }

    public void setCommitDetailIds(String commitDetailIds) {
        this.commitDetailIds = commitDetailIds;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCommitInfo() {
        return commitInfo;
    }

    public void setCommitInfo(String commitInfo) {
        this.commitInfo = commitInfo;
    }

    public JSONObject getCommitInfoJson() {
        return commitInfoJson;
    }

    public void setCommitInfoJson(JSONObject commitInfoJson) {
        this.commitInfoJson = commitInfoJson;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Date getBeginCreateTime() {
        return beginCreateTime;
    }

    public void setBeginCreateTime(Date beginCreateTime) {
        this.beginCreateTime = beginCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
