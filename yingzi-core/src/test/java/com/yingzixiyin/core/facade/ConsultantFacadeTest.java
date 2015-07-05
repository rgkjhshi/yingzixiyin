package com.yingzixiyin.core.facade;

import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.ConsultantRequestDto;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.core.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class ConsultantFacadeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantFacadeTest.class.getName());

    @Resource
    ConsultantFacade consultantFacade;

    @Test
    public void getConsultantListTest() {
        logger.debug("debug");
        logger.info("info");
        ConsultantRequestDto requestDto = new ConsultantRequestDto();
        requestDto.setRangeType(RangeTypeEnum.ONE);
        ConsultantQueryResponseDto responseDto =  consultantFacade.getConsultantListByRangeType(requestDto);
        logger.info(responseDto.toString());
    }
}
