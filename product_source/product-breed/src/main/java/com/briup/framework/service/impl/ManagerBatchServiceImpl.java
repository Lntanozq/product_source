package com.briup.framework.service.impl;

import com.briup.framework.bean.ManagerBatch;
import com.briup.framework.bean.extend.ManagerBatchExtend;
import com.briup.framework.mapper.ManagerBatchMapper;
import com.briup.framework.mapper.extend.ManagerBatchExtendMapper;
import com.briup.framework.service.IManagerBatchService;
import com.briup.framework.util.UUIDUtil;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: product_source
 * @description: 批次管理
 * @author: nirui
 * @create: 2022-03-28 09:23
 */
@Service
public class ManagerBatchServiceImpl implements IManagerBatchService {
    @Autowired
    private ManagerBatchMapper managerBatchMapper;

    @Autowired
    private ManagerBatchExtendMapper managerBatchExtendMapper;

    @Override
    public void saveOrUpdate(ManagerBatch batch) {
        if(batch != null) {
            //判断批次编号是否为空
            if (batch.getbSerialId() != null && !"".equals(batch.getbSerialId().trim())) {
                //编号不为空，则更新批次信息
                int count = managerBatchMapper.updateByPrimaryKeySelective(batch);
                //判断受影响的条数，判断是否更新成功
                if (count == 0) {
                    throw new BriupFrameworkException("更新失败！");
                }
            } else {
                //编号为空，则新增批次信息
                batch.setbSerialId(UUIDUtil.getUUID());
                batch.setbDelete(0);
                batch.setbQuarantine("未检疫");
                int count = managerBatchMapper.insertSelective(batch);
                //判断受影响的条数，判断是否新增成功
                if (count == 0) {
                    throw new BriupFrameworkException("保存失败！");
                }
            }
        }else{
            throw  new BriupFrameworkException("参数缺失");
        }
    }

    @Override
    public void deleteById(String serialId) {
        //判断编号是否为空
        if(!StringUtils.isEmpty(serialId)){
            //编号不为空，删除批次信息
            managerBatchExtendMapper.updateBySerialId(serialId);
        }else{
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public void deleteInBatch(List<String> serialIds) {
        //判断集合是否为空
        if(!CollectionUtils.isEmpty(serialIds)){
            //不为空，批量删除
           managerBatchExtendMapper.deleteInBatch(serialIds);
        }else{
            //为空，参数错误
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public PageInfo<ManagerBatchExtend> query(Integer pageNum, Integer pageSize, String serialId, String startTime, String endTime, String bQuarantine, String bQualified) {
        //判断参数是否为空
        if(pageNum != null && pageSize != null) {
            //设置分页
            PageHelper.startPage(pageNum, pageSize);
            //条件查询
            List<ManagerBatchExtend> list = managerBatchExtendMapper.query(serialId, startTime, endTime, bQuarantine, bQualified);
            PageInfo<ManagerBatchExtend> info = new PageInfo<>(list);
            return info;
        }else{
            throw new BriupFrameworkException("参数为空！");
        }
    }

    @Override
    public List<ManagerBatch> queryAll() {
        List<ManagerBatch> list = managerBatchExtendMapper.queryAll();
        return list;
    }

    @Override
    public ManagerBatchExtend selectById(String serialId) {
        //判断批次编号是否为空
        if(serialId != null && !"".equals(serialId.trim())){
            ManagerBatchExtend managerBatchExtend = managerBatchExtendMapper.selectById(serialId);
            return managerBatchExtend;
        }else{
            throw new BriupFrameworkException("参数为空");
        }

    }
}
