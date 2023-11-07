package com.briup.framework.service.impl;

import com.briup.framework.bean.extend.Animal;
import com.briup.framework.bean.extend.AnimalRecord;
import com.briup.framework.bean.extend.ManagerBatchAnimalExtend;
import com.briup.framework.mapper.extend.BatchAnimalMapper;
import com.briup.framework.service.IOutCircleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pgc
 * @Description: 生猪出栏业务层
 * @date 2022/3/30 15:33
 */
@Service
public class OutCircleServiceImpl implements IOutCircleService {
    @Autowired
    private BatchAnimalMapper batchAnimalMapper;
    @Override
    public List<ManagerBatchAnimalExtend> query(String aStatus) {
        return batchAnimalMapper.query(aStatus);
    }

    @Override
    public void saveOrUpdate(AnimalRecord animalRecord) {
        for (Animal animal : animalRecord.getAnimals()) {
            ////插入出栏信息
            batchAnimalMapper.insertOutCircle(animal.getOcId(),
                                         animalRecord.getAccountId(),
                                         animalRecord.getRecordTime());
        }
        ////更新动物过程状态
        for (Animal animal : animalRecord.getAnimals()) {
            List<String> animalIds = animal.getAnimalId();
            batchAnimalMapper.updateAnimalStatus(animalIds,"已出栏","");
        }

    }
}
