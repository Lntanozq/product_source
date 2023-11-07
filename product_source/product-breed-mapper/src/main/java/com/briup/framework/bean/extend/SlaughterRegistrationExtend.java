package com.briup.framework.bean.extend;

import com.briup.framework.bean.SlaughterRegistration;

/**
 * @author shaoyb
 * @program: product_source
 * @description 动物屠宰登记记录类(含屠宰登记记录基本信息，屠宰组信息，屠宰组人员信息)
 * @create 2022/4/12 10:38
 **/
public class SlaughterRegistrationExtend extends SlaughterRegistration {
    //屠宰组信息(含人员)
    private ManagerSlaughteringGroupExtend slaughteringGroup;

    public ManagerSlaughteringGroupExtend getSlaughteringGroup() {
        return slaughteringGroup;
    }

    public void setSlaughteringGroup(ManagerSlaughteringGroupExtend slaughteringGroup) {
        this.slaughteringGroup = slaughteringGroup;
    }
}
