package com.briup.framework.mapper.extend;

import com.briup.framework.bean.SlaughterRegistration;
import com.briup.framework.bean.extend.SlaughterRegistrationExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  屠宰登记记录 查询 dao接口
 */
public interface SlaughterRegistrationExtendMapper {

    //根据批次id，查询屠宰记录（基本信息）
    SlaughterRegistration selectRecordByBatchId(@Param("batchId") String batchId);

    //根据批次id,查询屠宰记录(含基本信息，屠宰组信息，屠宰组人员信息)
    List<SlaughterRegistrationExtend> selectRecordWithSGroupByBatchId(@Param("batchId") String batchId);

}
