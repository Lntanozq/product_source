package com.briup.framework.web;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.DiseaseRecord;
import com.briup.framework.bean.ManagerDisease;
import com.briup.framework.bean.extend.DiseaseRecordExtend;
import com.briup.framework.service.IDiseaseRecordService;
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
 * @author shaoyb
 * @program: product_source
 * @description 病症记录
 * @create 2022/4/8 9:25
 **/
@Api(tags = "病症记录接口")
@RestController
@RequestMapping("/diseaseRecord")
public class DiseaseRecordController {
    @Autowired
    private IDiseaseRecordService iDiseaseRecordService;

    @ApiOperation(value = "新增或更新病症记录")
    @Permission(value="diseaseRecord_saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    public Response saveOrUpdate(@RequestBody DiseaseRecord record) {
        iDiseaseRecordService.saveOrUpdate(record);
        return Response.success();
    }

    @ApiOperation(value = "删除指定病症记录")
    @Permission(value="diseaseRecord_deleteById")
    @DeleteMapping("/deleteById/{drId}")
    public Response deleteById(@PathVariable Integer drId) {
        iDiseaseRecordService.deleteRecodeById(drId);
        return Response.success();
    }

    @ApiOperation(value = "删除所有病症记录")
    @Permission(value="diseaseRecord_deleteByIdAll")
    @DeleteMapping("/deleteByIdAll")
    public Response deleteAllRecodeByIds(@RequestBody List<Integer> drIds){
        iDiseaseRecordService.deleteAllRecodeByIds(drIds);
        return Response.success();
    }

    @ApiOperation(value = "查询所有病症记录(含病症信息 医护人员信息)")
    @Permission(value="diseaseRecord_queryAll")
    @GetMapping("/queryAll")
    public Response<List<DiseaseRecordExtend>> queryAll() {
        List<DiseaseRecordExtend> list = iDiseaseRecordService.queryAllRecord();
        return Response.success(list);
    }

    @ApiOperation(value = "分页按条件查询病症记录")
    @Permission(value="diseaseRecord_query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "int", required = true, defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int", required = true, defaultValue = "5"),
            @ApiImplicitParam(name = "drAnimalId", value = "动物id", paramType = "query"),
            @ApiImplicitParam(name = "drStatus", value = "治疗状态(未治疗|治疗中|已治疗)", paramType = "query"),
            @ApiImplicitParam(name = "drDId", value = "病症id", paramType = "query"),
            @ApiImplicitParam(name = "drUdockerId", value = "医护人员id", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query")
    })
    @GetMapping("/query")
    public Response query(Integer page, Integer pageSize, String drAnimalId, String drStatus, Integer drDId, String drUdockerId, String startTime, String endTime) {
        PageInfo<DiseaseRecordExtend> info = iDiseaseRecordService.query(page,pageSize,drAnimalId,drStatus,drDId,drUdockerId,startTime,endTime);
        return Response.success(info);
    }

    @ApiOperation(value = "查询所有病症信息")
    @Permission(value="diseaseRecord_queryAllDisease")
    @GetMapping("/queryAllDisease")
    public Response<List<ManagerDisease>> queryAllDisease() {
        List<ManagerDisease> list = iDiseaseRecordService.queryAllDisease();
        return Response.success(list);
    }

    //获取所有医护人员
    @ApiOperation(value = "查询所有医护人员信息")
    @Permission(value="diseaseRecord_queryAllUser")
    @GetMapping("/queryAllUser")
    public Response<List<BaseAccount>> queryAllUser() {
        List<BaseAccount> list = iDiseaseRecordService.queryAllMedicalAccount();
        return Response.success(list);
    }

}
