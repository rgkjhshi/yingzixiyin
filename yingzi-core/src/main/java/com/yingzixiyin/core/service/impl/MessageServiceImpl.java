package com.yingzixiyin.core.service.impl;

import com.google.common.base.Splitter;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.core.dao.ConsultantDao;
import com.yingzixiyin.core.dao.MessageDao;
import com.yingzixiyin.core.entity.Consultant;
import com.yingzixiyin.core.entity.Message;
import com.yingzixiyin.core.service.ConsultantService;
import com.yingzixiyin.core.service.MessageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author lkzlee
 *
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class.getName());

    @Resource
    MessageDao messageDao;


    @Override
    public Integer insert(Message message) {
        if (null == message || StringUtils.isEmpty(message.getMessage()) || StringUtils.isEmpty(message.getRecordId())) {
            logger.info("message和recordId不能为空");
            return null;
        }
        return messageDao.insert(message);
    }

    @Override
    public void update(Message message) {
        if (null == message || null == message.getId()) {
            logger.info("id不能为空");
            return ;
        }
        messageDao.update(message);
    }
    @Override
    public List<MessageInfo> getMessageList(Map<String, Object> map) {
        if (CollectionUtils.isEmpty(map)) {
            logger.info("map不能为空");
            return null;
        }
        List<Message> messageList = messageDao.getMessageListByFilter(map);
        return Message.translateBeanList(messageList);
    }
	@Override
	public List<Map<String, Object>> queryConsultantAndMessageCountByUserId(
			Map<String, Object> map) {
		if (CollectionUtils.isEmpty(map)) {
			logger.info("map不能为空");
			return null;
		}
		return messageDao.queryConsultantAndMessageCountByUserId(map);
	}

}
