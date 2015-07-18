package com.yingzixiyin.api.facade;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.CodeInfo;

/**
 * @author song.shi
 * @date 2015-07-16
 */
public interface CodeFacade {

    /**
     * 发送验证码
     * @param phone 待发送验证码的手机
     * @return BaseResponseDto
     */
    public BaseResponseDto sendCode(String phone);

    /**
     * 检查验证码是否正确
     * @param recordInfo phone和code必填
     * @return
     */
    public BaseResponseDto checkCode(CodeInfo recordInfo);

}
