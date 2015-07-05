package com.yingzixiyin.core.service;

import com.yingzixiyin.api.dto.ConsultantInfo;

import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-02
 */


public interface ConsultantService {

    public List<ConsultantInfo> getConsultantList(Integer rangeType);
}
