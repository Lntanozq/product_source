package com.briup.framework.mapper.extend;

import com.briup.framework.bean.extend.SituationReportingExtend;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author pgc
 * @Description: 情况上报mapper
 * @date 2022/3/31 17:20
 */
public interface SituationReportingExtendMapper {
    //情况汇报状态修改
    void updateStatus(@Param("srId")int srId, @Param("srStatus")String srStatus, @Param("srUdockerId")String srUdockerId);
    //分页查询上报情况以及上报人员以及处理人员基本信息
    List<SituationReportingExtend> query(@Param("srAnimalId")String srAnimalId, @Param("srStatus")String srStatus,
                                         @Param("startTime") Date startTime, @Param("endTime")Date endTime,
                                         @Param("srUbreedId")String srUbreedId, @Param("srUdockerId")String srUdockerId);
}
