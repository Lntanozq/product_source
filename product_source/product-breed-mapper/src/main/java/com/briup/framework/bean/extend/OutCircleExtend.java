package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.OutCircle;

/**
 * @author pgc
 * @Description: 溯源针对生猪出栏记录扩展类
 * @date 2022/4/8 10:40
 */
public class OutCircleExtend extends OutCircle {
    BaseAccount mBaseAccount;

    public BaseAccount getBaseAccount() {
        return mBaseAccount;
    }

    public void setBaseAccount(BaseAccount baseAccount) {
        mBaseAccount = baseAccount;
    }
}
