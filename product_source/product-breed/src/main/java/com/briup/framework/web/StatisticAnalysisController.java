package com.briup.framework.web;

import com.briup.framework.service.IStatisticAnalysisService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @program: product_source
 * @description: 统计分析
 * @author: nirui
 * @create: 2022-04-11 18:41
 */
@RequestMapping("/analysis")
@RestController
@Api(tags = "统计分析接口")
public class StatisticAnalysisController {
    @Autowired
    private IStatisticAnalysisService statisticAnalysisService;

    @Permission(value="analysis_count")
    @ApiOperation(value = "展示栏舍数量，栏圈数量，动物数量，冷库数量，员工数量")
    @GetMapping("/count")
    public Response count(){
        Map<String, List<Object>> map = statisticAnalysisService.count();
        return Response.success(map);
    }

    @Permission(value="analysis_countSales")
    @ApiOperation(value = "根据年获取1~12月的销售数量")
    @ApiImplicitParam(name = "year",value = "年份",required = true)
    @GetMapping("/countSales")
    public Response countSales(@RequestParam String year){
        Map<String, List<Object>> map = statisticAnalysisService.countSales(year);
        return Response.success(map);
    }

    @Permission(value="analysis_indexCount")
    @ApiOperation(value = "获取例如0-30 猪的数量，30-50斤的猪数量，50斤以上的数量")
    @GetMapping("/indexCount")
    public Response indexCount(){
        Map<String,Integer> map = statisticAnalysisService.countWeight();
        return Response.success(map);
    }

    @Permission(value="analysis_countDisease")
    @ApiOperation(value = "统计病症出现情况")
    @GetMapping("/countDisease")
    public Response countDisease(){
        Map<String,Integer> map = statisticAnalysisService.countDisease();
        return Response.success(map);
    }
}
