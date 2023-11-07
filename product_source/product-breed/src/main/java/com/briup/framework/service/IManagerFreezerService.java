package com.briup.framework.service;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.ManagerFreezer;
import com.briup.framework.bean.extend.ManagerFenceHouseExtend;
import com.briup.framework.bean.extend.ManagerFreezerExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
冷库管理业务层
 */
public interface IManagerFreezerService {
    //插入或更新
    void saveOrUpdate(ManagerFreezer managerFreezer);
    //获取所有负责人的信息
    List<BaseAccount> queryFreezer();
    //多条件分页查询
    PageInfo<ManagerFreezerExtend> query(int page, int pageSize, String fzName,String fzNum,String freezerId);
    //不分页获取所有的冷库信息
    List<ManagerFreezer> queryAll();
    //通过id删除
    void deleteById(int fzId);
}
