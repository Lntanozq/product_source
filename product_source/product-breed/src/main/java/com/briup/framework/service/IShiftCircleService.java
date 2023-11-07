package com.briup.framework.service;

import com.briup.framework.bean.ShiftCircle;
import com.briup.framework.bean.extend.ShiftCircleExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 移圈记录管理
 */
public interface IShiftCircleService {
    //更新或保存移圈记录
    void saveOrUpdate(ShiftCircle shiftCircle);
    //批量删除移圈记录
    void deleteInBatch(List<Integer> ids);
    //根据id删除移圈记录
    void deleteById(Integer id);
    //查询所有移圈记录
    List<ShiftCircle> queryAll();
    //查询所有移圈人员
    List<ShiftCircleExtend> queryAllUser();
    //分页查询动物编号、移圈人员、时间段、移圈原因
    PageInfo<ShiftCircleExtend> query(Integer pageNum,Integer pageSize,String animalId,String ubeedId,String start,String end,String reason);

}
