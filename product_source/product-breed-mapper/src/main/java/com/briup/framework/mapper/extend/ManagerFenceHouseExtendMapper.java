package com.briup.framework.mapper.extend;

import com.briup.framework.bean.ManagerFenceHouse;
import com.briup.framework.bean.extend.ManagerFenceHouseExtend;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerFenceHouseExtendMapper {
    //多条件分页查询
    List<ManagerFenceHouseExtend> query(@Param("fhName")String fhName);
    //不分页查询所有栏舍基本信息
    List<ManagerFenceHouse> queryAll();
    //根据id查询栏舍以及对应栏圈信息
    ManagerFenceHouseExtend selectById(String fhId);
    //逻辑删除栏舍
    void updateById(String fhId);

}
