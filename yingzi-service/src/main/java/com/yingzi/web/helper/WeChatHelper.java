package com.yingzi.web.helper;

import com.gson.WeChat;

public class WeChatHelper {
	private static long time = System.currentTimeMillis();
	private static String token = null;
	public static synchronized String getAccessToken() throws Exception{
		long now = System.currentTimeMillis();
		if (token == null){
			token = WeChat.getAccessToken();
		} else {
			if ((now - time) > 7000000){
				time = now;
				token = WeChat.getAccessToken();
			}
		}
		return token;
	}
	
	public static synchronized Object[] getAccessTokenAndExpireTime() throws Exception{
		long now = System.currentTimeMillis();
		if (token == null){
			token = WeChat.getAccessToken();
		} else {
			if ((now - time) > 7000000){
				time = now;
				token = WeChat.getAccessToken();
			}
		}
		return new Object[]{token, time};
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getAccessToken());
	}

	public static synchronized void refreshAccessToken() throws Exception {
		token = WeChat.getAccessToken();
		time = System.currentTimeMillis();
	}
	
}
