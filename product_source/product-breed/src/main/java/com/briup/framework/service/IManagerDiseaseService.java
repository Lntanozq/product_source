package com.briup.framework.service;

import com.briup.framework.bean.ManagerDisease;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
    病症管理
 */
public interface IManagerDiseaseService {
    //保存或者更新
    void saveOrUpdate(ManagerDisease managerDisease);
    //多条件分页查询 (搜索条件：名称、类型)
    PageInfo<ManagerDisease> query(Integer page, Integer pageSize, String dName, String dType);
    //不分页查询所有疫苗基本信息
    List<ManagerDisease> queryAll();
    //删除指定病症
    void deleteById(Integer dId);

    //批量删除
    void deleteAllByIds(List<Integer> dIds);

    //根据编号查找
    ManagerDisease selectById(Integer diseaseId);
}
