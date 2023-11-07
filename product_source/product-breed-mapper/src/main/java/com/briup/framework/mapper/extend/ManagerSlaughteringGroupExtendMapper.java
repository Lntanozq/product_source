package com.briup.framework.mapper.extend;

import com.briup.framework.bean.extend.ManagerSlaughteringGroupExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 屠宰组扩展操作 dao层接口
 */
public interface ManagerSlaughteringGroupExtendMapper {
    //批量删除 屠宰组
    void deleteAllByIds(@Param("sgIds") List<Integer> sgIds);

    //查询所有 屠宰组(含员工)
    List<ManagerSlaughteringGroupExtend> selectAllSGroupWithAccounts();

    List<ManagerSlaughteringGroupExtend> query(@Param("sgName") String sgName, @Param("sgNum") Integer sgNum);

    ManagerSlaughteringGroupExtend selectSGroupWithAccountsById(@Param("sgId") Integer sgId);
}
