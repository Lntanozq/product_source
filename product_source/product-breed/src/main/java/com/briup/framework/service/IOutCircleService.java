package com.briup.framework.service;

import com.briup.framework.bean.extend.AnimalRecord;
import com.briup.framework.bean.extend.ManagerBatchAnimalExtend;

import java.util.List;

/**
 * @author pgc
 * @Description: 生猪出栏业务层
 * @date 2022/3/30 15:05
 */
public interface IOutCircleService {
    //根据动物过程状态获取对应批次以及动物信息
    List<ManagerBatchAnimalExtend> query(String aStatus);
    //插入和更新
     void saveOrUpdate(AnimalRecord animalRecord);
}
