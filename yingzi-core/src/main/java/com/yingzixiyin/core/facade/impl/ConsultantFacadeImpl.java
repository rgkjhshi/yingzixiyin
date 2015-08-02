package com.yingzixiyin.core.facade.impl;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.core.biz.ConsultantBiz;
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

@Component("consultantFacade")
public class ConsultantFacadeImpl implements ConsultantFacade {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantFacadeImpl.class.getName());

    @Resource
    ConsultantBiz consultantBiz;

    @Override
    public BaseResponseDto add(ConsultantInfo consultantInfo) {
        logger.info("收到参数:" + consultantInfo);
        BaseResponseDto responseDto = new BaseResponseDto();
        try {
            ParameterUtils.notNull(consultantInfo, "consultantInfo不能为null");
            ParameterUtils.notNull(consultantInfo.getPhone(), "phone不能为null");
            ParameterUtils.notNull(consultantInfo.getPassword(), "password不能为null");
            consultantBiz.add(consultantInfo);
            responseDto.setReturnCode(0);
            responseDto.setReturnMessage("成功");
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
    public BaseResponseDto update(ConsultantInfo consultantInfo) {
        logger.info("收到参数:" + consultantInfo);
        BaseResponseDto responseDto = new BaseResponseDto();
        try {
            ParameterUtils.notNull(consultantInfo, "consultantInfo不能为null");
            ParameterUtils.notNull(consultantInfo.getId(), "id不能为null");
            consultantBiz.update(consultantInfo);
            responseDto.setReturnCode(0);
            responseDto.setReturnMessage("修改成功");
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
    public ConsultantInfo queryOne(ConsultantQueryRequestDto requestDto) {
        logger.info("收到参数:" + requestDto);
        ConsultantInfo consultantInfo = null;
        try {
            ParameterUtils.notAllNull(requestDto);
            consultantInfo = consultantBiz.getConsultant(requestDto);
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
        }
        logger.info("返回数据:" + consultantInfo);
        return consultantInfo;
    }

    @Override
    public ConsultantQueryResponseDto query(ConsultantQueryRequestDto requestDto) {
        logger.info("收到参数:" + requestDto);
        ConsultantQueryResponseDto responseDto = new ConsultantQueryResponseDto();
        try {
            ParameterUtils.notNull(requestDto, "ConsultantRequestDto不能为null");
            responseDto = consultantBiz.getConsultantList(requestDto);
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
    public ConsultantQueryResponseDto queryByIds(String ids) {
        logger.info("收到参数:" + ids);
        ConsultantQueryResponseDto responseDto = new ConsultantQueryResponseDto();
        try {
            ParameterUtils.notEmpty(ids, "ids不能为空");
            responseDto = consultantBiz.getConsultantListByIds(ids);
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
	public Long queryCount(ConsultantQueryRequestDto requestDto) {
		logger.info("收到参数:" + requestDto);
		return consultantBiz.queryCount(requestDto);
	}

	@Override
	public ConsultantQueryResponseDto queryPage(
			ConsultantQueryRequestDto requestDto, Pagination page) {
		logger.info("收到参数:" + requestDto);
        ConsultantQueryResponseDto responseDto = new ConsultantQueryResponseDto();
        try {
            ParameterUtils.notEmpty(page, "分页page不能为空");
            responseDto = consultantBiz.queryConsultantListPage(requestDto,page);
            responseDto.setReturnCode(0);
            responseDto.setReturnMessage("查询咨询师page页成功");
        } catch (IllegalArgumentException e) {
            logger.info("参数异常:" + e.getMessage());
            responseDto.setReturnCode(-1);
            responseDto.setReturnMessage("查询咨询师page页失败");
            responseDto.setReturnMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("捕获异常:" + e.getMessage(), e);
            responseDto.setReturnCode(-1);
            responseDto.setReturnMessage("查询咨询师page页失败");
        }
        logger.info("返回数据:" + responseDto);
        return responseDto;
	}

	@Override
	public Integer delete(ConsultantQueryRequestDto requestDto) {
		logger.info("收到参数:" + requestDto);
		return consultantBiz.delete(requestDto);
	}
}
