package com.briup.framework.service;

import com.briup.framework.bean.ManagerHurdles;
import com.briup.framework.bean.extend.ManagerHurdlesExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
栏圈管理
 */
public interface IManagerHurdlesService {
    //保存或者更新
    void saveOrUpdate(ManagerHurdles managerHurdles);
    //通过id删除
    void deleteById(String hId);
    //多条件分页查询
    PageInfo<ManagerHurdlesExtend> query(int page, int pageSize, String hName, String hMax,String fhName,String hEnable);
    //根据id查询栏圈
    ManagerHurdlesExtend selectById(String hId);
    //查询所有容量，重复的去重
    List<Integer> queryAllMax();
    //增加动物时栏圈以存量每次加一
    void updateByIdSaved(String hId);
    //查询所有栏圈
    List<ManagerHurdlesExtend> selectAllHurdles();
}
