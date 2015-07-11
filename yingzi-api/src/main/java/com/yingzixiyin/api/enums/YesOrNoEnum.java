package com.yingzixiyin.api.enums;

/**
 * @author song.shi
 * @date 2015-07-11
 */
public enum YesOrNoEnum {

    NO(0, "NO"),

    YES(1, "YES");

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

    YesOrNoEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static YesOrNoEnum toEnum(int code) {
        for (YesOrNoEnum item : YesOrNoEnum.values()) {
            if (code == item.getValue()) {
                return item;
            }
        }
        return null;
    }
}
