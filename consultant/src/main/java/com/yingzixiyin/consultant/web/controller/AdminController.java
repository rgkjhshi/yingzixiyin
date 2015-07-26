package com.yingzixiyin.consultant.web.controller;

import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.core.entity.Consultant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView updateInfo(HttpSession session, Consultant consultant) {
        logger.info("updateInfoApi.htm, consultant={}", consultant);
        Map<String, Object> map = Maps.newHashMap();
        // 查询
        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
        queryRequestDto.setPhone(consultant.getPhone());
        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
        // 登陆结果
        if (null == info) {
            map.put("status", -1);
            map.put("message", "未查到用户相关信息");
            logger.info("修改信息失败");
        } else {
            ConsultantInfo updateInfo = Consultant.translateBean(consultant);
            updateInfo.setId(info.getId());
            BaseResponseDto responseDto = consultantFacade.update(updateInfo);
            map.put("status", responseDto.getReturnCode());
            map.put("message", responseDto.getReturnMessage());
            logger.info(responseDto.getReturnMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @RequestMapping("/changePasswordApi.htm")
    public ModelAndView changePassword(@RequestParam(value = "phone", required = true) String phone,
                                       @RequestParam(value = "oldPassword", required = true) String oldPassword,
                                       @RequestParam(value = "newPassword", required = true) String newPassword) {
        Map<String, Object> map = Maps.newHashMap();
        logger.info("phone={}", phone);
        // 查询
        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
        queryRequestDto.setPhone(phone);
        queryRequestDto.setPhone(oldPassword);
        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
        if (null == info) {
            map.put("status", -1);
            map.put("message", "旧密码不正确");
            logger.info("修改密码失败");
        } else {
            ConsultantInfo consultantInfo = new ConsultantInfo();
            consultantInfo.setId(info.getId());
            consultantInfo.setPassword(newPassword);
            BaseResponseDto responseDto = consultantFacade.update(consultantInfo);
            map.put("status", responseDto.getReturnCode());
            map.put("message", responseDto.getReturnMessage());
            logger.info(responseDto.getReturnMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

}
