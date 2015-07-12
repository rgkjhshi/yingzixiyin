package com.yingzixiyin.api.dto;

import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class ConsultantQueryResponseDto extends BaseResponseDto {
    private static final long serialVersionUID = 5588604487346912586L;

    private Integer count;    // list中元素个数
    private List<ConsultantInfo> consultantInfoList;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ConsultantInfo> getConsultantInfoList() {
        return consultantInfoList;
    }

    public void setConsultantInfoList(List<ConsultantInfo> consultantInfoList) {
        this.consultantInfoList = consultantInfoList;
    }

    @Override
    public String toString() {
        return "ConsultantQueryResponseDto{" +
                "count=" + count +
                ", consultantInfoList=" + consultantInfoList +
                '}';
    }
}
