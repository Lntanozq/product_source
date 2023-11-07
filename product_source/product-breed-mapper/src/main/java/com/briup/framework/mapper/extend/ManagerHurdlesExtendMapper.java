package com.briup.framework.mapper.extend;


import com.briup.framework.bean.extend.ManagerHurdlesExtend;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerHurdlesExtendMapper {
    List<ManagerHurdlesExtend> query(@Param("hName")String hName, @Param("hMax")String hMax,@Param("fhName")String fhName,@Param("hEnable")String hEnable);
    //逻辑删除栏舍
    void updateById(String hId);
    //查询所有容量，重复的去重
    List<Integer> queryAllMax();
    //获取栏圈总量
    int selectByIdMax(String hId);
    //获取栏圈以存量
    int selectByIdSaved(String hId);
    //增加动物时栏圈以存量每次加一
    void updateByIdSaved(String hId);
    //获取所有栏圈
    List<ManagerHurdlesExtend> selectAllHurdles();
}