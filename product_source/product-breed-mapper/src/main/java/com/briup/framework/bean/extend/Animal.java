package com.briup.framework.bean.extend;

import java.util.List;

/**
 * @author pgc
 * @Description: 业务生猪出栏对应动物
 * @date 2022/3/31 9:30
 */
public class Animal {
    String ocId;
    List<String> animalId;

    public String getOcId() {
        return ocId;
    }

    public void setOcId(String ocId) {
        this.ocId = ocId;
    }

    public List<String> getAnimalId() {
        return animalId;
    }

    public void setAnimalId(List<String> animalId) {
        this.animalId = animalId;
    }
}
