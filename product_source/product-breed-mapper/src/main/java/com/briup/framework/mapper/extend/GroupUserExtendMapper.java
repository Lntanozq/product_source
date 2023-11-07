package com.briup.framework.mapper.extend;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
    屠宰组人员构成表操作
 */
public interface GroupUserExtendMapper {

    //根据屠宰组id 删除该组所有员工[物理删除]
    void deleteBySGroupId(@Param("sgId") Integer sgId);

    //根据屠宰组id集合 删除该组中所有员工[物理删除]
    void deleteAllBySGroupIds(@Param("sgIds")List<Integer> sgIds);
}
