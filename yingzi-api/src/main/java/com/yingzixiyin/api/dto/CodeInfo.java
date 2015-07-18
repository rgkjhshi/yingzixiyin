package com.yingzixiyin.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class CodeInfo implements Serializable {
    private static final long serialVersionUID = 3810643542656981957L;

    private Long id;               // 主键id
    private String phone;          // 电话
    private String code;           // 验证码
    private Date updateTime;       // 更新时间

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CodeInfo{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
