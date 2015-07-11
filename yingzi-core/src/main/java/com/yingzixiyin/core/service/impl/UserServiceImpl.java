package com.yingzixiyin.core.service.impl;

import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.core.dao.UserDao;
import com.yingzixiyin.core.entity.User;
import com.yingzixiyin.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    @Resource
    UserDao userDao;

    @Override
    public Integer insert(User user) {
        if (null == user || StringUtils.isEmpty(user.getOpenId())) {
            logger.info("openId不能为空");
            return null;
        }
        return userDao.insert(user);
    }

    @Override
    public void update(User user) {
        if (null == user || null == user.getId()) {
            logger.info("id不能为空");
            return ;
        }
        userDao.update(user);
    }

    @Override
    public UserInfo getUser(User user) {
        if (null == user) {
            logger.info("user不能为null");
            return null;
        }
        User result = userDao.getUser(user);
        return User.translateBean(result);
    }
}
