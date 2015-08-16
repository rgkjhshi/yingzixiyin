package com.yingzi.admin.controller;

import java.io.IOException;
import java.util.Collections;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Collections2;
import com.yingzi.admin.annotation.PowerCheck;
import com.yingzi.admin.utils.JsonUtil;
import com.yingzi.admin.utils.ResponseUtils;
import com.yingzi.admin.view.ConsultantView;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryRequestDto;
import com.yingzixiyin.api.dto.MessageQueryResponseDto;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.dto.RecordInfoExtend;
import com.yingzixiyin.api.dto.RecordQueryRequestDto;
import com.yingzixiyin.api.dto.RecordQueryResponseDto;
import com.yingzixiyin.api.enums.StatusEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.AdminFacade;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.MessageFacade;
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
	@Resource
	private MessageFacade messageFacade;
	/**
	 * 咨询管理
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="query_records.do")
	@PowerCheck
	public String getConsultantInfor(HttpServletRequest request,
			HttpServletResponse response, Integer status, Integer isPaid,Integer pageNum)
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
		    if(isPaid!=null){
				YesOrNoEnum st = YesOrNoEnum.toEnum(isPaid);
				requestDto.setIsPaid(st);
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
			if(isPaid!=null){
				page.putParams("isPaid", isPaid);
			}
			page.setUrl(request.getContextPath()+"/record/query_records.do");
			RecordQueryResponseDto responseDto = recordFacade.queryPage(requestDto,page);
			if (responseDto != null) {
				if(!CollectionUtils.isEmpty(responseDto.getRecordInfoExtendList())){
					for(RecordInfoExtend rinfo:responseDto.getRecordInfoExtendList()){
						if(rinfo.getIsReplied()!=null&&rinfo.getIsReplied().getValue()==1){
							continue;
						}
						MessageQueryRequestDto requestDto2=new MessageQueryRequestDto();
						requestDto2.setRecordId(rinfo.getId());
						requestDto2.setFromPhone(rinfo.getConsultantPhone());
						MessageQueryResponseDto responseDto2= messageFacade.query(requestDto2);
						if(responseDto2!=null&&responseDto2.getMessageInfoList()!=null){
							if(!CollectionUtils.isEmpty(responseDto2.getMessageInfoList())){
								RecordInfo recordInfo=new RecordInfo();
								recordInfo.setId(rinfo.getId());
								recordInfo.setIsReplied(YesOrNoEnum.YES);
								recordFacade.update(recordInfo);
								rinfo.setIsReplied(YesOrNoEnum.YES);
							}
						}
					}
				}
				request.setAttribute("recordsList", responseDto.getRecordInfoExtendList());
				request.setAttribute("page", page);
			}
		} catch (Exception e) {
			logger.error("后台查询咨询师分页结果出错", e);
		}
		return response_page;
	}
	/**
	 * 修改咨询回话，关闭或者开启会话
	 * @param request
	 * @param response
	 * @param id
	 * @param status
	 * @throws IOException
	 */
	@RequestMapping(value="change_state.do")
	@PowerCheck
	public void changeRecordState(HttpServletRequest request,HttpServletResponse response,Long id,Integer status) throws IOException{
		logger.info("---系统后台调用咨询回话状态更新接口页面----");
		BaseResponseDto responseDto=new BaseResponseDto();
		try{
			RecordInfo requestDto=new RecordInfo();
			requestDto.setId(id);
			requestDto.setIsCompleted(YesOrNoEnum.toEnum(status));
			responseDto=recordFacade.update(requestDto);
		}
		catch(Exception e){
			logger.error("修改咨询会话失败",e);
			responseDto.setReturnCode(-1);
			responseDto.setReturnMessage("修改咨询会话状态失败");
		}
		ResponseUtils.renderJsonText(response, JsonUtil.getJsonText(responseDto));
	}
}
