package com.yingzixiyin.core.facade;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.api.enums.ConsultTypeEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.RecordFacade;
import com.yingzixiyin.core.BaseTest;
import com.yingzixiyin.page.Pagination;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class RecordFacadeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(RecordFacadeTest.class.getName());

    @Resource
    RecordFacade recordFacade;

   @Test
    public void addTest() {
        RecordInfo info = new RecordInfo();
        info.setId(1l);
        info.setUserId(1000L);
        info.setConsultantId(100L);
        BaseResponseDto responseDto =  recordFacade.add(info);
        logger.info(responseDto.toString());
    }

   /* @Test
    public void updateTest() {
        RecordInfo info = new RecordInfo();
        info.setId(1L);
        info.setConsultType(ConsultTypeEnum.OFF_LINE);
        BaseResponseDto responseDto =  recordFacade.update(info);
        logger.info(responseDto.toString());
    }

    @Test
    public void queryOneTest() {
        RecordQueryRequestDto requestDto = new RecordQueryRequestDto();
        requestDto.setUserId(1L);
        requestDto.setConsultantId(1L);
        requestDto.setConsultType(ConsultTypeEnum.OFF_LINE);
        RecordInfo info =  recordFacade.queryOne(requestDto);
        logger.info("{}", info);
    }

    @Test
    public void queryTest() {
        RecordQueryRequestDto requestDto = new RecordQueryRequestDto();
        requestDto.setUserId(1L);
        requestDto.setConsultantId(1L);
        requestDto.setConsultType(ConsultTypeEnum.OFF_LINE);
        RecordQueryResponseDto responseDto =  recordFacade.query(requestDto);
        logger.info("{}", responseDto);
    }*/
    @Test
    public void queryPage(){
    	RecordQueryRequestDto requestDto=new RecordQueryRequestDto();
    	Pagination page=new Pagination();
    	requestDto.setIsCompleted(YesOrNoEnum.toEnum(1));
    	requestDto.setConsultantId(1l);
    	requestDto.setConsultType(ConsultTypeEnum.toEnum(1));
    	requestDto.setIsPaid(YesOrNoEnum.toEnum(1));
    	requestDto.setIsReplied(YesOrNoEnum.toEnum(1));
    	Long count=recordFacade.queryCount(requestDto);
    	logger.info("--查询得到数目："+count);
    	page.setMaxCountAndCurrentPage(count, 1);
    	RecordQueryResponseDto responseDto=recordFacade.queryPage(requestDto, page);
    	logger.info("查询结果："+responseDto.getRecordInfoExtendList());
    }
}
