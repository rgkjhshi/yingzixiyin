package com.yingzixiyin.api.enums;

/**
 * @author song.shi
 * @date 2015-07-02
 */
public enum ConsultTypeEnum {

    ON_LINE(1, "线上咨询"),

    OFF_LINE(2, "线下咨询");

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

    ConsultTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ConsultTypeEnum toEnum(int code) {
        for (ConsultTypeEnum item : ConsultTypeEnum.values()) {
            if (code == item.getValue()) {
                return item;
            }
        }
        return null;
    }
}
