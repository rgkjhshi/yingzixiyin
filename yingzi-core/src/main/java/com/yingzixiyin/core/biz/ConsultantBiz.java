package com.yingzixiyin.core.biz;

import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.ConsultantRequestDto;

/**
 * @author song.shi
 * @date 2015-07-04
 */
public interface ConsultantBiz {

    public ConsultantQueryResponseDto getConsultantList(ConsultantRequestDto requestDto);

    public ConsultantQueryResponseDto getConsultantListByIds(String ids);

}
