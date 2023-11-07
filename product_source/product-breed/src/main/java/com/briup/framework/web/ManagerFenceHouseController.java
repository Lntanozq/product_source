package com.briup.framework.web;

import com.briup.framework.bean.ManagerFenceHouse;
import com.briup.framework.bean.extend.ManagerFenceHouseExtend;
import com.briup.framework.service.IManagerFenceHouseService;
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
 * @description: 栏舍管理控制类
 * @author: pgc
 * @create:2022-03-23
 **/
@Api(tags = "栏舍管理接口")
@RestController
@RequestMapping("/fenceHouse")
public class ManagerFenceHouseController {
    @Autowired
    IManagerFenceHouseService managerFenceHouseService;

    @Permission(value="fenceHouse_saveOrUpdate")
    @ApiOperation(value = "保存或更新栏舍")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody ManagerFenceHouse managerFenceHouse){
        managerFenceHouseService.saveOrUpdate(managerFenceHouse);
        return Response.success("操作成功");
    }

    @Permission(value="fenceHouse_deleteById")
    @ApiOperation(value = "根据id删除栏舍")
    @DeleteMapping("/deleteById/{fhId}")
    public Response<String> deleteById(@PathVariable String fhId) {
        managerFenceHouseService.deleteById(fhId);
        return Response.success("删除成功");
    }
    @Permission(value="fenceHouse_deleteByIdAll")
    @ApiOperation(value = "批量删除栏舍")
    @PostMapping("/deleteByIdAll")
    public Response<String> deleteByIdAll(@RequestBody String[] fhIds) {
        for (String fhId:fhIds) {
            managerFenceHouseService.deleteById(fhId);
        }
        return Response.success("删除成功");
    }
    @Permission(value="fenceHouse_selectById")
    @ApiOperation(value = "根据id查询栏舍以及对应栏圈信息")
    @GetMapping("/selectById")
    public Response<ManagerFenceHouseExtend> selectById(String fhId) {
        ManagerFenceHouseExtend managerFenceHouse = managerFenceHouseService.selectById(fhId);
        return Response.success(managerFenceHouse);
    }
    @Permission(value="fenceHouse_query")
    @ApiOperation(value = "分页并根据条件查询栏舍基本信息以及栏圈数量")
    @GetMapping("/query")
    @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true, paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true, paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "fhName", value = "栏舍名称", paramType = "query")
    })
    public Response<PageInfo<ManagerFenceHouseExtend>> query(int page, int pageSize, String fhName){
        PageInfo<ManagerFenceHouseExtend> pageVM=managerFenceHouseService.query(page,pageSize,fhName);
        return Response.success(pageVM);
    }
    @Permission(value="fenceHouse_queryAll")
    @ApiOperation(value = "查询所有栏舍信息")
    @GetMapping("/queryAll")
    public Response<List<ManagerFenceHouse>> queryAll() {
        List<ManagerFenceHouse> list = managerFenceHouseService.queryAll();
        return Response.success(list);
    }
}
