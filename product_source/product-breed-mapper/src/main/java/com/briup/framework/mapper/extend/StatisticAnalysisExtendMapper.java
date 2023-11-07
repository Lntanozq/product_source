package com.briup.framework.mapper.extend;


import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * 统计分析
 */
public interface StatisticAnalysisExtendMapper {
    // 统计栏舍数量
    Integer countFenceHouse();
    // 统计栏圈数量
    Integer countHurdles();
    // 统计动物数量
    Integer countAnimal();
    // 统计冷库数量
    Integer countFreezer();
    // 统计员工数量
    Integer countAccount();
    // 统计指定年份的销量
    List<Map<String,Object>> countIssueRecord(String year);
    // 统计病症记录中 各病症出现的次数
    List<Map<String,Object>> countDiseaseRecord();
}
