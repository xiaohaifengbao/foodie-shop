package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2019/11/8 15:52
 * @Created by 于明灏
 */
@Api(tags = "用户-登录注册")
@Slf4j
@RestController
@RequestMapping("/passport")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名校验", notes = "用户名校验", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", example = "admin", dataType = "string", paramType = "query", required = true)
    })
    @GetMapping(value = "usernameIsExist", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult queryUsernameIsExist(@RequestParam("username") String username) {

        // 1. 判断用户名是否为空
        if(StringUtils.isBlank("username")) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        // 2. 判断用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if(isExist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        // 3. 请求成功,用户名可以使用
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping(value = "/regist", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult createUser(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 得到用户名和密码
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        // 1. 用户名和密码不能为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("用户名和密码不能为空");
        }
        // 2. 用户名不能存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if(isExist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        // 3. 密码长度不能小于6位
        if(password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码不能小于6位");
        }
        // 4. 两个密码不能一致
        if(!password.equals(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("两次密码不一致");
        }
        // 注册成功
            // 密码加密存入数据库
        userBO.setPassword(MD5Utils.getMD5Str(password));
        Users user = userService.createUser(userBO);

        // cookie中保留信息
        this.setNullByUser(user);
        // 用户名信息存入cookie中
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        return IMOOCJSONResult.ok(user);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping(value = "/login", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult loginUser(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // 用户名或密码不能为空
       if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
           return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
       }

        // 密码加密处理
        password = MD5Utils.getMD5Str(password);
        Users user = userService.loginUser(username, password);
        if(user == null) {
            return IMOOCJSONResult.errorMsg("用户名或者密码不正确");
        }

        // cookie中保留信息
        this.setNullByUser(user);
        // 用户名信息存入cookie中
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        return IMOOCJSONResult.ok(user);
    }

    @ApiOperation(value = "用户退出", notes = "用户退出", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "cookie的name", example = "user", dataType = "String", required = true)
    })
    @PostMapping(value = "/logout", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult logoutUser(@RequestParam("userId") String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            // 清空cookie
            CookieUtils.deleteCookie(request, response, "user");
            return IMOOCJSONResult.ok();
        }catch (Exception e) {
            log.error("logoutUser error [{}]", e.getMessage());
            return IMOOCJSONResult.errorMsg("退出失败, 请稍后再试");
        }
    }

    /**
     * cookie中的保留信息
     * @param user
     */
    private void setNullByUser(Users user) {
        user.setRealname(null);
        user.setPassword(null);
        user.setBirthday(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setSex(null);
    }
}
