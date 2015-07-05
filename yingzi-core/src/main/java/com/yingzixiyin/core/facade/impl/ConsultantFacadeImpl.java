package com.yingzixiyin.core.facade.impl;

import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.dto.ConsultantRequestDto;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.core.biz.ConsultantBiz;
import com.yingzixiyin.core.utils.ParameterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-04
 */

@Component("ConsultantFacade")
public class ConsultantFacadeImpl implements ConsultantFacade {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantFacadeImpl.class.getName());

    @Resource
    ConsultantBiz consultantBiz;

    @Override
    public ConsultantQueryResponseDto getConsultantListByRangeType(ConsultantRequestDto requestDto) {
        logger.info("ConsultantFacade#getConsultantListByRangeType 收到数据:" + requestDto);
        ConsultantQueryResponseDto responseDto = new ConsultantQueryResponseDto();
        try {
            ParameterUtils.notNull(requestDto, "ConsultantRequestDto为null");
            ParameterUtils.notNull(requestDto.getRangeType(), "getRangeType为null");

            responseDto = consultantBiz.getConsultantListByRangeType(requestDto);
        } catch (IllegalArgumentException e) {
            logger.error("payCoin exception ,errMsg:" + e.getMessage(), e);
            responseDto.setReturnCode(-1);
            responseDto.setReturnMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("payCoin exception ,errMsg:" + e.getMessage(), e);
        }
        logger.info("payCoin, 返回参数  [" + responseDto + "]");
        return responseDto;
    }
}
