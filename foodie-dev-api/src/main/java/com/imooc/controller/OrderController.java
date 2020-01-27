package com.imooc.controller;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.OrdersBO;
import com.imooc.pojo.vo.MerchantOrdersVO;
import com.imooc.service.OrderService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.util.StringUtil;

@Api(value = "订单信息", tags = {"订单增删改查"})
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "创建订单", notes = "创建订单", httpMethod = "POST")
    @PostMapping(value = "/create", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult createOrder(@RequestBody OrdersBO ordersBO) {

        MerchantOrdersVO merchantOrdersVO = orderService.createOrder(ordersBO);
        // 1. 创建订单
        if(merchantOrdersVO == null || StringUtil.isEmpty(merchantOrdersVO.getOrderId())) {
            return IMOOCJSONResult.errorMsg("订单信息有误");
        }
        // 2. 移除购物车的商品 cookie中

        // 3. 把订单信息发送到支付中心
        MerchantOrdersVO.PayCenterOrdersVO payCenterOrdersVO = merchantOrdersVO.getPayCenterOrdersVO();
        payCenterOrdersVO.setReturnUrl(returnUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("imoocUserId", "5658415-287542862");
        httpHeaders.set("password", "3s6r-0eo2-wvr2-r5y6");
        // 请求参数
        HttpEntity<MerchantOrdersVO.PayCenterOrdersVO> httpEntity = new HttpEntity<>(payCenterOrdersVO, httpHeaders);
        // 发送请求
        ResponseEntity<IMOOCJSONResult> responseEntity = restTemplate
                .postForEntity(payCenterUrl, httpEntity, IMOOCJSONResult.class);
        IMOOCJSONResult paymentResult = responseEntity.getBody();
        if(paymentResult == null || paymentResult.getStatus() != 200) {
            return IMOOCJSONResult.errorMsg("支付订单创建有误!");
        }

        return IMOOCJSONResult.ok(merchantOrdersVO.getOrderId());

    }

    @ApiOperation(value = "回调地址-更改订单状态", notes = "回调地址-更改订单状态", httpMethod = "POST")
    @PostMapping(value = "/updateOrderStatus", produces = "application/json;charset=utf-8")
    public Integer updateOrderStatus(@RequestParam("merchantOrderId") String merchantOrderId) {

        // 更改订单状态
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.PAYMENT.value);

        return HttpStatus.OK.value();
    }

    @ApiOperation(value = "更新订单状态-交易完成", notes = "更新订单状态-交易完成", httpMethod = "POST")
    @PostMapping(value = "/getPaidOrderInfo", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult getPaidOrderInfo(@RequestParam("orderId") String orderId) {

        // 更改订单状态
        OrderStatus orderStatus = orderService.getPaidOrderInfo(orderId);

        return IMOOCJSONResult.ok(orderStatus);
    }
}
