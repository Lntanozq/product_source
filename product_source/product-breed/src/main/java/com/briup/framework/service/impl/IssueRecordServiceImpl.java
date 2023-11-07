package com.briup.framework.service.impl;

import com.briup.framework.bean.IssueRecord;
import com.briup.framework.bean.extend.IssueRecordExtend;
import com.briup.framework.bean.extend.ShiftCircleExtend;
import com.briup.framework.mapper.IssueRecordMapper;
import com.briup.framework.mapper.extend.IssueRecordExtendMapper;
import com.briup.framework.service.IIssueRecordService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: product_source
 * @description: 出单记录管理
 * @author: nirui
 * @create: 2022-03-31 17:07
 */
@Service
public class IssueRecordServiceImpl implements IIssueRecordService {
    @Autowired
    private IssueRecordMapper issueRecordMapper;
    @Autowired
    private IssueRecordExtendMapper issueRecordExtendMapper;

    @Override
    public void saveOrUpdate(IssueRecord issueRecord) {
        if(issueRecord != null){
            //判断记录id是否为空
            if(issueRecord.getIsrId() != null){
                //不为空，则更新
                int count = issueRecordMapper.updateByPrimaryKeySelective(issueRecord);
                if(count == 0){
                    throw new BriupFrameworkException("更新失败！");
                }
            }else{
                //为空，则新增
                int count = issueRecordMapper.insertSelective(issueRecord);
                if(count == 0){
                    throw new BriupFrameworkException("新增失败！");
                }
            }
        }else{
            throw new BriupFrameworkException("参数缺失！");
        }
    }

    @Override
    public void deleteInBatch(List<Integer> isrIds) {
        //判断集合是否为空
        if(!CollectionUtils.isEmpty(isrIds)){
            //不为空，批量删除
            issueRecordExtendMapper.deleteInBatch(isrIds);
        }else{
            //为空，参数错误
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public void deleteById(Integer isrId) {
        if(isrId != null){
            issueRecordExtendMapper.deleteById(isrId);
        }else{
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public List<IssueRecord> queryAll() {
        List<IssueRecord> list = issueRecordExtendMapper.queryAll();
        return list;
    }

    @Override
    public PageInfo<IssueRecordExtend> query(Integer pageNum, Integer pageSize, String isrSaleId, String isrStatus, String startTime, String endTime, String startDeliver, String endDeliver) {
        //判断参数是否为空
        if(pageNum != null && pageSize != null){
            //设置分页
            PageHelper.startPage(pageNum,pageSize);
            //条件查询
            List<IssueRecordExtend> list = issueRecordExtendMapper.query(isrSaleId, isrStatus, startTime, endTime, startDeliver, endDeliver);
            PageInfo<IssueRecordExtend> info = new PageInfo<>(list);
            return info;
        }else{
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public List<IssueRecordExtend> queryAllUser() {
        List<IssueRecordExtend> list = issueRecordExtendMapper.queryAllUser();
        return list;
    }

    @Override
    public void updateStatus(Integer isrId, String isrStatus) {
        if(isrId != null && isrStatus != null && !"".equals(isrStatus.trim())){
            issueRecordExtendMapper.updateStatus(isrId, isrStatus);
        }else{
            throw new BriupFrameworkException("参数为空");
        }
    }

    @Override
    public List<IssueRecordExtend> selectById(Integer isrId) {
        if(isrId != null){
            List<IssueRecordExtend> list = issueRecordExtendMapper.selectById(isrId);
            return list;
        }else{
            throw new BriupFrameworkException("参数为空");
        }
    }
}
