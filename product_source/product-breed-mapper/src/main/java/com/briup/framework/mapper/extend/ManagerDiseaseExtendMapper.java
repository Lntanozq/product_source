package com.briup.framework.mapper.extend;

import com.briup.framework.bean.ManagerDisease;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerDiseaseExtendMapper {
    //不分页查询所有疫苗基本信息
    List<ManagerDisease> queryAll();

    //按照条件查询
    List<ManagerDisease> query( @Param("dName") String dName, @Param("dType") String dType);

    //批量删除
    void deleteAllByIds(@Param("dIds") List<Integer> dIds);

    //根据id查询
    ManagerDisease selectById(@Param("diseaseId")Integer diseaseId);
}
