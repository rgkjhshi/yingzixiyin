package com.yingzi.web.weixin.req;

/**
 * 
 * @author liutao
 *
 */
public class VideoMessage extends BaseMessage {
	
	private String MedioId;
	
	private String ThumbMediaId;

	public String getMedioId() {
		return MedioId;
	}

	public void setMedioId(String medioId) {
		MedioId = medioId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	

}
