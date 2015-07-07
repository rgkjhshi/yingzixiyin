package com.yingzi.web.weixin.vo;

/**
 * 
 * @author bulute
 * @data 2014-4-10
 *
 */
public class TUser {

	private Integer id;
	private String userName;
	private String openId;
	private String userAddress;
	private String userTelephone;
	private Double userAmount;
	private String userStauts;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}
	public Double getUserAmount() {
		return userAmount;
	}
	public void setUserAmount(Double userAmount) {
		this.userAmount = userAmount;
	}
	public String getUserStauts() {
		return userStauts;
	}
	public void setUserStauts(String userStauts) {
		this.userStauts = userStauts;
	}
}
