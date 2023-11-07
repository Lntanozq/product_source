package com.briup.framework.mapper.extend;

import com.briup.framework.bean.extend.FeedRecordExtend;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pgc
 * @Description: 饲料更换mapper
 * @date 2022/4/2 9:42
 */
public interface FeedRecordExtendMapper {
    //多条件分页查询
    List<FeedRecordExtend> query(@Param("frBatchId")String frBatchId, @Param("feedName")String feedName);
}
