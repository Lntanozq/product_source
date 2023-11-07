package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.extend.Animal;
import com.briup.framework.bean.extend.AnimalRecord;
import com.briup.framework.bean.extend.ColdStorageRecordExtend;
import com.briup.framework.bean.extend.ManagerBatchAnimalExtend;
import com.briup.framework.bean.extend.OutStorageRecord;
import com.briup.framework.mapper.extend.BatchAnimalMapper;
import com.briup.framework.mapper.extend.ColdStorageRecordExtendMapper;
import com.briup.framework.mapper.extend.IssueRecordExtendMapper;
import com.briup.framework.mapper.extend.ManagerFreezerExtendMapper;
import com.briup.framework.service.IColdStorageRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pgc
 * @Description: 入库出库登记
 * @date 2022/3/31 16:02
 */
@Service
public class ColdStorageRecordServiceImpl implements IColdStorageRecordService {
    @Autowired
    private BatchAnimalMapper batchAnimalMapper;
    @Autowired
    private IssueRecordExtendMapper issueRecordExtendMapper;
    @Autowired
    private ColdStorageRecordExtendMapper coldStorageRecordExtendMapper;
    @Autowired
    private ManagerFreezerExtendMapper managerFreezerExtendMapper;
    @Override
    public List<ManagerBatchAnimalExtend> query(String aStatus) {
        return batchAnimalMapper.query(aStatus);
    }

    @Override
    public void saveOrUpdate(AnimalRecord animalRecord) {
        for (Animal animal : animalRecord.getAnimals()) {
            List<String> animalIds = animal.getAnimalId();
            for(String aId:animalIds){
                batchAnimalMapper.insertColdStorageRecord(animal.getOcId(),aId,
                        animalRecord.getRecordTime(),animalRecord.getAccountId());
                //完成冷库以存量
                int quantity = managerFreezerExtendMapper.selectByIdQuantity(Integer.parseInt(animal.getOcId()));
                   quantity++;
                managerFreezerExtendMapper.updateByIdQuantity(quantity,Integer.parseInt(animal.getOcId()));
            }
            batchAnimalMapper.updateAnimalStatus(animalIds,"已入库","");
        }
    }

    @Override
    public void updateStorageRecord(OutStorageRecord outStorageRecord) {
        //1.根据订单修改出单表的状态为已出库
         issueRecordExtendMapper.updateStatus(outStorageRecord.getIsrId(),"已出库");
        List<String> animals = outStorageRecord.getAnimals();
        for(String aId:animals){
            int coldId = batchAnimalMapper.selectByAId(aId);
            //完成冷库以存量
            int quantity = managerFreezerExtendMapper.selectByIdQuantity(coldId);
            quantity--;
            managerFreezerExtendMapper.updateByIdQuantity(quantity,coldId);
        }
        //2.根据动物编号修改冷库中的出库时间
        batchAnimalMapper.updateColdStorageRecord(animals,outStorageRecord.getCsrOutTime());
        //3.根据动物编号修改动物表中状态为已销售
       batchAnimalMapper.updateAnimalStatus(animals,"已销售",outStorageRecord.getIsrId()+"");


    }

    @Override
    public List<ColdStorageRecordExtend> queryAll() {
        //获取去重的冷库信息
        //获取冷库对应的动物信息
        List<ColdStorageRecordExtend> list = coldStorageRecordExtendMapper.queryFreezer();
        for (ColdStorageRecordExtend cr:list){
            List<ManagerAnimal> animals = coldStorageRecordExtendMapper.queryAnimal(cr.getFzId());
            cr.setManagerAnimals(animals);
        }

        return list;
    }
}
