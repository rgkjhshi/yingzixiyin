package com.yingzixiyin.core.service;

import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.core.entity.Consultant;
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
     * @return
     */
    public Integer insert(Message message);

    /**
     * 修改消息
     * @param message id必需，通过id去修改
     */
    public void update(Message message);

   

    /**
     * 查询消息
     * @param consultant 类型
     * @return 消息列表
     */
    public List<MessageInfo> getMessageList(Map<String, Object> map);

}
