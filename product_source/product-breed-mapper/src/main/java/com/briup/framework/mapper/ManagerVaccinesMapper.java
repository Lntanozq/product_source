package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerVaccines;

public interface ManagerVaccinesMapper {
    int deleteByPrimaryKey(String vVaccinesId);

    int insert(ManagerVaccines record);

    int insertSelective(ManagerVaccines record);

    ManagerVaccines selectByPrimaryKey(String vVaccinesId);

    int updateByPrimaryKeySelective(ManagerVaccines record);

    int updateByPrimaryKey(ManagerVaccines record);
}