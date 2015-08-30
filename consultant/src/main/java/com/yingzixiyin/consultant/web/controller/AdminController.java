package com.yingzixiyin.consultant.web.controller;

import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.*;
import com.yingzixiyin.api.enums.StatusEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.api.facade.RecordFacade;
import com.yingzixiyin.consultant.web.util.PictureUtil;
import com.yingzixiyin.core.entity.Consultant;
import com.yingzixiyin.page.Pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
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

    // 退出登录
    @RequestMapping("/logoutApi.htm")
    public ModelAndView logout(HttpSession session) {
        logger.info("logoutApi.htm");
        Map<String, Object> map = Maps.newHashMap();
        try {
            session.removeAttribute("session_phone");
        } catch (IllegalStateException e) {
            logger.info("invalid session");
        }
        map.put("status", 0);
        map.put("message", "退出登陆");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    // 更新个人信息
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

    // 修改密码
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

    // 结束咨询
    @RequestMapping("/endChatApi.htm")
    public ModelAndView endChat(Long recordId) {
        logger.info("endChatApi.htm, recordId={}", recordId);
        Map<String, Object> map = Maps.newHashMap();

        // 查询
        RecordQueryRequestDto queryRequestDto = new RecordQueryRequestDto();
        queryRequestDto.setId(recordId);
        RecordInfo info = recordFacade.queryOne(queryRequestDto);
        if (null == info) {
            map.put("status", -1);
            map.put("message", "未找到咨询纪录");
            logger.info("未找到咨询纪录");
        } else {
            RecordInfo updateInfo = new RecordInfo();
            updateInfo.setId(info.getId());
            updateInfo.setIsCompleted(YesOrNoEnum.YES);
            BaseResponseDto responseDto = recordFacade.update(updateInfo);
            map.put("status", responseDto.getReturnCode());
            map.put("message", responseDto.getReturnMessage());
            logger.info(responseDto.getReturnMessage());
        }
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    // 查询个人信息
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

    // 咨询列表
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
            HttpSession session = request.getSession();
            String phone = (String) session.getAttribute("session_phone");
            ConsultantQueryRequestDto consultantQueryRequestDto = new ConsultantQueryRequestDto();
            consultantQueryRequestDto.setPhone(phone);
            ConsultantInfo consultantInfo = consultantFacade.queryOne(consultantQueryRequestDto);
            requestDto.setConsultantId(consultantInfo.getId());
            if (status == 0) {
                mav = new ModelAndView("message_ing.jsp");
            } else {
                mav = new ModelAndView("message_end.jsp");
            }
            Long count = recordFacade.queryCount(requestDto);

            Pagination page = new Pagination();
            page.setMaxCountAndCurrentPage(count, pageNum);
            page.putParams("status", status);
            page.setUrl(request.getRequestURL().toString());
            RecordQueryResponseDto responseDto = recordFacade.queryPage(requestDto, page);
            if (responseDto != null) {
                request.setAttribute("recordList", responseDto.getRecordInfoExtendList());
                request.setAttribute("page", page);
            }
        } catch (Exception e) {
            logger.error("后台查询咨询师分页结果出错:{}", e);
        }
        return mav;
    }

    // 上传头像
    @RequestMapping(value = "uploadPicApi.htm", method = RequestMethod.POST)
    public ModelAndView uploadPic(HttpServletRequest request,
                                  HttpServletResponse response, String callback) {
        Map<String, Object> map = Maps.newHashMap();
        logger.info("--图片上传---");
        if (!(request instanceof DefaultMultipartHttpServletRequest)) {
            map.put("status", -1);
            map.put("message", "图片上传失败");
            return new ModelAndView(new MappingJackson2JsonView(), map);
        }
        DefaultMultipartHttpServletRequest mulRequest = (DefaultMultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> multiFileMap = mulRequest.getMultiFileMap();
        List<MultipartFile> uploadFile = multiFileMap.get("img");
        String iconUrl = null;

        if (uploadFile != null) {
            iconUrl = PictureUtil.savePicture(request, (CommonsMultipartFile) uploadFile.get(0));
        }
        logger.debug("---得到图片信息：" + uploadFile + "|imageurl:" + iconUrl);
        if (StringUtils.isEmpty(iconUrl)) {
            map.put("status", -1);
            map.put("message", "图片上传失败");
            return new ModelAndView(new MappingJackson2JsonView(), map);
        }
        logger.info("---上传图片得到url：" + iconUrl);
        HttpSession session = request.getSession(false);
        String phone = (String) session.getAttribute("session_phone");
        ConsultantQueryRequestDto requestDto = new ConsultantQueryRequestDto();
        requestDto.setPhone(phone);
        ConsultantInfo consultantInfo = consultantFacade.queryOne(requestDto);
        consultantInfo.setAvatar(iconUrl);
        consultantInfo.setStatus(StatusEnum.APPLIED);
        BaseResponseDto responseDto = consultantFacade.update(consultantInfo);
        if (!responseDto.isSuccess()) {
            map.put("status", -1);
            map.put("message", "图片上传失败");
            return new ModelAndView(new MappingJackson2JsonView(), map);
        }
        map.put("status", 0);
        map.put("message", "图片上传成功");
        map.put("imgurl", iconUrl);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }
}
