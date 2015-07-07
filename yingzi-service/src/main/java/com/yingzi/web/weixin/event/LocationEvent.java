package com.yingzi.web.weixin.event;

/**
 * 
 * @author liutao
 * @date 2014-3-19
 *
 */
public class LocationEvent extends BaseEvent {

	private String Latitude;
	
	private String Longitude;
	
	private String Precision;

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}
}
