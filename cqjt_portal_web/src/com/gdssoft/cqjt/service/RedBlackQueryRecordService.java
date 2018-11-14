package com.gdssoft.cqjt.service;

import com.gdssoft.core.tools.PageBean;
import com.gdssoft.cqjt.pojo.RedBlackQueryRecord;

import java.util.List;

/**
 * 红黑名单查询记录服务
 *
 * @author: lgaoyi
 * @date: 2018/6/21 18:38
 */
public interface RedBlackQueryRecordService {

    public void insert(RedBlackQueryRecord record);

    public void update(RedBlackQueryRecord record);

    public void delete(String id);

    public List<RedBlackQueryRecord> getByUserId(String userId);

    public RedBlackQueryRecord getById(String id);

    public PageBean queryPage(RedBlackQueryRecord record, int pageNo, int pageSize);
}
