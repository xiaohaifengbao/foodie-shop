package com.imooc.enums;

/**
 * @Classname YesOrNoEnum
 * @Description TODO
 * @Date 2019/11/9 20:18
 * @Created by 于明灏
 */
public enum LevelEnum {
    LEVEL1(1, "一级分类"),
    LEVEL2(2, "二级分类"),
    LEVEL3(3, "三级分类"),
    LEVEL4(4, "四级分类");

    public final Integer type;
    public final String value;

    LevelEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
