package com.yingzixiyin.api.dto;

import java.io.Serializable;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class UserQueryRequestDto implements Serializable {
    private static final long serialVersionUID = 9002499205593725352L;

    private Long id;               // 主键id
    private String openId;         // 来自微信的open_id
    private String phone;          // 电话
    private String password;       // 密码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserQueryRequestDto{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
