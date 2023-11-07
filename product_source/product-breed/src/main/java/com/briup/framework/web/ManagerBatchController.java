package com.briup.framework.web;

import com.briup.framework.bean.ManagerBatch;
import com.briup.framework.bean.extend.ManagerBatchExtend;
import com.briup.framework.service.IManagerBatchService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @program: product_source
 * @description: 批次管理
 * @author: nirui
 * @create: 2022-03-28 14:14
 */
@Api(tags = "批次管理接口")
@RestController
@RequestMapping("/batch")
public class ManagerBatchController {
    @Autowired
    private IManagerBatchService managerBatchService;

    @Permission(value="batch_saveOrUpdate")
    @ApiOperation(value = "保存或更新批次",notes = "批次编号存在则更新，不存在则保存")
    @PostMapping("/saveOrUpdate")
    public Response saveOrUpdate(@RequestBody ManagerBatch managerBatch){
        managerBatchService.saveOrUpdate(managerBatch);
        return Response.success();
    }
    @Permission(value="batch_deleteById")
    @ApiOperation(value = "根据id删除批次")
    @DeleteMapping("/deleteById/{bSerialId}")
    public Response deleteById(@PathVariable("bSerialId") String bSerialId) {
        managerBatchService.deleteById(bSerialId);
        return Response.success();
    }
    @Permission(value="batch_deleteByIdAll")
    @ApiOperation(value = "批量删除批次")
    @DeleteMapping("/deleteByIdAll")
    public Response deleteByIdAll(@RequestBody List<String> bSerialIds) {
        managerBatchService.deleteInBatch(bSerialIds);
        return Response.success();
    }

    @Permission(value="batch_query")
    @ApiOperation(value = "分页并根据条件查询批次基本信息以及动物数量/状态")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true,defaultValue = "5"),
            @ApiImplicitParam(name = "bSerialId", value = "批次编号", paramType = "query"),
            @ApiImplicitParam(name = "startTime",value = "开始时间",paramType = "query"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",paramType = "query"),
            @ApiImplicitParam(name = "bQuarantine",value = "检疫状态",paramType = "query"),
            @ApiImplicitParam(name = "bQualified",value ="合格状态",paramType = "query")
    })
    public Response query(Integer page,Integer pageSize,String bSerialId,String startTime,String endTime,String bQuarantine,String bQualified){
        PageInfo<ManagerBatchExtend> info = managerBatchService.query(page, pageSize, bSerialId, startTime, endTime, bQuarantine, bQualified);
        return Response.success(info);
    }
    @Permission(value="batch_queryAll")
    @ApiOperation(value = "查询所有批次信息")
    @GetMapping("/queryAll")
    public Response queryAll() {
        List<ManagerBatch> list = managerBatchService.queryAll();
        return Response.success(list);
    }

    @Permission(value="batch_selectById")
    @ApiOperation(value = "根据批次编号查询批次")
    @GetMapping("/selectById/{bSerialId}")
    public Response selectById(@PathVariable("bSerialId")  String bSerialId) {
        ManagerBatchExtend managerBatchExtend = managerBatchService.selectById(bSerialId);
        return Response.success(managerBatchExtend);
    }



}
