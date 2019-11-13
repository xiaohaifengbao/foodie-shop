package com.imooc.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname ShopCartVO
 * @Description TODO
 * @Date 2019/11/13 14:44
 * @Created by 于明灏
 */
@ApiModel("刷新购物车")
@Data
public class ShopCartVO {

    @ApiModelProperty("商品ID")
    private String itemId;

    @ApiModelProperty("商品图片")
    private String itemImgUrl;

    @ApiModelProperty("商品标题")
    private String itemName;

    @ApiModelProperty("优惠价")
    private Integer priceDiscount;

    @ApiModelProperty("原价")
    private Integer priceNormal;

    @ApiModelProperty("规格ID")
    private String specId;

    @ApiModelProperty("规格名称")
    private String specName;




}
