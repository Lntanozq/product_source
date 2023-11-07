package com.briup.framework.bean.extend;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.ManagerBatch;
import com.briup.framework.bean.ManagerHurdles;

/**
 * @program: product_source
 * @description: 动物扩展类
 * @author: pgc
 * @create:2022-03-28
 **/
public class ManagerAnimalExtend extends ManagerAnimal {
    private ManagerBatch mManagerBatch;
    private ManagerHurdlesExtend mManagerHurdles;

    public ManagerBatch getManagerBatch() {
        return mManagerBatch;
    }

    public void setManagerBatch(ManagerBatch managerBatch) {
        mManagerBatch = managerBatch;
    }

    public ManagerHurdles getManagerHurdles() {
        return mManagerHurdles;
    }

    public void setManagerHurdles(ManagerHurdlesExtend managerHurdles) {
        mManagerHurdles = managerHurdles;
    }
}
