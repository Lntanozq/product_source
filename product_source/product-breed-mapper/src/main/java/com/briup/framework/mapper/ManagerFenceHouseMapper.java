package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerFenceHouse;

public interface ManagerFenceHouseMapper {
    int deleteByPrimaryKey(String fhId);

    int insert(ManagerFenceHouse record);

    int insertSelective(ManagerFenceHouse record);

    ManagerFenceHouse selectByPrimaryKey(String fhId);

    int updateByPrimaryKeySelective(ManagerFenceHouse record);

    int updateByPrimaryKey(ManagerFenceHouse record);
}