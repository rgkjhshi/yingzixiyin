package com.yingzixiyin.core.facade.impl;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.CodeInfo;
import com.yingzixiyin.api.facade.CodeFacade;
import com.yingzixiyin.core.biz.CodeBiz;
import com.yingzixiyin.core.utils.ParameterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * @author song.shi
 * @date 2015-07-16
 */

@Component("codeFacade")
public class CodeFacadeImpl implements CodeFacade {
    private static final Logger logger = LoggerFactory.getLogger(CodeFacadeImpl.class.getName());

    @Resource
    CodeBiz codeBiz;

    // 测试时写的函数
    public void sendMessage() {
        Random random = new Random();
        Integer randomInteger = random.nextInt(900000)+100000;
        HashMap<String, Object> result = null;
        CCPRestSDK restAPI = new CCPRestSDK();
        restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
        restAPI.setAccount("8a48b5514e8a7522014e94912a430b71", "d6ef7224347248e8821ba7f0032d7465");// 初始化主帐号和主帐号TOKEN
        restAPI.setAppId("aaf98f894e91da24014e94944a960122");// 初始化应用ID
        result = restAPI.sendTemplateSMS("13121435540","1" ,new String[]{randomInteger.toString(),"1"});
        System.out.println("SDKTestSendTemplateSMS result=" + result);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
    }

    @Override
    public BaseResponseDto sendCode(String phone) {
        logger.info("收到参数:phone=" + phone);
        BaseResponseDto responseDto = new BaseResponseDto();
        try {
            ParameterUtils.notEmpty(phone, "phone不能为空");
            responseDto = codeBiz.sendCode(phone);
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
            responseDto.setReturnCode(-1);
            responseDto.setReturnMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
            responseDto.setReturnCode(-1);
        }
        logger.info("返回数据:" + responseDto);
        return responseDto;
    }

    @Override
    public BaseResponseDto checkCode(CodeInfo codeInfo) {
        logger.info("收到参数:" + codeInfo);
        BaseResponseDto responseDto = new BaseResponseDto();
        try {
            ParameterUtils.notNull(codeInfo, "codeInfo不能为null");
            ParameterUtils.notEmpty(codeInfo.getPhone(), "phone不能为空");
            ParameterUtils.notEmpty(codeInfo.getCode(), "code不能为空");
            responseDto = codeBiz.checkCode(codeInfo);
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
            responseDto.setReturnCode(-1);
            responseDto.setReturnMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
            responseDto.setReturnCode(-1);
        }
        logger.info("返回数据:" + responseDto);
        return responseDto;
    }
}
