package com.yingzixiyin.core.dao;

import com.yingzixiyin.core.entity.Code;
import org.springframework.stereotype.Repository;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Repository("codeDao")
public interface CodeDao {

    /**
     * 增
     * @param code 记录
     * @return 插入记录的主键
     */
    public Integer insert(Code code);

    /**
     * 改
     * @param code id必需，通过id去修改
     */
    public void update(Code code);

    /**
     * 查询一个
     * @param code 查询条件
     * @return Code
     */
    public Code getCode(Code code);

}
