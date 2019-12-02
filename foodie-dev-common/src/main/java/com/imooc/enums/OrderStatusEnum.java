package com.imooc.enums;

public enum OrderStatusEnum {
    UN_PAYMENT("未付款", 10),
    PAYMENT("已付款", 20),
    SHIP("已发货", 30),
    BUSINESS_SUCCESS("交易成功", 40),
    BUSINESS_CLOSE("交易关闭", 50);

    public final String type;
    public final Integer value;

    OrderStatusEnum(String type, Integer value) {
        this.type = type;
        this.value = value;
    }
}
