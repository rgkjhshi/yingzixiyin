package com.yingzi.web.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {

	public static ObjectNode getNoManagerResponse() {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("code", 0);
		ObjectNode response = createObjectNode();
		response.put("actions", createArrayNode());
		objectNode.put("response", response);
		return objectNode;
	}

	public static ObjectNode getSizeAndListObjectNode(long count, List<?> list,
			Integer pageSize) {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("code", 1);
		ObjectNode response = createObjectNode();
		response.put("size", (count - 1) / pageSize + 1);
		response.put("list", JsonUtil.objectToJsonNode(list));
		objectNode.put("response", response);
		return objectNode;
	}

	public static ObjectNode getSizeAndListObjectNodeWithTotal(long count,
			List<?> list, int pageSize) {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("code", 1);
		ObjectNode response = createObjectNode();
		response.put("size", (count - 1) / pageSize + 1);
		response.put("total", count);
		response.put("list", JsonUtil.objectToJsonNode(list));
		objectNode.put("response", response);
		return objectNode;
	}

	/***
	 * 分页实现的转换
	 * 
	 * @param pageNum
	 * @param count
	 * @param list
	 * @param pageSize
	 * @return
	 */
	public static ObjectNode getPageListObjectNode(int pageNum, long count,
			List<?> list, Integer pageSize, String condition) {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("code", 1);
		ObjectNode response = createObjectNode();
		response.put("maxPage", (count - 1) / pageSize + 1);
		response.put("pageNum", pageNum);
		response.put("condition", condition);
		response.put("list", JsonUtil.objectToJsonNode(list));
		objectNode.put("response", response);
		return objectNode;
	}

	public static ObjectNode getSizeAndListObjectNode(long count,
			ArrayNode list, Integer pageSize) {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("size", (count - 1) / pageSize + 1);
		ObjectNode response = createObjectNode();
		response.put("list", JsonUtil.objectToJsonNode(list));
		objectNode.put("response", response);
		return objectNode;
	}

	public static ObjectNode getListObjectNode(List<?> list) {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("code", 1);
		ObjectNode response = createObjectNode();
		response.put("list", JsonUtil.objectToJsonNode(list));
		objectNode.put("response", response);
		return objectNode;
	}

	/*
	 * public static ObjectNode getStatusObjectNode(Object obj,Integer status){
	 * ObjectNode objectNode=createObjectNode(); objectNode.put("status",
	 * status); objectNode.put("object", JsonUtil.objectToJsonNode(obj)); return
	 * objectNode; }
	 */

	public static <T> List<T> StringToObjectList(String str,
			TypeReference<?> type) {
		try {
			if (str == null || str.isEmpty() || type == null) {
				// TODO
				return null;
			}
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(str, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T StringToObject(String str, TypeReference<T> type) {
		try {
			if (str == null || str.isEmpty() || type == null) {
				// TODO
				return null;
			}
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(str, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String ObjectToString(Object obj) {
		try {
			if (obj == null) {
				// TODO
				return null;
			}
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static JsonNode StringToJsonNode(String json)
			throws JsonProcessingException, IOException {
		if (StringUtils.isEmpty(json))
			json = "{}";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(json);
		return jsonNode;
	}

	public static JsonNode objectToJsonNode(Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String objJson = objectMapper.writeValueAsString(obj);
			JsonNode jsonNode = objectMapper.readTree(objJson);
			return jsonNode;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ObjectNode createObjectNode() {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode ObjectNode = objectMapper.createObjectNode();
		return ObjectNode;
	}

	public static ArrayNode createArrayNode() {
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayNode arrayNode = objectMapper.createArrayNode();
		return arrayNode;
	}

	public static String ObjectToStringByReflect(Object obj, String extra) {
		StringBuilder sbf = new StringBuilder();
		try {

			Class<?> clz = obj.getClass();
			Field[] fields = clz.getDeclaredFields();
			Field.setAccessible(fields, true);
			int size = fields.length;
			sbf.append("{");
			boolean bool = false;
			for (int i = 0; i < size; i++) {
				String fieldName = fields[i].getName();
				if ("serialVersionUID".equals(fieldName)) {
					continue;
				}
				sbf.append("\"");
				sbf.append(fieldName);
				sbf.append("\"");
				sbf.append(":");
				Object value = fields[i].get(obj);
				if ((fields[i].getType().equals(String.class) || fields[i]
						.getType().equals(Date.class))
						&& value != null
						&& !fieldName.equals("common_cate")) {
					bool = true;
					sbf.append("\"");
				} else {
					bool = false;
				}
				sbf.append(value);
				if (bool) {
					sbf.append("\"");
				}
				if (i != size - 1) {
					sbf.append(",");
				}

			}
			if (extra != null || !"".equals(extra)) {
				sbf.append(",");
				sbf.append(extra);
			}
			sbf.append("}");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return sbf.toString();
	}

	public static String addChannelAttribute(String json, String childUrl,
			String url, String parament) throws JsonProcessingException,
			IOException {
		ArrayNode arrayNode = (ArrayNode) StringToJsonNode(json);

		if (arrayNode != null) {
			int size = arrayNode.size();
			ObjectNode objectNode = null;
			for (int i = 0; i < size; i++) {
				objectNode = (ObjectNode) arrayNode.get(i);
				objectNode.put("childUrl", childUrl + parament + "="
						+ objectNode.get(parament));
				objectNode.put("url", url);
			}
			return arrayNode.toString();
		}
		return json;
	}

	public static String optString(ObjectNode node, String key,
			String defaultValue) {
		JsonNode jsonNode = node.get(key);
		if (jsonNode == null)
			return defaultValue;
		else
			return jsonNode.asText();
	}

	public static void main(String[] args) throws JsonProcessingException,
			IOException {
		String a = "{\"phote\":\"/b/c.png\"}";
		JsonNode node = StringToJsonNode(a);
		System.out.println(node.getNodeType());
	}

	public static ObjectNode warpMessageResponse(int code, String msg) {
		ObjectNode returnV = createObjectNode();
		returnV.put("code", code);
		if (code > 0) {
			returnV.put("response", msg);
		} else {
			returnV.put("reason", msg);
		}
		return returnV;
	}


	/**
	 * 
	 * @param jsonStrings
	 *            map<dateTime,jsonContent>
	 * @param pageNum
	 * @param size
	 * @param key
	 * @param value
	 * @return
	 */
	public static String getDateListJsonResponse(List<String> jsonStrings,
			int pageNum, int pageSize, String key, long totalNumber) {
		// ObjectNode objectNode = createObjectNode();
		// ObjectNode response = createObjectNode();
		List<JsonNode> list = new ArrayList<JsonNode>();
		try {
			for (String item : jsonStrings)
				list.add(StringToJsonNode(item));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getSizeAndListStatObjectNode(pageNum, list, pageSize, key,
				totalNumber).toString();
	}

	public static ObjectNode getSizeAndListStatObjectNode(Integer pageNum,
			List<?> list, Integer pageSize, String key, long totalNumber) {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("code", 1);
		ObjectNode response = createObjectNode();
		response.put("pageNum", pageNum);
		response.put("pageSize", pageSize);
		response.put(key, totalNumber);
		response.put("list", JsonUtil.objectToJsonNode(list));
		objectNode.put("response", response);
		return objectNode;
	}

	public static Object getSizeAndListObjectNode(List<?> list) {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("code", 1);
		ObjectNode response = createObjectNode();
		response.put("list", JsonUtil.objectToJsonNode(list));
		objectNode.put("response", response);
		return objectNode;
	}

	public static ObjectNode getMapListObjectNode(Map<String, Object> datas) {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("code", 1);
		ObjectNode response = createObjectNode();
		for (String key : datas.keySet())
			response.put(key, JsonUtil.objectToJsonNode(datas.get(key)));
		objectNode.put("response", response);
		return objectNode;
	}

	public static ObjectNode getObjectResponse(Object obj) {
		ObjectNode objectNode = createObjectNode();
		objectNode.put("code", 1);
		objectNode.put("response", JsonUtil.objectToJsonNode(obj));
		return objectNode;
	}

}
