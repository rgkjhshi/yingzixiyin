package com.yingzi.admin.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yingzi.admin.annotation.PowerCheck;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.api.enums.StatusEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.AdminFacade;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.RecordFacade;
import com.yingzixiyin.page.Pagination;

/**
 * 咨询管理的整个后台接口
 * @author lkzlee
 *
 */
@Controller
@RequestMapping("/record")
public class RecordController {
	Logger logger=LoggerFactory.getLogger(RecordController.class);
	@Resource
	private RecordFacade recordFacade ;
	/**
	 * 咨询管理
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="query_records.do")
	@PowerCheck
	public String getConsultantInfor(HttpServletRequest request,
			HttpServletResponse response, Integer status, Integer pageNum)
			throws IOException {
		logger.info("---系统后台调用咨询记录接口页面----");
		String response_page = "admin/record";
		RecordQueryRequestDto requestDto = new RecordQueryRequestDto();
		try {
			if (status != null) {
				YesOrNoEnum st = YesOrNoEnum.toEnum(status);
				requestDto.setIsCompleted(st);
				if (status == 0) {
					response_page = "admin/record_ing";
				} else {
					response_page = "admin/record_end";
				}
			}
			if (pageNum == null) {
				pageNum = 1;
			}
			Long count=recordFacade.queryCount(requestDto);
			Pagination page=new Pagination();
			page.setMaxCountAndCurrentPage(count, pageNum);
			if(status!=null){
				page.putParams("status", status);
			}
			page.setUrl(request.getContextPath()+"/record/query_records.do");
			RecordQueryResponseDto responseDto = recordFacade.queryPage(requestDto,page);
			if (responseDto != null) {
				request.setAttribute("recordsList", responseDto.getConsultantRecordList());
				request.setAttribute("page", page);
			}
		} catch (Exception e) {
			logger.error("后台查询咨询师分页结果出错", e);
		}
		return response_page;
	}
}
