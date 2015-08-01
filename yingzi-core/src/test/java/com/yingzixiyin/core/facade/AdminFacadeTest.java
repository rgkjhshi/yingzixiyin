package com.yingzixiyin.core.facade;

import com.yingzixiyin.api.dto.AdminInfo;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.facade.AdminFacade;
import com.yingzixiyin.core.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class AdminFacadeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(AdminFacadeTest.class.getName());

    @Resource
    AdminFacade adminFacade;

    @Test
    public void addTest() {
    	AdminInfo admin=new AdminInfo();
    	admin.setUsername("zlm");
    	admin.setPassword("123");
        BaseResponseDto responseDto = adminFacade.add(admin);
        logger.info(responseDto.toString());
    }

    @Test
    public void updateTest() {
    	AdminInfo admin=new AdminInfo();
    	admin.setId(2l);
    	admin.setUsername("zlm");
    	admin.setPassword("12345");
        BaseResponseDto responseDto = adminFacade.update(admin);
        logger.info(responseDto.toString());
    }

    @Test
    public void queryOneTest() {
//    	AdminQueryRequestDto requestDto=new AdminQueryRequestDto();
//    	requestDto.setUsername("lkzlee");
//        AdminInfo userInfo = adminFacade.queryOne(requestDto);
//        logger.info(userInfo.toString());
    }
    @Test
    public void queryListTest() {
//    	AdminQueryRequestDto requestDto=new AdminQueryRequestDto();
//        AdminQueryResponseDto userInfo = adminFacade.query(requestDto);
//        logger.info(userInfo.toString());
    }

}
