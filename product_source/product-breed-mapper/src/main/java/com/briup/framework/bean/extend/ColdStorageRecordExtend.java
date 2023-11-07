package com.briup.framework.bean.extend;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.ManagerFreezer;

import java.util.List;

/**
 * @author pgc
 * @Description: 获取冷库以及冷库中动物信息
 * @date 2022/4/1 10:48
 */
public class ColdStorageRecordExtend extends ManagerFreezer {
    List<ManagerAnimal> mManagerAnimals;

    public List<ManagerAnimal> getManagerAnimals() {
        return mManagerAnimals;
    }

    public void setManagerAnimals(List<ManagerAnimal> managerAnimals) {
        mManagerAnimals = managerAnimals;
    }
}
