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
     * 增
     * @param consultant 咨询师
     * @return
     */
    public Integer insert(Consultant consultant);

    /**
     * 修改信息
     * @param consultant id必需，通过id去修改
     */
    public void update(Consultant consultant);

    /**
     * 查询一个
     * @param consultant 类型
     * @return 咨询师列表
     */
    public ConsultantInfo getConsultant(Consultant consultant);

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

	public List<ConsultantInfo> queryConsultantListPage(Map<String, Object> map);

	public Long queryCount(Consultant bean);

	public Integer delete(Consultant bean);
}
