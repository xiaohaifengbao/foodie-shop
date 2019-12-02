package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;

import java.util.List;

/**
 * @Classname UserAddressService
 * @Description TODO
 * @Date 2019/11/21 10:53
 * @Created by 于明灏
 */
public interface UserAddressService {

    /**
     * 查询用户下的所有收货地址
     * @param userId
     * @return
     */
    List<UserAddress> selectAddress(String userId);

    /**
     * 保存用户地址
     * @param addressBO
     */
    UserAddress saveUserAddress(AddressBO addressBO);

    /**
     * 修改用户地址
     * @param addressBO
     * @return
     */
    UserAddress updateUserAddress(AddressBO addressBO);

    /**
     * 删除用户地址
     * @param addressId
     */
    void deleteUserAddress(String addressId);

    /**
     * 设置默认地址
     * @param addressId
     * @param userId
     */
    void setDefaultAddress(String addressId, String userId);

    /**
     * 获取用户收货信息
     * @return
     */
    UserAddress queryUserAddressById(String addressId);
}
