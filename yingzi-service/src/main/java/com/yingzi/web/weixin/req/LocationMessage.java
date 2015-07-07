package com.yingzi.web.weixin.req;

/**
 * 
 * @author liutao
 * @date 2014-3-17
 */
public class LocationMessage extends BaseMessage {

	private String Location_X;
	
	private String Location_Y;
	
	private String Scale;
	
	private String Label;

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String locationX) {
		Location_X = locationX;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String locationY) {
		Location_Y = locationY;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}
}
