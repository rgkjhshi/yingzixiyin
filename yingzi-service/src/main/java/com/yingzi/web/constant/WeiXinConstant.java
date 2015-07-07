package com.yingzi.web.constant;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeiXinConstant {
	public final static String WX_TOKEN_KEY="WX_TOKEN";
	public final static Properties iniConfig=new Properties();
	
	private final static Logger logger=LoggerFactory.getLogger(WeiXinConstant.class);
	static {
		try{
			InputStream ins=WeiXinConstant.class.getResourceAsStream("/yingzi_weixin.properties");
			iniConfig.load(ins);
			ins.close();
		}catch(Exception e){
			logger.error("加载yingzi_weixin.properties出错！", e);
		}
	}
}
