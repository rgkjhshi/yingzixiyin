package com.yingzixiyin.core.dao;

import com.yingzixiyin.core.entity.Consultant;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Repository("consultantDao")
public interface ConsultantDao {

    /**
     * 单个查询，确保查询条件能够得到唯一结果
     * @param consultant , 可填字段: id, username, password, phone, email, gender, rangeType
     * @return 符合条件的 Consultant
     */
    public Consultant getConsultant(Consultant consultant);

    /**
     * 批量查询
     * @param consultant , 可填字段: gender, rangeType
     * @return 符合条件的 Consultant 列表
     */
    public List<Consultant> getConsultantList(Consultant consultant);
}
