package com.imooc.controller.center;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname CenterUserInfoController
 * @Description TODO
 * @Date 2020/1/28 21:01
 * @Created by 于明灏
 */
@Api(value = "用户信息", tags = {"用户信息增删改查"})
@RestController
@RequestMapping("/userInfo")
public class CenterUserInfoController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(
            @ApiParam(name = "userId", value = "用户ID", example = "191108GST0BS82K4")
            @RequestParam("userId") String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (result.hasErrors()) { // 校验不通过
            Map<String, String> errorMsg = this.getErrorMsg(result);
            return IMOOCJSONResult.errorMap(errorMsg);
        }

        // 获取更新后的用户信息
        Users user = centerUserService.updateUserInfo(centerUserBO, userId);
        // 同步更新cookie中的用户信息
        CookieUtils.setCookie(request, response, "users", JsonUtils.objectToJson(user), true);
        return IMOOCJSONResult.ok();
    }

    private Map<String, String> getErrorMsg(BindingResult result) {
        HashMap<String, String> returnMap = new HashMap<>();

        List<FieldError> errorList = result.getFieldErrors();
        errorList.forEach(errorModel -> {
            returnMap.put(errorModel.getField(), errorModel.getDefaultMessage());
        });
        return returnMap;
    }
}
