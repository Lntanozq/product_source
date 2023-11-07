package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.ColdStorageRecord;
import com.briup.framework.bean.ManagerFreezer;

/**
 * @author pgc
 * @Description: 溯源冷库记录扩展类
 * @date 2022/4/8 10:21
 */
public class ColdStorageRecordExtend1 extends ColdStorageRecord {
    ManagerFreezer mManagerFreezer;
    BaseAccount mBaseAccount;

    public ManagerFreezer getManagerFreezer() {
        return mManagerFreezer;
    }

    public void setManagerFreezer(ManagerFreezer managerFreezer) {
        mManagerFreezer = managerFreezer;
    }

    public BaseAccount getBaseAccount() {
        return mBaseAccount;
    }

    public void setBaseAccount(BaseAccount baseAccount) {
        mBaseAccount = baseAccount;
    }
}
