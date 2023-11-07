package com.briup.framework.mapper;

import com.briup.framework.bean.ManagerCustomer;

public interface ManagerCustomerMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(ManagerCustomer record);

    int insertSelective(ManagerCustomer record);

    ManagerCustomer selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(ManagerCustomer record);

    int updateByPrimaryKey(ManagerCustomer record);
}