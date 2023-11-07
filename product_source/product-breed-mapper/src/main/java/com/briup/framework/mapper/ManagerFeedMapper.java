package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerFeed;

public interface ManagerFeedMapper {
    int deleteByPrimaryKey(Integer fId);

    int insert(ManagerFeed record);

    int insertSelective(ManagerFeed record);

    ManagerFeed selectByPrimaryKey(Integer fId);

    int updateByPrimaryKeySelective(ManagerFeed record);

    int updateByPrimaryKey(ManagerFeed record);
}