package com.briup.framework.mapper;

import com.briup.framework.bean.DiseaseRecord;

public interface DiseaseRecordMapper {
    int deleteByPrimaryKey(Integer drId);

    int insert(DiseaseRecord record);

    int insertSelective(DiseaseRecord record);

    DiseaseRecord selectByPrimaryKey(Integer drId);

    int updateByPrimaryKeySelective(DiseaseRecord record);

    int updateByPrimaryKey(DiseaseRecord record);
}