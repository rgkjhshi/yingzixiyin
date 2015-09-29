package com.yingzi.web.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.yingzixiyin.api.enums.ConsultTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;

public class ConsumeRecordsInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;                      // 主键id
    private Long userId;                  // 咨询者id
    private Long consultantId;            // 咨询师id
   
    private ConsultTypeEnum consultType;  // 咨询类型, 1:线上咨询, 2:线下预约
    private YesOrNoEnum isPaid;           // 是否付款, 0:未付款, 1:已付款
    private YesOrNoEnum isReplied;        // 是否回应，0:未回应, 1:已经回应
    private YesOrNoEnum isCompleted;      // 是否结束，0:未结束, 1:已结束
    private Date createTime;              // 创建时间
    
    //咨询师信息
    private String consultantName;		//咨询师名称
    private BigDecimal price;      // 咨询单价
    private BigDecimal facePrice; 
    private BigDecimal videoPrice; 
    private String introduce;      // 个人简介
    private String signature;      // 个性签名
    private RangeTypeEnum rangeType;     // 咨询类型
    private String avatar;         // 头像url
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
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public RangeTypeEnum getRangeType() {
		return rangeType;
	}
	public void setRangeType(RangeTypeEnum rangeType) {
		this.rangeType = rangeType;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public BigDecimal getFacePrice() {
		return facePrice;
	}
	public void setFacePrice(BigDecimal facePrice) {
		this.facePrice = facePrice;
	}
	public BigDecimal getVideoPrice() {
		return videoPrice;
	}
	public void setVideoPrice(BigDecimal videoPrice) {
		this.videoPrice = videoPrice;
	}
	@Override
	public String toString() {
		return "ConsumeRecordsInfo [id=" + id + ", userId=" + userId
				+ ", consultantId=" + consultantId + ", consultType="
				+ consultType + ", isPaid=" + isPaid + ", isReplied="
				+ isReplied + ", isCompleted=" + isCompleted + ", createTime="
				+ createTime + ", consultantName=" + consultantName
				+ ", price=" + price + ", facePrice=" + facePrice
				+ ", videoPrice=" + videoPrice + ", introduce=" + introduce
				+ ", signature=" + signature + ", rangeType=" + rangeType
				+ ", avatar=" + avatar + "]";
	}
}
