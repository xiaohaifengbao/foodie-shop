package com.imooc.controller;

import com.imooc.pojo.Orders;
import com.imooc.service.center.CenterOrderService;
import com.imooc.utils.IMOOCJSONResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname BaseController
 * @Description TODO
 * @Date 2019/11/10 14:18
 * @Created by 于明灏
 */
public class BaseController {

    @Autowired
    private CenterOrderService centerOrderService;

    // 首页
    public static final Integer HOME = 1;
    // 默认每页记录数
    public static final Integer RECORD = 10;
    // 商品展示每页记录数
    public static final Integer ITEM_RECORD = 20;

    // 微信支付发送给天天吃货的回调地址
    String returnUrl = "http://qm7tnz.natappfree.cc/orders/updateOrderStatus";

    // 支付中心的接口地址
    String payCenterUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";


    /**
     * 校验订单和用户，避免用户非法调用
     * @return
     */
    public IMOOCJSONResult checkOrderUser(String userId, String orderId) {

        Orders orders = centerOrderService.checkOrderUser(userId, orderId);

        if(orders == null) {
            return IMOOCJSONResult.errorMsg("订单不存在");
        }
        return IMOOCJSONResult.ok(orders);
    }


}
