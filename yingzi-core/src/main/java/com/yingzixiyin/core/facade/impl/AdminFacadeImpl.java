package com.yingzixiyin.core.facade.impl;

import com.yingzixiyin.api.dto.AdminInfo;
import com.yingzixiyin.api.dto.AdminQueryRequestDto;
import com.yingzixiyin.api.dto.AdminQueryResponseDto;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.api.dto.UserQueryRequestDto;
import com.yingzixiyin.api.facade.AdminFacade;
import com.yingzixiyin.api.facade.UserFacade;
import com.yingzixiyin.core.biz.AdminBiz;
import com.yingzixiyin.core.biz.UserBiz;
import com.yingzixiyin.core.utils.ParameterUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 
 * @author lkzlee
 * 
 */
@Component("adminFacade")
public class AdminFacadeImpl implements AdminFacade {
	private static final Logger logger = LoggerFactory
			.getLogger(AdminFacadeImpl.class.getName());

	@Resource
	AdminBiz adminBiz;

	/**
	 * 添加注册管理员
	 */
	@Override
	public BaseResponseDto add(AdminInfo admin) {
		logger.info("收到参数:" + admin);
		BaseResponseDto responseDto = new BaseResponseDto();
		try {
			ParameterUtils.notNull(admin, "admin不能为null");
			ParameterUtils.notNull(admin.getUsername(), "username不能为null");
			ParameterUtils.notNull(admin.getPassword(), "password不能为null");
			adminBiz.add(admin);
			responseDto.setReturnCode(0);
			responseDto.setReturnMessage("添加、注册管理员成功!");
		} catch (IllegalArgumentException e) {
			logger.info("参数异常:" + e.getMessage());
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("捕获异常:" + e.getMessage(), e);
			responseDto.setReturnCode(-1);
		}
		logger.info("返回数据:" + responseDto);
		return responseDto;
	}

	/**
	 * 更新管理信息操作
	 */
	@Override
	public BaseResponseDto update(AdminInfo admin) {
		logger.info("收到参数:" + admin);
		BaseResponseDto responseDto = new BaseResponseDto();
		try {
			ParameterUtils.notNull(admin, "admin不能为null");
			ParameterUtils.notNull(admin.getId(), "id不能为null");
			/*
			 * ParameterUtils.notNull(admin.getUsername(), "username不能为null");
			 * ParameterUtils.notNull(admin.getPassword(), "password不能为null");
			 */
			adminBiz.update(admin);
			responseDto.setReturnCode(0);
			responseDto.setReturnMessage("修改管理员信息成功!");
		} catch (IllegalArgumentException e) {
			logger.info("参数异常:" + e.getMessage());
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("捕获异常:" + e.getMessage(), e);
			responseDto.setReturnCode(-1);
		}
		logger.info("返回数据:" + responseDto);
		return responseDto;
	}

	@Override
	public AdminInfo queryOne(AdminQueryRequestDto requestDto) {
		logger.info("收到参数:" + requestDto);
		AdminInfo adminInfo = null;
		try {
			ParameterUtils.notNull(requestDto, "AdminQueryRequestDto不能为null");
			adminInfo = adminBiz.getAdminUser(requestDto);
		} catch (IllegalArgumentException e) {
			logger.info("参数异常:" + e.getMessage());
		} catch (Exception e) {
			logger.error("捕获异常:" + e.getMessage(), e);
		}
		logger.info("返回数据:" + adminInfo);
		return adminInfo;
	}

	@Override
	public AdminQueryResponseDto query(AdminQueryRequestDto requestDto) {
		logger.info("收到参数:" + requestDto);
        AdminQueryResponseDto responseDto = new AdminQueryResponseDto();
        try {
            ParameterUtils.notNull(requestDto, "AdminQueryRequestDto不能为null");
            responseDto = adminBiz.getAdminList(requestDto);
            responseDto.setReturnCode(0);
            responseDto.setReturnMessage("查询得到管理员列表成功!");
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
            responseDto.setReturnCode(-1);
            responseDto.setReturnMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
            responseDto.setReturnCode(-1);
        }
        logger.info("返回数据:" + responseDto);
        return responseDto;
	}

}
