package com.imooc.enums;

/**
 * @Classname YesOrNoEnum
 * @Description TODO
 * @Date 2019/11/9 20:18
 * @Created by 于明灏
 */
public enum YesOrNoEnum {
    YES(1, "是"),
    NO(0, "否");

    public final Integer type;
    public final String value;

    YesOrNoEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
