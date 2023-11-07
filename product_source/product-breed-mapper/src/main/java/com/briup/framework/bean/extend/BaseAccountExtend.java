package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.base.bean.basic.BaseRole;

/**
 * @author pgc
 * @Description: 用户扩展类
 * @date 2022/3/30 10:55
 */
public class BaseAccountExtend extends BaseAccount {
    private BaseRole mBaseRole;

    public BaseRole getBaseRole() {
        return mBaseRole;
    }

    public void setBaseRole(BaseRole baseRole) {
        mBaseRole = baseRole;
    }
}
