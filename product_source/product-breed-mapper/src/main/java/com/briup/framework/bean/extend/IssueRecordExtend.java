package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.ext.BaseAccountExt;
import com.briup.framework.bean.IssueRecord;
import com.briup.framework.bean.ManagerCustomer;

/**
 * @program: product_source
 * @description: 出单记录扩展类
 * @author: nirui
 * @create: 2022-03-31 14:41
 */

public class IssueRecordExtend extends IssueRecord {
    //销售人员
    private BaseAccountExt account;
    //客户
    private ManagerCustomer managerCustomer;

    public BaseAccountExt getAccount() {
        return account;
    }

    public void setAccount(BaseAccountExt account) {
        this.account = account;
    }

    public ManagerCustomer getManagerCustomer() {
        return managerCustomer;
    }

    public void setManagerCustomer(ManagerCustomer managerCustomer) {
        this.managerCustomer = managerCustomer;
    }
}
