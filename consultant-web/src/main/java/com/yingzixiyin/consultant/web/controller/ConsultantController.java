package com.yingzixiyin.consultant.web.controller;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-13
 */

@RestController
public class ConsultantController {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantController.class);

    @RequestMapping("/vm.htm")
    public ModelAndView vm() {
        logger.info("vm.htm");
        return new ModelAndView("/html/signin.html");
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
