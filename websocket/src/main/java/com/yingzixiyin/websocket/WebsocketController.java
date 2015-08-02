package com.yingzixiyin.websocket;

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

//    @Bean//这个注解会从Spring容器拿出Bean
//    public InfoHandler infoHandler() {
//        return new InfoHandler();
//    }

    @RequestMapping("/login.do")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        HttpSession session = request.getSession(false);
        session.setAttribute("session_phone", username);
        session.setAttribute("session_recordId", 1L);
        logger.info("session_phone:{}", session.getAttribute("session_phone"));
        response.sendRedirect("/websocket/ws.jsp");
    }

}
