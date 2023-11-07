package com.briup.framework.bean.extend;

import java.util.List;

/**
 * @author pgc
 * @Description: 出库登记扩展业务类
 * @date 2022/4/1 9:20
 */
public class OutStorageRecord {
    private int isrId;
    private String csrOutTime;
    List<String> animals;

    public int getIsrId() {
        return isrId;
    }

    public void setIsrId(int isrId) {
        this.isrId = isrId;
    }

    public String getCsrOutTime() {
        return csrOutTime;
    }

    public void setCsrOutTime(String csrOutTime) {
        this.csrOutTime = csrOutTime;
    }

    public List<String> getAnimals() {
        return animals;
    }

    public void setAnimals(List<String> animals) {
        this.animals = animals;
    }
}
