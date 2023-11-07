package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerHurdles;

public interface ManagerHurdlesMapper {
    int deleteByPrimaryKey(String hId);

    int insert(ManagerHurdles record);

    int insertSelective(ManagerHurdles record);

    ManagerHurdles selectByPrimaryKey(String hId);

    int updateByPrimaryKeySelective(ManagerHurdles record);

    int updateByPrimaryKey(ManagerHurdles record);
}