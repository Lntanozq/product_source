package com.briup.framework.mapper.extend;

import com.briup.framework.base.bean.basic.BaseAccount;

import java.util.List;

public interface UserExtendMapper {
    List<BaseAccount> query(String roleId);
}
