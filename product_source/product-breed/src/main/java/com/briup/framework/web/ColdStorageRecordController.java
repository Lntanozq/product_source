package com.briup.framework.web;

import com.briup.framework.bean.extend.AnimalRecord;
import com.briup.framework.bean.extend.ColdStorageRecordExtend;
import com.briup.framework.bean.extend.ManagerBatchAnimalExtend;
import com.briup.framework.bean.extend.OutStorageRecord;
import com.briup.framework.service.IColdStorageRecordService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @Description: 入库出库登记控制层
 * @date 2022/3/31 16:19
 */
@Api(tags = "入库出库登记接口")
@RestController
@RequestMapping("/coldStorageRecord")
public class ColdStorageRecordController {
    @Autowired
    IColdStorageRecordService coldStorageRecordService;
    @Permission(value="coldStorageRecord_query")
    @ApiOperation(value = "根据动物过程状态查询动物基本信息以及对应批次信息,状态填已屠宰")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aStatus", value = "动物过程状态", required = true,paramType = "query")
    })
    public Response<List<ManagerBatchAnimalExtend>> query(String aStatus){
        List<ManagerBatchAnimalExtend> list=coldStorageRecordService.query(aStatus);
        return Response.success(list);
    }
    @Permission(value="coldStorageRecord_saveOrUpdate")
    @ApiOperation(value = "插入入库登记信息")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody AnimalRecord animalRecord){
        coldStorageRecordService.saveOrUpdate(animalRecord);
        return Response.success("操作成功");
    }
    @Permission(value="coldStorageRecord_updateStorageRecord")
    @ApiOperation(value = "更新出库登记信息")
    @PostMapping("/updateStorageRecord")
    public Response<String> updateStorageRecord(@RequestBody OutStorageRecord outStorageRecord){
        coldStorageRecordService.updateStorageRecord(outStorageRecord);
        return Response.success("更新成功");
    }
    @Permission(value="coldStorageRecord_queryAll")
    @ApiOperation(value = "获取冷库以及冷库中动物信息")
    @PostMapping("/queryAll")
    public Response<List<ColdStorageRecordExtend>> queryAll(){
        List<ColdStorageRecordExtend> list = coldStorageRecordService.queryAll();
        return Response.success(list);
    }
}
