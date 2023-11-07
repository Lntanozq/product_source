package com.briup.framework.mapper.extend;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.extend.InoculationRecordExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
    接种记录dao层扩展接口
 */
public interface InoculationRecordExtendMapper {
    //根据irIds判断 有效记录条数
    //int getCountByIds(List<Integer> irIds);

    //获取所有相关动物id，根据irIds
    List<String> getAllAnimalIdByIrIds(@Param("irIds") List<Integer> irIds);

    //根据irIds 删除接种表中相关记录
    int deleteAllByIds(@Param("irIds") List<Integer> irIds);

    //查询所有 接种记录(含疫苗 接种人员信息)
    List<InoculationRecordExtend> selectAllWithVaccinesAndUAccount();

    //查询 记录表中 所有已参与接种的人员
    List<BaseAccount> selectAllUAccounts();

    //分页查询
    List<InoculationRecordExtend> selectWithExtend(@Param("irAnimalId") String irAnimalId,
                                                   @Param("irVaccinesId") String irVaccinesId, @Param("irUinoculationId") String irUinoculationId,
                                                   @Param("startTime") String startTime,@Param("endTime") String endTime);
}
