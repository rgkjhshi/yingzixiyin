package com.yingzixiyin.websocket.service;

import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.RecordFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author song.shi
 * @date 2015-08-03
 */

@Service("chatService")
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Resource
    private RecordFacade recordFacade;
    @Resource
    private ConsultantFacade consultantFacade;

}
