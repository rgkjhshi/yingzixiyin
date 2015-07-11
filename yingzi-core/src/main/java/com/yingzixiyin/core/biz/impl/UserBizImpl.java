package com.yingzixiyin.core.biz.impl;

import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.core.biz.UserBiz;
import com.yingzixiyin.core.entity.User;
import com.yingzixiyin.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-04
 */

@Component("userBiz")
public class UserBizImpl implements UserBiz {
    private static final Logger logger = LoggerFactory.getLogger(UserBizImpl.class);

    @Resource
    UserService userService;

    @Override
    public void add(UserInfo userInfo) {
        // 增
        userService.insert(User.getBean(userInfo));
    }

    @Override
    public void update(UserInfo userInfo) {
        // 改
        userService.update(User.getBean(userInfo));
    }

    @Override
    public UserInfo getUser(UserQueryRequestDto requestDto) {
        return userService.getUser(User.getBean(requestDto));
    }

}
