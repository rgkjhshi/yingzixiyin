package com.yingzixiyin.core.entity;

/**
 * 用户
 * @author song.shi
 * @date 2015-07-02
 */

public class User {

    private Long id;            // 主键id
    private String openId;      // 微信用户的标识id
    private String phone;       // 电话号码
    private String password;    // 密码
    private Integer isBind;     // 是否绑定手机, 0:未绑定, 1:已绑定
    private String collected;   // 收藏的咨询师id列表, 如:1,2,3,
    private String visited;     // 咨询过的咨询师id列表, 如:1,2,3

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

    public Integer getIsBind() {
        return isBind;
    }

    public void setIsBind(Integer isBind) {
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
}
