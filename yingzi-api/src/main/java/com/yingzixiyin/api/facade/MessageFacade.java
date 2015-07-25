package com.yingzixiyin.api.facade;

import java.util.List;
import java.util.Map;

import com.yingzixiyin.api.dto.*;

/***
 * 
 * @author lkzlee
 *
 */
public interface MessageFacade {

    /**
     * 增加，id字段不要填写
     * @param MessageInfo 添加的信息
     * @return 结果
     */
    public BaseResponseDto add(MessageInfo message);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     * @param MessageInfo 新信息
     * @return 结果
     */
    public BaseResponseDto update(MessageInfo message);

    /**
     * 查询一个一个用户的未读信息
     * @param requestDto 按照所填字段去查询
     * @return MessageQueryResponseDto
     */
    public MessageQueryResponseDto query(MessageQueryRequestDto requestDto);

	public List<Map<String, Object>> queryConsultantAndMessageCountByUserId(
			Long id);

}
