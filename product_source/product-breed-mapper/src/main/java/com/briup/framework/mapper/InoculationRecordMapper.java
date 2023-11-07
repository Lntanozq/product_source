package com.briup.framework.mapper;

import com.briup.framework.bean.InoculationRecord;

public interface InoculationRecordMapper {
    int deleteByPrimaryKey(Integer irId);

    int insert(InoculationRecord record);

    int insertSelective(InoculationRecord record);

    InoculationRecord selectByPrimaryKey(Integer irId);

    int updateByPrimaryKeySelective(InoculationRecord record);

    int updateByPrimaryKey(InoculationRecord record);
}