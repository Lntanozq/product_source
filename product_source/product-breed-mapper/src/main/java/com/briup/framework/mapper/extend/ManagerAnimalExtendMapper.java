package com.briup.framework.mapper.extend;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.extend.ManagerAnimalExtend;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerAnimalExtendMapper {
    //逻辑删除栏舍
    void updateById(String aId);
    //多条件分页查询
    List<ManagerAnimalExtend> query(@Param("batchNumber")String batchNumber,
                                    @Param("hurdlesNumber")String hurdlesNumber,
                                    @Param("aHealthy")String aHealthy,
                                    @Param("aGender")String aGender);
   //根据批次更新过程状态
    void updateStatus(@Param("batchId") String batchId,@Param("status") String status);
    //根据动物的编号更新栏圈
    void updateHurdlesId(@Param("animalId") String animalId,@Param("hurdlesId") String hurdlesId);
    //根据动物编号查询动物信息
    ManagerAnimal selectById(String aId);
    //获取动物详情
    ManagerAnimalExtend findByAnimalId(String animalId);
    //获取动物体重范围数据
    int selectByWeightCount1();
    int selectByWeightCount2();
    int selectByWeightCount3();
}
