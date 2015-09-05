package com.yingzixiyin.api.enums;

/**
 * @author song.shi
 * @date 2015-07-02
 */
public enum RangeTypeEnum {

    UNKNOWN(0, "未知"),

    ONE(1, "解救单身"),

    TWO(2, "恋爱情感"),

    THREE(3, "婚姻生活");

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

    RangeTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static RangeTypeEnum toEnum(int code) {
        for (RangeTypeEnum item : RangeTypeEnum.values()) {
            if (code == item.getValue()) {
                return item;
            }
        }
        return null;
    }
}
