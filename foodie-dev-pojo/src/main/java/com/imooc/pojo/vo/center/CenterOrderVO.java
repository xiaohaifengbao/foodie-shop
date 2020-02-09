package com.imooc.pojo.vo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Classname CenterOrderVO
 * @Description TODO
 * @Date 2020/1/30 22:33
 * @Created by 于明灏
 */
@ApiModel("用户中心订单")
@Data
public class CenterOrderVO {

    @ApiModelProperty("订单ID")
    private String orderId;

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdTime;

    @ApiModelProperty("支付方式")
    private Integer payMethod;

    @ApiModelProperty("订单支付真实金额")
    private Integer realPayAmount;

    @ApiModelProperty("订单状态")
    private Integer orderStatus;

    @ApiModelProperty("订单运费")
    private Integer postAmount;

    @ApiModelProperty("是否评价")
    private Integer isComment;

    @ApiModelProperty("商品信息")
    private List<CenterOrderItemVO> subOrderItemList;
}
