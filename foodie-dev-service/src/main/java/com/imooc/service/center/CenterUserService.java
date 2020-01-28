package com.imooc.service.center;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;

/**
 * @Classname CenterUserService
 * @Description TODO
 * @Date 2020/1/28 20:31
 * @Created by 于明灏
 */
public interface CenterUserService {

    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    Users queryUsersInfoById(String userId);

    /**
     * 修改用户信息
     * @param centerUserBO
     * @param userId
     */
    Users updateUserInfo(CenterUserBO centerUserBO, String userId);
}
