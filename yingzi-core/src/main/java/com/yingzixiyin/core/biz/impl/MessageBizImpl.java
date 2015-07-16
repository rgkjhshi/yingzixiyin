package com.yingzixiyin.core.biz.impl;

import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;
import com.yingzixiyin.core.biz.ConsultantBiz;
import com.yingzixiyin.core.biz.MessageBiz;
import com.yingzixiyin.core.entity.Consultant;
import com.yingzixiyin.core.entity.Message;
import com.yingzixiyin.core.service.ConsultantService;
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
    public MessageQueryResponseDto getMessageList(MessageQueryRequestDto requestDto) {
		List<MessageInfo> messageInfoList;

		Map<String, Object> map = Maps.newHashMap();
		map.put("createTime", null == requestDto.getCreateTime()? null : requestDto.getCreateTime());
		map.put("isRead", null == requestDto.getIsRead() ? null : requestDto.getIsRead().getValue());
		map.put("message", null==requestDto.getMessage()?null:requestDto.getMessage());
		map.put("recordId", null==requestDto.getRecordId()?null:requestDto.getRecordId());
		messageInfoList = messageService.getMessageList(map);
		// 返回responseDto
		MessageQueryResponseDto responseDto = new MessageQueryResponseDto();
		if (!CollectionUtils.isEmpty(messageInfoList)) {
			responseDto.setMessageInfoList(messageInfoList);
			responseDto.setCount(messageInfoList.size());
		}
		return responseDto;
    }
}
