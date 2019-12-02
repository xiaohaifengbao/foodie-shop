package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("创建订单")
@Data
public class OrdersBO {

    @ApiModelProperty("地址ID")
    private String addressId;

    @ApiModelProperty("规格ID集")
    private String itemSpecIds;

    @ApiModelProperty("备注")
    private String leftMsg;

    @ApiModelProperty("支付方式")
    private Integer payMethod;

    @ApiModelProperty("用户ID")
    private String userId;
}
