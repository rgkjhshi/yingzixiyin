package com.yingzixiyin.core.biz;

import com.yingzixiyin.api.dto.AdminInfo;
import com.yingzixiyin.api.dto.AdminQueryRequestDto;
import com.yingzixiyin.api.dto.AdminQueryResponseDto;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;

/***
 * 
 * @author lkzlee
 *
 */
public interface AdminBiz {

	void add(AdminInfo admin);

	void update(AdminInfo admin);

	AdminInfo getAdminUser(AdminQueryRequestDto requestDto);

	AdminQueryResponseDto getAdminList(AdminQueryRequestDto requestDto);

}
