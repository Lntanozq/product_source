package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerFreezer;

public interface ManagerFreezerMapper {
    int deleteByPrimaryKey(Integer fzId);

    int insert(ManagerFreezer record);

    int insertSelective(ManagerFreezer record);

    ManagerFreezer selectByPrimaryKey(Integer fzId);

    int updateByPrimaryKeySelective(ManagerFreezer record);

    int updateByPrimaryKey(ManagerFreezer record);
}