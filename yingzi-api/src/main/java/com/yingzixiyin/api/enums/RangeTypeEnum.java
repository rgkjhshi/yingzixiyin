package com.yingzixiyin.api.enums;

/**
 * @author song.shi
 * @date 2015-07-02
 */
public enum RangeTypeEnum {


    ONE(1, "恋爱关系"),

    TWO(2, "情侣关系"),

    THREE(3, "婚前关系"),

    FOUR(4, "夫妻关系");

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
