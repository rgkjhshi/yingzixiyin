package com.yingzixiyin.core.biz.impl;

import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.core.biz.RecordBiz;
import com.yingzixiyin.core.entity.Record;
import com.yingzixiyin.core.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-04
 */

@Component("recordBiz")
public class RecordBizImpl implements RecordBiz {
    private static final Logger logger = LoggerFactory.getLogger(RecordBizImpl.class);

    @Resource
    RecordService recordService;

    @Override
    public void add(RecordInfo recordInfo) {
        // 增
        recordService.insert(Record.getBean(recordInfo));
    }

    @Override
    public void update(RecordInfo recordInfo) {
        // 改
        recordService.update(Record.getBean(recordInfo));
    }

    @Override
    public RecordInfo getRecord(RecordQueryRequestDto requestDto) {
        return recordService.getRecord(Record.getBean(requestDto));
    }

    @Override
    public RecordQueryResponseDto getRecordList(RecordQueryRequestDto requestDto) {

        List<RecordInfo> recordInfoList;
        // 查询
        recordInfoList = recordService.getRecordList(Record.getBean(requestDto));
        // 返回responseDto
        RecordQueryResponseDto responseDto = new RecordQueryResponseDto();
        if (!CollectionUtils.isEmpty(recordInfoList)) {
            responseDto.setRecordInfoList(recordInfoList);
            responseDto.setCount(recordInfoList.size());
        }
        return responseDto;
    }
}
