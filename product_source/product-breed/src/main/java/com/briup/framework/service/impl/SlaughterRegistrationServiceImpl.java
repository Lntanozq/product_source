package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.SlaughterRegistration;
import com.briup.framework.bean.extend.*;
import com.briup.framework.mapper.ManagerAnimalMapper;
import com.briup.framework.mapper.SlaughterRegistrationMapper;
import com.briup.framework.mapper.extend.BatchAnimalMapper;
import com.briup.framework.mapper.extend.ManagerSlaughteringGroupExtendMapper;
import com.briup.framework.mapper.extend.SlaughterRegistrationExtendMapper;
import com.briup.framework.service.ISlaughterRegistrationService;

import com.briup.framework.utils.execption.BriupFrameworkException;
import com.briup.framework.utils.web.ResponseCode;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author pgc
 * @Description: 屠宰登记业务层
 * @date 2022/3/31 9:46
 */
@Service
public class SlaughterRegistrationServiceImpl implements ISlaughterRegistrationService {
    @Autowired
    private BatchAnimalMapper batchAnimalMapper;
    @Autowired
    private ManagerAnimalMapper managerAnimalMapper;
    @Autowired
    private SlaughterRegistrationExtendMapper slaughterRegistrationExtendMapper;

    @Override
    public List<ManagerBatchAnimalExtend> query(String aStatus) {
        return batchAnimalMapper.query(aStatus);
    }

    @Override
    public void saveOrUpdate(AnimalRecord animalRecord) {
        for (Animal animal : animalRecord.getAnimals()) {
            ////插入屠宰信息
            batchAnimalMapper.insertSlaughterRegistration(animal.getOcId(),
                    animalRecord.getAccountId(),
                    animalRecord.getRecordTime());
        }
        ////更新动物过程状态
        for (Animal animal : animalRecord.getAnimals()) {
            List<String> animalId = animal.getAnimalId();
            batchAnimalMapper.updateAnimalStatus(animalId,"已屠宰","");
        }
    }

    @Override
    public List<SlaughterRegistrationExtend> selectSlaughterRecordByAnimalId(String animalId) {
        ManagerAnimal animal = managerAnimalMapper.selectByPrimaryKey(animalId);
        List<SlaughterRegistrationExtend> list = slaughterRegistrationExtendMapper.selectRecordWithSGroupByBatchId(animal.getaBatchId());
        return list;
    }

}
