package com.yingzixiyin.core.dao;

import com.yingzixiyin.core.entity.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Repository("recordDao")
public interface RecordDao {

    /**
     * 增
     * @param record 记录
     * @return 插入记录的主键
     */
    public Integer insert(Record record);

    /**
     * 改
     * @param record id必需，通过id去修改
     */
    public void update(Record record);

    /**
     * 查询一个
     * @param record 查询条件
     * @return Record
     */
    public Record getRecord(Record record);

    /**
     * 查询多个
     * @param record 查询条件
     * @return RecordList
     */
    public List<Record> getRecordList(Record record);

}
