package com.briup.framework.web;

import com.briup.framework.bean.ManagerDisease;
import com.briup.framework.bean.ManagerVaccines;
import com.briup.framework.service.IManagerDiseaseService;
import com.briup.framework.service.IManagerVaccinesService;
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
 * @description: 病症管理控制类
 * @author: shaoyb
 * @create: 2022-03-28
 **/
@Api(tags = "病症管理接口")
@RestController
@RequestMapping("/disease")
public class ManagerDiseaseController {
    @Autowired
    private IManagerDiseaseService managerDiseaseService;

    @ApiOperation(value = "保存或更新病症")
    @Permission(value="disease_saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody ManagerDisease managerDisease){
        managerDiseaseService.saveOrUpdate(managerDisease);
        return Response.success("操作成功");
    }

    @ApiOperation(value = "根据id删除病症")
    @Permission(value="disease_deleteById")
    @ApiImplicitParams({			//含义			数据类型			来源				是否必须
            @ApiImplicitParam(name="dId", value="项目id", dataType="int", paramType="path", required=true)
    })
    @DeleteMapping("/deleteById/{dId}")
    public Response<String> deleteById(@PathVariable Integer dId) {
        managerDiseaseService.deleteById(dId);
        return Response.success("删除成功");
    }

    @ApiOperation(value = "批量删除病症")
    @Permission(value="disease_deleteByIdAll")
    @PostMapping("/deleteByIdAll")
    public Response<String> deleteByIdAll(@RequestBody List<Integer> dIds) {
        managerDiseaseService.deleteAllByIds(dIds);
        return Response.success("删除成功");
    }

    @ApiOperation(value = "查询所有病症")
    @Permission(value="disease_queryAll")
    @GetMapping("/queryAll")
    public Response<List<ManagerDisease>> queryAll() {
        List<ManagerDisease> list = managerDiseaseService.queryAll();
        return Response.success(list);
    }

    @ApiOperation(value = "分页并根据条件查询病症基本信息")
    @Permission(value="disease_query")
    @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true, paramType = "query"),
            @ApiImplicitParam(name = "dName", value = "病症名称", paramType = "query"),
            @ApiImplicitParam(name = "dType", value = "病症类型", paramType = "query")
    })
    @GetMapping("/query")
    public Response<PageInfo<ManagerDisease>> query(Integer page, Integer pageSize, String dName, String dType){
        PageInfo<ManagerDisease> pageVM=managerDiseaseService.query(page,pageSize,dName,dType);
        return Response.success(pageVM);
    }

    @ApiOperation(value = "根据病症编号查询病症")
    @Permission(value="disease_selectById")
    @GetMapping("/selectById/{dId}")
    public Response selectById(@PathVariable("dId")  Integer dId) {
        ManagerDisease managerDisease = managerDiseaseService.selectById(dId);
        return Response.success(managerDisease);
    }
}
