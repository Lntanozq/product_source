package com.briup.framework.service.impl;

import com.briup.framework.mapper.extend.ManagerAnimalExtendMapper;
import com.briup.framework.mapper.extend.StatisticAnalysisExtendMapper;
import com.briup.framework.service.IStatisticAnalysisService;
import com.briup.framework.utils.execption.BriupFrameworkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @program: product_source
 * @description: 统计分析
 * @author: nirui
 * @create: 2022-04-11 17:29
 */
@Service
public class StatisticAnalysisServiceImpl implements IStatisticAnalysisService {
   @Autowired
   private StatisticAnalysisExtendMapper statisticAnalysisExtendMapper;
   @Autowired
   private ManagerAnimalExtendMapper managerAnimalExtendMapper;
    @Override
    public Map<String, List<Object>> count() {
        Map<String, List<Object>> map = new HashMap<>();
        List<Object> keys = Arrays.asList("栏舍","栏圈","动物","冷库","员工");
        List<Object> values = new ArrayList<>();
        //栏舍数量
        Integer fenceHouses = statisticAnalysisExtendMapper.countFenceHouse();
        values.add(fenceHouses);
        //栏圈数量
        Integer hurdles = statisticAnalysisExtendMapper.countHurdles();
        values.add(hurdles);
        //动物数量
        Integer animals = statisticAnalysisExtendMapper.countAnimal();
        values.add(animals);
        //冷库数量
        Integer freezers = statisticAnalysisExtendMapper.countFreezer();
        values.add(freezers);
        //员工数量
        Integer accounts = statisticAnalysisExtendMapper.countAccount();
        values.add(accounts);
        map.put("name",keys);
        map.put("value",values);
        return map;
    }

    @Override
    public Map<String, List<Object>> countSales(String year) {
        Map<String, List<Object>> map= new HashMap<>();
        List<Map<String, Object>> lists = statisticAnalysisExtendMapper.countIssueRecord(year);
        List<Object> keys = Arrays.asList("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
        List<Object> values = Arrays.asList(0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L,0L);
        lists.forEach(e->{
            Integer mon  = (Integer)(e.get("mon"));
            Long num = (Long)(e.get("num"));
            if(keys.contains(mon+"月")){
                values.set(mon-1,num);
            }
        });
        map.put("name",keys);
        map.put("value",values);
        return map;
    }

    @Override
    public Map<String, Integer> countWeight() {
        int count1 = managerAnimalExtendMapper.selectByWeightCount1();
        int count2 = managerAnimalExtendMapper.selectByWeightCount2();
        int count3 = managerAnimalExtendMapper.selectByWeightCount3();
        Map<String,Integer> map=new HashMap<>();
        map.put("30以下",count1);
        map.put("30~50",count2);
        map.put("50以上",count3);
        return map;
    }

    @Override
    public Map<String, Integer> countDisease() {
        List<Map<String, Object>> list = statisticAnalysisExtendMapper.countDiseaseRecord();
        if(CollectionUtils.isEmpty(list))
            return null;

        Map<String,Integer> map = new HashMap<>();
        for (Map<String, Object> m : list) {
            map.put(m.get("dname").toString(),Integer.parseInt(m.get("num").toString()));
        }

        return map;
    }
}
