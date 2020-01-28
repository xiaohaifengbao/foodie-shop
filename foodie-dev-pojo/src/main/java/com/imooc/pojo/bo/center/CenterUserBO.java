package com.imooc.pojo.bo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @Classname CenterUserBO
 * @Description TODO
 * @Date 2020/1/28 20:53
 * @Created by 于明灏
 */
@ApiModel("用户信息")
@Data
public class CenterUserBO {

    @ApiModelProperty("用户ID")
    private String id;

    @ApiModelProperty("生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式错误")
    private String email;

    @ApiModelProperty("头像")
    private String face;

    @ApiModelProperty("电话")
    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message = "手机号格式不正确")
    private String mobile;

    @ApiModelProperty("昵称")
    @Length(max = 12, message = "昵称长度不能超过12位")
    private String nickname;

    @ApiModelProperty("真实姓名")
    @Length(max = 12, message = "真实姓名长度不能超过12位")
    private String realname;

    @ApiModelProperty("性别")
    @Min(value = 1, message = "性别输入有误")
    @Max(value = 2, message = "性别输入有误")
    private Integer sex;

}
