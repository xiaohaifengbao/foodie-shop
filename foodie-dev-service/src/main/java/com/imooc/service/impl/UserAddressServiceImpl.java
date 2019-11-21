package com.imooc.service.impl;

import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.UserAddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Classname UserAddressServiceImpl
 * @Description TODO
 * @Date 2019/11/21 10:54
 * @Created by 于明灏
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;

    /*@Resource
    private Sid sid;*/

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> selectAddress(String userId) {

        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);

        return userAddressMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserAddress saveUserAddress(AddressBO addressBO) {

        UserAddress targetModel = new UserAddress();
        Sid sid = new Sid();
        // 赋值
        targetModel.setId(sid.nextShort());
        targetModel.setCreatedTime(new Date());
        targetModel.setUpdatedTime(new Date());
        BeanUtils.copyProperties(addressBO, targetModel);

        // 默认地址属性
        Integer isDefault = 1;
        List<UserAddress> userAddressList = this.queryAll(addressBO.getUserId());
        if(userAddressList != null && userAddressList.size() > 0) {
            targetModel.setIsDefault(isDefault);
        }
        // 入库
        userAddressMapper.insertSelective(targetModel);

        return targetModel;
    }

    @Override
    public UserAddress updateUserAddress(AddressBO addressBO) {

        UserAddress targetModel = new UserAddress();
        // 赋值
        targetModel.setUpdatedTime(new Date());
        BeanUtils.copyProperties(addressBO, targetModel);

        // 修改数据入库
        userAddressMapper.updateByPrimaryKeySelective(targetModel);
        return targetModel;
    }

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    public List<UserAddress> queryAll(String userId) {
        // 判断是否为默认地址
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);

        return userAddressMapper.selectByExample(example);
    }
}
