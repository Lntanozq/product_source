package com.briup.framework.mapper.extend;

import com.briup.framework.bean.ShiftCircle;
import com.briup.framework.bean.extend.ShiftCircleExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 移圈记录管理
 */
public interface ShiftCircleExtendMapper {
    //批量删除移圈记录
    void deleteInBatch(@Param("ids") List<Integer> ids);
    //根据id删除移圈记录
    void deleteById(Integer id);
    //查询所有移圈记录
    List<ShiftCircle> queryAll();
    //条件查询移圈记录
    List<ShiftCircleExtend> query(@Param("animalId") String animalId,@Param("beedId") String ubeedId,
                                  @Param("start") String start,@Param("end") String end,
                                  @Param("reason") String reason);
    //查询所有移圈人员
    List<ShiftCircleExtend> queryAllUser();
}
