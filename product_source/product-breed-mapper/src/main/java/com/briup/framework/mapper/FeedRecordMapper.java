package com.briup.framework.mapper;

import com.briup.framework.bean.FeedRecord;

public interface FeedRecordMapper {
    int deleteByPrimaryKey(Integer frId);

    int insert(FeedRecord record);

    int insertSelective(FeedRecord record);

    FeedRecord selectByPrimaryKey(Integer frId);

    int updateByPrimaryKeySelective(FeedRecord record);

    int updateByPrimaryKey(FeedRecord record);
}