package com.briup.framework.service;

import com.briup.framework.bean.ManagerFenceHouse;
import com.briup.framework.bean.ManagerVaccines;
import com.briup.framework.bean.extend.ManagerFenceHouseExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
    疫苗管理
 */
public interface IManagerVaccinesService {
    //保存或者更新
    void saveOrUpdate(ManagerVaccines managerVaccines);

    //通过id删除
    void deleteById(String vId);
    //批量删除
    void deleteAllByIds(List<String> vIds);

    //多条件分页查询 (搜索条件：编号、名称、供应商、类型)
    PageInfo<ManagerVaccines> query(Integer page, Integer pageSize, String vId, String vName, String vSupplier, String vType);
    //不分页查询所有疫苗基本信息
    List<ManagerVaccines> queryAll();

    //根据id查询疫苗
    ManagerVaccines selectById(String vId);
}
