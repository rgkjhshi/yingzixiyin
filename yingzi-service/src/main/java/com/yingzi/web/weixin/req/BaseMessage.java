package com.yingzi.web.weixin.req;
/**
 * 
 * @author liutao
 * @date 2014-3-17
 */
public class BaseMessage {
	
	private String ToUserName;
	
	private String FromUserName;
	
	private long CreateTime;
	
	private String MsgType;
	
	private long MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFormUserName() {
		return FromUserName;
	}

	public void setFormUserName(String formUserName) {
		FromUserName = formUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

}
