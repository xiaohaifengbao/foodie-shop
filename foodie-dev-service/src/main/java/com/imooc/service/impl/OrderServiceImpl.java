package com.imooc.service.impl;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayEnum;
import com.imooc.enums.YesOrNoEnum;
import com.imooc.mapper.OrderItemsMapper;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.*;
import com.imooc.pojo.bo.OrdersBO;
import com.imooc.pojo.vo.MerchantOrdersVO;
import com.imooc.service.ItemInfoService;
import com.imooc.service.OrderService;
import com.imooc.service.UserAddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrdersMapper ordersMapper;

    @Autowired
    private UserAddressService userAddressService;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private ItemInfoService itemInfoService;

    @Resource
    private OrderItemsMapper orderItemsMapper;

    /*@Autowired
    private Sid sid;*/

    public static Sid sid = new Sid();


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public MerchantOrdersVO createOrder(OrdersBO ordersBO) {

        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();

        String addressId = ordersBO.getAddressId();
        String userId = ordersBO.getUserId();
        String leftMsg = ordersBO.getLeftMsg();
        Integer payMethod = ordersBO.getPayMethod();
        String itemSpecIds = ordersBO.getItemSpecIds();
        // 邮费
        Integer postAmount = 0;

        // 1. 主表订单
        Orders newOrder = new Orders();
        String orderId = sid.nextShort();
        newOrder.setId(orderId);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());
        newOrder.setIsComment(YesOrNoEnum.NO.type);
        newOrder.setIsDelete(YesOrNoEnum.NO.type);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setUserId(userId);
        newOrder.setPayMethod(payMethod);
        newOrder.setPostAmount(postAmount);
        // 订单总金额
        Integer totalPrice = 0;
        // 订单实际金额
        Integer price = 0;
        // 获取收货人信息
        UserAddress userAddress = userAddressService.queryUserAddressById(addressId);
        newOrder.setReceiverName(userAddress.getReceiver());
        newOrder.setReceiverMobile(userAddress.getMobile());
        // 收货地址
        newOrder.setReceiverAddress(userAddress.getProvince()
                + " " + userAddress.getCity()
                + " " + userAddress.getDistrict()
                + " " + userAddress.getDetail());


        // 2. 订单商品表
        String[] specArray = itemSpecIds.split(",");
        for (String specId : specArray) {
            OrderItems orderItems = new OrderItems();
            orderItems.setId(sid.nextShort());
            orderItems.setOrderId(orderId);
            orderItems.setItemSpecId(specId);
            // 通过规格查询规格信息
            ItemsSpec itemsSpec = itemInfoService.queryItemInfoBySpecId(specId);
            // TODO 购买数量从购物车中获得
            Integer buyCounts = 1;
            orderItems.setBuyCounts(buyCounts);
            // 总金额
            Integer totalAmount = itemsSpec.getPriceDiscount() * buyCounts;
            // 实际支付金额
            Integer priceDiscount = itemsSpec.getPriceNormal() * buyCounts;
            orderItems.setPrice(priceDiscount);
            // 商品ID
            String itemId = itemsSpec.getItemId();
            orderItems.setItemId(itemId);
            // 获取商品信息
            Items items = itemInfoService.selectItemInfo(itemId);
            orderItems.setItemName(items.getItemName());
            orderItems.setItemSpecName(itemsSpec.getName());
            // 获取商品图片信息
            ItemsImg itemsImg = itemInfoService.queryItemImgById(itemId);
            orderItems.setItemImg(itemsImg.getUrl());
            // 入库
            orderItemsMapper.insertSelective(orderItems);
            totalPrice += totalAmount;
            price += priceDiscount;
            // 扣除库存
            itemInfoService.decreaseStock(itemsSpec.getId(), buyCounts);
        }

        // 订单金额和实际支付价格
        newOrder.setTotalAmount(totalPrice);
        newOrder.setRealPayAmount(price);
        // 商品信息入库
        ordersMapper.insertSelective(newOrder);

        // 3. 订单状态表
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.UN_PAYMENT.value);
        orderStatus.setCreatedTime(new Date());
        // 入库
        orderStatusMapper.insertSelective(orderStatus);

        // 构建支付中心订单信息
        MerchantOrdersVO.PayCenterOrdersVO payCenterOrdersVO = new MerchantOrdersVO.PayCenterOrdersVO();
        payCenterOrdersVO.setPayMethod(PayEnum.WEIXIN.value);
        // TODO 假数据 一分钱方便测试
        payCenterOrdersVO.setAmount(1);
        payCenterOrdersVO.setMerchantOrderId(orderId);
        payCenterOrdersVO.setMerchantUserId(userId);

        merchantOrdersVO.setPayCenterOrdersVO(payCenterOrdersVO);
        merchantOrdersVO.setOrderId(orderId);

        return merchantOrdersVO;
    }

    @Override
    public void updateOrderStatus(String orderId, Integer value) {

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(value);
        orderStatus.setPayTime(new Date());

        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Override
    public OrderStatus getPaidOrderInfo(String orderId) {

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);

        return orderStatusMapper.selectOne(orderStatus);
    }


}
