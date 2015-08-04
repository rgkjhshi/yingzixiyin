package com.yingzixiyin.api.dto;

import java.io.Serializable;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class BaseResponseDto implements Serializable {
    private static final long serialVersionUID = -2610710908896211500L;

    private Integer returnCode;    // 响应码，0表示成功

    private String returnMessage;  // 响应信息

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public boolean isSuccess() {
        return getReturnCode().equals(0);
    }

    @Override
    public String toString() {
        return "BaseResponseDto: {returnCode=" + returnCode +
                ", returnMessage=" + returnMessage + "}";
    }

}
