package com.briup.framework.web;

import com.briup.framework.bean.ManagerBatch;
import com.briup.framework.bean.ManagerVaccines;
import com.briup.framework.bean.extend.ManagerBatchExtend;
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
 * @description: 疫苗管理控制类
 * @author: shaoyb
 * @create:2022-03-28
 **/
@Api(tags = "疫苗管理接口")
@RestController
@RequestMapping("/vaccines")
public class ManagerVaccinesController {
    @Autowired
    private IManagerVaccinesService managerVaccinesService;

    @ApiOperation(value = "保存或更新疫苗",notes = "疫苗编号存在则更新，不存在则保存")
    @Permission(value="vaccines_saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    public Response saveOrUpdate(@RequestBody ManagerVaccines managerVaccines){
        managerVaccinesService.saveOrUpdate(managerVaccines);
        return Response.success();
    }

    @ApiOperation(value = "根据id删除疫苗")
    @Permission(value="vaccines_deleteById")
    @DeleteMapping("/deleteById/{vVaccinesId}")
    public Response<String> deleteById(@PathVariable String vVaccinesId) {
        managerVaccinesService.deleteById(vVaccinesId);
        return Response.success("删除成功");
    }

    @ApiOperation(value = "批量删除疫苗")
    @Permission(value="vaccines_deleteByIdAll")
    @PostMapping("/deleteByIdAll")
    public Response<String> deleteAllByIds(@RequestBody List<String> vVaccinesIds) {
        managerVaccinesService.deleteAllByIds(vVaccinesIds);
        return Response.success("批量删除成功");
    }

    @ApiOperation(value = "查询所有疫苗")
    @Permission(value="vaccines_queryAll")
    @GetMapping("/queryAll")
    public Response<List<ManagerVaccines>> queryAll() {
        List<ManagerVaccines> list = managerVaccinesService.queryAll();
        return Response.success(list);
    }

    //编号、名称、供应商、类型
    @ApiOperation(value = "分页并根据条件查询疫苗基本信息")
    @Permission(value="vaccines_query")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码",dataType = "int", required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",dataType = "int", required = true,defaultValue = "5"),
            @ApiImplicitParam(name = "vVaccinesId", value = "疫苗编号", paramType = "query"),
            @ApiImplicitParam(name = "vName",value = "疫苗名称",paramType = "query"),
            @ApiImplicitParam(name = "vSupplier",value = "疫苗供应商",paramType = "query"),
            @ApiImplicitParam(name = "vType",value = "疫苗类型",paramType = "query")
    })
    public Response query(Integer page,Integer pageSize,String vVaccinesId,String vName,String vSupplier,String vType){
        PageInfo<ManagerVaccines> info = managerVaccinesService.query(page, pageSize, vVaccinesId, vName, vSupplier, vType);
        return Response.success(info);
    }

    @ApiOperation(value = "根据疫苗编号查询疫苗")
    @Permission(value="vaccines_selectById")
    @GetMapping("/selectById/{vVaccinesId}")
    public Response selectById(@PathVariable("vVaccinesId")  String vVaccinesId) {
        ManagerVaccines managerVaccines = managerVaccinesService.selectById(vVaccinesId);
        return Response.success(managerVaccines);
    }
}
