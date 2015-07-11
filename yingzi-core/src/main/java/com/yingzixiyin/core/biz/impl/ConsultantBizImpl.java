package com.yingzixiyin.core.biz.impl;

import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.core.biz.ConsultantBiz;
import com.yingzixiyin.core.entity.Consultant;
import com.yingzixiyin.core.service.ConsultantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public void add(ConsultantInfo consultantInfo) {
        // 增
        consultantService.insert(Consultant.getBean(consultantInfo));
    }

    @Override
    public void update(ConsultantInfo consultantInfo) {
        // 改
        consultantService.update(Consultant.getBean(consultantInfo));
    }

    @Override
    public ConsultantQueryResponseDto getConsultantList(ConsultantQueryRequestDto requestDto) {
        List<ConsultantInfo> consultantInfoList = Collections.emptyList();
        // 按年龄范围查询
        if (null != requestDto.getMinAge() || null != requestDto.getMaxAge()) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("gender", null == requestDto.getGender() ? null : requestDto.getGender().getValue());
            map.put("status", null == requestDto.getStatus() ? null : requestDto.getStatus().getValue());
            map.put("minAge", requestDto.getMinAge());
            map.put("maxAge", requestDto.getMaxAge());
            consultantInfoList = consultantService.getConsultantList(map);
        } else {
            consultantInfoList = consultantService.getConsultantList(Consultant.getBean(requestDto));
        }
        // 返回responseDto
        ConsultantQueryResponseDto responseDto = new ConsultantQueryResponseDto();
        if (!CollectionUtils.isEmpty(consultantInfoList)) {
            responseDto.setConsultantList(consultantInfoList);
            responseDto.setCount(consultantInfoList.size());
        }
        return responseDto;
    }

    @Override
    public ConsultantQueryResponseDto getConsultantListByIds(String ids) {
        // 查询
        List<ConsultantInfo> consultantInfoList = consultantService.getConsultantList(ids);
        // 返回responseDto
        ConsultantQueryResponseDto responseDto = new ConsultantQueryResponseDto();
        if (!CollectionUtils.isEmpty(consultantInfoList)) {
            responseDto.setConsultantList(consultantInfoList);
            responseDto.setCount(consultantInfoList.size());
        }
        return responseDto;
    }
}
