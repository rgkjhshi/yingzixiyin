package com.yingzixiyin.core.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.yingzixiyin.api.dto.AdminInfo;
import com.yingzixiyin.api.dto.AdminQueryRequestDto;

/**
 * 用户
 * @author song.shi
 * @date 2015-07-02
 */

public class Admin {

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

	public static Admin getBean(AdminInfo adminInfo) {
		Admin admin=new Admin();
		admin.setId(adminInfo.getId());
		admin.setUsername(adminInfo.getUsername());
		admin.setPassword(adminInfo.getPassword());
		return admin;
	}

	public static Admin getBean(AdminQueryRequestDto requestDto) {
		Admin admin=new Admin();
		admin.setId(requestDto.getId());
		admin.setUsername(requestDto.getUsername());
		admin.setPassword(requestDto.getPassword());
		return admin;
	}

	public static AdminInfo translateBean(Admin adres) {
		if(adres==null) return null;
		AdminInfo adminInfo=new AdminInfo();
		adminInfo.setId(adres.getId());
		adminInfo.setUsername(adres.getUsername());
		adminInfo.setPassword(adres.getPassword());
		return adminInfo;
	}

	public static List<AdminInfo> translateBeanList(List<Admin> list) {
		List<AdminInfo> res=Lists.newArrayList();
		for(Admin admin:list){
			res.add(translateBean(admin));
		}
		return res;
	}
}
