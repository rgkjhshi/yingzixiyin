package com.yingzixiyin.page;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author lkzlee
 *
 */
public class Pagination {
    private String url; // 页码url
    private int pageSize = 10;  // 每页显示记录数
    private int currentPage = 1;    // 当前页码
    private int maxPage;    // 最大页数
    private Long maxCount = 0l;    // 最大数目
    private Map<String,Object> params=new HashMap<String, Object>();
    public Long getMaxCount() {
		return maxCount;
	}

	private void setMaxCount(Long maxCount) {
		if(maxCount<0) maxCount=0l;
		maxPage=(int)((maxCount-1)/pageSize+1);
		this.maxCount = maxCount;
	}
    // 获取offset
    public int getOffset() {
        return (currentPage - 1) * pageSize;
    }
    // 获取limit
    public int getLimit() {
        return getPageSize();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    private void setCurrentPage(int currentPage) {
        if (currentPage < 1)
            currentPage = 1;
        if (currentPage > maxPage)
            currentPage = maxPage;
        this.currentPage = currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }
	public void setMaxCountAndCurrentPage(Long count, int pageNum) {
		setMaxCount(count);
		setCurrentPage(pageNum);
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public void putParams(String key,Object value) {
		this.params.put(key, value);
	}
	private String generateParamString(Map<String, Object> params){
		StringBuffer sb=new StringBuffer();
		sb.append("?x="+1);
		for(Entry<String, Object> kt:params.entrySet()){
			sb.append("&"+kt.getKey()+"="+kt.getValue());
		}
		return sb.toString();
	}
	@Override
	public String toString() {
		if(maxCount==0||maxPage==1){
			return "";
		}
		String paramString=generateParamString(params);
		String furl=url+paramString;
		return "<div class='pagediv'><span>第"+currentPage+"页/共"+maxPage+"页</span>"
				+ "<a href='"+furl+"&pageNum=1'>首页</a><a href='"+furl+
				"&pageNum="+(currentPage-1)+"'>上一页</a><a href='"+furl+
				"&pageNum="+(currentPage+1)+"'>下一页</a><a href='"+furl+"&pageNum="+maxPage+"'>尾页</a>"
						+ "</div>";
	}
	
}