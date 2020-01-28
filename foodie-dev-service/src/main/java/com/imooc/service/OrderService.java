package com.imooc.service;


import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.OrdersBO;
import com.imooc.pojo.vo.MerchantOrdersVO;

public interface OrderService {

    /**
     * 创建订单
     * @param ordersBO
     */
    MerchantOrdersVO createOrder(OrdersBO ordersBO);

    /**
     * 支付回调-更改订单状态
     * @param orderId
     * @param value
     */
    void updateOrderStatus(String orderId, Integer value);

    /**
     * 获取订单状态信息
     * @param orderId
     * @return
     */
    OrderStatus getPaidOrderInfo(String orderId);

    /**
     * 定时关闭订单
     */
    void closeOrder();
}
