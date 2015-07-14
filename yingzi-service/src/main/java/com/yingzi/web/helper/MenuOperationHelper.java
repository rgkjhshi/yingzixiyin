package com.yingzi.web.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gson.WeChat;

public class MenuOperationHelper {
	private static StringBuffer menuJson=new StringBuffer();
	private static Logger LOG=LoggerFactory.getLogger(MenuOperationHelper.class);
	static{
		try{
//			InputStream ins=Thread.currentThread().getContextClassLoader().getResourceAsStream("/wechat.config");
			InputStream ins=MenuOperationHelper.class.getResourceAsStream("/wechat.config");
			BufferedReader br=new BufferedReader(new InputStreamReader(ins));
			String msg=null;
			do{
				msg=br.readLine();
				if(msg!=null){
					menuJson.append(msg);
				}
			}while(msg!=null);
			LOG.debug("加载的菜单内容为:"+menuJson);
			ins.close();
			br.close();
		}catch(Exception e){
			LOG.error("加载wechat.config配置文件出错！",e);
		}
	}
	public static boolean createMenuByConfig() throws Exception{
		String access_token=WeChatHelper.getAccessToken();
		return WeChat.menu.createMenu(access_token, menuJson.toString());
	}
	public static boolean deleteMenuByConfig() throws Exception{
		String access_token=WeChatHelper.getAccessToken();
		return WeChat.menu.deleteMenu(access_token);
	}
	public static void main(String[] args) throws Exception {
		boolean flag=createMenuByConfig();
		LOG.info("创建菜单结果："+flag);
//		boolean dflag=deleteMenuByConfig();
//		LOG.info("删除菜单结果："+dflag);
	}
}
