package com.yingzi.admin.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {
	public static void renderJsonText(HttpServletResponse response,String json) throws IOException{
		response.setHeader("Cache-Control", "no-cache");   
        response.setContentType("text/json;charset=UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out = response.getWriter();  
        out.write(json);
	}
	public static void renderXMLText(HttpServletResponse response,String xml) throws IOException{
		response.setHeader("Cache-Control", "no-cache");   
        response.setContentType("text/xml;charset=UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out = response.getWriter();  
        out.write(xml);
	}
}
