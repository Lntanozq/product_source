package com.briup.framework.bean.extend;

import com.briup.framework.bean.ManagerBatch;

/**
 * @program: product_source
 * @description: 批次扩展类
 * @author: nirui
 * @create: 2022-03-28 10:09
 */

public class ManagerBatchExtend extends ManagerBatch {
    //动物数量
    private Integer count;
    //猪的状态
    private String astatus;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getAstatus() {
        return astatus;
    }

    public void setAstatus(String astatus) {
        this.astatus = astatus;
    }
}
