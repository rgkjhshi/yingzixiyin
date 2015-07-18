package com.yingzixiyin.core.facade.impl;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.yingzixiyin.api.facade.CommonFacade;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * @author song.shi
 * @date 2015-07-16
 */

public class CommonFacadeImpl implements CommonFacade {

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
}
