package com.yingzixiyin.core.facade.impl;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.MessageFacade;
import com.yingzixiyin.core.biz.ConsultantBiz;
import com.yingzixiyin.core.biz.MessageBiz;
import com.yingzixiyin.core.utils.ParameterUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-04
 */

@Component("messageFacade")
public class MessageFacadeImpl implements MessageFacade {
    private static final Logger logger = LoggerFactory.getLogger(MessageFacadeImpl.class.getName());

    @Resource
    MessageBiz messageBiz;

	@Override
	public BaseResponseDto add(MessageInfo message) {
		logger.info("收到参数:" + message);
		BaseResponseDto responseDto = new BaseResponseDto();
		try {
			ParameterUtils.notNull(message, "messageInfo不能为null");
			ParameterUtils.notNull(message.getMessage(), "message不能为null");
			ParameterUtils.notNull(message.getRecordId(), "recordId不能为null");
			messageBiz.add(message);
			responseDto.setReturnCode(0);
		} catch (IllegalArgumentException e) {
			logger.info("参数异常:" + e.getMessage());
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("捕获异常:" + e.getMessage(), e);
			responseDto.setReturnCode(-1);
		}
		logger.info("返回数据:" + responseDto);
		return responseDto;
	}

	@Override
	public BaseResponseDto update(MessageInfo message) {
		logger.info("收到参数:" + message);
		BaseResponseDto responseDto = new BaseResponseDto();
		try {
			ParameterUtils.notNull(message, "messageInfo不能为null");
			messageBiz.update(message);
			responseDto.setReturnCode(0);
		} catch (IllegalArgumentException e) {
			logger.info("参数异常:" + e.getMessage());
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("捕获异常:" + e.getMessage(), e);
			responseDto.setReturnCode(-1);
		}
		logger.info("返回数据:" + responseDto);
		return responseDto;
	}

	@Override
	public MessageQueryResponseDto query(MessageQueryRequestDto requestDto) {
		 logger.info("收到参数:" + requestDto);
	        MessageQueryResponseDto responseDto = new MessageQueryResponseDto();
	        try {
	            ParameterUtils.notNull(requestDto, "MessageQueryResponseDto不能为null");
	            responseDto = messageBiz.getMessageList(requestDto);
	            responseDto.setReturnCode(0);
	        } catch (IllegalArgumentException e) {
	            logger.info("参数异常:" + e.getMessage());
	            responseDto.setReturnCode(-1);
	            responseDto.setReturnMessage(e.getMessage());
	        } catch (Exception e) {
	            logger.error("捕获异常:" + e.getMessage(), e);
	            responseDto.setReturnCode(-1);
	        }
	        logger.info("返回数据:" + responseDto);
	        return responseDto;
	}
}
