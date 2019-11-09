package com.imooc.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname CategoryVO
 * @Description TODO
 * @Date 2019/11/9 20:42
 * @Created by 于明灏
 */
@ApiModel("二级分类")
@Data
public class CategoryVO {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("父ID")
    private Integer fatherId;

    @ApiModelProperty("三级分类集合")
    private List<SubCategoryVO> subCatList;
}
