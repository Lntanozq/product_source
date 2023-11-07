package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.IndexRecord;
import com.briup.framework.bean.ManagerAnimal;

/**
 * @program: product_source
 * @description: 指标记录扩展类
 * @author: pgc
 * @create:2022-03-29
 **/
public class IndexRecordExtend extends IndexRecord {
    private ManagerAnimal mManagerAnimal;
    private BaseAccountExtend mBaseAccount;

    public ManagerAnimal getManagerAnimal() {
        return mManagerAnimal;
    }

    public void setManagerAnimal(ManagerAnimal managerAnimal) {
        mManagerAnimal = managerAnimal;
    }

    public BaseAccount getBaseAccount() {
        return mBaseAccount;
    }

    public void setBaseAccount(BaseAccountExtend baseAccount) {
        mBaseAccount = baseAccount;
    }
}
