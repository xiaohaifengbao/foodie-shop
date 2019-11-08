package com.imooc.enums;

/**
 * @Classname SEX
 * @Description TODO
 * @Date 2019/11/8 17:23
 * @Created by 于明灏
 */
public enum Sex {
    woman(0, "女"),
    man(1, "男"),
    secret(2, "保密");

    public final Integer type;
    public final String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
