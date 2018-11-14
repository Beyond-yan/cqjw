package com.gdssoft.cqjt.pojo;

import java.util.List;

public class RedBlackType {

    private String unitedId;
    private String unitedType;
    private String unitedName;
    private String requestId;
    private List<UnionPair> unionPairList; //奖惩措施依据列表
    private List<RedblackDetail> redblackDetailList;//具体内容

    public String getUnitedId() {
        return unitedId;
    }

    public void setUnitedId(String unitedId) {
        this.unitedId = unitedId;
    }

    public String getUnitedType() {
        return unitedType;
    }

    public void setUnitedType(String unitedType) {
        this.unitedType = unitedType;
    }

    public String getUnitedName() {
        return unitedName;
    }

    public void setUnitedName(String unitedName) {
        this.unitedName = unitedName;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<UnionPair> getUnionPairList() {
        return unionPairList;
    }

    public void setUnionPairList(List<UnionPair> unionPairList) {
        this.unionPairList = unionPairList;
    }

    public List<RedblackDetail> getRedblackDetailList() {
        return redblackDetailList;
    }

    public void setRedblackDetailList(List<RedblackDetail> redblackDetailList) {
        this.redblackDetailList = redblackDetailList;
    }
}
