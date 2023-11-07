package com.briup.framework.service;

import java.util.List;
import java.util.Map;

/**
 * 统计分析
 */
public interface IStatisticAnalysisService {
    //统计栏舍数量，栏圈数量，动物数量，冷库数量，员工数量
    Map<String,List<Object>> count();
    //统计某年获取1~12月的销售数量
    Map<String,List<Object>> countSales(String year);
    //指标记录更改动物体重，根据动物表的批次获取对应体重的猪的数量，获取例如0-30 猪的数量，30-50斤的猪数量，50斤以上的数量
    Map<String,Integer> countWeight();
    //病症统计
    Map<String, Integer> countDisease();
}
