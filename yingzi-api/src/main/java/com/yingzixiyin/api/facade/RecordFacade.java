package com.yingzixiyin.api.facade;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public interface RecordFacade {

    /**
     * 增加，id字段不要填写
     * @param recordInfo 新增信息
     * @return 结果
     */
    public BaseResponseDto add(RecordInfo recordInfo);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     * @param recordInfo 新信息
     * @return 结果
     */
    public BaseResponseDto update(RecordInfo recordInfo);

    /**
     * 查询一个
     * @param requestDto 按照所填字段去查询
     * @return RecordInfo
     */
    public RecordInfo queryOne(RecordQueryRequestDto requestDto);

    /**
     * 查询多个
     * @param requestDto 按照所填字段去查询
     * @return RecordInfoList
     */
    public RecordQueryResponseDto query(RecordQueryRequestDto requestDto);
}
