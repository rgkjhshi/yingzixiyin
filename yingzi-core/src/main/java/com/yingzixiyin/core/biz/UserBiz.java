package com.yingzixiyin.core.biz;

import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;

/**
 * @author song.shi
 * @date 2015-07-04
 */
public interface UserBiz {

    /**
     * 增加
     */
    public void add(UserInfo userInfo);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     */
    public void update(UserInfo userInfo);

    public UserInfo getUser(UserQueryRequestDto requestDto);

}
