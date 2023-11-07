package com.briup.framework.service;

import com.briup.framework.base.bean.basic.BaseAccount;

import java.util.List;

public interface IUserService {
    //根据角色id查询对应不分页用户
      List<BaseAccount> query(String roleId);
}
