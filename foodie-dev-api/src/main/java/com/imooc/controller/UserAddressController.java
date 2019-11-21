package com.imooc.controller;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.UserAddressService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname UserAddressController
 * @Description 用户地址
 * @Date 2019/11/21 10:57
 * @Created by 于明灏
 */
@Api(value = "用户收获地址", tags = {"用户收获地址增删改查"})
@RequestMapping("/address")
@RestController
@Slf4j
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "查询收货地址", notes = "查询收货地址", httpMethod = "GET")
    @GetMapping(value = "/list", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult selectUserAddress(@RequestParam("userId") String userId) {

        if(StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("查询失败");
        }

        List<UserAddress> returnList = userAddressService.selectAddress(userId);
        return IMOOCJSONResult.ok(returnList);
    }

    @ApiOperation(value = "添加收货地址", notes = "添加收货地址", httpMethod = "POST")
    @PostMapping(value = "/add", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult saveUserAddress(@RequestBody AddressBO addressBO) {

        if(StringUtils.isBlank(addressBO.getUserId())) {
            return IMOOCJSONResult.errorMsg("用户ID不能为空");
        }
        IMOOCJSONResult imoocjsonResult = this.checkUserAddress(addressBO);
        if(imoocjsonResult.getStatus() != 200) {
            return imoocjsonResult;
        }
        UserAddress userAddress = userAddressService.saveUserAddress(addressBO);
        return IMOOCJSONResult.ok(userAddress);
    }

    @ApiOperation(value = "修改收货地址", notes = "修改收货地址", httpMethod = "PUT")
    @PutMapping(value = "/update", produces = "application/json;charset=utf-8")
    public IMOOCJSONResult updateUserAddress(@RequestBody AddressBO addressBO) {

        if(StringUtils.isBlank(addressBO.getUserId())) {
            return IMOOCJSONResult.errorMsg("用户ID不能为空");
        }
        IMOOCJSONResult imoocjsonResult = this.checkUserAddress(addressBO);
        if(imoocjsonResult.getStatus() != 200) {
            return imoocjsonResult;
        }

        UserAddress userAddress = userAddressService.updateUserAddress(addressBO);

        return IMOOCJSONResult.ok(userAddress);
    }

    /**
     * 校验用户地址信息
     * @param addressBO
     * @return
     */
    private IMOOCJSONResult checkUserAddress(AddressBO addressBO) {
        // 1. 收货人姓名不能为空
        if(StringUtils.isBlank(addressBO.getReceiver())) {
            return IMOOCJSONResult.errorMsg("收货人姓名不能为空");
        }
        // 2. 收货人姓名不能太长
        if(addressBO.getReceiver().length() > 12) {
            return IMOOCJSONResult.errorMsg("收货人姓名不能太长");
        }
        // 3. 手机不能为空
        if(StringUtils.isBlank(addressBO.getMobile())) {
            return IMOOCJSONResult.errorMsg("手机不能为空");
        }
        // 4. 手机号长度为11位
        if(addressBO.getMobile().length() != 11) {
            return IMOOCJSONResult.errorMsg("手机号长度为11位");
        }
        // 5. 校验手机号格式
        if(!MobileEmailUtils.checkMobileIsOk(addressBO.getMobile())) {
            return IMOOCJSONResult.errorMsg("校验手机号格式");
        }
        // 6. 详细地址不能为空
        if(StringUtils.isBlank(addressBO.getDetail())
                || StringUtils.isBlank(addressBO.getProvince())
                || StringUtils.isBlank(addressBO.getCity())
                || StringUtils.isBlank(addressBO.getDistrict())) {
            return IMOOCJSONResult.errorMsg("详细地址不能为空");
        }
        return IMOOCJSONResult.ok();
    }
}
