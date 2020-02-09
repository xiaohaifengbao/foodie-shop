package com.imooc.pojo.bo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname MyCommentBO
 * @Description 商品评价
 * @Date 2020/2/9 16:55
 * @Created by 于明灏
 */
@ApiModel("商品评价")
@Data
public class MyCommentBO {

    @ApiModelProperty("购买数量")
    private Integer buyCounts;

    @ApiModelProperty("购买数量")
    private String commentId;

    @ApiModelProperty("评价等级 1：好评 2：中评 3：差评")
    private Integer commentLevel;

    @ApiModelProperty("评价内容")
    private String content;

    @ApiModelProperty("订单ID")
    private String id;

    @ApiModelProperty("商品ID")
    private String itemId;

    @ApiModelProperty("商品图片")
    private String itemImg;

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty("商品规格ID")
    private String itemSpecId;

    @ApiModelProperty("商品规格")
    private String itemSpecName;

    @ApiModelProperty("订单ID")
    private String orderId;

    @ApiModelProperty("价格")
    private Integer price;

}
