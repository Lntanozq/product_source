package com.briup.framework.bean.extend;

import com.briup.framework.bean.FeedRecord;
import com.briup.framework.bean.ManagerBatch;
import com.briup.framework.bean.ManagerFeed;

/**
 * @author pgc
 * @Description: 饲料更换扩展类
 * @date 2022/4/2 9:38
 */
public class FeedRecordExtend extends FeedRecord {
    //批次信息
    private ManagerBatch mManagerBatch;
    //饲料信息
   private ManagerFeed mManagerFeed;
   //养殖人员
    private BaseAccountExtend mBaseAccount;

    public ManagerBatch getManagerBatch() {
        return mManagerBatch;
    }

    public void setManagerBatch(ManagerBatch managerBatch) {
        mManagerBatch = managerBatch;
    }

    public ManagerFeed getManagerFeed() {
        return mManagerFeed;
    }

    public void setManagerFeed(ManagerFeed managerFeed) {
        mManagerFeed = managerFeed;
    }

    public BaseAccountExtend getBaseAccount() {
        return mBaseAccount;
    }

    public void setBaseAccount(BaseAccountExtend baseAccount) {
        mBaseAccount = baseAccount;
    }
}
