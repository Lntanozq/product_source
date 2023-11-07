package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.base.bean.ext.BaseAccountExt;
import com.briup.framework.bean.ManagerCustomer;

/**
 * @program: product_source
 * @description: 客户扩展类
 * @author: nirui
 * @create: 2022-03-29 10:00
 */

public class ManagerCustomerExtend extends ManagerCustomer {
    //销售人员
    private BaseAccountExt account;

    public BaseAccountExt getAccount() {
        return account;
    }

    public void setAccount(BaseAccountExt account) {
        this.account = account;
    }
}
