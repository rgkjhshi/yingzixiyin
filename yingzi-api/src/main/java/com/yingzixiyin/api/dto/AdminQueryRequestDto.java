package com.yingzixiyin.api.dto;

import java.io.Serializable;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class AdminQueryRequestDto implements Serializable {
    private static final long serialVersionUID = 9002499205593725352L;

    private Long id;            // 主键id
    private String username;    // 用户名
    private String password;    // 密码


    public Long getId() {
        return id;
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


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "AdminQueryRequestDto [id=" + id + ", username=" + username
				+ ", password=" + password + "]";
	}
   
}
