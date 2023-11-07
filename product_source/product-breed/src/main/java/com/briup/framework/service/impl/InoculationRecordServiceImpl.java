package com.briup.framework.service.impl;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.InoculationRecord;
import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.extend.Animal;
import com.briup.framework.bean.extend.InoculationRecordExtend;
import com.briup.framework.mapper.InoculationRecordMapper;
import com.briup.framework.mapper.ManagerAnimalMapper;
import com.briup.framework.mapper.extend.BatchAnimalMapper;
import com.briup.framework.mapper.extend.InoculationRecordExtendMapper;
import com.briup.framework.service.IInoculationRecordService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.briup.framework.utils.web.ResponseCode;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author shaoyb
 * @program: product_source
 * @description 接种记录相关业务处理
 * @create 2022/3/31 14:57
 **/
@Service
public class InoculationRecordServiceImpl implements IInoculationRecordService {
    @Autowired
    private ManagerAnimalMapper animalMapper;
    @Autowired
    private InoculationRecordMapper inoculationRecordMapper;
    @Autowired
    private InoculationRecordExtendMapper inoculationRecordExtendMapper;
    @Autowired
    private BatchAnimalMapper batchAnimalMapper;

    @Override
    public void saveOrUpdate(InoculationRecord inoculationRecord) {
        //1.参数判断
        if(inoculationRecord == null)
            throw new BriupFrameworkException("参数缺失");

        //2.动物id判断
        //2.1 动物id是否为空
        String animalId = inoculationRecord.getIrAnimalId();
        if(animalId == null)
            throw new BriupFrameworkException("动物id为空");
        //2.2 动物是否存在
        ManagerAnimal animal = animalMapper.selectByPrimaryKey(animalId);
        if(animal == null)
            throw new BriupFrameworkException("动物id无效");
        //2.3 动物养殖状态判断，必须为"养殖中"
        if(!"养殖中".equals(animal.getaStatus()))
            throw new BriupFrameworkException("动物不在养殖中", ResponseCode.DATA_WRONG);
        //2.4 动物是否已经被删除
        if(animal.getaBackup2() == null || "1".equals(animal.getaBackup2()))
            throw new BriupFrameworkException("操作失败：动物已删除");
        //2.5 动物健康状态判断，a_healthy不能为 生病
        if("生病".equals(animal.getaHealthy()))
            throw new BriupFrameworkException("操作失败：病猪");

        //2.6 判断医生id是否有效【如果医生自行登录，则无需判断；如果管理员选医护人员，也不需判断，广闯已处理】

        //3.功能实现
        if(inoculationRecord.getIrId() == null){
            //3.1 记录表数据插入
            inoculationRecordMapper.insertSelective(inoculationRecord);

            //3.2 更新相应id动物的 接种状态为：已接种
            animal.setaInoculate("已接种");
            animalMapper.updateByPrimaryKeySelective(animal);
        }else{
            //3.1 检查接种表中修改项 是否包含动物id
            //  a.如果不包含，直接更新接种表即可
            //  b.如果包含，则还需要额外修改动物表：原动物表接种状态为“未接种” 新动物接种状态：“已接种”
            InoculationRecord oldRecord = inoculationRecordMapper.selectByPrimaryKey(inoculationRecord.getIrId());
            String oldAnimalId = oldRecord.getIrAnimalId();
            //3.3.1 更新接种表
            inoculationRecordMapper.updateByPrimaryKeySelective(inoculationRecord);

            //3.2.2 额外更新 动物表数据
            if(!animalId.equals(oldAnimalId)) {
                // 原动物表接种状态为“未接种”
                ManagerAnimal oldAnimal = animalMapper.selectByPrimaryKey(oldAnimalId);
                oldAnimal.setaInoculate("未接种");
                animalMapper.updateByPrimaryKeySelective(oldAnimal);

                // 新动物接种状态：“已接种”
                animal.setaInoculate("已接种");
                animalMapper.updateByPrimaryKeySelective(animal);
            }
        }
    }

    @Override
    public void deleteById(Integer irId) {
        //1.id判断
        if(irId == null)
            throw new BriupFrameworkException("参数为空");

        InoculationRecord record = inoculationRecordMapper.selectByPrimaryKey(irId);
        if(record == null)
            throw new BriupFrameworkException("参数无效");

        //2.更新动物表中 接种状态
        String animalId = record.getIrAnimalId();
        ManagerAnimal animal = animalMapper.selectByPrimaryKey(animalId);
        animal.setaInoculate("未接种");
        animalMapper.updateByPrimaryKeySelective(animal);

        //3.删除记录表中数据
        inoculationRecordMapper.deleteByPrimaryKey(irId);
    }

    @Override
    public void deleteAllByIds(List<Integer> irIds) {
        //1.参数判断
        if(irIds == null || irIds.isEmpty())
            throw new BriupFrameworkException("参数为空");

        //2.根据irIds获取所有相关动物id
        List<String> animalIds = inoculationRecordExtendMapper.getAllAnimalIdByIrIds(irIds);
        if(animalIds == null || animalIds.isEmpty())
            throw new BriupFrameworkException("参数无效");

        //3.删除接种表中相关记录
        int count = inoculationRecordExtendMapper.deleteAllByIds(irIds);
        if(count == 0)
            throw new BriupFrameworkException("删除接种表记录失败");

        //4.根据ids更新所有动物接种状态：未接种
        // 借助 batchAnimalMapper实现
        batchAnimalMapper.updateAnimalInoculate(animalIds,"未接种");
    }

    @Override
    public List<InoculationRecordExtend> queryAllRecordWithVaccinesAndUinoculationAccount() {
        List<InoculationRecordExtend> list = inoculationRecordExtendMapper.selectAllWithVaccinesAndUAccount();
        return list;
    }

    @Override
    public PageInfo<InoculationRecordExtend> query(Integer page, Integer pageSize, String irAnimalId, String irVaccinesId, String irUinoculationId, String startTime, String endTime) {
        if(page == null || pageSize == null)
            throw new BriupFrameworkException("参数为空");

        PageHelper.startPage(page,pageSize);

        List<InoculationRecordExtend> list = inoculationRecordExtendMapper.selectWithExtend(irAnimalId,irVaccinesId,irUinoculationId,startTime,endTime);
        PageInfo<InoculationRecordExtend> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public List<BaseAccount> queryAllUinoculationAccount() {
        List<BaseAccount> list = inoculationRecordExtendMapper.selectAllUAccounts();
        return list;
    }

}
