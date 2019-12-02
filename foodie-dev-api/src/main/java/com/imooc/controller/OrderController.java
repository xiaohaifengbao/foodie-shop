package com.imooc.controller;

import com.imooc.pojo.bo.OrdersBO;
import com.imooc.service.OrderService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "创建订单", notes = "创建订单", httpMethod = "POST")
    @PostMapping(value = "/create", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult createOrder(@RequestBody OrdersBO ordersBO) {

        if(ordersBO != null && ordersBO.getPayMethod() == 1 || ordersBO.getPayMethod() == 2) {
            orderService.createOrder(ordersBO);
            return IMOOCJSONResult.ok();
        }

        return IMOOCJSONResult.errorMsg("订单信息有误");

    }
}
