package com.yingzixiyin.core.dao;

import com.yingzixiyin.core.entity.Consultant;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Repository("consultantDao")
public interface ConsultantDao {

    /**
     * 增
     * @param consultant 咨询师
     * @return
     */
    public Integer insert(Consultant consultant);

    /**
     * 改
     * @param consultant id必需，通过id去修改
     */
    public void update(Consultant consultant);

    /**
     * 单个查询，确保查询条件能够得到唯一结果
     * @param consultant , 可填字段: id, phone, email, password, gender, rangeType, status
     * @return 符合条件的 Consultant
     */
    public Consultant getConsultant(Consultant consultant);

    /**
     * 批量查询
     * @param consultant , 可填字段: gender, rangeType, status
     * @return 符合条件的 Consultant 列表
     */
    public List<Consultant> getConsultantList(Consultant consultant);

    /**
     * 优化筛选咨询师
     * @param map 筛选参数，必须包含rangeType, gender, minAge, maxAge
     * @return 筛选的咨询师列表
     */
    public List<Consultant> getConsultantListByFilter(Map<String, Object> map);

    /**
     * 通过id列表查找咨询师
     * @param idList id 列表
     * @return 咨询师列表
     */
    public List<Consultant> getConsultantListByIdList(List<String> idList);

    /**
     * 查询个数
     * @param bean
     * @return
     */
	public Long queryCount(Consultant bean);
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<Consultant> queryConsultantListPage(Map<String, Object> map);
	/**
	 * 删除
	 * @param bean
	 * @return
	 */
	public Integer delete(Consultant bean);

}
