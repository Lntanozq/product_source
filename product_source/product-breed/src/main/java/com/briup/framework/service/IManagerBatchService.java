package com.briup.framework.service;

import com.briup.framework.bean.ManagerBatch;
import com.briup.framework.bean.extend.ManagerBatchExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 批次管理
 */
public interface IManagerBatchService {
    //保存或更新批次信息
    void saveOrUpdate(ManagerBatch batch);
    //通过id删除
    void deleteById(String serialId);
    //根据id批量删除批次信息
    void deleteInBatch(List<String> serialIds);
    //根据编号、时间段(开始时间和结束时间)、是否检疫、是否合格条件查询批次信息
    PageInfo<ManagerBatchExtend> query(Integer pageNum,Integer pageSize,String serialId,String startTime,String endTime,String bQuarantine,String bQualified);
    //查询所有批次信息
    List<ManagerBatch> queryAll();
    //根据批次编号查询动物信息
    ManagerBatchExtend selectById(String serialId);
}
