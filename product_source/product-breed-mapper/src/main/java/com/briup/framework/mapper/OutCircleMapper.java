package com.briup.framework.mapper;

import com.briup.framework.bean.OutCircle;

public interface OutCircleMapper {
    int deleteByPrimaryKey(Integer ocId);

    int insert(OutCircle record);

    int insertSelective(OutCircle record);

    OutCircle selectByPrimaryKey(Integer ocId);

    int updateByPrimaryKeySelective(OutCircle record);

    int updateByPrimaryKey(OutCircle record);
}