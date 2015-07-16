package com.yingzixiyin.api.dto;

import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.StatusEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class MessageQueryRequestDto implements Serializable {
    private static final long serialVersionUID = 1611119459947347588L;

    private Long id;               // 主键id
    private Long recordId;         // 咨询记录id
    private String message;        // 消息内容
    private Date createTime;       // 创建时间
    private YesOrNoEnum isRead;        // 是否已读 0未读 1已读
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
	public YesOrNoEnum getIsRead() {
		return isRead;
	}
	public void setIsRead(YesOrNoEnum isRead) {
		this.isRead = isRead;
	}
	@Override
	public String toString() {
		return "MessageQueryRequestDto [id=" + id + ", recordId=" + recordId
				+ ", message=" + message + ", createTime=" + createTime
				+ ", isRead=" + isRead + "]";
	}

}
