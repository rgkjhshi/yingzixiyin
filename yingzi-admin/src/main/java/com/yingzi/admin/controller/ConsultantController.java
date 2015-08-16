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
import com.yingzi.admin.utils.JsonUtil;
import com.yingzi.admin.utils.ResponseUtils;
import com.yingzi.admin.view.ConsultantView;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.StatusEnum;
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
	 * @param pageNum
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="query_consultants.do")
	@PowerCheck
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
//			logger.info("--条件筛选得到的咨询师列表为："+responseDto.getConsultantInfoList());
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
	@RequestMapping(value="cdetail.do")
	@PowerCheck
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
			request.setAttribute("cinfo", cqrDto);
		}
		return response_page;
	}
	/**
	 * 删除咨询师
	 * @param request
	 * @param response
	 * @param ctype
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="cdelete.do")
	@PowerCheck
	public void deleteConsultantInfor(HttpServletRequest request,HttpServletResponse response,Long id) throws IOException{
		logger.info("---系统后台调用咨询师删除接口页面----");
		BaseResponseDto responseDto=new BaseResponseDto();
		responseDto.setReturnCode(0);
		responseDto.setReturnMessage("删除成功");
		try{
			ConsultantQueryRequestDto rcrDto=new ConsultantQueryRequestDto();
			rcrDto.setId(id);
			consultantFacade.delete(rcrDto);
		}
		catch(Exception e){
			logger.error("删除咨询师异常",e);
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage("删除咨询师失败");
		}
		ResponseUtils.renderJsonText(response, JsonUtil.getJsonText(responseDto));
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value="ccheck.do")
	@PowerCheck
	public void checkConsultantInfo(HttpServletRequest request,HttpServletResponse response,ConsultantView cinfo) throws IOException{
		logger.info("---系统后台调用咨询师审核接口页面----");
		BaseResponseDto responseDto=new BaseResponseDto();
		logger.info("审核咨询师信息："+cinfo);
		try{
			ConsultantInfo requestDto=new ConsultantInfo();
			copyConsultantToByView(requestDto,cinfo);
			responseDto=consultantFacade.update(requestDto);
		}
		catch(Exception e){
			logger.error("审核咨询师异常",e);
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage("审核咨询师失败");
		}
		ResponseUtils.renderJsonText(response, JsonUtil.getJsonText(responseDto));
	}
	private void copyConsultantToByView(ConsultantInfo requestDto,
			ConsultantView cinfo) {
		if(cinfo==null||requestDto==null||cinfo.getId()==null) {
			return ;
		}
		requestDto.setId(cinfo.getId());
		requestDto.setEmail(cinfo.getEmail());
		if(null!=cinfo.getGender()){
			requestDto.setGender(GenderTypeEnum.toEnum(cinfo.getGender()));
		}
		requestDto.setName(cinfo.getName());
		requestDto.setPassword(cinfo.getPassword());
		requestDto.setPhone(cinfo.getPhone());
		if(null!=cinfo.getRangeType()){
			requestDto.setRangeType(RangeTypeEnum.toEnum(cinfo.getRangeType()));
		}
		if(null!=cinfo.getStatus()){
			requestDto.setStatus(StatusEnum.toEnum(cinfo.getStatus()));
		}
		requestDto.setAvatar(cinfo.getAvatar());
		requestDto.setAddress(cinfo.getAddress());
		requestDto.setAlipay(cinfo.getAlipay());
		requestDto.setBackground(cinfo.getBackground());
		requestDto.setBookTime(cinfo.getBookTime());
		requestDto.setProfessional(cinfo.getProfessional());
		requestDto.setPrice(cinfo.getPrice());
		requestDto.setNickname(cinfo.getNickname());
		requestDto.setIntroduce(cinfo.getIntroduce());
		requestDto.setSignature(cinfo.getSignature());
	}
}
