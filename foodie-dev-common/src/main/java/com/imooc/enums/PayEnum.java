package com.imooc.enums;

public enum PayEnum {
    WEIXIN("微信", 1),
    ALIPAY("支付宝", 2);

    public final String type;
    public final Integer value;

    PayEnum(String type, Integer value) {
        this.type = type;
        this.value = value;
    }
}
