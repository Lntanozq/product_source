package com.briup.framework.mapper;

import com.briup.framework.bean.IndexRecord;

public interface IndexRecordMapper {
    int deleteByPrimaryKey(Integer irdId);

    int insert(IndexRecord record);

    int insertSelective(IndexRecord record);

    IndexRecord selectByPrimaryKey(Integer irdId);

    int updateByPrimaryKeySelective(IndexRecord record);

    int updateByPrimaryKey(IndexRecord record);
}