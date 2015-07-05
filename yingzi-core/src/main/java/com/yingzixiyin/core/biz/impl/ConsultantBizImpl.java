package com.yingzixiyin.core.biz.impl;

import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.ConsultantRequestDto;
import com.yingzixiyin.core.biz.ConsultantBiz;
import com.yingzixiyin.core.service.ConsultantService;
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

@Component("ConsultantBiz")
public class ConsultantBizImpl implements ConsultantBiz {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantBizImpl.class);

    @Resource
    ConsultantService consultantService;

    @Override
    public ConsultantQueryResponseDto getConsultantListByRangeType(ConsultantRequestDto requestDto) {
        logger.info("ConsultantBizImpl#getConsultantListByRangeType 接收数据:" + requestDto);
        // 查询数据库
        List<ConsultantInfo> consultantInfoList = consultantService.getConsultantList(requestDto.getRangeType().getValue());
        // 返回responseDto
        ConsultantQueryResponseDto responseDto = new ConsultantQueryResponseDto();
        if (!CollectionUtils.isEmpty(consultantInfoList)) {
            responseDto.setConsultantList(consultantInfoList);
            responseDto.setCount(consultantInfoList.size());
        }
        return responseDto;
    }
}
