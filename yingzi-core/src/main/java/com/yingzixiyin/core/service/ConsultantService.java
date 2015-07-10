package com.yingzixiyin.core.service;

import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.core.entity.Consultant;

import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-02
 */


public interface ConsultantService {

    /**
     * 查询咨询师
     * @param consultant 类型
     * @return 咨询师列表
     */
    public List<ConsultantInfo> getConsultantList(Consultant consultant);

    /**
     * 通过过滤条件查询咨询师
     * @param map 过滤条件,包含rangeType, gender, minAge, maxAge
     * @return 咨询师列表
     */
    public List<ConsultantInfo> getConsultantList(Map<String, Object> map);


    /**
     * 通过id的列表查找咨询师
     * @param ids id字符串，用", "隔开
     * @return 咨询师列表
     */
    public List<ConsultantInfo> getConsultantList(String ids);
}
