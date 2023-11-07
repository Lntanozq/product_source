package com.briup.framework.web;

import com.briup.framework.base.bean.basic.BaseAccount;
import com.briup.framework.bean.ManagerFreezer;
import com.briup.framework.bean.extend.ManagerFreezerExtend;
import com.briup.framework.service.IManagerFreezerService;
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
 * @description: 冷库管理控制层
 * @author: pgc
 * @create:2022-03-29
 **/
@Api(tags = "冷库管理接口")
@RestController
@RequestMapping("/freezer")
public class ManagerFreezerController {
    @Autowired
    IManagerFreezerService managerFreezerService;
    @Permission(value="freezer_saveOrUpdate")
    @ApiOperation(value = "保存或更新冷库")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody ManagerFreezer managerFreezer){
       managerFreezerService.saveOrUpdate(managerFreezer);
        return Response.success("操作成功");
    }
    @Permission(value="freezer_selectAllAccount")
    @ApiOperation(value = "查询负责人信息")
    @GetMapping("/selectAllAccount")
    public Response<List<BaseAccount>> selectAllAccount() {
        List<BaseAccount> list = managerFreezerService.queryFreezer();
        return Response.success(list);
    }
    @Permission(value="freezer_query")
    @ApiOperation(value = "分页并根据条件查询冷库基本信息以及负责人信息")
    @GetMapping("/query")
    @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true, paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true, paramType = "query",defaultValue ="5"),
            @ApiImplicitParam(name = "fzName", value = "冷库名称", paramType = "query"),
            @ApiImplicitParam(name = "fzNum", value = "冷库容量",paramType = "query"),
            @ApiImplicitParam(name = "fzUcoldId", value = "负责人编号", paramType = "query")
    })
    public Response<PageInfo<ManagerFreezerExtend>> query(int page, int pageSize, String fzName, String fzNum, String fzUcoldId){
        PageInfo<ManagerFreezerExtend> pageVM=managerFreezerService.query(page,pageSize,fzName,fzNum,fzUcoldId);
        return Response.success(pageVM);
    }
    @Permission(value="freezer_deleteById")
    @ApiOperation(value = "根据id删除冷库")
    @DeleteMapping("/deleteById/{fzId}")
    public Response<String> deleteById(@PathVariable int fzId) {
        managerFreezerService.deleteById(fzId);
        return Response.success("删除成功");
    }
    @Permission(value="freezer_deleteByIdAll")
    @ApiOperation(value = "批量删除冷库")
    @PostMapping("/deleteByIdAll")
    public Response<String> deleteByIdAll(@RequestBody int[] fzIds) {
        for (int fzId:fzIds) {
            managerFreezerService.deleteById(fzId);
        }
        return Response.success("删除成功");
    }
    @Permission(value="freezer_squeryAll")
    @ApiOperation(value = "不分页查询所有冷库基本信息")
    @GetMapping("/queryAll")
    public Response<List<ManagerFreezer>> queryAll() {
        List<ManagerFreezer> list = managerFreezerService.queryAll();
        return Response.success(list);
    }
}
