package com.yingzixiyin.api.enums;

/**
 * @author song.shi
 * @date 2015-07-04
 */
public enum GenderTypeEnum {

    UNKNOWN(0, "未知"),

    MALE(1, "男"),

    FEMALE(2, "女");

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

    GenderTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static GenderTypeEnum toEnum(int code) {
        for (GenderTypeEnum item : GenderTypeEnum.values()) {
            if (code == item.getValue()) {
                return item;
            }
        }
        return null;
    }
}
