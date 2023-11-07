package com.briup.framework.web;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.ManagerDisease;
import com.briup.framework.bean.ManagerSlaughteringGroup;
import com.briup.framework.bean.extend.ManagerFenceHouseExtend;
import com.briup.framework.bean.extend.ManagerSlaughteringGroupExtend;
import com.briup.framework.service.IManagerSlaughteringGroupService;
import com.briup.framework.utils.security.Permission;
import com.briup.framework.utils.web.Response;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @program: product_source
 * @description: 屠宰组管理控制类
 * @author: shaoyb
 * @create: 2022-03-29 19:50
 **/
@Api(tags = "屠宰组管理接口")
@RestController
@RequestMapping("/slaughteringGroup")
public class ManagerSlaughteringGroupController {

    @Autowired
    private IManagerSlaughteringGroupService managerSlaughteringGroupService;

    @ApiOperation(value = "保存或更新屠宰组基本信息")
    @Permission(value = "slaughteringGroup_saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody ManagerSlaughteringGroup slaughteringGroup){
        managerSlaughteringGroupService.saveOrUpdate(slaughteringGroup);
        return Response.success("操作成功");
    }

    @ApiOperation(value = "更新屠宰组中人员信息(含新增、删除、更新)")
    @Permission(value = "slaughteringGroup_updateAccount")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sgId", value="屠宰组id", dataType="int", paramType="query", required=true),
            @ApiImplicitParam(name="accountIds", value="人员id数组", dataType="String", paramType="query", required=true, allowMultiple=true),
            @ApiImplicitParam(name="sgNum", value="人员数量", dataType="int", paramType="query", required=true)
    })
    @GetMapping("/updateAccount")
    public Response<String> updateSlaughteringGroupAccount(Integer sgId,String[] accountIds,Integer sgNum){
        managerSlaughteringGroupService.updateSlaughteringGroupAccount(sgId,accountIds,sgNum);
        return Response.success();
    }

    @ApiOperation(value = "根据屠宰组id删除屠宰组")
    @Permission(value = "slaughteringGroup_deleteById")
    @DeleteMapping("/deleteById/{sgId}")
    public Response<String> deleteSlaughteringGroupBySgId(@PathVariable("sgId") Integer sgId){
        managerSlaughteringGroupService.deleteSlaughteringGroupBySgId(sgId);
        return Response.success();
    }

    @ApiOperation(value = "根据屠宰组ids批量删除屠宰组")
    @Permission(value = "slaughteringGroup_deleteByIdAll")
    @DeleteMapping("/deleteByIdAll")
    public Response<String> deleteAllSlaughteringGroupBySgIds(@RequestBody List<Integer> sgroupIds){
        managerSlaughteringGroupService.deleteAllSlaughteringGroupBySgIds(sgroupIds);
        return Response.success();
    }

    @ApiOperation(value = "查询所有屠宰组信息")
    @Permission(value = "slaughteringGroup_queryAll")
    @GetMapping("/queryAll")
    public Response<List<ManagerSlaughteringGroupExtend>> queryAll(){
        List<ManagerSlaughteringGroupExtend> sGroupList = managerSlaughteringGroupService.queryAll();
        return Response.success(sGroupList);
    }

    @ApiOperation(value = "分页并根据条件查询屠宰组信息(含组员信息)")
    @Permission(value = "slaughteringGroup_query")
    @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true, paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true, paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "sgName", value = "屠宰组名称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sgNum", value = "组员数量", dataType = "int", paramType = "query")
    })
    @GetMapping("/query")
    public Response<PageInfo<ManagerSlaughteringGroupExtend>> query(Integer page, Integer pageSize, String sgName, Integer sgNum){
        PageInfo<ManagerSlaughteringGroupExtend> info = managerSlaughteringGroupService.query(page,pageSize,sgName,sgNum);
        return Response.success(info);
    }

    @ApiOperation(value = "根据屠宰组id查询屠宰组完整信息(含组员信息)")
    @Permission(value = "slaughteringGroup_selectById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sgId", value = "屠宰组id",dataType = "int", required = true, paramType = "query")
    })
    @GetMapping("/selectById")
    public Response<ManagerSlaughteringGroupExtend> selectById(Integer sgId) {
        ManagerSlaughteringGroupExtend mSGroupExtend = managerSlaughteringGroupService.selectById(sgId);
        return Response.success(mSGroupExtend);
    }

    @ApiOperation(value = "查询所有屠宰人员信息")
    @Permission(value = "slaughteringGroup_selectAllAccount")
    @GetMapping("/selectAllAccount")
    public Response<List<BaseAccount>> selectAllAccount() {
        List<BaseAccount> list =managerSlaughteringGroupService.queryAllSlaughteringAccount();
        return Response.success(list);
    }
}
