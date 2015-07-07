package com.yingzi.web.weixin.event;

/**
 * 
 * @author liutao
 * @date 2014-3-19
 *
 */
public class QRCodeEvent extends BaseEvent {

	private String EventKey;
	
	private String Ticket;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	
}
