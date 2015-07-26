package com.yingzi.admin.utils;

import com.yingzixiyin.api.dto.BaseResponseDto;

/***
 * 解析json的工具类 
 * @author lkzlee
 *
 */
public class JsonUtil {
	public static String getJsonText(BaseResponseDto brDto) {
		StringBuffer sb=new StringBuffer();
		sb.append("{\"returnCode\":"+brDto.getReturnCode()+",");
		sb.append("\"returnMessage\":\""+brDto.getReturnMessage()+"\"}");
		return sb.toString();
	}
}
