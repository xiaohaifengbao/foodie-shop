package com.imooc.pojo.vo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname CenterOrderItemVO
 * @Description TODO
 * @Date 2020/1/30 23:38
 * @Created by 于明灏
 */
@ApiModel("订单商品")
@Data
public class CenterOrderItemVO {

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty("商品ID")
    private String itemId;

    @ApiModelProperty("商品图片")
    private String itemImg;

    @ApiModelProperty("规格名称")
    private String itemSpecName;

    @ApiModelProperty("商品单价")
    private Integer price;

    @ApiModelProperty("购买数量")
    private Integer buyCounts;
}
