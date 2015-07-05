package com.yingzi.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yingzi.web.constant.WeiXinConstant;
import com.yingzi.web.utils.ByteToHexStringUtils;

@Controller
public class WeiXinResponseController {
	/***
	 * 校验是否微信发过来的信息
	 * @param timestamp
	 * @param nonce
	 * @param token
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	private static Logger logger = LoggerFactory.getLogger(WeiXinResponseController.class);
	private boolean checkValidWeiXin(String signatrue,String timestamp,String nonce) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if(StringUtils.isEmpty(signatrue)) return false;
	    String[] arr = new String[] { timestamp, nonce, WeiXinConstant.WX_TOKEN };
	    Arrays.sort(arr);
	    String s = arr[0] + arr[1] + arr[2];
	    MessageDigest md = MessageDigest.getInstance("SHA-1");
	    byte[] digest = md.digest(s.getBytes("utf-8"));
	    String rsc= ByteToHexStringUtils.bytes2HexString(digest);
	    logger.info("---weixi check signature:"+signatrue);
	    logger.info("---weixi caculate signature:"+rsc);
	    return signatrue.equals(rsc);
	}
	/*public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String signatrue="7ca1780b90103de6f76110d30116c7a3e45571f3";
		String timestamp="1436110067";
		String nonce="1808349648";
		String echostr="8789464330704350971";
		checkValidWeiXin(signatrue, timestamp, nonce);
	}*/
	/***
	 * 微信调用该接口，成为开发者
	 * @param timestamp
	 * @param nonce
	 * @param token
	 * @throws IOException 
	 */
	@RequestMapping("/weixin/check_signature.do")
	@ResponseBody
	public String checkSignatrue(HttpServletRequest request,HttpServletResponse response,String signature,
			String echostr,String timestamp,String nonce) throws IOException{
		logger.info("--微信调用，传参：signature="+signature
				+"|timestamp="+timestamp+"|nonce="+nonce+"|echostr="+echostr);
		//signature  echostr timestamp nonce
		boolean flag=false;
		try {
			flag=checkValidWeiXin(signature, timestamp, nonce);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("--微信接入错误--",e);
		}
		if(flag){
			return echostr;
		}
		return "";
	}
}
