package com.briup.framework.mapper;

import com.briup.framework.bean.ColdStorageRecord;

public interface ColdStorageRecordMapper {
    int deleteByPrimaryKey(Integer csrId);

    int insert(ColdStorageRecord record);

    int insertSelective(ColdStorageRecord record);

    ColdStorageRecord selectByPrimaryKey(Integer csrId);

    int updateByPrimaryKeySelective(ColdStorageRecord record);

    int updateByPrimaryKey(ColdStorageRecord record);
}