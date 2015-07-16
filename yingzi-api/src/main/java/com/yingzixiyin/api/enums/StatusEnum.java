package com.yingzixiyin.api.enums;

/**
 * @author song.shi
 * @date 2015-07-04
 */
public enum StatusEnum {

    INIT(0, "初始状态"),

    APPLIED(1, "申请"),

    ACCEPTED(2, "审核通过"),

    REFUSED(3, "审核不通过");

    private Integer value;

    private String desc;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    StatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static StatusEnum toEnum(int code) {
        for (StatusEnum item : StatusEnum.values()) {
            if (code == item.getValue()) {
                return item;
            }
        }
        return null;
    }
}
