package com.briup.framework.service;

import com.briup.framework.bean.extend.AnimalRecord;
import com.briup.framework.bean.extend.ManagerBatchAnimalExtend;
import com.briup.framework.bean.extend.SlaughterRegistrationExtend;

import java.util.List;

/**
 * @author pgc
 * @Description: 屠宰登记业务层
 * @date 2022/3/31 9:45
 */
public interface ISlaughterRegistrationService {
    //根据动物过程状态获取对应批次以及动物信息
    List<ManagerBatchAnimalExtend> query(String aStatus);
    //插入和更新
    void saveOrUpdate(AnimalRecord animalRecord);

    //根据动物id查询 其屠宰登记信息(含屠宰组)
    List<SlaughterRegistrationExtend> selectSlaughterRecordByAnimalId(String animalId);
}
