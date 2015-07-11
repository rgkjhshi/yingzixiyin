package com.yingzixiyin.core.biz;

import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;

/**
 * @author song.shi
 * @date 2015-07-04
 */
public interface ConsultantBiz {

    /**
     * 增加
     */
    public void add(ConsultantInfo consultantInfo);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     */
    public void update(ConsultantInfo consultantInfo);

    public ConsultantQueryResponseDto getConsultantList(ConsultantQueryRequestDto requestDto);

    public ConsultantQueryResponseDto getConsultantListByIds(String ids);

}
