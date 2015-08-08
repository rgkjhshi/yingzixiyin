package com.yingzixiyin.consultant.web.controller;

import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.*;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.RecordFacade;
import com.yingzixiyin.core.entity.Consultant;
import com.yingzixiyin.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private ConsultantFacade consultantFacade;
    @Resource
    private RecordFacade recordFacade;

    @RequestMapping("/updateInfoApi.htm")
    public ModelAndView updateInfo(HttpSession session, Consultant entity) {
        logger.info("updateInfoApi.htm, consultant={}", entity);
        Map<String, Object> map = Maps.newHashMap();
        String phone = (String) session.getAttribute("session_phone");

        // 查询
        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
        queryRequestDto.setPhone(phone);
        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
        // 登陆结果
        if (null == info) {
            map.put("status", -1);
            map.put("message", "未查到用户相关信息");
            logger.info("修改信息失败");
        } else {
            ConsultantInfo updateInfo = Consultant.translateBean(entity);
            updateInfo.setId(info.getId());
            BaseResponseDto responseDto = consultantFacade.update(updateInfo);
            map.put("status", responseDto.getReturnCode());
            map.put("message", responseDto.getReturnMessage());
            logger.info(responseDto.getReturnMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @RequestMapping("/changePasswordApi.htm")
    public ModelAndView changePassword(HttpSession session,
                                       @RequestParam(value = "oldPassword", required = true) String oldPassword,
                                       @RequestParam(value = "newPassword", required = true) String newPassword) {
        Map<String, Object> map = Maps.newHashMap();
        String phone = (String) session.getAttribute("session_phone");
        logger.info("changePasswordApi.htm, phone={}", phone);
        // 查询
        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
        queryRequestDto.setPhone(phone);
        queryRequestDto.setPassword(oldPassword);
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

    @RequestMapping("/queryInfoApi.htm")
    public ModelAndView queryInfo(HttpSession session) {
        Map<String, Object> map = Maps.newHashMap();
        String phone = (String) session.getAttribute("session_phone");
        logger.info("queryInfoApi.htm, phone={}", phone);
        // 查询
        ConsultantQueryRequestDto queryRequestDto = new ConsultantQueryRequestDto();
        queryRequestDto.setPhone(phone);
        ConsultantInfo info = consultantFacade.queryOne(queryRequestDto);
        if (null == info) {
            map.put("status", -1);
            map.put("message", "查询个人信息失败");
            logger.info("phone={},查询个人信息失败");
        } else {
            map.put("status", 0);
            map.put("message", "查询个人信息成功");
            map.put("data", ConsultantInfo.toMap(info));
            logger.info("phone={}, 查询个人信息成功", phone);
        }
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @RequestMapping("/chatApi.htm")
    public void chat(HttpServletRequest request, HttpServletResponse response,
                     @RequestParam(value = "recordId", required = true) Long recordId) throws Exception {
        HttpSession session = request.getSession(false);
        if (null == session || null == session.getAttribute("session_phone")) {
            logger.error("chatApi.htm, 无法获取session_phone, recordId={}", recordId);
            return;
        }
        session.setAttribute("session_recordId", recordId);
        logger.info("chatApi.htm, phone={}, recordId={}", session.getAttribute("session_phone"), recordId);
        response.sendRedirect("http://localhost:8080/websocket/login.do");
    }

    /**
     * 咨询管理
     */
    @RequestMapping(value = "queryRecordApi.htm")
    public ModelAndView getConsultantInfor(HttpServletRequest request,
                                           @RequestParam(value = "status", defaultValue = "1", required = false) Integer status,
                                           @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum) {
        logger.info("queryRecordApi.htm, status={}, pageNum={}", status, pageNum);
        ModelAndView mav = null;
        RecordQueryRequestDto requestDto = new RecordQueryRequestDto();
        try {
            YesOrNoEnum st = YesOrNoEnum.toEnum(status);
            requestDto.setIsCompleted(st);
            if (status == 0) {
                mav = new ModelAndView("admin/record_ing.jsp");
            } else {
                mav = new ModelAndView("admin/record_end.jsp");
            }
            Long count = recordFacade.queryCount(requestDto);

            Pagination page = new Pagination();
            page.setMaxCountAndCurrentPage(count, pageNum);
            page.putParams("status", status);
            page.setUrl(request.getRequestURL().toString());
            RecordQueryResponseDto responseDto = recordFacade.queryPage(requestDto, page);
            if (responseDto != null) {
                request.setAttribute("recordsList", responseDto.getConsultantRecordList());
                request.setAttribute("page", page);
            }
        } catch (Exception e) {
            logger.error("后台查询咨询师分页结果出错:{}", e);
        }
        return mav;
    }
}
