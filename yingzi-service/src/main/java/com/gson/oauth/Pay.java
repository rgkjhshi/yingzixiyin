/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.gson.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.gson.WeChat;
import com.gson.util.ConfKit;
import com.gson.util.HttpKit;
import com.yingzi.web.utils.MD5Utils;

/**
 * 支付相关方法
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 *
 */
public class Pay {

    // 发货通知接口
    private static final String DELIVERNOTIFY_URL = "https://api.weixin.qq.com/pay/delivernotify?access_token=";

    //预下单接口
    public static final String PRE_ORDER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    //支付接口

    /**
     * 构造签名
     * @param params
     * @param encode
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }

    /**
     * 构造package, 这是我见到的最草蛋的加密，尼玛文档还有错
     * @param params
     * @param paternerKey
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String packageSign(Map<String, String> params) throws UnsupportedEncodingException {
        String string1 = createSign(params, false);
        String stringSignTemp = string1 + "&key=" + WeChat.getPaySignKey();
//        System.out.println("@生成的签名参数："+stringSignTemp);
        String signValue = MD5Utils.getMD5(stringSignTemp).toUpperCase();
//        System.out.println("@生成的签名："+signValue);
        return signValue;
    }

    /**
     * 支付回调校验签名
     * @param timestamp
     * @param noncestr
     * @param openid
     * @param issubscribe
     * @param appsignature
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static boolean verifySign( Map<String, String> paras ,String sign) throws UnsupportedEncodingException {
        String paySign = packageSign(paras);;
//        System.out.println("校验签名是否正确,paySign="+paySign+",sign="+sign);
        return paySign.equalsIgnoreCase(sign);
    }
    
    /**
     * 发货通知签名
     * @param paras
     * @return
     * @throws UnsupportedEncodingException
     * 
     * @参数 appid、appkey、openid、transid、out_trade_no、deliver_timestamp、deliver_status、deliver_msg；
     */
    private static String deliverSign(Map<String, String> paras) throws UnsupportedEncodingException {
        paras.put("appkey", ConfKit.get("paySignKey"));
        String string1 = createSign(paras, false);
        String paySign = DigestUtils.shaHex(string1);
        return paySign;
    }
    
    
    /**
     * 发货通知
     * @param access_token
     * @param openid
     * @param transid
     * @param out_trade_no
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     * @throws InterruptedException 
     * @throws ExecutionException 
     */

    public static boolean delivernotify(String access_token, String openid, String transid, String out_trade_no) throws IOException, ExecutionException, InterruptedException {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", ConfKit.get("AppId"));
        paras.put("openid", openid);
        paras.put("transid", transid);
        paras.put("out_trade_no", out_trade_no);
        paras.put("deliver_timestamp", (System.currentTimeMillis() / 1000) + "");
        paras.put("deliver_status", "1");
        paras.put("deliver_msg", "ok");
        // 签名
        String app_signature = deliverSign(paras);
        paras.put("app_signature", app_signature);
        paras.put("sign_method", "sha1");
        String json = HttpKit.post(DELIVERNOTIFY_URL.concat(access_token), JSONObject.toJSONString(paras));
        if (StringUtils.isNotBlank(json)) {
            JSONObject object = JSONObject.parseObject(json);
            if (object.containsKey("errcode")) {
                int errcode = object.getIntValue("errcode");
                return errcode == 0;
            }
        }
        return false;
    }
}
