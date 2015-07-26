package com.yingzi.admin.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yingzixiyin.api.facade.AdminFacade;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.RecordFacade;

/**
 * 咨询管理的整个后台接口
 * @author lkzlee
 *
 */
@Controller
@RequestMapping("/record")
public class RecordController {
	Logger log=LoggerFactory.getLogger(RecordController.class);
	@Resource
	private RecordFacade recordFacade ;
	
}
