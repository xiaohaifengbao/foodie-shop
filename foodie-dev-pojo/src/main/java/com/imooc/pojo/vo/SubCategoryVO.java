package com.imooc.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname CategoryVO
 * @Description TODO
 * @Date 2019/11/9 20:42
 * @Created by 于明灏
 */
@ApiModel("三级分类")
@Data
public class SubCategoryVO {

    @ApiModelProperty("主键")
    private Integer subId;

    @ApiModelProperty("名称")
    private String subName;

    @ApiModelProperty("类型")
    private Integer subType;

    @ApiModelProperty("父ID")
    private Integer subFatherId;
}
