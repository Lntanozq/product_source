package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.SituationReporting;
import com.briup.framework.bean.extend.SituationReportingExtend;
import com.briup.framework.mapper.SituationReportingMapper;
import com.briup.framework.mapper.extend.BatchAnimalMapper;
import com.briup.framework.mapper.extend.ManagerAnimalExtendMapper;
import com.briup.framework.mapper.extend.SituationReportingExtendMapper;
import com.briup.framework.service.ISituationReportingService;
import com.briup.framework.utils.common.DateTimeUtil;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pgc
 * @Description: 特殊情况汇报业务层
 * @date 2022/3/31 17:06
 */
@Service
public class SituationReportingServiceImpl implements ISituationReportingService {
    @Autowired
    SituationReportingMapper situationReportingMapper;
    @Autowired
    SituationReportingExtendMapper situationReportingExtendMapper;
    @Autowired
    BatchAnimalMapper batchAnimalMapper;
    @Autowired
    ManagerAnimalExtendMapper managerAnimalExtendMapper;
    @Override
    public void saveOrUpdate(SituationReporting situationReporting) {
        ManagerAnimal managerAnimal = managerAnimalExtendMapper.selectById(situationReporting.getSrAnimalId());
        if (managerAnimal==null){
            throw new BriupFrameworkException("该动物不存在");
        }
        if (!managerAnimal.getaStatus().equals("养殖中")){
            throw new BriupFrameworkException("该动物不在养殖中");
        }
            if (situationReporting.getSrId()!=null){
                 int count=situationReportingMapper.updateByPrimaryKeySelective(situationReporting);
                if (count == 0) {
                    throw new BriupFrameworkException("更新失败！");
                }
            }else {
                int count=situationReportingMapper.insertSelective(situationReporting);
                if (count == 0) {
                    throw new BriupFrameworkException("插入失败！");
                }
            }
    }

    @Override
    public void deleteById(int srId) {
        situationReportingMapper.deleteByPrimaryKey(srId);
    }

    @Override
    public void updateStatus(int srId,String srAnimalId, String srStatus,String srUdockerId) {
        if (srStatus.equals("生病中")||srStatus.equals("未治愈")){
            batchAnimalMapper.updateAnimalHealthy(srAnimalId,"生病");
        }
        if (srStatus.equals("已治愈")){
            batchAnimalMapper.updateAnimalHealthy(srAnimalId,"健康");
        }
       situationReportingExtendMapper.updateStatus(srId,srStatus,srUdockerId);
    }

    @Override
    public PageInfo<SituationReportingExtend> query(int page, int pageSize, String srAnimalId, String srStatus, String startTime, String endTime, String srUbreedId, String srUdockerId) {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(page,pageSize);
        List<SituationReportingExtend> list=null;
        if (startTime!=null&&endTime!=null) {
            list=situationReportingExtendMapper.query(srAnimalId,srStatus,
                    DateTimeUtil.formatDateStringDefault(startTime),
                    DateTimeUtil.formatDateStringDefault(endTime),
                    srUbreedId,srUdockerId);

        }else {
            list=situationReportingExtendMapper.query(srAnimalId,srStatus,
                    null,null,srUbreedId,srUdockerId);

        }
        PageInfo<SituationReportingExtend> pageInfo=new PageInfo<>(list);
       return pageInfo;
    }
}
