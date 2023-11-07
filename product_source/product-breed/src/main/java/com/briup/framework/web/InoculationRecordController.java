package com.briup.framework.web;


import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.InoculationRecord;
import com.briup.framework.bean.ShiftCircle;
import com.briup.framework.bean.extend.InoculationRecordExtend;
import com.briup.framework.bean.extend.ShiftCircleExtend;
import com.briup.framework.service.IInoculationRecordService;
import com.briup.framework.service.IShiftCircleService;
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
 * @description: 接种记录管理
 * @author: shaoyb
 * @create: 2022-03-31 14:53
 */
@Api(tags = "接种记录接口")
@RestController
@RequestMapping("/inoculationRecord")
public class InoculationRecordController {
    @Autowired
    private IInoculationRecordService inoculationRecordService;

    @ApiOperation(value = "保存或更新接种记录",notes = "接种id存在则更新，不存在则保存")
    @Permission(value="inoculationRecord_saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    public Response saveOrUpdate(@RequestBody InoculationRecord inoculationRecord){
        inoculationRecordService.saveOrUpdate(inoculationRecord);
        return Response.success();
    }

    @ApiOperation(value = "根据id删除接种记录")
    @Permission(value="inoculationRecord_deleteById")
    @DeleteMapping("/deleteById/{irId}")
    public Response deleteById(@PathVariable("irId") Integer irId) {
        inoculationRecordService.deleteById(irId);
        return Response.success();
    }

    @ApiOperation(value = "批量删除接种记录")
    @Permission(value="inoculationRecord_deleteByIdAll")
    @DeleteMapping("/deleteByIdAll")
    public Response deleteAllByIds(@RequestBody List<Integer> irIds) {
        inoculationRecordService.deleteAllByIds(irIds);
        return Response.success();
    }

    @ApiOperation(value = "查询所有接种记录",notes = "记录(含疫苗信息、接种人员信息)")
    @Permission(value="inoculationRecord_queryAll")
    @GetMapping("/queryAll")
    public Response queryAll() {
        //查询所有接种记录
        List<InoculationRecordExtend> list = inoculationRecordService.queryAllRecordWithVaccinesAndUinoculationAccount();
        return Response.success(list);
    }

    @ApiOperation(value = "分页并根据条件查询接种记录")
    @Permission(value="inoculationRecord_query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "int", required = true, defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int", required = true, defaultValue = "5"),
            @ApiImplicitParam(name = "irAnimalId",value = "动物id",paramType = "query"),
            @ApiImplicitParam(name = "irVaccinesId",value = "疫苗id", paramType = "query"),
            @ApiImplicitParam(name = "irUinoculationId",value = "接种人员id", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间",paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query")
    })
    @GetMapping("/query")
    public Response query(Integer page,Integer pageSize,String irAnimalId,String irVaccinesId,String irUinoculationId,String startTime,String endTime){
        PageInfo<InoculationRecordExtend> info = inoculationRecordService.query(page, pageSize, irAnimalId, irVaccinesId, irUinoculationId, startTime,endTime);
        return Response.success(info);
    }

    @ApiOperation(value = "查询所有已参与接种人员信息")
    @Permission(value="inoculationRecord_selectAllAccount")
    @GetMapping("/selectAllAccount")
    public Response<List<BaseAccount>> selectAllAccount() {
        List<BaseAccount> list =inoculationRecordService.queryAllUinoculationAccount();
        return Response.success(list);
    }
}
