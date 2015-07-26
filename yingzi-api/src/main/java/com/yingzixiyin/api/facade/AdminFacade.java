package com.yingzixiyin.api.facade;

import com.yingzixiyin.api.dto.*;


/**
 * 
 * @author lkzlee
 *
 */
public interface AdminFacade {

    /**
     * 增加，id字段不要填写
     * @param AdminInfo 注册的信息
     * @return 结果
     */
    public BaseResponseDto add(AdminInfo admin);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     * @param AdminInfo 新信息
     * @return 结果
     */
    public BaseResponseDto update(AdminInfo admin);

    /**
     * 查询一个管理员信息
     * @param requestDto 按照所填字段去查询
     * @return AdminInfo
     */
    public AdminInfo queryOne(AdminQueryRequestDto requestDto);
    /**
     * 根据条件查询管理员列表
     * @param requestDto
     * @return
     */
    public AdminQueryResponseDto query(AdminQueryRequestDto requestDto);

}
