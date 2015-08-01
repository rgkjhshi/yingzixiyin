package com.yingzi.admin.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.page.Pagination;

@Controller
@RequestMapping("/consultant")
public class ConsultantController {
	Logger logger=LoggerFactory.getLogger(ConsultantController.class);
	@Resource
	private ConsultantFacade consultantFacade;
	/**
	 * 咨询类型+条件筛选获取咨询师列表
	 * @param request
	 * @param response
	 * @param ctype
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="query_consultants.do")
	public String queryConsultantsByCondition(HttpServletRequest request,HttpServletResponse response,Integer pageNum) throws IOException{
		logger.info("---后台查询咨询师接口页面");
		try{
			if(pageNum==null){
				pageNum=1;
			}
			ConsultantQueryRequestDto requestDto=new ConsultantQueryRequestDto();
			Long count=consultantFacade.queryCount(requestDto);
			Pagination page=new Pagination();
			page.setMaxCountAndCurrentPage(count, pageNum);
			page.setUrl(request.getContextPath()+"/consultant/query_consultants.do");
			ConsultantQueryResponseDto responseDto=  consultantFacade.queryPage(requestDto,page); 
			logger.info("--条件筛选得到的咨询师列表为："+responseDto.getConsultantInfoList());
			request.setAttribute("list", responseDto.getConsultantInfoList());
			request.setAttribute("page", page);
		}catch(Exception e){
			logger.error("后台查询咨询师分页结果出错",e);
		}
		return "admin/consultant";
	}
	/**
	 * 根据咨询类型获取咨询师列表
	 * @param request
	 * @param response
	 * @param ctype
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="cdeatil.do")
	public String getConsultantInfor(HttpServletRequest request,HttpServletResponse response,Long id) throws IOException{
		logger.info("---系统后台调用咨询师详细信息接口页面----");
		String response_page="admin/detail";
		if(id==null){
			return response_page;
		}
		ConsultantQueryRequestDto rcrDto=new ConsultantQueryRequestDto();
		rcrDto.setId(id);
		ConsultantInfo cqrDto=consultantFacade.queryOne(rcrDto);
		if(cqrDto!=null){
			request.setAttribute("consultant", cqrDto);
		}
		return response_page;
	}
}
