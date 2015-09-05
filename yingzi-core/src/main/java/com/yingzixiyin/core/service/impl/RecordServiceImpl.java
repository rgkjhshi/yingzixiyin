package com.yingzixiyin.core.service.impl;

import com.google.common.collect.Lists;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordInfoExtend;
import com.yingzixiyin.api.enums.ConsultTypeEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.core.dao.MessageDao;
import com.yingzixiyin.core.dao.RecordDao;
import com.yingzixiyin.core.entity.Message;
import com.yingzixiyin.core.entity.Record;
import com.yingzixiyin.core.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Service("recordService")
public class RecordServiceImpl implements RecordService {
    private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class.getName());

    @Resource
    RecordDao recordDao;
    @Resource
    MessageDao messageDao;

    @Override
    public Integer insert(Record record) {
        if (null == record || null == record.getUserId() || null == record.getConsultantId()) {
            logger.info("record内容不能为空");
            return null;
        }
        return recordDao.insert(record);
    }

    @Override
    public void update(Record record) {
        if (null == record || null == record.getId()) {
            logger.info("id不能为空");
            return;
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

    @Override
    public Long queryCount(Record bean) {
        return recordDao.queryCount(bean);
    }

    @Override
    public List<RecordInfoExtend> queryConsultantRecordsListPage(Map<String, Object> map) {
        List<Map<String, Object>> list = recordDao.queryConsultantRecordsListPage(map);
//		logger.info("得到查询咨询记录结果:"+list);
        List<RecordInfoExtend> resList = Lists.newArrayList();
        for (Map<String, Object> bean : list) {
            RecordInfoExtend recordInfoExtend = translateBean(bean);
            MessageQueryRequestDto messageQueryRequestDto = new MessageQueryRequestDto();
            messageQueryRequestDto.setRecordId(recordInfoExtend.getId());  // recordId
            messageQueryRequestDto.setIsRead(YesOrNoEnum.NO);  // 未读
            messageQueryRequestDto.setFromPhone(recordInfoExtend.getUserPhone());  // 来自某个用户
            messageQueryRequestDto.setToPhone(recordInfoExtend.getConsultantPhone());  // 到这个咨询师
            List<Message> messageList =  messageDao.getMessageList(Message.getBean(messageQueryRequestDto));
            int count = messageList.size();
            recordInfoExtend.setConsultantUnReadMessageCount(count);
            resList.add(recordInfoExtend);
        }
//		logger.info("得到list:"+resList);
        return resList;
    }

    private RecordInfoExtend translateBean(Map<String, Object> map) {
        RecordInfoExtend recordInfoExtend = new RecordInfoExtend();

        recordInfoExtend.setId((Long)map.get("id"));
        recordInfoExtend.setUserId((Long)map.get("userId"));
        recordInfoExtend.setConsultantId((Long)map.get("consultantId"));
        recordInfoExtend.setConsultType(ConsultTypeEnum.toEnum((Integer)map.get("consultType")));
        recordInfoExtend.setIsCompleted(YesOrNoEnum.toEnum((Integer) map.get("isCompleted")));
        recordInfoExtend.setIsPaid(YesOrNoEnum.toEnum((Integer) map.get("isPaid")));
        recordInfoExtend.setIsReplied(YesOrNoEnum.toEnum((Integer) map.get("isReplied")));
        recordInfoExtend.setCreateTime((Date) map.get("createTime"));
        // consultant 扩展
        recordInfoExtend.setConsultantPhone(map.get("consultantPhone") + "");
        recordInfoExtend.setConsultantName(map.get("consultantName") + "");
        recordInfoExtend.setConsultantAvatar(map.get("consultantAvatar") + "");
        // user 扩展
        recordInfoExtend.setUserPhone(map.get("userPhone") + "");
        recordInfoExtend.setOpenId(map.get("openId") + "");
        return recordInfoExtend;
    }
}
