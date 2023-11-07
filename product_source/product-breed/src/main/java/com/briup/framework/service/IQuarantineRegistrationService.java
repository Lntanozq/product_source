package com.briup.framework.service;

import com.briup.framework.bean.QuarantineRegistration;
import com.briup.framework.bean.extend.QuarantineRegistrationExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 检疫登记管理
 */
public interface IQuarantineRegistrationService {
    //保存或更新检疫记录
    void saveOrUpdate(QuarantineRegistrationExtend quarantineRegistrationExtend);
    //批量删除检疫记录
    void deleteInBatch(List<Integer> grIds);
    //根据id删除检疫记录
    void deleteById(Integer grId);
   //查询所有检疫记录
   List<QuarantineRegistration> queryAll();
   //查询所有检疫人员
    List<QuarantineRegistrationExtend> queryAllUser();
    //根据批次编号（手动输入），检疫机构，检疫结果，检疫人员id，时间段(时间字符串1、时间字符串2)分页查询检疫记录
    PageInfo<QuarantineRegistrationExtend> query(Integer pageNum,Integer pageSize,String grBatchId,String grMechanism,String bQualified,String grUquarantinerId,String startTime,String endTime);
}
