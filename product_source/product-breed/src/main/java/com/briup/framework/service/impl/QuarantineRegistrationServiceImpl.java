package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerBatch;
import com.briup.framework.bean.QuarantineRegistration;
import com.briup.framework.bean.extend.ManagerBatchExtend;
import com.briup.framework.bean.extend.QuarantineRegistrationExtend;
import com.briup.framework.bean.extend.ShiftCircleExtend;
import com.briup.framework.mapper.ManagerBatchMapper;
import com.briup.framework.mapper.QuarantineRegistrationMapper;
import com.briup.framework.mapper.extend.ManagerAnimalExtendMapper;
import com.briup.framework.mapper.extend.ManagerBatchExtendMapper;
import com.briup.framework.mapper.extend.QuarantineRegistrationExtendMapper;
import com.briup.framework.service.IQuarantineRegistrationService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: product_source
 * @description: 检疫登记管理
 * @author: nirui
 * @create: 2022-03-31 10:14
 */
@Service
public class QuarantineRegistrationServiceImpl implements IQuarantineRegistrationService {
    @Autowired
    private QuarantineRegistrationMapper quarantineRegistrationMapper;
    @Autowired
    private QuarantineRegistrationExtendMapper quarantineRegistrationExtendMapper;
    @Autowired
    private ManagerBatchMapper managerBatchMapper;
    @Autowired
    private ManagerAnimalExtendMapper managerAnimalExtendMapper;
    @Autowired
    private ManagerBatchExtendMapper managerBatchExtendMapper;

    @Override
    public void saveOrUpdate(QuarantineRegistrationExtend quarantineRegistrationExtend) {
        if(quarantineRegistrationExtend != null){
            QuarantineRegistration quarantineRegistration = new QuarantineRegistration();
            quarantineRegistration.setGrBatchId(quarantineRegistrationExtend.getGrBatchId());
            quarantineRegistration.setGrImg(quarantineRegistrationExtend.getGrImg());
            quarantineRegistration.setGrMechanism(quarantineRegistrationExtend.getGrMechanism());
            quarantineRegistration.setGrTime(quarantineRegistrationExtend.getGrTime());
            quarantineRegistration.setGrUquarantinerId(quarantineRegistrationExtend.getGrUquarantinerId());
            ManagerBatch managerBatch = new ManagerBatch();
            managerBatch.setbSerialId(quarantineRegistrationExtend.getGrBatchId());
            managerBatch.setbQualified(quarantineRegistrationExtend.getbQualified());
            managerBatch.setbQuarantineImg(quarantineRegistrationExtend.getGrImg());
            managerBatch.setbQuarantine("已检疫");
            //判断检疫登记id是否为空
            if(quarantineRegistrationExtend.getGrId() != null){
                //不为空，更新
                //更新检疫记录对象，存储到数据库中
                quarantineRegistration.setGrId(quarantineRegistrationExtend.getGrId());
                int count = quarantineRegistrationMapper.updateByPrimaryKeySelective(quarantineRegistration);
                if(count == 0){
                    throw new BriupFrameworkException("更新失败！");
                }
            }else{
                //为空，新增
                //判断批次编号是否有效
                String grBatchId = quarantineRegistrationExtend.getGrBatchId();
                ManagerBatchExtend managerBatchExtend = managerBatchExtendMapper.selectById(grBatchId);
                if(managerBatchExtend != null) {
                    //新增检疫记录对象，存储到数据库中
                    int count = quarantineRegistrationMapper.insertSelective(quarantineRegistration);
                    if (count == 0) {
                        throw new BriupFrameworkException("新增成功！");
                    }
                }else {
                    throw new BriupFrameworkException("批次编号无效！");
                }
            }
            //检疫结果等更新到批次管理
            managerBatchMapper.updateByPrimaryKeySelective(managerBatch);
            //检疫状态更新到动物管理
            managerAnimalExtendMapper.updateStatus(quarantineRegistrationExtend.getGrBatchId(),"已检疫");
        }
    }

    @Override
    public void deleteInBatch(List<Integer> grIds) {
        //判断集合是否为空
        if(!CollectionUtils.isEmpty(grIds)){
            //不为空，批量删除
            quarantineRegistrationExtendMapper.deleteInBatch(grIds);
        }else{
            //为空，参数错误
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public void deleteById(Integer grId) {
        if(grId != null){
            quarantineRegistrationExtendMapper.deleteById(grId);
        }else{
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public List<QuarantineRegistration> queryAll() {
        List<QuarantineRegistration> list = quarantineRegistrationExtendMapper.queryAll();
        return list;
    }

    @Override
    public List<QuarantineRegistrationExtend> queryAllUser() {
        List<QuarantineRegistrationExtend> list = quarantineRegistrationExtendMapper.queryAllUser();
        return list;
    }

    @Override
    public PageInfo<QuarantineRegistrationExtend> query(Integer pageNum,Integer pageSize,String grBatchId, String grMechanism, String bQualified, String grUquarantinerId, String startTime, String endTime) {
        //判断参数是否为空
        if(pageNum != null && pageSize != null){
            //设置分页
            PageHelper.startPage(pageNum,pageSize);
            //条件查询
            List<QuarantineRegistrationExtend> list = quarantineRegistrationExtendMapper.query(grBatchId, grMechanism, bQualified, grUquarantinerId, startTime, endTime);
            PageInfo<QuarantineRegistrationExtend> info = new PageInfo<>(list);
            return info;
        }else{
            throw new BriupFrameworkException("参数为空！");
        }
    }
}
