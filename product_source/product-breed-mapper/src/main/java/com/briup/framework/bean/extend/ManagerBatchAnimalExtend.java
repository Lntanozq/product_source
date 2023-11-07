package com.briup.framework.bean.extend;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.ManagerBatch;

import java.util.List;

/**
 * @author pgc
 * @Description: 获取批次以及对应的动物信息
 * @date 2022/3/30 15:15
 */
public class ManagerBatchAnimalExtend extends ManagerBatch {
    List<ManagerAnimal> mManagerAnimals;

    public List<ManagerAnimal> getManagerAnimals() {
        return mManagerAnimals;
    }

    public void setManagerAnimals(List<ManagerAnimal> managerAnimals) {
        mManagerAnimals = managerAnimals;
    }
}
