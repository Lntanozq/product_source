package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.ext.BaseAccountExt;
import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.ManagerHurdles;
import com.briup.framework.bean.ShiftCircle;
import org.apache.catalina.Manager;

/**
 * @program: product_source
 * @description: 移圈记录扩展类
 * @author: nirui
 * @create: 2022-03-30 10:25
 */

public class ShiftCircleExtend extends ShiftCircle {
    //动物
    private ManagerAnimal animal;
    //移圈人员
    private BaseAccountExt account;
    //新栏圈
    private ManagerHurdlesExtend newManagerHurdlesExtend;
    //旧栏圈
    private ManagerHurdlesExtend oldManagerHurdlesExtend;

    public BaseAccountExt getAccount() {
        return account;
    }

    public void setAccount(BaseAccountExt account) {
        this.account = account;
    }

    public ManagerAnimal getAnimal() {
        return animal;
    }

    public void setAnimal(ManagerAnimal animal) {
        this.animal = animal;
    }

    public ManagerHurdlesExtend getNewManagerHurdlesExtend() {
        return newManagerHurdlesExtend;
    }

    public void setNewManagerHurdlesExtend(ManagerHurdlesExtend newManagerHurdlesExtend) {
        this.newManagerHurdlesExtend = newManagerHurdlesExtend;
    }

    public ManagerHurdlesExtend getOldManagerHurdlesExtend() {
        return oldManagerHurdlesExtend;
    }

    public void setOldManagerHurdlesExtend(ManagerHurdlesExtend oldManagerHurdlesExtend) {
        this.oldManagerHurdlesExtend = oldManagerHurdlesExtend;
    }
}
