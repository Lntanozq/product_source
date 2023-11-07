package com.briup.framework.service.impl;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.DiseaseRecord;
import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.ManagerDisease;
import com.briup.framework.bean.extend.DiseaseRecordExtend;
import com.briup.framework.mapper.DiseaseRecordMapper;
import com.briup.framework.mapper.ManagerAnimalMapper;
import com.briup.framework.mapper.ManagerDiseaseMapper;
import com.briup.framework.mapper.extend.DiseaseRecordExtendMapper;
import com.briup.framework.mapper.extend.ManagerAnimalExtendMapper;
import com.briup.framework.mapper.extend.ManagerDiseaseExtendMapper;
import com.briup.framework.mapper.extend.UserExtendMapper;
import com.briup.framework.service.IDiseaseRecordService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.briup.framework.utils.web.ResponseCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author shaoyb
 * @program: product_source
 * @description 病症记录逻辑层
 * @create 2022/4/8 9:33
 **/
@Service
public class DiseaseRecordServiceImpl implements IDiseaseRecordService {
    @Autowired
    private UserExtendMapper userExtendMapper;
    @Autowired
    private ManagerDiseaseExtendMapper managerDiseaseExtendMapper;
    @Autowired
    private DiseaseRecordMapper diseaseRecordMapper;
    @Autowired
    private DiseaseRecordExtendMapper diseaseRecordExtendMapper;
    @Autowired
    private ManagerAnimalMapper managerAnimalMapper;
    @Autowired
    private ManagerAnimalExtendMapper managerAnimalExtendMapper;

    //新增或更新病症信息
    @Override
    public void saveOrUpdate(DiseaseRecord record) {
        //1.参数判断
        //1.1 参数判空
        if(record == null)
            throw new BriupFrameworkException("参数为空", ResponseCode.DATA_NOT_FOUND);
        Integer drId = record.getDrId();

        //1.2 根据动物编号判断动物是否存在，是否处于养殖状态
        String animalId = record.getDrAnimalId();
        ManagerAnimal animal = managerAnimalMapper.selectByPrimaryKey(animalId);
        if(ObjectUtils.isEmpty(animal))
            throw new BriupFrameworkException("动物id无效", ResponseCode.DATA_WRONG);
        if(!"养殖中".equals(animal.getaStatus()))
            throw new BriupFrameworkException("动物不在养殖中", ResponseCode.DATA_WRONG);

        //1.3 判断病症是否有效【此处无需判断，后台提供给前台接口 会返回所有 有效病症】
        //1.4 判断医生id是否有效【如果医生自行登录，则无需判断；如果管理员选医护人员，不需要额外判断，广闯已处理】

        if(ObjectUtils.isEmpty(drId)){
            //2.插入操作
            //2.1 记录表插入数据
            diseaseRecordMapper.insertSelective(record);

            //2.2 额外更新相关动物 健康状态为 生病
            updateAnimalHealthy(animalId,"生病");
        }else{
            //3.更新操作
            DiseaseRecord oldRecode = diseaseRecordMapper.selectByPrimaryKey(drId);
            if(ObjectUtils.isEmpty(oldRecode))
                throw new BriupFrameworkException("参数值错误",ResponseCode.DATA_WRONG);
            //3.1 完成记录表更新
            diseaseRecordMapper.updateByPrimaryKeySelective(record);

            //3.2 如果修改 记录表中状态为：已治疗，则需要额外更新 动物健康状态为 健康
            /* 此处不需要额外处理，特殊情况上报部分 处理
            if("已治疗".equals(record.getDrStatus())){
                updateAnimalHealthy(animalId,"健康");
            }
            */
        }
    }

    //工具方法，更新 指定动物状态
    @Override
    public void updateAnimalHealthy(String animalId, String status) {
        if(StringUtils.isEmpty(animalId) || StringUtils.isEmpty(status))
            throw new BriupFrameworkException("参数为空", ResponseCode.DATA_NOT_FOUND);

        ManagerAnimal animal = managerAnimalMapper.selectByPrimaryKey(animalId);
        if(ObjectUtils.isEmpty(animal))
            throw new BriupFrameworkException("参数无效", ResponseCode.DATA_WRONG);

        animal.setaHealthy(status);
        managerAnimalMapper.updateByPrimaryKeySelective(animal);
    }

    //根据记录id 删除病症记录
    @Override
    public void deleteRecodeById(Integer drId) {
        //1.参数判断
        if(ObjectUtils.isEmpty(drId))
            throw new BriupFrameworkException("参数为空",ResponseCode.DATA_NOT_FOUND);

        DiseaseRecord recode = diseaseRecordMapper.selectByPrimaryKey(drId);
        if(ObjectUtils.isEmpty(recode))
            throw new BriupFrameworkException("参数有误",ResponseCode.DATA_WRONG);

        //2.删除记录
        diseaseRecordMapper.deleteByPrimaryKey(drId);

        //3.更新动物健康状态为：健康
        updateAnimalHealthy(recode.getDrAnimalId(),"健康");
    }

    //根据drIds 删除所有病症记录
    @Override
    public void deleteAllRecodeByIds(List<Integer> drIds) {
        //1.参数判断
        if(CollectionUtils.isEmpty(drIds))
            throw new BriupFrameworkException("参数为空",ResponseCode.DATA_NOT_FOUND);

        //2.获取病症记录涉及到的所有动物ids
        List<String> aIds = diseaseRecordExtendMapper.queryAllAnimalIdsByRecodeIds(drIds);
        if(CollectionUtils.isEmpty(aIds))
            throw new BriupFrameworkException("参数无效",ResponseCode.DATA_WRONG);

        //3.删除所有病症记录
        diseaseRecordExtendMapper.deleteAllByRecodeIds(drIds);

        //4.更新所有动物的健康状态：健康
        //managerAnimalExtendMapper.updateHealthyByIds(aIds,"健康");
        for (String aId : aIds) {
            ManagerAnimal animal = managerAnimalMapper.selectByPrimaryKey(aId);
            if(animal != null && "0".equals(animal.getaBackup2())){
                animal.setaHealthy("健康");
                managerAnimalMapper.updateByPrimaryKeySelective(animal);
            }
        }
    }

    @Override
    public List<DiseaseRecordExtend> queryAllRecord() {
        return diseaseRecordExtendMapper.queryAllRecords();
    }

    @Override
    public PageInfo<DiseaseRecordExtend> query(Integer page, Integer pageSize, String drAnimalId, String drStatus, Integer drDId, String drUdockerId, String startTime, String endTime) {
        //1.参数判断
        if(ObjectUtils.isEmpty(page) || ObjectUtils.isEmpty(pageSize))
            throw new BriupFrameworkException("参数为空",ResponseCode.DATA_NOT_FOUND);

        //2.开启分页查询
        PageHelper.startPage(page,pageSize);

        //3.分页模糊查询
        List<DiseaseRecordExtend> list = diseaseRecordExtendMapper.query(drAnimalId,drStatus,drDId,drUdockerId,startTime,endTime);

        //4.封装分页信息对象
        PageInfo<DiseaseRecordExtend> info = new PageInfo<>(list);

        return info;
    }

    //获取所有 医护人员
    @Override
    public List<BaseAccount> queryAllMedicalAccount() {
        return userExtendMapper.query("caced5e5-a727-4f19-bf1f-009823a70a83");
    }

    //查询所有病症信息
    @Override
    public List<ManagerDisease> queryAllDisease() {
        return managerDiseaseExtendMapper.queryAll();
    }

}
