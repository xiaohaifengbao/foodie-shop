package com.imooc.config;

import com.imooc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Classname OrderJob
 * @Description TODO
 * @Date 2020/1/28 15:28
 * @Created by 于明灏
 */
@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "1-2 0 0/1 * * ?")
    public void autoCloseOrder() {
        orderService.closeOrder();
        System.out.println("执行关闭订单操作");
    }
}
