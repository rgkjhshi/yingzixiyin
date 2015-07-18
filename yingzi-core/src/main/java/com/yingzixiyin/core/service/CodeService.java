package com.yingzixiyin.core.service;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.CodeInfo;
import com.yingzixiyin.core.entity.Code;

/**
 * @author song.shi
 * @date 2015-07-02
 */


public interface CodeService {

    /**
     * 增
     * @param code 记录
     * @return 插入记录的主键
     */
    public Integer insert(Code code);

    /**
     * 改
     * @param code id必需，通过id去修改
     */
    public void update(Code code);

    /**
     * 查询一个
     * @param code 查询条件
     * @return CodeInfo
     */
    public CodeInfo getCode(Code code);

    /**
     * 发送短信验证码并且入库
     * @param phone 待发送验证码的手机
     * @return BaseResponseDto
     */
    public BaseResponseDto sendCodeAndSave(String phone);


    public BaseResponseDto checkCode(Code code);

}
