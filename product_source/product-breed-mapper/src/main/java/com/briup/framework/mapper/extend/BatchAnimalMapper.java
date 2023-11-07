package com.briup.framework.mapper.extend;

import com.briup.framework.bean.extend.ManagerBatchAnimalExtend;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pgc
 * @Description: 提供动物状态获取批次以及对应动物信息，以及业务返回数据
 * @date 2022/3/30 15:18
 */
public interface BatchAnimalMapper {
    //根据动物过程状态获取对应批次以及动物信息
    List<ManagerBatchAnimalExtend> query(String aStatus);
    //插入出栏信息
    void insertOutCircle(@Param("ocBatchId")String ocBatchId,
                         @Param("ocUbreedId")String ocUbreedId,
                         @Param("ocTime")String ocTime);
    //插入屠宰信息
    void insertSlaughterRegistration(@Param("srnBatchId")String srnBatchId,
                         @Param("srnGroupId")String srnGroupId,
                         @Param("srnTime")String srnTime);
    //插入入库信息
    void insertColdStorageRecord(@Param("csrColdId")String csrColdId,
                         @Param("csrAnimalId")String csrAnimalId,
                         @Param("csrIntoTime")String csrIntoTime,
                         @Param("csrUcoldId")String csrUcoldId);
    //更新出库信息
    void updateColdStorageRecord(@Param("animalIds")List<String> animalIds,
                                 @Param("csrOutTime")String csrOutTime);
    //更新动物过程状态
   void updateAnimalStatus(@Param("animalIds")List<String> animalIds,
                           @Param("status")String status,@Param("isrId")String isrId);
    //更新动物接种状态
    void updateAnimalInoculate(@Param("animalIds")List<String> animalIds,
                            @Param("aInoculate")String aInoculate);
    //更新动物健康状态
    void updateAnimalHealthy(@Param("animalId")String animalId,
                               @Param("aHealthy")String aHealthy);
    //更新动物二维码
    void updateAnimalImg(@Param("animalId")String animalId,
                             @Param("aBackup3")String aBackup3);
    //更新动物体重
    void updateAnimaWeight(@Param("animalId")String animalId,
                         @Param("aWeight")String aWeight);
    //根据动物id获取冷库id
    int selectByAId(String aId);
}
