package com.imooc.controller.center;

import com.imooc.controller.BaseController;
import com.imooc.enums.YesOrNoEnum;
import com.imooc.pojo.OrderItems;
import com.imooc.pojo.Orders;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.MyCommentBO;
import com.imooc.service.center.CenterUserService;
import com.imooc.service.center.MyCommentService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname MyCommentController
 * @Description TODO
 * @Date 2020/1/28 20:25
 * @Created by 于明灏
 */
@Api(value = "订单评价", tags = {"订单评价增删改查"})
@RestController
@RequestMapping("/mycomments")
public class MyCommentController extends BaseController {

    @Autowired
    private MyCommentService myCommentService;

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", httpMethod = "GET")
    @PostMapping("/pending")
    public IMOOCJSONResult queryPendingItem(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam("userId") String userId,
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParam("orderId") String orderId) {

        IMOOCJSONResult imoocjsonResult = this.checkOrderUser(userId, orderId);
        if(imoocjsonResult.getStatus() != HttpStatus.OK.value()) {
            return IMOOCJSONResult.errorMsg("订单信息有误");
        }
        Orders orders = (Orders) imoocjsonResult.getData();
        // 校验未评价
        if(YesOrNoEnum.YES.type.equals(orders.getIsComment())) {
            return IMOOCJSONResult.errorMsg("商品已评价");
        }

        List<OrderItems> itemList = myCommentService.queryPendingItem(orderId);
        return IMOOCJSONResult.ok(itemList);
    }

    @ApiOperation(value = "保存评论信息", notes = "保存评论信息", httpMethod = "POST")
    @PostMapping("/saveList")
    public IMOOCJSONResult saveList(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam("userId") String userId,
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParam("orderId") String orderId,
            @RequestBody List<MyCommentBO> orderItemList) {


        IMOOCJSONResult imoocjsonResult = this.checkOrderUser(userId, orderId);
        if(imoocjsonResult.getStatus() != HttpStatus.OK.value()) {
            return IMOOCJSONResult.errorMsg("订单信息有误");
        }
        Orders orders = (Orders) imoocjsonResult.getData();
        // 校验未评价
        if(YesOrNoEnum.YES.type.equals(orders.getIsComment())) {
            return IMOOCJSONResult.errorMsg("商品已评价");
        }

        myCommentService.saveMyComment(userId, orderId, orderItemList);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "查询评论列表", notes = "查询评论列表", httpMethod = "POST")
    @PostMapping("/query")
    public IMOOCJSONResult query(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam("userId") String userId,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize) {

        if(page == null) {
            page = HOME;
        }
        if(pageSize == null) {
            pageSize = RECORD;
        }
        PagedGridResult pagedGridResult = myCommentService.query(userId, page, pageSize);

        return IMOOCJSONResult.ok(pagedGridResult);
    }
}
