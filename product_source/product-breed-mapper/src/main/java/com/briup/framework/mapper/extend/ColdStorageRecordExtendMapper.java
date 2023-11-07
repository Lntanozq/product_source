package com.briup.framework.mapper.extend;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.extend.ColdStorageRecordExtend;
import com.briup.framework.bean.extend.ColdStorageRecordExtend1;

import java.util.List;

/**
 * @author pgc
 * @Description:
 * @date 2022/4/1 10:52
 */
public interface ColdStorageRecordExtendMapper {
    //获取冷库记录中所有去重的冷库信息
    List<ColdStorageRecordExtend> queryFreezer();
    List<ManagerAnimal> queryAnimal(int freezerId);
    //溯源需要根据动物编号获取冷库以及出库人员信息
    ColdStorageRecordExtend1 queryByAnimalId(String animalId);
}
