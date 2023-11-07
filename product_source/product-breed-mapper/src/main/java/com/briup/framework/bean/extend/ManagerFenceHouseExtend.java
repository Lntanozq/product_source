package com.briup.framework.bean.extend;

import com.briup.framework.bean.ManagerFenceHouse;
import com.briup.framework.bean.ManagerHurdles;

import java.util.List;

/**
 * @program: product_source
 * @description: 栏舍扩展类
 * @author: pgc
 * @create:2022-03-24
 **/
public class ManagerFenceHouseExtend extends ManagerFenceHouse {
    private int count;
    private List<ManagerHurdles> mhs;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ManagerHurdles> getMhs() {
        return mhs;
    }

    public void setMhs(List<ManagerHurdles> mhs) {
        this.mhs = mhs;
    }
}
