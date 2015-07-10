package com.yingzixiyin.core.service;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.core.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        Map<String, Object> map = Maps.newHashMap();
        map.put("minAge", 10);
        System.out.println(consultantService.getConsultantList(map));
    }

    @Test
    public void getConsultantListByIdListTest() {
        List<String> idList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList("1 ,2, ,4 ,");
        System.out.println(idList);
        String ids = "1 ,2, ,4 ,";
        List<ConsultantInfo> result = consultantService.getConsultantList(ids);
        System.out.println(result);
    }
}
