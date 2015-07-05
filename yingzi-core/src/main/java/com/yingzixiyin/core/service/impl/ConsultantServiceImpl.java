package com.yingzixiyin.core.service.impl;

import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.core.dao.ConsultantDao;
import com.yingzixiyin.core.entity.Consultant;
import com.yingzixiyin.core.service.ConsultantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Service("consultantService")
public class ConsultantServiceImpl implements ConsultantService {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantServiceImpl.class.getName());

    @Resource
    ConsultantDao consultantDao;

    @Override
    public List<ConsultantInfo> getConsultantList(Integer rangeType) {
        if (null == rangeType) {
            logger.info("ConsultantServiceImpl#getConsultantList的参数rangeType为null");
            return null;
        }
        Consultant consultant = new Consultant();
        consultant.setRangeType(rangeType);
        List<Consultant> consultantList = consultantDao.getConsultantList(consultant);
        return Consultant.translateBeanList(consultantList);
    }

}
