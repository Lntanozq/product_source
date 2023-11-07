package com.briup.framework.mapper;

import com.briup.framework.bean.QuarantineRegistration;

public interface QuarantineRegistrationMapper {
    int deleteByPrimaryKey(Integer grId);

    int insert(QuarantineRegistration record);

    int insertSelective(QuarantineRegistration record);

    QuarantineRegistration selectByPrimaryKey(Integer grId);

    int updateByPrimaryKeySelective(QuarantineRegistration record);

    int updateByPrimaryKey(QuarantineRegistration record);
}