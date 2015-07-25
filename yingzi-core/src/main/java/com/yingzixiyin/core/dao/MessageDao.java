package com.yingzixiyin.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yingzixiyin.core.entity.Consultant;
import com.yingzixiyin.core.entity.Message;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Repository("messageDao")
public interface MessageDao {
	/**
	 * 添加
	 * @param message
	 * @return
	 */
	Integer insert(Message message);
	/**
	 * 修改
	 * @param message
	 */
	void update(Message message);
	/**
	 * 按条件批量查询
	 * @param map
	 * @return
	 */
	List<Message> getMessageListByFilter(Map<String, Object> map);
	List<Map<String, Object>> queryConsultantAndMessageCountByUserId(Map<String, Object> map);

}
