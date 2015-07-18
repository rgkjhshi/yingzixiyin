package com.yingzixiyin.core.biz;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.CodeInfo;

/**
 * @author song.shi
 * @date 2015-07-18
 */
public interface CodeBiz {

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
