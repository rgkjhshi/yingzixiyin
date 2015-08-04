package com.yingzixiyin.core.entity;

import com.google.common.collect.Lists;
import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import org.apache.ibatis.type.Alias;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 聊天消息
 *
 * @author song.shi
 * @date 2015-07-02
 */

@Alias("Message")
public class Message {

    private Long id;               // 主键id
    private Long recordId;         // 咨询记录id
    private String message;        // 消息内容
    private String fromPhone;      // 发送者手机号
    private String toPhone;        // 接收者手机号
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

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
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

    public static Message getBean(MessageQueryRequestDto requestDto) {
        if (null == requestDto) {
            return null;
        }
        Message message = new Message();
        message.setId(requestDto.getId());
        message.setRecordId(requestDto.getRecordId());
        message.setFromPhone(requestDto.getFromPhone());
        message.setToPhone(requestDto.getToPhone());
        message.setCreateTime(requestDto.getCreateTime());
        message.setIsRead(null == requestDto.getIsRead() ? null : requestDto.getIsRead().getValue());
        return message;
    }


    public static Message getBean(MessageInfo messageInfo) {
        if (null == messageInfo) {
            return null;
        }
        Message message = new Message();
        message.setId(messageInfo.getId());
        message.setRecordId(messageInfo.getRecordId());
        message.setMessage(messageInfo.getMessage());
        message.setFromPhone(messageInfo.getFromPhone());
        message.setToPhone(messageInfo.getToPhone());
        message.setCreateTime(messageInfo.getCreateTime());
        message.setIsRead(null == messageInfo.getIsRead() ? null : messageInfo.getIsRead().getValue());
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

    public static MessageInfo translateBean(Message message) {
        MessageInfo msgInfo = new MessageInfo();
        msgInfo.setId(message.getId());
        msgInfo.setRecordId(message.getRecordId());
        msgInfo.setMessage(message.getMessage());
        msgInfo.setFromPhone(message.getFromPhone());
        msgInfo.setToPhone(message.getToPhone());
        msgInfo.setCreateTime(message.getCreateTime());
        msgInfo.setIsRead(YesOrNoEnum.toEnum(message.getIsRead()));
        return msgInfo;
    }
}
