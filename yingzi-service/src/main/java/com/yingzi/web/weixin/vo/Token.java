package com.yingzi.web.weixin.vo;

/**
 * 
 * @author bulute
 * @date 2014-3-20
 *
 */
public class Token {
	
	private String accesstoken;
	private int expriseIn;
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public int getExpriseIn() {
		return expriseIn;
	}
	public void setExpriseIn(int expriseIn) {
		this.expriseIn = expriseIn;
	}

}
