package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.InoculationRecord;
import com.briup.framework.bean.ManagerVaccines;

/**
 * @author shaoyb
 * @program: product_source
 * @description 接种记录扩展类（额外包含 接种疫苗  接种人员信息）
 * @create 2022/4/1 10:51
 **/
public class InoculationRecordExtend extends InoculationRecord {
    //疫苗
    private ManagerVaccines vaccines;
    //接种人员
    private BaseAccountExtend inoculationAccount;

    public ManagerVaccines getVaccines() {
        return vaccines;
    }

    public void setVaccines(ManagerVaccines vaccines) {
        this.vaccines = vaccines;
    }

    public BaseAccountExtend getInoculationAccount() {
        return inoculationAccount;
    }

    public void setInoculationAccount(BaseAccountExtend inoculationAccount) {
        this.inoculationAccount = inoculationAccount;
    }
}
