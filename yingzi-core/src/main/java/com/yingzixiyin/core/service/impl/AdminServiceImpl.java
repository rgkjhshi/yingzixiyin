package com.yingzixiyin.core.service.impl;

import java.util.List;

import com.yingzixiyin.api.dto.AdminInfo;
import com.yingzixiyin.api.dto.UserInfo;
import com.yingzixiyin.core.dao.AdminDao;
import com.yingzixiyin.core.dao.UserDao;
import com.yingzixiyin.core.entity.Admin;
import com.yingzixiyin.core.entity.Record;
import com.yingzixiyin.core.entity.User;
import com.yingzixiyin.core.service.AdminService;
import com.yingzixiyin.core.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 
 * @author lkzlee
 *
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class.getName());

    @Resource
    AdminDao adminDao;

	@Override
	public Integer insert(Admin admin) {
		if(null==admin||StringUtils.isEmpty(admin.getUsername())){
			logger.info("username不能为空");
            return null;
		}
		return adminDao.insert(admin);
	}

	@Override
	public Integer update(Admin admin) {
		if(null==admin||StringUtils.isEmpty(admin.getUsername())){
			logger.info("username不能为空");
            return null;
		}
		return adminDao.update(admin);
	}

	@Override
	public AdminInfo getAdminUser(Admin admin) {
		if (null == admin) {
            logger.info("admin不能为null");
            return null;
        }
        Admin adres = adminDao.getAdminUser(admin);
		return Admin.translateBean(adres);
	}

	@Override
	public List<AdminInfo> getAdminUserList(Admin admin) {
		if (null == admin) {
            logger.info("admin不能为null");
            return null;
        }
        List<Admin> list = adminDao.getAdminUserList(admin);
		return Admin.translateBeanList(list);
	}

   
}
