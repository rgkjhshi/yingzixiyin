package com.yingzixiyin.api.facade;

import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.ConsultantRequestDto;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public interface ConsultantFacade {

    /**
     * 通过分类获取咨询师列表
     * @param requestDto rangeType字段必填
     * @return ConsultantQueryResponseDto
     */
    public ConsultantQueryResponseDto query(ConsultantRequestDto requestDto);

    /**
     * 通过id列表查询咨询师
     * @param ids id列表字符串，用","隔开
     * @return ConsultantQueryResponseDto
     */
    public ConsultantQueryResponseDto queryByIds(String ids);
}
