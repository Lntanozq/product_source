package com.briup.framework.service;

import com.briup.framework.bean.IssueRecord;
import com.briup.framework.bean.extend.IssueRecordExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 出单记录管理
 */
public interface IIssueRecordService {
    //保存或更新出单记录
    void saveOrUpdate(IssueRecord issueRecord);
    //批量删除出单记录
    void deleteInBatch(List<Integer> isrIds);
    //根据出单记录id单个删除出单记录
    void deleteById(Integer isrId);
    //查询所有出单记录
    List<IssueRecord> queryAll();
    //根据销售人员id，状态,出单时间段(时间字符串1、时间字符串2)，交付时间段(时间字符串1、时间字符串2)分页查询出单信息
    PageInfo<IssueRecordExtend> query(Integer pageNum,Integer pageSize,String isrSaleId,String isrStatus,String startTime,String endTime,String startDeliver,String endDeliver);
    //查询所有销售人员
    List<IssueRecordExtend> queryAllUser();
    //根据出单记录id更新状态
    void updateStatus(Integer isrId,String isrStatus);
    //根据订单id查询出单记录
    List<IssueRecordExtend> selectById(Integer isrId);
}
