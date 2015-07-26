package com.yingzi.web.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Sets;
import com.yingzi.web.enums.AgeEnum;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.api.enums.ConsultTypeEnum;
import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.StatusEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
/***
 * 解析json的工具类 
 * @author lkzlee
 *
 */
public class JsonUtil {
	public static String getJsonBySingleObject(Class clazz,Object cinfo,String ...ignoreFieldName)
			throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException{
		StringBuffer sb=new StringBuffer();
		if(cinfo==null) return "";
		sb.append("{");
		Field fileds[]=  clazz.getDeclaredFields();
		Set<String> fset=Sets.newHashSet();
		for(String fname:ignoreFieldName){
			fset.add(fname);
		}
		for (int i = 0; i < fileds.length; i++) {
			fileds[i].setAccessible(true);
			if ("serialVersionUID".equals(fileds[i].getName())
					|| fset.contains(fileds[i].getName())) {
				continue;
			}
			if (fileds[i].getType().equals(Integer.class)
					|| fileds[i].getType().equals(Long.class)
					|| fileds[i].getType().equals(Boolean.class)
					|| fileds[i].getType().equals(Short.class)) {
				sb.append("\"" + fileds[i].getName() + "\":"
						+ fileds[i].get(cinfo));
			} 
			else if (fileds[i].getType().equals(AgeEnum.class)||fileds[i].getType().equals(GenderTypeEnum.class)
					||fileds[i].getType().equals(ConsultTypeEnum.class)||fileds[i].getType().equals(StatusEnum.class)
					||fileds[i].getType().equals(YesOrNoEnum.class)||fileds[i].getType().equals(RangeTypeEnum.class)) {
				Object obj=fileds[i].get(cinfo);
				
				Method method=null;
				if (fileds[i].getType().equals(AgeEnum.class)) {
					method = AgeEnum.class.getDeclaredMethod("getValue");
				} else if (fileds[i].getType().equals(GenderTypeEnum.class)) {
					method = GenderTypeEnum.class.getDeclaredMethod("getValue");
				} else if (fileds[i].getType().equals(ConsultTypeEnum.class)) {
					method = ConsultTypeEnum.class.getDeclaredMethod("getValue");
				} else if (fileds[i].getType().equals(StatusEnum.class)) {
					method = StatusEnum.class.getDeclaredMethod("getValue");
				} else if (fileds[i].getType().equals(YesOrNoEnum.class)) {
					method = YesOrNoEnum.class.getDeclaredMethod("getValue");
				} else if (fileds[i].getType().equals(RangeTypeEnum.class)) {
					method = RangeTypeEnum.class.getDeclaredMethod("getValue");
				}
				Object res=method.invoke(obj);
				sb.append("\"" + fileds[i].getName() + "\":" +res.toString());
			}
			else {
				sb.append("\"" + fileds[i].getName() + "\":\""
						+ fileds[i].get(cinfo) + "\"");
			}
			if (i < fileds.length - 1) {
				sb.append(",");
			}
		}
		sb.append("}");
		return sb.toString();
	}
	public static String getJsonByConsultantQueryResponseDto(
			ConsultantQueryResponseDto resDto,String ...ignoreFieldName) throws NoSuchMethodException, SecurityException, InvocationTargetException {
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"returnCode\":"+resDto.getReturnCode()+",");
		sb.append("\"returnMessage\":\""+resDto.getReturnMessage()+"\",");
		sb.append("\"list\":[");
		List<ConsultantInfo> list=resDto.getConsultantInfoList();
		
		if(null!=list&&list.size()>0){
			
			for(int i=0;i<list.size();i++){
				try {
					if(i<list.size()-1){
						sb.append(getJsonBySingleObject(ConsultantInfo.class, list.get(i), ignoreFieldName)+",");
					}
					else{
						sb.append(getJsonBySingleObject(ConsultantInfo.class, list.get(i), ignoreFieldName));
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		sb.append("]");
		sb.append("}");
		return sb.toString();
	}

	public static JsonNode StringToJsonNode(String json)
			throws JsonProcessingException, IOException {
		if (StringUtils.isEmpty(json))
			json = "{}";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(json);
		return jsonNode;
	}
	public static String getJsonText(BaseResponseDto brDto) {
		StringBuffer sb=new StringBuffer();
		sb.append("{\"returnCode\":"+brDto.getReturnCode()+",");
		sb.append("\"returnMessage\":\""+brDto.getReturnMessage()+"\"}");
		return sb.toString();
	}
	public static String getJsonByRecordQueryResponseDto(
			RecordQueryResponseDto resqrDto,String ...ignoreFieldName) throws NoSuchMethodException, SecurityException, InvocationTargetException {
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"returnCode\":"+resqrDto.getReturnCode()+",");
		sb.append("\"returnMessage\":\""+resqrDto.getReturnMessage()+"\",");
		List<RecordInfo> list=resqrDto.getRecordInfoList();
		sb.append("\"list\":[");
		if(null!=list&&list.size()>0){
			for(int i=0;i<list.size();i++){
				try {
					if(i<list.size()-1){
						sb.append(getJsonBySingleObject(RecordInfo.class, list.get(i), ignoreFieldName)+",");
					}
					else{
						sb.append(getJsonBySingleObject(RecordInfo.class, list.get(i), ignoreFieldName));
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		sb.append("]");
		sb.append("}");
		return sb.toString();
	}
}
