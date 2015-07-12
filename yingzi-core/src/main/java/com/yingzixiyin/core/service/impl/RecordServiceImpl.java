package com.yingzixiyin.core.service.impl;

import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.core.dao.RecordDao;
import com.yingzixiyin.core.entity.Record;
import com.yingzixiyin.core.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Service("recordService")
public class RecordServiceImpl implements RecordService {
    private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class.getName());

    @Resource
    RecordDao recordDao;

    @Override
    public Integer insert(Record record) {
        if (null == record || null == record.getUserId() || null == record.getConsultantId() ) {
            logger.info("record内容不能为空");
            return null;
        }
        return recordDao.insert(record);
    }

    @Override
    public void update(Record record) {
        if (null == record || null == record.getId()) {
            logger.info("id不能为空");
            return ;
        }
        recordDao.update(record);
    }

    @Override
    public RecordInfo getRecord(Record record) {
        if (null == record) {
            logger.info("record不能为null");
            return null;
        }
        Record result = recordDao.getRecord(record);
        return Record.translateBean(result);
    }

    @Override
    public List<RecordInfo> getRecordList(Record record) {
        if (null == record) {
            logger.info("record不能为null");
            return null;
        }
        List<Record> consultantList = recordDao.getRecordList(record);
        return Record.translateBeanList(consultantList);
    }
}
