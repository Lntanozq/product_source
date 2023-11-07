package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerDisease;

public interface ManagerDiseaseMapper {
    int deleteByPrimaryKey(Integer dId);

    int insert(ManagerDisease record);

    int insertSelective(ManagerDisease record);

    ManagerDisease selectByPrimaryKey(Integer dId);

    int updateByPrimaryKeySelective(ManagerDisease record);

    int updateByPrimaryKey(ManagerDisease record);
}