package com.briup.framework.mapper.extend;

import com.briup.framework.bean.ManagerFenceHouse;
import com.briup.framework.bean.ManagerVaccines;
import com.briup.framework.mapper.ManagerVaccinesMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerVaccinesExtendMapper {
    //批量删除：update set v_delete=1
    void deleteAllByIds(@Param("vIds") List<String> vIds);

    //不分页查询所有疫苗基本信息
    List<ManagerVaccines> queryAll();

    //分页查询疫苗基本信息
    List<ManagerVaccines> query(@Param("vId")String vId, @Param("vName")String vName, @Param("vSupplier")String vSupplier, @Param("vType")String vType);

    //根据vid查询疫苗基本信息
    ManagerVaccines selectById(@Param("vId") String vId);
}
