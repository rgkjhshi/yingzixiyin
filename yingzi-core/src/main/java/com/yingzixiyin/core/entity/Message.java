package com.yingzixiyin.core.entity;

import java.util.Date;

/**
 * 聊天消息
 * @author song.shi
 * @date 2015-07-02
 */

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
}
