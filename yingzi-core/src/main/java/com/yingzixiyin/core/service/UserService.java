package com.yingzixiyin.core.service;

import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.core.entity.User;

/**
 * @author song.shi
 * @date 2015-07-02
 */


public interface UserService {

    /**
     * 增
     * @param user 用户
     * @return 插入记录的主键
     */
    public Integer insert(User user);

    /**
     * 修改信息
     * @param user id必需，通过id去修改
     */
    public void update(User user);

    public UserInfo getUser(User user);
}
