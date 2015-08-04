package com.yingzixiyin.api.facade;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.MessageInfo;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;

import java.util.List;
import java.util.Map;

/***
 * 
 * @author lkzlee
 *
 */
public interface MessageFacade {

    /**
     * 增加，id字段不要填写
     * @param message 添加的信息
     * @return 结果
     */
    public BaseResponseDto add(MessageInfo message);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     * @param message 新信息
     * @return 结果
     */
    public BaseResponseDto update(MessageInfo message);

    /**
     * song.shi
     * 查询一个
     * @param requestDto 按照所填字段去查询
     * @return MessageInfo
     */
    public MessageInfo queryOne(MessageQueryRequestDto requestDto);

    /**
     * song.shi
     * 查询多个
     * @param requestDto 按照所填字段去查询
     * @return MessageQueryResponseDto
     */
    public MessageQueryResponseDto query(MessageQueryRequestDto requestDto);

	public List<Map<String, Object>> queryConsultantAndMessageCountByUserId(Long id);

}
