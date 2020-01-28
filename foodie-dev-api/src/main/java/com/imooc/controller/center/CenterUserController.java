package com.imooc.controller.center;

import com.imooc.pojo.Users;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname CenterUserController
 * @Description TODO
 * @Date 2020/1/28 20:25
 * @Created by 于明灏
 */
@Api(value = "用户中心", tags = {"用户中心增删改查"})
@RestController
@RequestMapping("/center")
public class CenterUserController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", httpMethod = "GET")
    @GetMapping("/userInfo")
    public IMOOCJSONResult userInfo(
            @ApiParam(name = "userId", value = "用户ID", example = "191108GST0BS82K4")
            @RequestParam("userId") String userId) {

        Users user = centerUserService.queryUsersInfoById(userId);
        return IMOOCJSONResult.ok(user);
    }
}
