package com.yingzixiyin.core.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.MessageInfo;

import java.util.Date;
import java.util.List;

/**
 * 聊天消息
 * @author song.shi
 * @date 2015-07-02
 */

@Alias("Message")
public class Message {

    private Long id;               // 主键id
    private Long recordId;         // 咨询记录id
    private String message;        // 消息内容
    private Date createTime;       // 创建时间
    private Integer isRead;        // 是否已读

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

	public static Message getBean(MessageInfo messageInfo) {
		if (null == messageInfo) {
            return null;
        }
		Message message=new Message();
		message.setCreateTime(messageInfo.getCreateTime());
		message.setId(messageInfo.getId());
		message.setIsRead(messageInfo.getIsRead());
		message.setMessage(messageInfo.getMessage());
		message.setRecordId(messageInfo.getRecordId());
		return message;
	}

	public static List<MessageInfo> translateBeanList(List<Message> messageList) {
		 if (CollectionUtils.isEmpty(messageList)) {
	            return null;
	        }
	        List<MessageInfo> messageInfoList = Lists.newArrayList();
	        for (Message message : messageList) {
	        	messageInfoList.add(translateBean(message));
	        }
	        return messageInfoList;
	}

	private static MessageInfo translateBean(Message message) {
		MessageInfo msgInfo=new MessageInfo();
		msgInfo.setCreateTime(message.getCreateTime());
		msgInfo.setId(message.getId());
		msgInfo.setIsRead(message.getIsRead());
		msgInfo.setMessage(message.getMessage());
		msgInfo.setRecordId(message.getRecordId());
		return msgInfo;
	}
}
