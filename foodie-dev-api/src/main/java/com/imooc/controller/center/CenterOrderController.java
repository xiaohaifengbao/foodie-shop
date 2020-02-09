package com.imooc.controller.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.controller.BaseController;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.vo.center.CenterOrderVO;
import com.imooc.pojo.vo.center.StatusCountVO;
import com.imooc.service.center.CenterOrderService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.annotation.Order;

import java.util.List;

/**
 * @Classname CenterOrderController
 * @Description TODO
 * @Date 2020/1/30 22:20
 * @Created by 于明灏
 */
@Api(value = "订单管理", tags = {"订单信息增删改查"})
@RestController
@RequestMapping("/myorders")
public class CenterOrderController extends BaseController {

    @Autowired
    private CenterOrderService centerOrderService;

    @ApiOperation(value = "查询订单信息", notes = "查询订单信息", httpMethod = "POST")
    @PostMapping("/query")
    public IMOOCJSONResult userInfo(
            @ApiParam(name = "userId", value = "用户ID", example = "191108GST0BS82K4")
            @RequestParam("userId") String userId,
            @ApiParam(name = "orderStatus", value = "订单状态", example = "10")
            @RequestParam("orderStatus") Integer orderStatus,
            @ApiParam(name = "page", value = "当前页", example = "1")
            @RequestParam("page") Integer page,
            @ApiParam(name = "pageSize", value = "每页数", example = "10")
            @RequestParam("pageSize") Integer pageSize) {
        if(page == null) {
            page = HOME;
        }
        if(pageSize == null) {
            pageSize = RECORD;
        }
        if(StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("用户登录错误");
        }
        PageHelper.startPage(page, pageSize);
        // 获取订单集合
        List<CenterOrderVO> orderList = centerOrderService.queryCenterOrderInfo(userId, orderStatus);
        PageInfo pageInfo = new PageInfo<>(orderList);

        return IMOOCJSONResult.ok(new PagedGridResult<>(pageInfo));
    }

    @ApiOperation(value = "商品发货", notes = "商品发货", httpMethod = "POST")
    @PostMapping("/deliver")
    public IMOOCJSONResult deliver(
            @ApiParam(name = "orderId", value = "订单ID", example = "191108GST0BS82K4", required = true)
            @RequestParam("orderId") String orderId) {


        if(StringUtils.isBlank(orderId)) {
            return IMOOCJSONResult.errorMsg("订单ID不能为空");
        }
        boolean isDeliver = centerOrderService.deliver(orderId);
        if(isDeliver) {
            return IMOOCJSONResult.ok();
        }else {
            return IMOOCJSONResult.errorMsg("变更发货有误,请重新查看订单状态");
        }

    }

    @ApiOperation(value = "删除订单", notes = "删除订单", httpMethod = "POST")
    @PostMapping("/delete")
    public IMOOCJSONResult delete(
            @ApiParam(name = "userId", value = "订单ID", required = true)
            @RequestParam("userId") String userId,
            @ApiParam(name = "orderId", value = "用户ID", required = true)
            @RequestParam("orderId") String orderId) {

        if(StringUtils.isBlank(orderId)) {
            return IMOOCJSONResult.errorMsg("订单ID不能为空");
        }

        centerOrderService.deleteOrder(orderId,userId);

        return IMOOCJSONResult.ok();

    }

    @ApiOperation(value = "确认收货", notes = "确认收货", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public IMOOCJSONResult confirmReceive(
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParam("orderId") String orderId) {

        if(StringUtils.isBlank(orderId)) {
            return IMOOCJSONResult.errorMsg("订单ID不能为空");
        }

        boolean isReceive = centerOrderService.confirmReceive(orderId);

        if(isReceive) {
            return IMOOCJSONResult.ok();
        }else {
            return IMOOCJSONResult.errorMsg("确认收货失败");
        }

    }

    @ApiOperation(value = "我的订单数", notes = "我的订单数", httpMethod = "POST")
    @PostMapping("/statusCounts")
    public IMOOCJSONResult statusCounts(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam("userId") String userId) {

        if(StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("用户ID不能为空");
        }

        StatusCountVO statusCountVO = centerOrderService.statusCounts(userId);

        return IMOOCJSONResult.ok(statusCountVO);
    }

    @ApiOperation(value = "订单动向", notes = "订单动向", httpMethod = "POST")
    @PostMapping("/trend")
    public IMOOCJSONResult trend(
            @ApiParam(name = "trend", value = "用户ID", required = true)
            @RequestParam("userId") String userId,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize) {

        if(StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("用户ID不能为空");
        }

        PagedGridResult pagedGridResult = centerOrderService.trend(userId, page, pageSize);

        return IMOOCJSONResult.ok(pagedGridResult);
    }

}
