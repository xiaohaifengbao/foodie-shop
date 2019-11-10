package com.imooc.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SixNewItemsVO
 * @Description TODO
 * @Date 2019/11/9 23:58
 * @Created by 于明灏
 */
@ApiModel("分类下的最新商品")
@Data
public class SixItemsVO {

    @ApiModelProperty("商品ID")
    private String itemId;

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty("商品图片")
    private String itemUrl;
}
