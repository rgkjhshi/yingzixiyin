package com.yingzixiyin.core.dao;

import com.yingzixiyin.core.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Repository("messageDao")
public interface MessageDao {
	/**
	 * 添加
	 * @param message 新纪录
	 * @return 插入的主键
	 */
	Integer insert(Message message);

	/**
	 * 修改
	 * @param message id 字段不能为空
	 */
	void update(Message message);

    /**
     * 单个查询，确保查询条件能够得到唯一结果
     * @param message , 可填字段: id, recordId, fromPhone, toPhone, isRead
     * @return 符合条件的 Message
     */
    public Message getMessage(Message message);

    /**
     * 批量查询
     * @param message , 可填字段: id, recordId, fromPhone, toPhone, isRead
     * @return 符合条件的 Message 列表
     */
    public List<Message> getMessageList(Message message);


    /**
	 * 按条件批量查询
	 * @param map
	 * @return
	 */
	List<Message> getMessageListByFilter(Map<String, Object> map);
	List<Map<String, Object>> queryConsultantAndMessageCountByUserId(Map<String, Object> map);

}
