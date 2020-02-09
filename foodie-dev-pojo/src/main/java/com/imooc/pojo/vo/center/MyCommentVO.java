package com.imooc.pojo.vo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname MyCommentVO
 * @Description TODO
 * @Date 2020/2/9 18:18
 * @Created by 于明灏
 */
@ApiModel("评价列表")
@Data
public class MyCommentVO {

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdTime;

    @ApiModelProperty("商品图片")
    private String itemImg;

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty("商品评价")
    private String content;

    @ApiModelProperty("商品规格")
    private String specName;

}
