package com.briup.framework.web;

import com.briup.framework.bean.ManagerHurdles;
import com.briup.framework.bean.extend.ManagerHurdlesExtend;
import com.briup.framework.service.IManagerHurdlesService;
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
 * @description: 栏圈管理控制类
 * @author: pgc
 * @create:2022-03-24
 **/
@Api(tags = "栏圈管理接口")
@RestController
@RequestMapping("/hurdles")
public class ManagerHurdlesController {
    @Autowired
    IManagerHurdlesService managerHurdlesService;
    @Permission(value="hurdles_saveOrUpdate")
    @ApiOperation(value = "保存或更新栏舍")
        @PostMapping("/saveOrUpdate")
        public Response<String> saveOrUpdate(@RequestBody ManagerHurdles managerHurdles){
            managerHurdlesService.saveOrUpdate(managerHurdles);
            return Response.success("操作成功");
    }
    @Permission(value="hurdles_deleteById")
    @ApiOperation(value = "根据id删除栏圈")
    @DeleteMapping("/deleteById/{hId}")
    public Response<String> deleteById(@PathVariable String hId) {
        managerHurdlesService.deleteById(hId);
        return Response.success("删除成功");
    }
    @Permission(value="hurdles_deleteByIdAll")
    @ApiOperation(value = "批量删除栏圈")
    @PostMapping("/deleteByIdAll")
    public Response<String> deleteByIdAll(@RequestBody String[] hIds) {
        for (String hId:hIds) {
            managerHurdlesService.deleteById(hId);
        }
        return Response.success("删除成功");
    }
    @Permission(value="hurdles_query")
    @ApiOperation(value = "分页并根据条件查询栏圈基本信息以及栏舍信息")
    @GetMapping("/query")
    @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true, paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true, paramType = "query",defaultValue ="5"),
            @ApiImplicitParam(name = "hName", value = "栏圈名称", paramType = "query"),
            @ApiImplicitParam(name = "hMax", value = "栏圈容量",paramType = "query"),
            @ApiImplicitParam(name = "fhName", value = "栏舍名称", paramType = "query"),
            @ApiImplicitParam(name = "hEnable", value = "是否可用", paramType = "query")
    })
    public Response<PageInfo<ManagerHurdlesExtend>> query(int page, int pageSize,String hName,String hMax, String fhName,String hEnable){
        PageInfo<ManagerHurdlesExtend> pageVM=managerHurdlesService.query(page,pageSize,hName,hMax,fhName,hEnable);
        return Response.success(pageVM);
    }
    @Permission(value="hurdles_queryAllMax")
    @ApiOperation(value = "查询所有栏圈容量")
    @GetMapping("/queryAllMax")
    public Response<List<Integer>> queryAllMax() {
        List<Integer> allMax = managerHurdlesService.queryAllMax();
        return Response.success(allMax);
    }
    @Permission(value="hurdles_updateByIdSaved")
    @ApiOperation(value = "根据id更新以存量")
    @GetMapping("/updateByIdSaved/{hId}")
    public Response<String> updateByIdSaved(@PathVariable String hId) {
        managerHurdlesService.updateByIdSaved(hId);
        return Response.success("更新成功");
    }
    @Permission(value="hurdles_selectAllHurdles")
    @ApiOperation(value = "查询所有栏圈")
    @GetMapping("/selectAllHurdles")
    public Response<List<ManagerHurdlesExtend>> selectAllHurdles() {
        List<ManagerHurdlesExtend> list = managerHurdlesService.selectAllHurdles();
        return Response.success(list);
    }
}
