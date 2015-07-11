package com.yingzixiyin.core.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 咨询记录
 * @author song.shi
 * @date 2015-07-02
 */

@Alias("Record")
public class Record {

    private Long id;               // 主键id
    private Long userId;           // 咨询者id
    private Long consultantId;     // 咨询师id
    private Integer consultType;   // 咨询类型, 1:线上咨询, 2:线下预约
    private Integer isPaid;        // 是否付款, 0:未付款, 1:已付款
    private Integer isReplied;     // 是否回应，0:未回应, 1:已经回应
    private Integer isCompleted;   // 是否结束，0:未结束, 1:已结束
    private Date createTime;       // 创建时间


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Long consultantId) {
        this.consultantId = consultantId;
    }

    public Integer getConsultType() {
        return consultType;
    }

    public void setConsultType(Integer consultType) {
        this.consultType = consultType;
    }

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    public Integer getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(Integer isReplied) {
        this.isReplied = isReplied;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
