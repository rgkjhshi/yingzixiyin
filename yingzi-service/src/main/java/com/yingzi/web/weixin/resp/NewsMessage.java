package com.yingzi.web.weixin.resp;

import java.util.List;

/**
 * 
 * @author liutao
 * @date 2014-3-17
 *
 */
public class NewsMessage extends BaseMessage {

	private int ArticleCount;
	
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}
