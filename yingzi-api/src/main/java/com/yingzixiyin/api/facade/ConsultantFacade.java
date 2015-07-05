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
     * @return
     */
    public ConsultantQueryResponseDto getConsultantListByRangeType(ConsultantRequestDto requestDto);

}
