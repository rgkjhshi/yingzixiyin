package com.yingzi.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yingzi.web.weixin.utils.SignUtil;

@Controller
public class WeiXinResponseController {
	private static Logger logger = LoggerFactory.getLogger(WeiXinResponseController.class);
	/***
	 * 微信调用该接口，成为开发者
	 * @param timestamp
	 * @param nonce
	 * @param token
	 * @throws IOException 
	 */
	@RequestMapping(value="/weixin/check_signature.do",method=RequestMethod.GET)
	@ResponseBody
	public String checkSignatrue(HttpServletRequest request,HttpServletResponse response,String signature,
			String echostr,String timestamp,String nonce) throws IOException{
		logger.info("--微信调用，传参：signature="+signature
				+"|timestamp="+timestamp+"|nonce="+nonce+"|echostr="+echostr);
		if(SignUtil.checkSignature(signature,timestamp,nonce)){
			return echostr;
		}
		return "";
	}
	/***
	 * 微信调用该接口，处理请求
	 * @param timestamp
	 * @param nonce
	 * @param token
	 * @throws IOException 
	 */
	@RequestMapping(value="/weixin/request.do",method=RequestMethod.POST)
	@ResponseBody
	public void proccessRequest(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("--微信调用后台请求|request:"+request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		
		PrintWriter out = response.getWriter();
		if(SignUtil.checkSignature(signature,timestamp,nonce)){
		/*	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			 MarketService marketService=(MarketService)ctx.getBean("marketService");
			 
			String respMessage = marketService.processRequest(request);
			out.print(respMessage);*/
		}
		out.close();
		out = null;
	}
}
