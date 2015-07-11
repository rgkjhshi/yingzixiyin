package com.yingzixiyin.api.dto;

import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.StatusEnum;

import java.io.Serializable;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class ConsultantQueryRequestDto implements Serializable {
    private static final long serialVersionUID = 1611119459947347588L;

    private Long id;               // 主键id
    private String username;       // 用户名
    private String password;       // 密码
    private String phone;          // 电话
    private String email;          // 邮箱
    private String name;           // 姓名
    private GenderTypeEnum gender; // 性别
    private Integer minAge;        // 最小年龄
    private Integer maxAge;        // 最大年龄
    private RangeTypeEnum rangeType;     // 咨询类型
    private StatusEnum status;     // 状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
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

    @Override
    public String toString() {
        return "ConsultantRequestDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", rangeType=" + (null == rangeType ? null : rangeType.getDesc()) +
                ", status=" + (null == status ? null : status.getDesc()) +
                '}';
    }
}
