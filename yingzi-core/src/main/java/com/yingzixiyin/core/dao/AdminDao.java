package com.yingzixiyin.core.dao;

import java.util.List;

import com.yingzixiyin.core.entity.Admin;
import com.yingzixiyin.core.entity.User;

import org.springframework.stereotype.Repository;
/**
 * 
 * @author lkzlee
 *
 */
@Repository("adminDao")
public interface AdminDao {

	Integer insert(Admin admin);

	Integer update(Admin admin);

	Admin getAdminUser(Admin admin);

	List<Admin> getAdminUserList(Admin admin);


}
