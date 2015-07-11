package com.yingzixiyin.api.dto;

import com.yingzixiyin.api.enums.YesOrNoEnum;

import java.io.Serializable;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class UserInfo implements Serializable {
    private static final long serialVersionUID = -7284288258694624647L;

    private Long id;               // 主键id
    private String openId;         // 来自微信的open_id
    private String phone;          // 电话
    private String password;       // 密码
    private YesOrNoEnum isBind;    // 是否绑定了电话
    private String collected;      // 收藏过的
    private String visited;        // 访问过的

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

    public YesOrNoEnum getIsBind() {
        return isBind;
    }

    public void setIsBind(YesOrNoEnum isBind) {
        this.isBind = isBind;
    }

    public String getCollected() {
        return collected;
    }

    public void setCollected(String collected) {
        this.collected = collected;
    }

    public String getVisited() {
        return visited;
    }

    public void setVisited(String visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", phone='" + phone + '\'' +
                ", isBind=" + (null == isBind ? null : isBind.getDesc()) +
                ", collected='" + collected + '\'' +
                ", visited='" + visited + '\'' +
                '}';
    }

}
