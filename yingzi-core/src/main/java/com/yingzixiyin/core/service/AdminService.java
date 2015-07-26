package com.yingzixiyin.core.service;


import java.util.List;

import com.yingzixiyin.api.dto.AdminInfo;
import com.yingzixiyin.api.dto.AdminQueryResponseDto;
import com.yingzixiyin.core.entity.Admin;

/**
 * 
 * @author lkzlee
 *
 */
public interface AdminService {

	Integer insert(Admin admin);

	Integer update(Admin admin);

	AdminInfo getAdminUser(Admin admin);

	List<AdminInfo> getAdminUserList(Admin admin);

}
