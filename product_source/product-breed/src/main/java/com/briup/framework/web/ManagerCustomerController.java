package com.briup.framework.web;


import com.briup.framework.bean.ManagerCustomer;
import com.briup.framework.bean.extend.ManagerCustomerExtend;
import com.briup.framework.service.IManagerCustomerService;
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
 * @description: 客户管理
 * @author: nirui
 * @create: 2022-03-29 11:24
 */
@Api(tags = "客户管理接口")
@RestController
@RequestMapping("/customer")
public class ManagerCustomerController {
    @Autowired
    private IManagerCustomerService managerCustomerService;

    @Permission(value="customer_saveOrUpdate")
    @ApiOperation(value = "保存或更新客户",notes = "客户id存在则更新，不存在则保存")
    @PostMapping("/saveOrUpdate")
    public Response saveOrUpdate(@RequestBody ManagerCustomer managerCustomer){
        managerCustomerService.saveOrUpdate(managerCustomer);
        return Response.success();
    }

    @Permission(value = "customer_deleteByIdAll")
    @ApiOperation(value = "批量删除客户")
    @DeleteMapping("/deleteByIdAll")
    public Response deleteByIdAll(@RequestBody List<Integer> ids) {
        managerCustomerService.deleteInBatch(ids);
        return Response.success();
    }

    @Permission(value = "customer_deleteById")
    @ApiOperation(value = "根据id删除客户")
    @DeleteMapping("/deleteById/{cId}")
    public Response deleteById(@PathVariable("cId") Integer cId) {
        managerCustomerService.deleteById(cId);
        return Response.success();
    }

    @Permission(value = "customer_queryAll")
    @ApiOperation(value = "查询所有客户信息")
    @GetMapping("/queryAll")
    public Response queryAll() {
        List<ManagerCustomer> list = managerCustomerService.queryAll();
        return Response.success(list);
    }

    @Permission(value = "customer_query")
    @ApiOperation(value = "分页并根据条件查询客户以及销售信息")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true,defaultValue = "5"),
            @ApiImplicitParam(name = "cCompany", value = "公司名称", paramType = "query"),
            @ApiImplicitParam(name = "cName",value = "客户名称",paramType = "query"),
            @ApiImplicitParam(name = "cUsaleId",value = "销售ID",paramType = "query"),
            @ApiImplicitParam(name = "cLevel",value = "等级",paramType = "query"),
    })
    public Response query(Integer page,Integer pageSize,String cCompany,String cName,String cUsaleId,String cLevel){
        PageInfo<ManagerCustomerExtend> info = managerCustomerService.query(page, pageSize, cCompany, cName, cUsaleId, cLevel);
        return Response.success(info);
    }

    @Permission(value = "customer_queryAllUser")
    @ApiOperation(value = "查询所有接待人员信息")
    @GetMapping("/queryAllUser")
    public Response queryAllUser() {
        List<ManagerCustomerExtend> list = managerCustomerService.queryAllUser();
        return Response.success(list);
    }

}
