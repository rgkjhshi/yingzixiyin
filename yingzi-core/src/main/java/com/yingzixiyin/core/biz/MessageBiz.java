package com.yingzixiyin.core.biz;

import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;

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


}
