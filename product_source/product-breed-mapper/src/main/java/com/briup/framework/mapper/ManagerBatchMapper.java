package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerBatch;

public interface ManagerBatchMapper {
    int deleteByPrimaryKey(String bSerialId);

    int insert(ManagerBatch record);

    int insertSelective(ManagerBatch record);

    ManagerBatch selectByPrimaryKey(String bSerialId);

    int updateByPrimaryKeySelective(ManagerBatch record);

    int updateByPrimaryKey(ManagerBatch record);
}