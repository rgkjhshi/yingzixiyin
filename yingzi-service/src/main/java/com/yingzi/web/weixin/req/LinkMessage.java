package com.yingzi.web.weixin.req;

/**
 * 
 * @author liutao
 * @date 2014-3-17
 *
 */
public class LinkMessage extends BaseMessage {

	private String Title;
	
	private String Description;
	
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
	
}
