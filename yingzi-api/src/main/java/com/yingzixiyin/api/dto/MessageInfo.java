package com.yingzixiyin.api.dto;

import com.yingzixiyin.api.enums.YesOrNoEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天消息
 *
 * @author song.shi
 * @date 2015-07-02
 */


public class MessageInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;               // 主键id
    private Long recordId;         // 咨询记录id
    private String message;        // 消息内容
    private String fromPhone;      // 发送者手机号
    private String toPhone;        // 接收者手机号
    private Date createTime;       // 创建时间
    private YesOrNoEnum isRead;    // 是否已读

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

    public YesOrNoEnum getIsRead() {
        return isRead;
    }

    public void setIsRead(YesOrNoEnum isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "id=" + id +
                ", recordId=" + recordId +
                ", message='" + message + '\'' +
                ", fromPhone='" + fromPhone + '\'' +
                ", toPhone='" + toPhone + '\'' +
                ", createTime=" + createTime +
                ", isRead=" + (null == isRead ? "NO" : isRead.getDesc()) +
                '}';
    }
}
