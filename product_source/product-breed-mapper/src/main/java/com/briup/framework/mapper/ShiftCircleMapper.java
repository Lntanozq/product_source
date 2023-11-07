package com.briup.framework.mapper;

import com.briup.framework.bean.ShiftCircle;

public interface ShiftCircleMapper {
    int deleteByPrimaryKey(Integer scId);

    int insert(ShiftCircle record);

    int insertSelective(ShiftCircle record);

    ShiftCircle selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(ShiftCircle record);

    int updateByPrimaryKey(ShiftCircle record);
}