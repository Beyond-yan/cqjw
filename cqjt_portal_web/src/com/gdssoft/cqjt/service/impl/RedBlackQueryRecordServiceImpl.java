package com.gdssoft.cqjt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.PageBean;
import com.gdssoft.cqjt.dao.RedBlackQueryRecordDao;
import com.gdssoft.cqjt.pojo.RedBlackQueryRecord;
import com.gdssoft.cqjt.service.RedBlackQueryRecordService;
import com.gdssoft.cqjt.util.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lgaoyi
 * @date: 2018/6/21 18:40
 */
@Service("redBlackQueryRecordService")
public class RedBlackQueryRecordServiceImpl implements RedBlackQueryRecordService {

    @Resource(name = "redBlackQueryRecordDao")
    private RedBlackQueryRecordDao redBlackQueryRecordDao;


    @Override
    public void insert(RedBlackQueryRecord record) {
        redBlackQueryRecordDao.insert(record);
    }

    @Override
    public void update(RedBlackQueryRecord record) {
        redBlackQueryRecordDao.update(record);
    }

    @Override
    public void delete(String id) {
        redBlackQueryRecordDao.delete(id);
    }

    @Override
    public List<RedBlackQueryRecord> getByUserId(String userId) {
        List<RedBlackQueryRecord> records = redBlackQueryRecordDao.getByUserId(userId);
        for (RedBlackQueryRecord record : records) {
            initRecord(record);
        }
        return records;
    }

    @Override
    public RedBlackQueryRecord getById(String id) {
        RedBlackQueryRecord record = redBlackQueryRecordDao.getById(id);
        initRecord(record);
        return record;
    }

    @Override
    public PageBean queryPage(RedBlackQueryRecord record, int pageNo, int pageSize) {
        int count = redBlackQueryRecordDao.queryCount(record);
        PageUtils page = new PageUtils(count, pageNo - 1, pageSize);
        List<RedBlackQueryRecord> list = redBlackQueryRecordDao.queryPageList(record, page.getLimitBegin(), page.getLimitEnd());
        for (RedBlackQueryRecord record1: list) {
            initRecord(record1);
        }
        PageBean pageBean = new PageBean(list, pageNo, pageSize);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    /**
     * 基础转换
     *
     * @param record
     */
    private void initRecord(RedBlackQueryRecord record) {
        if (record == null) {
            return;
        }
        if (record.getCreateTime() != null) {
            record.setCreateTimeStr(DateUtil.dateFormat(record.getCreateTime()));
        }
        if (StringUtils.isNotEmpty(record.getCommitInfo())) {
            record.setCommitInfoJson(JSONObject.parseObject(record.getCommitInfo()));
        }
    }
}
