package com.yingzixiyin.api.dto;

import com.yingzixiyin.api.utils.StringUtils;

import java.util.List;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class ConsultantQueryResponseDto extends BaseResponseDto {
    private static final long serialVersionUID = 5588604487346912586L;

    private Integer count;    // list中元素个数
    private List<ConsultantInfo> consultantList;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ConsultantInfo> getConsultantList() {
        return consultantList;
    }

    public void setConsultantList(List<ConsultantInfo> consultantList) {
        this.consultantList = consultantList;
    }

    @Override
    public String toString() {
        return "ConsultantQueryResponseDto{" +
                "count=" + count +
                ", consultantList=" + StringUtils.listToString(consultantList) +
                '}';
    }
}
