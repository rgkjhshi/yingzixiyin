package com.yingzixiyin.api.facade;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.page.Pagination;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public interface RecordFacade {

    /**
     * song.shi
     * 增加，id字段不要填写
     * @param recordInfo 新增信息
     * @return 结果
     */
    public BaseResponseDto add(RecordInfo recordInfo);

    /**
     * song.shi
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     * @param recordInfo 新信息
     * @return 结果
     */
    public BaseResponseDto update(RecordInfo recordInfo);

    /**
     * song.shi
     * 查询一个
     * @param requestDto 按照所填字段去查询
     * @return RecordInfo
     */
    public RecordInfo queryOne(RecordQueryRequestDto requestDto);

    /**
     * song.shi
     * 查询多个
     * @param requestDto 按照所填字段去查询
     * @return RecordInfoList
     */
    public RecordQueryResponseDto query(RecordQueryRequestDto requestDto);

    /**
     * 查询数量
     * @param requestDto 查询条件
     * @return 数量
     */
	public Long queryCount(RecordQueryRequestDto requestDto);

	public RecordQueryResponseDto queryPage(RecordQueryRequestDto requestDto, Pagination page);
}
