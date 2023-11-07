package com.briup.framework.mapper.extend;

import com.briup.framework.bean.extend.DiseaseRecordExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
    病症记录dao层扩展接口
 */
public interface DiseaseRecordExtendMapper {
    //根据 病症记录ids 获取所有相关的动物
    List<String> queryAllAnimalIdsByRecodeIds(@Param("drIds") List<Integer> drIds);

    void deleteAllByRecodeIds(@Param("drIds") List<Integer> drIds);

    //获取病症记录(含病症信息 医护人员信息)
    List<DiseaseRecordExtend> queryAllRecords();

    List<DiseaseRecordExtend> query(@Param("drAnimalId")String drAnimalId, @Param("drStatus")String drStatus, @Param("drDId")Integer drDId,
                                    @Param("drUdockerId")String drUdockerId, @Param("startTime")String startTime, @Param("endTime")String endTime);
}
