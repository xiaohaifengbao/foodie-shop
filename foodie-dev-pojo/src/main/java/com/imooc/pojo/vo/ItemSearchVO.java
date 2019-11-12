package com.imooc.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname ItemSearchVO
 * @Description TODO
 * @Date 2019/11/12 9:53
 * @Created by 于明灏
 */
@ApiModel("商品搜索")
@Data
public class ItemSearchVO {

    @ApiModelProperty("商品ID")
    private String itemId;

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty("商品ID")
    private Integer price;

    @ApiModelProperty("商品图片")
    private String imgUrl;

    @ApiModelProperty("商品销量")
    private Integer sellCounts;
}
