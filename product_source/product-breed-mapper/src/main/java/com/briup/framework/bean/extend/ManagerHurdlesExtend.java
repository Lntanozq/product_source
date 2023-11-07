package com.briup.framework.bean.extend;

import com.briup.framework.bean.ManagerFenceHouse;
import com.briup.framework.bean.ManagerHurdles;

/**
 * @program: product_source
 * @description: 栏圈继承类
 * @author: pgc
 * @create:2022-03-23
 **/
public class ManagerHurdlesExtend extends ManagerHurdles {
    private ManagerFenceHouse mManagerFenceHouse;

    public ManagerFenceHouse getManagerFenceHouse() {
        return mManagerFenceHouse;
    }

    public void setManagerFenceHouse(ManagerFenceHouse managerFenceHouse) {
        mManagerFenceHouse = managerFenceHouse;
    }
}
