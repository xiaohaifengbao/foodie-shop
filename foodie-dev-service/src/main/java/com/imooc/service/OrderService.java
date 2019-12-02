package com.imooc.service;


import com.imooc.pojo.bo.OrdersBO;

public interface OrderService {

    /**
     * 创建订单
     * @param ordersBO
     */
    public void createOrder(OrdersBO ordersBO);
}
