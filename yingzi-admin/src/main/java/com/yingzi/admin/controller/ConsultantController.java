package com.yingzi.admin.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yingzixiyin.api.facade.AdminFacade;
import com.yingzixiyin.api.facade.ConsultantFacade;

@Controller
@RequestMapping("/consultant")
public class ConsultantController {
	Logger log=LoggerFactory.getLogger(ConsultantController.class);
	@Resource
	private ConsultantFacade consultantFacade;
	
}
