package com.yingzixiyin.core.biz.impl;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.CodeInfo;
import com.yingzixiyin.core.biz.CodeBiz;
import com.yingzixiyin.core.entity.Code;
import com.yingzixiyin.core.service.CodeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-18
 */

@Component("codeBiz")
public class CodeBizImpl implements CodeBiz {

    @Resource
    CodeService codeService;

    @Override
    public BaseResponseDto sendCode(String phone) {
        return codeService.sendCodeAndSave(phone);
    }

    @Override
    public BaseResponseDto checkCode(CodeInfo codeInfo) {
        return codeService.checkCode(Code.getBean(codeInfo));
    }
}
