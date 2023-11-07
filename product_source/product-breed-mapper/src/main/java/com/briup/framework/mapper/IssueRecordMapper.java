package com.briup.framework.mapper;

import com.briup.framework.bean.IssueRecord;

public interface IssueRecordMapper {
    int deleteByPrimaryKey(Integer isrId);

    int insert(IssueRecord record);

    int insertSelective(IssueRecord record);

    IssueRecord selectByPrimaryKey(Integer isrId);

    int updateByPrimaryKeySelective(IssueRecord record);

    int updateByPrimaryKey(IssueRecord record);
}