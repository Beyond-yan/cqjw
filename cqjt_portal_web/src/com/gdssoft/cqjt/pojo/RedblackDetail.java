package com.gdssoft.cqjt.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * 红黑名单详情
 *
 * @author: lgaoyi
 * @date: 2018/6/22 11:33
 */
public class RedblackDetail {

    private String id;

    private String name; // 名称

    private String code; // 统一社会信用代码

    private List<RedblackDetailContent> redblackDetailContentList; // 详情项

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RedblackDetailContent> getRedblackDetailContentList() {
        return redblackDetailContentList;
    }

    public void setRedblackDetailContentList(List<RedblackDetailContent> redblackDetailContentList) {
        this.redblackDetailContentList = redblackDetailContentList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
