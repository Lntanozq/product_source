package com.briup.framework.service;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.ManagerCustomer;
import com.briup.framework.bean.ManagerSlaughteringGroup;
import com.briup.framework.bean.extend.ManagerSlaughteringGroupExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 屠宰组管理
 */
public interface IManagerSlaughteringGroupService {
    //添加或更新屠宰组基本信息
    void saveOrUpdate(ManagerSlaughteringGroup slaughteringGroup);

    //更新屠宰组中 屠宰人员信息
    void updateSlaughteringGroupAccount(Integer sgId, String[] accountIds, Integer sgNum);

    //根据屠宰组id 删除指定屠宰组
    void deleteSlaughteringGroupBySgId(Integer sgId);

    //根据多个屠宰组id 批量删除屠宰组
    void deleteAllSlaughteringGroupBySgIds(List<Integer> sgIds);

    //查询所有屠宰组信息(包含 人员信息)
    List<ManagerSlaughteringGroupExtend> queryAll();

    //按条件查询 并分页显示
    PageInfo<ManagerSlaughteringGroupExtend> query(Integer page, Integer pageSize, String sgName, Integer sgNum);
    // 嵌套结果映射 会出bug
    // PageInfo<ManagerSlaughteringGroupExtend> query2(Integer page, Integer pageSize, String sgName, Integer sgNum);

    //根据屠宰组id 查询指定屠宰组信息（含员工）
    ManagerSlaughteringGroupExtend selectById(Integer sgId);

    //查询所有屠宰人员信息
    List<BaseAccount> queryAllSlaughteringAccount();
}
