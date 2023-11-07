package com.briup.framework.web;

import com.briup.framework.bean.extend.AnimalRecord;
import com.briup.framework.bean.extend.ManagerBatchAnimalExtend;
import com.briup.framework.service.IOutCircleService;
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
 * @Description: 生猪出栏控制层
 * @date 2022/3/30 15:34
 */
@Api(tags = "生猪出栏接口")
@RestController
@RequestMapping("/outCircle")
public class OutCircleController {
    @Autowired
    IOutCircleService outCircleService;
    @Permission(value="outCircle_query")
    @ApiOperation(value = "根据动物过程状态查询动物基本信息以及对应批次信息，填已检疫")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aStatus", value = "动物过程状态", required = true,paramType = "query")
    })
    public Response<List<ManagerBatchAnimalExtend>> query( String aStatus){
        List<ManagerBatchAnimalExtend> list=outCircleService.query(aStatus);
        return Response.success(list);
    }
    @Permission(value="outCircle_saveOrUpdate")
    @ApiOperation(value = "插入生猪出栏信息")
    @PostMapping("/saveOrUpdate")
    public Response<String> saveOrUpdate(@RequestBody AnimalRecord animalRecord){
        outCircleService.saveOrUpdate(animalRecord);
        return Response.success("操作成功");
    }
}
