package com.briup.framework.bean.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.ManagerSlaughteringGroup;

import java.util.List;

/**
 * @author shaoyb
 * @program: product_source
 * @description 屠宰组扩展基类（含员工s）
 * @create 2022/3/30 15:10
 **/
public class ManagerSlaughteringGroupExtend extends ManagerSlaughteringGroup {
    //该屠宰组中包含的所有员工
    private List<BaseAccount> accounts;

    public List<BaseAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BaseAccount> accounts) {
        this.accounts = accounts;
    }
}
