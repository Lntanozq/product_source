package com.briup.framework.mapper.extend;

import com.briup.framework.bean.ManagerBatch;
import com.briup.framework.bean.extend.ManagerBatchExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 批次管理
 */
public interface ManagerBatchExtendMapper {
    //通过id删除批次信息
    void updateBySerialId(String serialId);
    //批量删除批次信息
    void deleteInBatch(@Param("ids") List<String> ids);
    //查询所有批次信息
    List<ManagerBatch> queryAll();
    //根据批次id查询批次信息以及动物数量
    ManagerBatchExtend selectById(String serialId);
    //条件检索
    List<ManagerBatchExtend> query(@Param("serialId") String serialId,
                                   @Param("start") String startTime, @Param("end") String endTime,
                                   @Param("quarantine") String bQuarantine,@Param("qualified") String bQualified);
}
