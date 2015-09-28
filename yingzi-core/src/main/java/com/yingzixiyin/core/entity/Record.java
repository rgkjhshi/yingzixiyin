package com.yingzixiyin.core.entity;

import com.google.common.collect.Lists;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.enums.ConsultTypeEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import org.apache.ibatis.type.Alias;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

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
    private String recordNonce;	//咨询标记

    public static Record getBean(RecordQueryRequestDto requestDto) {
        if (null == requestDto) {
            return null;
        }
        Record record = new Record();
        record.setId(requestDto.getId());
        record.setUserId(requestDto.getUserId());
        record.setConsultantId(requestDto.getConsultantId());
        record.setConsultType(null == requestDto.getConsultType() ? null : requestDto.getConsultType().getValue());
        record.setIsPaid(null == requestDto.getIsPaid() ? null : requestDto.getIsPaid().getValue());
        record.setIsReplied(null == requestDto.getIsReplied() ? null : requestDto.getIsReplied().getValue());
        record.setIsCompleted(null == requestDto.getIsCompleted() ? null : requestDto.getIsCompleted().getValue());
        record.setRecordNonce(requestDto.getRecordNonce());
        return record;
    }

    public static Record getBean(RecordInfo info) {
        if (null == info) {
            return null;
        }
        Record record = new Record();
        record.setId(info.getId());
        record.setUserId(info.getUserId());
        record.setConsultantId(info.getConsultantId());
        record.setConsultType(null == info.getConsultType() ? null : info.getConsultType().getValue());
        record.setIsPaid(null == info.getIsPaid() ? null : info.getIsPaid().getValue());
        record.setIsReplied(null == info.getIsReplied() ? null : info.getIsReplied().getValue());
        record.setIsCompleted(null == info.getIsCompleted() ? null : info.getIsCompleted().getValue());
        record.setCreateTime(info.getCreateTime());
        record.setRecordNonce(info.getRecordNonce());
        return record;
    }

    /**
     * 将bean类型转换成api的dto中对应的的类型
     * @param record 需要转换的对象
     * @return 返回对应的类型
     */
    public static RecordInfo translateBean(Record record) {
        if (null == record) {
            return null;
        }
        RecordInfo info = new RecordInfo();
        info.setId(record.getId());
        info.setUserId(record.getUserId());
        info.setConsultantId(record.getConsultantId());
        info.setConsultType(ConsultTypeEnum.toEnum(record.getConsultType()));
        info.setIsPaid(YesOrNoEnum.toEnum(record.getIsPaid()));
        info.setIsReplied(YesOrNoEnum.toEnum(record.getIsReplied()));
        info.setIsCompleted(YesOrNoEnum.toEnum(record.getIsCompleted()));
        info.setCreateTime(record.getCreateTime());
        info.setRecordNonce(record.getRecordNonce());
        return info;
    }

    /**
     * 将bean的list转换成api的dto中对应的的类型的list
     * @param recordList 需要转换的beanList
     * @return 对应的类型的list
     */
    public static List<RecordInfo> translateBeanList(List<Record> recordList) {
        if (CollectionUtils.isEmpty(recordList)) {
            return null;
        }
        List<RecordInfo> recordInfoList = Lists.newArrayList();
        for (Record record : recordList) {
            recordInfoList.add(translateBean(record));
        }
        return recordInfoList;
    }


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

	public String getRecordNonce() {
		return recordNonce;
	}

	public void setRecordNonce(String recordNonce) {
		this.recordNonce = recordNonce;
	}
    
}
