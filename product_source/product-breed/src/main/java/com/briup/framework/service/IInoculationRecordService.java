package com.briup.framework.service;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.InoculationRecord;
import com.briup.framework.bean.extend.InoculationRecordExtend;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
    接种接口
 */
public interface IInoculationRecordService {

    //新增或更新 接种记录
    void saveOrUpdate(InoculationRecord inoculationRecord);

    //根据ids删除 接种记录
    void deleteById(Integer irId);

    //批量删除 接种记录
    void deleteAllByIds(List<Integer> irIds);

    //查询所有 接种记录(含疫苗信息、接种人员信息)
    List<InoculationRecordExtend> queryAllRecordWithVaccinesAndUinoculationAccount();

    //分页查询
    PageInfo<InoculationRecordExtend> query(Integer page, Integer pageSize, String irAnimalId, String irVaccinesId, String irUinoculationId, String startTime, String endTime);

    //查询所有 已经参与接种的人员 信息
    List<BaseAccount> queryAllUinoculationAccount();
}
