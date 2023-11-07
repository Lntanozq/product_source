package com.briup.framework.service;

import com.briup.framework.bean.ManagerFenceHouse;
import com.briup.framework.bean.extend.ManagerFenceHouseExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;


/*
栏舍管理
 */
public interface IManagerFenceHouseService {
    //保存或者更新
    void saveOrUpdate(ManagerFenceHouse managerFenceHouse);
    //通过id删除
    void deleteById(String fhId);
    //多条件分页查询
    PageInfo<ManagerFenceHouseExtend> query(int page, int pageSize, String fhName);
    //不分页查询所有栏舍基本信息
    List<ManagerFenceHouse> queryAll();
    //根据id查询栏舍
    ManagerFenceHouseExtend selectById(String fhId);
}
