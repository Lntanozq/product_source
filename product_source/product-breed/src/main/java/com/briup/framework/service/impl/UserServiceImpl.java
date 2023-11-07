package com.briup.framework.service.impl;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.mapper.extend.UserExtendMapper;
import com.briup.framework.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: product_source
 * @description: 用户角色查找业务层
 * @author: pgc
 * @create:2022-03-29
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserExtendMapper userExtendMapper;
    @Override
    public List<BaseAccount> query(String roleId) {
        return userExtendMapper.query(roleId);
    }
}
