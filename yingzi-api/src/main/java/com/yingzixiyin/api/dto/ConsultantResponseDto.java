package com.yingzixiyin.api.dto;

import java.io.Serializable;
import java.util.List;

public class ConsultantResponseDto implements Serializable {
	private Integer returnCode;
	private String returnMessage;
	private List<UserConsultantInfo> list;
	public Integer getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public List<UserConsultantInfo> getList() {
		return list;
	}
	public void setList(List<UserConsultantInfo> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "ConsultantResponseDto [returnCode=" + returnCode
				+ ", returnMessage=" + returnMessage + ", list=" + list + "]";
	}
}
