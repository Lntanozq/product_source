package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.ManagerFreezer;

/**
 * @program: product_source
 * @description: 冷库扩展类
 * @author: pgc
 * @create:2022-03-29
 **/
public class ManagerFreezerExtend extends ManagerFreezer {
    BaseAccountExtend mBaseAccount;

    public BaseAccountExtend getBaseAccount() {
        return mBaseAccount;
    }

    public void setBaseAccount(BaseAccountExtend baseAccount) {
        mBaseAccount = baseAccount;
    }
}
