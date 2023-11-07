package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerSlaughteringGroup;

public interface ManagerSlaughteringGroupMapper {
    int deleteByPrimaryKey(Integer sgId);

    int insert(ManagerSlaughteringGroup record);

    int insertSelective(ManagerSlaughteringGroup record);

    ManagerSlaughteringGroup selectByPrimaryKey(Integer sgId);

    int updateByPrimaryKeySelective(ManagerSlaughteringGroup record);

    int updateByPrimaryKey(ManagerSlaughteringGroup record);
}