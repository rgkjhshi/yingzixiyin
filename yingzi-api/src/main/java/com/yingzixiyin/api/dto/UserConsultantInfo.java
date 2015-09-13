package com.yingzixiyin.api.dto;

import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.StatusEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class UserConsultantInfo implements Serializable {
    private static final long serialVersionUID = -5554209037777700465L;

    private Long id;               // 主键id
    private String phone;          // 电话
    private String email;          // 邮箱
//    private String password;       // 密码
//    private String alipay;         // 支付宝
    private String nickname;       // 昵称
    private String name;           // 姓名
    private Integer gender; // 性别
    private Integer age;           // 年龄
    private String address;        // 咨询地址
    private String speciality;     // 特长
    private String professional;   // 专业背景
    private String background;     // 受训背景
    private String bookTime;       // 可预定时间
    private BigDecimal price;      // 咨询单价
    private BigDecimal facePrice;      // 咨询单价
    private BigDecimal videoPrice;      // 咨询单价
    private String introduce;      // 个人简介
    private String signature;      // 个性签名
    private Integer rangeType;     // 咨询类型
    private String avatar;         // 头像url
    private Integer status;     // 状态
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getBookTime() {
		return bookTime;
	}
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	public Integer getRangeType() {
		return rangeType;
	}
	public void setRangeType(Integer rangeType) {
		this.rangeType = rangeType;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UserConsultantInfo [id=" + id + ", phone=" + phone + ", email="
				+ email + ", nickname=" + nickname + ", name=" + name
				+ ", gender=" + gender + ", age=" + age + ", address="
				+ address + ", speciality=" + speciality + ", professional="
				+ professional + ", background=" + background + ", bookTime="
				+ bookTime + ", price=" + price + ", facePrice=" + facePrice
				+ ", videoPrice=" + videoPrice + ", introduce=" + introduce
				+ ", signature=" + signature + ", rangeType=" + rangeType
				+ ", avatar=" + avatar + ", status=" + status + "]";
	}

 
	
}
