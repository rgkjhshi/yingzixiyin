package com.yingzixiyin.api.dto;

import com.yingzixiyin.api.enums.ConsultTypeEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;

import java.io.Serializable;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class RecordQueryRequestDto implements Serializable {
    private static final long serialVersionUID = -4524181623762558381L;

    private Long id;                      // 主键id
    private Long userId;                  // 咨询者id
    private Long consultantId;            // 咨询师id
    private ConsultTypeEnum consultType;  // 咨询类型, 1:线上咨询, 2:线下预约
    private YesOrNoEnum isPaid;           // 是否付款, 0:未付款, 1:已付款
    private YesOrNoEnum isReplied;        // 是否回应，0:未回应, 1:已经回应
    private YesOrNoEnum isCompleted;      // 是否结束，0:未结束, 1:已结束

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

    public ConsultTypeEnum getConsultType() {
        return consultType;
    }

    public void setConsultType(ConsultTypeEnum consultType) {
        this.consultType = consultType;
    }

    public YesOrNoEnum getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(YesOrNoEnum isPaid) {
        this.isPaid = isPaid;
    }

    public YesOrNoEnum getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(YesOrNoEnum isReplied) {
        this.isReplied = isReplied;
    }

    public YesOrNoEnum getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(YesOrNoEnum isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "RecordInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", consultantId=" + consultantId +
                ", isBind=" + (null == consultType ? null : consultType.getDesc()) +
                ", isBind=" + (null == isPaid ? null : isPaid.getDesc()) +
                ", isBind=" + (null == isReplied ? null : isReplied.getDesc()) +
                ", isBind=" + (null == isCompleted ? null : isCompleted.getDesc()) +
                '}';
    }
}
