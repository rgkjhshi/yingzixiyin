package com.yingzixiyin.api.dto;

/**
 * 用户
 * @author song.shi
 * @date 2015-07-02
 */

public class AdminInfo {



	private Long id;            // 主键id
    private String username;    // 用户名
    private String password;    // 密码

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
    @Override
	public String toString() {
		return "AdminInfo [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
}
