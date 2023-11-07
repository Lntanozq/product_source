package com.briup.framework.web;

import com.briup.framework.bean.SituationReporting;
import com.briup.framework.bean.extend.SituationReportingExtend;
import com.briup.framework.service.ISituationReportingService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author pgc
 * @Description: 上报情况控制层
 * @date 2022/3/31 17:25
 */
@Api(tags = "上报情况接口")
@RestController
@RequestMapping("/situationReporting")
public class SituationReportingController {
    @Autowired
    ISituationReportingService situationReportingService;
    @Permission(value="situationReporting_saveOrUpdate")
    @ApiOperation(value = "保存或更新上报情况")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody SituationReporting situationReporting){
        situationReportingService.saveOrUpdate(situationReporting);
        return Response.success("操作成功");
    }
    @Permission(value="situationReporting_deleteById")
    @ApiOperation(value = "根据id删除上报情况")
    @DeleteMapping("/deleteById/{srId}")
    public Response<String> deleteById(@PathVariable int srId) {
        situationReportingService.deleteById(srId);
        return Response.success("删除成功");
    }
    @Permission(value="situationReporting_deleteByIdAll")
    @ApiOperation(value = "批量删除上报情况")
    @PostMapping("/deleteByIdAll")
    public Response<String> deleteByIdAll(@RequestBody int[] srIds) {
        for (int srId:srIds) {
            situationReportingService.deleteById(srId);
        }
        return Response.success("删除成功");
    }
    @Permission(value="situationReporting_query")
    @ApiOperation(value = "分页并根据条件查询特殊情况基本信息以及负责人上报人员和医护人员信息")
    @GetMapping("/query")
    @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true, paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true, paramType = "query",defaultValue ="5"),
            @ApiImplicitParam(name = "srAnimalId", value = "动物编号", paramType = "query"),
            @ApiImplicitParam(name = "srStatus", value = "处理状态", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间",paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name = "srUbreedId", value = "上报人员",paramType = "query"),
            @ApiImplicitParam(name = "srUdockerId", value = "处理人员", paramType = "query")
    })
    public Response<PageInfo<SituationReportingExtend>> query(int page, int pageSize, String srAnimalId, String srStatus,
                                                              String startTime, String endTime,
                                                              String srUbreedId, String srUdockerId){
        PageInfo<SituationReportingExtend> pageVM=situationReportingService.query(page,pageSize,srAnimalId,srStatus,startTime,endTime,srUbreedId,srUdockerId);
        return Response.success(pageVM);
    }
    @Permission(value="situationReporting_updateStatus")
    @ApiOperation(value = "更新特殊情况状态")
    @PutMapping("/updateStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "srId", value = "情况上报id",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "srAnimalId", value = "动物编号", paramType = "query"),
            @ApiImplicitParam(name = "srStatus", value = "处理状态", paramType = "query"),
            @ApiImplicitParam(name = "srUdockerId", value = "医生编号", paramType = "query")
    })
    public Response<String> updateStatus(int srId,String srAnimalId, String srStatus,String srUdockerId){
        situationReportingService.updateStatus(srId,srAnimalId,srStatus,srUdockerId);
        return Response.success("更新成功");
    }
}
