package com.briup.framework.service;

import com.briup.framework.bean.ManagerCustomer;
import com.briup.framework.bean.extend.ManagerCustomerExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 客户管理
 */
public interface IManagerCustomerService {
    //添加或更新客户信息
    void saveOrUpdate(ManagerCustomer managerCustomer);
    //批量删除客户信息
    void deleteInBatch(List<Integer> ids);
    //根据id删除客户信息
    void deleteById(Integer id);
    //查询所有客户信息
    List<ManagerCustomer> queryAll();
    //根据名称、公司名称、接待人员id、等级条件分页查询客户以及销售人员信息
    PageInfo<ManagerCustomerExtend> query(Integer pageNum,Integer pageSize,String company,String name,String usaleId,String level);
    //查询客户对应的所有接待人员的信息
    List<ManagerCustomerExtend> queryAllUser();
}
