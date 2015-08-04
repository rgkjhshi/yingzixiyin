package com.yingzixiyin.api.dto;

import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class MessageQueryResponseDto extends BaseResponseDto {
    private static final long serialVersionUID = 5628588447971673064L;

    private Integer count;    // list中元素个数
    private List<MessageInfo> messageInfoList;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

	public List<MessageInfo> getMessageInfoList() {
		return messageInfoList;
	}

	public void setMessageInfoList(List<MessageInfo> messageInfoList) {
		this.messageInfoList = messageInfoList;
	}

    @Override
    public String toString() {
        return "MessageQueryResponseDto{" +
                "count=" + count +
                ", messageInfoList=" + messageInfoList +
                '}';
    }
}
