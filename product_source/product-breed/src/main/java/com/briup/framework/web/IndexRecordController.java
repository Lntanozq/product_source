package com.briup.framework.web;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.IndexRecord;
import com.briup.framework.bean.extend.IndexRecordExtend;
import com.briup.framework.service.IIndexRecordService;
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
 * @author pgc
 * @Description: 指标记录控制层
 * @date 2022/3/30 11:18
 */
@Api(tags = "指标记录接口")
@RestController
@RequestMapping("/indexRecord")
public class IndexRecordController {
    @Autowired
    IIndexRecordService indexRecordService;

    @Permission(value="indexRecord_saveOrUpdate")
    @ApiOperation(value = "保存或更新指标记录")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody IndexRecord indexRecord){
        indexRecordService.saveOrUpdate(indexRecord);
        return Response.success("操作成功");
    }
    @Permission(value="indexRecord_selectAllAccount")
    @ApiOperation(value = "查询检测人员信息")
    @GetMapping("/selectAllAccount")
    public Response<List<BaseAccount>> selectAllAccount() {
        List<BaseAccount> list =indexRecordService.queryTesting();
        return Response.success(list);
    }
    @Permission(value="indexRecord_deleteById")
    @ApiOperation(value = "根据id删除指标记录")
    @DeleteMapping("/deleteById/{irdId}")
    public Response<String> deleteById(@PathVariable int irdId) {
        indexRecordService.deleteById(irdId);
        return Response.success("删除成功");
    }
    @Permission(value="indexRecord_deleteByIdAll")
    @ApiOperation(value = "批量删除指标记录")
    @PostMapping("/deleteByIdAll")
    public Response<String> deleteByIdAll(@RequestBody int[] irIds) {
        for (int irId:irIds) {
         indexRecordService.deleteById(irId);
        }
        return Response.success("删除成功");
    }
    @Permission(value="indexRecord_query")
    @ApiOperation(value = "分页并根据条件查询指标记录基本信息以及负责人信息")
    @GetMapping("/query")
    @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true, paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true, paramType = "query",defaultValue ="5"),
            @ApiImplicitParam(name = "irdAnimalId", value = "动物编号", paramType = "query"),
            @ApiImplicitParam(name = "healthy", value = "是否健康", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间",paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name = "startHeartRate", value = "最低心率", paramType = "query"),
            @ApiImplicitParam(name = "endHeartRate", value = "最高心率", paramType = "query"),
            @ApiImplicitParam(name = "startBreathing", value = "最低频率", paramType = "query"),
            @ApiImplicitParam(name = "endBreathing", value = "最高频率", paramType = "query"),
            @ApiImplicitParam(name = "startWeight", value = "最低体重", paramType = "query"),
            @ApiImplicitParam(name = "endWeight", value = "最高体重", paramType = "query"),
            @ApiImplicitParam(name = "irdUbreedId", value = "检测人员编号", paramType = "query")
    })
    public Response<PageInfo<IndexRecordExtend>> query(int page, int pageSize, String irdAnimalId, String healthy,
                                                       String startTime, String endTime,
                                                       String startHeartRate, String endHeartRate,
                                                       String startBreathing, String endBreathing,
                                                       String startWeight, String endWeight,
                                                       String irdUbreedId){
        PageInfo<IndexRecordExtend> pageVM=indexRecordService.query(page,pageSize,irdAnimalId,healthy,startTime,endTime,startHeartRate,
                                                                endHeartRate,startBreathing,endBreathing,startWeight,endWeight,irdUbreedId);
        return Response.success(pageVM);
    }
}
