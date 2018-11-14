package com.gdssoft.cqjt.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * 红黑名单备忘录
 *
 * @author: lgaoyi
 * @date: 2018/6/22 15:48
 */
public class UnionPair {

    // 备忘录名称
    private String itemName;
    // 发起部门，多个发起部门以逗号隔开
    private String originDept;
    // 措施内容
    private List<String> contents;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOriginDept() {
        return originDept;
    }

    public void setOriginDept(String originDept) {
        this.originDept = originDept;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
