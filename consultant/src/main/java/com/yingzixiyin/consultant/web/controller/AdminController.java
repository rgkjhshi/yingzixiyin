package com.yingzixiyin.consultant.web.controller;

import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.facade.ConsultantFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-23
 */

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Resource
    ConsultantFacade consultantFacade;

    @RequestMapping("/updateInfoApi.htm")
    public ModelAndView login(HttpSession session, ConsultantInfo consultantInfo) {
        logger.info("updateInfoApi.htm, consultantInfo={}", consultantInfo);
        Map<String, Object> map = Maps.newHashMap();
        String phone = (String)session.getAttribute("session_phone");
        logger.info("session_phone={}", phone);
        // 查询
        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
        queryRequestDto.setPhone(phone);
        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
//        consultantInfo.setId(info.getId());
        // 登陆结果
        if (null == info) {
            map.put("status", -1);
            map.put("message", "用户名或密码不正确");
        } else {
            map.put("status", 0);
            map.put("message", "登陆成功");
        }
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @RequestMapping("/changePasswordApi.htm")
    public ModelAndView login(HttpSession session, String oldPassword, String newPassword) {
        Map<String, Object> map = Maps.newHashMap();
        String phone = (String)session.getAttribute("session_phone");
        logger.info("sessionAttributeNames={}", session.getAttributeNames());
        logger.info("session_phone={}", phone);
        // 查询
        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
        queryRequestDto.setPhone(phone);
        queryRequestDto.setPhone(oldPassword);
        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
        if (null == info) {
            map.put("status", -1);
            map.put("message", "旧密码不正确");
        } else {
            ConsultantInfo consultantInfo = new ConsultantInfo();
            consultantInfo.setId(info.getId());
            consultantInfo.setPassword(newPassword);
            BaseResponseDto responseDto = consultantFacade.update(consultantInfo);
            map.put("status", responseDto.getReturnCode());
            map.put("message", responseDto.getReturnMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

}
