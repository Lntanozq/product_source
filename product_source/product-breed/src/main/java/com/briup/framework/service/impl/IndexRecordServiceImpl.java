package com.briup.framework.service.impl;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.IndexRecord;
import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.extend.IndexRecordExtend;
import com.briup.framework.mapper.IndexRecordMapper;
import com.briup.framework.mapper.extend.BatchAnimalMapper;
import com.briup.framework.mapper.extend.IndexRecordExtendMapper;
import com.briup.framework.mapper.extend.ManagerAnimalExtendMapper;
import com.briup.framework.service.IIndexRecordService;
import com.briup.framework.utils.common.DateTimeUtil;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: product_source
 * @description: 指标记录业务层
 * @author: pgc
 * @create:2022-03-29
 **/
@Service
public class IndexRecordServiceImpl implements IIndexRecordService {
    @Autowired
    IndexRecordMapper indexRecordMapper;
    @Autowired
    IndexRecordExtendMapper indexRecordExtendMapper;
    @Autowired
    ManagerAnimalExtendMapper managerAnimalExtendMapper;
    @Autowired
    BatchAnimalMapper batchAnimalMapper;
    @Override
    public void saveOrUpdate(IndexRecord indexRecord) {
        ManagerAnimal managerAnimal = managerAnimalExtendMapper.selectById(indexRecord.getIrdAnimalId());
        if (managerAnimal==null){
            throw new BriupFrameworkException("该动物不存在");
        }
        if (!managerAnimal.getaStatus().equals("养殖中")){
            throw new BriupFrameworkException("该动物不在养殖中");
        }
        if (indexRecord.getIrdId()!=null){
            //更新
            int count=indexRecordMapper.updateByPrimaryKeySelective(indexRecord);

            if (count==0){
                throw new BriupFrameworkException("更新失败");
            }
            if (indexRecord.getIrdWeight()!=null){
                batchAnimalMapper.updateAnimaWeight(indexRecord.getIrdAnimalId(),indexRecord.getIrdWeight());
            }
        }else {
            //插入
            int count=indexRecordMapper.insertSelective(indexRecord);
            if (count==0){
                throw new BriupFrameworkException("插入失败");
            }
            if (indexRecord.getIrdWeight()!=null){
                batchAnimalMapper.updateAnimaWeight(indexRecord.getIrdAnimalId(),indexRecord.getIrdWeight());
            }
        }
    }

    @Override
    public List<BaseAccount> queryTesting() {
        return indexRecordExtendMapper.queryTesting();
    }

    @Override
    public void deleteById(int irdId) {
        indexRecordMapper.deleteByPrimaryKey(irdId);
    }

    @Override
    public PageInfo<IndexRecordExtend> query(int page, int pageSize, String animalNumber, String healthy, String startTime, String endTime, String startHeartRate, String endHeartRate, String startBreathing, String endBreathing, String startWeight, String endWeight, String ubreedId) {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(page,pageSize);
        List<IndexRecordExtend> list=null;
        if (startTime!=null&&endTime!=null) {
            list = indexRecordExtendMapper.query(animalNumber, healthy,
                    DateTimeUtil.formatDateStringDefault(startTime),
                    DateTimeUtil.formatDateStringDefault(endTime),
                    startHeartRate, endHeartRate, startBreathing, endBreathing, startWeight, endWeight, ubreedId);
        }else {
            list = indexRecordExtendMapper.query(animalNumber, healthy,
                    null,
                    null,
                    startHeartRate, endHeartRate, startBreathing, endBreathing, startWeight, endWeight, ubreedId);
        }
       PageInfo<IndexRecordExtend> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
}
