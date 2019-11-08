package com.imooc.service.impl;

import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import lombok.experimental.var;
import org.n3r.idworker.IdWorker;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UsersMapper usersMapper;

    public static final String FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        Example usersExample = new Example(Users.class);
        Example.Criteria criteria = usersExample.createCriteria();

        criteria.andEqualTo("username", username);

        Users returnModel = usersMapper.selectOneByExample(usersExample);

        return returnModel == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {

        Users returnModel = new Users();
        // IdWorker唯一ID
        Sid sid = new Sid();
        returnModel.setId(sid.nextShort());
        returnModel.setUsername(userBO.getUsername());
        returnModel.setPassword(userBO.getPassword());
        returnModel.setNickname(userBO.getUsername());
        returnModel.setFace(FACE);
        returnModel.setSex(Sex.secret.type);

        usersMapper.insertSelective(returnModel);

        return returnModel;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users loginUser(String username, String password) {

        Example usersExample = new Example(Users.class);
        Example.Criteria criteria = usersExample.createCriteria();

        criteria.andEqualTo("username", username)
                .andEqualTo("password", password);

        Users returnModel = usersMapper.selectOneByExample(usersExample);

        return returnModel;
    }




}
