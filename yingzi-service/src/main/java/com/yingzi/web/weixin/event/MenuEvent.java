package com.yingzi.web.weixin.event;

/**
 * 
 * @author liutao
 * @date 2014-3-19
 *
 */
public class MenuEvent extends BaseEvent {

	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
