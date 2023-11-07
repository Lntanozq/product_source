package com.briup.framework.mapper;

import com.briup.framework.bean.SituationReporting;

public interface SituationReportingMapper {
    int deleteByPrimaryKey(Integer srId);

    int insert(SituationReporting record);

    int insertSelective(SituationReporting record);

    SituationReporting selectByPrimaryKey(Integer srId);

    int updateByPrimaryKeySelective(SituationReporting record);

    int updateByPrimaryKey(SituationReporting record);
}