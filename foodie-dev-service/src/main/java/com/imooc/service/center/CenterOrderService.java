package com.imooc.service.center;

import com.imooc.pojo.vo.center.CenterOrderVO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

/**
 * @Classname CenterOrderService
 * @Description TODO
 * @Date 2020/1/30 22:29
 * @Created by 于明灏
 */
public interface CenterOrderService {

    /**
     * 获取订单信息
     * @param userId
     * @param orderStatus
     * @return
     */
    List<CenterOrderVO> queryCenterOrderInfo(String userId, Integer orderStatus);

    /**
     * 商家发货
     * @param orderId
     * @return
     */
    boolean deliver(String orderId);

    /**
     * 删除订单
     * @param orderId
     * @param userId
     */
    void deleteOrder(String orderId, String userId);

    /**
     * 确认收货
     * @param orderId
     * @return
     */
    boolean confirmReceive(String orderId);
}
