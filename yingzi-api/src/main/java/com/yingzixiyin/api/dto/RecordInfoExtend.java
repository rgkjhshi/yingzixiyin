package com.yingzixiyin.api.dto;

import java.io.Serializable;

public class RecordInfoExtend extends RecordInfo implements Serializable{
    private static final long serialVersionUID = -7312810497528209823L;

    //咨询师信息
    private String consultantPhone;     // 咨询师手机号
    private String consultantName;		// 咨询师名称
    private String consultantAvatar;    // 头像url
    private int consultantUnReadMessageCount;  // 咨询师的未读消息条数

    // 用户信息
    private String userPhone;           // 用户手机号
    private String openId;              // 用户openId

    public String getConsultantPhone() {
        return consultantPhone;
    }

    public void setConsultantPhone(String consultantPhone) {
        this.consultantPhone = consultantPhone;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getConsultantAvatar() {
        return consultantAvatar;
    }

    public void setConsultantAvatar(String consultantAvatar) {
        this.consultantAvatar = consultantAvatar;
    }

    public int getConsultantUnReadMessageCount() {
        return consultantUnReadMessageCount;
    }

    public void setConsultantUnReadMessageCount(int consultantUnReadMessageCount) {
        this.consultantUnReadMessageCount = consultantUnReadMessageCount;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "RecordInfoExtend{" +
                super.toString() +
                "consultantPhone='" + consultantPhone + '\'' +
                ", consultantName='" + consultantName + '\'' +
                ", consultantAvatar='" + consultantAvatar + '\'' +
                ", consultantUnReadMessageCount='" + consultantUnReadMessageCount + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
