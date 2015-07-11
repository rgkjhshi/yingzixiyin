package com.yingzixiyin.api.dto;

import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class UserQueryResponseDto extends BaseResponseDto {
    private static final long serialVersionUID = 1426407809902789016L;

    private Integer count;    // list中元素个数
    private List<UserInfo> userList;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "ConsultantQueryResponseDto{" +
                "count=" + count +
                ", userList=" + userList +
                '}';
    }
}
