package com.briup.framework.service;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.DiseaseRecord;
import com.briup.framework.bean.ManagerDisease;
import com.briup.framework.bean.extend.DiseaseRecordExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 病症记录service层接口
 */
public interface IDiseaseRecordService {

    List<BaseAccount> queryAllMedicalAccount();

    List<ManagerDisease> queryAllDisease();

    void saveOrUpdate(DiseaseRecord record);

    //更新动物健康状态
    void updateAnimalHealthy(String animalId,String status);

    //根据记录id 删除病症记录
    void deleteRecodeById(Integer drId);

    //根据记录ids 删除所有病症记录
    void deleteAllRecodeByIds(List<Integer> drIds);

    //获取所有 记录信息(含病症信息 医护人员信息)
    List<DiseaseRecordExtend> queryAllRecord();

    //分页查询
    PageInfo<DiseaseRecordExtend> query(Integer page, Integer pageSize, String drAnimalId, String drStatus, Integer drDId, String drUdockerId, String startTime, String endTime);
}
