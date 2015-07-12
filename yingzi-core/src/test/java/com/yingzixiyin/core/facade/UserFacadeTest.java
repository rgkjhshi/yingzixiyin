package com.yingzixiyin.core.facade;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.UserFacade;
import com.yingzixiyin.core.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class UserFacadeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(UserFacadeTest.class.getName());

    @Resource
    UserFacade userFacade;

    @Test
    public void addTest() {
        UserInfo info = new UserInfo();
        info.setOpenId("123123");
        BaseResponseDto responseDto =  userFacade.add(info);
        logger.info(responseDto.toString());
    }

    @Test
    public void updateTest() {
        UserInfo info = new UserInfo();
        info.setId(1L);
        info.setPhone("13121435540");
        info.setPassword("123456");
        info.setIsBind(YesOrNoEnum.YES);
        BaseResponseDto responseDto =  userFacade.update(info);
        logger.info(responseDto.toString());
    }

    @Test
    public void queryOneTest() {
        UserQueryRequestDto requestDto = new UserQueryRequestDto();
        requestDto.setOpenId("123123");
        UserInfo userInfo =  userFacade.queryOne(requestDto);
        logger.info(userInfo.toString());
    }

}
