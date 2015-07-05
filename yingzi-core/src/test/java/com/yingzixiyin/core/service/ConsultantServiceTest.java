package com.yingzixiyin.core.service;

import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.core.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-02
 */

public class ConsultantServiceTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantServiceTest.class.getName());


    @Resource
    ConsultantService consultantService;

    @Test
    public void getConsultantListTest() {
        logger.debug("debug");
        logger.info("info");
        System.out.println(consultantService.getConsultantList(RangeTypeEnum.ONE.getValue()));
    }
}
