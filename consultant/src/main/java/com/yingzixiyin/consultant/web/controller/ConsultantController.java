package com.yingzixiyin.consultant.web.controller;

import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.CodeInfo;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.facade.CodeFacade;
import com.yingzixiyin.api.facade.ConsultantFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-13
 */

@RestController
public class ConsultantController {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantController.class);

    @Resource
    CodeFacade codeFacade;
    @Resource
    ConsultantFacade consultantFacade;

    @RequestMapping("/loginApi.htm")
    public ModelAndView login(HttpSession session, HttpServletResponse response,
                              @RequestParam(value = "phone", required = true) String phone,
                              @RequestParam(value = "password", required = true) String password) {
        logger.info("loginApi.htm, phone={}", phone);
        Map<String, Object> map = Maps.newHashMap();

        // 查询
        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
        queryRequestDto.setPhone(phone);
        queryRequestDto.setPassword(password);
        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
        // 登陆结果
        if (null == info) {
            map.put("status", -1);
            map.put("message", "用户名或密码不正确");
            logger.info("登陆失败");
        } else {
            map.put("status", 0);
            map.put("message", "登陆成功");
            logger.info("登陆成功");
        }
        Cookie cookie = new Cookie("cookie_phone", phone);
        response.addCookie(cookie);
        session.setAttribute("session_phone", phone);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @RequestMapping("/registerApi.htm")
    public ModelAndView register(@RequestParam(value = "phone", required = true) String phone,
                                 @RequestParam(value = "checkCode", required = true) String checkCode,
                                 @RequestParam(value = "password", required = true) String password) {
        logger.info("registerApi.htm, phone={}, checkCode={}", phone, checkCode);
        Map<String, Object> map = Maps.newHashMap();
        //查询验证码
        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setPhone(phone);
        codeInfo.setCode(checkCode);
        BaseResponseDto responseDto = codeFacade.checkCode(codeInfo);
        if (!responseDto.isSuccess()) {
            map.put("status", responseDto.getReturnCode());
            map.put("message", responseDto.getReturnMessage());
            return new ModelAndView(new MappingJackson2JsonView(), map);
        }
        logger.info("验证码通过");
        // 查询用户名和密码
        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
        queryRequestDto.setPhone(phone);
        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
        if (null != info) {
            map.put("status", -1);
            map.put("message", "该手机号已经注册");
            return new ModelAndView(new MappingJackson2JsonView(), map);
        }
        logger.info("注册前校验通过");
        // 注册
        ConsultantInfo consultantInfo = new ConsultantInfo();
        consultantInfo.setPhone(phone);
        consultantInfo.setPassword(password);
        responseDto = consultantFacade.add(consultantInfo);
        logger.info(responseDto.getReturnMessage());
        // 返回
        map.put("status", responseDto.getReturnCode());
        map.put("message", responseDto.getReturnMessage());
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    // 获取验证码
    @RequestMapping("/getCheckCodeApi.htm")
    public ModelAndView getCheckCode(@RequestParam(value = "phone", required = true) String phone) {
        logger.info("getCheckCodeApi.htm, phone={}", phone);
        // 获取验证码
        BaseResponseDto responseDto = codeFacade.sendCode(phone);
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", responseDto.getReturnCode());
        map.put("message", responseDto.getReturnMessage());
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @RequestMapping("/test/testApi.htm")
    public ModelAndView test(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Map<String, Object> map = Maps.newHashMap();
        map.put("getRequestURL", request.getRequestURL());
        map.put("getRequestURI", request.getRequestURI());
        map.put("getContextPath", request.getContextPath());
        map.put("getServletPath", request.getServletPath());
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

}
