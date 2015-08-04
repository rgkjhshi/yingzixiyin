package com.yingzixiyin.core.service;

import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.core.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-02
 */


public interface MessageService {

    /**
     * 增
     * @param message 消息
     * @return 插入的主键
     */
    public Integer insert(Message message);

    /**
     * 修改消息
     * @param message id必需，通过id去修改
     */
    public void update(Message message);

    /**
     * 查询一个
     * @param message 类型
     * @return 消息
     */
    public MessageInfo getMessage(Message message);

    /**
     * 查询多个
     * @param message 类型
     * @return 消息列表
     */
    public List<MessageInfo> getMessageList(Message message);


	public List<Map<String, Object>> queryConsultantAndMessageCountByUserId(
			Map<String, Object> map);

}
