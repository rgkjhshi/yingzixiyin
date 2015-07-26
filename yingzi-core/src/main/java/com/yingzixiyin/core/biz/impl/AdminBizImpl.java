package com.yingzixiyin.core.biz.impl;

import java.util.List;

import com.yingzixiyin.api.dto.AdminInfo;
import com.yingzixiyin.api.dto.AdminQueryRequestDto;
import com.yingzixiyin.api.dto.AdminQueryResponseDto;

import com.yingzixiyin.core.biz.AdminBiz;

import com.yingzixiyin.core.entity.Admin;

import com.yingzixiyin.core.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * 
 * @author lkzlee
 * 
 */
@Component("adminBiz")
public class AdminBizImpl implements AdminBiz {
	private static final Logger logger = LoggerFactory
			.getLogger(AdminBizImpl.class);

	@Resource
	AdminService adminService;

	@Override
	public void add(AdminInfo admin) {
		adminService.insert(Admin.getBean(admin));
	}

	@Override
	public void update(AdminInfo admin) {
		adminService.update(Admin.getBean(admin));
	}

	@Override
	public AdminInfo getAdminUser(AdminQueryRequestDto requestDto) {
		return adminService.getAdminUser(Admin.getBean(requestDto));
	}

	@Override
	public AdminQueryResponseDto getAdminList(AdminQueryRequestDto requestDto) {
		List<AdminInfo> adminInfoList = null;
		adminInfoList = adminService
				.getAdminUserList(Admin.getBean(requestDto));
		AdminQueryResponseDto aqrDto = new AdminQueryResponseDto();
		if (!CollectionUtils.isEmpty(adminInfoList)) {
			aqrDto.setAdminList(adminInfoList);
			aqrDto.setCount(adminInfoList.size());
			aqrDto.setReturnCode(0);
			aqrDto.setReturnMessage("查询管理员列表成功!");
		}
		return aqrDto;
	}

}
