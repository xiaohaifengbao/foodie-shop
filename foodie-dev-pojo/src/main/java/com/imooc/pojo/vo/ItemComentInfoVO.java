package com.imooc.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Classname ItemComentInfoVO
 * @Description TODO
 * @Date 2019/11/10 13:32
 * @Created by 于明灏
 */
@ApiModel("商品评价详细信息列表")
@Data
public class ItemComentInfoVO {

    @ApiModelProperty("用户名")
    private String nickname;

    @ApiModelProperty("头像")
    private String userFace;

    @ApiModelProperty("评价时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty("评论")
    private String content;

    @ApiModelProperty("规格")
    private String specName;

}
