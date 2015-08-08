package com.yingzixiyin.core.facade.impl;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.api.facade.RecordFacade;
import com.yingzixiyin.core.biz.RecordBiz;
import com.yingzixiyin.core.utils.ParameterUtils;
import com.yingzixiyin.page.Pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-07-04
 */

@Component("recordFacade")
public class RecordFacadeImpl implements RecordFacade {
    private static final Logger logger = LoggerFactory.getLogger(RecordFacadeImpl.class.getName());

    @Resource
    RecordBiz recordBiz;

    @Override
    public BaseResponseDto add(RecordInfo recordInfo) {
        logger.info("收到参数:" + recordInfo);
        BaseResponseDto responseDto = new BaseResponseDto();
        try {
            ParameterUtils.notNull(recordInfo, "recordInfo不能为null");
            ParameterUtils.notNull(recordInfo.getUserId(), "userId不能为null");
            ParameterUtils.notNull(recordInfo.getConsultantId(), "consultantId不能为null");
            recordBiz.add(recordInfo);
            responseDto.setReturnCode(0);
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
            responseDto.setReturnCode(-1);
            responseDto.setReturnMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
            responseDto.setReturnCode(-1);
        }
        logger.info("返回数据:" + responseDto);
        return responseDto;
    }

    @Override
    public BaseResponseDto update(RecordInfo recordInfo) {
        logger.info("收到参数:" + recordInfo);
        BaseResponseDto responseDto = new BaseResponseDto();
        try {
            ParameterUtils.notNull(recordInfo, "recordInfo不能为null");
            ParameterUtils.notNull(recordInfo.getId(), "id不能为null");
            recordBiz.update(recordInfo);
            responseDto.setReturnCode(0);
            responseDto.setReturnMessage("修改咨询记录信息成功");
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
            responseDto.setReturnCode(-1);
            responseDto.setReturnMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
            responseDto.setReturnCode(-1);
        }
        logger.info("返回数据:" + responseDto);
        return responseDto;
    }

    @Override
    public RecordInfo queryOne(RecordQueryRequestDto requestDto) {
        logger.info("收到参数:" + requestDto);
        RecordInfo consultantInfo = null;
        try {
            ParameterUtils.notNull(requestDto, "RecordQueryRequestDto不能为null");
            consultantInfo = recordBiz.getRecord(requestDto);
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
        }
        logger.info("返回数据:" + consultantInfo);
        return consultantInfo;
    }

    @Override
    public RecordQueryResponseDto query(RecordQueryRequestDto requestDto) {
        logger.info("收到参数:" + requestDto);
        RecordQueryResponseDto responseDto = new RecordQueryResponseDto();
        try {
            ParameterUtils.notNull(requestDto, "RecordQueryRequestDto不能为null");
            responseDto = recordBiz.getRecordList(requestDto);
            responseDto.setReturnCode(0);
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
            responseDto.setReturnCode(-1);
            responseDto.setReturnMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
            responseDto.setReturnCode(-1);
        }
        logger.info("返回数据:" + responseDto);
        return responseDto;
    }

	@Override
	public Long queryCount(RecordQueryRequestDto requestDto) {
        logger.info("收到参数:{}", requestDto);
        Long count = 0L;
        try {
            ParameterUtils.notNull(requestDto, "RecordQueryRequestDto不能为null");
            count = recordBiz.queryCount(requestDto);
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
        }
        logger.info("返回数据:{}", count);
		return count;
	}

	@Override
	public RecordQueryResponseDto queryPage(RecordQueryRequestDto requestDto,
			Pagination page) {
		logger.info("收到参数:" + requestDto);
		RecordQueryResponseDto responseDto = new RecordQueryResponseDto();
		try {
			ParameterUtils.notNull(requestDto, "RecordQueryRequestDto不能为null");
			responseDto = recordBiz.queryRecordListPage(requestDto,page);
			responseDto.setReturnCode(0);
			responseDto.setReturnMessage("查询咨询记录成功");
		} catch (IllegalArgumentException e) {
			logger.info("参数异常:" + e.getMessage());
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage("查询咨询记录异常");
		} catch (Exception e) {
			logger.error("捕获异常:" + e.getMessage(), e);
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage("查询咨询记录异常");
		}
		return responseDto;
	}

}
