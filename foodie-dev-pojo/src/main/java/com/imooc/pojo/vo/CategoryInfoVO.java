package com.imooc.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname SixNewItemsVO
 * @Description TODO
 * @Date 2019/11/9 23:58
 * @Created by 于明灏
 */
@ApiModel("下拉一级分类")
@Data
public class CategoryInfoVO {

    @ApiModelProperty("主键")
    private Integer rootCatId;

    @ApiModelProperty("一级分类名称")
    private String rootCatName;

    @ApiModelProperty("口号")
    private String slogan;

    @ApiModelProperty("一级分类图片")
    private String catImage;

    @ApiModelProperty("分类下的最新商品")
    private List<SixItemsVO> simpleItemList;
}
