package com.briup.framework.bean.extend;

import java.util.List;

/**
 * @author pgc
 * @Description: 针对生猪出栏和屠宰登记所对应前端所返回的json数据所产生的实体类
 * @date 2022/3/30 15:50
 */
public class AnimalRecord {
    String recordTime;
    String accountId;
    List<Animal> mAnimals;

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<Animal> getAnimals() {
        return mAnimals;
    }

    public void setAnimals(List<Animal> animals) {
        mAnimals = animals;
    }
}

