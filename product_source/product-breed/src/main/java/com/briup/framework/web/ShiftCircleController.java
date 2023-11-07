package com.briup.framework.web;


import com.briup.framework.bean.ShiftCircle;
import com.briup.framework.bean.extend.ShiftCircleExtend;
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
 * @description: 移圈记录管理
 * @author: nirui
 * @create: 2022-03-30 16:24
 */
@Api(tags = "移圈记录接口")
@RestController
@RequestMapping("/shiftCircle")
public class ShiftCircleController {
    @Autowired
    private IShiftCircleService shiftCircleService;

    @Permission(value="shiftCircle_saveOrUpdate")
    @ApiOperation(value = "保存或更新移圈记录",notes = "移圈id存在则更新，不存在则保存")
    @PostMapping("/saveOrUpdate")
    public Response saveOrUpdate(@RequestBody ShiftCircle shiftCircle){
        shiftCircleService.saveOrUpdate(shiftCircle);
        return Response.success();
    }

    @Permission(value="shiftCircle_deleteByIdAll")
    @ApiOperation(value = "批量删除移圈记录")
    @DeleteMapping("/deleteByIdAll")
    public Response deleteByIdAll(@RequestBody List<Integer> ids) {
        shiftCircleService.deleteInBatch(ids);
        return Response.success();
    }

    @Permission(value="shiftCircle_deleteById")
    @ApiOperation(value = "根据id删除移圈记录")
    @DeleteMapping("/deleteById/{scId}")
    public Response deleteById(@PathVariable("scId") Integer scId) {
        shiftCircleService.deleteById(scId);
        return Response.success();
    }

    @Permission(value="shiftCircle_queryAll")
    @ApiOperation(value = "查询所有移圈记录")
    @GetMapping("/queryAll")
    public Response queryAll() {
        List<ShiftCircle> list = shiftCircleService.queryAll();
        return Response.success(list);
    }

    @Permission(value="shiftCircle_query")
    @ApiOperation(value = "分页并根据条件查询移圈以及移圈人员信息")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true,defaultValue = "5"),
            @ApiImplicitParam(name = "scAnimalId", value = "动物编号", paramType = "query"),
            @ApiImplicitParam(name = "scUbreedId",value = "养殖工id",paramType = "query"),
            @ApiImplicitParam(name = "startTime",value = "开始时间",paramType = "query"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",paramType = "query"),
            @ApiImplicitParam(name = "scReason",value = "移圈原因",paramType = "query")
    })
    public Response query(Integer page,Integer pageSize,String scAnimalId,String scUbreedId,String startTime,String endTime,String scReason){
        PageInfo<ShiftCircleExtend> info = shiftCircleService.query(page, pageSize, scAnimalId, scUbreedId, startTime, endTime, scReason);
        return Response.success(info);
    }

    @Permission(value="shiftCircle_queryAllUser")
    @ApiOperation(value = "查询所有移圈人员信息")
    @GetMapping("/queryAllUser")
    public Response queryAllUser() {
        List<ShiftCircleExtend> list = shiftCircleService.queryAllUser();
        return Response.success(list);
    }

}
