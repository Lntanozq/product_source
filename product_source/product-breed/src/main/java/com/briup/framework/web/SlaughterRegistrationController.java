package com.briup.framework.web;

import com.briup.framework.bean.extend.AnimalRecord;
import com.briup.framework.bean.extend.ManagerBatchAnimalExtend;
import com.briup.framework.bean.extend.SlaughterRegistrationExtend;
import com.briup.framework.service.ISlaughterRegistrationService;
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
 * @Description: 屠宰登记控制层
 * @date 2022/3/31 9:53
 */
@Api(tags = "屠宰登记接口")
@RestController
@RequestMapping("/slaughterRegistration")
public class SlaughterRegistrationController {
    @Autowired
    ISlaughterRegistrationService slaughterRegistrationService;

    @Permission(value="slaughterRegistration_query")
    @ApiOperation(value = "根据动物过程状态查询动物基本信息以及对应批次信息，填已出栏")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aStatus", value = "动物过程状态", required = true,paramType = "query")
    })
    public Response<List<ManagerBatchAnimalExtend>> query(String aStatus){
        List<ManagerBatchAnimalExtend> list=slaughterRegistrationService.query(aStatus);
        return Response.success(list);
    }
    @Permission(value="slaughterRegistration_saveOrUpdate")
    @ApiOperation(value = "插入屠宰登记信息")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody AnimalRecord animalRecord){
        slaughterRegistrationService.saveOrUpdate(animalRecord);
        return Response.success("操作成功");
    }

    /**
     * @author shaoyb
     * @date 2022/04/12 10:16
     * @Description 根据动物id查询屠宰记录(含基本信息、屠宰组信息、组员信息)
     */
    @Permission(value="slaughterRegistration_selectById")
    @ApiOperation(value = "据动物id查询屠宰记录(含基本信息、屠宰组信息、组员信息)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aAnimalId", value = "动物id", required = true, paramType = "query")
    })
    @GetMapping("/selectById")
    public Response<List<SlaughterRegistrationExtend>> selectById(String aAnimalId) {
        List<SlaughterRegistrationExtend> list = slaughterRegistrationService.selectSlaughterRecordByAnimalId(aAnimalId);
        return Response.success(list);
    }
}
