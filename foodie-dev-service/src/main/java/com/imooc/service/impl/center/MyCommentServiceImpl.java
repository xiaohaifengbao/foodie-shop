package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.enums.YesOrNoEnum;
import com.imooc.mapper.*;
import com.imooc.pojo.ItemsComments;
import com.imooc.pojo.OrderItems;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.Orders;
import com.imooc.pojo.bo.center.MyCommentBO;
import com.imooc.pojo.vo.center.MyCommentVO;
import com.imooc.service.center.MyCommentService;
import com.imooc.utils.PagedGridResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.code.ORDER;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Classname MyCommentServiceImpl
 * @Description TODO
 * @Date 2020/2/6 11:31
 * @Created by 于明灏
 */
@Service
public class MyCommentServiceImpl implements MyCommentService {

    @Resource
    private OrderItemsMapper orderItemsMapper;
    @Resource
    private MyCommentMapperCustom myCommentMapperCustom;
    @Resource
    private OrderStatusMapper orderStatusMapper;
    @Resource
    private ItemsCommentsMapper itemsCommentsMapper;
    @Resource
    private OrdersMapper ordersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingItem(String orderId) {

        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(orderId);
        return orderItemsMapper.select(orderItems);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void saveMyComment(String userId, String orderId, List<MyCommentBO> orderItemList) {
        Sid sid = new Sid();

        // 1. 商品评价表插入数据
        orderItemList.forEach(model -> {
            ItemsComments itemsComments = new ItemsComments();
            BeanUtils.copyProperties(model, itemsComments);
            String newId = sid.nextShort();
            itemsComments.setId(newId);
            itemsComments.setCreatedTime(new Date());
            itemsComments.setUserId(userId);
            itemsComments.setSepcName(model.getItemSpecName());
            // 入库
            itemsCommentsMapper.insertSelective(itemsComments);
        });

        // 2. 订单状态表更新评价时间
        Example orderStatusExample = new Example(OrderStatus.class);
        Example.Criteria criteria = orderStatusExample.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setCommentTime(new Date());
        // 入库
        orderStatusMapper.updateByExampleSelective(orderStatus, orderStatusExample);

        // 3. 订单表更改评价状态
        Example orderExample = new Example(Orders.class);
        Example.Criteria orderExampleCriteria = orderExample.createCriteria();
        orderExampleCriteria.andEqualTo("id", orderId);
        Orders orders = new Orders();
        orders.setIsComment(YesOrNoEnum.YES.type);
        orders.setUpdatedTime(new Date());
        // 入库
        ordersMapper.updateByExampleSelective(orders, orderExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult query(String userId, Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        List<MyCommentVO> commentList = myCommentMapperCustom.query(userId);

        return new PagedGridResult(new PageInfo(commentList));
    }
}
