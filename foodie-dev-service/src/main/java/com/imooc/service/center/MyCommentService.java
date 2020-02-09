package com.imooc.service.center;


import com.imooc.pojo.OrderItems;
import com.imooc.pojo.bo.center.MyCommentBO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

/**
 * @Classname CenterUserService
 * @Description TODO
 * @Date 2020/1/28 20:31
 * @Created by 于明灏
 */
public interface MyCommentService {

    /**
     * 根据订单查询商品
     * @param orderId
     * @return
     */
   List<OrderItems> queryPendingItem(String orderId);

    /**
     * 发表评论
     * @param userId
     * @param orderId
     * @param orderItemList
     */
    void saveMyComment(String userId, String orderId, List<MyCommentBO> orderItemList);

    /**
     * 查询评价列表
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult query(String userId, Integer page, Integer pageSize);
}
