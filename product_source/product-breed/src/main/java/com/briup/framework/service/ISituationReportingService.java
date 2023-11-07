package com.briup.framework.service;

import com.briup.framework.bean.SituationReporting;
import com.briup.framework.bean.extend.SituationReportingExtend;
import com.github.pagehelper.PageInfo;

/**
 * @author pgc
 * @Description: 特殊情况上报业务层
 * @date 2022/3/31 17:05
 */
public interface ISituationReportingService {
    //插入或修改 针对情况上报信息
    void saveOrUpdate(SituationReporting situationReporting);
    //根据id删除信息
    void deleteById(int srId);
    //情况汇报状态修改
    void updateStatus(int srId,String srAnimalId,String srStatus,String srUdockerId);
    //多条件分页查询
    PageInfo<SituationReportingExtend> query(int page, int pageSize,
                                             String srAnimalId,String srStatus,
                                             String startTime,String endTime,
                                             String srUbreedId,String srUdockerId);
}
