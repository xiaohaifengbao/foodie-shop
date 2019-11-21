package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname UpdateAddressBO
 * @Description 用户地址
 * @Date 2019/11/21 18:01
 * @Created by 于明灏
 */
@ApiModel("用户地址")
@Data
public class AddressBO {

    @ApiModelProperty("地址主键")
    private String addressId;

    @ApiModelProperty("收货人")
    private String receiver;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("区县")
    private String district;

    @ApiModelProperty("详细地址")
    private String detail;
}
