package com.briup.framework.mapper.extend;

import com.briup.framework.bean.ManagerCustomer;
import com.briup.framework.bean.extend.ManagerCustomerExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerCustomerExtendMapper {
    //批量删除客户信息
    void deleteInBatch(@Param("ids") List<Integer> ids);
    //根据id删除客户信息
    void deleteById(Integer id);
    //查询所有客户信息
    List<ManagerCustomer> queryAll();
    //根据名称、公司名称、接待人员id、等级查询客户以及销售信息
    List<ManagerCustomerExtend> query(@Param("company") String company,@Param("name") String name,
                                      @Param("usaleId") String usaleId,@Param("level") String level);
    //查询客户对应所有接待人员的信息
    List<ManagerCustomerExtend> queryAllUser();
}
