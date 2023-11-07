package com.briup.framework.service;

import com.briup.framework.bean.extend.AnimalRecord;
import com.briup.framework.bean.extend.ColdStorageRecordExtend;
import com.briup.framework.bean.extend.ManagerBatchAnimalExtend;
import com.briup.framework.bean.extend.OutStorageRecord;

import java.util.List;

/**
 * @author pgc
 * @Description: 入库出库登记
 * @date 2022/3/31 16:02
 */
public interface IColdStorageRecordService {
    //根据动物过程状态获取对应批次以及动物信息
    List<ManagerBatchAnimalExtend> query(String aStatus);
    //插入和更新
    void saveOrUpdate(AnimalRecord animalRecord);
    //出库
    void updateStorageRecord(OutStorageRecord outStorageRecord);
    //获取冷库以及冷库中中所有动物信息
    List<ColdStorageRecordExtend> queryAll();
}
