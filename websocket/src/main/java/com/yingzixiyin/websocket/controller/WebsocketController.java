package com.yingzixiyin.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/login.do")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String phone = request.getParameter("phone");
        Long recordId = Long.valueOf(request.getParameter("recordId"));
        // 创建一个session
        HttpSession session = request.getSession();
        session.setAttribute("session_phone", "13121435540");
        session.setAttribute("session_recordId", 1L);
        logger.info("session_phone={}, session_recordId={}", session.getAttribute("session_phone"), session.getAttribute("session_recordId"));
        response.sendRedirect("/websocket/ws.jsp");
    }

}
