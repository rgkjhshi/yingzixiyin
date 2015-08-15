package com.yingzixiyin.core.service;

import com.yingzixiyin.api.dto.RecordInfoExtend;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.core.entity.Record;

import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-02
 */


public interface RecordService {

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
    public RecordInfo getRecord(Record record);

    /**
     * 查询多个
     * @param record 查询条件
     * @return RecordInfoList
     */
    public List<RecordInfo> getRecordList(Record record);

	public Long queryCount(Record bean);

	public List<RecordInfoExtend> queryConsultantRecordsListPage(
			Map<String, Object> map);
}
