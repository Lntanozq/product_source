package com.briup.framework.web;

import com.briup.framework.bean.QuarantineRegistration;
import com.briup.framework.bean.extend.QuarantineRegistrationExtend;
import com.briup.framework.service.IQuarantineRegistrationService;
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
 * @description: 检疫记录管理
 * @author: nirui
 * @create: 2022-03-31 11:27
 */
@Api(tags = "检疫登记接口")
@RestController
@RequestMapping("/quarantineRegistration")
public class QuarantineRegistrationController {
    @Autowired
    private IQuarantineRegistrationService quarantineRegistrationService;

    @Permission(value="quarantineRegistration_saveOrUpdate")
    @ApiOperation(value = "保存或更新检疫记录",notes = "检疫记录id存在则更新，不存在则保存")
    @PostMapping("/saveOrUpdate")
    public Response saveOrUpdate(@RequestBody QuarantineRegistrationExtend quarantineRegistrationExtend){
        quarantineRegistrationService.saveOrUpdate(quarantineRegistrationExtend);
        return Response.success();
    }

    @Permission(value="quarantineRegistration_deleteByIdAll")
    @ApiOperation(value = "批量删除检疫记录")
    @DeleteMapping("/deleteByIdAll")
    public Response deleteByIdAll(@RequestBody List<Integer> ids) {
        quarantineRegistrationService.deleteInBatch(ids);
        return Response.success();
    }

    @Permission(value="quarantineRegistration_deleteById")
    @ApiOperation(value = "根据id删除检疫记录")
    @DeleteMapping("/deleteById/{grId}")
    public Response deleteById(@PathVariable("grId") Integer grId) {
        quarantineRegistrationService.deleteById(grId);
        return Response.success();
    }

    @Permission(value="quarantineRegistration_queryAll")
    @ApiOperation(value = "查询所有检疫记录")
    @GetMapping("/queryAll")
    public Response queryAll() {
        List<QuarantineRegistration> list = quarantineRegistrationService.queryAll();
        return Response.success(list);
    }


    @Permission(value="quarantineRegistration_query")
    @ApiOperation(value = "分页并根据条件查询检疫记录以检疫人员")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true,defaultValue = "5"),
            @ApiImplicitParam(name = "grBatchId", value = "批次编号", paramType = "query"),
            @ApiImplicitParam(name = "grMechanism",value = "检疫机构",paramType = "query"),
            @ApiImplicitParam(name = "bQualified",value = "检疫结果",paramType = "query"),
            @ApiImplicitParam(name = "grUquarantinerId",value = "检疫人员id",paramType = "query"),
            @ApiImplicitParam(name = "startTime",value = "开始时间",paramType = "query"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",paramType = "query")
    })
    public Response query(Integer page,Integer pageSize,String grBatchId, String grMechanism, String bQualified, String grUquarantinerId, String startTime, String endTime){
        PageInfo<QuarantineRegistrationExtend> info = quarantineRegistrationService.query(page, pageSize, grBatchId, grMechanism, bQualified, grUquarantinerId, startTime, endTime);
        return Response.success(info);
    }

    @Permission(value="quarantineRegistration_queryAllUser")
    @ApiOperation(value = "查询所有检疫人员信息")
    @GetMapping("/queryAllUser")
    public Response queryAllUser() {
        List<QuarantineRegistrationExtend> list = quarantineRegistrationService.queryAllUser();
        return Response.success(list);
    }



}
