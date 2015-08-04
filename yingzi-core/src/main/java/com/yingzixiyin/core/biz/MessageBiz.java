package com.yingzixiyin.core.biz;

import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;

import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-04
 */
public interface MessageBiz {

    /**
     * 增加
     */
    public void add(MessageInfo messageInfo);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     */
    public void update(MessageInfo messageInfo);

    public MessageQueryResponseDto getMessageList(MessageQueryRequestDto requestDto);

    public MessageInfo getMessage(MessageQueryRequestDto requestDto);

	public List<Map<String, Object>> queryConsultantAndMessageCountByUserId(
			Long userId);


}
