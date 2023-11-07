package com.briup.framework.mapper;

import com.briup.framework.bean.GroupUser;

public interface GroupUserMapper {
    int deleteByPrimaryKey(Integer guId);

    int insert(GroupUser record);

    int insertSelective(GroupUser record);

    GroupUser selectByPrimaryKey(Integer guId);

    int updateByPrimaryKeySelective(GroupUser record);

    int updateByPrimaryKey(GroupUser record);
}