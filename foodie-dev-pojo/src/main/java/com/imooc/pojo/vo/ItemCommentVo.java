package com.imooc.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname ItemCommentVo
 * @Description 商品评价信息
 * @Date 2019/11/10 13:02
 * @Created by 于明灏
 */
@ApiModel("商品评价信息")
@Data
public class ItemCommentVo {

    @ApiModelProperty("差评")
    private Integer badCounts;

    @ApiModelProperty("好评")
    private Integer goodCounts;

    @ApiModelProperty("中评")
    private Integer normalCounts;

    @ApiModelProperty("全部评价")
    private Integer totalCounts;

}
