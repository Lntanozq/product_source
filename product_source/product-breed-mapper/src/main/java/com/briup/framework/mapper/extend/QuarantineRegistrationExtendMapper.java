package com.briup.framework.mapper.extend;

import com.briup.framework.bean.QuarantineRegistration;
import com.briup.framework.bean.extend.QuarantineRegistrationExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 检疫登记管理
 */
public interface QuarantineRegistrationExtendMapper {
    //批量删除检疫登记
    void deleteInBatch(@Param("ids") List<Integer> ids);
    //根据id删除检疫登记
    void deleteById(Integer id);
    //查询所有检疫登记
    List<QuarantineRegistration> queryAll();
    //条件查询检疫登记
    List<QuarantineRegistrationExtend> query(@Param("grBatchId") String grBatchId,@Param("grMechanism") String grMechanism,
                                             @Param("bQualified") String bQualified,@Param("grUquarantinerId") String grUquarantinerId,
                                             @Param("start") String startTime,@Param("end") String endTime);
    //查询所有检疫人员
    List<QuarantineRegistrationExtend> queryAllUser();
}
