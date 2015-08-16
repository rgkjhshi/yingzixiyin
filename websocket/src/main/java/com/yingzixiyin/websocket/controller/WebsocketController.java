package com.yingzixiyin.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author song.shi
 * @date 2015-07-24
 */

@RestController
public class WebsocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebsocketController.class);

    // http://yingzixiyin.com/websocket/chat.do?phone=13121435540&toPhone=13121435541&recordId=1
    @RequestMapping("/chat.do")
    public void consultantChat(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(value="recordId", required = true) Long recordId,
                      @RequestParam(value="phone", required = true) String phone,
                      @RequestParam(value="toPhone", required = true) String toPhone) throws Exception {
        // 创建一个session
        HttpSession session = request.getSession();
        session.setAttribute("recordId", recordId);
        session.setAttribute("phone", phone);
        session.setAttribute("toPhone", toPhone);
        logger.info("recordId={}", session.getAttribute("recordId"));
        logger.info("phone={}, toPhone={}", session.getAttribute("phone"), session.getAttribute("toPhone"));
//        return new ModelAndView("/consultant/chat.jsp");
        response.sendRedirect("/websocket/consultant/chat.jsp");
    }

    @RequestMapping("/wxChat.do")
    public void weixinChat(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(value="recordId", required = true) Long recordId,
                      @RequestParam(value="phone", required = true) String phone,
                      @RequestParam(value="toPhone", required = true) String toPhone) throws Exception {
        // 创建一个session
        HttpSession session = request.getSession();
        session.setAttribute("recordId", recordId);
        session.setAttribute("phone", phone);
        session.setAttribute("toPhone", toPhone);
        logger.info("recordId={}", session.getAttribute("recordId"));
        logger.info("phone={}, toPhone={}", session.getAttribute("phone"), session.getAttribute("toPhone"));
//        return new ModelAndView("/consultant/chat.jsp");
        response.sendRedirect("/websocket/weixin/chat.jsp");
    }

}
