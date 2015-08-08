package com.yingzixiyin.core.biz.impl;

import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;
import com.yingzixiyin.core.biz.MessageBiz;
import com.yingzixiyin.core.entity.Message;
import com.yingzixiyin.core.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-04
 */

@Component("messageBiz")
public class MessageBizImpl implements MessageBiz {
    private static final Logger logger = LoggerFactory.getLogger(MessageBizImpl.class);

    @Resource
    MessageService messageService;

    @Override
    public void add(MessageInfo messageInfo) {
        // 增
    	messageService.insert(Message.getBean(messageInfo));
    }

    @Override
    public void update(MessageInfo messageInfo) {
        // 改
    	messageService.update(Message.getBean(messageInfo));
    }

    @Override
    public MessageInfo getMessage(MessageQueryRequestDto requestDto) {
        return messageService.getMessage(Message.getBean(requestDto));
    }

    @Override
    public MessageQueryResponseDto getMessageList(MessageQueryRequestDto requestDto) {
		List<MessageInfo> messageInfoList;
        messageInfoList = messageService.getMessageList(Message.getBean(requestDto));
        // 返回responseDto
        MessageQueryResponseDto responseDto = new MessageQueryResponseDto();
        if (!CollectionUtils.isEmpty(messageInfoList)) {
            responseDto.setMessageInfoList(messageInfoList);
            responseDto.setCount(messageInfoList.size());
        } else {
            responseDto.setCount(0);
        }
		return responseDto;
    }

    @Override
	public List<Map<String, Object>> queryConsultantAndMessageCountByUserId(
			Long userId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("userId", userId);
		return messageService.queryConsultantAndMessageCountByUserId(map);
	}
}
