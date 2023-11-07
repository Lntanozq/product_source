package com.briup.framework.mapper.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.ManagerFreezer;
import com.briup.framework.bean.extend.ManagerFreezerExtend;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerFreezerExtendMapper {
    //获取所有负责人的信息
    List<BaseAccount> queryFreezer();
    //多条件分页查询
    List<ManagerFreezerExtend> query(@Param("fzName")String fzName,@Param("fzNum")String fzNum,@Param("freezerId")String freezerId);
    //逻辑删除冷库
    void updateById(int fzId);
    //不分页获取所有的冷库信息
    List<ManagerFreezer> queryAll();
    //根据冷库id获取以存量
    int selectByIdQuantity(int fzId);
    //根据id更新以存量
    void updateByIdQuantity(@Param("fzQuantity")int quantity,@Param("fzId")int fzId);
}
