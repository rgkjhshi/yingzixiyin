package com.yingzixiyin.api.facade;

import com.yingzixiyin.api.dto.*;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public interface UserFacade {

    /**
     * 增加，id字段不要填写
     * @param userInfo 注册的信息
     * @return 结果
     */
    public BaseResponseDto add(UserInfo userInfo);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     * @param userInfo 新信息
     * @return 结果
     */
    public BaseResponseDto update(UserInfo userInfo);

    /**
     * 查询一个用户信息
     * @param requestDto 按照所填字段去查询
     * @return UserInfo
     */
    public UserInfo queryOne(UserQueryRequestDto requestDto);

}
