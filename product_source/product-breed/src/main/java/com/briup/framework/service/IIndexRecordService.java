package com.briup.framework.service;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.IndexRecord;
import com.briup.framework.bean.extend.IndexRecordExtend;
import com.briup.framework.bean.extend.ManagerFenceHouseExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
指标记录
 */
public interface IIndexRecordService {
    //插入或更新
    void saveOrUpdate(IndexRecord indexRecord);
    //获取所有检测人员信息
    List<BaseAccount> queryTesting();
    //根据id删除
    void deleteById(int irdId);
    //多条件分页查询
    PageInfo<IndexRecordExtend> query(int page, int pageSize,
                                      String animalNumber,String healthy,
                                      String startTime,String endTime,
                                      String startHeartRate,String endHeartRate,
                                      String startBreathing,String endBreathing,
                                      String startWeight,String endWeight,
                                      String ubreedId);
}
