package com.yingzixiyin.core.entity;

import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import org.apache.ibatis.type.Alias;

/**
 * 用户
 *
 * @author song.shi
 * @date 2015-07-02
 */

@Alias("User")
public class User {

    private Long id;            // 主键id
    private String openId;      // 微信用户的标识id
    private String phone;       // 电话号码
    private String password;    // 密码
    private Integer isBind;     // 是否绑定手机, 0:未绑定, 1:已绑定
    private String collected;   // 收藏的咨询师id列表, 如:1,2,3,
    private String visited;     // 咨询过的咨询师id列表, 如:1,2,3

    public static User getBean(UserQueryRequestDto requestDto) {
        if (null == requestDto) {
            return null;
        }
        User user = new User();
        user.setId(requestDto.getId());
        user.setOpenId(requestDto.getOpenId());
        user.setPhone(requestDto.getPhone());
        user.setPassword(requestDto.getPassword());
        return user;
    }

    public static User getBean(UserInfo info) {
        if (null == info) {
            return null;
        }
        User user = new User();
        user.setId(info.getId());
        user.setOpenId(info.getOpenId());
        user.setPhone(info.getPhone());
        user.setPassword(info.getPassword());
        user.setIsBind(null == info.getIsBind() ? null : info.getIsBind().getValue());
        user.setCollected(info.getCollected());
        user.setVisited(info.getVisited());
        return user;
    }

    /**
     * 将bean类型转换成api的dto中对应的的类型
     * @param user 需要转换的对象
     * @return 返回对应的类型
     */
    public static UserInfo translateBean(User user) {
        if (null == user) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setOpenId(user.getOpenId());
        userInfo.setPhone(user.getPhone());
        userInfo.setIsBind(YesOrNoEnum.toEnum(user.getIsBind()));
        userInfo.setCollected(user.getCollected());
        userInfo.setVisited(user.getVisited());

        return userInfo;
    }

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
