package com.briup.framework.web;

import com.briup.framework.bean.ManagerAnimal;
import com.briup.framework.bean.extend.ManagerAnimalExtend;
import com.briup.framework.service.IManagerAnimalService;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @program: product_source
 * @description: 动物管理模块
 * @author: pgc
 * @create:2022-03-28
 **/
@Api(tags = "动物管理接口")
@RestController
@RequestMapping("/animal")
public class ManagerAnimalController {
    @Autowired
    IManagerAnimalService managerAnimalService;
    @Permission(value="animal_saveOrUpdate")
    @ApiOperation(value = "保存或更新动物")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody ManagerAnimal managerAnimal){
       managerAnimalService.saveOrUpdate(managerAnimal);
        return Response.success("操作成功");
    }
    @Permission(value="animal_deleteById")
    @ApiOperation(value = "根据id删除动物")
    @DeleteMapping("/deleteById/{aAnimalId}")
    public Response<String> deleteById(@PathVariable String aAnimalId) {
        managerAnimalService.deleteById(aAnimalId);
        return Response.success("删除成功");
    }
    @Permission(value="animal_deleteByIdAll")
    @ApiOperation(value = "批量删除动物")
    @PostMapping("/deleteByIdAll")
    public Response<String> deleteByIdAll(@RequestBody String[] aIds) {
        for (String aId:aIds) {
            managerAnimalService.deleteById(aId);
        }
        return Response.success("删除成功");
    }
    @Permission(value="animal_query")
    @ApiOperation(value = "分页并根据条件查询动物基本信息以及对应批次和栏圈信息")
    @GetMapping("/query")
    @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页",dataType = "int", required = true, paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小",dataType = "int", required = true, paramType = "query",defaultValue ="5"),
            @ApiImplicitParam(name = "aBatchId", value = "批次编号", paramType = "query"),
            @ApiImplicitParam(name = "aHurdlesId", value = "栏圈编号",paramType = "query"),
            @ApiImplicitParam(name = "aHealthy", value = "健康状态", paramType = "query"),
            @ApiImplicitParam(name = "aGender", value = "性别", paramType = "query")
    })
    public Response<PageInfo<ManagerAnimalExtend>> query(int page, int pageSize, String aBatchId, String aHurdlesId, String aHealthy, String aGender){
        PageInfo<ManagerAnimalExtend> pageVM=managerAnimalService.query(page,pageSize,aBatchId,aHurdlesId,aHealthy,aGender);
        return Response.success(pageVM);
    }
    @Permission(value="animal_findByAnimalId")
    @ApiOperation(value = "根据动物编号查询动物基本信息以及对应批次和栏圈信息")
    @GetMapping("/findByAnimalId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "animalId", value = "动物编号", paramType = "query")
    })
    public Response<ManagerAnimalExtend> findByAnimalId(String animalId){
        ManagerAnimalExtend managerAnimalExtend=managerAnimalService.findByAnimalId(animalId);
        return Response.success(managerAnimalExtend);
    }
}
