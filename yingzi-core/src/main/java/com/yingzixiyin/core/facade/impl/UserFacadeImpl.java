package com.yingzixiyin.core.facade.impl;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.api.facade.UserFacade;
import com.yingzixiyin.core.biz.UserBiz;
import com.yingzixiyin.core.utils.ParameterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-04
 */

@Component("userFacade")
public class UserFacadeImpl implements UserFacade {
    private static final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class.getName());

    @Resource
    UserBiz userBiz;

    @Override
    public BaseResponseDto add(UserInfo userInfo) {
        logger.info("收到参数:" + userInfo);
        BaseResponseDto responseDto = new BaseResponseDto();
        try {
            ParameterUtils.notNull(userInfo, "userInfo不能为null");
            ParameterUtils.notNull(userInfo.getOpenId(), "openId不能为null");
            userBiz.add(userInfo);
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
    public BaseResponseDto update(UserInfo userInfo) {
        logger.info("收到参数:" + userInfo);
        BaseResponseDto responseDto = new BaseResponseDto();
        try {
            ParameterUtils.notNull(userInfo, "userInfo不能为null");
            ParameterUtils.notNull(userInfo.getId(), "id不能为null");
            userBiz.update(userInfo);
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
    public UserInfo queryOne(UserQueryRequestDto requestDto) {
        logger.info("收到参数:" + requestDto);
        UserInfo userInfo = null;
        try {
            ParameterUtils.notNull(requestDto, "UserQueryRequestDto不能为null");
            userInfo = userBiz.getUser(requestDto);
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
        }
        logger.info("返回数据:" + userInfo);
        return userInfo;
    }

}
