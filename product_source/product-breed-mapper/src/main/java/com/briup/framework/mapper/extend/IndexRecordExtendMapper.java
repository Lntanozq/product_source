package com.briup.framework.mapper.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.extend.IndexRecordExtend;
import com.github.pagehelper.PageInfo;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IndexRecordExtendMapper {
    List<BaseAccount> queryTesting();
    //多条件分页查询
    List<IndexRecordExtend> query(@Param("animalNumber")String animalNumber,
                                  @Param("healthy")String healthy,
                                  @Param("startTime")Date startTime,
                                  @Param("endTime") Date endTime,
                                  @Param("startHeartRate") String startHeartRate,
                                  @Param("endHeartRate")String endHeartRate,
                                  @Param("startBreathing") String startBreathing,
                                  @Param("endBreathing")String endBreathing,
                                  @Param("startWeight")String startWeight,
                                  @Param("endWeight") String endWeight,
                                  @Param("ubreedId") String ubreedId);

}
