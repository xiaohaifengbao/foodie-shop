package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.YesOrNoEnum;
import com.imooc.mapper.CenterOrderMapperCustom;
import com.imooc.mapper.OrderMapperCustom;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.Orders;
import com.imooc.pojo.vo.center.CenterOrderVO;
import com.imooc.pojo.vo.center.CenterTrendVO;
import com.imooc.pojo.vo.center.StatusCountVO;
import com.imooc.service.center.CenterOrderService;
import com.imooc.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.code.ORDER;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname CenterOrderServiceImpl
 * @Description TODO
 * @Date 2020/1/31 0:10
 * @Created by 于明灏
 */
@Service
public class CenterOrderServiceImpl implements CenterOrderService {

    @Resource
    private OrderMapperCustom orderMapperCustom;
    @Resource
    private OrderStatusMapper orderStatusMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private CenterOrderMapperCustom centerOrderMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CenterOrderVO> queryCenterOrderInfo(String userId, Integer orderStatus) {

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId);
        if(orderStatus != null) {
            paramsMap.put("orderStatus", orderStatus);
        }
        List<CenterOrderVO> returnList = orderMapperCustom.queryCenterOrderInfo(paramsMap);
        return returnList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deliver(String orderId) {

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.SHIP.value);
        orderStatus.setDeliverTime(new Date());
        int result = orderStatusMapper.updateByPrimaryKeySelective(orderStatus);

        return result == 1 ? true : false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteOrder(String orderId, String userId) {

        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setUserId(userId);
        orders.setIsDelete(YesOrNoEnum.YES.type);

        ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public boolean confirmReceive(String orderId) {

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.BUSINESS_SUCCESS.value);
        orderStatus.setSuccessTime(new Date());
        int result = orderStatusMapper.updateByPrimaryKeySelective(orderStatus);

        return result == 1 ? true : false;
    }

    @Override
    public Orders checkOrderUser(String userId, String orderId) {

        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setId(orderId);

        return ordersMapper.selectOne(orders);
    }

    @Override
    public StatusCountVO statusCounts(String userId) {

        Integer waitPayCounts = centerOrderMapperCustom
                .statusCounts(userId, OrderStatusEnum.UN_PAYMENT.value);
        Integer waitDeliverCounts = centerOrderMapperCustom
                .statusCounts(userId, OrderStatusEnum.PAYMENT.value);
        Integer waitReceiveCounts = centerOrderMapperCustom
                .statusCounts(userId, OrderStatusEnum.SHIP.value);
        Integer waitCommentCounts = centerOrderMapperCustom
                .statusCounts(userId, OrderStatusEnum.BUSINESS_SUCCESS.value);

        return new StatusCountVO(waitPayCounts,
                                waitDeliverCounts,
                                waitReceiveCounts,
                                waitCommentCounts);

    }

    @Override
    public PagedGridResult trend(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CenterTrendVO> modelList =  centerOrderMapperCustom.trend(userId);
        return new PagedGridResult(new PageInfo(modelList));
    }
}
