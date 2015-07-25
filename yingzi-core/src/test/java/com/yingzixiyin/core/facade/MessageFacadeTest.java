package com.yingzixiyin.core.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.StatusEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.MessageFacade;
import com.yingzixiyin.core.BaseTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 
 * @author lkzlee
 *
 */
public class MessageFacadeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(MessageFacadeTest.class.getName());

    @Resource
    MessageFacade messageFacade;

    @Test
    public void addTest() {
    	MessageInfo info = new MessageInfo();
    	info.setCreateTime(new Date());
        info.setIsRead(YesOrNoEnum.NO.getValue());
        info.setMessage("消息测试1");
        info.setRecordId(1L);
        BaseResponseDto responseDto =  messageFacade.add(info);
        logger.info(responseDto.toString());
    }

    @Test
    public void updateTest() {
    	MessageInfo info = new MessageInfo();
        info.setId(1L);
        info.setMessage("消息测试safafaf");
        BaseResponseDto responseDto =  messageFacade.update(info);
        logger.info(responseDto.toString());
    }

    @Test
    public void getConsultantListTest() {
        logger.debug("debug");
        logger.info("info");
        MessageQueryRequestDto requestDto = new MessageQueryRequestDto();
        requestDto.setRecordId(1L);
        MessageQueryResponseDto responseDto =  messageFacade.query(requestDto);
        logger.info(responseDto.toString());
    }
    @Test
    public void getConsultantAndMessageCountByUserId() {
        logger.debug("debug");
        logger.info("info");
        List<Map<String,Object>>  res =  messageFacade.queryConsultantAndMessageCountByUserId(3l);
        logger.info(res.toString());
    }
}
