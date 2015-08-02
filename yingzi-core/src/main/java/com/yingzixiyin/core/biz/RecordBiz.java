package com.yingzixiyin.core.biz;

import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.page.Pagination;

/**
 * @author song.shi
 * @date 2015-07-04
 */
public interface RecordBiz {

    /**
     * 增加
     */
    public void add(RecordInfo recordInfo);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     */
    public void update(RecordInfo recordInfo);

    public RecordInfo getRecord(RecordQueryRequestDto requestDto);

    public RecordQueryResponseDto getRecordList(RecordQueryRequestDto requestDto);

	public Long queryCount(RecordQueryRequestDto requestDto);

	public RecordQueryResponseDto queryRecordListPage(
			RecordQueryRequestDto requestDto, Pagination page);

}
