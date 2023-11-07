package com.briup.framework.web;

import com.briup.framework.bean.IssueRecord;
import com.briup.framework.bean.extend.IssueRecordExtend;
import com.briup.framework.service.IIssueRecordService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: product_source
 * @description: 出单记录管理
 * @author: nirui
 * @create: 2022-03-31 18:10
 */
@Api(tags = "出单记录管理")
@RestController
@RequestMapping("/issueRecord")
public class IssueRecordController {
    @Autowired
    private IIssueRecordService issueRecordService;

    @Permission(value="issueRecord_saveOrUpdate")
    @ApiOperation(value = "保存或更新出单记录",notes = "出单记录id存在则更新，不存在则保存")
    @PostMapping("/saveOrUpdate")
    public Response saveOrUpdate(@RequestBody IssueRecord issueRecord){
        issueRecordService.saveOrUpdate(issueRecord);
        return Response.success();
    }

    @Permission(value="issueRecord_deleteByIdAll")
    @ApiOperation(value = "批量删除出单记录")
    @DeleteMapping("/deleteByIdAll")
    public Response deleteByIdAll(@RequestBody List<Integer> isrIds) {
        issueRecordService.deleteInBatch(isrIds);
        return Response.success();
    }

    @Permission(value="issueRecord_deleteById")
    @ApiOperation(value = "根据id删除出单记录")
    @DeleteMapping("/deleteById/{isrId}")
    public Response deleteById(@PathVariable("isrId") Integer isrId) {
        issueRecordService.deleteById(isrId);
        return Response.success();
    }

    @Permission(value="issueRecord_queryAll")
    @ApiOperation(value = "查询所有出单记录")
    @GetMapping("/queryAll")
    public Response queryAll() {
        List<IssueRecord> list = issueRecordService.queryAll();
        return Response.success(list);
    }

    @Permission(value="issueRecord_query")
    @ApiOperation(value = "分页并根据条件查询出单记录以及销售人员")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true,defaultValue = "5"),
            @ApiImplicitParam(name = "isrSaleId", value = "销售人员id", paramType = "query"),
            @ApiImplicitParam(name = "isrStatus",value = "状态",paramType = "query"),
            @ApiImplicitParam(name = "startTime",value = "出单开始时间",paramType = "query"),
            @ApiImplicitParam(name = "endTime",value = "出单结束时间",paramType = "query"),
            @ApiImplicitParam(name = "startDeliver",value = "交付开始时间",paramType = "query"),
            @ApiImplicitParam(name = "endDeliver",value = "交付结束时间",paramType = "query")
    })
    public Response query(Integer page,Integer pageSize,String isrSaleId,String isrStatus,String startTime,String endTime,String startDeliver,String endDeliver){
        PageInfo<IssueRecordExtend> info = issueRecordService.query(page, pageSize, isrSaleId, isrStatus, startTime, endTime, startDeliver, endDeliver);
        return Response.success(info);
    }

    @Permission(value="issueRecord_queryAllUser")
    @ApiOperation(value = "查询所有销售人员信息")
    @GetMapping("/queryAllUser")
    public Response queryAllUser() {
        List<IssueRecordExtend> list = issueRecordService.queryAllUser();
        return Response.success(list);
    }

    @Permission(value="issueRecord_updateStatusById")
    @ApiOperation(value = "根据出单记录id更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isrId",value = "出单记录id",paramType = "query",required = true),
            @ApiImplicitParam(name = "isrStatus",value = "状态",paramType = "query",required = true)
    })
    @PutMapping("/updateStatusById")
    public Response updateStatus(Integer isrId,String isrStatus){
        issueRecordService.updateStatus(isrId, isrStatus);
        return Response.success();
    }

    @Permission(value="issueRecord_selectById")
    @ApiOperation(value = "根据出单id查询出单信息")
    @GetMapping("/selectById/{isrId}")
    public Response selectById(@PathVariable("isrId")  Integer isrId) {
        List<IssueRecordExtend> list = issueRecordService.selectById(isrId);
        return Response.success(list);
    }
}
