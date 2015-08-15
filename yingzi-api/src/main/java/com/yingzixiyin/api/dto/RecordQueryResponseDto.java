package com.yingzixiyin.api.dto;

import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class RecordQueryResponseDto extends BaseResponseDto {
    private static final long serialVersionUID = -4511354507150159150L;

    private Integer count;    // list中元素个数
    private List<RecordInfo> recordInfoList;
    private List<RecordInfoExtend> recordInfoExtendList;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<RecordInfo> getRecordInfoList() {
        return recordInfoList;
    }

    public void setRecordInfoList(List<RecordInfo> recordInfoList) {
        this.recordInfoList = recordInfoList;
    }

    public List<RecordInfoExtend> getRecordInfoExtendList() {
        return recordInfoExtendList;
    }

    public void setRecordInfoExtendList(List<RecordInfoExtend> recordInfoExtendList) {
        this.recordInfoExtendList = recordInfoExtendList;
    }

    @Override
    public String toString() {
        return "RecordQueryResponseDto{" +
                "count=" + count +
                ", recordInfoList=" + recordInfoList +
                ", recordInfoExtendList=" + recordInfoExtendList +
                '}';
    }
}
