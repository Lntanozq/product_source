package com.briup.framework.service.impl;

import com.briup.framework.bean.FeedRecord;
import com.briup.framework.bean.extend.FeedRecordExtend;
import com.briup.framework.mapper.FeedRecordMapper;
import com.briup.framework.mapper.extend.FeedRecordExtendMapper;
import com.briup.framework.service.IFeedRecordService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pgc
 * @Description: 饲料更换业务层
 * @date 2022/4/2 9:30
 */
@Service
public class FeedRecordServiceImpl implements IFeedRecordService {
    @Autowired
    private FeedRecordMapper feedRecordMapper;
    @Autowired
    private FeedRecordExtendMapper feedRecordExtendMapper;
    @Override
    public void saveOrUpdate(FeedRecord feedRecord) {
     if (feedRecord.getFrId()!=null){
         int count=feedRecordMapper.updateByPrimaryKeySelective(feedRecord);
         if (count==0){
             throw new BriupFrameworkException("更新失败");
         }
     }else {
         int count=feedRecordMapper.insertSelective(feedRecord);
         if (count==0){
             throw new BriupFrameworkException("插入失败");
         }
     }
    }

    @Override
    public void deleteById(int fId) {
        feedRecordMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public PageInfo<FeedRecordExtend> query(int page, int pageSize, String frBatchId, String feedName) {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(page,pageSize);
        List<FeedRecordExtend> list=feedRecordExtendMapper.query(frBatchId,feedName);
        PageInfo<FeedRecordExtend> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
}
