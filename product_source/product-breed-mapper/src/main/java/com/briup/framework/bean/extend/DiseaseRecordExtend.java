package com.briup.framework.bean.extend;

import com.briup.framework.bean.DiseaseRecord;
import com.briup.framework.bean.ManagerDisease;

/**
 * @author shaoyb
 * @program: product_source
 * @description 病症记录扩展类
 * @create 2022/4/8 12:50
 **/
public class DiseaseRecordExtend extends DiseaseRecord {
    //病症信息
    private ManagerDisease disease;
    //医护人员信息
    private BaseAccountExtend account;

    public ManagerDisease getDisease() {
        return disease;
    }

    public void setDisease(ManagerDisease disease) {
        this.disease = disease;
    }

    public BaseAccountExtend getAccount() {
        return account;
    }

    public void setAccount(BaseAccountExtend account) {
        this.account = account;
    }
}
