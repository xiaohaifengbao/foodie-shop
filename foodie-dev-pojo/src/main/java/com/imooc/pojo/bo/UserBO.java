package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname UserBO
 * @Description TODO
 * @Date 2019/11/8 17:07
 * @Created by 于明灏
 */
@ApiModel("用户注册信息")
@Data
public class UserBO {

    @ApiModelProperty(value = "用户名", name = "username", dataType = "String", example = "admin", required = true)
    private String username;

    @ApiModelProperty(value = "密码", name = "password", dataType = "String", example = "123456", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码", name = "confirmPassword", dataType = "String", example = "123456", required = false)
    private String confirmPassword;
}
