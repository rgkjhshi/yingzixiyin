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

public class ConsultantInfo implements Serializable {
    private static final long serialVersionUID = -5554209037777700465L;

    private Long id;               // 主键id
    private String phone;          // 电话
    private String email;          // 邮箱
    private String password;       // 密码
    private String alipay;         // 支付宝
    private String nickname;       // 昵称
    private String name;           // 姓名
    private GenderTypeEnum gender; // 性别
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
    private RangeTypeEnum rangeType;     // 咨询类型
    private String avatar;         // 头像url
    private StatusEnum status;     // 状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
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

    public GenderTypeEnum getGender() {
        return gender;
    }

    public void setGender(GenderTypeEnum gender) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RangeTypeEnum getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeTypeEnum rangeType) {
        this.rangeType = rangeType;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

	public static Map<String, Object> toMap(ConsultantInfo info) {
        if (null == info) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", info.getId());
        map.put("phone", info.getPhone());
        map.put("email", info.getEmail());
        map.put("alipay", info.getAlipay());
        map.put("nickname", info.getNickname());
        map.put("name", info.getName());
        map.put("gender", null == info.getGender() ? 0 : info.getGender().getValue());
        map.put("age", info.getAge());
        map.put("address", info.getAddress());
        map.put("speciality", info.getSpeciality());
        map.put("professional", info.getProfessional());
        map.put("background", info.getBackground());
        map.put("bookTime", info.getBookTime());
        map.put("price", info.getPrice());
        map.put("videoPrice", info.getVideoPrice());
        map.put("facePrice", info.getFacePrice());
        map.put("introduce", info.getIntroduce());
        map.put("signature", info.getSignature());
        map.put("rangeType", null == info.getRangeType() ? 0 : info.getRangeType().getValue());
        map.put("avatar", info.getAvatar());
        map.put("status", null == info.getStatus() ? 0 : info.getStatus().getValue());
        return map;
    }

    @Override
    public String toString() {
        return "ConsultantInfo{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", alipay='" + alipay + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", speciality='" + speciality + '\'' +
                ", professional='" + professional + '\'' +
                ", background='" + background + '\'' +
                ", bookTime='" + bookTime + '\'' +
                ", price=" + price +
                ", facePrice=" + facePrice +
                ", videoPrice=" + videoPrice +
                ", introduce='" + introduce + '\'' +
                ", signature='" + signature + '\'' +
                ", avatar='" + avatar + '\'' +
                ", rangeType=" + (null == rangeType ? null : rangeType.getDesc()) +
                ", status=" + (null == status ? null : status.getDesc()) +
                '}';
    }
}
