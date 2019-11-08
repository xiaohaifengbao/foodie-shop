package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 注册用户信息
     * @param userBO
     * @return
     */
    Users createUser(UserBO userBO);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    Users loginUser(String username, String password);


}
