package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.ext.BaseAccountExt;
import com.briup.framework.bean.QuarantineRegistration;

/**
 * @program: product_source
 * @description: 检疫登记扩展类
 * @author: nirui
 * @create: 2022-03-31 09:57
 */

public class QuarantineRegistrationExtend extends QuarantineRegistration {
    //检疫人员
    private BaseAccountExt account;
    //检疫结果
    private String bQualified;
    //批次
    private ManagerBatchExtend managerBatchExtend;

    public BaseAccountExt getAccount() {
        return account;
    }

    public void setAccount(BaseAccountExt account) {
        this.account = account;
    }

    public String getbQualified() {
        return bQualified;
    }

    public void setbQualified(String bQualified) {
        this.bQualified = bQualified;
    }

    public ManagerBatchExtend getManagerBatchExtend() {
        return managerBatchExtend;
    }

    public void setManagerBatchExtend(ManagerBatchExtend managerBatchExtend) {
        this.managerBatchExtend = managerBatchExtend;
    }
}
