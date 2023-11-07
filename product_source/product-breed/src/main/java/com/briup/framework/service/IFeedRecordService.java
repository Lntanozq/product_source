package com.briup.framework.service;

import com.briup.framework.bean.FeedRecord;
import com.briup.framework.bean.extend.FeedRecordExtend;
import com.github.pagehelper.PageInfo;

/**
 * @author pgc
 * @Description: 饲料更换
 * @date 2022/4/2 9:29
 */
public interface IFeedRecordService {
     //插入或更新
    void saveOrUpdate(FeedRecord feedRecord);
    //根据id删除信息
    void deleteById(int fId);
    //多条件分页查询
    PageInfo<FeedRecordExtend> query(int page, int pageSize,
                                     String frBatchId, String feedName);
}
