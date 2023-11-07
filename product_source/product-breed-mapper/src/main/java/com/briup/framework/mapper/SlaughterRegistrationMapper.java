package com.briup.framework.mapper;

import com.briup.framework.bean.SlaughterRegistration;

public interface SlaughterRegistrationMapper {
    int deleteByPrimaryKey(Integer srnId);

    int insert(SlaughterRegistration record);

    int insertSelective(SlaughterRegistration record);

    SlaughterRegistration selectByPrimaryKey(Integer srnId);

    int updateByPrimaryKeySelective(SlaughterRegistration record);

    int updateByPrimaryKey(SlaughterRegistration record);
}