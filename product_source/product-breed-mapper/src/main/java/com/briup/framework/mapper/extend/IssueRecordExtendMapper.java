package com.briup.framework.mapper.extend;

import com.briup.framework.bean.IssueRecord;
import com.briup.framework.bean.extend.IssueRecordExtend;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 出单记录管理
 */
public interface IssueRecordExtendMapper {
    //批量删除出单记录
    void deleteInBatch(@Param("ids") List<Integer> isrIds);
    //根据出单记录id单个删除出单记录
    void deleteById(Integer isrId);
    //查询所有出单记录
    List<IssueRecord> queryAll();
    //根据销售人员id，状态,出单时间段(时间字符串1、时间字符串2)，交付时间段(时间字符串1、时间字符串2)查询出单信息
    List<IssueRecordExtend> query(@Param("isrSaleId") String isrSaleId, @Param("isrStatus") String isrStatus,
                                  @Param("startTime") String startTime, @Param("endTime") String endTime,
                                  @Param("startDeliver") String startDeliver, @Param("endDeliver") String endDeliver);
    //查询所有销售人员
    List<IssueRecordExtend> queryAllUser();
    //根据出单记录id更新状态
    void updateStatus(@Param("isrId") Integer isrId,@Param("isrStatus") String isrStatus);
    //根据订单id查询出单记录
    List<IssueRecordExtend> selectById(Integer isrId);

}
