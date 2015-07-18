package com.yingzixiyin.consultant.web.controller;

import com.google.common.collect.Maps;
import com.yingzixiyin.api.facade.ConsultantFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-13
 */

@RestController
public class ConsultantController {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantController.class);

    @Resource
    ConsultantFacade consultantFacade;

    @RequestMapping("/loginApi.htm")
    public ModelAndView login(HttpSession session, HttpServletResponse response,
                              @RequestParam(value = "phone", required = true) String phone,
                              @RequestParam(value = "password", required = true) String password) {
        logger.info("login.htm");
        // 查询
//        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
//        queryRequestDto.setPhone(phone);
//        queryRequestDto.setPassword(password);
//        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
        // 返回
//        ModelAndView mav = null;
//        if (null == info) {
//            // TODO 登陆失败
//        } else {
//            // TODO 登陆成功，跳转到管理界面
//        }
        Cookie cookie = new Cookie("phone", phone);
        response.addCookie(cookie);
        session.setAttribute("session_phone", phone);
        
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", 0);
        return new ModelAndView(new MappingJackson2JsonView(),map);
    }

    @RequestMapping("/registerApi.htm")
    public ModelAndView register(@RequestParam(value = "phone", required = true) String phone,
                                 @RequestParam(value = "phone", required = true) String checkCode,
                                 @RequestParam(value = "password", required = true) String password) {
        logger.info("registerApi.htm");
        //查询验证码

        // 查询用户名和密码
//        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
//        queryRequestDto.setPhone(phone);
//        queryRequestDto.setPassword(password);
//        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
        // 返回
//        ModelAndView mav = null;
//        if (null == info) {
//            // TODO 登陆失败
//        } else {
//            // TODO 登陆成功，跳转到管理界面
//        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("status", 0);
        return new ModelAndView(new MappingJackson2JsonView(),map);
    }

    @RequestMapping("/test.htm")
    public Map test(@RequestHeader("Accept-Encoding") String encoding) {
        String result = null == encoding ? "nothing" : encoding;
        Map<String ,Object> map = Maps.newHashMap();
        map.put("Accept-Encoding", result);
        map.put("test", "test");
        map.put("date", new Date());
        return map;
    }

}
